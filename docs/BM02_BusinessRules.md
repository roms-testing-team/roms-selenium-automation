# BM02 – BUSINESS RULES & TEST CONDITIONS (ROMS)

Tài liệu này tổng hợp các Quy tắc nghiệp vụ (Business Rules) có thể kiểm thử được qua giao diện người dùng (UI-testable) cho 4 module chính của hệ thống ROMS.

---

## 1. Module M1: Đăng Nhập Nhân Viên & Phân Quyền (Staff Login & RBAC)
- **URL:** `/login` | **Actor:** Manager, Waiter, Kitchen Staff, Cashier
- **Giao diện:** `StaffLoginPage.jsx`

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-STAFF-01` | Đăng nhập đúng Mã nhân viên & PIN của Quản lý (`MANAGER`) | Đăng nhập thành công, chuyển hướng vào `/admin` (hoặc `/admin/reports`), hiển thị thông tin ca trực | Decision Table | ✅ |
| `RULE-STAFF-02` | Đăng nhập đúng Mã nhân viên & PIN của Phục vụ (`WAITER`) | Đăng nhập thành công, chuyển hướng vào `/waiter/floor-plan` | Decision Table | ✅ |
| `RULE-STAFF-03` | Đăng nhập đúng Mã nhân viên & PIN của Bếp (`KITCHEN`) | Đăng nhập thành công, chuyển hướng vào `/kitchen/kds` | Decision Table | ✅ |
| `RULE-STAFF-04` | Đăng nhập đúng Mã nhân viên & PIN của Thu ngân (`CASHIER`) | Đăng nhập thành công, chuyển hướng vào `/pos/checkout` | Decision Table | ✅ |
| `RULE-STAFF-05` | Sai mã PIN hoặc mã nhân viên không tồn tại | Ở lại `/login`, hiển thị thông báo lỗi: *"Mã nhân viên hoặc mã PIN không chính xác"* | Decision Table | ✅ |
| `RULE-STAFF-06` | Để trống Mã nhân viên hoặc Mã PIN | Chặn gửi form, hiển thị thông báo yêu cầu nhập trường bắt buộc | EP | ✅ |
| `RULE-STAFF-07` | Nhập mã PIN không đủ 6 chữ số hoặc chứa chữ cái | Chặn gửi form, hiển thị thông báo: *"Mã PIN ca trực phải gồm chính xác 6 chữ số!"* | BVA / EP | ✅ |
| `RULE-STAFF-08` | Phân quyền: Truy cập trái phép đường dẫn quản trị (VD: Waiter gõ URL `/admin`) | Hệ thống chặn qua `ProtectedRoute`, chuyển hướng về trang chủ ca trực của Waiter hoặc báo lỗi 403 | EP | ✅ |

---

## 2. Module M2: Đặt Bàn Trực Tuyến (Public Table Reservation)
- **URL:** `/reservation` | **Actor:** Khách hàng vãng lai (Public Customer)
- **Giao diện:** `ReservationPage.jsx`

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-RESV-01` | Số lượng khách (Party size) hợp lệ trong khoảng [1, 20] | Chấp nhận đặt bàn với các giá trị: 1, 2, 10, 19, 20 | BVA | ✅ |
| `RULE-RESV-02` | Số lượng khách vi phạm biên: $\le 0$ hoặc $> 20$ | Báo lỗi: *"Số lượng khách phải từ 1 đến 20 người"* | BVA | ✅ |
| `RULE-RESV-03` | Số điện thoại Việt Nam hợp lệ (10 số, bắt đầu bằng số 0) | Chấp nhận số hợp lệ (VD: `0912345678`) | EP / BVA | ✅ |
| `RULE-RESV-04` | Số điện thoại không hợp lệ (9 số, 11 số, chứa chữ, bắt đầu khác 0) | Báo lỗi định dạng số điện thoại không hợp lệ | EP | ✅ |
| `RULE-RESV-05` | Họ tên khách hàng hợp lệ (2–50 ký tự, có dấu tiếng Việt) | Form hợp lệ | EP / BVA | ✅ |
| `RULE-RESV-06` | Họ tên khách hàng chứa ký tự script hoặc để trống | Báo lỗi trường họ tên không được để trống / không hợp lệ | Error Guessing | ✅ |
| `RULE-RESV-07` | Ngày giờ đặt bàn phải trong tương lai và trong khung giờ hoạt động (09:00 - 22:00) | Chọn thời gian hợp lệ thì submit thành công; chọn quá khứ bị chặn | EP | ✅ |
| `RULE-RESV-08` | Đặt bàn thành công với đầy đủ thông tin hợp lệ | Hiển thị màn hình / Modal xác nhận thành công kèm Mã Đặt Bàn | State Transition | ✅ |

