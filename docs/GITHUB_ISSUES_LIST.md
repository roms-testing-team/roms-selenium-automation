# DANH SÁCH GITHUB ISSUES – DỰ ÁN ROMS TESTING
## Kế hoạch hoàn thành trong 1 ngày (1-Day Intensive Sprint)

> File này chuẩn bị sẵn **12 Issues chuẩn chỉ** để nhóm bạn copy/paste tạo trên GitHub Issues.  
> Việc tạo đầy đủ Issues, gán Assignee (Người làm) và đóng Issue (Close) khi hoàn thành sẽ giúp nhóm đạt điểm tối đa ở phần **Làm việc nhóm và GitHub (1.5 điểm)**.

---

### Bảng Phân Công Nhiệm Vụ 3 Thành Viên:
* **Ngô Viết Bảo Huy (`ngo17574-ops`):** Phụ trách **Đăng nhập nhân viên** + Test Lead
* **Phạm Minh Đức (`ducminh14-cmd`):** Phụ trách **Thực đơn & Gọi món tại bàn** + Framework Owner
* **Dương Trong (`duogtrog06tk`):** Phụ trách **Đặt bàn trực tuyến** + Defect & Doc Manager

---

# GIAI ĐOẠN 1: BUỔI SÁNG (ANALYSIS & DESIGN)

### Issue #1: [Setup] Khởi tạo Skeleton Framework và cấu hình WebDriver/POM
- **Assignee:** `ngo17574-ops`, `ducminh14-cmd`
- **Labels:** `chore`, `base`
- **Milestone:** `Phase 1 - Morning`
- **Nội dung mô tả (Copy vào description):**
```markdown
## Mục tiêu
Dựng khung dự án Maven cho Selenium 4 + JUnit 5 và cấu hình base classes.

## Công việc cần làm
- [x] Tạo `pom.xml` với Selenium 4.21, JUnit 5.10, WebDriverManager 5.7, Maven Surefire Plugin.
- [x] Cấu hình `BaseTest.java` (quản lý lifecycle WebDriver, ChromeOptions, headless).
- [x] Cấu hình `BasePage.java` (Explicit Wait `WebDriverWait`, click, type, getText).
- [x] Tích hợp `ScreenshotOnFailure.java` tự động chụp ảnh màn hình khi test fail.
- [x] Cấu hình URL target trong `config.properties` trỏ về http://localhost:5173.
```

---

### Issue #2: [Docs] Soạn thảo Test Plan (BM01) và Business Rules (BM02)
- **Assignee:** `ngo17574-ops`
- **Labels:** `docs`
- **Milestone:** `Phase 1 - Morning`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Hoàn thiện tài liệu kế hoạch kiểm thử và trích xuất quy tắc nghiệp vụ cho 3 chức năng.

## Công việc cần làm
- [x] Hoàn thiện `docs/BM01_TestPlan.md` (Scope, Rủi ro, Entry/Exit criteria).
- [x] Hoàn thiện `docs/BM02_BusinessRules.md`:
  - Module M1: Đăng nhập nhân viên (RULE-STAFF-01 đến 08)
  - Module M2: Gọi món tại bàn (RULE-ORDER-01 đến 10)
  - Module M3: Đặt bàn trực tuyến (RULE-RESV-01 đến 08)
```

---

### Issue #3: [TestDesign] Thiết kế Test Cases cho chức năng Đăng Nhập (M1)
- **Assignee:** `ngo17574-ops`
- **Labels:** `module:login`, `docs`
- **Milestone:** `Phase 1 - Morning`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Thiết kế bộ Test Cases cho chức năng Đăng nhập nhân viên & Phân quyền URL.

## Công việc cần làm
- [ ] Áp dụng kỹ thuật Decision Table (Bảng quyết định) cho các ca: Đăng nhập đúng các Role (Manager, Waiter, Kitchen, Cashier), sai PIN, sai mã NV.
- [ ] Áp dụng Equivalence Partitioning cho validate bỏ trống, mã PIN không đủ 6 chữ số.
- [ ] Viết tối thiểu 10 Test Cases vào Sheet `M1_StaffLogin` của BM03.
- [ ] Chuẩn bị file dữ liệu CSV `src/test/resources/testdata/staff_login_data.csv`.
```

---

