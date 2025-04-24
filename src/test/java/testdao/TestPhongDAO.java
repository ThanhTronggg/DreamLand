package testdao;

import dao.PhongDAO;
import entity.Phong;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhongDAO {

    private PhongDAO phongDAO;
    private Phong phong;

    @BeforeEach
    void setUp() {
        phongDAO = new PhongDAO();
        phong = new Phong();
        Faker faker = new Faker();

        phong.setTenPhong("Phòng " + faker.random().nextInt(10));
        phong.setSoLuongGhe(192);
    }

    @Test
    void testThemPhong() {
        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        Phong phongDaTao = phongDAO.timTheoMaPhong(phong.getMaPhong());

        assertNotNull(phongDaTao, "Không tìm thấy phòng");
        assertEquals(phong.getMaPhong(), phongDaTao.getMaPhong(), "Mã phòng không khớp.");
        assertEquals(phong.getTenPhong(), phongDaTao.getTenPhong(), "Tên phòng không khớp.");
        assertEquals(phong.getSoLuongGhe(), phongDaTao.getSoLuongGhe(), "Số lượng ghế không khớp.");
    }

    @Test
    void testCapNhatPhong() {
        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        phong.setTenPhong("Phòng 10");
        phong.setSoLuongGhe(208);

        boolean daCapNhatPhong = phongDAO.capNhatPhong(phong);
        assertTrue(daCapNhatPhong, "Lỗi không cập nhật được phòng");

        Phong phongCapNhat = phongDAO.timTheoMaPhong(phong.getMaPhong());

        assertNotNull(phongCapNhat, "Không tìm thấy phòng sau khi cập nhật");
        assertEquals("Phòng 10", phongCapNhat.getTenPhong(), "Tên phòng không khớp.");
        assertEquals(208, phongCapNhat.getSoLuongGhe(), "Số lượng ghế không khớp.");
    }

    @Test
    void testXoaPhong() {
        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        boolean daXoaPhong = phongDAO.xoaPhong(phong.getMaPhong());
        assertTrue(daXoaPhong, "Lỗi không xóa được phòng");

        Phong phongDaXoa = phongDAO.timTheoMaPhong(phong.getMaPhong());
        assertNull(phongDaXoa, "Phòng vẫn còn tồn tại.");
    }
}
