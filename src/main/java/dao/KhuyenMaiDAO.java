package dao;

import entity.KhuyenMai;
import jakarta.persistence.*;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO extends GenericDAO<KhuyenMai, Integer> {
    public KhuyenMaiDAO(EntityManager em, Class<KhuyenMai> cls) {
        super(em, cls);
    }

    public KhuyenMaiDAO(Class<KhuyenMai> cls) {
        super(cls);
    }

    public KhuyenMai getKhuyenMaiConHanTheoTongTienToiThieu(double tongTien) {
        try {
            String query = "SELECT km FROM KhuyenMai km " +
                    "WHERE :currentTime BETWEEN km.ngayBatDau AND km.ngayKetThuc " +
                    "AND km.tongHoaDonToiThieu <= :tongTien " +
                    "ORDER BY km.phamTramKhuyenMai DESC";
            return em.createQuery(query, KhuyenMai.class)
                    .setParameter("currentTime", LocalDate.now())
                    .setParameter("tongTien", tongTien)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getNamKhuyenMaiSapHetHan() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            String query = "SELECT km FROM KhuyenMai km " +
                    "WHERE :currentTime < km.ngayKetThuc " +
                    "ORDER BY km.ngayKetThuc ASC";
            List<KhuyenMai> results = em.createQuery(query, KhuyenMai.class)
                    .setParameter("currentTime", LocalDate.now())
                    .setMaxResults(5)
                    .getResultList();
            dsKhuyenMai.addAll(results);
            return dsKhuyenMai;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getKhuyenMaiConHan() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            String query = "SELECT km FROM KhuyenMai km " +
                    "WHERE :currentTime BETWEEN km.ngayBatDau AND km.ngayKetThuc ";
            List<KhuyenMai> results = em.createQuery(query, KhuyenMai.class)
                    .setParameter("currentTime", LocalDate.now())
                    .getResultList();
            dsKhuyenMai.addAll(results);
            return dsKhuyenMai;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getKhuyenMaiHetHan() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            String query = "SELECT km FROM KhuyenMai km " +
                    "WHERE km.ngayKetThuc < :currentTime ";
            List<KhuyenMai> results = em.createQuery(query, KhuyenMai.class)
                    .setParameter("currentTime", LocalDate.now())
                    .getResultList();
            dsKhuyenMai.addAll(results);
            return dsKhuyenMai;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO(KhuyenMai.class);
//        ArrayList<KhuyenMai> khuyenMais = khuyenMaiDAO.getNamKhuyenMaiSapHetHan();

//        ArrayList<KhuyenMai> khuyenMais = khuyenMaiDAO.getKhuyenMaiConHan();

//        ArrayList<KhuyenMai> khuyenMais = khuyenMaiDAO.getKhuyenMaiHetHan();
//        khuyenMais.forEach(tx -> System.out.println(tx));

        KhuyenMai khuyenMai = khuyenMaiDAO.getKhuyenMaiConHanTheoTongTienToiThieu(2000000);
        System.out.println(khuyenMai);
    }
}
