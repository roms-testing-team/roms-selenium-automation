# TÀI LIỆU HƯỚNG DẪN BÁO CÁO CÁ NHÂN — DƯƠNG TRONG
## Môn học: SWT301 – Software Testing | Đồ án: ROMS Automation Testing

> **Dành riêng cho:** **Dương Trong** (GitHub: `@duogtrog06tk`)  
> **Mục đích:** Tài liệu này tổng hợp toàn bộ công việc bạn đã làm, kèm giải thích cặn kẽ thuật ngữ chuyên ngành (bằng ngôn ngữ dễ hiểu nhất) và kịch bản trả lời chất vấn để bạn tự tin giải trình với Cô giáo.

---

# PHẦN 1: TỔNG QUAN VAI TRÒ CỦA DƯƠNG TRONG (NÓI GÌ KHI MỞ ĐẦU?)

Khi Cô hỏi: *"Dương Trong làm phần gì trong nhóm này?"*, bạn trả lời gãy gọn theo 2 ý chính:

1. **Về mặt Chức năng (Chiều dọc):**  
   Em phụ trách trọn vẹn **Module 3: Đặt bàn trực tuyến (`/reservation`)** từ đầu đến cuối:
   - Viết quy tắc nghiệp vụ (Business Rules).
   - Thiết kế 16 Test Cases áp dụng kỹ thuật hộp đen (BVA, EP, State Transition).
   - Chuẩn bị dữ liệu kiểm thử hướng dữ liệu (Data-Driven CSV).
   - Lập trình lớp **`ReservationPage.java`** theo mô hình Page Object Model (POM) và lớp kiểm thử **`ReservationTest.java`**.
   - Chạy tự động hóa trên server thật: **16/16 Test Cases PASS 100%**.

