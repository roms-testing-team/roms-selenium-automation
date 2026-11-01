# BM09 – BẢNG PHÂN CÔNG CÔNG VIỆC & ĐÓNG GÓP THÀNH VIÊN
## Dự Án: ROMS Automation Testing (Nhóm 3 Thành Viên)

Nhóm áp dụng nguyên tắc **phân công theo chiều dọc**: Mỗi sinh viên sở hữu trọn vẹn 1 module (từ BR, TC, CSV data, Page Object, Test Class đến log Bug) kết hợp đảm nhận **một vai trò ngang** trong nhóm:

---

### 1. Bảng Phân Công Nhiệm Vụ 3 Module Cho 3 Thành Viên

| Thành viên | Module phụ trách (Chiều dọc) | Vai trò chung (Chiều ngang) | Nhiệm vụ chính cụ thể |
|:---|:---|:---|:---|
| **SV1: Ngô Viết Bảo Huy**<br>(`ngo17574-ops`) | **M1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** | **Test Lead & Git Manager** | - Điều phối dự án, quản lý Organization & Repo trên GitHub.<br>- Soạn thảo `BM01 Test Plan` và `BM08 Summary Report`.<br>- Duyệt và Merge Pull Request vào nhánh `main`.<br>- Lập trình Page Object + Test script cho Module M1.<br>- Chạy đợt Regression test chính thức cuối cùng. |
| **SV2: Phạm Minh Đức**<br>(`ducminh14-cmd`) | **M2: Đặt bàn trực tuyến (Public Table Reservation)** | **Framework Owner** | - Dựng khung dự án Maven (`pom.xml`), cấu trúc thư mục.<br>- Lập trình và bảo trì các lớp Base: `BaseTest`, `BasePage`, `ConfigReader`, `ScreenshotOnFailure`.<br>- Lập trình Page Object + Test script cho Module M2.<br>- Hỗ trợ nhóm xử lý lỗi ChromeDriver, timeout, sync. |
| **SV3: Dương Trong**<br>(`duogtrog06tk`) | **M3: Thực đơn số & Gọi món tại bàn (Menu & Ordering)** | **Documentation & Defect Manager** | - Thu thập, chuẩn hóa định dạng các biểu mẫu `BM02`, `BM03`, `BM04 (RTM)`.<br>- Quản lý GitHub Issues, cấu hình labels phân loại bug.<br>- Kiểm duyệt chất lượng các báo cáo lỗi trong `BM07 Defect Report`.<br>- Lập trình Page Object + Test script cho Module M3. |

---

### 2. Ma Trận Trách Nhiệm RACI (3 Thành Viên)
*(R = Thực hiện, A = Chịu trách nhiệm cuối, C = Tham vấn, I = Nhận thông tin)*

| Đầu mục công việc | SV1 (Bảo Huy - Lead) | SV2 (Minh Đức - Framework) | SV3 (Dương Trong - Docs/Bug) |
|:---|:---:|:---:|:---:|
| **Test Plan (BM01)** | **A / R** | C | C |
| **Framework Skeleton (Base classes)** | C | **A / R** | I |
| **Module M1: Đăng nhập (BR, TC, Page, Test)** | **A / R** | I | C |
| **Module M2: Đặt bàn (BR, TC, Page, Test)** | I | **A / R** | C |
| **Module M3: Gọi món (BR, TC, Page, Test)** | I | C | **A / R** |
| **Quản lý Defect & GitHub Issues (BM07)** | C | C | **A / R** |
| **Tổng kết & Báo cáo cuối kỳ (BM08)** | **A / R** | C | R |

---

### 3. Đánh Giá Tỷ Lệ Đóng Góp Cá Nhân

| STT | Họ và tên | GitHub Username | Vai trò | Số commit / PR | Đóng góp (%) |
|:---:|:---|:---:|:---|:---:|:---:|
| 1 | **Ngô Viết Bảo Huy** | `ngo17574-ops` | Test Lead / Owner M1 | Đầy đủ minh chứng GitHub | 100% |
| 2 | **Phạm Minh Đức** | `ducminh14-cmd` | Framework Owner / Owner M2 | Đầy đủ minh chứng GitHub | 100% |
| 3 | **Dương Trong** | `duogtrog06tk` | Documentation & Defect Owner / Owner M3 | Đầy đủ minh chứng GitHub | 100% |
