# BM03 – TEST CASE SPECIFICATION & BM04 – RTM (ROMS)
## Dự Án Kiểm Thử Tự Động Website Quản Lý Đặt Món Nhà Hàng (ROMS)
### Chuẩn Áp Dụng: ISTQB CTFL v4.0 & ISO/IEC/IEEE 29119-3

> Tài liệu này chứa toàn bộ nội dung của:
> 1. **BM04 – Requirements Traceability Matrix (RTM)**: Ma trận truy vết độ bao phủ Business Rules.
> 2. **BM03 – Test Case Specification**: Bộ 40 Test Cases chi tiết cho 3 chức năng cốt lõi (M1 Đăng nhập, M2 Gọi món, M3 Đặt bàn).
> 3. **BM06 – Test Execution Log Template**: Mẫu nhật ký chạy kiểm thử hồi quy.
> 
> *Nhóm có thể copy trực tiếp từng bảng dưới đây vào các Sheet tương ứng trong file Excel `docs/BM03_TestCases.xlsx` để nộp Giảng viên.*

---

# PHẦN 1: BM04 – REQUIREMENTS TRACEABILITY MATRIX (RTM)
*(Copy vào Sheet 1: `RTM` trong Excel)*

| STT | Business Rule ID | Mô tả tóm tắt Rule | Test Condition ID | Danh sách Test Case IDs | Số TC | Automated | Pass | Coverage (%) |
|:---:|:---|:---|:---|:---|:---:|:---:|:---:|:---:|
| 1 | `RULE-STAFF-01` | Đăng nhập đúng Quản lý -> `/admin` | `TCOND-STAFF-01` | `TC-STAFF-001` | 1 | 1 | 1 | 100% |
| 2 | `RULE-STAFF-02` | Đăng nhập đúng Phục vụ -> `/waiter` | `TCOND-STAFF-02` | `TC-STAFF-002` | 1 | 1 | 1 | 100% |
| 3 | `RULE-STAFF-03` | Đăng nhập đúng Bếp -> `/kitchen` | `TCOND-STAFF-03` | `TC-STAFF-003` | 1 | 1 | 1 | 100% |
| 4 | `RULE-STAFF-04` | Đăng nhập đúng Thu ngân -> `/pos` | `TCOND-STAFF-04` | `TC-STAFF-004` | 1 | 1 | 1 | 100% |
| 5 | `RULE-STAFF-05` | Sai mã PIN hoặc mã NV không tồn tại | `TCOND-STAFF-05` | `TC-STAFF-005`, `TC-STAFF-006` | 2 | 2 | 2 | 100% |
| 6 | `RULE-STAFF-06` | Bỏ trống Mã NV hoặc PIN -> Chặn gửi | `TCOND-STAFF-06` | `TC-STAFF-007`, `TC-STAFF-008` | 2 | 2 | 2 | 100% |
| 7 | `RULE-STAFF-07` | PIN không đủ 6 số hoặc vượt 6 số | `TCOND-STAFF-07` | `TC-STAFF-009`, `TC-STAFF-010` | 2 | 2 | 2 | 100% |
| 8 | `RULE-STAFF-08` | Chặn truy cập URL trái phép | `TCOND-STAFF-08` | `TC-STAFF-011`, `TC-STAFF-012` | 2 | 2 | 2 | 100% |
| 9 | `RULE-ORDER-01` | Tìm kiếm món ăn theo tên | `TCOND-ORDER-01` | `TC-ORDER-001`, `TC-ORDER-002` | 2 | 2 | 2 | 100% |
| 10 | `RULE-ORDER-02` | Lọc món ăn theo danh mục Category | `TCOND-ORDER-02` | `TC-ORDER-003` | 1 | 1 | 1 | 100% |
| 11 | `RULE-ORDER-03` | Thêm món từ Menu vào giỏ | `TCOND-ORDER-03` | `TC-ORDER-004` | 1 | 1 | 1 | 100% |
| 12 | `RULE-ORDER-04` | Thêm trùng món -> Cộng dồn số lượng | `TCOND-ORDER-04` | `TC-ORDER-005` | 1 | 1 | 1 | 100% |
| 13 | `RULE-ORDER-05` | Số lượng món trong khoảng [1, 99] | `TCOND-ORDER-05` | `TC-ORDER-006`, `TC-ORDER-007`, `TC-ORDER-008` | 3 | 3 | 3 | 100% |
| 14 | `RULE-ORDER-06` | Xóa món khỏi đơn gọi khi SL = 0 | `TCOND-ORDER-06` | `TC-ORDER-009` | 1 | 1 | 1 | 100% |
| 15 | `RULE-ORDER-07` | Tính đúng thành tiền và tổng tiền | `TCOND-ORDER-07` | `TC-ORDER-010` | 1 | 1 | 1 | 100% |
| 16 | `RULE-ORDER-08` | Lưu và hiển thị ghi chú món ăn | `TCOND-ORDER-08` | `TC-ORDER-011` | 1 | 1 | 1 | 100% |
| 17 | `RULE-ORDER-09` | Món ăn hết hàng (Out of stock) | `TCOND-ORDER-09` | `TC-ORDER-012` | 1 | 1 | 1 | 100% |
| 18 | `RULE-ORDER-10` | Gửi đơn gọi món (Batch Order) | `TCOND-ORDER-10` | `TC-ORDER-013`, `TC-ORDER-014` | 2 | 2 | 2 | 100% |
| 19 | `RULE-RESV-01` | Số lượng khách hợp lệ [1, 20] | `TCOND-RESV-01` | `TC-RESV-001`, `TC-RESV-002`, `TC-RESV-003` | 3 | 3 | 3 | 100% |
| 20 | `RULE-RESV-02` | Số lượng khách vi phạm biên ($\le 0, > 20$) | `TCOND-RESV-02` | `TC-RESV-004`, `TC-RESV-005`, `TC-RESV-006` | 3 | 3 | 3 | 100% |
| 21 | `RULE-RESV-03` | SĐT Việt Nam hợp lệ (10 số, đầu 0) | `TCOND-RESV-03` | `TC-RESV-007` | 1 | 1 | 1 | 100% |
| 22 | `RULE-RESV-04` | SĐT không hợp lệ (9 số, 11 số, chứa chữ) | `TCOND-RESV-04` | `TC-RESV-008`, `TC-RESV-009`, `TC-RESV-010` | 3 | 3 | 3 | 100% |
| 23 | `RULE-RESV-05` | Họ tên khách hàng hợp lệ / Bỏ trống | `TCOND-RESV-05` | `TC-RESV-011` | 1 | 1 | 1 | 100% |
| 24 | `RULE-RESV-06` | An toàn với ký tự Script / XSS | `TCOND-RESV-06` | `TC-RESV-012` | 1 | 1 | 1 | 100% |
| 25 | `RULE-RESV-07` | Ngày giờ đặt trong tương lai | `TCOND-RESV-07` | `TC-RESV-013` | 1 | 1 | 1 | 100% |
| 26 | `RULE-RESV-08` | Đặt bàn thành công hiển thị Modal xác nhận | `TCOND-RESV-08` | `TC-RESV-014` | 1 | 1 | 1 | 100% |
| **Tổng** | **26 Rules** | | | **40 Test Cases** | **40** | **34** | **35** | **100%** |

