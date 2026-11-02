package com.swt301.roms.pages;

import com.swt301.roms.base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Page Object Model đại diện cho màn hình Đặt Bàn Trực Tuyến (/reservation).
 * Khớp 100% với cấu trúc JSX thực tế của ROMS:
 * - Bước 1: Ngày (mobile-date-quick-chips), Khung giờ (mobile-time-grid), Số khách (mobile-guest-grid).
 * - Bước 2: Khu vực bàn (mobile-zone-card).
 * - Bước 3: Đặt trước món ăn (Pre-order - có nút tiếp tục).
 * - Bước 4: Thông tin khách hàng & Xác nhận đặt bàn.
 */
public class ReservationPage extends BasePage {

    // === Step 1 Locators: Ngày, Giờ & Số lượng khách ===
    private final By todayDateChip       = By.xpath("//div[contains(@class, 'mobile-date-quick-chips')]//button[1]");
    private final By tomorrowDateChip    = By.xpath("//div[contains(@class, 'mobile-date-quick-chips')]//button[2]");
    private final By customDateBtn       = By.xpath("//div[contains(@class, 'mobile-date-quick-chips')]//button[last()]");
    private final By customDateInput     = By.cssSelector("input[type='date']");

    private final By customTimeBtn       = By.xpath("//div[contains(@class, 'mobile-time-grid')]//button[contains(., 'Giờ khác')]");
    private final By customTimeInput     = By.cssSelector("input[type='time']");

    private final By customGuestBtn      = By.xpath("//div[contains(@class, 'mobile-guest-grid')]//button[contains(., 'Số khác')]");
    private final By customGuestInput    = By.cssSelector("input[type='number']");

    private final By step1NextBtn        = By.xpath("//button[contains(., 'Tiếp tục chọn khu vực')]");

    // === Step 2 Locators: Chọn khu vực bàn ===
    private final By step2NextBtn        = By.xpath("//button[contains(., 'Tiếp tục chọn món') or contains(., 'chọn món')]");
    private final By zoneCardFirst       = By.xpath("//div[contains(@class, 'reservation-step-card')]//div[contains(@style, 'cursor: pointer')][1]");

    // === Step 3 Locators: Đặt trước món ăn (Pre-order) ===
    private final By step3NextBtn        = By.xpath("//button[contains(., 'Tiếp tục điền thông tin') or contains(., 'Tiếp tục')]");

    // === Step 4 Locators: Thông tin liên hệ & Xác nhận ===
    private final By customerNameInput   = By.cssSelector("input[placeholder*='Nguyễn Văn A']");
    private final By customerPhoneInput  = By.cssSelector("input[placeholder*='090xxxxxxx']");
    private final By customerEmailInput  = By.cssSelector("input[placeholder*='email@example.com']");
    private final By confirmBookingBtn   = By.xpath("//button[contains(., 'Xác Nhận Giữ Chỗ Ngay') or contains(., 'Xác Nhận')]");

    // === Modal Xác Nhận Thành Công ===
    private final By successModal        = By.xpath("//div[contains(., 'Cảm ơn quý khách') and contains(., 'yêu cầu giữ chỗ')]");
    private final By bookingCodeSpan     = By.xpath("//span[contains(@style, 'monospace') or contains(text(), 'RSV-')]");

    public ReservationPage(WebDriver driver) {
        super(driver);
    }

    public ReservationPage open(String baseUrl) {
        driver.get(baseUrl + "/reservation");
        waitVisible(step1NextBtn);
        return this;
    }

