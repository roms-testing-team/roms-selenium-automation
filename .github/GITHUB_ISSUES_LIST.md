# DANH SÁCH GITHUB ISSUES & QUY ƯỚC ĐẶT TÊN NHÁNH (ROMS)
## Kế hoạch kiểm thử tự động 3 tuần (RBL)

> Tài liệu này liệt kê chi tiết 12 Issues trên GitHub kèm theo **Quy ước tên nhánh (Working Branch)** và câu lệnh git để các thành viên trong nhóm biết chính xác cách tạo nhánh làm việc độc lập mà không bị xung đột (conflict).

---

### Bảng Phân Công Nhiệm Vụ 3 Thành Viên:
* **Ngô Viết Bảo Huy (`ngo17574-ops`):** Phụ trách **Đăng nhập nhân viên** + Test Lead
* **Phạm Minh Đức (`ducminh14-cmd`):** Phụ trách **Thực đơn & Gọi món tại bàn** + Framework Owner
* **Dương Trong (`duogtrog06tk`):** Phụ trách **Đặt bàn trực tuyến** + Defect & Doc Manager

---

# GIAI ĐOẠN 1: ANALYSIS & DESIGN

### Issue #1: [Setup] Khởi tạo Skeleton Framework và cấu hình WebDriver/POM
* 🌿 **Tên nhánh (Branch):** `chore/setup-skeleton-framework`
* 💻 **Lệnh tạo nhánh:** `git checkout -b chore/setup-skeleton-framework`
* **Assignee:** `ngo17574-ops`, `ducminh14-cmd` | **Milestone:** `Giai đoạn 1: Analysis & Design`
* **Trạng thái:** ✅ **DONE (Closed)**

---

### Issue #2: [Docs] Soạn thảo Test Plan (BM01) và Business Rules (BM02)
* 🌿 **Tên nhánh (Branch):** `docs/testplan-businessrules`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/testplan-businessrules`
* **Assignee:** `ngo17574-ops` | **Milestone:** `Giai đoạn 1: Analysis & Design`
* **Trạng thái:** ✅ **DONE (Closed)**

---

### Issue #3: [TestDesign] Thiết kế Test Cases cho chức năng Đăng Nhập (M1)
* 🌿 **Tên nhánh (Branch):** `docs/M1-testcase-design-login`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/M1-testcase-design-login`
* **Assignee:** `ngo17574-ops` | **Milestone:** `Giai đoạn 1: Analysis & Design`
* **Mô tả:** Thiết kế 12 Test Cases Đăng nhập vào file Excel `docs/BM03_TestCases.xlsx` (Sheet `M1_StaffLogin`).

---

### Issue #4: [TestDesign] Thiết kế Test Cases cho chức năng Gọi Món Tại Bàn (M2)
* 🌿 **Tên nhánh (Branch):** `docs/M2-testcase-design-ordering`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/M2-testcase-design-ordering`
* **Assignee:** `ducminh14-cmd` | **Milestone:** `Giai đoạn 1: Analysis & Design`
* **Mô tả:** Thiết kế 14 Test Cases Gọi món vào file Excel `docs/BM03_TestCases.xlsx` (Sheet `M2_TableOrdering`).

---

### Issue #5: [TestDesign] Thiết kế Test Cases cho chức năng Đặt Bàn Trực Tuyến (M3)
* 🌿 **Tên nhánh (Branch):** `docs/M3-testcase-design-reservation`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/M3-testcase-design-reservation`
* **Assignee:** `duogtrog06tk` | **Milestone:** `Giai đoạn 1: Analysis & Design`
* **Mô tả:** Thiết kế 14 Test Cases Đặt bàn vào file Excel `docs/BM03_TestCases.xlsx` (Sheet `M3_Reservation`).

---

# GIAI ĐOẠN 2: IMPLEMENTATION & AUTOMATION

