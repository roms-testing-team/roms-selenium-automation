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
}
