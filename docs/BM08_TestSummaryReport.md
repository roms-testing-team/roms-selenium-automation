# BM08 – TEST SUMMARY REPORT (BÁO CÁO TỔNG KẾT KIỂM THỬ)
## Dự Án: ROMS (Restaurant Ordering Management System)

- **Report ID:** `TSR-ROMS-G<SỐ>-v1.0`
- **Ngày lập:** [Ngày nộp bài]
- **Người lập:** Test Lead (SV1)

---

## 1. Tóm tắt điều hành (Executive Summary)
Dự án kiểm thử tự động hệ thống ROMS đã hoàn thành tự động hóa 4 module chính: Đăng nhập nhân viên (M1), Đặt bàn trực tuyến (M2), Thực đơn số & Tìm kiếm (M3), Giỏ hàng & Gọi món (M4). Toàn bộ mã kiểm thử được xây dựng theo mô hình Page Object Model (POM) kết hợp Data-driven Testing từ file CSV. Kết quả thực thi cho thấy hệ thống hoạt động ổn định ở các luồng chính, các lỗi phát hiện đã được báo cáo và phân loại chi tiết.

## 2. Số liệu thực thi kiểm thử (Test Execution Metrics)

| Module | Tổng TC | Automated | Executed | Pass | Fail | Blocked | Tỷ lệ Pass (%) |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **M1: Staff Login & RBAC** | 10 | 8 | 10 | 9 | 1 | 0 | 90.0% |
| **M2: Public Reservation** | 15 | 12 | 15 | 13 | 2 | 0 | 86.7% |
| **M3: Menu & Search** | 10 | 8 | 10 | 10 | 0 | 0 | 100.0% |
| **M4: Cart & Ordering** | 12 | 10 | 12 | 10 | 2 | 0 | 83.3% |
| **Tổng cộng** | **47** | **38** | **47** | **42** | **5** | **0** | **89.4%** |

### Các chỉ số đo lường (Quality Metrics):
- **Tỷ lệ tự động hóa (Automation Rate):** $38 / 47 = 80.85\%$ (Đạt yêu cầu $\ge 80\%$).
- **Tỷ lệ thực thi (Execution Rate):** $47 / 47 = 100\%$.
- **Độ bao phủ quy tắc nghiệp vụ (BR Coverage):** $100\%$ các Business Rule đều có ít nhất 1 Test Case kiểm chứng.
- **Thời gian chạy hồi quy toàn bộ:** ~ 5 phút 40 giây (Tiết kiệm > 85% thời gian so với kiểm thử thủ công).

## 3. Tình trạng Defect phát hiện
- **Tổng số Bug ghi nhận:** 5
  - Critical: 0
  - Major: 2 (Lỗi cộng dồn giỏ hàng, Lỗi validate ngày đặt bàn)
  - Minor: 3 (Lỗi thông báo tiếng Việt, Trùng ID trên UI)

## 4. Đánh giá theo Exit Criteria
- 100% Test Case mức High đã được chạy: **ĐẠT**
- Tỷ lệ Pass $\ge 85\%$: **ĐẠT**
- Không có lỗi Critical mở chưa báo cáo: **ĐẠT**

## 5. Khuyến nghị & Kết luận
- **Khuyến nghị Go / No-Go:** **CONDITIONAL GO** (Cho phép triển khai thử nghiệm nội bộ sau khi khắc phục 2 lỗi Major trong BM07).
