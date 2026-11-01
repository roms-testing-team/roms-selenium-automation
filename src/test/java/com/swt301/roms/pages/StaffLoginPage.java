package com.swt301.roms.pages;

import com.swt301.roms.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaffLoginPage extends BasePage {
    // Locators ổn định dựa theo thuộc tính của StaffLoginPage.jsx
    private final By employeeCodeInput = By.cssSelector("input[placeholder*='MGR-001']");
    private final By pinInput          = By.cssSelector("input[type='password'][placeholder='••••••']");
    private final By loginButton       = By.cssSelector("button[type='submit']");
    private final By errorMessageDiv = By.xpath("//span[text()='error']/following-sibling::span");

    public StaffLoginPage(WebDriver driver) {
        super(driver);
    }

    public StaffLoginPage open(String baseUrl) {
        driver.get(baseUrl + "/login");
        waitVisible(employeeCodeInput);
        return this;
    }

    public void login(String employeeCode, String pin) {
        type(employeeCodeInput, employeeCode);
        type(pinInput, pin);
        click(loginButton);
    }

    public void selectPreset(String roleLabel) {
        By presetBtn = By.xpath("//button[contains(., '" + roleLabel + "')]");
        click(presetBtn);
    }

    public String getErrorMessage() {
        return getText(errorMessageDiv);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessageDiv);
    }

    public String getEmployeeCodeValidationMessage() {
        return driver.findElement(employeeCodeInput).getAttribute("validationMessage");
    }

    public String getPinValidationMessage() {
        return driver.findElement(pinInput).getAttribute("validationMessage");
    }
    /**
     * Lấy thuộc tính maxLength của ô nhập PIN để kiểm tra giới hạn 6 số
     */
    public String getPinMaxLength() {
        return driver.findElement(pinInput).getAttribute("maxlength");
    }

    /**
     * Lấy giá trị chuỗi đang hiển thị trong ô nhập PIN
     */
    public String getPinValue() {
        return driver.findElement(pinInput).getAttribute("value");
    }

}
