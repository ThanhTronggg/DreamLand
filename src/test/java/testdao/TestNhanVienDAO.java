package testdao;

import dao.NhanVienDAO;
import entity.NhanVien;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestNhanVienDAO {

    private NhanVienDAO nhanVienDAO;
    private NhanVien nhanVien;

    @BeforeEach
    void setUp() {
        nhanVienDAO = new NhanVienDAO();
        nhanVien = new NhanVien();
        Faker faker = new Faker();
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
    }

    @Test
    void testThemNhanVien() {
        boolean daTaoNhanVien = nhanVienDAO.themNhanVien(nhanVien);
        assertTrue(daTaoNhanVien, "Lỗi không thêm được nhân viên");

        NhanVien nhanVienDaTao = nhanVienDAO.timTheoMaNhanVien(nhanVien.getMaNhanVien());

        assertNotNull(nhanVienDaTao, "Không tìm thấy nhân viên");
        assertEquals(nhanVien.getMaNhanVien(), nhanVienDaTao.getMaNhanVien(), "Lỗi khi tạo: Mã nhân viên không khớp.");
        assertEquals(nhanVien.getHoTen(), nhanVienDaTao.getHoTen(), "Lỗi khi tạo: Họ tên không khớp.");
        assertEquals(nhanVien.getNgaySinh(), nhanVienDaTao.getNgaySinh(), "Lỗi khi tạo: Ngày sinh không khớp.");
        assertEquals(nhanVien.getEmail(), nhanVienDaTao.getEmail(), "Lỗi khi tạo: Email không khớp.");
        assertEquals(nhanVien.getSoDienThoai(), nhanVienDaTao.getSoDienThoai(), "Lỗi khi tạo: Số điện thoại không khớp.");
        assertEquals(nhanVien.getVaiTro(), nhanVienDaTao.getVaiTro(), "Lỗi khi tạo: Vai trò không khớp.");
        assertEquals(nhanVien.getNgayBatDauLam(), nhanVienDaTao.getNgayBatDauLam(), "Lỗi khi tạo: Ngày bắt đầu làm không khớp.");
        assertEquals(nhanVien.getLuong(), nhanVienDaTao.getLuong(), "Lỗi khi tạo: Lương không khớp.");
        assertEquals(nhanVien.getTrangThai(), nhanVienDaTao.getTrangThai(), "Lỗi khi tạo: Trạng thái không khớp.");
    }

    @Test
    void testCapNhatNhanVien() {
        boolean daTaoNhanVien = nhanVienDAO.themNhanVien(nhanVien);
        assertTrue(daTaoNhanVien, "Lỗi không thêm được nhân viên");

        nhanVien.setHoTen("Nguyễn Thành Trọng");
        nhanVien.setEmail("22642481.trong@student.iuh.edu.vn");
        nhanVien.setSoDienThoai("0328546227");
        nhanVien.setVaiTro("Nhân viên bán vé");
        nhanVien.setLuong(20000.0);

        boolean daCapNhatNhanVien = nhanVienDAO.capNhatNhanVien(nhanVien);
        assertTrue(daCapNhatNhanVien, "Lỗi không cập nhật được nhân viên");

        NhanVien nhanVienCapNhat = nhanVienDAO.timTheoMaNhanVien(nhanVien.getMaNhanVien());

        assertNotNull(nhanVienCapNhat, "Không tìm thấy nhân viên sau khi cập nhật");

        assertEquals("Nguyễn Thành Trọng", nhanVienCapNhat.getHoTen(), "Lỗi khi cập nhật: Họ tên không khớp.");
        assertEquals("22642481.trong@student.iuh.edu.vn", nhanVienCapNhat.getEmail(), "Lỗi khi cập nhật: Email không khớp.");
        assertEquals("0328546227", nhanVienCapNhat.getSoDienThoai(), "Lỗi khi cập nhật: Số điện thoại không khớp.");
        assertEquals("Nhân viên bán vé", nhanVienCapNhat.getVaiTro(), "Lỗi khi cập nhật: Vai trò không khớp.");
        assertEquals(20000.0, nhanVienCapNhat.getLuong(), "Lỗi khi cập nhật: Lương không khớp.");
    }
}
