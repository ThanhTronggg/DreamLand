package dao;

import entity.Phim;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class ChiTietHoaDonDAO {

    private EntityManager em;

    public ChiTietHoaDonDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(chiTietHoaDon);
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

    public ChiTietHoaDon timTheoMaChiTietHoaDon(int id) {
        return em.find(ChiTietHoaDon.class, id);
    }

    public ArrayList<ChiTietHoaDon> getDanhSachChiTietHoaDon() {
        try {
            return (ArrayList<ChiTietHoaDon>) em.createQuery("SELECT p FROM ChiTietHoaDon p", ChiTietHoaDon.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ChiTietHoaDon updatedChiTietHoaDon = em.merge(chiTietHoaDon);
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
}
