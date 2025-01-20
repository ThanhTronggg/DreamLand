package testdao;

import dao.KhuyenMaiDAO;
import entity.KhuyenMai;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestKhuyenMaiDAO {

    private KhuyenMaiDAO khuyenMaiDAO;
    private KhuyenMai khuyenMai;

    @BeforeEach
    void setUp() {
        khuyenMaiDAO = new KhuyenMaiDAO();
        khuyenMai = new KhuyenMai();
        Faker faker = new Faker();

        String[] events = {
                "Tết Nguyên Đán",
                "Giáng Sinh",
                "Black Friday",
                "Valentine",
                "Lễ Quốc Khánh",
                "Halloween",
                "Lễ Phục Sinh"
        };

        khuyenMai.setTenKhuyenMai(faker.options().option(events));
        khuyenMai.setNgayBatDau(LocalDate.now().minusDays(faker.random().nextInt(1, 30)));
        khuyenMai.setNgayKetThuc(LocalDate.now().plusDays(faker.random().nextInt(1, 30)));
        khuyenMai.setPhamTramKhuyenMai(faker.random().nextDouble(0.1, 0.5));
        khuyenMai.setTongHoaDonToiThieu(faker.random().nextDouble(100000, 2000000));
    }

    @Test
    void testThemKhuyenMai() {
        boolean daTaoKhuyenMai = khuyenMaiDAO.themKhuyenMai(khuyenMai);
        assertTrue(daTaoKhuyenMai, "Lỗi không thêm được khuyến mãi");

        KhuyenMai khuyenMaiDaTao = khuyenMaiDAO.timTheoMaKhuyenMai(khuyenMai.getMaKhuyenMai());

        assertNotNull(khuyenMaiDaTao, "Không tìm thấy khuyến mãi");
        assertEquals(khuyenMai.getMaKhuyenMai(), khuyenMaiDaTao.getMaKhuyenMai(), "Lỗi khi tạo: Mã khuyến mãi không khớp.");
        assertEquals(khuyenMai.getTenKhuyenMai(), khuyenMaiDaTao.getTenKhuyenMai(), "Lỗi khi tạo: Tên khuyến mãi không khớp.");
        assertEquals(khuyenMai.getNgayBatDau(), khuyenMaiDaTao.getNgayBatDau(), "Lỗi khi tạo: Ngày bắt đầu không khớp.");
        assertEquals(khuyenMai.getNgayKetThuc(), khuyenMaiDaTao.getNgayKetThuc(), "Lỗi khi tạo: Ngày kết thúc không khớp.");
        assertEquals(khuyenMai.getPhamTramKhuyenMai(), khuyenMaiDaTao.getPhamTramKhuyenMai(), "Lỗi khi tạo: Phần trăm khuyến mãi không khớp.");
        assertEquals(khuyenMai.getTongHoaDonToiThieu(), khuyenMaiDaTao.getTongHoaDonToiThieu(), "Lỗi khi tạo: Tổng hóa đơn tối thiểu không khớp.");
    }

    @Test
    void testCapNhatKhuyenMai() {
        boolean daTaoKhuyenMai = khuyenMaiDAO.themKhuyenMai(khuyenMai);
        assertTrue(daTaoKhuyenMai, "Lỗi không thêm được khuyến mãi");

        khuyenMai.setTenKhuyenMai("Khuyến mãi Tết");
        khuyenMai.setPhamTramKhuyenMai(0.3);
        khuyenMai.setTongHoaDonToiThieu(500000.0);

        boolean daCapNhatKhuyenMai = khuyenMaiDAO.capNhatKhuyenMai(khuyenMai);
        assertTrue(daCapNhatKhuyenMai, "Lỗi không cập nhật được khuyến mãi");

        KhuyenMai khuyenMaiCapNhat = khuyenMaiDAO.timTheoMaKhuyenMai(khuyenMai.getMaKhuyenMai());

        assertNotNull(khuyenMaiCapNhat, "Không tìm thấy khuyến mãi sau khi cập nhật");
        assertEquals("Khuyến mãi Tết", khuyenMaiCapNhat.getTenKhuyenMai(), "Lỗi khi cập nhật: Tên khuyến mãi không khớp.");
        assertEquals(0.3, khuyenMaiCapNhat.getPhamTramKhuyenMai(), "Lỗi khi cập nhật: Phần trăm khuyến mãi không khớp.");
        assertEquals(500000.0, khuyenMaiCapNhat.getTongHoaDonToiThieu(), "Lỗi khi cập nhật: Tổng hóa đơn tối thiểu không khớp.");
    }

    @Test
    void testXoaKhuyenMai() {
        boolean daTaoKhuyenMai = khuyenMaiDAO.themKhuyenMai(khuyenMai);
        assertTrue(daTaoKhuyenMai, "Lỗi không thêm được khuyến mãi");

        boolean daXoaKhuyenMai = khuyenMaiDAO.xoaKhuyenMai(khuyenMai.getMaKhuyenMai());
        assertTrue(daXoaKhuyenMai, "Lỗi không xóa được khuyến mãi");

        KhuyenMai khuyenMaiDaXoa = khuyenMaiDAO.timTheoMaKhuyenMai(khuyenMai.getMaKhuyenMai());
        assertNull(khuyenMaiDaXoa, "Lỗi khi xóa: Khuyến mãi vẫn còn tồn tại.");
    }

}
