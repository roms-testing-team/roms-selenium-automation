# BM09 – BẢNG PHÂN CÔNG CÔNG VIỆC & ĐÓNG GÓP THÀNH VIÊN
## Dự Án: ROMS Automation Testing (Kế Hoạch 1 Ngày / 1-Day Sprint)

Nhóm 3 thành viên thực hiện toàn diện quy trình kiểm thử tự động hệ thống ROMS trong **1 ngày (1-Day Intensive Sprint)**. Mỗi thành viên phụ trách **trọn vẹn 1 chức năng theo chiều dọc** (từ BR, TC, CSV data, Page Object, Test Class đến log Bug) kết hợp đảm nhận **một vai trò ngang**:

---

### 1. Phân Công Nhiệm Vụ 3 Chức Năng Cho 3 Thành Viên

| Thành viên | Chức năng phụ trách (Chiều dọc) | Vai trò chung (Chiều ngang) | Nhiệm vụ chính cụ thể |
|:---|:---|:---|:---|
| **Ngô Viết Bảo Huy**<br>(`ngo17574-ops`) | **Chức năng 1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** | **Test Lead & Git Manager** | - Điều phối dự án, quản lý Organization & Repo trên GitHub.<br>- Soạn thảo `BM01 Test Plan` và `BM08 Summary Report`.<br>- Duyệt và Merge Pull Request vào nhánh `main`.<br>- Lập trình Page Object + Test script cho chức năng Đăng nhập.<br>- Chạy đợt Regression test chính thức cuối cùng. |
| **Phạm Minh Đức**<br>(`ducminh14-cmd`) | **Chức năng 2: Thực đơn & Gọi món tại bàn (Menu & Ordering)** | **Framework & Automation Owner** | - Dựng khung dự án Maven (`pom.xml`), cấu trúc thư mục.<br>- Lập trình và bảo trì các lớp Base: `BaseTest`, `BasePage`, `ConfigReader`, `ScreenshotOnFailure`.<br>- Lập trình Page Object + Test script cho chức năng Thực đơn & Gọi món tại bàn.<br>- Hỗ trợ nhóm xử lý lỗi ChromeDriver, timeout, sync. |
| **Dương Trong**<br>(`duogtrog06tk`) | **Chức năng 3: Đặt bàn trực tuyến (Public Table Reservation)** | **Documentation & Defect Manager** | - Thu thập, chuẩn hóa định dạng các biểu mẫu `BM02`, `BM03`, `BM04 (RTM)`.<br>- Quản lý GitHub Issues, cấu hình labels phân loại bug.<br>- Kiểm duyệt chất lượng các báo cáo lỗi trong `BM07 Defect Report`.<br>- Lập trình Page Object + Test script cho chức năng Đặt bàn trực tuyến. |

---

### 2. Kế Hoạch Thực Hiện Trong 1 Ngày (1-Day Intensive Sprint)

| Khung giờ | Giai đoạn (Milestone) | Đầu ra chính cần hoàn thành | Phụ trách |
|:---|:---|:---|:---|
| **08:00 – 11:30**<br>(Buổi Sáng) | **Giai đoạn 1: Analysis & Design** | - Hoàn thiện BM01 (Test Plan) và BM02 (Business Rules).<br>- Từng bạn thiết kế xong Test Cases (BM03 nháp) và CSV data của chức năng mình.<br>- Tạo bảng Kanban và 12 Issues trên GitHub theo `docs/GITHUB_ISSUES_LIST.md`. | Toàn nhóm |
| **13:00 – 17:30**<br>(Buổi Chiều) | **Giai đoạn 2: Implementation & Automation** | - Lập trình xong Page Object classes và Test classes cho 3 chức năng.<br>- Mở Pull Request, các thành viên review chéo và merge vào nhánh `main`.<br>- Đạt tỷ lệ tự động hóa $\ge 80\%$ (ít nhất 34 test cases chạy tự động). | Toàn nhóm |
| **19:00 – 22:30**<br>(Buổi Tối) | **Giai đoạn 3: Execution, Defect & Closure** | - Chạy Regression Test toàn bộ hệ thống bằng `mvn clean test`.<br>- Xuất HTML Surefire Report (`mvn surefire-report:report`).<br>- Ghi nhận các bug phát hiện vào `BM07` và tạo GitHub Issues tương ứng.<br>- Hoàn thiện `BM08 Test Summary Report`, gắn tag git `v1.0` và quay video demo. | Toàn nhóm |

---

### 3. Ma Trận Trách Nhiệm RACI (3 Thành Viên)
*(R = Thực hiện, A = Chịu trách nhiệm cuối, C = Tham vấn, I = Nhận thông tin)*

| Đầu mục công việc | Bảo Huy (Lead - Đăng nhập) | Minh Đức (Gọi món) | Dương Trong (Đặt bàn) |
|:---|:---:|:---:|:---:|
| **Test Plan (BM01) & Setup GitHub** | **A / R** | C | C |
| **Framework Base (`BaseTest`, `BasePage`)** | C | **A / R** | I |
| **Chức năng 1: Đăng nhập (BR, TC, Page, Test)** | **A / R** | I | C |
| **Chức năng 2: Gọi món (BR, TC, Page, Test)** | I | **A / R** | C |
| **Chức năng 3: Đặt bàn (BR, TC, Page, Test)** | I | C | **A / R** |
| **Quản lý Defect & GitHub Issues (BM07)** | C | C | **A / R** |
| **Tổng kết & Báo cáo cuối kỳ (BM08)** | **A / R** | C | R |

---

### 4. Đánh Giá Tỷ Lệ Đóng Góp Cá Nhân

| STT | Họ và tên | GitHub Username | Vai trò | Trọng số đóng góp |
|:---:|:---|:---:|:---|:---:|
| 1 | **Ngô Viết Bảo Huy** | `ngo17574-ops` | Test Lead / Owner Chức năng Đăng nhập | **100%** |
| 2 | **Phạm Minh Đức** | `ducminh14-cmd` | Framework Owner / Owner Chức năng Gọi món | **100%** |
| 3 | **Dương Trong** | `duogtrog06tk` | Documentation & Defect Owner / Owner Chức năng Đặt bàn | **100%** |
