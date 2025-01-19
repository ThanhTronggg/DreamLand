import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();

        EntityTransaction tr = em.getTransaction();

        Faker faker = new Faker();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenKhachHang(faker.name().fullName());
            khachHang.setSoDienThoai(faker.phoneNumber().cellPhone());
            khachHang.setEmail(faker.internet().emailAddress());
            khachHang.setHoaDons(new HashSet<>());

            NhanVien nhanVien = new NhanVien();
            nhanVien.setHoTen(faker.name().fullName());
            nhanVien.setGioiTinh(faker.bool().bool());
            nhanVien.setNgaySinh(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());  // Corrected line
            nhanVien.setEmail(faker.internet().emailAddress());
            nhanVien.setSoDienThoai(faker.phoneNumber().phoneNumber());
            nhanVien.setVaiTro(faker.job().title());
            nhanVien.setNgayBatDauLam(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            nhanVien.setLuong(faker.number().randomDouble(2, 5000, 15000));
            nhanVien.setTrangThai(faker.options().option("Active", "Inactive"));
            nhanVien.setHoaDons(new HashSet<>());

            int hoaDonCount = faker.number().numberBetween(1, 4);
            for (int k = 0; k < hoaDonCount; k++) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNgayDat(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
                hoaDon.setSoGhe(faker.number().numberBetween(1, 100));
                hoaDon.setGhiChu(faker.lorem().sentence());
                hoaDon.setKhachHang(khachHang);
                hoaDon.setNhanVien(nhanVien);

                em.persist(hoaDon);
            }
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTaiKhoan(faker.name().username());
            taiKhoan.setMatKhau(faker.internet().password());
            taiKhoan.setNhanVien(nhanVien);

            tr.begin();

            em.persist(khachHang);
            em.persist(nhanVien);
            em.persist(taiKhoan);

            tr.commit();

        }
    }
}

