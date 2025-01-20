package dao;

import entity.Phim;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class PhongDAO {

    private EntityManager em;

    public PhongDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themPhong(Phong phong) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(phong);
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

    public Phong timTheoMaPhong(int id) {
        return em.find(Phong.class, id);
    }

    public ArrayList<Phong> getDanhSachPhong() {
        try {
            return (ArrayList<Phong>) em.createQuery("SELECT p FROM Phong p", Phong.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatPhong(Phong phong) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Phong updatedPhong = em.merge(phong);
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

    public boolean xoaPhong(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Phong Phong = em.find(Phong.class, id);
            if (Phong != null) {
                em.remove(Phong);
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
