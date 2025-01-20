package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class SanPhamDAO {

    private EntityManager em;

    public SanPhamDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themSanPham(SanPham sanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(sanPham);
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

    public SanPham timTheoMaSanPham(int id) {
        return em.find(SanPham.class, id);
    }

    public boolean capNhatSanPham(SanPham sanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            SanPham updatedSanPham = em.merge(sanPham);
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

    public boolean xoaSanPham(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            SanPham SanPham = em.find(SanPham.class, id);
            if (SanPham != null) {
                em.remove(SanPham);
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

    public ArrayList<SanPham> getDanhSachSanPham() {
        try {
            return (ArrayList<SanPham>) em.createQuery("SELECT p FROM SanPham p", SanPham.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
