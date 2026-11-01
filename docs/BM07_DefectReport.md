# BM07 – DEFECT REPORT (DANH SÁCH BÁO CÁO SỰ CỐ / LỖI)
## Dự án: ROMS (3 Chức Năng: Đăng Nhập, Đặt Bàn, Gọi Món)

Tài liệu này ghi nhận các Defect phát hiện trên hệ thống ROMS trong quá trình kiểm thử tự động (Automation Regression Testing) cho 3 module chính. Mỗi Defect được quản lý đồng bộ với 1 Issue trên GitHub theo chuẩn quốc tế **ISTQB CTFL v4.0 (Section 5.5 - Defect Management)**.

---

### Danh Sách Defect Tổng Hợp

| Defect ID | GitHub Issue | Module | Tiêu đề lỗi | Mức độ (Severity) | Trạng thái (Status) | Người báo |
|:---|:---:|:---|:---|:---:|:---:|:---|
| `BUG-ROMS-01` | [#18](https://github.com/roms-testing-team/roms-selenium-automation/issues/18) | M1 Đăng nhập | Trùng thuộc tính `id="outlined-required"` trên cả ô Tài khoản và Mật khẩu ở form Đăng nhập | Minor (Testability) | Open | Dương Trọng |
| `BUG-ROMS-02` | [#19](https://github.com/roms-testing-team/roms-selenium-automation/issues/19) | M2 Gọi món | Thêm cùng một món ăn nhiều lần tạo ra các dòng riêng biệt trong giỏ hàng thay vì cộng dồn số lượng | Major | Open | Dương Trọng |
| `BUG-ROMS-03` | [#20](https://github.com/roms-testing-team/roms-selenium-automation/issues/20) | M3 Đặt bàn | Form Đặt bàn cho phép nhập và gửi thông tin với số lượng khách vượt quá quy định (> 20 khách) | Major | Open | Dương Trọng |

---

### Chi Tiết Từng Defect Report (Theo chuẩn ISTQB CTFL 5.5)

#### 1. Defect Report: BUG-ROMS-01
- **Defect ID:** `BUG-ROMS-01`
- **GitHub Issue:** [#18](https://github.com/roms-testing-team/roms-selenium-automation/issues/18)
- **Tiêu đề:** [Đăng nhập][Testability] Trùng thuộc tính id="outlined-required" trên cả trường Tài khoản và Mật khẩu ở form Đăng nhập
- **Người báo / Ngày:** Dương Trọng (`duogtrog06tk`) / 02-11-2026
- **Module / BR:** M1 Đăng nhập nhân viên / `RULE-LOGIN-01`
- **Môi trường:** Google Chrome 130+, Windows 11, Web SUT: `http://roms-restaurant.duckdns.org/login`
- **Severity:** Minor (Testability)
- **Priority:** Medium
- **Status:** Open (Open -> Fixed -> Retest -> Closed)
- **Mô tả chi tiết:**
  Trên trang đăng nhập nhân viên (`/login`), cả 2 trường nhập liệu Tài khoản (Username) và Mật khẩu (Password) đều mang chung thuộc tính HTML `id="outlined-required"`. Điều này xảy ra do sử dụng component mặc định của Material UI (MUI) TextField mà không cấu hình `id` hoặc `data-testid` tường minh cho từng trường.
- **Các bước tái hiện:**
  1. Truy cập `http://roms-restaurant.duckdns.org/login`.
  2. Mở DevTools (F12) kiểm tra DOM của ô Tài khoản: `<input id="outlined-required" name="username" ...>`.
  3. Kiểm tra DOM của ô Mật khẩu: `<input id="outlined-required" name="password" ...>`.
- **Kết quả mong đợi (Expected):**
  Mỗi trường input phải có một `id` duy nhất theo chuẩn W3C HTML5 (ví dụ `id="staff-username"` và `id="staff-password"`) hoặc bổ sung `data-testid` để phục vụ tự động hóa kiểm thử tin cậy.
- **Kết quả thực tế (Actual):**
  Hai ô input có cùng `id="outlined-required"`. Khi kiểm thử tự động bằng Selenium với locator `By.id("outlined-required")` sẽ luôn chỉ tìm thấy ô đầu tiên (Tài khoản).
- **Đề xuất khắc phục:**
  Bổ sung `id="username"` và `id="password"` hoặc `data-testid` riêng biệt trong code React component `StaffLoginPage.jsx`.

---

#### 2. Defect Report: BUG-ROMS-02
- **Defect ID:** `BUG-ROMS-02`
- **GitHub Issue:** [#19](https://github.com/roms-testing-team/roms-selenium-automation/issues/19)
- **Tiêu đề:** [Gọi món] Thêm cùng một món ăn nhiều lần tạo ra các dòng riêng biệt trong giỏ hàng thay vì cộng dồn số lượng
- **Người báo / Ngày:** Dương Trọng (`duogtrog06tk`) / 02-11-2026
- **Module / BR / TC:** M2 Gọi món tại bàn / `RULE-ORDER-04` / `TC-ORDER-004`
- **Môi trường:** Google Chrome 130+, Windows 11, Web SUT: `http://roms-restaurant.duckdns.org/dining/menu`
- **Severity:** Major
- **Priority:** High
- **Status:** Open (Open -> Fixed -> Retest -> Closed)
- **Điều kiện tiên quyết:** Bàn 01 đang mở phiên gọi món, giỏ hàng ban đầu rỗng.
- **Các bước tái hiện:**
  1. Truy cập trang Thực đơn: `http://roms-restaurant.duckdns.org/dining/menu?table=1`.
  2. Tìm món "Bò bít tết sốt tiêu", chọn số lượng = 2, bấm **"Thêm vào giỏ"**.
  3. Tiếp tục tìm lại món "Bò bít tết sốt tiêu", chọn số lượng = 3, bấm **"Thêm vào giỏ"**.
  4. Mở popup Giỏ hàng để kiểm tra danh sách món.
- **Kết quả mong đợi (Expected):**
  Giỏ hàng chỉ có 1 dòng duy nhất cho món "Bò bít tết sốt tiêu" với tổng số lượng hiển thị là 5 ($2 + 3 = 5$).
- **Kết quả thực tế (Actual):**
  Giỏ hàng xuất hiện 2 dòng riêng biệt: 1 dòng số lượng 2 và 1 dòng số lượng 3. Gây rối mắt và người dùng có thể nhầm lẫn khi chỉnh sửa số lượng trước khi gửi bếp.
- **Đề xuất khắc phục:**
  Trong reducer quản lý giỏ hàng (`cartSlice` hoặc state logic), kiểm tra nếu `item.id` đã tồn tại thì thực hiện cập nhật `item.quantity += newQuantity` thay vì thêm mới phần tử vào mảng `cart`.

---

#### 3. Defect Report: BUG-ROMS-03
- **Defect ID:** `BUG-ROMS-03`
- **GitHub Issue:** [#20](https://github.com/roms-testing-team/roms-selenium-automation/issues/20)
- **Tiêu đề:** [Đặt bàn] Form Đặt bàn cho phép nhập và gửi thông tin với số lượng khách vượt quá quy định (> 20 khách)
- **Người báo / Ngày:** Dương Trọng (`duogtrog06tk`) / 02-11-2026
- **Module / BR / TC:** M3 Đặt bàn trực tuyến / `RULE-RESV-01` / `TC-RESV-003`
- **Môi trường:** Google Chrome 130+, Windows 11, Web SUT: `http://roms-restaurant.duckdns.org/reservation`
- **Severity:** Major
- **Priority:** High
- **Status:** Open (Open -> Fixed -> Retest -> Closed)
- **Mô tả chi tiết:**
  Theo quy định nghiệp vụ trong `BM02_BusinessRules.md`, hệ thống đặt bàn trực tuyến chỉ phục vụ các đoàn khách tối đa 20 người. Các đoàn từ 21 khách trở lên phải liên hệ trực tiếp hotline để nhà hàng bố trí phòng tiệc riêng. Tuy nhiên, trên giao diện và API tiếp nhận đặt bàn, người dùng có thể nhập số khách là 50 hoặc 100 khách và gửi form thành công mà không nhận được cảnh báo ngăn chặn.
- **Các bước tái hiện:**
  1. Mở trang Đặt bàn: `http://roms-restaurant.duckdns.org/reservation`.
  2. Điền thông tin: Họ tên "Nguyễn Văn A", SĐT "0912345678", Email "vana@gmail.com".
  3. Nhập số khách: `50` (hoặc số lượng > 20).
  4. Chọn ngày và khung giờ hợp lệ, bấm **"Xác nhận đặt bàn"**.
- **Kết quả mong đợi (Expected):**
  Hệ thống hiển thị thông báo lỗi biên: *"Số lượng khách đặt trực tuyến tối đa là 20 người. Quý khách vui lòng liên hệ hotline 1900-xxxx để được phục vụ tiệc đoàn."* Form không được gửi đi.
- **Kết quả thực tế (Actual):**
  Form đặt bàn được gửi đi thành công, modal xác nhận hiển thị mã đặt bàn cho đoàn 50 khách.
- **Đề xuất khắc phục:**
  - Frontend: Thêm validation constraint `max="20"` trên ô nhập số khách và kiểm tra form submission.
  - Backend: Bổ sung validation annotation `@Max(value = 20, message = "Số lượng khách không được vượt quá 20")` trên DTO `ReservationRequest`.
