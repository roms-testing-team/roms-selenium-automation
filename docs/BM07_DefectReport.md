# BM07 – DEFECT REPORT (DANH SÁCH BÁO CÁO SỰ CỐ / LỖI)
## Dự án: ROMS (3 Chức Năng: Đăng Nhập, Đặt Bàn, Gọi Món)

Tài liệu này ghi nhận các Defect phát hiện trên hệ thống ROMS trong quá trình chạy kiểm thử tự động (Automation Regression Testing) cho 3 module chính. Mỗi Defect được quản lý đồng bộ với 1 Issue trên GitHub.

---

### Danh Sách Defect Tổng Hợp

| Defect ID | GitHub Issue | Module | Tiêu đề lỗi | Mức độ (Severity) | Trạng thái (Status) | Người báo |
|:---|:---:|:---|:---|:---:|:---:|:---|
| `BUG-ROMS-01` | #1 | M1 Đăng nhập | Trùng ID hoặc thiếu thuộc tính `data-testid` trên form đăng nhập nhân viên | Minor (Testability) | Open | SV1 |
| `BUG-ROMS-02` | #2 | M2 Đặt bàn | Báo lỗi tiếng Việt không dấu hoặc sai định dạng khi chọn số lượng khách âm | Minor | Open | SV2 |
| `BUG-ROMS-03` | #3 | M3 Gọi món | Thêm cùng một món lần 2 tạo dòng mới thay vì cộng dồn số lượng | Major | New | SV3 |

---

### Mẫu Chi Tiết Một Defect Report (Theo chuẩn ISTQB CTFL 5.5)

```markdown
**Defect ID:** BUG-ROMS-03
**GitHub Issue:** #3
**Tiêu đề:** [Gọi món] Thêm cùng 1 món ăn 2 lần tạo thành 2 dòng riêng biệt thay vì cộng dồn số lượng
**Người báo / Ngày:** Nguyễn Văn A / 15-11-2026
**Module / BR / TC:** M3 Gọi món / RULE-ORDER-04 / TC-ORDER-004
**Môi trường:** Google Chrome mới nhất, Windows 11, Frontend commit: a1b2c3, Backend commit: x9y8z7
**Severity:** Major
**Priority:** High
**Status:** New (New -> Open -> Fixed -> Retest -> Closed)
**Điều kiện tiên quyết:** Bàn 01 đang mở session, giỏ hàng ban đầu rỗng.

**Các bước tái hiện:**
1. Truy cập vào trang Menu: http://localhost:5173/dining/menu
2. Tìm món "Bò bít tết sốt tiêu", chọn số lượng = 2, bấm "Thêm vào giỏ".
3. Tiếp tục chọn lại món "Bò bít tết sốt tiêu", chọn số lượng = 3, bấm "Thêm vào giỏ".
4. Mở trang Giỏ hàng: http://localhost:5173/dining/cart

**Kết quả mong đợi (Expected):**
- Giỏ hàng chỉ có 1 dòng duy nhất cho món "Bò bít tết sốt tiêu" với tổng số lượng hiển thị là 5.

**Kết quả thực tế (Actual):**
- Giỏ hàng xuất hiện 2 dòng riêng biệt: 1 dòng số lượng 2 và 1 dòng số lượng 3.

**Bằng chứng kiểm thử (Evidence):**
- Ảnh chụp màn hình: target/screenshots/TC-ORDER-004.png
- Console log phản hồi từ API.

**Test Script tự động:**
- `com.swt301.roms.tests.OrderTest#shouldAccumulateQuantityWhenAddingSameItemTwice`
```
