package testdao;

import dao.KhachHangDAO;
import entity.KhachHang;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestKhachHangDAO {

    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;

    @BeforeEach
    void setUp() {
        khachHangDAO = new KhachHangDAO();
        khachHang = new KhachHang();
        Faker faker = new Faker();

        khachHang.setTenKhachHang(faker.name().fullName());
        khachHang.setSoDienThoai(faker.phoneNumber().cellPhone());
        khachHang.setEmail(faker.internet().emailAddress());
        khachHang.setHoaDons(new HashSet<>());
    }

    @Test
    void testThemKhachHang() {
        boolean daTaoKhachHang = khachHangDAO.themKhachHang(khachHang);
        assertTrue(daTaoKhachHang, "Lỗi không thêm được khách hàng");

        KhachHang khachHangDaTao = khachHangDAO.timTheoMaKhachHang(khachHang.getMaKhachHang());

        assertNotNull(khachHangDaTao, "Không tìm thấy khách hàng");
        assertEquals(khachHang.getMaKhachHang(), khachHangDaTao.getMaKhachHang(), "Lỗi khi tạo: Mã khách hàng không khớp.");
        assertEquals(khachHang.getTenKhachHang(), khachHangDaTao.getTenKhachHang(), "Lỗi khi tạo: Tên khách hàng không khớp.");
        assertEquals(khachHang.getSoDienThoai(), khachHangDaTao.getSoDienThoai(), "Lỗi khi tạo: Số điện thoại không khớp.");
        assertEquals(khachHang.getEmail(), khachHangDaTao.getEmail(), "Lỗi khi tạo: Email không khớp.");
    }

    @Test
    void testCapNhatKhachHang() {
        boolean daTaoKhachHang = khachHangDAO.themKhachHang(khachHang);
        assertTrue(daTaoKhachHang, "Lỗi không thêm được khách hàng");

        khachHang.setTenKhachHang("Nguyễn Thành Trọng");
        khachHang.setSoDienThoai("0328546227");
        khachHang.setEmail("22642481.trong@student.iuh.edu.vn");

        boolean daCapNhatKhachHang = khachHangDAO.capNhatKhachHang(khachHang);
        assertTrue(daCapNhatKhachHang, "Lỗi không cập nhật được khách hàng");

        KhachHang khachHangCapNhat = khachHangDAO.timTheoMaKhachHang(khachHang.getMaKhachHang());

        assertNotNull(khachHangCapNhat, "Không tìm thấy khách hàng sau khi cập nhật");

        assertEquals("Nguyễn Thành Trọng", khachHangCapNhat.getTenKhachHang(), "Lỗi khi cập nhật: Tên khách hàng không khớp.");
        assertEquals("0328546227", khachHangCapNhat.getSoDienThoai(), "Lỗi khi cập nhật: Số điện thoại không khớp.");
        assertEquals("22642481.trong@student.iuh.edu.vn", khachHangCapNhat.getEmail(), "Lỗi khi cập nhật: Email không khớp.");
    }
}
