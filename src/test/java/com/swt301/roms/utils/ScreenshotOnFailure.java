package com.swt301.roms.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {
    private final Supplier<WebDriver> driverSupplier;

    public ScreenshotOnFailure(Supplier<WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        // Kiểm tra xem test case có throw exception (tức là FAIL) không
        if (context.getExecutionException().isPresent()) {
            WebDriver driver = driverSupplier.get();
            if (driver != null) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Path screenshotDir = Paths.get("target", "screenshots");
                    Files.createDirectories(screenshotDir);

                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String cleanTestName = context.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_");
                    String fileName = cleanTestName + "_" + timestamp + ".png";

                    Files.write(screenshotDir.resolve(fileName), screenshotBytes);
                    System.out.println(">>> Đã tự động chụp ảnh màn hình lỗi: " + screenshotDir.resolve(fileName));
                } catch (Exception e) {
                    System.err.println("Không thể chụp ảnh màn hình: " + e.getMessage());
                }
            }
        }
    }
}
