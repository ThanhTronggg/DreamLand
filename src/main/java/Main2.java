import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import net.datafaker.Faker;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main2 {
    private static final Faker faker = new Faker();
    private static final Random rand = new Random();

    // Dữ liệu mẫu
    private static final String[] foods = {"Bắp rang bơ cỡ nhỏ", "Bắp rang bơ cỡ lớn", "X2 Bắp", "Combo x3 bắp"};
    private static final String[] drinks = {"Cocacola", "7up", "Pepsi", "Sting"};
    private static final String[] ngonNgu = {
            "Tiếng Anh", "Tiếng Pháp", "Tiếng Tây Ban Nha", "Tiếng Trung",
            "Tiếng Nhật", "Tiếng Đức", "Tiếng Hàn", "Tiếng Việt", "Tiếng Nga"
    };
    private static final String[] tenLoaiGhe = {"Ghế thường", "Ghế VIP", "Ghế đôi Sweetbox"};
    private static final String[] events = {
            "Tết Nguyên Đán", "Giáng Sinh", "Black Friday", "Valentine",
            "Lễ Quốc Khánh", "Halloween", "Lễ Phục Sinh"
    };

    // Dữ liệu TaiKhoan từ SQL
    private static final String[][] taiKhoanData = {
            {"admin", "$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO"},
            {"baouyen", "$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO"},
            {"trong", "$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO"},
            {"nguyenvanb", "$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO"},
            {"thudao", "$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO"},
            {"minhhoang", "$2a$10$g.hCsU9Xj02KDyOB8.YJ/Ov3yNrcPjePQGcAsTPlWsA9xFiq8gG1e"},
            {"hanhpham", "$2a$10$rA9HzfT8f7m5lXMFmhnG3.WU9Fo/sG.Raz1uAcIjvUN7RbX9A3CSW"},
            {"hongnhung", "$2a$10$QXLQHGpLO8OlEFD4H2vnBe1uP/zWzISdjxYPFT8T3BvUmQbfv5mF2"},
            {"quangtran", "$2a$10$6J9OEXpO.ZSRs59RLO/yhuiBf0PV/d8ZsSiMIh/8Ml.e1WEVJ4wEm"},
            {"namnguyen", "$2a$10$wDEv8TbKv8JHmfXNPjZyjeXuW7esTYY7Q53ihLg9yRY.ZDtcuvXOG"}
    };

    // Hàm tạo sequence trong database nếu chưa tồn tại
    private static void createSequenceIfNotExists(EntityManager em, String sequenceName) {
        try {
            em.createNativeQuery("CREATE SEQUENCE " + sequenceName + " INCREMENT BY 1 START WITH 1").executeUpdate();
            System.out.println("Tạo sequence: " + sequenceName);
        } catch (Exception e) {
            System.out.println("Sequence " + sequenceName + " đã tồn tại hoặc lỗi: " + e.getMessage());
        }
    }

    // Hàm lấy giá trị sequence và định dạng mã
    private static String getNextSequenceValue(EntityManager em, String sequenceName, String prefix, int padLength) {
        try {
            Long nextValue = (Long) em.createNativeQuery("SELECT NEXT VALUE FOR " + sequenceName).getSingleResult();
            return prefix + String.format("%0" + padLength + "d", nextValue);
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy sequence " + sequenceName + ": " + e.getMessage());
            throw e;
        }
    }

    // Hàm lấy tất cả LoaiGhe từ cơ sở dữ liệu
    private static List<LoaiGhe> getAllLoaiGhe(EntityManager em) {
        return em.createQuery("SELECT lg FROM LoaiGhe lg", LoaiGhe.class).getResultList();
    }

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            // Tạo các sequence cần thiết
            tr.begin();
            createSequenceIfNotExists(em, "LoaiGheSequence");
            createSequenceIfNotExists(em, "PhongSequence");
            createSequenceIfNotExists(em, "GheSequence");
            createSequenceIfNotExists(em, "TaiKhoanSequence");
            createSequenceIfNotExists(em, "NhanVienSequence");
            createSequenceIfNotExists(em, "KhachHangSequence");
            createSequenceIfNotExists(em, "SanPhamSequence");
            createSequenceIfNotExists(em, "PhimSequence");
            createSequenceIfNotExists(em, "LichChieuSequence");
            createSequenceIfNotExists(em, "KhuyenMaiSequence");
            createSequenceIfNotExists(em, "HoaDonSequence");
            createSequenceIfNotExists(em, "VeSequence");
            em.flush();
            tr.commit();

            String[] anhList = {
                    "images/venom_keo_cuoi.jpg",
                    "images/co_dau_hao_mon.jpg",
                    "images/elli_va_tau_ma.jpg",
                    "images/ngay_xua_co_mot_chuyen_tinh.jpg",
                    "images/mufasa_vua_su_tu.jpg",
                    "images/thien_duong_qua_bao.jpg",
                    "images/godzilla_minus_one.jpg",
                    "images/doi_ban_hoc_yeu.jpg",
                    "images/tee_yod_quy_an_tang_phan_2.jpg",
                    "images/tro_choi_nhan_tinh.jpg"
            };

            List<String> anhDoAn = Arrays.asList(
                    "images/bap_rang_bo.jpg",
                    "images/combo_bap_nuoc.png",
                    "images/baprangbo.jpg",
                    "images/bapranglon.jpg",
                    "images/bapthuong.jpg"
            );

            // Danh sách ảnh cho Nước uống
            List<String> anhNuocUong = Arrays.asList(
                    "images/coca_cola.png",
                    "images/aquafina.jpg",
                    "images/pepsi.png",
                    "images/nuocsuoi.jpg",
                    "images/fanta.jpg"
            );

            String[] foods = {"Bắp rang bơ", "Combo Bắp nước", "Bắp Thường"};
            String[] drinks = {"Nước ngọt Coca-Cola", "Nước suối Aquafina", "Pepsi"};

            // 1. Tạo hoặc sử dụng LoaiGhe hiện có
            System.out.println("Tạo hoặc sử dụng LoaiGhe...");
            tr.begin();
            List<LoaiGhe> loaiGhes = new ArrayList<>();
            List<LoaiGhe> existingLoaiGhes = getAllLoaiGhe(em);
            Set<String> existingTenLoaiGhe = new HashSet<>();
            for (LoaiGhe lg : existingLoaiGhes) {
                existingTenLoaiGhe.add(lg.getTenLoaiGhe());
                loaiGhes.add(lg);
                System.out.println("Sử dụng LoaiGhe hiện có: " + lg.getTenLoaiGhe() + " (maLoaiGhe: " + lg.getMaLoaiGhe() + ")");
            }

            // Chỉ tạo mới các LoaiGhe còn thiếu
            for (String ten : tenLoaiGhe) {
                if (!existingTenLoaiGhe.contains(ten)) {
                    LoaiGhe loaiGhe = new LoaiGhe();
                    loaiGhe.setMaLoaiGhe(getNextSequenceValue(em, "LoaiGheSequence", "LG", 3));
                    loaiGhe.setTenLoaiGhe(ten);
                    loaiGhe.setMoTaLoaiGhe(faker.lorem().sentence(20));
                    em.persist(loaiGhe);
                    loaiGhes.add(loaiGhe);
                    System.out.println("Tạo mới LoaiGhe: " + ten + " (maLoaiGhe: " + loaiGhe.getMaLoaiGhe() + ")");
                }
            }
            em.flush();
            tr.commit();
            System.out.println("Đã xử lý " + loaiGhes.size() + " LoaiGhe");

            // 2. Tạo Phong và Ghe
            System.out.println("Tạo Phong và Ghe...");
            List<Phong> phongs = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                tr.begin();
                Phong phong = new Phong();
                phong.setMaPhong(getNextSequenceValue(em, "PhongSequence", "PH", 3));
                phong.setTenPhong("Phòng " + (i + 1));
                phong.setSoLuongGhe(192);
                em.persist(phong);
                phongs.add(phong);
                em.flush();
                tr.commit();

                System.out.println("Tạo Ghe cho " + phong.getTenPhong() + "...");
                tr.begin();
                List<Ghe> ghes = new ArrayList<>();
                for (char row = 'A'; row <= 'M'; row++) {
                    for (int col = 1; col <= 16; col++) {
                        Ghe ghe = new Ghe();
                        String viTri = String.format("%c%02d", row, col);
                        LoaiGhe loaiGhe;

                        if (row >= 'A' && row <= 'D') {
                            loaiGhe = loaiGhes.stream().filter(lg -> lg.getTenLoaiGhe().equals("Ghế thường")).findFirst().orElse(loaiGhes.get(0));
                        } else if (row >= 'E' && row <= 'L') {
                            loaiGhe = loaiGhes.stream().filter(lg -> lg.getTenLoaiGhe().equals("Ghế VIP")).findFirst().orElse(loaiGhes.get(1));
                        } else { // row == 'M'
                            if (col % 2 == 1) {
                                loaiGhe = loaiGhes.stream().filter(lg -> lg.getTenLoaiGhe().equals("Ghế đôi SweetBox")).findFirst().orElse(loaiGhes.get(2));
                                viTri = String.format("%c%02d-%02d", row, col, col + 1);
                                Ghe gheDoi = new Ghe();
                                gheDoi.setMaGhe(getNextSequenceValue(em, "GheSequence", "Ghe", 4));
                                gheDoi.setLoaiGhe(loaiGhe);
                                gheDoi.setViTri(viTri);
                                gheDoi.setPhong(phong);
                                em.persist(gheDoi);
                                ghes.add(gheDoi);
                            } else {
                                continue;
                            }
                        }

                        ghe.setMaGhe(getNextSequenceValue(em, "GheSequence", "Ghe", 4));
                        ghe.setViTri(viTri);
                        ghe.setLoaiGhe(loaiGhe);
                        ghe.setPhong(phong);
                        em.persist(ghe);
                        ghes.add(ghe);
                    }
                }
                em.flush();
                tr.commit();
                System.out.println("Đã tạo " + ghes.size() + " Ghe cho " + phong.getTenPhong());
            }

            // 3. Tạo TaiKhoan, NhanVien, KhachHang
            System.out.println("Tạo TaiKhoan, NhanVien, KhachHang...");
            tr.begin();
            List<KhachHang> khachHangs = new ArrayList<>();
            List<NhanVien> nhanViens = new ArrayList<>();
            List<TaiKhoan> taiKhoans = new ArrayList<>();
            Set<String> uniqueEmails = new HashSet<>();
            Set<String> uniquePhones = new HashSet<>();
            Set<String> uniqueUsernames = new HashSet<>();

            // Tạo TaiKhoan từ dữ liệu SQL
            for (String[] tkData : taiKhoanData) {
                String username = tkData[0];
                String matKhau = tkData[1];

                if (!uniqueUsernames.add(username)) {
                    System.out.println("Bỏ qua username trùng: " + username);
                    continue;
                }

                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setId(getNextSequenceValue(em, "TaiKhoanSequence", "TK", 3));
                taiKhoan.setTaiKhoan(username);
                taiKhoan.setMatKhau(matKhau);

                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(getNextSequenceValue(em, "NhanVienSequence", "NV", 3));
                nhanVien.setHoTen(faker.name().fullName());
                nhanVien.setGioiTinh(faker.bool().bool());
                nhanVien.setNgaySinh(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                String emailNv;
                do {
                    emailNv = faker.internet().emailAddress();
                } while (!uniqueEmails.add(emailNv));
                nhanVien.setEmail(emailNv);
                String phoneNv;
                do {
                    phoneNv = "0" + faker.number().numberBetween(100000000, 999999999);
                } while (!uniquePhones.add(phoneNv));
                nhanVien.setSoDienThoai(phoneNv);
                // Gán vai trò dựa trên username
                if (username.equals("admin")) {
                    nhanVien.setVaiTro("Nhân viên quản lý");
                } else if (username.equals("trong")) {
                    nhanVien.setVaiTro("Nhân viên bán vé");
                } else {
                    nhanVien.setVaiTro(faker.options().option("Nhân viên bán vé", "Nhân viên quản lý"));
                }
                nhanVien.setNgayBatDauLam(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                nhanVien.setTrangThai(faker.options().option("Đang làm", "Đã nghỉ"));
                nhanVien.setHoaDons(new HashSet<>());
                taiKhoan.setNhanVien(nhanVien);

                em.persist(nhanVien);
                em.persist(taiKhoan);
                nhanViens.add(nhanVien);
                taiKhoans.add(taiKhoan);
            }

            // Tạo KhachHang
            for (int i = 0; i < 1000; i++) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(getNextSequenceValue(em, "KhachHangSequence", "KH", 6));
                khachHang.setTenKhachHang(faker.name().fullName());
                String phoneKh;
                do {
                    phoneKh = "0" + faker.number().numberBetween(100000000, 999999999);
                } while (!uniquePhones.add(phoneKh));
                khachHang.setSoDienThoai(phoneKh);
                String emailKh;
                do {
                    emailKh = faker.internet().emailAddress();
                } while (!uniqueEmails.add(emailKh));
                khachHang.setEmail(emailKh);
                khachHang.setHoaDons(new HashSet<>());
                em.persist(khachHang);
                khachHangs.add(khachHang);
            }
            em.flush();
            tr.commit();
            System.out.println("Đã tạo " + khachHangs.size() + " KhachHang, " + nhanViens.size() + " NhanVien, " + taiKhoans.size() + " TaiKhoan");

            // 4. Tạo KhuyenMai, SanPham, Phim
            System.out.println("Tạo KhuyenMai, SanPham, Phim...");
            tr.begin();
            List<KhuyenMai> khuyenMais = new ArrayList<>();
            List<SanPham> sanPhams = new ArrayList<>();
            List<Phim> phims = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKhuyenMai(getNextSequenceValue(em, "KhuyenMaiSequence", "KM", 4));
                km.setTenKhuyenMai(faker.options().option(events));
                km.setNgayBatDau(LocalDate.now().minusDays(faker.random().nextInt(1, 30)));
                km.setNgayKetThuc(LocalDate.now().plusDays(faker.random().nextInt(1, 30)));
                km.setPhanTramKhuyenMai(faker.random().nextDouble(0.01, 0.1));
                km.setTongHoaDonToiThieu(faker.random().nextDouble(100000, 2000000));

                SanPham sp = new SanPham();
                sp.setMaSanPham(getNextSequenceValue(em, "SanPhamSequence", "SP", 4));
                String loaiSanPham = faker.options().option("Đồ ăn", "Nước uống");
                sp.setLoaiSanPham(loaiSanPham);
                if ("Đồ ăn".equals(loaiSanPham)) {
                    sp.setTenSanPham(faker.options().option(foods));
                } else {
                    sp.setTenSanPham(faker.options().option(drinks));
                }
                sp.setSoLuong(faker.random().nextInt(1, 100));
                double giaMua = faker.random().nextDouble(10000, 30000);
                sp.setGiaMua(giaMua);
                sp.setGiaBan(giaMua * 1.5);
                if ("Đồ ăn".equals(loaiSanPham)) {
                    sp.setTenSanPham(faker.options().option(foods));
                    sp.setAnh(faker.options().option(anhDoAn.toArray(new String[0])));
                } else {
                    sp.setTenSanPham(faker.options().option(drinks));
                    sp.setAnh(faker.options().option(anhNuocUong.toArray(new String[0])));
                }

                Phim phim = new Phim();
                phim.setMaPhim(getNextSequenceValue(em, "PhimSequence", "P", 3));
                phim.setTenPhim(faker.book().title());
                phim.setTheLoai(faker.book().genre());
                phim.setDaoDien(faker.name().fullName());
                phim.setThoiLuong(faker.number().numberBetween(90, 180));
                phim.setNgayCongChieu(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                phim.setNgonNgu(faker.options().option(ngonNgu));
                phim.setQuocGia(faker.country().name());
                phim.setTrangThai(faker.bool().bool() ? "Đang chiếu" : "Sắp chiếu");
                phim.setNgayBatDau(faker.date().past(10, java.util.concurrent.TimeUnit.DAYS)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                phim.setGiaThau(faker.number().randomDouble(2, 50000, 200000));
                phim.setAnh(faker.options().option(anhList));
                phim.setTrailer(faker.internet().url());
                phim.setTomTat(faker.lorem().sentence(20));

                em.persist(km);
                em.persist(sp);
                em.persist(phim);
                khuyenMais.add(km);
                sanPhams.add(sp);
                phims.add(phim);
            }
            em.flush();
            tr.commit();
            System.out.println("Đã tạo " + khuyenMais.size() + " KhuyenMai, " + sanPhams.size() + " SanPham, " + phims.size() + " Phim");

            // 5. Tạo LichChieu
            System.out.println("Tạo LichChieu...");
            tr.begin();
            List<LichChieu> lichChieus = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                LichChieu lichChieu = new LichChieu();
                lichChieu.setMaLichChieu(getNextSequenceValue(em, "LichChieuSequence", "LC", 6));
                Phim phim = phims.get(faker.random().nextInt(phims.size()));
                Phong phong = phongs.get(faker.random().nextInt(phongs.size()));
                LocalDate ngayChieu = LocalDate.now().plusDays(faker.random().nextInt(1, 300));
                LocalTime gioBatDau = LocalTime.of(faker.random().nextInt(8, 22), faker.random().nextInt(0, 59));
                LocalTime gioKetThuc = gioBatDau.plus(phim.getThoiLuong(), ChronoUnit.MINUTES);

                lichChieu.setGioBatDau(ngayChieu.atTime(gioBatDau));
                lichChieu.setGioKetThuc(ngayChieu.atTime(gioKetThuc));
                lichChieu.setGiaMotGhe(faker.number().randomDouble(2, 50000, 100000));
                lichChieu.setPhim(phim);
                lichChieu.setPhong(phong);
                em.persist(lichChieu);
                lichChieus.add(lichChieu);
            }
            em.flush();
            tr.commit();
            System.out.println("Đã tạo " + lichChieus.size() + " LichChieu");

            // 6. Tạo HoaDon, ChiTietHoaDon, Ve
            System.out.println("Tạo HoaDon, ChiTietHoaDon, Ve...");
            tr.begin();
            List<HoaDon> hoaDons = new ArrayList<>();
            int totalVe = 0;
            int totalCTHD = 0;
            for (int i = 0; i < 1000; i++) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(getNextSequenceValue(em, "HoaDonSequence", "HD", 6));
                NhanVien nhanVien = nhanViens.get(faker.random().nextInt(nhanViens.size()));
                KhachHang khachHang = khachHangs.get(faker.random().nextInt(khachHangs.size()));
                LichChieu lichChieu = lichChieus.get(faker.random().nextInt(lichChieus.size()));
                KhuyenMai khuyenMai = khuyenMais.get(faker.random().nextInt(khuyenMais.size()));

                hoaDon.setNgayDat(faker.date().past(300, java.util.concurrent.TimeUnit.DAYS)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                int soGhe = faker.number().numberBetween(1, 5);
                hoaDon.setSoGhe(soGhe);
                hoaDon.setGhiChu(faker.lorem().sentence());
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setKhuyenMai(khuyenMai);

                // Tạo ChiTietHoaDon
                Set<ChiTietHoaDon> danhSachCTHD = new HashSet<>();
                for (int j = 0; j < 3; j++) {
                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                    cthd.setHoaDon(hoaDon);
                    SanPham sanPham = sanPhams.get(faker.random().nextInt(sanPhams.size()));
                    cthd.setSanPham(sanPham);
                    int soLuong = faker.number().numberBetween(1, 5);
                    cthd.setSoLuong(soLuong);
                    cthd.setThanhTien(soLuong * sanPham.getGiaBan());
                    danhSachCTHD.add(cthd);
                }
                hoaDon.setDanhSachChiTietHD(danhSachCTHD);
                totalCTHD += danhSachCTHD.size();

                // Lưu HoaDon trước
                em.persist(hoaDon);

                // Tạo Ve
                Set<Ve> danhSachVe = new HashSet<>();
                int numTickets = faker.random().nextInt(1, 4);
                List<Ghe> danhSachGhe = em.createQuery("SELECT g FROM Ghe g WHERE g.phong = :phong", Ghe.class)
                        .setParameter("phong", lichChieu.getPhong())
                        .getResultList();
                Set<Integer> usedGheIndices = new HashSet<>();
                for (int j = 0; j < numTickets && !danhSachGhe.isEmpty(); j++) {
                    int gheIndex;
                    do {
                        gheIndex = faker.random().nextInt(danhSachGhe.size());
                    } while (!usedGheIndices.add(gheIndex));
                    Ghe ghe = danhSachGhe.get(gheIndex);
                    Ve ve = new Ve();
                    ve.setMaVe(getNextSequenceValue(em, "VeSequence", "Ve", 6));
                    ve.setLichChieu(lichChieu);
                    ve.setNgayPhatHanh(lichChieu.getGioBatDau().toLocalDate());
                    ve.setGhe(ghe);
                    ve.setHoaDon(hoaDon);
                    em.persist(ve);
                    danhSachVe.add(ve);
                }
                hoaDon.setDanhSachVe(danhSachVe);
                totalVe += danhSachVe.size();

                // Tính tổng tiền
                double tongTienVe = 0;
                for (Ve ve : danhSachVe) {
                    String tenLoaiGhe = ve.getGhe().getLoaiGhe().getTenLoaiGhe();
                    double giaGhe = ve.getLichChieu().getGiaMotGhe();
                    if ("Ghế thường".equals(tenLoaiGhe)) {
                        tongTienVe += giaGhe;
                    } else if ("Ghế VIP".equals(tenLoaiGhe)) {
                        tongTienVe += giaGhe * 1.5;
                    } else { // Ghế đôi SweetBox
                        tongTienVe += giaGhe * 2;
                    }
                }
                double tongTienSanPham = danhSachCTHD.stream()
                        .mapToDouble(ChiTietHoaDon::getThanhTien)
                        .sum();
                hoaDon.setTongTien(tongTienVe + tongTienSanPham);

                // Lưu ChiTietHoaDon
                danhSachCTHD.forEach(cthd -> {
                    ChiTietHoaDonPK pk = new ChiTietHoaDonPK(hoaDon, cthd.getSanPham());

                    cthd.setId(pk);
                    em.persist(cthd);
                });

                hoaDons.add(hoaDon);
            }
            em.flush();
            tr.commit();
            System.out.println("Đã tạo " + hoaDons.size() + " HoaDon, " + totalCTHD + " ChiTietHoaDon, " + totalVe + " Ve");

            System.out.println("Dữ liệu giả lập đã được tạo thành công!");
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            System.err.println("Lỗi khi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}