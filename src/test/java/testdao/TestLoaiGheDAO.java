package testdao;

import dao.LoaiGheDAO;
import entity.LoaiGhe;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoaiGheDAO {

    private LoaiGheDAO loaiGheDAO;
    private LoaiGhe loaiGhe;

    @BeforeEach
    void setUp() {
        loaiGheDAO = new LoaiGheDAO();
        loaiGhe = new LoaiGhe();
        Faker faker = new Faker();

        String[] tenLoaiGhe = {"Ghế thường", "Ghế VIP", "Ghế đôi SweetBox"};
        loaiGhe.setTenLoaiGhe(tenLoaiGhe[faker.random().nextInt(3)]);
        loaiGhe.setMoTaLoaiGhe(faker.lorem().sentence(20));
    }

    @Test
    void testThemLoaiGhe() {
        boolean daTaoLoaiGhe = loaiGheDAO.themLoaiGhe(loaiGhe);
        assertTrue(daTaoLoaiGhe, "Lỗi không thêm được loại ghế");

        LoaiGhe loaiGheDaTao = loaiGheDAO.timTheoMaLoaiGhe(loaiGhe.getMaLoaiGhe());

        assertNotNull(loaiGheDaTao, "Không tìm thấy loại ghế");
        assertEquals(loaiGhe.getMaLoaiGhe(), loaiGheDaTao.getMaLoaiGhe(), "Mã loại ghế không khớp.");
        assertEquals(loaiGhe.getTenLoaiGhe(), loaiGheDaTao.getTenLoaiGhe(), "Tên loại ghế không khớp.");
        assertEquals(loaiGhe.getMoTaLoaiGhe(), loaiGheDaTao.getMoTaLoaiGhe(), "Mô tả loại ghế không khớp.");
    }

    @Test
    void testCapNhatLoaiGhe() {
        boolean daTaoLoaiGhe = loaiGheDAO.themLoaiGhe(loaiGhe);
        assertTrue(daTaoLoaiGhe, "Lỗi không thêm được loại ghế");

        loaiGhe.setTenLoaiGhe("Ghế VIP cao cấp");
        loaiGhe.setMoTaLoaiGhe("Ghế loại VIP, thoải mái và sang trọng.");

        boolean daCapNhatLoaiGhe = loaiGheDAO.capNhatLoaiGhe(loaiGhe);
        assertTrue(daCapNhatLoaiGhe, "Lỗi không cập nhật được loại ghế");

        LoaiGhe loaiGheCapNhat = loaiGheDAO.timTheoMaLoaiGhe(loaiGhe.getMaLoaiGhe());

        assertNotNull(loaiGheCapNhat, "Không tìm thấy loại ghế sau khi cập nhật");
        assertEquals("Ghế VIP cao cấp", loaiGheCapNhat.getTenLoaiGhe(), "Tên loại ghế không khớp.");
        assertEquals("Ghế loại VIP, thoải mái và sang trọng.", loaiGheCapNhat.getMoTaLoaiGhe(), "Mô tả loại ghế không khớp.");
    }

    @Test
    void testXoaLoaiGhe() {
        boolean daTaoLoaiGhe = loaiGheDAO.themLoaiGhe(loaiGhe);
        assertTrue(daTaoLoaiGhe, "Lỗi không thêm được loại ghế");

        boolean daXoaLoaiGhe = loaiGheDAO.xoaLoaiGhe(loaiGhe.getMaLoaiGhe());
        assertTrue(daXoaLoaiGhe, "Lỗi không xóa được loại ghế");

        LoaiGhe loaiGheDaXoa = loaiGheDAO.timTheoMaLoaiGhe(loaiGhe.getMaLoaiGhe());
        assertNull(loaiGheDaXoa, "Loại ghế vẫn còn tồn tại.");
    }
}
