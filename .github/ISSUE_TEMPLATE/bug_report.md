---
name: Bug report
about: Báo cáo lỗi phát hiện trên hệ thống ROMS trong quá trình kiểm thử
title: "[BUG][Module] Tóm tắt ngắn gọn lỗi"
labels: ["bug"]
assignees: ''
---

**Mã Defect (Defect ID):** BUG-XX  
**Module liên quan:** M1 (Login) / M2 (Reservation) / M3 (Menu) / M4 (Cart)  
**Quy tắc nghiệp vụ (BR ID):** RULE-ROMS-XXX-XX  
**Test Case ID:** TC-ROMS-XXX-XXX  

---

### 1. Mức độ nghiêm trọng & Mức độ ưu tiên
- **Severity:** [Critical / Major / Minor / Trivial]
- **Priority:** [High / Medium / Low]

### 2. Môi trường kiểm thử (Environment)
- **URL:** http://localhost:5173/...
- **Trình duyệt:** Google Chrome
- **OS:** Windows

### 3. Điều kiện tiên quyết (Pre-conditions)
- [Ví dụ: Đã có bàn hoạt động, tài khoản đã được cấp...]

### 4. Các bước tái hiện (Steps to Reproduce)
1. Truy cập vào URL: ...
2. Nhập dữ liệu: ...
3. Bấm nút: ...

### 5. Kết quả mong đợi (Expected Result)
- [Hệ thống phải phản hồi hoặc hiển thị theo đúng Business Rule]

### 6. Kết quả thực tế (Actual Result)
- [Hệ thống phản hồi sai, thông báo sai hoặc crash...]

### 7. Bằng chứng kiểm thử (Evidence)
- [Đính kèm ảnh chụp màn hình từ target/screenshots/]

### 8. Test script tự động
- Class: `com.swt301.roms.tests.XxxTest#methodName`
