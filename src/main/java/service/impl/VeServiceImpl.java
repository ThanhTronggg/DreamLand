package service.impl;

import dao.PhimDAO;
import dao.VeDAO;
import entity.LichChieu;
import entity.Phim;
import entity.Ve;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VeServiceImpl extends GenericServiceImpl<Ve, String> implements service.VeService {

    protected VeDAO veDAO;

    public VeServiceImpl(VeDAO veDAO) throws RemoteException {
        super(veDAO);
        this.veDAO = veDAO;
    }

    @Override
    public ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) {
        return veDAO.getVeTheoLichChieu(lc);
    }

    @Override
    public synchronized void addVeWithCheck(Ve ve) throws RemoteException {
        ArrayList<Ve> danhSachVe = veDAO.getVeTheoLichChieu(ve.getLichChieu());
        for (Ve existingVe : danhSachVe) {
            if (existingVe.getGhe().getMaGhe().equals(ve.getGhe().getMaGhe())) {
                throw new RemoteException("Ghế " + ve.getGhe().getViTri() + " đã được đặt cho lịch chiếu này.");
            }
        }
        super.add(ve);
    }
    @Override
    public synchronized void addMultipleVesWithCheck(Set<Ve> ves, LichChieu lichChieu) throws RemoteException {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Kiểm tra xem có ghế nào trong danh sách đã được đặt chưa
            ArrayList<Ve> danhSachVe = veDAO.getVeTheoLichChieu(lichChieu);
            Set<String> gheDaDat = new HashSet<>();
            for (Ve existingVe : danhSachVe) {
                gheDaDat.add(existingVe.getGhe().getMaGhe());
            }

            for (Ve ve : ves) {
                if (gheDaDat.contains(ve.getGhe().getMaGhe())) {
                    throw new RemoteException("Ghế " + ve.getGhe().getViTri() + " đã được đặt cho lịch chiếu này.");
                }
            }

            // Thêm tất cả vé nếu không có ghế nào bị trùng
            for (Ve ve : ves) {
                em.persist(ve);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RemoteException("Lỗi khi thêm vé: " + e.getMessage(), e);
        } finally {
            JPAUtil.closeEntityManager(em);
        }
    }
}
