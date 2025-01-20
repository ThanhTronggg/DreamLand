package testdao;

import dao.GheDAO;
import dao.LoaiGheDAO;
import dao.PhongDAO;
import entity.Ghe;
import entity.LoaiGhe;
import entity.Phong;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestGheDAO {

    private GheDAO gheDAO;
    private Phong phong;
    private PhongDAO phongDAO;
    private LoaiGheDAO loaiGheDAO;

    @BeforeEach
    void setUp() {
        gheDAO = new GheDAO();
        phongDAO = new PhongDAO();
        loaiGheDAO = new LoaiGheDAO();
        Faker faker = new Faker();
        phong = new Phong();

        phong.setTenPhong("Phòng " + faker.random().nextInt(10));
        phong.setSoLuongGhe(192);

        String[] tenLoaiGhe = {"Ghế thường", "Ghế VIP", "Ghế đôi SweetBox"};
        LoaiGhe loaiGhe;
        for (String ten : tenLoaiGhe) {
            loaiGhe = new LoaiGhe();
            loaiGhe.setTenLoaiGhe(ten);
            loaiGhe.setMoTaLoaiGhe(faker.lorem().sentence(20));
            loaiGheDAO.themLoaiGhe(loaiGhe);
        }
    }

    @Test
    void testThemGhe() {
        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        for (char row = 'A'; row <= 'M'; row++) {
            for (int col = 1; col <= 16; col++) {
                Ghe ghe = new Ghe();
                String viTri = String.format("%c%02d", row, col);

                if (row >= 'A' && row <= 'D') {
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế thường"));
                } else if (row >= 'E' && row <= 'L') {
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế VIP"));
                } else if (row == 'M' && col % 2 == 1) {
                    String viTriDoi = String.format("%c%02d-%02d", row, col, col + 1);
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế đôi SweetBox"));
                    ghe.setViTri(viTriDoi);
                }

                ghe.setPhong(phong);
                ghe.setViTri(viTri);
                gheDAO.themGhe(ghe);
            }
        }
    }

    @Test
    void testXoaGhe() {
        boolean daTaoPhong = phongDAO.themPhong(phong);
        assertTrue(daTaoPhong, "Lỗi không thêm được phòng");

        for (char row = 'A'; row <= 'M'; row++) {
            for (int col = 1; col <= 16; col++) {
                Ghe ghe = new Ghe();
                String viTri = String.format("%c%02d", row, col);

                if (row >= 'A' && row <= 'D') {
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế thường"));
                } else if (row >= 'E' && row <= 'L') {
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế VIP"));
                } else if (row == 'M' && col % 2 == 1) {
                    String viTriDoi = String.format("%c%02d-%02d", row, col, col + 1);
                    ghe.setLoaiGhe(loaiGheDAO.timTheoTenLoaiGhe("Ghế đôi SweetBox"));
                    ghe.setViTri(viTriDoi);
                }

                ghe.setPhong(phong);
                ghe.setViTri(viTri);
                gheDAO.themGhe(ghe);
            }
        }
        for (char row = 'A'; row <= 'M'; row++) {
            for (int col = 1; col <= 16; col++) {
                String viTri = String.format("%c%02d", row, col);
                Ghe gheDaTao = gheDAO.timTheoViTri(viTri);
                boolean daXoaGhe = gheDAO.xoaGhe(gheDaTao.getMaGhe());
                assertTrue(daXoaGhe, "Lỗi không xóa được ghế tại vị trí: " + viTri);

                Ghe gheDaXoa = gheDAO.timTheoViTri(viTri);
                assertNull(gheDaXoa, "Ghế vẫn còn tồn tại sau khi xóa.");
            }
        }
    }
}