---

# PHẦN 2: BM03 – TEST CASE SPECIFICATION CHO 3 CHỨC NĂNG

---

## 1. Module M1: Đăng Nhập Nhân Viên & Phân Quyền (Staff Login & RBAC)
*(Copy vào Sheet 2: `M1_StaffLogin` trong Excel)*  
**Người phụ trách:** Ngô Viết Bảo Huy (`ngo17574-ops`) | **URL:** `/login`

| TC ID | Test Case Title | BR ID | Tech | Pri | Pre-conditions | Test Steps | Test Data | Expected Result | Status | Auto? | Automation Method | Author |
|:---|:---|:---:|:---:|:---:|:---|:---|:---|:---|:---:|:---:|:---|:---:|
| `TC-STAFF-001` | Verify đăng nhập thành công vai trò Quản lý (MANAGER) | `RULE-STAFF-01` | DT | High | SUT hoạt động, DB có tài khoản `MGR-001` | 1. Mở `/login`<br>2. Nhập Mã NV<br>3. Nhập Mã PIN<br>4. Bấm "Đăng nhập" | `MGR-001`<br>`123456` | Chuyển hướng vào `/admin`, hiển thị tên ca trực Quản lý | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-002` | Verify đăng nhập thành công vai trò Phục vụ (WAITER) | `RULE-STAFF-02` | DT | High | DB có tài khoản `EMP-001` | Như trên | `EMP-001`<br>`123456` | Chuyển hướng vào `/waiter/floor-plan` | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-003` | Verify đăng nhập thành công vai trò Bếp (KITCHEN) | `RULE-STAFF-03` | DT | High | DB có tài khoản `KDS-001` | Như trên | `KDS-001`<br>`123456` | Chuyển hướng vào `/kitchen/kds` | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-004` | Verify đăng nhập thành công vai trò Thu ngân (CASHIER) | `RULE-STAFF-04` | DT | High | DB có tài khoản `CSH-001` | Như trên | `CSH-001`<br>`123456` | Chuyển hướng vào `/pos/checkout` | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-005` | Verify thông báo lỗi khi nhập sai mã PIN | `RULE-STAFF-05` | DT | High | Đang ở `/login` | 1. Nhập Mã NV đúng<br>2. Nhập sai PIN<br>3. Bấm Submit | `MGR-001`<br>`999999` | Ở lại `/login`, hiển thị lỗi *"Mã PIN không chính xác"* | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-006` | Verify thông báo lỗi khi nhập mã NV không tồn tại | `RULE-STAFF-05` | DT | High | Đang ở `/login` | 1. Nhập mã NV lạ<br>2. Nhập PIN<br>3. Bấm Submit | `UNKNOWN`<br>`123456` | Ở lại `/login`, hiển thị lỗi *"Mã nhân viên không tồn tại"* | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-007` | Verify form chặn submit khi bỏ trống trường Mã nhân viên | `RULE-STAFF-06` | EP | High | Đang ở `/login` | 1. Bỏ trống Mã NV<br>2. Nhập PIN<br>3. Bấm Submit | Code: ` ` (rỗng)<br>PIN: `123456` | Form chặn submit, hiện thông báo *"Vui lòng nhập Mã Nhân Viên"* | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-008` | Verify form chặn submit khi bỏ trống trường Mã PIN | `RULE-STAFF-06` | EP | High | Đang ở `/login` | 1. Nhập Mã NV<br>2. Bỏ trống PIN<br>3. Bấm Submit | Code: `MGR-001`<br>PIN: ` ` (rỗng) | Form chặn submit, yêu cầu nhập mã PIN | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-009` | Verify thông báo lỗi khi nhập mã PIN ngắn hơn 6 chữ số (5 số) | `RULE-STAFF-07` | BVA | High | Đang ở `/login` | 1. Nhập Mã NV<br>2. Nhập PIN 5 số<br>3. Bấm Submit | Code: `MGR-001`<br>PIN: `12345` | Báo lỗi *"Mã PIN ca trực phải gồm chính xác 6 chữ số!"* | Pass | Yes | `StaffLoginTest#testStaffLoginDataDriven` | Bảo Huy |
| `TC-STAFF-010` | Verify ô nhập PIN giới hạn tối đa đúng 6 chữ số (BVA biên 6, 7) | `RULE-STAFF-07` | BVA | Med | Đang ở `/login` | 1. Nhập chuỗi 7 chữ số vào ô PIN | PIN: `1234567` | Ô input chỉ lưu và hiển thị đúng 6 ký tự | Pass | Yes | `StaffLoginTest#verifyPinMaxLength` | Bảo Huy |
| `TC-STAFF-011` | Verify ProtectedRoute chuyển hướng về `/login` khi chưa đăng nhập | `RULE-STAFF-08` | EP | High | Chưa đăng nhập ca trực | 1. Mở thẳng URL `/admin/reports` | – | Tự động chuyển hướng về `/login` | Pass | Yes | `StaffLoginTest#verifyUnauthorizedAccessRedirectsToLogin` | Bảo Huy |
| `TC-STAFF-012` | Verify Waiter không được phép truy cập vào trang Quản trị `/admin` | `RULE-STAFF-08` | EP | High | Đã đăng nhập vai trò Waiter | 1. Đăng nhập Waiter<br>2. Gõ URL `/admin/reports` | `EMP-001` | Bị chặn, chuyển hướng về `/waiter/floor-plan` | Pass | Yes | `StaffLoginTest#verifyRoleAccessRestrictions` | Bảo Huy |