### Issue #4: [TestDesign] Thiết kế Test Cases cho chức năng Gọi Món Tại Bàn (M2)
- **Assignee:** `ducminh14-cmd`
- **Labels:** `module:ordering`, `docs`
- **Milestone:** `Phase 1 - Morning`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Thiết kế bộ Test Cases cho quy trình Thực đơn, Giỏ hàng và Gọi món theo đợt.

## Công việc cần làm
- [ ] Áp dụng Boundary Value Analysis (BVA) cho số lượng món trong giỏ [1, 99].
- [ ] Áp dụng State Transition (Chuyển trạng thái) cho luồng: Thêm món -> Cộng dồn -> Chỉnh SL -> Xóa món -> Gửi đơn gọi món.
- [ ] Viết tối thiểu 12 Test Cases vào Sheet `M2_TableOrdering` của BM03.
- [ ] Chuẩn bị file dữ liệu CSV `src/test/resources/testdata/order_data.csv`.
```

---

### Issue #5: [TestDesign] Thiết kế Test Cases cho chức năng Đặt Bàn Trực Tuyến (M3)
- **Assignee:** `duogtrog06tk`
- **Labels:** `module:reservation`, `docs`
- **Milestone:** `Phase 1 - Morning`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Thiết kế bộ Test Cases cho form Đặt bàn trực tuyến (`/reservation`).

## Công việc cần làm
- [ ] Áp dụng BVA cho số lượng khách Party Size [1, 20] (biên 0, 1, 2, 19, 20, 21).
- [ ] Áp dụng Equivalence Partitioning cho định dạng SĐT Việt Nam (10 số, đầu 0).
- [ ] Áp dụng Error Guessing cho validate họ tên (HTML tags, ký tự lạ), ngày giờ quá khứ.
- [ ] Viết tối thiểu 12 Test Cases vào Sheet `M3_Reservation` của BM03.
- [ ] Chuẩn bị file dữ liệu CSV `src/test/resources/testdata/reservation_data.csv`.
```

---

# GIAI ĐOẠN 2: BUỔI CHIỀU (IMPLEMENTATION & AUTOMATION)

### Issue #6: [Auto][M1] Xây dựng StaffLoginPage và StaffLoginTest
- **Assignee:** `ngo17574-ops`
- **Labels:** `module:login`, `test-script`
- **Milestone:** `Phase 2 - Afternoon`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Tự động hóa toàn bộ các ca kiểm thử Đăng nhập nhân viên theo mô hình POM.

## Công việc cần làm
- [ ] Hoàn thiện `StaffLoginPage.java`:
  - Locators ổn định cho Mã NV, Mã PIN, Nút đăng nhập, Thông báo lỗi.
  - Tuyệt đối không chứa assertion trong Page class.
- [ ] Hoàn thiện `StaffLoginTest.java`:
  - Dùng `@ParameterizedTest` và `@CsvFileSource`.
  - Assert chuyển hướng URL đúng vai trò (`/admin`, `/waiter`, `/kitchen`, `/pos`).
  - Assert thông báo lỗi khi đăng nhập sai.
- [ ] Tạo Pull Request merge vào `main` có ít nhất 1 bạn review duyệt.
```

---

### Issue #7: [Auto][M2] Xây dựng OrderPage và OrderTest
- **Assignee:** `ducminh14-cmd`
- **Labels:** `module:ordering`, `test-script`
- **Milestone:** `Phase 2 - Afternoon`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Tự động hóa các ca kiểm thử Thực đơn số và Gọi món tại bàn.

## Công việc cần làm
- [ ] Xây dựng `OrderPage.java`:
  - Thao tác chọn danh mục món, tìm kiếm món ăn.
  - Thao tác thêm vào giỏ, tăng/giảm số lượng món, xóa món.
  - Thao tác bấm nút "Gửi đơn gọi món".
- [ ] Xây dựng `OrderTest.java`:
  - Kiểm tra cộng dồn số lượng khi thêm trùng món.
  - Kiểm tra tính đúng tổng tiền từng dòng và tổng hóa đơn tạm tính.
  - Kiểm tra chuyển sang màn hình tracking trạng thái món.
- [ ] Tạo Pull Request merge vào `main`.
```

---

### Issue #8: [Auto][M3] Xây dựng ReservationPage và ReservationTest
- **Assignee:** `duogtrog06tk`
- **Labels:** `module:reservation`, `test-script`
- **Milestone:** `Phase 2 - Afternoon`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Tự động hóa các ca kiểm thử Đặt bàn trực tuyến.

