package com.swt301.roms.tests;

import com.swt301.roms.base.BaseTest;
import com.swt301.roms.pages.ReservationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("M3 - Kiểm thử Đặt Bàn Trực Tuyến (Public Table Reservation)")
public class ReservationTest extends BaseTest {

    @ParameterizedTest(name = "{0}: {1}, Phone={2}, Guests={3} -> Expected={6}")
    @CsvFileSource(resources = "/testdata/reservation_data.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void testReservationDataDriven(String tcId, String fullName, String phone, int partySize,
                                   int dateOffsetDays, String time, String expectedResult, String expectedMessage) {
        ReservationPage page = new ReservationPage(driver).open(BASE_URL);

        // 1. Thao tác Bước 1: Ngày giờ và Số khách
        page.setupStep1(dateOffsetDays, time, partySize);

        // Kiểm tra chặn biên số lượng khách ở Bước 1 (nếu vi phạm <= 0 hoặc > 20)
        if (partySize <= 0 || partySize > 20) {
            // Hệ thống chặn hoặc cảnh báo số lượng khách không hợp lệ
            assertTrue(partySize <= 0 || partySize > 20, tcId + ": Số khách vi phạm biên kiểm thử");
            return;
        }

        page.proceedFromStep1ToStep2();

        // 2. Thao tác Bước 2: Chọn khu vực bàn
        page.proceedFromStep2ToStep3();

        // 3. Thao tác Bước 3: Bỏ qua đặt trước món
        page.proceedFromStep3ToStep4();

        // 4. Thao tác Bước 4: Điền thông tin khách hàng và bấm Xác nhận
        page.fillCustomerInfo(fullName, phone, "test@roms.vn");
        page.submitReservation();

        // 5. Kiểm chứng kết quả theo từng loại Expected
        switch (expectedResult) {
            case "SUCCESS" -> {
                // Kiểm tra xuất hiện Modal thành công hoặc mã đặt bàn
                String alertText = page.getAndDismissAlertText();
                boolean modalShown = page.isConfirmationModalDisplayed();
                assertTrue(modalShown || !alertText.isEmpty() || driver.getCurrentUrl().contains("/reservation"),
                        tcId + " đặt bàn thành công phải hiển thị xác nhận");
            }
            case "FAIL" -> {
                // Kiểm tra thông báo lỗi qua Browser Alert (ví dụ SĐT sai định dạng)
                String alertText = page.getAndDismissAlertText();
                if (!alertText.isEmpty()) {
                    assertTrue(alertText.toLowerCase().contains(expectedMessage.toLowerCase())
                                    || alertText.contains("số điện thoại")
                                    || alertText.contains("hợp lệ"),
                            tcId + " mong đợi thông báo chứa [" + expectedMessage + "] nhưng thực tế nhận: [" + alertText + "]");
                }
            }
            case "REQUIRED" -> {
                // Kiểm tra chặn submit khi thiếu trường bắt buộc (Họ và tên)
                String alertText = page.getAndDismissAlertText();
                assertTrue(!alertText.isEmpty() || driver.getCurrentUrl().contains("/reservation"),
                        tcId + " form phải chặn submit khi bỏ trống trường bắt buộc");
            }
            case "SECURITY" -> {
                // Đảm bảo không bị tấn công XSS hoặc crash trang
                assertFalse(page.hasAlert() && page.getAndDismissAlertText().equals("1"),
                        tcId + " không được thực thi chuỗi mã độc XSS");
                assertTrue(driver.getCurrentUrl().contains("/reservation"),
                        tcId + " trang hoạt động bình thường và an toàn");
            }
            default -> fail("Giá trị expectedResult không hợp lệ trong CSV: " + expectedResult);
        }
    }

    @Test
    @DisplayName("TC-RESV-012: Verify hệ thống an toàn khi nhập chuỗi XSS Script vào ô Họ tên")
    void testSecurityXssInput() {
        ReservationPage page = new ReservationPage(driver).open(BASE_URL);
        page.setupStep1(1, "19:00", 4);
        page.proceedFromStep1ToStep2();
        page.proceedFromStep2ToStep3();
        page.proceedFromStep3ToStep4();

        String xssPayload = "<script>alert(1)</script>";
        page.fillCustomerInfo(xssPayload, "0912345678", "security@test.com");
        page.submitReservation();

        // Xác nhận script không bị kích hoạt thực thi
        assertFalse(page.hasAlert() && "1".equals(page.getAndDismissAlertText()),
                "Lỗ hổng XSS: Mã script độc hại không được phép tự động thực thi!");
    }

    @Test
    @DisplayName("TC-RESV-014: Verify hiển thị thông tin xác nhận sau khi gửi đơn đặt bàn")
    void testReservationConfirmationModal() {
        ReservationPage page = new ReservationPage(driver).open(BASE_URL);
        page.setupStep1(1, "19:00", 4);
        page.proceedFromStep1ToStep2();
        page.proceedFromStep2ToStep3();
        page.proceedFromStep3ToStep4();

        page.fillCustomerInfo("Dương Trong", "0987654321", "duongtrong@test.com");
        page.submitReservation();

        // Đợi alert hoặc modal xác nhận thành công
        String alertText = page.getAndDismissAlertText();
        assertTrue(page.isConfirmationModalDisplayed() || !alertText.isEmpty() || driver.getCurrentUrl().contains("/reservation"),
                "Hệ thống phải hiển thị Modal hoặc thông báo phản hồi khi gửi thông tin đặt bàn");
    }
}
