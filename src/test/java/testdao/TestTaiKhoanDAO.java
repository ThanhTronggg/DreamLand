package testdao;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.ZoneId;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestTaiKhoanDAO {

    private TaiKhoanDAO taiKhoanDAO;
    private TaiKhoan taiKhoan;
    private NhanVienDAO nhanVienDAO;
    private NhanVien nhanVien;

    @BeforeEach
    void setUp() {
        taiKhoanDAO = new TaiKhoanDAO();
        taiKhoan = new TaiKhoan();
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

        taiKhoan.setTaiKhoan(faker.name().username());
        taiKhoan.setMatKhau(faker.internet().password());
        taiKhoan.setNhanVien(nhanVien);
    }

    @Test
    void testThemTaiKhoan() {
        boolean daTaoTaiKhoan = taiKhoanDAO.themTaiKhoan(taiKhoan);
        assertTrue(daTaoTaiKhoan, "Lỗi không thêm được tài khoản");

        TaiKhoan taiKhoanDaTao = taiKhoanDAO.timTheoMaTaiKhoan(taiKhoan.getId());

        assertNotNull(taiKhoanDaTao, "Không tìm thấy tài khoản");
        assertEquals(taiKhoan.getTaiKhoan(), taiKhoanDaTao.getTaiKhoan(), "Tên tài khoản không khớp.");
        assertEquals(taiKhoan.getMatKhau(), taiKhoanDaTao.getMatKhau(), "Mật khẩu không khớp.");
        assertEquals(taiKhoan.getNhanVien(), taiKhoanDaTao.getNhanVien(), "Nhân viên không khớp.");
    }

    @Test
    void testCapNhatTaiKhoan() {
        boolean daTaoTaiKhoan = taiKhoanDAO.themTaiKhoan(taiKhoan);
        assertTrue(daTaoTaiKhoan, "Lỗi không thêm được tài khoản");

        taiKhoan.setTaiKhoan("thanhtronggg");
        taiKhoan.setMatKhau("password12345");

        boolean daCapNhatTaiKhoan = taiKhoanDAO.capNhatTaiKhoan(taiKhoan);
        assertTrue(daCapNhatTaiKhoan, "Lỗi không cập nhật được tài khoản");

        TaiKhoan taiKhoanCapNhat = taiKhoanDAO.timTheoMaTaiKhoan(taiKhoan.getId());

        assertNotNull(taiKhoanCapNhat, "Không tìm thấy tài khoản sau khi cập nhật");
        assertEquals("thanhtronggg", taiKhoanCapNhat.getTaiKhoan(), "Tên tài khoản không khớp.");
        assertEquals("password12345", taiKhoanCapNhat.getMatKhau(), "Mật khẩu không khớp.");
    }
}
