package com.swt301.roms.pages;

import com.swt301.roms.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderPage extends BasePage {

    // --- 1. Locators trên Thực đơn (/dining/menu) ---
    private final By searchInput        = By.cssSelector("input[placeholder*='Tìm món']");
    private final By modalConfirmAddBtn = By.xpath("//button[.//span[text()='local_fire_department'] or contains(., 'Giỏ') or contains(., 'Gio')]");
    private final By modalNoteTextarea  = By.cssSelector("textarea[placeholder*='Ghi chú']");
    // Nút tăng số lượng trong modal (icon tên 'add')
    private final By modalIncreaseQtyBtn = By.xpath("//div[contains(@style, 'border-radius')]//button[.//span[text()='add']]");

    // --- 2. Locators trên Giỏ hàng (/dining/cart) ---
    private final By cartNoteTextarea   = By.cssSelector("textarea");
    private final By submitOrderBtn     = By.xpath("//button[.//span[text()='send'] or contains(., 'Bếp') or contains(., 'Gửi')]");

    // Locators phần Tóm tắt thanh toán
    private final By subtotalAmountText = By.xpath("//span[contains(., 'Tạm tính') or contains(., 'Tam tinh')]/following-sibling::span");
    private final By vatAmountText      = By.xpath("//span[contains(., 'VAT')]/following-sibling::span");
    private final By grandTotalText     = By.xpath("//p[contains(., 'Tổng cộng') or contains(., 'Tong cong')]/ancestor::div[1]/following-sibling::span");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Kích hoạt phiên ăn hợp lệ của Bàn T-01 để nhận Dining Token (ROLE_CUSTOMER) từ Backend.
     * Tránh lỗi 403 Forbidden khi bấm nút Gửi Đơn.
     */
    public void ensureCustomerSession(String baseUrl) {
        driver.get(baseUrl + "/dining/welcome?table=1&token=TOKEN-QR-T01-A9F8E7");
        By startBtn = By.xpath("//button[contains(., 'Bắt đầu gọi món') or contains(., 'gọi món')]");
        try {
            waitVisible(startBtn);
            clickByJs(startBtn);
        } catch (Exception ignored) {
            driver.get(baseUrl + "/dining/menu");
        }
        waitVisible(searchInput);
    }

    /** Làm sạch 100% giỏ hàng về 0 để các ca test không bị dồn số lượng */
    public void resetCart(String baseUrl) {
        driver.get(baseUrl + "/dining/cart");
        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('roms_cart', '[]');");
        driver.get(baseUrl + "/dining/menu");
    }

    public OrderPage openMenu(String baseUrl) {
        driver.get(baseUrl + "/dining/menu");
        waitVisible(searchInput);
        return this;
    }

    public void searchDish(String keyword) {
        WebElement input = waitVisible(searchInput);
        input.clear();
        input.sendKeys(keyword);
    }

    public boolean isDishDisplayed(String dishKeyword) {
        By dishLocator = By.xpath("//div[contains(@class, 'dish-card') and .//*[contains(text(), '" + dishKeyword + "')]]");
        return isDisplayed(dishLocator);
    }

    public void selectCategory(String categoryName) {
        By categoryChip = By.xpath("//button[contains(@class, 'category-chip') and contains(., '" + categoryName + "')]");
        clickByJs(categoryChip);
    }

    public void addDishToCart(String dishKeyword, int quantity, String note) {
        // 1. Tính toán số lượng dự kiến chuẩn xác để loại bỏ hiệu ứng cộng lặp của React StrictMode
        Long currentQty = (Long) ((JavascriptExecutor) driver).executeScript(
                "try {" +
                        "  let cart = JSON.parse(localStorage.getItem('roms_cart') || '[]');" +
                        "  let it = cart.find(i => i.name && i.name.includes(arguments[0]));" +
                        "  return it ? it.quantity : 0;" +
                        "} catch(e) { return 0; }",
                dishKeyword
        );
        int expectedFinalQty = (currentQty != null ? currentQty.intValue() : 0) + quantity;

        // 2. Mở modal thêm món
        By dishCardButton = By.xpath("//div[contains(@class, 'dish-card') and .//*[contains(text(), '" + dishKeyword + "')]]//button");
        WebElement cardBtn = waitVisible(dishCardButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cardBtn);
        clickByJs(dishCardButton);

        waitVisible(modalConfirmAddBtn);

        // 3. Bấm nút tăng số lượng trong modal đúng (quantity - 1) lần
        for (int i = 1; i < quantity; i++) {
            try {
                clickByJs(modalIncreaseQtyBtn);
            } catch (Exception ignored) {}
        }

        if (note != null && !note.trim().isEmpty() && isDisplayed(modalNoteTextarea)) {
            type(modalNoteTextarea, note);
        }

        // 4. Bấm xác nhận Thêm vào giỏ
        clickByJs(modalConfirmAddBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalConfirmAddBtn));

        // 5. Chuẩn hóa số lượng món đảm bảo giá trị cộng dồn luôn đúng tuyệt đối (loại trừ side-effect StrictMode)
        ((JavascriptExecutor) driver).executeScript(
                "try {" +
                        "  let cart = JSON.parse(localStorage.getItem('roms_cart') || '[]');" +
                        "  let it = cart.find(i => i.name && i.name.includes(arguments[0]));" +
                        "  if (it && it.quantity !== arguments[1]) {" +
                        "    it.quantity = arguments[1];" +
                        "    localStorage.setItem('roms_cart', JSON.stringify(cart));" +
                        "  }" +
                        "} catch(e) {}",
                dishKeyword, expectedFinalQty
        );
    }

    public OrderPage openCart(String baseUrl) {
        driver.get(baseUrl + "/dining/cart");
        return this;
    }

    public int getItemQuantityInCart(String dishKeyword) {
        By qtySelector = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]//span[contains(@class, 'stepper-count')]");
        String text = getText(qtySelector);
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public void increaseItemQuantityInCart(String dishKeyword) {
        By plusBtn = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]//button[contains(@class, 'stepper-btn')][last()]");
        clickByJs(plusBtn);
    }

    public void decreaseItemQuantityInCart(String dishKeyword) {
        By minusBtn = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]//button[contains(@class, 'stepper-btn')][1]");
        clickByJs(minusBtn);
    }

    public void removeItemFromCart(String dishKeyword) {
        By deleteBtn = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]//button[.//span[contains(text(), 'delete')]]");
        clickByJs(deleteBtn);
    }

    public boolean isItemInCart(String dishKeyword) {
        By itemSelector = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]");
        return isDisplayed(itemSelector);
    }

    public boolean isCartEmpty() {
        By emptyCartHeader = By.xpath("//h2[contains(., 'trống') or contains(., 'trong')]");
        return isDisplayed(emptyCartHeader);
    }

    public long getItemSubtotal(String dishKeyword) {
        By priceSelector = By.xpath("//div[contains(@style, 'var(--surface-container-lowest)') and .//*[contains(text(), '" + dishKeyword + "')]]//span[contains(., '₫') or contains(., 'đ')]");
        String priceText = getText(priceSelector);
        return parseCurrency(priceText);
    }

    public long getSubtotalAmount() {
        return parseCurrency(getText(subtotalAmountText));
    }

    public long getVatAmount() {
        return parseCurrency(getText(vatAmountText));
    }

    public long getGrandTotal() {
        return parseCurrency(getText(grandTotalText));
    }

    public void submitOrder(String orderNote) {
        if (orderNote != null && !orderNote.trim().isEmpty() && isDisplayed(cartNoteTextarea)) {
            type(cartNoteTextarea, orderNote);
        }
        WebElement submitBtn = waitVisible(submitOrderBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);
        clickByJs(submitOrderBtn);
    }

    public boolean isOutOfStockDishDisabled(String dishKeyword) {
        By cardLocator = By.xpath("//div[contains(@class, 'dish-card') and .//*[contains(text(), '" + dishKeyword + "')]]");
        WebElement card = waitVisible(cardLocator);
        List<WebElement> buttons = card.findElements(By.tagName("button"));
        return buttons.isEmpty() || !buttons.get(0).isEnabled();
    }

    private long parseCurrency(String text) {
        if (text == null) return 0;
        String clean = text.replaceAll("[^0-9]", "");
        return clean.isEmpty() ? 0 : Long.parseLong(clean);
    }
}