package service.impl;

import dao.KhachHangDAO;
import dao.PhimDAO;
import entity.KhachHang;
import entity.Phim;

import java.rmi.RemoteException;

public class KhachHangServiceImpl extends GenericServiceImpl<KhachHang, String> implements service.KhachHangService {

    protected KhachHangDAO khachHangDAO;

    public KhachHangServiceImpl(KhachHangDAO khachHangDAO) throws RemoteException {
        super(khachHangDAO);
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public String capNhatTenVaEmailKhachHangTheoSoDienThoai(String soDienThoai, String tenKhachHangMoi, String emailMoi) throws RemoteException {
        return khachHangDAO.capNhatTenVaEmailKhachHangTheoSoDienThoai(soDienThoai, tenKhachHangMoi, emailMoi);
    }

    @Override
    public boolean kiemTraSoDienThoaiTonTai(String soDienThoai) throws RemoteException {
        return khachHangDAO.kiemTraSoDienThoaiTonTai(soDienThoai);
    }

    @Override
    public KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) throws RemoteException {
        return khachHangDAO.timKhachHangTheoSoDienThoai(soDienThoai);
    }
}
