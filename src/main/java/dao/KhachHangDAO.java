package dao;

import entity.KhachHang;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class KhachHangDAO {

    private EntityManager em;

    public KhachHangDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themKhachHang(KhachHang khachHang) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(khachHang);
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

    public KhachHang timTheoMaKhachHang(int id) {
        return em.find(KhachHang.class, id);
    }

    public boolean capNhatKhachHang(KhachHang khachHang) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhachHang updatedKhachHang = em.merge(khachHang);
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

    public ArrayList<KhachHang> getDanhSachKhachHang() {
        try {
            return (ArrayList<KhachHang>) em.createQuery("SELECT p FROM KhachHang p", KhachHang.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
