import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();

        Faker faker = new Faker();
        Random rand = new Random();

        String[] events = {
                "Tết Nguyên Đán",
                "Giáng Sinh",
                "Black Friday",
                "Valentine",
                "Lễ Quốc Khánh",
                "Halloween",
                "Lễ Phục Sinh"
        };
        String[] foods = {"Bắp rang bơ cỡ nhỏ", "Bắp rang bơ cỡ lớn", "X2 Bắp", "Combo x3 bắp"};
        String[] drinks = {"Cocacola", "7up", "Pepsi", "Sting"};
        String[] ngonNgu = {
                "Tiếng Anh",
                "Tiếng Pháp",
                "Tiếng Tây Ban Nha",
                "Tiếng Trung",
                "Tiếng Nhật",
                "Tiếng Đức",
                "Tiếng Hàn",
                "Tiếng Việt",
                "Tiếng Nga"
        };

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
        String[] tenLoaiGhe = {"Ghế thường", "Ghế VIP", "Ghế đôi SweetBox"};

        for (int i = 0; i < 3; i++) {
            LoaiGhe loaiGhe = new LoaiGhe();
            loaiGhe.setTenLoaiGhe(tenLoaiGhe[i]);
            loaiGhe.setMoTaLoaiGhe(faker.lorem().sentence(20));

            tr.begin();
            em.persist(loaiGhe);
            tr.commit();
        }

        for (int i = 0; i < 7; i++) { // Lặp qua các phòng (7 phòng)
            Phong phong = new Phong();
            phong.setTenPhong("Phòng " + i);
            phong.setSoLuongGhe(192);

            tr.begin();
            em.persist(phong);
            tr.commit();

            for (char row = 'A'; row <= 'M'; row++) {
                for (int col = 1; col <= 16; col++) {

                    Ghe ghe = new Ghe();
                    String viTri = String.format("%c%02d", row, col);

                    if (row >= 'A' && row <= 'D') {
                        ghe.setLoaiGhe(em.find(LoaiGhe.class, 1));
                    } else if (row >= 'E' && row <= 'L') {
                        ghe.setLoaiGhe(em.find(LoaiGhe.class, 2));
                    } else if (row == 'M') {
                        if (col % 2 == 1) {
                            String viTriDoi = String.format("%c%02d-%02d", row, col, col + 1);
                            ghe.setLoaiGhe(em.find(LoaiGhe.class, 3));
                            ghe.setViTri(viTriDoi);

                            Ghe gheDoi = new Ghe();
                            gheDoi.setLoaiGhe(em.find(LoaiGhe.class, 3));
                            gheDoi.setViTri(viTriDoi);
                            gheDoi.setPhong(phong);

                            tr.begin();
                            em.persist(gheDoi);
                            tr.commit();
                        }
                        continue;
                    }

                    ghe.setPhong(phong);
                    ghe.setViTri(viTri);

                    tr.begin();
                    em.persist(ghe);
                    tr.commit();
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang(faker.name().fullName());
            khachHang.setSoDienThoai(faker.phoneNumber().cellPhone());
            khachHang.setEmail(faker.internet().emailAddress());
            khachHang.setHoaDons(new HashSet<>());

            NhanVien nhanVien = new NhanVien();
            nhanVien.setHoTen(faker.name().fullName());
            nhanVien.setGioiTinh(faker.bool().bool());
            nhanVien.setNgaySinh(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());  // Corrected line
            nhanVien.setEmail(faker.internet().emailAddress());
            nhanVien.setSoDienThoai(faker.phoneNumber().phoneNumber());
            nhanVien.setVaiTro(faker.options().option("Nhân viên bán vé", "Nhân viên quản lý"));
            nhanVien.setNgayBatDauLam(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            nhanVien.setLuong(faker.number().randomDouble(2, 5000, 15000));
            nhanVien.setTrangThai(faker.options().option("Active", "Inactive"));
            nhanVien.setHoaDons(new HashSet<>());

            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTaiKhoan(faker.name().username());
            taiKhoan.setMatKhau(faker.internet().password());
            taiKhoan.setNhanVien(nhanVien);

            tr.begin();

            em.persist(khachHang);
            em.persist(nhanVien);
            em.persist(taiKhoan);

            tr.commit();

        }

        for (int i = 0; i < 100; i++) {
            KhuyenMai km = new KhuyenMai();
            SanPham sp = new SanPham();
            Phim phim = new Phim();

            km.setTenKhuyenMai(faker.options().option(events));
            km.setNgayBatDau(LocalDate.now().minusDays(faker.random().nextInt(1, 30)));
            km.setNgayKetThuc(LocalDate.now().plusDays(faker.random().nextInt(1, 30)));
            km.setPhanTramKhuyenMai(faker.random().nextDouble(0.1, 0.5));
            km.setTongHoaDonToiThieu(faker.random().nextDouble(100000, 2000000));

            sp.setLoaiSanPham(faker.options().option("Đồ ăn", "Nước uống"));
            if ("Đồ ăn".equals(sp.getLoaiSanPham())) {
                sp.setTenSanPham(faker.options().option(foods));
            } else {
                sp.setTenSanPham(faker.options().option(drinks));
            }
            sp.setSoLuong(faker.random().nextInt(1, 100));
            sp.setGiaMua(faker.random().nextDouble(10000, 30000));
            sp.setGiaBan(sp.getGiaMua()*1.5);
            sp.setAnh(faker.internet().url());

            phim.setTenPhim(faker.book().title());
            phim.setTheLoai(faker.book().genre());
            phim.setDaoDien(faker.name().fullName());
            phim.setThoiLuong(faker.number().numberBetween(90, 180));
            phim.setNgayCongChieu(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            phim.setNgonNgu(String.valueOf(faker.random().nextInt(ngonNgu.length)));
            phim.setQuocGia(faker.country().name());
            phim.setTrangThai(faker.bool().bool() ? "Đang chiếu" : "Sắp chiếu");
            phim.setNgayBatDau(faker.date().past(10, java.util.concurrent.TimeUnit.DAYS)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            phim.setGiaThau(faker.number().randomDouble(2, 50000, 200000));
            phim.setAnh(faker.internet().url());
            phim.setTrailer(faker.internet().url());
            phim.setTomTat(faker.lorem().sentence(20));

            tr.begin();
            em.persist(phim);
            em.persist(sp);
            em.persist(km);
            tr.commit();
        }

        List<Phim> danhSachPhim = em.createQuery("SELECT p FROM Phim p", Phim.class).getResultList();
        List<Phong> danhSachPhong = em.createQuery("SELECT p FROM Phong p", Phong.class).getResultList();

        for (int i = 0; i < 100; i++) {
            Phim phim = danhSachPhim.get(faker.random().nextInt(danhSachPhim.size()));
            Phong phong = danhSachPhong.get(faker.random().nextInt(danhSachPhong.size()));

            LocalDate ngayChieu = LocalDate.now().plusDays(faker.random().nextInt(1, 30));
            LocalTime gioBatDau = LocalTime.of(faker.random().nextInt(8, 22), faker.random().nextInt(0, 59));
            LocalTime gioKetThuc = gioBatDau.plus(phim.getThoiLuong(), ChronoUnit.MINUTES);

            LichChieu lichChieu = new LichChieu();
            lichChieu.setGioBatDau(ngayChieu.atTime(gioBatDau));
            lichChieu.setGioKetThuc(ngayChieu.atTime(gioKetThuc));
            lichChieu.setGiaMotGhe(faker.number().randomDouble(2, 50000, 100000));
            lichChieu.setPhim(phim);
            lichChieu.setPhong(phong);

            tr.begin();
            em.persist(lichChieu);
            tr.commit();
        }

        List<NhanVien> danhSachNhanVien = em.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class).getResultList();
        List<KhachHang> danhSachKhachHang = em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class).getResultList();
        List<LichChieu> danhSachLichChieu = em.createQuery("SELECT lc FROM LichChieu lc", LichChieu.class).getResultList();
        List<SanPham> danhSachSanPham = em.createQuery("SELECT sp FROM SanPham sp", SanPham.class).getResultList();

        for (int i = 0; i < 100; i++) {
            NhanVien nhanVien = danhSachNhanVien.get(faker.random().nextInt(danhSachNhanVien.size()));
            KhachHang khachHang = danhSachKhachHang.get(faker.random().nextInt(danhSachKhachHang.size()));
            LichChieu lichChieu = danhSachLichChieu.get(faker.random().nextInt(danhSachLichChieu.size()));
            HoaDon hoaDon = new HoaDon();

            hoaDon.setNgayDat(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            hoaDon.setSoGhe(faker.number().numberBetween(1, 5));  // Số lượng ghế ngẫu nhiên
            hoaDon.setGhiChu(faker.lorem().sentence());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setKhachHang(khachHang);
            Set<ChiTietHoaDon> danhSachCTHD = new HashSet<>();
            for (int j = 0; j < 3; j++) {  // Giả sử mỗi hóa đơn có 3 sản phẩm
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setHoaDon(hoaDon);
                chiTietHoaDon.setSanPham(danhSachSanPham.get(faker.random().nextInt(danhSachSanPham.size())));
                chiTietHoaDon.setSoLuong(faker.number().numberBetween(1, 5));
                chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong()*chiTietHoaDon.getSanPham().getGiaBan());
                System.out.println(chiTietHoaDon);
                danhSachCTHD.add(chiTietHoaDon);
            }
            Set<Ve> danhSachVe = new HashSet<>();
            int numTickets = faker.random().nextInt(1, 4);  // Random number between 1 and 3
            for (int j = 0; j < numTickets; j++) {
                Ve ve = new Ve();
                ve.setLichChieu(lichChieu);
                ve.setNgayPhatHanh(lichChieu.getGioBatDau().toLocalDate());

                List<Ghe> danhSachGhe = em.createQuery("SELECT ghe FROM Ghe ghe where phong = :phong", Ghe.class)
                        .setParameter("phong", lichChieu.getPhong())
                        .getResultList();

                Ghe selectedGhe = danhSachGhe.get(faker.random().nextInt(danhSachGhe.size()));
                ve.setGhe(selectedGhe);
                tr.begin();
                em.persist(ve);
                tr.commit();
                danhSachVe.add(ve);
            }

            hoaDon.setDanhSachChiTietHD(danhSachCTHD);
            hoaDon.setDanhSachVe(danhSachVe);

            double tongTienVe = 0;
            for (Ve ve : danhSachVe) {
                if (ve.getGhe().getLoaiGhe().getTenLoaiGhe() == "Ghế thường") {
                    tongTienVe += ve.getLichChieu().getGiaMotGhe();
                }
                else if (ve.getGhe().getLoaiGhe().getTenLoaiGhe() == "Ghế VIP"){
                    tongTienVe += ve.getLichChieu().getGiaMotGhe()*1.5;
                }
                else {
                    tongTienVe += ve.getLichChieu().getGiaMotGhe() * 2;
                }
            }
            double tongTienSanPham = 0;
            for (ChiTietHoaDon ct : hoaDon.getDanhSachChiTietHD()) {
                tongTienSanPham += ct.getThanhTien();
            }
            hoaDon.setTongTien(tongTienVe + tongTienSanPham);

            tr.begin();

            em.persist(hoaDon);

            tr.commit();

            for (Ve ve : danhSachVe) {
                ve.setHoaDon(hoaDon);
                tr.begin();
                em.persist(ve);
                tr.commit();
            }

            for (ChiTietHoaDon ct : hoaDon.getDanhSachChiTietHD()) {
                ChiTietHoaDonPK chiTietHoaDonPK = new ChiTietHoaDonPK(hoaDon, ct.getSanPham());

                ct.setId(chiTietHoaDonPK);
                tr.begin();
                em.persist(ct);
                tr.commit();
            }

        }
    }
}
