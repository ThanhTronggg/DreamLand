package dao;

import entity.LoaiGhe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class LoaiGheDAO {

    private EntityManager em;

    public LoaiGheDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themLoaiGhe(LoaiGhe loaiGhe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loaiGhe);
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

    public LoaiGhe timTheoMaLoaiGhe(int id) {
        return em.find(LoaiGhe.class, id);
    }

    public LoaiGhe timTheoTenLoaiGhe(String ten) {
        String jpql = "SELECT l FROM LoaiGhe l WHERE l.tenLoaiGhe = :ten";
        try {
            return em.createQuery(jpql, LoaiGhe.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;  // Nếu không tìm thấy, trả về null.
        }
    }

    public boolean capNhatLoaiGhe(LoaiGhe loaiGhe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            LoaiGhe updatedLoaiGhe = em.merge(loaiGhe);
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

    public boolean xoaLoaiGhe(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            LoaiGhe LoaiGhe = em.find(LoaiGhe.class, id);
            if (LoaiGhe != null) {
                em.remove(LoaiGhe);
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