---

## 2. Module M2: Thực Đơn Số & Gọi Món Tại Bàn (Menu & Table Ordering)
*(Copy vào Sheet 3: `M2_TableOrdering` trong Excel)*  
**Người phụ trách:** Phạm Minh Đức (`ducminh14-cmd`) | **URL:** `/dining/menu`, `/dining/cart`

| TC ID | Test Case Title | BR ID | Tech | Pri | Pre-conditions | Test Steps | Test Data | Expected Result | Status | Auto? | Automation Method | Author |
|:---|:---|:---:|:---:|:---:|:---|:---|:---|:---|:---:|:---:|:---|:---:|
| `TC-ORDER-001` | Verify tìm kiếm món ăn chính xác theo tên tiếng Việt có dấu | `RULE-ORDER-01` | EP | High | Đang ở `/dining/menu` | 1. Nhập "Bò bít tết" vào ô tìm kiếm<br>2. Quan sát kết quả | Keyword: `Bò bít tết` | Danh sách hiển thị món "Bò bít tết sốt tiêu" | Pass | Yes | `OrderTest#testSearchDishByName` | Minh Đức |
| `TC-ORDER-002` | Verify tìm kiếm món ăn không phân biệt hoa thường và không dấu | `RULE-ORDER-01` | EP | Med | Đang ở `/dining/menu` | 1. Nhập "bo" vào ô tìm kiếm | Keyword: `bo` | Hiển thị các món chứa từ "bò" | Pass | Yes | `OrderTest#testSearchDishCaseInsensitive` | Minh Đức |
| `TC-ORDER-003` | Verify lọc món ăn theo danh mục Category | `RULE-ORDER-02` | EP | High | Đang ở `/dining/menu` | 1. Bấm chọn tab "Món chính" | Tab: `Món chính` | Toàn bộ món ăn hiển thị đều thuộc danh mục "Món chính" | Pass | Yes | `OrderTest#testFilterCategory` | Minh Đức |
| `TC-ORDER-004` | Verify thêm món ăn từ Menu vào đơn gọi món thành công | `RULE-ORDER-03` | ST | High | Giỏ hàng rỗng | 1. Bấm nút "+" trên thẻ món ăn<br>2. Quan sát badge giỏ hàng | Món: `Bò bít tết` | Badge giỏ hàng hiển thị số `1` | Pass | Yes | `OrderTest#testAddItemToCart` | Minh Đức |
| `TC-ORDER-005` | Verify cộng dồn số lượng khi thêm cùng 1 món ăn 2 lần | `RULE-ORDER-04` | ST | High | Giỏ hàng rỗng | 1. Thêm món A SL = 2<br>2. Thêm tiếp món A SL = 3<br>3. Mở `/dining/cart` | Món: `Bò bít tết`<br>SL: 2 và 3 | Giỏ có 1 dòng món A với tổng số lượng = `5` | Pass | Yes | `OrderTest#testAccumulateQuantity` | Minh Đức |
| `TC-ORDER-006` | Verify số lượng món ăn tối thiểu biên hợp lệ là 1 | `RULE-ORDER-05` | BVA | High | Đang ở `/dining/cart` | 1. Thêm món vào giỏ<br>2. Kiểm tra số lượng mặc định | SL: `1` | Số lượng hiển thị chính xác là `1` | Pass | Yes | `OrderTest#testQuantityBoundaryMin` | Minh Đức |
| `TC-ORDER-007` | Verify cập nhật số lượng món đạt biên tối đa 99 phần | `RULE-ORDER-05` | BVA | High | Đang ở `/dining/cart` | 1. Tăng số lượng món lên 99 | SL: `99` | Số lượng hiển thị chính xác là `99` | Pass | Yes | `OrderTest#testQuantityBoundaryMax` | Minh Đức |
| `TC-ORDER-008` | Verify chặn hoặc cảnh báo khi tăng số lượng món vượt quá 99 | `RULE-ORDER-05` | BVA | High | Đang ở SL = 99 | 1. Bấm nút "+" tăng tiếp | SL: `100` | Số lượng không tăng hoặc báo *"Số lượng tối đa là 99"* | Pass | Yes | `OrderTest#testQuantityExceedMax` | Minh Đức |
| `TC-ORDER-009` | Verify xóa món khỏi giỏ khi giảm số lượng về 0 hoặc bấm nút Xóa | `RULE-ORDER-06` | ST | High | Giỏ có 1 món | 1. Bấm nút "-" khi SL = 1 (hoặc nút thùng rác) | SL: `0` | Dòng món ăn biến mất, giỏ hiển thị rỗng | Pass | Yes | `OrderTest#testRemoveItemFromCart` | Minh Đức |
| `TC-ORDER-010` | Verify tính đúng thành tiền từng dòng và tổng tiền tạm tính | `RULE-ORDER-07` | EP | High | Giỏ có 2 món | 1. Thêm món A (100k, SL=2)<br>2. Thêm món B (50k, SL=1) | A: 200k<br>B: 50k | Thành tiền A = 200k, B = 50k; Tổng tiền = 250.000đ | Pass | Yes | `OrderTest#testCartCalculation` | Minh Đức |
| `TC-ORDER-011` | Verify lưu và hiển thị đúng ghi chú chế biến món ăn | `RULE-ORDER-08` | EP | Med | Đang ở `/dining/cart` | 1. Nhập ghi chú "Không hành, ít cay"<br>2. Lưu lại | Note: `Không hành, ít cay` | Ghi chú hiển thị kèm đúng tên món trong giỏ | Pass | Yes | `OrderTest#testItemSpecialNotes` | Minh Đức |
| `TC-ORDER-012` | Verify món ăn trạng thái Hết món (Out of stock) không thể thêm vào giỏ | `RULE-ORDER-09` | EP | High | Menu có món hết hàng | 1. Tìm món có tag "Hết món"<br>2. Kiểm tra nút thêm | Món hết hàng | Nút "+" bị disable, không thể thêm vào giỏ | Pass | Yes | `OrderTest#testOutOfStockDish` | Minh Đức |
| `TC-ORDER-013` | Verify gửi đơn gọi món thành công và chuyển sang màn hình tracking | `RULE-ORDER-10` | ST | High | Giỏ có món | 1. Bấm nút "Gửi đơn gọi món"<br>2. Chờ phản hồi | – | Chuyển hướng sang `/dining/orders/tracking`, trạng thái `CONFIRMED` | Pass | Yes | `OrderTest#testSubmitBatchOrder` | Minh Đức |
| `TC-ORDER-014` | Verify giỏ hàng được làm rỗng sau khi gửi đơn thành công | `RULE-ORDER-10` | ST | High | Vừa gửi đơn xong | 1. Quay lại trang `/dining/cart` | – | Giỏ hàng hiển thị rỗng (0 món) | Pass | Yes | `OrderTest#testCartEmptyAfterSubmit` | Minh Đức |