---

## 3. Module M3: Thực Đơn Số, Tìm Kiếm & Lọc Món (Digital Menu Browsing)
- **URL:** `/dining/menu` | **Actor:** Thực khách tại bàn
- **Giao diện:** `CustomerMenuPage.jsx`

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-MENU-01` | Tìm kiếm theo tên món (tiếng Việt có dấu, không dấu, không phân biệt hoa thường) | Trả về danh sách các món ăn có tên chứa từ khóa tìm kiếm | EP | ✅ |
| `RULE-MENU-02` | Tìm kiếm từ khóa không có trong thực đơn (VD: `monan9999`) | Hiển thị thông báo *"Không tìm thấy món ăn phù hợp"* | EP | ✅ |
| `RULE-MENU-03` | Lọc món theo danh mục (Category: Khai vị, Món chính, Đồ uống...) | Toàn bộ món ăn hiển thị đều thuộc đúng danh mục đã chọn | EP | ✅ |
| `RULE-MENU-04` | Nhập chuỗi tấn công XSS / SQLi vào thanh tìm kiếm (`<script>`, `' OR 1=1`) | Hệ thống xử lý an toàn, không bật alert, không crash ứng dụng | Error Guessing | ✅ |
| `RULE-MENU-05` | Món ăn ở trạng thái Tạm hết / Hết hàng | Món bị làm mờ, có nhãn "Hết món", nút "Thêm vào giỏ" bị vô hiệu | EP | ✅ |

---

## 4. Module M4: Giỏ Hàng & Gọi Món Tại Bàn (Customer Cart & Ordering)
- **URL:** `/dining/cart` | **Actor:** Thực khách tại bàn
- **Giao diện:** `CustomerCartPage.jsx`

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-CART-01` | Thêm món ăn từ Menu vào giỏ | Số lượng badge giỏ hàng tăng tương ứng | State Transition | ✅ |
| `RULE-CART-02` | Thêm cùng 1 món nhiều lần | Hệ thống cộng dồn số lượng của món đó, không tạo thêm dòng mới | State Transition | ✅ |
| `RULE-CART-03` | Điều chỉnh số lượng món trong giỏ trong khoảng [1, 99] | Cập nhật số lượng hiển thị hợp lệ | BVA | ✅ |
| `RULE-CART-04` | Giảm số lượng món về 0 hoặc bấm nút Xóa món | Món ăn biến mất khỏi danh sách giỏ hàng | State Transition | ✅ |
| `RULE-CART-05` | Tính toán thành tiền từng món và tổng tiền tạm tính | Thành tiền dòng = Đơn giá × SL; Tổng tiền = $\sum$ các dòng | EP | ✅ |
| `RULE-CART-06` | Thêm ghi chú đặc biệt cho món (VD: "Không hành", "Ít cay") | Ghi chú được lưu và hiển thị đúng kèm món trong giỏ | EP | ✅ |
| `RULE-CART-07` | Gửi đơn gọi món thành công (Batch Order) | Giỏ hàng được chuyển trạng thái sang đơn chờ xác nhận (`/dining/orders/tracking`) | State Transition | ✅ |
