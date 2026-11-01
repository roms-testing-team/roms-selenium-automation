## 📌 Mô tả thay đổi (Description)
- **Module:** M[1/2/3/4/5]
- **Issue liên quan:** #[Nhập số Issue]
- **Tóm tắt nội dung:** [Mô tả ngắn gọn code thêm mới hoặc fix bug script]

## 🎯 Test Cases kiểm thử liên quan
- `TC-ROMS-XXX-001` ... `TC-ROMS-XXX-00n`

## ✅ Checklist của Tác giả (Author)
- [ ] Đã chạy lệnh `mvn clean test -Dtest=<TênClass>` pass ở máy local.
- [ ] Không sử dụng `Thread.sleep()`.
- [ ] Không sử dụng XPath tuyệt đối.
- [ ] Không có `Assertions` trong Page class.
- [ ] Không có `By` hoặc `driver.findElement` trong Test class.
- [ ] Dữ liệu test được lưu trong file CSV, không hard-code trong code test.
- [ ] Đã cập nhật phương thức kiểm thử tự động vào BM03.

## 📊 Kết quả kiểm thử thực tế
- Tests run: [Số TC], Failures: [Số TC fail], Errors: [Số TC lỗi]
- Đính kèm ảnh screenshot / log surefire (nếu có):
