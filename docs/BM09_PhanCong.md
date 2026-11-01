# BM09 – BẢNG PHÂN CÔNG CÔNG VIỆC & ĐÓNG GÓP THÀNH VIÊN
## Dự Án: ROMS Automation Testing – Kế Hoạch 3 Tuần (RBL)

Nhóm 3 thành viên thực hiện toàn diện quy trình kiểm thử tự động hệ thống ROMS trong **3 tuần (RBL Milestone Plan)**. Mỗi thành viên phụ trách **trọn vẹn 1 chức năng theo chiều dọc** (từ BR, TC, CSV data, Page Object, Test Class đến log Bug) kết hợp đảm nhận **một vai trò ngang**:

---

### 1. Phân Công Nhiệm Vụ 3 Chức Năng Cho 3 Thành Viên

| Thành viên | Chức năng phụ trách (Chiều dọc) | Vai trò chung (Chiều ngang) | Nhiệm vụ chính cụ thể |
|:---|:---|:---|:---|
| **Ngô Viết Bảo Huy**<br>(`ngo17574-ops`) | **Chức năng 1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** | **Test Lead & Git Manager** | - Điều phối dự án, quản lý Organization & Repo trên GitHub.<br>- Soạn thảo `BM01 Test Plan` và `BM08 Summary Report`.<br>- Duyệt và Merge Pull Request vào nhánh `main`.<br>- Lập trình Page Object + Test script cho chức năng Đăng nhập.<br>- Chạy đợt Regression test chính thức cuối cùng. |
| **Phạm Minh Đức**<br>(`ducminh14-cmd`) | **Chức năng 2: Thực đơn & Gọi món tại bàn (Menu & Ordering)** | **Framework & Automation Owner** | - Dựng khung dự án Maven (`pom.xml`), cấu trúc thư mục.<br>- Lập trình và bảo trì các lớp Base: `BaseTest`, `BasePage`, `ConfigReader`, `ScreenshotOnFailure`.<br>- Lập trình Page Object + Test script cho chức năng Thực đơn & Gọi món tại bàn.<br>- Hỗ trợ nhóm xử lý lỗi ChromeDriver, timeout, sync. |
| **Dương Trong**<br>(`duogtrog06tk`) | **Chức năng 3: Đặt bàn trực tuyến (Public Table Reservation)** | **Documentation & Defect Manager** | - Thu thập, chuẩn hóa định dạng các biểu mẫu `BM02`, `BM03`, `BM04 (RTM)`.<br>- Quản lý GitHub Issues, cấu hình labels phân loại bug.<br>- Kiểm duyệt chất lượng các báo cáo lỗi trong `BM07 Defect Report`.<br>- Lập trình Page Object + Test script cho chức năng Đặt bàn trực tuyến. |

---

### 2. Kế Hoạch Thực Hiện Trong 3 Tuần (Milestone Plan)

| Tuần | Giai đoạn (Milestone) | Đầu ra chính cần hoàn thành | Phụ trách |
|:---|:---|:---|:---|
| **Tuần 1** | **Analysis & Design**<br>(Milestone `W1-Design`) | - Hoàn thiện BM01 (Test Plan) và BM02 (Business Rules).<br>- Từng thành viên thiết kế xong Test Cases (BM03 nháp) và CSV data của chức năng mình.<br>- Dựng khung Skeleton framework & Base classes.<br>- Thiết lập GitHub Projects (Kanban board) và các Issues theo đúng chuẩn. | Toàn nhóm |
| **Tuần 2** | **Implementation & Automation**<br>(Milestone `W2-Implementation`) | - Lập trình xong Page Object classes và Test classes cho 3 chức năng.<br>- Mở Pull Request, các thành viên review chéo bắt buộc và merge vào nhánh `develop`.<br>- Đạt tỷ lệ tự động hóa $\ge 80\%$ (ít nhất 34 test cases chạy tự động bằng Selenium POM). | Toàn nhóm |
| **Tuần 3** | **Execution, Defect & Closure**<br>(Milestone `W3-Closure`) | - Chạy Regression Test toàn bộ hệ thống bằng `mvn clean test` (RUN-01, RUN-02).<br>- Xuất HTML Surefire Report (`mvn surefire-report:report`).<br>- Ghi nhận các bug phát hiện vào `BM07` và tạo GitHub Bug Issues tương ứng.<br>- Hoàn thiện `BM08 Test Summary Report`, gắn tag git `v1.0` và quay video demo 5–7 phút nộp bài. | Toàn nhóm |

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
