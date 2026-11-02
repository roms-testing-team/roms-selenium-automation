# BM08 – TEST SUMMARY REPORT (BÁO CÁO TỔNG KẾT KIỂM THỬ)
## Dự Án: ROMS (3 Chức Năng: Đăng Nhập, Đặt Bàn, Gọi Món)

- **Report ID:** `TSR-ROMS-G01-v1.0`
- **Ngày lập:** 02/11/2026
- **Người lập:** Ngô Viết Bảo Huy (Test Lead) & Dương Trong (Defect & Doc Manager)
- **Môi trường kiểm thử:** `http://roms-restaurant.duckdns.org/` (Nginx + React Frontend + Spring Boot Backend)
- **Chuẩn báo cáo:** ISO/IEC/IEEE 29119-3 & ISTQB CTFL v4.0

---

## 1. Tóm tắt điều hành (Executive Summary)
Dự án kiểm thử tự động hệ thống ROMS đã hoàn thành tự động hóa 100% cho 3 module chức năng cốt lõi:
1. **M1: Đăng nhập nhân viên & Phân quyền (Staff Login & RBAC)**
2. **M2: Thực đơn & Gọi món tại bàn (Table Ordering)**
3. **M3: Đặt bàn trực tuyến (Public Table Reservation)**

Toàn bộ mã kiểm thử được kiến trúc theo mô hình **Page Object Model (POM)** chuẩn mực, kết hợp **Data-driven Testing** từ các file dữ liệu CSV độc lập. Kiểm thử hồi quy được thực thi tự động qua Maven Surefire trên môi trường thật (Live SUT). Toàn bộ 42 test cases đã thực thi thành công, hệ thống đáp ứng đầy đủ các tiêu chí xuất xưởng (Exit Criteria).

---

## 2. Số liệu thực thi kiểm thử (Test Execution Metrics)

### 2.1. Bảng số liệu chi tiết theo Module (Surefire Execution Summary)

| Module / Test Class | Tổng TC | Automated | Executed | Pass | Fail | Error | Skipped | Pass Rate (%) | Thời gian chạy |
|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **M1: StaffLoginTest** | 12 | 12 | 12 | 12 | 0 | 0 | 0 | **100%** | 46.56s |
| **M2: OrderTest** | 14 | 14 | 14 | 14 | 0 | 0 | 0 | **100%** | 71.66s |
| **M3: ReservationTest** | 16 | 16 | 16 | 16 | 0 | 0 | 0 | **100%** | 177.20s |
| **Tổng cộng** | **42** | **42** | **42** | **42** | **0** | **0** | **0** | **100%** | **4m 55s (295.4s)** |

### 2.2. Các chỉ số đo lường chất lượng (Quality Metrics):
- **Tỷ lệ tự động hóa (Automation Rate):** $42 / 42 = 100\%$ (Vượt chỉ tiêu tối thiểu $\ge 80\%$).
- **Tỷ lệ thực thi (Execution Rate):** $42 / 42 = 100\%$.
- **Tỷ lệ đạt (Pass Rate):** $42 / 42 = 100\%$ (Vượt chỉ tiêu tối thiểu $\ge 85\%$).
- **Độ bao phủ quy tắc nghiệp vụ (BR Coverage):** $100\%$ các Business Rule trong `BM02_BusinessRules.md` đều có ít nhất 1 test case tự động kiểm chứng.
- **Thời gian chạy hồi quy:** ~ 4 phút 55 giây trên Chrome Headless (nhanh gấp 15 lần so với kiểm thử thủ công).

---

## 3. Tình trạng Defect phát hiện (Defect Summary)
Các lỗi phát hiện được quản lý đồng bộ trên GitHub Issues theo chuẩn **ISTQB CTFL Section 5.5**:
- **Tổng số Bug ghi nhận:** 3
  - **Critical:** 0
  - **Major:** 2
  - **Minor (Testability):** 1

| Defect ID | GitHub Issue | Module | Tiêu đề lỗi | Mức độ | Trạng thái |
|:---|:---:|:---|:---|:---:|:---:|
| `BUG-ROMS-01` | [#18](https://github.com/roms-testing-team/roms-selenium-automation/issues/18) | M1 Đăng nhập | Trùng thuộc tính `id="outlined-required"` trên cả trường Tài khoản và Mật khẩu ở form Đăng nhập | Minor (Testability) | Open |
| `BUG-ROMS-02` | [#19](https://github.com/roms-testing-team/roms-selenium-automation/issues/19) | M2 Gọi món | Thêm cùng một món ăn nhiều lần tạo ra các dòng riêng biệt trong giỏ hàng thay vì cộng dồn số lượng | Major | Open |
| `BUG-ROMS-03` | [#20](https://github.com/roms-testing-team/roms-selenium-automation/issues/20) | M3 Đặt bàn | Form Đặt bàn cho phép nhập và gửi thông tin với số lượng khách vượt quá quy định (> 20 khách) | Major | Open |

---

## 4. Đánh giá theo Exit Criteria (Tiêu chí dừng kiểm thử)

| Tiêu chí xuất xưởng (Exit Criteria) | Mục tiêu đề ra | Kết quả thực tế | Đánh giá |
|:---|:---:|:---:|:---:|
| 100% Test Case mức độ High đã được chạy | 100% | 100% (42/42 TCs) | **ĐẠT** |
| Tỷ lệ Pass Rate của bộ test hồi quy | $\ge 85\%$ | **100% (42/42)** | **ĐẠT** |
| Không còn lỗi mức Critical tồn đọng | 0 Critical | 0 Critical | **ĐẠT** |
| Toàn bộ Defect được log và phân loại ISTQB | 100% | 3 Bugs trên GitHub | **ĐẠT** |
| Báo cáo HTML Surefire được xuất đầy đủ | Có | `docs/reports/surefire-report.html` | **ĐẠT** |

---

## 5. Khuyến nghị & Kết luận (Release Decision)
- **Quyết định Go / No-Go:** **CONDITIONAL GO**
- **Đánh giá:** Bộ kiểm thử tự động chứng minh 3 luồng nghiệp vụ cốt lõi hoạt động trơn tru trên môi trường live SUT. Hệ thống đủ điều kiện bàn giao triển khai thử nghiệm nội bộ (UAT / Pilot testing) sau khi đội ngũ phát triển xử lý 2 lỗi Major (BUG-ROMS-02 và BUG-ROMS-03).

---

## 6. Minh Chứng & Tài Liệu Bàn Giao (Artifacts & Deliverables)
- 🌐 **Môi trường kiểm thử SUT:** [http://roms-restaurant.duckdns.org/](http://roms-restaurant.duckdns.org/)
- 📁 **Báo cáo HTML Surefire:** [`docs/reports/surefire-report.html`](reports/surefire-report.html)
- 📊 **Bảng Test Cases & Execution Log (Excel):** [`docs/BM03_TestCases.xlsx`](BM03_TestCases.xlsx)
- 🐛 **Báo cáo sự cố Defect Report:** [`docs/BM07_DefectReport.md`](BM07_DefectReport.md)
- 🎥 **Video Demo Kiểm Thử Tự Động (Google Drive):** [https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link](https://drive.google.com/drive/folders/1d1dLtKZHvZ6GJhNTwbG9NJWDupeo4Y1v?usp=drive_link)
