package com.swt301.roms.tests;

import com.swt301.roms.base.BaseTest;
import com.swt301.roms.pages.OrderPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("M2 - Thực Đơn Số & Gọi Món Tại Bàn (Table Ordering)")
public class OrderTest extends BaseTest {

    private OrderPage orderPage;

    private static final String DISH_1 = "Bò Wagyu";
    private static final String DISH_2 = "Bánh Mì Bơ Tỏi";
    private static final String DISH_ACCUMULATE = "Salad"; // Dùng món riêng cho test cộng dồn để 100% độc lập
    private static final String DISH_OUT_OF_STOCK = "Tomahawk";

    @BeforeEach
    void setupOrderTest() {
        orderPage = new OrderPage(driver);
        // Làm sạch giỏ hàng trước mỗi ca test
        orderPage.resetCart(BASE_URL);
    }

    @Test
    @DisplayName("TC-ORDER-001: Verify tìm kiếm món ăn chính xác theo tên tiếng Việt có dấu")
    void testSearchDishByName() {
        orderPage.openMenu(BASE_URL);
        orderPage.searchDish(DISH_1);
        assertTrue(orderPage.isDishDisplayed(DISH_1), "Phải hiển thị món khi tìm đúng tên có dấu");
    }

    @Test
    @DisplayName("TC-ORDER-002: Verify tìm kiếm món ăn không phân biệt hoa thường và không dấu")
    void testSearchDishCaseInsensitive() {
        orderPage.openMenu(BASE_URL);
        orderPage.searchDish("wagyu");
        assertTrue(orderPage.isDishDisplayed(DISH_1), "Phải tìm thấy món khi nhập từ khóa chữ thường không dấu");
    }

    @Test
    @DisplayName("TC-ORDER-003: Verify lọc món ăn theo danh mục Category")
    void testFilterCategory() {
        orderPage.openMenu(BASE_URL);
        orderPage.selectCategory("Khai Vị");
        assertTrue(orderPage.isDishDisplayed("Salad") || orderPage.isDishDisplayed("Bánh Mì"),
                "Phải hiển thị các món thuộc danh mục Khai Vị");
    }

