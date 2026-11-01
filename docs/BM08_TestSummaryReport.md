# BM08 – TEST SUMMARY REPORT (BÁO CÁO TỔNG KẾT KIỂM THỬ)
## Dự Án: ROMS (3 Chức Năng: Đăng Nhập, Đặt Bàn, Gọi Món)

- **Report ID:** `TSR-ROMS-G01-v1.0`
- **Ngày lập:** [Ngày nộp bài]
- **Người lập:** Test Lead (SV1)

---

## 1. Tóm tắt điều hành (Executive Summary)
Dự án kiểm thử tự động hệ thống ROMS đã hoàn thành tự động hóa 3 module chức năng cốt lõi: **Đăng nhập nhân viên & Phân quyền (M1)**, **Đặt bàn trực tuyến (M2)**, và **Thực đơn số & Gọi món tại bàn (M3)**. Toàn bộ mã kiểm thử được xây dựng theo mô hình Page Object Model (POM) kết hợp Data-driven Testing từ file CSV. Kết quả thực thi cho thấy hệ thống hoạt động ổn định ở các luồng chính, các lỗi phát hiện đã được báo cáo và phân loại chi tiết theo chuẩn ISTQB.

## 2. Số liệu thực thi kiểm thử (Test Execution Metrics)

| Module | Tổng TC | Automated | Executed | Pass | Fail | Blocked | Tỷ lệ Pass (%) |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **M1: Đăng nhập nhân viên (Login & RBAC)** | 12 | 10 | 12 | 11 | 1 | 0 | 91.7% |
| **M2: Đặt bàn trực tuyến (Reservation)** | 14 | 12 | 14 | 12 | 2 | 0 | 85.7% |
| **M3: Thực đơn & Gọi món tại bàn (Ordering)** | 14 | 12 | 14 | 12 | 2 | 0 | 85.7% |
| **Tổng cộng** | **40** | **34** | **40** | **35** | **5** | **0** | **87.5%** |

### Các chỉ số đo lường (Quality Metrics):
- **Tỷ lệ tự động hóa (Automation Rate):** $34 / 40 = 85.0\%$ (Vượt chỉ tiêu tối thiểu $\ge 80\%$).
- **Tỷ lệ thực thi (Execution Rate):** $40 / 40 = 100\%$.
- **Độ bao phủ quy tắc nghiệp vụ (BR Coverage):** $100\%$ các Business Rule trong BM02 đều có ít nhất 1 Test Case kiểm chứng.
- **Thời gian chạy hồi quy toàn bộ:** ~ 4 phút 30 giây (Nhanh hơn 90% so với kiểm thử thủ công).

## 3. Tình trạng Defect phát hiện
- **Tổng số Bug ghi nhận:** 5
  - Critical: 0
  - Major: 2 (Lỗi cộng dồn số lượng giỏ hàng, Lỗi validate ngày giờ đặt bàn)
  - Minor: 3 (Lỗi thông báo tiếng Việt, Trùng ID trên UI)

## 4. Đánh giá theo Exit Criteria
- 100% Test Case mức High đã được chạy: **ĐẠT**
- Tỷ lệ Pass $\ge 85\%$: **ĐẠT**
- Không có lỗi Critical mở chưa báo cáo: **ĐẠT**

## 5. Khuyến nghị & Kết luận
- **Khuyến nghị Go / No-Go:** **CONDITIONAL GO** (Cho phép triển khai thử nghiệm nội bộ sau khi khắc phục 2 lỗi Major trong BM07).
