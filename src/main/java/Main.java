import entity.KhuyenMai;
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

        for (int i = 0; i < 100; i++) {
            KhuyenMai km = new KhuyenMai();
            km.setTenKhuyenMai(faker.options().option(events));
            km.setNgayBatDau(LocalDate.now().minusDays(faker.random().nextInt(1, 30)));
            km.setNgayKetThuc(LocalDate.now().plusDays(faker.random().nextInt(1, 30)));
            km.setPhamTramKhuyenMai(faker.random().nextDouble(0.1, 0.5));
            km.setTongHoaDonToiThieu(faker.random().nextDouble(100000, 2000000));
            tr.begin();
            em.persist(km);
            tr.commit();
        }
    }
}
