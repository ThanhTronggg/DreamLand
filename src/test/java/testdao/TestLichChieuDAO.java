package testdao;

import dao.LichChieuDAO;
import dao.PhimDAO;
import dao.PhongDAO;
import entity.LichChieu;
import entity.Phim;
import entity.Phong;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TestLichChieuDAO {

    private LichChieuDAO lichChieuDAO;
    private LichChieu lichChieu;
    private Phim phim;
    private PhimDAO phimDAO;
    private Phong phong;
    private PhongDAO phongDAO;

    @BeforeEach
    void setUp() {
        lichChieuDAO = new LichChieuDAO();
        lichChieu = new LichChieu();
        phim = new Phim();
        phimDAO = new PhimDAO();
        phong = new Phong();
        phongDAO = new PhongDAO();
        Faker faker = new Faker();

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

        phong.setTenPhong("Phòng " + faker.random().nextInt(10));
        phong.setSoLuongGhe(192);

        LocalDate ngayChieu = LocalDate.now().plusDays(faker.random().nextInt(1, 30));
        LocalTime gioBatDau = LocalTime.of(faker.random().nextInt(8, 22), faker.random().nextInt(0, 59));
        LocalTime gioKetThuc = gioBatDau.plus(phim.getThoiLuong(), ChronoUnit.MINUTES);

        lichChieu.setGioBatDau(ngayChieu.atTime(gioBatDau));
        lichChieu.setGioKetThuc(ngayChieu.atTime(gioKetThuc));
        lichChieu.setGiaMotGhe(faker.number().randomDouble(2, 50000, 100000));
        lichChieu.setPhim(phim);
        lichChieu.setPhong(phong);
    }

    @Test
    void testThemLichChieu() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        boolean daTaoLichChieu = lichChieuDAO.themLichChieu(lichChieu);
        assertTrue(daTaoLichChieu, "Lỗi không thêm được lịch chiếu");

        LichChieu lichChieuDaTao = lichChieuDAO.timTheoMaLichChieu(lichChieu.getMaLichChieu());
        assertNotNull(lichChieuDaTao, "Không tìm thấy lịch chiếu");

        assertEquals(lichChieu.getGioBatDau(), lichChieuDaTao.getGioBatDau(), "Giờ bắt đầu không khớp.");
        assertEquals(lichChieu.getGioKetThuc(), lichChieuDaTao.getGioKetThuc(), "Giờ kết thúc không khớp.");
        assertEquals(lichChieu.getGiaMotGhe(), lichChieuDaTao.getGiaMotGhe(), "Giá mỗi ghế không khớp.");
        assertEquals(lichChieu.getPhim(), lichChieuDaTao.getPhim(), "Phim không khớp.");
        assertEquals(lichChieu.getPhong(), lichChieuDaTao.getPhong(), "Phòng không khớp.");
    }

    @Test
    void testCapNhatLichChieu() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        boolean daTaoLichChieu = lichChieuDAO.themLichChieu(lichChieu);
        assertTrue(daTaoLichChieu, "Lỗi không thêm được lịch chiếu");

        lichChieu.setGiaMotGhe(70000.0);

        boolean daCapNhatLichChieu = lichChieuDAO.capNhatLichChieu(lichChieu);
        assertTrue(daCapNhatLichChieu, "Lỗi không cập nhật được lịch chiếu");

        LichChieu lichChieuCapNhat = lichChieuDAO.timTheoMaLichChieu(lichChieu.getMaLichChieu());
        assertNotNull(lichChieuCapNhat, "Không tìm thấy lịch chiếu sau khi cập nhật");
        assertEquals(70000.0, lichChieuCapNhat.getGiaMotGhe(), "Giá mỗi ghế không khớp sau khi cập nhật.");
    }

    @Test
    void testXoaLichChieu() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        boolean daTaoLichChieu = lichChieuDAO.themLichChieu(lichChieu);
        assertTrue(daTaoLichChieu, "Lỗi không thêm được lịch chiếu");

        boolean daXoaLichChieu = lichChieuDAO.xoaLichChieu(lichChieu.getMaLichChieu());
        assertTrue(daXoaLichChieu, "Lỗi không xóa được lịch chiếu");

        LichChieu lichChieuDaXoa = lichChieuDAO.timTheoMaLichChieu(lichChieu.getMaLichChieu());
        assertNull(lichChieuDaXoa, "Lịch chiếu vẫn còn tồn tại sau khi xóa.");
    }
}
