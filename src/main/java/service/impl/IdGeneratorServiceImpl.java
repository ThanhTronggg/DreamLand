package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import jakarta.persistence.EntityManager;
import service.IdGeneratorService;
import util.JPAUtil;

public class IdGeneratorServiceImpl extends UnicastRemoteObject implements IdGeneratorService {
    private final EntityManager em;

    public IdGeneratorServiceImpl(EntityManager em) throws RemoteException {
        this.em = em;
    }

    public IdGeneratorServiceImpl() throws RemoteException {
        this.em = JPAUtil.getEntityManager();
    }

    @Override
    public String getNextId(String entityType) throws RemoteException {
        String sequenceName = entityType + "Sequence";
        String prefix = getPrefixForEntity(entityType);
        int padLength = getPadLengthForEntity(entityType);
        return getNextSequenceValue(sequenceName, prefix, padLength);
    }

    private String getPrefixForEntity(String entityType) {
        switch (entityType) {
            case "KhachHang": return "KH";
            case "HoaDon": return "HD";
            case "Ve": return "Ve";
            case "LoaiGhe": return "LG";
            case "Phong": return "PH";
            case "Ghe": return "Ghe";
            case "TaiKhoan": return "TK";
            case "NhanVien": return "NV";
            case "KhuyenMai": return "KM";
            case "SanPham": return "SP";
            case "Phim": return "P";
            case "LichChieu": return "LC";
            default: throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }

    private int getPadLengthForEntity(String entityType) {
        switch (entityType) {
            case "KhachHang": return 6;
            case "HoaDon": return 6;
            case "Ve": return 6;
            case "LoaiGhe": return 3;
            case "Phong": return 3;
            case "Ghe": return 4;
            case "TaiKhoan": return 3;
            case "NhanVien": return 3;
            case "KhuyenMai": return 4;
            case "SanPham": return 4;
            case "Phim": return 3;
            case "LichChieu": return 6;
            default: throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }


    private String getNextSequenceValue(String sequenceName, String prefix, int padLength) {
        try {
            Long nextValue = (Long) em.createNativeQuery("SELECT NEXT VALUE FOR " + sequenceName).getSingleResult();
            return prefix + String.format("%0" + padLength + "d", nextValue);
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy sequence " + sequenceName + ": " + e.getMessage());
            throw e;
        }
    }
}