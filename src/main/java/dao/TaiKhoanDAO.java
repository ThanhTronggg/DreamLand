package dao;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TaiKhoanDAO {

    private EntityManager em;

    public TaiKhoanDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(taiKhoan);
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

    public TaiKhoan timTheoMaTaiKhoan(int id) {
        return em.find(TaiKhoan.class, id);
    }

    public boolean capNhatTaiKhoan(TaiKhoan taiKhoan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TaiKhoan updatedTaiKhoan = em.merge(taiKhoan);
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

    public boolean xoaTaiKhoan(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TaiKhoan TaiKhoan = em.find(TaiKhoan.class, id);
            if (TaiKhoan != null) {
                em.remove(TaiKhoan);
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
