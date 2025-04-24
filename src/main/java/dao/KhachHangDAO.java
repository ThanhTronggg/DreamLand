package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO extends GenericDAO<KhachHang, Integer> {
    public KhachHangDAO(EntityManager em, Class<KhachHang> cls) {
        super(em, cls);
    }

    public KhachHangDAO(Class<KhachHang> cls) {
        super(cls);
    }

//    public ArrayList<KhachHang> timKiemKhachHangTheoSDT(String soDienThoai) {
//        ArrayList<KhachHang> listKhachHang = new ArrayList<>();
//        try {
//            String query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai LIKE :soDienThoai";
//            List<KhachHang> results = em.createQuery(query, KhachHang.class)
//                    .setParameter("soDienThoai", "%" + soDienThoai + "%")
//                    .getResultList();
//            listKhachHang.addAll(results);
//            return listKhachHang;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public String capNhatTenVaEmailKhachHangTheoSoDienThoai(String soDienThoai, String tenKhachHangMoi, String emailMoi) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            String query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :soDienThoai";
            KhachHang khachHang = em.createQuery(query, KhachHang.class)
                    .setParameter("soDienThoai", soDienThoai)
                    .getSingleResult();

            khachHang.setTenKhachHang(tenKhachHangMoi);
            khachHang.setEmail(emailMoi);
            em.merge(khachHang);

            transaction.commit();
            return String.valueOf(khachHang.getMaKhachHang());
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean kiemTraSoDienThoaiTonTai(String soDienThoai) {
        try {
            String query = "SELECT COUNT(kh) FROM KhachHang kh WHERE kh.soDienThoai = :soDienThoai";
            Long count = em.createQuery(query, Long.class)
                    .setParameter("soDienThoai", soDienThoai)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) {
        try {
            String query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :soDienThoai";
            return em.createQuery(query, KhachHang.class)
                    .setParameter("soDienThoai", soDienThoai)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        KhachHangDAO khachHangDAO = new KhachHangDAO(KhachHang.class);
//        KhachHang khachHang = khachHangDAO.timKhachHangTheoSoDienThoai("(305) 892-6241");

//        String tenKH = khachHangDAO.capNhatTenVaEmailKhachHangTheoSoDienThoai("(305) 892-6241", "Nguyễn Thành Trọng", "tronggg01010100@gmail.com");

        boolean result = khachHangDAO.kiemTraSoDienThoaiTonTai("(305) 892-6241111");
        System.out.println(result);

    }
}
