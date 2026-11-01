# BM02 – BUSINESS RULES & TEST CONDITIONS (ROMS)
## Áp Dụng Cho 3 Chức Năng: Đăng Nhập, Gọi Món, Đặt Bàn

Tài liệu này tổng hợp toàn bộ các Quy tắc nghiệp vụ (Business Rules) có thể kiểm thử được qua giao diện người dùng (UI-testable) cho 3 chức năng chính của hệ thống ROMS theo đúng phân công thành viên.

---

## 1. Module M1: Đăng Nhập Nhân Viên & Phân Quyền (Staff Login & RBAC)
- **URL:** `/login` | **Actor:** Manager, Waiter, Kitchen Staff, Cashier
- **Giao diện:** `StaffLoginPage.jsx`
- **Người phụ trách:** **Ngô Viết Bảo Huy (`ngo17574-ops`)**

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-STAFF-01` | Đăng nhập đúng Mã nhân viên & PIN của Quản lý (`MANAGER`) | Đăng nhập thành công, chuyển hướng vào `/admin` (hoặc `/admin/reports`), hiển thị thông tin ca trực | Decision Table | ✅ |
| `RULE-STAFF-02` | Đăng nhập đúng Mã nhân viên & PIN của Phục vụ (`WAITER`) | Đăng nhập thành công, chuyển hướng vào `/waiter/floor-plan` | Decision Table | ✅ |
| `RULE-STAFF-03` | Đăng nhập đúng Mã nhân viên & PIN của Bếp (`KITCHEN`) | Đăng nhập thành công, chuyển hướng vào `/kitchen/kds` | Decision Table | ✅ |
| `RULE-STAFF-04` | Đăng nhập đúng Mã nhân viên & PIN của Thu ngân (`CASHIER`) | Đăng nhập thành công, chuyển hướng vào `/pos/checkout` | Decision Table | ✅ |
| `RULE-STAFF-05` | Sai mã PIN hoặc mã nhân viên không tồn tại | Ở lại `/login`, hiển thị thông báo lỗi: *"Mã nhân viên hoặc mã PIN không chính xác"* | Decision Table | ✅ |
| `RULE-STAFF-06` | Để trống Mã nhân viên hoặc Mã PIN | Chặn gửi form, hiển thị thông báo yêu cầu nhập trường bắt buộc | EP | ✅ |
| `RULE-STAFF-07` | Nhập mã PIN không đủ 6 chữ số hoặc chứa chữ cái | Chặn gửi form, hiển thị thông báo: *"Mã PIN ca trực phải gồm chính xác 6 chữ số!"* | BVA / EP | ✅ |
| `RULE-STAFF-08` | Phân quyền URL: Truy cập trái phép trang quản trị (VD: Waiter gõ URL `/admin`) | Hệ thống chặn qua `ProtectedRoute`, chuyển hướng về trang chủ ca trực của Waiter hoặc báo lỗi 403 | EP | ✅ |

---

## 2. Module M2: Thực Đơn Số & Gọi Món Tại Bàn (Menu & Table Ordering)
- **URL:** `/dining/menu` và `/dining/cart` | **Actor:** Thực khách tại bàn
- **Giao diện:** `CustomerMenuPage.jsx` kết hợp `CustomerCartPage.jsx`
- **Người phụ trách:** **Phạm Minh Đức (`ducminh14-cmd`)**

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-ORDER-01` | Tìm kiếm món ăn theo tên (tiếng Việt có dấu, không dấu, không phân biệt hoa thường) | Danh sách món được lọc hiển thị chính xác theo từ khóa tìm kiếm | EP | ✅ |
| `RULE-ORDER-02` | Lọc món theo danh mục (Category: Khai vị, Món chính, Đồ uống...) | Toàn bộ món ăn hiển thị đều thuộc đúng danh mục đã chọn | EP | ✅ |
| `RULE-ORDER-03` | Thêm món ăn từ Menu vào đơn gọi món | Icon giỏ hàng tăng số lượng món tương ứng | State Transition | ✅ |
| `RULE-ORDER-04` | Thêm cùng 1 món nhiều lần | Hệ thống cộng dồn số lượng của món đó trong giỏ, không tạo thêm dòng mới | State Transition | ✅ |
| `RULE-ORDER-05` | Điều chỉnh số lượng món trong khoảng [1, 99] | BVA tại 1, 99 hợp lệ; vượt quá 99 giới hạn tối đa | BVA | ✅ |
| `RULE-ORDER-06` | Giảm số lượng món về 0 hoặc bấm nút Xóa món | Dòng món ăn biến mất khỏi đơn gọi món, tổng tiền cập nhật lại | State Transition | ✅ |
| `RULE-ORDER-07` | Tính toán thành tiền từng món và tổng tiền tạm tính | Thành tiền dòng = Đơn giá × SL; Tổng tiền = $\sum$ các dòng | EP | ✅ |
| `RULE-ORDER-08` | Thêm ghi chú cho món (VD: "Ít cay", "Không đá") | Ghi chú được lưu đúng vào món trong đơn gọi | EP | ✅ |
| `RULE-ORDER-09` | Món ăn ở trạng thái Tạm hết (Out of stock) | Món bị làm mờ, có tag "Hết món", vô hiệu hóa nút thêm | EP | ✅ |
| `RULE-ORDER-10` | Bấm "Gửi đơn gọi món" (Batch Order Placement) | Đơn gọi món được gửi thành công, chuyển sang trang theo dõi trạng thái món (`/dining/orders/tracking`) | State Transition | ✅ |

