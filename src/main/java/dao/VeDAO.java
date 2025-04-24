package dao;

import entity.Ghe;
import entity.HoaDon;
import entity.LichChieu;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VeDAO extends GenericDAO<Ve, Integer>{
    public VeDAO(EntityManager em, Class<Ve> cls) {
        super(em, cls);
    }

    public VeDAO(Class<Ve> cls) {
        super(cls);
    }

    public ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) {
        EntityManager em = JPAUtil.getEntityManager();
        ArrayList<Ve> danhSachVe = new ArrayList<>();
        try {
            String query = "SELECT v FROM Ve v WHERE v.lichChieu.maLichChieu = :maLichChieu";
            List<Ve> results = em.createQuery(query, Ve.class)
                    .setParameter("maLichChieu", lc.getMaLichChieu())
                    .getResultList();
            danhSachVe.addAll(results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return danhSachVe;
    }


    public static void main(String[] args) {
        VeDAO veDAO = new VeDAO(Ve.class);
        GheDAO gheDAO = new GheDAO(Ghe.class);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(HoaDon.class);
        LichChieuDAO lichChieuDAO = new LichChieuDAO(LichChieu.class);

        // Test getVeTheoLichChieu
        System.out.println("=== Kiểm tra getVeTheoLichChieu ===");
        LichChieu lichChieu = lichChieuDAO.findById(22); // Giả định lịch chiếu tồn tại
        ArrayList<Ve> dsVe = veDAO.getVeTheoLichChieu(lichChieu);
        System.out.println("Vé cho lịch chiếu LC001: " + dsVe.size() + " vé");
        dsVe.forEach(System.out::println);

        // Đóng EntityManagerFactory
        JPAUtil.close();
    }
}
