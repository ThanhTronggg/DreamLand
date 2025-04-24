package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends GenericDAO<SanPham, Integer>{

    public SanPhamDAO(EntityManager em, Class<SanPham> cls) {
        super(em, cls);
    }

    public SanPhamDAO(Class<SanPham> cls) {
        super(cls);
    }

    public boolean tangSoLuongSanPham(String maSanPham, int soLuong) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            String query = "UPDATE SanPham sp SET sp.soLuong = sp.soLuong + :soLuong WHERE sp.maSanPham = :maSanPham";
            int rowsUpdated = em.createQuery(query)
                    .setParameter("soLuong", soLuong)
                    .setParameter("maSanPham", maSanPham)
                    .executeUpdate();
            em.getTransaction().commit();
            return rowsUpdated > 0;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean giamSoLuongSanPham(String maSanPham, int soLuongCanGiam) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            String query = "UPDATE SanPham sp SET sp.soLuong = sp.soLuong - :soLuong WHERE sp.maSanPham = :maSanPham";
            int rowsUpdated = em.createQuery(query)
                    .setParameter("soLuong", soLuongCanGiam)
                    .setParameter("maSanPham", maSanPham)
                    .executeUpdate();
            em.getTransaction().commit();
            return rowsUpdated > 0;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public ArrayList<SanPham> getSanPhamTheoLoaiSP(String loaiSanPham) {
        EntityManager em = JPAUtil.getEntityManager();
        ArrayList<SanPham> dsSanPham = new ArrayList<>();
        try {
            String query = "SELECT sp FROM SanPham sp WHERE sp.loaiSanPham = :loaiSanPham";
            List<SanPham> results = em.createQuery(query, SanPham.class)
                    .setParameter("loaiSanPham", loaiSanPham)
                    .getResultList();
            dsSanPham.addAll(results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dsSanPham;
    }

    public static void main(String[] args) {
        SanPhamDAO sanPhamDAO = new SanPhamDAO(SanPham.class);

        // Test tangSoLuongSanPham
        System.out.println("=== Kiểm tra tangSoLuongSanPham ===");
        boolean tangResult = sanPhamDAO.tangSoLuongSanPham("SP001", 10);
        System.out.println("Tăng số lượng SP001 (+10): " + (tangResult ? "Thành công" : "Thất bại"));

        // Test giamSoLuongSanPham
        System.out.println("\n=== Kiểm tra giamSoLuongSanPham ===");
        boolean giamResult = sanPhamDAO.giamSoLuongSanPham("SP001", 5);
        System.out.println("Giảm số lượng SP001 (-5): " + (giamResult ? "Thành công" : "Thất bại"));

        // Test getSanPhamTheoLoaiSP
        System.out.println("\n=== Kiểm tra getSanPhamTheoLoaiSP ===");
        ArrayList<SanPham> dsSanPham = sanPhamDAO.getSanPhamTheoLoaiSP("DoAn");
        System.out.println("Sản phẩm loại DoAn: " + dsSanPham.size() + " sản phẩm");
        dsSanPham.forEach(System.out::println);

        // Đóng EntityManagerFactory
        JPAUtil.close();
    }
}
