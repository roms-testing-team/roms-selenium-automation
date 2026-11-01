# ROMS — Selenium Automation Testing Framework
## Dự Án Kiểm Thử Tự Động Website Quản Lý Đặt Món Nhà Hàng (ROMS)

> **Môn học:** SWT301 — Software Testing | **Học kỳ:** Fall 2026 | **FPT University**  
> **Quy mô nhóm:** 3 thành viên  
> **Phạm vi kiểm thử:** 3 chức năng trọng tâm: **Đăng Nhập Nhân Viên**, **Đặt Bàn Trực Tuyến**, **Thực Đơn & Gọi Món Tại Bàn**  
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

## 2. Phạm Vi Kiểm Thử (3 Chức Năng Cốt Lõi)

| Module | Chức năng | URL | Kỹ thuật kiểm thử | Phụ trách |
|:---|:---|:---|:---:|:---:|
| **M1** | **Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** | `/login` | Decision Table, Equivalence Partitioning | SV1 (Test Lead) |
| **M2** | **Đặt bàn trực tuyến (Public Table Reservation)** | `/reservation` | Boundary Value Analysis, Equivalence Partitioning | SV2 (Framework Owner) |
| **M3** | **Thực đơn số & Gọi món tại bàn (Menu & Ordering)** | `/dining/menu`<br>`/dining/cart` | Boundary Value Analysis, State Transition, Error Guessing | SV3 (Doc & Defect Owner) |

---

## 3. Hướng Dẫn Chạy Test

### 3.1. Chạy toàn bộ các ca kiểm thử
```bash
mvn clean test
```

### 3.2. Chạy từng chức năng riêng lẻ
```bash
# 1. Chạy kiểm thử Đăng nhập nhân viên (M1)
mvn test -Dtest=StaffLoginTest

# 2. Chạy kiểm thử Đặt bàn trực tuyến (M2)
mvn test -Dtest=ReservationTest

# 3. Chạy kiểm thử Thực đơn & Gọi món tại bàn (M3)
mvn test -Dtest=OrderTest
```

### 3.3. Xuất báo cáo HTML Surefire Report
```bash
mvn surefire-report:report
```
*Báo cáo HTML được sinh tại:* `target/site/surefire-report.html`.

---

## 4. Cấu Trúc Dự Án
```
roms-selenium-automation/
├── pom.xml                                   # Quản lý thư viện Maven (Selenium 4, JUnit 5)
├── README.md                                 # Hướng dẫn dự án
├── docs/                                     # Toàn bộ biểu mẫu BM01 → BM09 cho 3 module
│   ├── BM01_TestPlan.md                      # Kế hoạch kiểm thử chi tiết (nhóm 3 người)
│   ├── BM02_BusinessRules.md                 # Quy tắc nghiệp vụ 3 chức năng
│   ├── BM03_TestCases.xlsx                   # Bảng đặc tả Test Cases & RTM
│   ├── BM07_DefectReport.md                  # Báo cáo sự cố / Bugs
│   ├── BM08_TestSummaryReport.md             # Báo cáo tổng kết kiểm thử
│   └── BM09_PhanCong.md                      # Bảng phân công 3 thành viên & RACI
└── src/test/
    ├── java/com/swt301/roms/
    │   ├── base/
    │   │   ├── BaseTest.java                 # Quản lý WebDriver & Screenshot failure
    │   │   └── BasePage.java                 # Explicit Wait (WebDriverWait), click, type
    │   ├── utils/
    │   │   ├── ConfigReader.java             # Đọc config.properties
    │   │   ├── DataGenerator.java            # Tiện ích sinh dữ liệu ngẫu nhiên
    │   │   └── ScreenshotOnFailure.java      # Extension tự động chụp ảnh khi test fail
    │   ├── pages/                            # Page Object Classes
    │   │   ├── StaffLoginPage.java           # M1 Login Page
    │   │   ├── ReservationPage.java          # M2 Reservation Page
    │   │   └── OrderPage.java                # M3 Menu & Cart Page
    │   └── tests/                            # Test Classes
    │       ├── StaffLoginTest.java
    │       ├── ReservationTest.java
    │       └── OrderTest.java
    └── resources/
        ├── config.properties                 # URL http://localhost:5173
        └── testdata/                         # Dữ liệu CSV Data-driven
            ├── staff_login_data.csv
            ├── reservation_data.csv
            └── order_data.csv
```
