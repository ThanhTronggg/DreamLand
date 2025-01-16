import entity.KhuyenMai;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Random;

public class Main{
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
        EntityTransaction tr = em.getTransaction();

        Faker faker = new Faker();
        Random rand = new Random();

        String[] events = {
                "Tết Nguyên Đán",
                "Giáng Sinh",
                "Black Friday",
                "Valentine",
                "Lễ Quốc Khánh",
                "Halloween",
                "Lễ Phục Sinh"
        };
        String[] foods = {"Bắp rang bơ cỡ nhỏ", "Bắp rang bơ cỡ lớn", "X2 Bắp", "Combo x3 bắp"};
        String[] drinks = {"Cocacola", "7up", "Pepsi", "Sting"};
        for (int i = 0; i < 100; i++) {
            KhuyenMai km = new KhuyenMai();
            SanPham sp = new SanPham();

            km.setTenKhuyenMai(faker.options().option(events));
            km.setNgayBatDau(LocalDate.now().minusDays(faker.random().nextInt(1, 30)));
            km.setNgayKetThuc(LocalDate.now().plusDays(faker.random().nextInt(1, 30)));
            km.setPhamTramKhuyenMai(faker.random().nextDouble(0.1, 0.5));
            km.setTongHoaDonToiThieu(faker.random().nextDouble(100000, 2000000));

            sp.setLoaiSanPham(faker.options().option("Đồ ăn", "Nước uống"));
            if ("Đồ ăn".equals(sp.getLoaiSanPham())) {
                sp.setTenSanPham(faker.options().option(foods));
            } else {
                sp.setTenSanPham(faker.options().option(drinks));
            }
            sp.setSoLuong(faker.random().nextInt(1, 100));
            sp.setGiaMua(faker.random().nextDouble(10000, 30000));
            sp.setGiaBan(sp.getGiaMua()*1.5);
            sp.setAnh(faker.internet().url());
            
            tr.begin();
            em.persist(sp);
            em.persist(km);
            tr.commit();
        }
    }
}
