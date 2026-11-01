# TEST PLAN – ROMS AUTOMATION TESTING – NHÓM 3 THÀNH VIÊN

| Mục | Nội dung chi tiết |
|:---|:---|
| **1. Test Plan ID** | `TP-ROMS-G01-v1.0` |
| **2. Mục tiêu** | Tự động hóa kiểm thử chức năng và luồng nghiệp vụ 3 module cốt lõi (M1–M3) trên hệ thống Restaurant Ordering Management System (ROMS). |
| **3. Test items** | Hệ thống ROMS (Frontend React Vite port 5173 + Backend Spring Boot port 8080). |
| **4. Features to be tested** | - **M1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)** (`/login`)<br>- **M2: Đặt bàn trực tuyến (Public Table Reservation)** (`/reservation`)<br>- **M3: Thực đơn số & Gọi món tại bàn (Digital Menu, Cart & Ordering)** (`/dining/menu` và `/dining/cart`) |
| **5. Features not to be tested** | Cổng thanh toán VNPay thực tế qua ngân hàng, Thiết bị in bill vật lý, Hiệu năng chịu tải > 1000 CCU, Đa trình duyệt ngoài Chrome. |
| **6. Kỹ thuật & Phương pháp** | - Black-box testing: Equivalence Partitioning (EP), Boundary Value Analysis (BVA), Decision Table (DT), State Transition (ST), Error Guessing (EG).<br>- Automation: Selenium WebDriver 4.21, JUnit 5, Page Object Model (POM), Data-driven với CSV.<br>- Regression testing tự động qua Maven Surefire. |
| **7. Entry criteria** | - SUT khởi động thành công (Frontend ở port 5173, Backend ở port 8080).<br>- Database đã có dữ liệu mẫu (Tài khoản nhân viên, Bàn, Menu món ăn).<br>- Toàn bộ Business Rules (BM02) và Test Cases (BM03) đã được review trong nhóm. |
| **8. Exit criteria** | - 100% Test Case mức High được thực thi.<br>- Tỷ lệ Pass Rate đạt $\ge 90\%$ (loại trừ các ca fail do lỗi bug của hệ thống đã log vào BM07).<br>- 0 lỗi mức độ Critical còn tồn đọng mà chưa được ghi nhận vào GitHub Issues. |
| **9. Suspension / Resumption** | - **Tạm dừng:** Khi backend hoặc database sập, hoặc trang `/login` gặp lỗi trắng trang (Blocker).<br>- **Tiếp tục:** Sau khi môi trường dev khôi phục và kiểm tra liveness thành công. |
| **10. Test deliverables** | - Bộ tài liệu biểu mẫu `BM01` đến `BM09`.<br>- Mã nguồn tự động hóa trên GitHub repository.<br>- Báo cáo HTML Surefire (`target/site/surefire-report.html`).<br>- Video quay demo thực thi kiểm thử 5–7 phút. |
| **11. Môi trường** | Windows 10/11, Google Chrome mới nhất, Java JDK 17, Maven 3.8+. |
| **12. Phân công** | Xem chi tiết tại `docs/BM09_PhanCong.md` (3 thành viên chia 3 module dọc). |
| **13. Lịch trình 3 tuần** | - Tuần 1: Phân tích BR, thiết kế Test Cases, dựng Skeleton Framework.<br>- Tuần 2: Lập trình Page Objects, tạo CSV Data, viết Test classes.<br>- Tuần 3: Chạy Regression, ghi nhận Defect Reports, lập Summary Report, gắn tag v1.0. |

---

### Bảng Phân Tích Rủi Ro (Project Risk Analysis)

| ID | Rủi ro tiềm ẩn | Xác suất | Ảnh hưởng | Giải pháp kiểm soát |
|:---|:---|:---:|:---:|:---|
| **R1** | Locator giao diện thay đổi do frontend tái cấu trúc | Cao | Cao | Ưu tiên dùng `data-testid`, `type`, `name`, `placeholder`, CSS Selector ổn định; tuyệt đối cấm XPath tuyệt đối. |
| **R2** | Dữ liệu test bị trùng lặp (đặt bàn trùng SĐT, giờ đặt) | Cao | Trung bình | Sử dụng `DataGenerator` sinh số điện thoại và thời gian ngẫu nhiên theo timestamp. |
| **R3** | Kiểm thử flaky do mạng chậm hoặc render trễ | Trung bình | Cao | Sử dụng 100% `WebDriverWait` (Explicit Wait), cấm dùng `Thread.sleep()`. |
| **R4** | Xung đột mã nguồn (Merge conflict) khi nhiều người commit | Trung bình | Trung bình | Mỗi người 1 module độc lập theo chiều dọc; file cấu hình chung chỉ 1 người sở hữu; tuân thủ quy trình Pull Request có review. |
