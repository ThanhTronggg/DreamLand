package dao;

import entity.LichChieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class LichChieuDAO {

    private EntityManager em;

    public LichChieuDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themLichChieu(LichChieu lichChieu) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(lichChieu);
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

    public LichChieu timTheoMaLichChieu(int id) {
        return em.find(LichChieu.class, id);
    }

    public ArrayList<LichChieu> getDanhSachLichChieu() {
        try {
            return (ArrayList<LichChieu>) em.createQuery("SELECT p FROM LichChieu p", LichChieu.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatLichChieu(LichChieu lichChieu) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            LichChieu updatedLichChieu = em.merge(lichChieu);
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

    public boolean xoaLichChieu(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            LichChieu LichChieu = em.find(LichChieu.class, id);
            if (LichChieu != null) {
                em.remove(LichChieu);
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