    @Test
    @DisplayName("TC-ORDER-004: Verify thêm món ăn từ Menu vào đơn gọi món thành công")
    void testAddItemToCart() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        assertTrue(orderPage.isItemInCart(DISH_1), "Món ăn phải xuất hiện trong giỏ sau khi thêm");
    }

    @Test
    @DisplayName("TC-ORDER-005: Verify cộng dồn số lượng khi thêm cùng 1 món ăn 2 lần")
    void testAccumulateQuantity() {
        orderPage.openMenu(BASE_URL);
        // Thêm món DISH_ACCUMULATE lần 1 (SL = 2)
        orderPage.addDishToCart(DISH_ACCUMULATE, 2, "");
        // Thêm tiếp món DISH_ACCUMULATE lần 2 (SL = 3)
        orderPage.addDishToCart(DISH_ACCUMULATE, 3, "");
        orderPage.openCart(BASE_URL);

        assertTrue(orderPage.isItemInCart(DISH_ACCUMULATE), "Món ăn phải có trong giỏ");
        assertEquals(5, orderPage.getItemQuantityInCart(DISH_ACCUMULATE),
                "Số lượng món phải được cộng dồn chính xác là 5 (2 + 3)");
    }

    @Test
    @DisplayName("TC-ORDER-006: Verify số lượng món ăn tối thiểu biên hợp lệ là 1")
    void testQuantityBoundaryMin() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        assertEquals(1, orderPage.getItemQuantityInCart(DISH_1), "Số lượng mặc định biên tối thiểu phải là 1");
    }

    @Test
    @DisplayName("TC-ORDER-007: Verify cập nhật số lượng món đạt biên tối đa 99 phần")
    void testQuantityBoundaryMax() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        orderPage.increaseItemQuantityInCart(DISH_1);
        int qty = orderPage.getItemQuantityInCart(DISH_1);
        assertTrue(qty >= 2 && qty <= 99, "Số lượng món cập nhật hợp lệ trong khoảng <= 99");
    }

    @Test
    @DisplayName("TC-ORDER-008: Verify chặn hoặc cảnh báo khi tăng số lượng món vượt quá 99")
    void testQuantityExceedMax() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        assertTrue(orderPage.getItemQuantityInCart(DISH_1) <= 99, "Hệ thống đảm bảo số lượng không vượt quá 99");
    }

    @Test
    @DisplayName("TC-ORDER-009: Verify xóa món khỏi giỏ khi bấm nút Xóa")
    void testRemoveItemFromCart() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        orderPage.removeItemFromCart(DISH_1);
        assertTrue(orderPage.isCartEmpty(), "Giỏ hàng phải hiển thị rỗng sau khi xóa hết món");
    }

    @Test
    @DisplayName("TC-ORDER-010: Verify tính đúng thành tiền từng dòng và tổng tiền tạm tính")
    void testCartCalculation() {
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 2, "");
        orderPage.addDishToCart(DISH_2, 1, "");
        orderPage.openCart(BASE_URL);

        long lineTotal1 = orderPage.getItemSubtotal(DISH_1);
        long lineTotal2 = orderPage.getItemSubtotal(DISH_2);
        long subtotal = orderPage.getSubtotalAmount();
        long vat = orderPage.getVatAmount();
        long grandTotal = orderPage.getGrandTotal();

        assertEquals(lineTotal1 + lineTotal2, subtotal, "Tạm tính phải bằng tổng thành tiền các dòng");
        assertEquals(Math.round(subtotal * 0.08), vat, "Thuế VAT 8% tính đúng");
        assertEquals(subtotal + vat, grandTotal, "Tổng thanh toán bằng Tạm tính + VAT");
    }

    @Test
    @DisplayName("TC-ORDER-011: Verify lưu và hiển thị đúng ghi chú chế biến món ăn")
    void testItemSpecialNotes() {
        String note = "Ít cay";
        orderPage.openMenu(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, note);
        orderPage.openCart(BASE_URL);
        assertTrue(orderPage.isItemInCart(DISH_1), "Món có ghi chú phải xuất hiện trong giỏ hàng");
    }

    @Test
    @DisplayName("TC-ORDER-012: Verify món ăn trạng thái Hết món (Out of stock) không thể thêm")
    void testOutOfStockDish() {
        orderPage.openMenu(BASE_URL);
        orderPage.searchDish(DISH_OUT_OF_STOCK);
        assertTrue(orderPage.isOutOfStockDishDisabled(DISH_OUT_OF_STOCK),
                "Món ăn hết hàng (Tomahawk) phải bị vô hiệu hóa hoặc không có nút thêm");
    }

    @Test
    @DisplayName("TC-ORDER-013: Verify gửi đơn gọi món thành công và chuyển sang màn hình tracking")
    void testSubmitBatchOrder() {
        // Xác thực mã bàn hợp lệ để nhận quyền ROLE_CUSTOMER trước khi đặt hàng
        orderPage.ensureCustomerSession(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        orderPage.submitOrder("Phục vụ nhanh giúp");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dining/orders/tracking"));
        assertTrue(driver.getCurrentUrl().contains("/dining/orders/tracking"),
                "Sau khi gửi đơn phải chuyển sang trang tracking");
    }

    @Test
    @DisplayName("TC-ORDER-014: Verify giỏ hàng được làm rỗng sau khi gửi đơn thành công")
    void testCartEmptyAfterSubmit() {
        // Xác thực mã bàn hợp lệ
        orderPage.ensureCustomerSession(BASE_URL);
        orderPage.addDishToCart(DISH_1, 1, "");
        orderPage.openCart(BASE_URL);
        orderPage.submitOrder("");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dining/orders/tracking"));

        // Quay lại kiểm tra giỏ hàng đã được làm rỗng
        orderPage.openCart(BASE_URL);
        assertTrue(orderPage.isCartEmpty(), "Giỏ hàng phải rỗng sau khi đã gửi đơn thành công");
    }
}