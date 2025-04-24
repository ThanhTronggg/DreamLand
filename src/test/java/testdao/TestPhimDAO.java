package testdao;

import dao.PhimDAO;
import entity.Phim;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhimDAO {

    private PhimDAO phimDAO;
    private Phim phim;

    @BeforeEach
    void setUp() {
        phimDAO = new PhimDAO();
        phim = new Phim();
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
    }

    @Test
    void testThemPhim() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        Phim phimDaTao = phimDAO.timTheoMaPhim(phim.getMaPhim());

        assertNotNull(phimDaTao, "Không tìm thấy phim");
        assertEquals(phim.getMaPhim(), phimDaTao.getMaPhim(), "Lỗi khi tạo: Mã phim không khớp.");
        assertEquals(phim.getTenPhim(), phimDaTao.getTenPhim(), "Lỗi khi tạo: Tên phim không khớp.");
        assertEquals(phim.getTheLoai(), phimDaTao.getTheLoai(), "Lỗi khi tạo: Thể loại không khớp.");
        assertEquals(phim.getDaoDien(), phimDaTao.getDaoDien(), "Lỗi khi tạo: Đạo diễn không khớp.");
        assertEquals(phim.getThoiLuong(), phimDaTao.getThoiLuong(), "Lỗi khi tạo: Thời lượng không khớp.");
        assertEquals(phim.getNgayCongChieu(), phimDaTao.getNgayCongChieu(), "Lỗi khi tạo: Ngày công chiếu không khớp.");
        assertEquals(phim.getNgonNgu(), phimDaTao.getNgonNgu(), "Lỗi khi tạo: Ngôn ngữ không khớp.");
        assertEquals(phim.getQuocGia(), phimDaTao.getQuocGia(), "Lỗi khi tạo: Quốc gia không khớp.");
        assertEquals(phim.getTrangThai(), phimDaTao.getTrangThai(), "Lỗi khi tạo: Trạng thái không khớp.");
        assertEquals(phim.getNgayBatDau(), phimDaTao.getNgayBatDau(), "Lỗi khi tạo: Ngày bắt đầu không khớp.");
        assertEquals(phim.getGiaThau(), phimDaTao.getGiaThau(), "Lỗi khi tạo: Giá thầu không khớp.");
        assertEquals(phim.getAnh(), phimDaTao.getAnh(), "Lỗi khi tạo: Ảnh không khớp.");
        assertEquals(phim.getTrailer(), phimDaTao.getTrailer(), "Lỗi khi tạo: Trailer không khớp.");
        assertEquals(phim.getTomTat(), phimDaTao.getTomTat(), "Lỗi khi tạo: Tóm tắt không khớp.");
    }

    @Test
    void testCapNhatPhim() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        phim.setTenPhim("Doraemon");
        phim.setGiaThau(300000.0);
        phim.setTrangThai("Đang chiếu");

        boolean daCapNhatPhim = phimDAO.capNhatPhim(phim);
        assertTrue(daCapNhatPhim, "Lỗi không cập nhật được phim");

        Phim phimCapNhat = phimDAO.timTheoMaPhim(phim.getMaPhim());

        assertNotNull(phimCapNhat, "Không tìm thấy phim sau khi cập nhật");
        assertEquals("Doraemon", phimCapNhat.getTenPhim(), "Lỗi khi cập nhật: Tên phim không khớp.");
        assertEquals(300000.0, phimCapNhat.getGiaThau(), "Lỗi khi cập nhật: Giá thầu không khớp.");
        assertEquals("Đang chiếu", phimCapNhat.getTrangThai(), "Lỗi khi cập nhật: Trạng thái không khớp.");
    }

    @Test
    void testXoaPhim() {
        boolean daTaoPhim = phimDAO.themPhim(phim);
        assertTrue(daTaoPhim, "Lỗi không thêm được phim");

        boolean daXoaPhim = phimDAO.xoaPhim(phim.getMaPhim());
        assertTrue(daXoaPhim, "Lỗi không xóa được phim");

        Phim phimDaXoa = phimDAO.timTheoMaPhim(phim.getMaPhim());
        assertNull(phimDaXoa, "Lỗi khi xóa: Phim vẫn còn tồn tại.");
    }
}