### Issue #6: [Auto][M1] Xây dựng StaffLoginPage và StaffLoginTest
* 🌿 **Tên nhánh (Branch):** `feature/M1-staff-login`
* 💻 **Lệnh tạo nhánh:** `git checkout -b feature/M1-staff-login`
* **Assignee:** `ngo17574-ops` | **Milestone:** `Giai đoạn 2: Implementation & Automation`
* **Mô tả:** Hoàn thiện `StaffLoginPage.java`, `StaffLoginTest.java` và chạy data-driven với `staff_login_data.csv`.

---

### Issue #7: [Auto][M2] Xây dựng OrderPage và OrderTest
* 🌿 **Tên nhánh (Branch):** `feature/M2-table-ordering`
* 💻 **Lệnh tạo nhánh:** `git checkout -b feature/M2-table-ordering`
* **Assignee:** `ducminh14-cmd` | **Milestone:** `Giai đoạn 2: Implementation & Automation`
* **Mô tả:** Lập trình `OrderPage.java`, `OrderTest.java` (thêm món, chỉnh SL, cộng dồn, gửi đơn).

---

### Issue #8: [Auto][M3] Xây dựng ReservationPage và ReservationTest
* 🌿 **Tên nhánh (Branch):** `feature/M3-table-reservation`
* 💻 **Lệnh tạo nhánh:** `git checkout -b feature/M3-table-reservation`
* **Assignee:** `duogtrog06tk` | **Milestone:** `Giai đoạn 2: Implementation & Automation`
* **Mô tả:** Lập trình `ReservationPage.java`, `ReservationTest.java` (form đặt bàn, validate SĐT, số khách, ngày giờ).

---

### Issue #9: [Review] Thực hiện Review chéo Pull Request giữa các thành viên
* 🌿 **Tên nhánh (Branch):** `review/cross-pr-reviews`
* **Assignee:** `ngo17574-ops`, `ducminh14-cmd` | **Milestone:** `Giai đoạn 2: Implementation & Automation`
* **Mô tả:** Review duyệt PR của nhau trên GitHub trước khi merge vào `main` (chặn Thread.sleep, XPath tuyệt đối, assert trong Page).

---

# GIAI ĐOẠN 3: EXECUTION, DEFECT & CLOSURE

### Issue #10: [Execute] Chạy Regression Test toàn bộ và xuất báo cáo Surefire
* 🌿 **Tên nhánh (Branch):** `test/regression-execution-report`
* 💻 **Lệnh tạo nhánh:** `git checkout -b test/regression-execution-report`
* **Assignee:** `ngo17574-ops` | **Milestone:** `Giai đoạn 3: Execution, Defect & Closure`
* **Mô tả:** Chạy `mvn clean test` 2 lần (RUN-01 và RUN-02) và xuất `mvn surefire-report:report`.

---

### Issue #11: [Bug] Ghi nhận Defect Report và tạo Bug Issues trên GitHub
* 🌿 **Tên nhánh (Branch):** `docs/defect-reports`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/defect-reports`
* **Assignee:** `duogtrog06tk` | **Milestone:** `Giai đoạn 3: Execution, Defect & Closure`
* **Mô tả:** Tạo ít nhất 2–3 Bug Issues trên GitHub có gán label `bug`, cập nhật vào `BM07_DefectReport.md`.

---

### Issue #12: [Report] Hoàn thiện Test Summary Report (BM08) & Gắn Tag v1.0
* 🌿 **Tên nhánh (Branch):** `docs/summary-report-release-v1.0`
* 💻 **Lệnh tạo nhánh:** `git checkout -b docs/summary-report-release-v1.0`
* **Assignee:** `ngo17574-ops`, `ducminh14-cmd` | **Milestone:** `Giai đoạn 3: Execution, Defect & Closure`
* **Mô tả:** Hoàn thiện BM08, cập nhật BM09, gắn tag `git tag v1.0` và quay video demo 5 phút nộp bài.
