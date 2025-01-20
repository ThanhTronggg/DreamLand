package dao;

import entity.NhanVien;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class NhanVienDAO {

    private EntityManager em;

    public NhanVienDAO() {
        em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
    }

    public boolean themNhanVien(NhanVien nhanVien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(nhanVien);
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

    public NhanVien timTheoMaNhanVien(int id) {
        return em.find(NhanVien.class, id);
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            return (ArrayList<NhanVien>) em.createQuery("SELECT p FROM NhanVien p", NhanVien.class).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean capNhatNhanVien(NhanVien nhanVien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhanVien updatedNhanVien = em.merge(nhanVien);
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
