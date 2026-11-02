package com.swt301.roms.tests;

import com.swt301.roms.base.BaseTest;
import com.swt301.roms.pages.StaffLoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("M1 - Kiểm thử Đăng Nhập Ca Trực & Phân Quyền (Staff Login)")
class StaffLoginTest extends BaseTest {

    @ParameterizedTest(name = "{0}: Code={1}, PIN={2} -> Result={3}")
    @CsvFileSource(resources = "/testdata/staff_login_data.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void testStaffLoginDataDriven(String tcId, String employeeCode, String pin,
                                  String expectedResult, String expectedTargetUrlOrError) {
        StaffLoginPage loginPage = new StaffLoginPage(driver).open(BASE_URL);
        loginPage.login(employeeCode, pin);

        switch (expectedResult) {
            case "SUCCESS" -> {
                // Kiểm tra chuyển hướng thành công sang URL của role (ví dụ /admin, /waiter...)
                new WebDriverWait(driver, Duration.ofSeconds(8))
                        .until(ExpectedConditions.urlContains(expectedTargetUrlOrError));
                assertTrue(driver.getCurrentUrl().contains(expectedTargetUrlOrError),
                        tcId + " phải chuyển hướng đến " + expectedTargetUrlOrError);
            }
            case "FAIL", "VALIDATION" -> {
                // Kiểm tra hiển thị thông báo lỗi trên giao diện và vẫn ở lại trang /login
                assertTrue(loginPage.isErrorDisplayed(), tcId + " phải hiển thị thông báo lỗi");
                String actualMsg = loginPage.getErrorMessage();
                assertTrue(actualMsg.contains(expectedTargetUrlOrError),
                        tcId + " mong đợi chứa: [" + expectedTargetUrlOrError + "] nhưng thực tế là: [" + actualMsg + "]");
                assertTrue(driver.getCurrentUrl().contains("/login"), tcId + " không được rời khỏi trang /login");
            }
            case "REQUIRED" -> {
                // Kiểm tra form bị chặn submit khi thiếu trường bắt buộc
                assertTrue(driver.getCurrentUrl().contains("/login"),
                        tcId + " form phải ở lại trang /login khi thiếu trường bắt buộc");
            }
            default -> fail("Giá trị expectedResult không hợp lệ trong CSV: " + expectedResult);
        }
    }
    @org.junit.jupiter.api.Test
    @DisplayName("TC-STAFF-010: Verify ô nhập PIN giới hạn tối đa đúng 6 chữ số (BVA biên 6, 7)")
    void verifyPinMaxLength() {
        StaffLoginPage loginPage = new StaffLoginPage(driver).open(BASE_URL);

        // Kiểm tra thuộc tính HTML maxlength = 6
        assertEquals("6", loginPage.getPinMaxLength(), "Thuộc tính maxlength của ô PIN phải là 6");

        // Thử nhập chuỗi 7 chữ số: 1234567
        loginPage.login("MGR-001", "1234567");

        // Giá trị nhận vào chỉ được tối đa 6 ký tự
        assertEquals(6, loginPage.getPinValue().length(), "Ô PIN không được phép nhận quá 6 chữ số");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("TC-STAFF-011: Verify ProtectedRoute chuyển hướng về /login khi chưa đăng nhập")
    void verifyUnauthorizedAccessRedirectsToLogin() {
        // Cố tình truy cập trực tiếp vào trang quản trị khi chưa đăng nhập
        driver.get(BASE_URL + "/admin/reports");

        // Chờ ProtectedRoute chặn và đẩy về trang /login
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));

        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Người dùng chưa đăng nhập phải bị đẩy về trang /login");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("TC-STAFF-012: Verify nhân viên vai trò Waiter không được phép truy cập trang /admin")
    void verifyRoleAccessRestrictions() {
        // 1. Đăng nhập với tài khoản Phục vụ (WAITER)
        StaffLoginPage loginPage = new StaffLoginPage(driver).open(BASE_URL);
        loginPage.login("EMP-001", "123456");

        // Chờ vào trang phục vụ
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.urlContains("/waiter"));

        // 2. Cố tình gõ URL của Quản lý (/admin)
        driver.get(BASE_URL + "/admin/reports");

        // 3. Hệ thống phải chặn và không cho ở lại trang /admin
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/admin/reports")));

        assertFalse(driver.getCurrentUrl().contains("/admin/reports"),
                "Nhân viên Waiter không được phép xem màn hình báo cáo của Admin");
    }
}
