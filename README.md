# ROMS — Selenium Automation Testing Framework
## Dự Án Kiểm Thử Tự Động Website Quản Lý Đặt Món Nhà Hàng (ROMS)

> **Môn học:** SWT301 — Software Testing | **Học kỳ:** Fall 2026 | **FPT University**  
> **Quy mô nhóm:** 3 thành viên  
> **Phạm vi kiểm thử:** 3 chức năng trọng tâm: **Đăng Nhập Nhân Viên**, **Thực Đơn & Gọi Món Tại Bàn**, **Đặt Bàn Trực Tuyến**  
> **Framework:** Selenium 4 + JUnit 5 + Page Object Model (POM) + Data-Driven (CSV)  
> **Chuẩn tài liệu:** ISTQB CTFL v4.0 & ISO/IEC/IEEE 29119-3  

---

## 1. Yêu Cầu Môi Trường & Hệ Thống Kiểm Thử (SUT)
- **Java Development Kit (JDK):** Version 17 trở lên.
- **Apache Maven:** Version 3.8+ (kiểm tra bằng `mvn -v`).
- **Google Chrome:** Bản mới nhất.
- **SUT (Hệ thống ROMS Live Environment):**
  - Web URL: [http://roms-restaurant.duckdns.org/](http://roms-restaurant.duckdns.org/) (Nginx Reverse Proxy, React Frontend, Spring Boot Backend).

---

## 2. Phạm Vi Kiểm Thử & Phân Công 3 Thành Viên

| Module | Chức năng | URL | Kỹ thuật kiểm thử | Phụ trách | GitHub |
|:---|:---|:---|:---:|:---:|:---:|
| **M1** | **Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** | `/login` | Decision Table, Equivalence Partitioning | **Ngô Viết Bảo Huy** (Test Lead) | `@ngo17574-ops` |
| **M2** | **Thực đơn & Gọi món tại bàn (Menu & Ordering)** | `/dining/menu`<br>`/dining/cart` | Boundary Value Analysis, State Transition | **Phạm Minh Đức** (Framework Owner) | `@ducminh14-cmd` |
| **M3** | **Đặt bàn trực tuyến (Public Table Reservation)** | `/reservation` | Boundary Value Analysis, Equivalence Partitioning | **Dương Trọng** (Defect & Doc Owner) | `@duogtrog06tk` |

---

## 3. Video Demo & Báo Cáo Kiểm Thử (Deliverables)
- 🎥 **Video Demo Kiểm Thử Tự Động (Google Drive):** [https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link](https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link)
- 📊 **Báo cáo HTML Surefire Report:** [`docs/reports/surefire-report.html`](docs/reports/surefire-report.html)
- 📝 **Test Summary Report (BM08):** [`docs/BM08_TestSummaryReport.md`](docs/BM08_TestSummaryReport.md)
- 🐛 **Defect Report (BM07):** [`docs/BM07_DefectReport.md`](docs/BM07_DefectReport.md)

---

## 4. Hướng Dẫn Chạy Test

### 4.1. Chạy toàn bộ bộ kiểm thử hồi quy (Regression Test)
```bash
# Chạy ở chế độ Headless (khuyên dùng khi chạy CI/CD hoặc báo cáo):
mvn clean test -Dheadless=true

# Chạy ở chế độ mở giao diện trình duyệt trực quan:
mvn clean test
```

### 4.2. Chạy từng chức năng riêng lẻ
```bash
# 1. Chạy kiểm thử Đăng nhập nhân viên (M1) - 12 TCs
mvn test -Dtest=StaffLoginTest -Dheadless=true

# 2. Chạy kiểm thử Thực đơn & Gọi món tại bàn (M2) - 14 TCs
mvn test -Dtest=OrderTest -Dheadless=true

# 3. Chạy kiểm thử Đặt bàn trực tuyến (M3) - 16 TCs
mvn test -Dtest=ReservationTest -Dheadless=true
```

### 4.3. Xuất báo cáo HTML Surefire Report
```bash
mvn surefire-report:report
```
*Báo cáo HTML được sinh tại:* `target/site/surefire-report.html` và lưu trữ tại `docs/reports/surefire-report.html`.

---

## 5. Kết Quả Kiểm Thử Tự Động (Execution Summary)

| Module / Test Class | Số lượng TC | Pass | Fail | Error | Pass Rate (%) | Thời gian |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|
| **M1: StaffLoginTest** | 12 | 12 | 0 | 0 | **100%** | 46.56s |
| **M2: OrderTest** | 14 | 14 | 0 | 0 | **100%** | 71.66s |
| **M3: ReservationTest** | 16 | 16 | 0 | 0 | **100%** | 177.20s |
| **Tổng cộng** | **42** | **42** | **0** | **0** | **100%** | **4m 55s** |

---

## 6. Cấu Trúc Dự Án
```
roms-selenium-automation/
├── pom.xml                                   # Quản lý thư viện Maven (Selenium 4, JUnit 5, Surefire Report)
├── README.md                                 # Hướng dẫn dự án & bàn giao
├── docs/                                     # Toàn bộ biểu mẫu BM01 → BM09 chuẩn ISTQB / ISO
│   ├── BM01_TestPlan.md                      # Kế hoạch kiểm thử chi tiết (nhóm 3 người)
│   ├── BM02_BusinessRules.md                 # Quy tắc nghiệp vụ 3 chức năng
│   ├── BM03_TestCases.xlsx                   # Bảng đặc tả Test Cases & RTM & Execution Log
│   ├── BM03_TestCases_Specification.md       # Bảng đặc tả chi tiết bằng Markdown
│   ├── BM07_DefectReport.md                  # Báo cáo sự cố / Bugs (đồng bộ GitHub Issues #18, #19, #20)
│   ├── BM08_TestSummaryReport.md             # Báo cáo tổng kết kiểm thử
│   ├── BM09_PhanCong.md                      # Bảng phân công 3 thành viên & RACI
│   └── reports/                              # Báo cáo kết quả kiểm thử HTML
│       └── surefire-report.html              # Báo cáo Surefire Report hoàn chỉnh
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
    │   │   ├── OrderPage.java                # M2 Menu & Cart Page
    │   │   └── ReservationPage.java          # M3 Reservation Page
    │   └── tests/                            # Test Classes
    │       ├── StaffLoginTest.java           # M1 Tests
    │       ├── OrderTest.java                # M2 Tests
    │       └── ReservationTest.java          # M3 Tests
    └── resources/
        ├── config.properties                 # URL http://roms-restaurant.duckdns.org/
        └── testdata/                         # Dữ liệu CSV Data-driven
            ├── staff_login_data.csv
            ├── order_data.csv
            └── reservation_data.csv
```
