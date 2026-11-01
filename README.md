# ROMS — Selenium Automation Testing Framework
## Dự Án Kiểm Thử Tự Động Website Quản Lý Đặt Món Nhà Hàng (ROMS)

> **Môn học:** SWT301 — Software Testing | **Học kỳ:** Fall 2026 | **FPT University**  
> **Framework:** Selenium 4 + JUnit 5 + Page Object Model (POM) + Data-Driven (CSV)  
> **Chuẩn tài liệu:** ISTQB CTFL v4.0 & ISO/IEC/IEEE 29119-3  

---

## 1. Yêu Cầu Môi Trường
- **Java Development Kit (JDK):** Version 17 trở lên.
- **Apache Maven:** Version 3.8+ (kiểm tra bằng `mvn -v`).
- **Google Chrome:** Bản mới nhất.
- **SUT (Hệ thống ROMS):**
  - Frontend: React Vite đang chạy tại `http://localhost:5173`.
  - Backend: Spring Boot đang chạy tại `http://localhost:8080`.

---

## 2. Hướng Dẫn Cài Đặt & Chạy Test

### 2.1. Cài đặt dependencies
```bash
mvn clean install -DskipTests
```

### 2.2. Chạy toàn bộ các ca kiểm thử
```bash
mvn clean test
```

### 2.3. Chạy từng Test Class riêng lẻ
```bash
# Chạy kiểm thử Đăng nhập nhân viên & RBAC
mvn test -Dtest=StaffLoginTest

# Chạy kiểm thử Đặt bàn
mvn test -Dtest=ReservationTest

# Chạy kiểm thử Menu & Tìm kiếm
mvn test -Dtest=CustomerMenuTest

# Chạy kiểm thử Giỏ hàng
mvn test -Dtest=CustomerCartTest
```

### 2.4. Xuất báo cáo HTML Surefire Report
```bash
mvn surefire-report:report
```
*Báo cáo HTML được sinh tại:* `target/site/surefire-report.html`.

---

## 3. Cấu Trúc Dự Án
```
roms-selenium-automation/
├── pom.xml                                   # Quản lý thư viện Maven
├── README.md                                 # Hướng dẫn dự án
├── .gitignore                                # Cấu hình loại trừ git
├── .github/
│   ├── pull_request_template.md              # Template PR chuẩn cho nhóm
│   └── ISSUE_TEMPLATE/bug_report.md          # Biểu mẫu báo Bug chuẩn ISTQB
├── docs/                                     # Toàn bộ biểu mẫu BM01 → BM09
│   ├── BM01_TestPlan.md                      # Kế hoạch kiểm thử chi tiết
│   ├── BM02_BusinessRules.md                 # Quy tắc nghiệp vụ 4 module
│   ├── BM03_TestCases.xlsx                   # Bảng đặc tả Test Cases & RTM
│   ├── BM07_DefectReport.md                  # Báo cáo sự cố / Bugs
│   ├── BM08_TestSummaryReport.md             # Báo cáo tổng kết kiểm thử
│   └── BM09_PhanCong.md                      # Bảng phân công RACI & Đóng góp
└── src/test/
    ├── java/com/swt301/roms/
    │   ├── base/
    │   │   ├── BaseTest.java                 # Quản lý WebDriver lifecycle & options
    │   │   └── BasePage.java                 # Các thao tác tương tác & Explicit Wait
    │   ├── utils/
    │   │   ├── ConfigReader.java             # Đọc config.properties
    │   │   ├── DataGenerator.java            # Tiện ích sinh dữ liệu ngẫu nhiên
    │   │   └── ScreenshotOnFailure.java      # Extension tự động chụp ảnh khi test fail
    │   ├── pages/                            # Page Object Classes
    │   │   └── StaffLoginPage.java
    │   └── tests/                            # Test Classes (JUnit 5 + Assertions)
    │       └── StaffLoginTest.java
    └── resources/
        ├── config.properties                 # Cấu hình URL và timeout
        └── testdata/                         # Dữ liệu CSV Data-driven
            ├── staff_login_data.csv
            ├── reservation_data.csv
            ├── menu_search_data.csv
            └── cart_data.csv
```

---

## 4. Quy Tắc Page Object Model (POM)
1. **Tuyệt đối không assertion trong Page class:** Page Object chỉ tìm kiếm và tương tác; assertion chỉ thực hiện tại Test class.
2. **Tuyệt đối không dùng By / findElement trong Test class:** Mọi locator đóng gói trong Page class.
3. **Cấm dùng `Thread.sleep()`:** Toàn bộ quá trình đồng bộ hóa dùng `WebDriverWait` và `ExpectedConditions`.
4. **Không dùng XPath tuyệt đối:** Ưu tiên `data-testid`, `name`, `id`, `CSS Selector`.
5. **Dữ liệu phân tách:** 100% dữ liệu test đọc từ file CSV hoặc `config.properties`.
