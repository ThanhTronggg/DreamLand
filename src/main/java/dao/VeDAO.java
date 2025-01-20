package dao;

import entity.Phim;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class VeDAO {

    private EntityManager em;

    public VeDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themVe(Ve ve) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(ve);
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

    public Ve timTheoMaVe(int id) {
        return em.find(Ve.class, id);
    }

    public ArrayList<Ve> getDanhSachVe() {
        try {
            return (ArrayList<Ve>) em.createQuery("SELECT p FROM Ve p", Ve.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatVe(Ve ve) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ve updatedVe = em.merge(ve);
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

    public boolean xoaVe(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ve Ve = em.find(Ve.class, id);
            if (Ve != null) {
                em.remove(Ve);
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