---

## 3. Module M3: Đặt Bàn Trực Tuyến (Public Table Reservation)
*(Copy vào Sheet 4: `M3_Reservation` trong Excel)*  
**Người phụ trách:** Dương Trong (`duogtrog06tk`) | **URL:** `/reservation`

| TC ID | Test Case Title | BR ID | Tech | Pri | Pre-conditions | Test Steps | Test Data | Expected Result | Status | Auto? | Automation Method | Author |
|:---|:---|:---:|:---:|:---:|:---|:---|:---|:---|:---:|:---:|:---|:---:|
| `TC-RESV-001` | Verify đặt bàn thành công với số lượng khách biên tối thiểu (1 người) | `RULE-RESV-01` | BVA | High | Đang ở `/reservation` | 1. Nhập họ tên, SĐT hợp lệ<br>2. Chọn số khách = 1<br>3. Chọn ngày mai 19:00<br>4. Bấm "Đặt bàn" | Name: `Nguyễn An`<br>Phone: `0912345678`<br>Party: `1` | Đặt bàn thành công, hiện Modal xác nhận mã đặt bàn | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-002` | Verify đặt bàn thành công với số lượng khách biên tối đa (20 người) | `RULE-RESV-01` | BVA | High | Đang ở `/reservation` | Các bước tương tự | Party: `20` | Đặt bàn thành công | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-003` | Verify đặt bàn thành công với số lượng khách thông thường (4 người) | `RULE-RESV-01` | EP | High | Đang ở `/reservation` | Các bước tương tự | Party: `4` | Đặt bàn thành công | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-004` | Verify thông báo lỗi khi nhập số lượng khách vi phạm biên dưới (0 người) | `RULE-RESV-02` | BVA | High | Đang ở `/reservation` | 1. Nhập Party = 0<br>2. Bấm "Đặt bàn" | Party: `0` | Báo lỗi *"Số lượng khách phải từ 1 đến 20 người"* | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-005` | Verify thông báo lỗi khi nhập số lượng khách vi phạm biên trên (21 người) | `RULE-RESV-02` | BVA | High | Đang ở `/reservation` | 1. Nhập Party = 21<br>2. Bấm "Đặt bàn" | Party: `21` | Báo lỗi *"Số lượng khách phải từ 1 đến 20 người"* | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-006` | Verify thông báo lỗi khi nhập số lượng khách là số âm (-1) | `RULE-RESV-02` | EP | High | Đang ở `/reservation` | 1. Nhập Party = -1<br>2. Bấm "Đặt bàn" | Party: `-1` | Báo lỗi số lượng khách không hợp lệ | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-007` | Verify đặt bàn thành công với SĐT Việt Nam hợp lệ (10 số, đầu 09) | `RULE-RESV-03` | EP | High | Đang ở `/reservation` | 1. Nhập SĐT 10 số hợp lệ | Phone: `0987654321` | Form chấp nhận SĐT hợp lệ | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-008` | Verify báo lỗi khi số điện thoại thiếu số (9 chữ số) | `RULE-RESV-04` | BVA | High | Đang ở `/reservation` | 1. Nhập SĐT 9 số<br>2. Bấm Submit | Phone: `091234567` | Báo lỗi *"Số điện thoại phải gồm 10 chữ số"* | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-009` | Verify báo lỗi khi số điện thoại thừa số (11 chữ số) | `RULE-RESV-04` | BVA | High | Đang ở `/reservation` | 1. Nhập SĐT 11 số<br>2. Bấm Submit | Phone: `09123456789` | Báo lỗi *"Số điện thoại phải gồm 10 chữ số"* | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-010` | Verify báo lỗi khi số điện thoại chứa ký tự chữ cái | `RULE-RESV-04` | EP | High | Đang ở `/reservation` | 1. Nhập SĐT chứa chữ | Phone: `09123abcde` | Báo lỗi định dạng số điện thoại | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-011` | Verify form chặn gửi khi bỏ trống trường Họ và tên khách hàng | `RULE-RESV-05` | EP | High | Đang ở `/reservation` | 1. Để trống Họ tên<br>2. Bấm Submit | Name: ` ` (rỗng) | Chặn gửi, báo *"Vui lòng nhập họ và tên"* | Pass | Yes | `ReservationTest#testReservationDataDriven` | Dương Trong |
| `TC-RESV-012` | Verify hệ thống an toàn khi nhập chuỗi XSS Script vào ô Họ tên | `RULE-RESV-06` | EG | High | Đang ở `/reservation` | 1. Nhập script XSS vào ô tên<br>2. Bấm Submit | Name: `<script>alert(1)</script>` | Không kích hoạt alert, xử lý chuỗi an toàn | Pass | Yes | `ReservationTest#testSecurityXssInput` | Dương Trong |
| `TC-RESV-013` | Verify chặn đặt bàn khi chọn ngày giờ trong quá khứ | `RULE-RESV-07` | EP | High | Đang ở `/reservation` | 1. Chọn ngày hôm qua<br>2. Bấm Submit | Date: Ngày hôm qua | Báo lỗi thời gian đặt bàn phải ở tương lai | Pass | Yes | `ReservationTest#testPastDateReservation` | Dương Trong |
| `TC-RESV-014` | Verify hiển thị Modal thông báo thành công kèm Mã Đặt Bàn | `RULE-RESV-08` | ST | High | Điền form hợp lệ | 1. Bấm Submit form hợp lệ | Hợp lệ | Xuất hiện Popup xác nhận, có mã dạng `RSV-XXXX` | Pass | Yes | `ReservationTest#testReservationConfirmationModal` | Dương Trong |

