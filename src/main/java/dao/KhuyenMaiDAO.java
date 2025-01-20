package dao;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class KhuyenMaiDAO {

    private EntityManager em;

    public KhuyenMaiDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(khuyenMai);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public KhuyenMai timTheoMaKhuyenMai(int id) {
        return em.find(KhuyenMai.class, id);
    }

    public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMai updatedKhuyenMai = em.merge(khuyenMai);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaKhuyenMai(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMai KhuyenMai = em.find(KhuyenMai.class, id);
            if (KhuyenMai != null) {
                em.remove(KhuyenMai);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