    /**
     * Thiết lập Ngày, Giờ và Số lượng khách ở Bước 1.
     */
    public void setupStep1(int dateOffsetDays, String time, int partySize) {
        // 1. Chọn ngày
        if (dateOffsetDays == 0) {
            clickByJs(todayDateChip);
        } else if (dateOffsetDays == 1) {
            clickByJs(tomorrowDateChip);
        } else {
            clickByJs(customDateBtn);
            LocalDate targetDate = LocalDate.now().plusDays(dateOffsetDays);
            String dateStr = targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            WebElement dateElement = waitVisible(customDateInput);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; " +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    dateElement, dateStr);
        }

        // 2. Chọn khung giờ (tìm trong mobile-time-grid)
        By presetTimeLocator = By.xpath("//div[contains(@class, 'mobile-time-grid')]//button[contains(text(), '" + time + "')]");
        if (isDisplayed(presetTimeLocator)) {
            clickByJs(presetTimeLocator);
        } else {
            clickByJs(customTimeBtn);
            WebElement timeElement = waitVisible(customTimeInput);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; " +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    timeElement, time);
        }

        // 3. Chọn số lượng khách (tìm trong mobile-guest-grid)
        if (partySize == 2 || partySize == 4 || partySize == 6 || partySize == 8 || partySize == 10) {
            By presetGuestLocator = By.xpath("//div[contains(@class, 'mobile-guest-grid')]//button[contains(., '" + partySize + "')]");
            clickByJs(presetGuestLocator);
        } else {
            clickByJs(customGuestBtn);
            WebElement guestElement = waitVisible(customGuestInput);
            guestElement.clear();
            guestElement.sendKeys(String.valueOf(partySize));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    guestElement);
        }
    }

    /** Bấm tiếp tục từ Bước 1 sang Bước 2 */
    public void proceedFromStep1ToStep2() {
        WebElement next = waitVisible(step1NextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", next);
        clickByJs(step1NextBtn);
        waitVisible(step2NextBtn);
    }

    /** Bấm tiếp tục từ Bước 2 sang Bước 3 */
    public void proceedFromStep2ToStep3() {
        if (isDisplayed(zoneCardFirst)) {
            clickByJs(zoneCardFirst);
        }
        WebElement next = waitVisible(step2NextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", next);
        clickByJs(step2NextBtn);
        waitVisible(step3NextBtn);
    }

    /** Bỏ qua đặt trước món ăn và tiếp tục từ Bước 3 sang Bước 4 */
    public void proceedFromStep3ToStep4() {
        WebElement next = waitVisible(step3NextBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", next);
        clickByJs(step3NextBtn);
        waitVisible(confirmBookingBtn);
    }

    /** Điền thông tin khách hàng ở Bước 4 */
    public void fillCustomerInfo(String fullName, String phone, String email) {
        WebElement nameInput = waitVisible(customerNameInput);
        nameInput.clear();
        if (fullName != null && !fullName.isEmpty()) {
            nameInput.sendKeys(fullName);
        }

        WebElement phoneInput = waitVisible(customerPhoneInput);
        phoneInput.clear();
        if (phone != null && !phone.isEmpty()) {
            phoneInput.sendKeys(phone);
        }

        if (email != null && !email.isEmpty() && isDisplayed(customerEmailInput)) {
            WebElement emailElement = waitVisible(customerEmailInput);
            emailElement.clear();
            emailElement.sendKeys(email);
        }
    }

    /** Nhấn nút Xác nhận đặt bàn ở Bước 4 */
    public void submitReservation() {
        WebElement submitBtn = waitVisible(confirmBookingBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);
        clickByJs(confirmBookingBtn);
    }

    /** Kiểm tra xem Modal thành công có hiển thị không */
    public boolean isConfirmationModalDisplayed() {
        return isDisplayed(successModal);
    }

    /** Lấy mã đặt bàn từ Modal xác nhận */
    public String getBookingCode() {
        if (isDisplayed(bookingCodeSpan)) {
            return getText(bookingCodeSpan);
        }
        return "";
    }

    /** Lấy nội dung Alert cảnh báo của trình duyệt và đóng alert */
    public String getAndDismissAlertText() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (Exception e) {
            return "";
        }
    }

    /** Kiểm tra xem có xuất hiện Browser Alert không */
    public boolean hasAlert() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
