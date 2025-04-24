package testdao;

import dao.SanPhamDAO;
import entity.SanPham;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class TestSanPhamDAO {

    private SanPhamDAO sanPhamDAO;
    private SanPham sanPham;

    @BeforeEach
    void setUp() {
        sanPhamDAO = new SanPhamDAO();
        sanPham = new SanPham();
        Faker faker = new Faker();

        String[] foods = {"Bắp rang bơ cỡ nhỏ", "Bắp rang bơ cỡ lớn", "X2 Bắp", "Combo x3 bắp"};
        String[] drinks = {"Cocacola", "7up", "Pepsi", "Sting"};
        sanPham.setLoaiSanPham(faker.options().option("Đồ ăn", "Nước uống"));
        if ("Đồ ăn".equals(sanPham.getLoaiSanPham())) {
            sanPham.setTenSanPham(faker.options().option(foods));
        } else {
            sanPham.setTenSanPham(faker.options().option(drinks));
        }
        sanPham.setSoLuong(faker.random().nextInt(1, 100));
        sanPham.setGiaMua(faker.random().nextDouble(10000, 30000));
        sanPham.setGiaBan(sanPham.getGiaMua()*1.5);
        sanPham.setAnh(faker.internet().url());
    }

    @Test
    void testThemSanPham() {
        boolean daTaoSanPham = sanPhamDAO.themSanPham(sanPham);
        assertTrue(daTaoSanPham, "Lỗi không thêm được sản phẩm");

        SanPham sanPhamDaTao = sanPhamDAO.timTheoMaSanPham(sanPham.getMaSanPham());

        assertNotNull(sanPhamDaTao, "Không tìm thấy sản phẩm");
        assertEquals(sanPham.getMaSanPham(), sanPhamDaTao.getMaSanPham(), "Mã sản phẩm không khớp.");
        assertEquals(sanPham.getTenSanPham(), sanPhamDaTao.getTenSanPham(), "Tên sản phẩm không khớp.");
        assertEquals(sanPham.getLoaiSanPham(), sanPhamDaTao.getLoaiSanPham(), "Loại sản phẩm không khớp.");
        assertEquals(sanPham.getSoLuong(), sanPhamDaTao.getSoLuong(), "Số lượng không khớp.");
        assertEquals(sanPham.getGiaMua(), sanPhamDaTao.getGiaMua(), "Giá mua không khớp.");
        assertEquals(sanPham.getGiaBan(), sanPhamDaTao.getGiaBan(), "Giá bán không khớp.");
        assertEquals(sanPham.getAnh(), sanPhamDaTao.getAnh(), "Ảnh không khớp.");
    }

    @Test
    void testCapNhatSanPham() {
        boolean daTaoSanPham = sanPhamDAO.themSanPham(sanPham);
        assertTrue(daTaoSanPham, "Lỗi không thêm được sản phẩm");

        sanPham.setTenSanPham("Nước cam ép");
        sanPham.setGiaBan(35000.0);

        boolean daCapNhatSanPham = sanPhamDAO.capNhatSanPham(sanPham);
        assertTrue(daCapNhatSanPham, "Lỗi không cập nhật được sản phẩm");

        SanPham sanPhamCapNhat = sanPhamDAO.timTheoMaSanPham(sanPham.getMaSanPham());

        assertNotNull(sanPhamCapNhat, "Không tìm thấy sản phẩm sau khi cập nhật");
        assertEquals("Nước cam ép", sanPhamCapNhat.getTenSanPham(), "Tên sản phẩm không khớp.");
        assertEquals(35000.0, sanPhamCapNhat.getGiaBan(), "Giá bán không khớp.");
    }

    @Test
    void testXoaSanPham() {
        boolean daTaoSanPham = sanPhamDAO.themSanPham(sanPham);
        assertTrue(daTaoSanPham, "Lỗi không thêm được sản phẩm");

        boolean daXoaSanPham = sanPhamDAO.xoaSanPham(sanPham.getMaSanPham());
        assertTrue(daXoaSanPham, "Lỗi không xóa được sản phẩm");

        SanPham sanPhamDaXoa = sanPhamDAO.timTheoMaSanPham(sanPham.getMaSanPham());
        assertNull(sanPhamDaXoa, "Sản phẩm vẫn còn tồn tại.");
    }
}
