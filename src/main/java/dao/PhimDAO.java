package dao;

import entity.Phim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class PhimDAO {

    private EntityManager em;

    public PhimDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themPhim(Phim phim) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(phim);
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

    public Phim timTheoMaPhim(int id) {
        return em.find(Phim.class, id);
    }

    public ArrayList<Phim> getDanhSachPhim() {
        try {
            return (ArrayList<Phim>) em.createQuery("SELECT p FROM Phim p", Phim.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatPhim(Phim phim) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Phim updatedPhim = em.merge(phim);
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

    public boolean xoaPhim(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Phim Phim = em.find(Phim.class, id);
            if (Phim != null) {
                em.remove(Phim);
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
