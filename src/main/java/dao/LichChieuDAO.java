package dao;

import entity.LichChieu;
import entity.Phim;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LichChieuDAO extends GenericDAO<LichChieu, String>{


    public LichChieuDAO(EntityManager em, Class<LichChieu> cls) {
        super(em, cls);
    }

    public LichChieuDAO(Class<LichChieu> cls) {
        super(cls);
    }

    public ArrayList<LichChieu> getLichChieuTheoNgay(LocalDate ngay) {
        EntityManager em = JPAUtil.getEntityManager();
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        try {
            String query = "SELECT lc FROM LichChieu lc WHERE FUNCTION('DATE', lc.gioBatDau) = :ngay";
            List<LichChieu> results = em.createQuery(query, LichChieu.class)
                    .setParameter("ngay", ngay)
                    .getResultList();
            dsLichChieu.addAll(results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dsLichChieu;
    }

    public ArrayList<LichChieu> getLichChieuTheoPhong(String maPhong) {
        EntityManager em = JPAUtil.getEntityManager();
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        try {
            String query = "SELECT lc FROM LichChieu lc WHERE lc.phong.maPhong = :maPhong";
            List<LichChieu> results = em.createQuery(query, LichChieu.class)
                    .setParameter("maPhong", maPhong)
                    .getResultList();
            dsLichChieu.addAll(results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dsLichChieu;
    }

    public static void main(String[] args) {
        LichChieuDAO lichChieuDAO = new LichChieuDAO(LichChieu.class);

//        // Test timLichChieuTheoMa
//        System.out.println("=== Kiểm tra timLichChieuTheoMa ===");
//        LichChieu lichChieu = lichChieuDAO.findById(1);
//        System.out.println("Lịch chiếu LC001: " + (lichChieu != null ? lichChieu : "Không tìm thấy"));

//        // Test getLichChieuTheoNgay
//        System.out.println("\n=== Kiểm tra getLichChieuTheoNgay ===");
//        LocalDate ngay = LocalDate.of(2025, 5, 14);
//        ArrayList<LichChieu> dsLichChieuNgay = lichChieuDAO.getLichChieuTheoNgay(ngay);
//        System.out.println("Lịch chiếu ngày " + ngay + ": " + dsLichChieuNgay.size() + " lịch chiếu");
//        dsLichChieuNgay.forEach(System.out::println);

//        // Test getTatCaLichChieu
//        System.out.println("\n=== Kiểm tra getTatCaLichChieu ===");
//        List<LichChieu> dsTatCaLichChieu = lichChieuDAO.getAll();
//        System.out.println("Tất cả lịch chiếu: " + dsTatCaLichChieu.size() + " lịch chiếu");
//        dsTatCaLichChieu.forEach(System.out::println);


        // Test getLichChieuTheoPhong
        System.out.println("\n=== Kiểm tra getLichChieuTheoPhong ===");
        ArrayList<LichChieu> dsLichChieuPhong = lichChieuDAO.getLichChieuTheoPhong(String.valueOf(1));
        System.out.println("Lịch chiếu phòng P001: " + dsLichChieuPhong.size() + " lịch chiếu");
        dsLichChieuPhong.forEach(System.out::println);

        // Đóng EntityManagerFactory
        JPAUtil.close();
    }
}
