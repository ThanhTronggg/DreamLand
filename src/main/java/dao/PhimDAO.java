package dao;

import dao.GenericDAO;
import entity.Phim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PhimDAO extends GenericDAO<Phim, String> {


    public PhimDAO(EntityManager em, Class<Phim> cls) {
        super(em, cls);
    }

    public PhimDAO(Class<Phim> cls) {
        super(cls);
    }

    public boolean exists(String maPhim) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String query = "SELECT COUNT(p) FROM Phim p WHERE p.maPhim = :maPhim";
            Long count = em.createQuery(query, Long.class)
                    .setParameter("maPhim", maPhim)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public String getNextMaPhim() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String query = "SELECT MAX(p.maPhim) FROM Phim p";
            Integer maxMaPhim = em.createQuery(query, Integer.class)
                    .getSingleResult();
            int nextValue = (maxMaPhim == null) ? 1 : maxMaPhim + 1;
            return String.valueOf(nextValue);
        } catch (NoResultException e) {
            return "P001"; // Bảng rỗng
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        PhimDAO phimDAO = new PhimDAO(Phim.class);

//        // Test exists
//        System.out.println("=== Kiểm tra exists ===");
//        String maPhim = "1";
//        boolean exists = phimDAO.exists(maPhim);
//        System.out.println("Phim " + maPhim + " tồn tại: " + (exists ? "Có" : "Không"));

        // Test getNextMaPhim
        System.out.println("\n=== Kiểm tra getNextMaPhim ===");
        String nextMaPhim = phimDAO.getNextMaPhim();
        System.out.println("Mã phim tiếp theo: " + (nextMaPhim != null ? nextMaPhim : "Thất bại"));

        // Đóng EntityManagerFactory
        JPAUtil.close();
    }
}
