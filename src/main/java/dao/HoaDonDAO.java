package dao;

import entity.Phim;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class HoaDonDAO {

    private EntityManager em;

    public HoaDonDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(hoaDon);
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

    public HoaDon timTheoMaHoaDon(int id) {
        return em.find(HoaDon.class, id);
    }

    public ArrayList<HoaDon> getDanhSachHoaDon() {
        try {
            return (ArrayList<HoaDon>) em.createQuery("SELECT p FROM HoaDon p", HoaDon.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatHoaDon(HoaDon hoaDon) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            HoaDon updatedHoaDon = em.merge(hoaDon);
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

    public boolean xoaHoaDon(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            HoaDon HoaDon = em.find(HoaDon.class, id);
            if (HoaDon != null) {
                em.remove(HoaDon);
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
