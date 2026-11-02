# TEST PLAN – ROMS AUTOMATION TESTING – DỰ ÁN 3 TUẦN (RBL)

| Mục | Nội dung chi tiết |
|:---|:---|
| **1. Test Plan ID** | `TP-ROMS-G01-v1.0` |
| **2. Mục tiêu** | Tự động hóa kiểm thử chức năng và luồng nghiệp vụ 3 chức năng cốt lõi (M1–M3) trên hệ thống Restaurant Ordering Management System (ROMS) theo chuẩn ISTQB CTFL v4.0. |
| **3. Test items** | Hệ thống ROMS (Frontend React Vite port 5173 + Backend Spring Boot port 8080). |
| **4. Features to be tested** | - **M1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** (`/login`) — Phụ trách: Bảo Huy<br>- **M2: Thực đơn số & Gọi món tại bàn (Menu, Cart & Ordering)** (`/dining/menu` & `/dining/cart`) — Phụ trách: Minh Đức<br>- **M3: Đặt bàn trực tuyến (Public Table Reservation)** (`/reservation`) — Phụ trách: Dương Trong |
| **5. Features not to be tested** | Cổng thanh toán VNPay thực tế qua ngân hàng, Thiết bị in bill vật lý, Hiệu năng chịu tải > 1000 CCU, Đa trình duyệt ngoài Chrome. |
| **6. Kỹ thuật & Phương pháp** | - Black-box testing: Equivalence Partitioning (EP), Boundary Value Analysis (BVA), Decision Table (DT), State Transition (ST), Error Guessing (EG).<br>- Automation: Selenium WebDriver 4.21, JUnit 5, Page Object Model (POM), Data-driven với CSV.<br>- Regression testing tự động qua Maven Surefire. |
| **7. Entry criteria** | - SUT khởi động thành công (Frontend ở port 5173, Backend ở port 8080).<br>- Database đã có dữ liệu mẫu (Tài khoản nhân viên, Bàn, Menu món ăn).<br>- Toàn bộ Business Rules (BM02) và Test Cases (BM03) đã được review trong nhóm. |
| **8. Exit criteria** | - 100% Test Case mức High được thực thi.<br>- Tỷ lệ Pass Rate đạt $\ge 90\%$ (loại trừ các ca fail do lỗi bug của hệ thống đã log vào BM07).<br>- 0 lỗi mức độ Critical còn tồn đọng mà chưa được ghi nhận vào GitHub Issues. |
| **9. Suspension / Resumption** | - **Tạm dừng:** Khi backend hoặc database sập, hoặc trang `/login` gặp lỗi trắng trang (Blocker).<br>- **Tiếp tục:** Sau khi môi trường dev khôi phục và kiểm tra liveness thành công. |
| **10. Test deliverables** | - Bộ tài liệu biểu mẫu `BM01` đến `BM09` (theo ISO/IEC/IEEE 29119-3).<br>- Mã nguồn tự động hóa trên GitHub repository (Page Objects, Test Classes, CSV data).<br>- Báo cáo HTML Surefire (`target/site/surefire-report.html`).<br>- Video quay demo thực thi kiểm thử 5–7 phút (chạy `mvn clean test`, giải thích 1 ca Pass và 1 ca Fail/Bug). |
| **11. Môi trường** | Windows 10/11, Google Chrome mới nhất, Java JDK 17, Maven 3.8+. |
| **12. Phân công** | Xem chi tiết tại `docs/BM09_PhanCong.md` (3 thành viên chia 3 chức năng dọc). |
| **13. Lịch trình 3 tuần (Milestones)** | - **Tuần 1 – Analysis & Design (W1-Design):** Họp kickoff; viết Test Plan (BM01) & Business Rules (BM02); thiết kế Test Cases (BM03 nháp) & chuẩn bị dữ liệu CSV; dựng khung Skeleton framework & Base classes; thiết lập GitHub Kanban board & Issues.<br>- **Tuần 2 – Implementation & Automation (W2-Implementation):** Lập trình các lớp Page Object và Test classes theo mô hình POM; tách dữ liệu data-driven vào CSV; mở Pull Request và review chéo giữa các thành viên; đạt tỷ lệ tự động hóa $\ge 80\%$.<br>- **Tuần 3 – Execution, Defect & Closure (W3-Closure):** Chạy kiểm thử hồi quy 2 đợt (RUN-01, RUN-02) bằng `mvn clean test`; xuất báo cáo HTML Surefire; ghi nhận Defect Report (BM07) và tạo GitHub Bug Issues; hoàn thiện Test Summary Report (BM08); gắn tag `v1.0` và quay video demo 5–7 phút nộp bài. |

---

### Bảng Phân Tích Rủi Ro (Project Risk Analysis)

| ID | Rủi ro tiềm ẩn | Xác suất | Ảnh hưởng | Giải pháp kiểm soát |
|:---|:---|:---:|:---:|:---|
| **R1** | Locator giao diện thay đổi do frontend tái cấu trúc | Cao | Cao | Ưu tiên dùng `data-testid`, `type`, `name`, `placeholder`, CSS Selector ổn định; tuyệt đối cấm XPath tuyệt đối. |
| **R2** | Dữ liệu test bị trùng lặp (đặt bàn trùng SĐT, giờ đặt) | Cao | Trung bình | Sử dụng `DataGenerator` sinh số điện thoại và thời gian ngẫu nhiên theo timestamp. |
| **R3** | Kiểm thử flaky do mạng chậm hoặc render trễ | Trung bình | Cao | Sử dụng 100% `WebDriverWait` (Explicit Wait), cấm dùng `Thread.sleep()`. |
| **R4** | Tiến độ làm việc không đồng đều giữa các thành viên | Trung bình | Cao | Phân công RACI rõ ràng, theo dõi tiến độ qua GitHub Projects (Kanban), bắt buộc review chéo PR trước khi merge. |
