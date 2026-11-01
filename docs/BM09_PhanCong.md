# BM09 – BẢNG PHÂN CÔNG CÔNG VIỆC & ĐÓNG GÓP THÀNH VIÊN
## Dự Án: ROMS Automation Testing (SWT301)

Nhóm áp dụng nguyên tắc **phân công theo chiều dọc**: Mỗi sinh viên sở hữu trọn vẹn 1 module (từ BR, TC, CSV data, Page Object, Test Class đến log Bug) kết hợp đảm nhận **một vai trò ngang** trong nhóm:

---

### 1. Bảng Phân Công Nhiệm Vụ Chi Tiết

| Thành viên | Module phụ trách (Chiều dọc) | Vai trò chung (Chiều ngang) | Nhiệm vụ chính cụ thể |
|:---|:---|:---|:---|
| **Sinh viên 1** | **M1: Staff Login & RBAC** | **Test Lead** | - Điều phối dự án, theo dõi tiến độ theo Milestones.<br>- Soạn thảo `BM01 Test Plan` và `BM08 Summary Report`.<br>- Duyệt và Merge Pull Request vào nhánh `main`.<br>- Chạy đợt Regression test chính thức cuối cùng. |
| **Sinh viên 2** | **M2: Public Reservation** | **Framework Owner** | - Dựng khung dự án Maven (`pom.xml`), cấu trúc thư mục.<br>- Lập trình các lớp Base: `BaseTest`, `BasePage`, `ConfigReader`, `ScreenshotOnFailure`.<br>- Hỗ trợ nhóm giải quyết các lỗi cấu hình WebDriver, ChromeOptions. |
| **Sinh viên 3** | **M3: Menu & Search** | **Documentation Owner** | - Thu thập, chuẩn hóa định dạng các biểu mẫu `BM02`, `BM03`, `BM04 (RTM)`.<br>- Kiểm tra tính nhất quán giữa Test Case ID, BR ID và Tên Test Method. |
| **Sinh viên 4** | **M4: Cart & Ordering** | **Defect Manager** | - Quản lý GitHub Issues, cấu hình labels phân loại bug (severity, priority).<br>- Kiểm duyệt chất lượng các báo cáo lỗi trong `BM07 Defect Report`.<br>- Nhắc nhở theo dõi vòng đời của Defect. |
| **Sinh viên 5** *(nếu có)* | **M5: POS / KDS** | **CI / Reviewer** | - Thiết lập GitHub Actions tự động build và chạy test headless.<br>- Review chất lượng mã nguồn test (chặn `Thread.sleep`, chặn XPath tuyệt đối). |

---

### 2. Ma Trận Trách Nhiệm RACI
*(R = Thực hiện, A = Chịu trách nhiệm cuối, C = Tham vấn, I = Nhận thông tin)*

| Đầu mục công việc | SV1 (Lead) | SV2 (Framework) | SV3 (Docs) | SV4 (Defect) | SV5 (CI) |
|:---|:---:|:---:|:---:|:---:|:---:|
| **Test Plan (BM01)** | **A / R** | C | C | C | C |
| **Framework Skeleton (Base classes)** | C | **A / R** | I | I | C |
| **Module M1 (Login: BR, TC, Page, Test)** | **A / R** | I | C | C | I |
| **Module M2 (Reservation: BR, TC, Page, Test)**| I | **A / R** | C | C | I |
| **Module M3 (Menu: BR, TC, Page, Test)** | I | I | **A / R** | C | I |
| **Module M4 (Cart: BR, TC, Page, Test)** | I | I | C | **A / R** | I |
| **Quản lý Defect & Issues (BM07)** | C | C | C | **A / R** | I |
| **Tổng kết & Báo cáo cuối kỳ (BM08)** | **A / R** | C | R | R | C |

---

### 3. Đánh Giá Tỷ Lệ Đóng Góp Cá Nhân

| STT | Họ và tên | MSSV | Vai trò | Số commit / PR | Đóng góp (%) |
|:---:|:---|:---:|:---|:---:|:---:|
| 1 | [Tên SV1] | [MSSV] | Test Lead / Owner M1 | Đầy đủ minh chứng GitHub | 100% |
| 2 | [Tên SV2] | [MSSV] | Framework Owner / Owner M2 | Đầy đủ minh chứng GitHub | 100% |
| 3 | [Tên SV3] | [MSSV] | Doc Owner / Owner M3 | Đầy đủ minh chứng GitHub | 100% |
| 4 | [Tên SV4] | [MSSV] | Defect Manager / Owner M4 | Đầy đủ minh chứng GitHub | 100% |
