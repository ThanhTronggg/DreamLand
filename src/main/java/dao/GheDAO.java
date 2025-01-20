package dao;

import entity.Ghe;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class GheDAO {

    private EntityManager em;

    public GheDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themGhe(Ghe ghe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(ghe);
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

    public Ghe timTheoMaGhe(int id) {
        return em.find(Ghe.class, id);
    }

    public boolean capNhatGhe(Ghe ghe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ghe updatedGhe = em.merge(ghe);
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

    public Ghe timTheoViTri(String viTri) {
        String query = "SELECT g FROM Ghe g WHERE g.viTri = :viTri";
        try {
            return em.createQuery(query, Ghe.class)
                    .setParameter("viTri", viTri)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Ghe> getDanhSachGheTheoPhong(Phong phong) {
        try {
            return (ArrayList<Ghe>) em.createQuery("SELECT p FROM Ghe p where p.phong = :phong", Ghe.class)
                    .setParameter("phong", phong).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean xoaGhe(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Ghe Ghe = em.find(Ghe.class, id);
            if (Ghe != null) {
                em.remove(Ghe);
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