## Công việc cần làm
- [ ] Xây dựng `ReservationPage.java`:
  - Locators cho Họ tên, SĐT, Số lượng khách, Ngày giờ đặt, Nút xác nhận.
- [ ] Xây dựng `ReservationTest.java`:
  - Kiểm tra các ca biên số lượng khách (0, 1, 20, 21).
  - Kiểm tra validate định dạng số điện thoại.
  - Kiểm tra thông báo đặt bàn thành công và mã đặt bàn.
- [ ] Tạo Pull Request merge vào `main`.
```

---

### Issue #9: [Review] Thực hiện Review chéo Pull Request giữa các thành viên
- **Assignee:** `ngo17574-ops`, `ducminh14-cmd`, `duogtrog06tk`
- **Labels:** `code-review`
- **Milestone:** `Phase 2 - Afternoon`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Đảm bảo chất lượng mã nguồn test và tuân thủ 8 quy tắc POM của môn học.

## Checklist kiểm duyệt khi Review PR:
- [ ] Không có `Thread.sleep()`.
- [ ] Không có XPath tuyệt đối (`/html/body/...`).
- [ ] Không có `Assertion` trong các lớp Page.
- [ ] Không có `By` hoặc `driver.findElement` trong các lớp Test.
- [ ] Dữ liệu test tách biệt hoàn toàn trong CSV, không hard-code.
```

---

# GIAI ĐOẠN 3: BUỔI TỐI (EXECUTION, DEFECT & CLOSURE)

### Issue #10: [Execute] Chạy Regression Test toàn bộ và xuất báo cáo Surefire
- **Assignee:** `ngo17574-ops`
- **Labels:** `test-execution`
- **Milestone:** `Phase 3 - Evening`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Thực thi toàn bộ bộ test suite trên môi trường tích hợp.

## Công việc cần làm
- [ ] Chạy lần 1 (RUN-01): `mvn clean test` -> phát hiện các test flaky, locator lệch để fix ngay.
- [ ] Chạy lần 2 (RUN-02): Chạy chính thức, kiểm tra tỷ lệ Pass Rate đạt $\ge 85\%$.
- [ ] Xuất báo cáo HTML: `mvn surefire-report:report`.
- [ ] Copy file báo cáo HTML vào `docs/reports/surefire-report.html`.
- [ ] Điền kết quả vào `BM06 Test Execution Log`.
```

---

### Issue #11: [Bug] Ghi nhận Defect Report và tạo Bug Issues trên GitHub
- **Assignee:** `duogtrog06tk`
- **Labels:** `bug`
- **Milestone:** `Phase 3 - Evening`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Ghi nhận đầy đủ các lỗi phát hiện trên hệ thống ROMS theo chuẩn ISTQB CTFL 5.5.

## Công việc cần làm
- [ ] Tạo ít nhất 2–3 Bug Issues trên GitHub (có label `bug`, gán severity, priority).
- [ ] Cập nhật bảng danh sách lỗi trong `docs/BM07_DefectReport.md`.
- [ ] Đính kèm ảnh chụp bằng chứng từ thư mục `target/screenshots/`.
```

---

### Issue #12: [Report] Hoàn thiện Test Summary Report (BM08) & Gắn Tag v1.0
- **Assignee:** `ngo17574-ops`, `ducminh14-cmd`, `duogtrog06tk`
- **Labels:** `docs`, `release`
- **Milestone:** `Phase 3 - Evening`
- **Nội dung mô tả:**
```markdown
## Mục tiêu
Tổng kết dự án kiểm thử tự động, chuẩn bị sẵn sàng nộp bài cho Giảng viên.

## Công việc cần làm
- [ ] Hoàn thiện `docs/BM08_TestSummaryReport.md` với các số liệu thực tế từ Surefire report.
- [ ] Hoàn thiện bảng đóng góp thành viên trong `docs/BM09_PhanCong.md`.
- [ ] Gắn tag release trên git:
  `git tag -a v1.0 -m "Release v1.0 - Complete ROMS Automation Testing"`
  `git push origin v1.0`
- [ ] Quay video demo thực thi kiểm thử (5–7 phút) giải thích 1 ca Pass và 1 ca Fail/Bug.
```