---

## 3. Module M3: Đặt Bàn Trực Tuyến (Public Table Reservation)
- **URL:** `/reservation` | **Actor:** Khách hàng vãng lai (Public Customer)
- **Giao diện:** `ReservationPage.jsx`
- **Người phụ trách:** **Dương Trong (`duogtrog06tk`)**

| BR ID | Nội dung Quy tắc nghiệp vụ | Kết quả mong đợi trên UI | Kỹ thuật | Auto? |
|:---|:---|:---|:---:|:---:|
| `RULE-RESV-01` | Số lượng khách (Party size) hợp lệ trong khoảng [1, 20] | Chấp nhận đặt bàn với các giá trị: 1, 2, 10, 19, 20 | BVA | ✅ |
| `RULE-RESV-02` | Số lượng khách vi phạm biên: $\le 0$ hoặc $> 20$ | Báo lỗi: *"Số lượng khách phải từ 1 đến 20 người"* | BVA | ✅ |
| `RULE-RESV-03` | Số điện thoại Việt Nam hợp lệ (10 số, bắt đầu bằng số 0) | Chấp nhận số hợp lệ (VD: `0912345678`) | EP / BVA | ✅ |
| `RULE-RESV-04` | Số điện thoại không hợp lệ (9 số, 11 số, chứa chữ, bắt đầu khác 0) | Báo lỗi định dạng số điện thoại không hợp lệ | EP | ✅ |
| `RULE-RESV-05` | Họ tên khách hàng hợp lệ (2–50 ký tự, có dấu tiếng Việt) | Form hợp lệ | EP / BVA | ✅ |
| `RULE-RESV-06` | Họ tên khách hàng chứa ký tự script hoặc để trống | Báo lỗi trường họ tên không được để trống / không hợp lệ | Error Guessing | ✅ |
| `RULE-RESV-07` | Ngày giờ đặt bàn phải trong tương lai và trong khung giờ hoạt động (09:00 - 22:00) | Chọn thời gian hợp lệ thì submit thành công; chọn quá khứ bị chặn | EP | ✅ |
| `RULE-RESV-08` | Đặt bàn thành công với đầy đủ thông tin hợp lệ | Hiển thị thông báo hoặc Modal xác nhận thành công kèm Mã Đặt Bàn | State Transition | ✅ |