2. **Về mặt Hệ thống & Đóng gói (Chiều ngang):**  
   Em đảm nhận vai trò **Documentation & Defect Manager**:
   - Quản lý chuẩn hóa các biểu mẫu **BM01 $\rightarrow$ BM09** theo chuẩn quốc tế **ISTQB CTFL v4.0** và **ISO/IEC/IEEE 29119-3**.
   - Trực tiếp chạy **Regression Testing (Kiểm thử hồi quy)** cho toàn bộ 3 module của cả nhóm (42 test cases) trên server live `http://roms-restaurant.duckdns.org/` $\rightarrow$ **Pass 100% trong 4 phút 55 giây**.
   - Xuất báo cáo HTML **Maven Surefire Report** đặt tại `docs/reports/surefire-report.html`.
   - Tìm ra và trực tiếp log **3 Defect thực tế** lên GitHub Issues (#18, #19, #20) và hoàn thiện tài liệu **`BM07_DefectReport.md`**.
   - Hoàn thiện báo cáo tổng kết **`BM08_TestSummaryReport.md`**, gắn tag phát hành **`git tag v1.0`** và cập nhật link Video Demo Google Drive.

---

# PHẦN 2: CHI TIẾT CÔNG VIỆC THEO 3 GIAI ĐOẠN (ĐỐI CHIẾU FILE CỤ THỂ)

### 📌 GIAI ĐOẠN 1: PHÂN TÍCH & THIẾT KẾ (Tuần 1 - Analysis & Design)
*File tài liệu liên quan: `docs/BM02_BusinessRules.md`, `docs/BM03_TestCases.xlsx`, `docs/BM03_TestCases_Specification.md`, `src/test/resources/testdata/reservation_data.csv`*

* **Công việc đã làm:**
  1. **Xây dựng 8 Business Rules (Quy tắc nghiệp vụ):** Từ `RULE-RESV-01` đến `RULE-RESV-08` quy định: số khách tối đa 20 người, định dạng SĐT Việt Nam 10 số, không được đặt ngày/giờ trong quá khứ, form phải hiện Modal xác nhận có mã `RSV-XXXX`.
  2. **Thiết kế 16 Test Cases chi tiết:**
     - **14 Test Cases Data-Driven:** Đưa vào file `reservation_data.csv` bao gồm:
       - *Kỹ thuật Phân vùng tương đương (EP):* Tên hợp lệ, SĐT đúng đầu số 03, 05, 07, 08, 09; Email đúng/sai format; ngày giờ tương lai/quá khứ.
       - *Kỹ thuật Phân tích giá trị biên (BVA):* Số khách ở biên dưới (1 người), biên trên (20 người), vượt biên (0 người, số âm, >20 người).
     - **2 Test Cases độc lập:** Kiểm thử chống tấn công XSS Injection (`<script>alert('XSS')</script>`) và kiểm thử đóng Popup Modal xác nhận.
  3. **Thiết lập Ma trận truy xuất nguồn gốc (RTM):** Đảm bảo mỗi quy tắc nghiệp vụ trong BM02 đều có ít nhất 1 Test Case kiểm chứng trong BM03.

---

### 📌 GIAI ĐOẠN 2: LẬP TRÌNH TỰ ĐỘNG HÓA POM (Tuần 2 - Implementation & Automation)
*File mã nguồn liên quan: `ReservationPage.java`, `ReservationTest.java`, Pull Request #16*

* **Công việc đã làm:**
  1. **Lập trình `ReservationPage.java` (Page Object):**
     - Tuân thủ nghiêm ngặt **8 nguyên tắc POM**: Không bao giờ viết lệnh `assertEquals` hay `assertTrue` trong Page Object; tất cả phương thức chỉ thực hiện hành động (action) hoặc trả về dữ liệu (state/boolean).
     - **Xử lý giao diện động (Dynamic Locators):** Giao diện của ROMS dùng component Grid của React (`mobile-guest-grid` và `mobile-time-grid`), các nút số khách và giờ không có ID cố định. Em đã viết locator XPath động linh hoạt:
       `//div[contains(@class, 'mobile-guest-grid')]//button[contains(., '4 người')]`
     - **Nói KHÔNG với `Thread.sleep`:** Em dùng **`WebDriverWait` (Explicit Wait)** kết hợp `ExpectedConditions.elementToBeClickable` và `visibilityOfElementLocated`. Nhờ vậy, khi React vừa render xong nút là click ngay lập tức, không bị chậm hay giật lag.
  2. **Lập trình `ReservationTest.java` (Test Script):**
     - Sử dụng `@ParameterizedTest` kết hợp `@CsvFileSource(resources = "/testdata/reservation_data.csv")` để tự động lặp qua 14 dòng dữ liệu CSV.
     - Sử dụng `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` để kiểm soát thứ tự chạy logic.
     - Kiểm thử trên Live Server: **16/16 tests PASS 100% (177 giây)**.
  3. **Quản lý Git:** Đẩy lên nhánh `feature/M3-table-reservation`, mở **Pull Request #16** trên GitHub với đầy đủ label, milestone và mô tả chi tiết.

---

### 📌 GIAI ĐOẠN 3: KIỂM THỬ HỒI QUY, LOG BUG & BÀN GIAO (Tuần 3 - Execution, Defect & Closure)
*File liên quan: `surefire-report.html`, `BM06 Execution Log`, `BM07_DefectReport.md`, `BM08_TestSummaryReport.md`, `README.md`, Git Tag `v1.0`*

* **Công việc đã làm:**
  1. **Chạy kiểm thử hồi quy (Issue #10 - Regression Execution):**
     - Em đại diện nhóm kéo toàn bộ mã nguồn của cả 3 bạn về chạy hồi quy toàn diện:
       - `StaffLoginTest` (M1): 12 tests PASS (46.56s)
       - `OrderTest` (M2): 14 tests PASS (71.66s)
       - `ReservationTest` (M3): 16 tests PASS (177.20s)
       - **Tổng cộng: 42/42 tests PASS 100% trong 4 phút 55 giây.**
     - Xuất báo cáo HTML Surefire đặt tại: [`docs/reports/surefire-report.html`](reports/surefire-report.html).
     - Điền kết quả 2 lần chạy (**RUN-01** kiểm tra độ ổn định và **RUN-02** chính thức) vào Sheet `Execution_Log` của file Excel `BM03_TestCases.xlsx`.
     - Mở **Pull Request #17** trên GitHub.
  2. **Ghi nhận lỗi Defect Report (Issue #11):**
     - Em thực hiện kiểm thử thăm dò (Exploratory Testing) và phát hiện **3 lỗi thực tế của hệ thống ROMS**, tạo 3 Issue tương ứng trên GitHub theo đúng chuẩn **ISTQB CTFL Section 5.5**:
       - **[Issue #18](https://github.com/roms-testing-team/roms-selenium-automation/issues/18) (Minor - Testability):** Trùng thuộc tính `id="outlined-required"` trên cả ô Tài khoản và Mật khẩu ở trang login (do code React dùng component mẫu của Material UI mà quên đặt ID riêng). Lỗi này khiến automation test không thể bắt bằng `By.id`.
       - **[Issue #19](https://github.com/roms-testing-team/roms-selenium-automation/issues/19) (Major):** Trong giỏ hàng, khi thêm cùng một món 2 lần thì hệ thống tạo 2 dòng riêng biệt thay vì cộng dồn số lượng.
       - **[Issue #20](https://github.com/roms-testing-team/roms-selenium-automation/issues/20) (Major):** Form Đặt bàn cho phép nhập và gửi thành công số lượng khách lên đến 50 hoặc 100 người, trong khi quy định tối đa là 20 khách (cả frontend và backend đều chưa có validation chặn).
     - Đồng bộ toàn bộ thông tin chi tiết vào file **`docs/BM07_DefectReport.md`**, mở **Pull Request #21**.
  3. **Hoàn thiện Test Summary Report & Đóng gói Release (Issue #12):**
     - Hoàn thiện file **`docs/BM08_TestSummaryReport.md`** theo chuẩn **ISO/IEC/IEEE 29119-3**, đưa ra quyết định phát hành **CONDITIONAL GO** (sẵn sàng bàn giao thử nghiệm nội bộ sau khi Dev sửa 2 lỗi Major).
     - Tích hợp link Video Demo kiểm thử thực tế lưu trên Google Drive:  
       👉 [Link Google Drive Video Demo](https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link).
     - Gắn tag phiên bản phát hành **`git tag v1.0`** trên GitHub.
     - Cập nhật tài liệu hướng dẫn **`README.md`** và mở **Pull Request #22**.

---

# PHẦN 3: "PHAO CỨU SINH" TRẢ LỜI CÂU HỎI VẤN ĐÁP CỦA CÔ GIÁO (ĂN ĐIỂM 10)

Cô giáo chấm bài thường sẽ hỏi xoáy vào kiến thức lý thuyết gắn liền với code. Dưới đây là các câu hỏi kinh điển và cách trả lời chuẩn nhất:

### ❓ Câu 1: "Tại sao em lại dùng mô hình Page Object Model (POM)? Nó đem lại lợi ích gì?"
* **Trả lời:**  
  *"Thưa Cô, POM giúp tách biệt hoàn toàn giữa **Giao diện (Locators & Hành vi người dùng)** và **Logic kiểm thử (Assertions & Data)**. Lợi ích lớn nhất là **Dễ bảo trì và Giảm trùng lặp mã**:  
  Sau này nếu Dev đổi giao diện (ví dụ đổi nút 'Xác nhận đặt bàn' thành 'Gửi yêu cầu' hoặc đổi ID/CSS), em chỉ cần vào duy nhất file `ReservationPage.java` sửa lại đúng 1 dòng Locator, toàn bộ 16 ca test trong `ReservationTest.java` sẽ tự động chạy đúng mà không cần phải đi sửa từng test case một."*

---

### ❓ Câu 2: "Tại sao trong code của em không có `Thread.sleep`? Làm sao để không bị lỗi không tìm thấy phần tử (NoSuchElementException)?"
* **Trả lời:**  
  *"Thưa Cô, `Thread.sleep` là hard wait, là một **anti-pattern** trong automation testing vì nó bắt trình duyệt dừng cứng (ví dụ ngủ 5 giây thì dù trang tải xong trong 0.5 giây vẫn phải đợi đủ 5 giây, gây lãng phí thời gian và nếu mạng lag quá 5 giây thì test vẫn fail).*  
  *Thay vào đó, em dùng **Explicit Wait (`WebDriverWait`)** kế thừa từ `BasePage`. Em dùng các điều kiện thông minh của `ExpectedConditions`:*  
  - *`elementToBeClickable`: Chờ phần tử vừa hiển thị vừa sẵn sàng để click.*  
  - *`visibilityOfElementLocated`: Chờ phần tử hiển thị lên DOM trước khi lấy text.*  
  *Nhờ vậy bộ test chạy rất mượt, 42 test cases chỉ mất có 4 phút 55 giây mà tỷ lệ Pass đạt tuyệt đối 100%."*

---

### ❓ Câu 3: "Em thiết kế Test Cases dựa trên những kỹ thuật nào trong giáo trình ISTQB?"
* **Trả lời:**  
  *"Thưa Cô, em áp dụng 3 kỹ thuật Black-box Testing chính:*  
  1. ***Phân vùng tương đương (Equivalence Partitioning - EP):*** *Chia dữ liệu thành các vùng hợp lệ và không hợp lệ. Ví dụ: SĐT hợp lệ (10 số, đầu 03, 05, 07, 08, 09), SĐT không hợp lệ (chứa chữ cái, ký tự đặc biệt, thiếu số, thừa số).*  
  2. ***Phân tích giá trị biên (Boundary Value Analysis - BVA):*** *Áp dụng cho trường Số khách (1 đến 20 khách). Em chọn các giá trị biên: giá trị hợp lệ nhỏ nhất (1 khách), giá trị hợp lệ lớn nhất (20 khách); giá trị ngoài biên (0 khách, số âm, 21 khách, 50 khách).*  
  3. ***Chuyển đổi trạng thái (State Transition):*** *Kiểm tra luồng đi từ điền form $\rightarrow$ bấm Submit $\rightarrow$ hệ thống chuyển sang trạng thái hiển thị Popup Modal xác nhận kèm Mã đặt bàn `RSV-XXXX` $\rightarrow$ bấm Đóng modal."*

---

### ❓ Câu 4: "Tại sao chạy test 42/42 Pass 100% mà em lại ghi nhận có 3 Defect trong BM07?"
* **Trả lời (Câu này thể hiện tư duy Tester đẳng cấp):**  
  *"Thưa Cô, 42 ca kiểm thử tự động Pass 100% chứng minh rằng **hệ thống hiện tại hoạt động đúng theo các kịch bản kiểm thử đã được lập trình sẵn**.*  
  *Tuy nhiên, kiểm thử tự động chỉ kiểm tra những gì kịch bản bảo nó kiểm tra. Là một Tester có tư duy phản biện, em đã thực hiện thêm **Exploratory Testing (Kiểm thử thăm dò)** và **Code Inspection**:*  
  - *Khi inspect mã nguồn trang login, em thấy hai ô input bị trùng ID `outlined-required`. Đây là lỗi **Testability** cản trở việc viết automation test bằng `By.id`.*  
  - *Khi thử nhập số khách là 50 người, form vẫn cho gửi thành công dù quy định nghiệp vụ trong BM02 chỉ cho phép tối đa 20 người. Đây là **lỗi thiếu sót trong validation của Dev**.*  
  *Việc phát hiện ra các khiếm khuyết ngoài kịch bản tự động thể hiện đúng mục tiêu của chuẩn ISTQB là liên tục tìm kiếm và ngăn chặn rủi ro chất lượng cho sản phẩm."*

---

### ❓ Câu 5: "Quy trình Git của nhóm em được tổ chức như thế nào?"
* **Trả lời:**  
  *"Thưa Cô, nhóm em áp dụng **Git Feature Branch Workflow**:*  
  - *Nhánh `main` là nhánh phát hành chính thức (được gắn tag `v1.0`).*  
  - *Nhánh `develop` là nhánh tích hợp của cả nhóm.*  
  - *Khi em làm tính năng Đặt bàn, em tạo nhánh riêng `feature/M3-table-reservation`. Khi làm báo cáo hồi quy em tạo nhánh `test/regression-execution-report`, khi làm bug em tạo `docs/defect-reports`, khi làm tổng kết em tạo `docs/summary-report-release-v1.0`.*  
  - *Sau khi commit xong, em đẩy lên GitHub và mở **Pull Request** vào nhánh `develop`. Các bạn trong nhóm sẽ vào **Review chéo (Peer Review)** kiểm tra code xem có vi phạm quy tắc POM không, kiểm tra chạy thử rồi mới Approve và Merge."*

---

# PHẦN 4: THỨ TỰ THAO TÁC TRÊN MÁY TÍNH KHI BÁO CÁO

Để phần trình bày trôi chảy và thuyết phục nhất, bạn nên mở sẵn 4 tab sau trên trình duyệt và VS Code:

1. **Tab 1: Báo cáo Surefire Report HTML**  
   Mở file: [`docs/reports/surefire-report.html`](reports/surefire-report.html)  
   $\rightarrow$ *Chỉ cho Cô thấy bảng tổng kết 42 Tests, 0 Failures, 100% Success Rate, thời gian 295.4s.*
2. **Tab 2: Mã nguồn `ReservationPage.java` & `ReservationTest.java` trên VS Code**  
   $\rightarrow$ *Chỉ cho Cô thấy cấu trúc POM, không dùng `Thread.sleep`, dùng `@ParameterizedTest` đọc từ file `reservation_data.csv`.*
3. **Tab 3: GitHub Pull Requests & Issues của nhóm**  
   Đường dẫn: `https://github.com/roms-testing-team/roms-selenium-automation`  
   $\rightarrow$ *Chỉ cho Cô thấy các PR #16, #17, #21, #22 và 3 Bug Issues #18, #19, #20.*
4. **Tab 4: Thư mục Video Demo Google Drive**  
   Đường dẫn: `https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link`  
   $\rightarrow$ *Sẵn sàng mở nếu Cô yêu cầu xem video minh chứng.*

---

> 🚀 **Lời khuyên:** Bạn chỉ cần đọc kỹ tài liệu này 1-2 lần để nắm rõ các ý chính. Khi trình bày, hãy nói với giọng tự tin, rõ ràng, tập trung vào những số liệu thực tế (42 tests, 100% pass, 3 bugs, 16 test cases M3) — Cô giáo sẽ đánh giá rất cao sự am hiểu và tính trung thực, chuyên nghiệp trong bài làm của bạn!