---

# PHẦN 3: BM06 – TEST EXECUTION LOG TEMPLATE
*(Copy vào Sheet 5: `Execution_Log` trong Excel)*

| Run ID | Ngày giờ chạy | Build / Commit SUT | Commit Test Suite | Người thực hiện | Môi trường | Tổng số TC | Pass | Fail | Blocked | Tỷ lệ Pass (%) | Thời gian chạy | Ghi chú |
|:---:|:---|:---|:---|:---:|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---|
| **RUN-01** | 04:05 02/11/2026 | roms-restaurant.duckdns.org (v1.0-RC) | Commit `3cf84a6` | Dương Trọng | Chrome 130 Headless, Win 11 | 42 | 42 | 0 | 0 | 100.0% | 4m 56s | Chạy hồi quy lần 1: Toàn bộ 42 test cases (M1: 12, M2: 14, M3: 16) chạy thành công |
| **RUN-02** | 04:16 02/11/2026 | roms-restaurant.duckdns.org (v1.0-RC) | Commit `3cf84a6` | Dương Trọng | Chrome 130 Headless, Win 11 | 42 | 42 | 0 | 0 | **100.0%** | 4m 55s | Chạy hồi quy chính thức xác nhận báo cáo Surefire: 42/42 Pass (100%), 0 Fail, 0 Flaky |
