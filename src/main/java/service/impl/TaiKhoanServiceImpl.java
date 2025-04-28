package service.impl;

import dao.PhimDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.Phim;
import entity.TaiKhoan;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaiKhoanServiceImpl extends GenericServiceImpl<TaiKhoan, String> implements service.TaiKhoanService {

    protected TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanServiceImpl(TaiKhoanDAO taiKhoanDAO) throws RemoteException {
        super(taiKhoanDAO);
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @Override
    public TaiKhoan getTaiKhoanTheoUsername(String username) throws RemoteException {
        return taiKhoanDAO.getTaiKhoanTheoUsername(username);
    }

    @Override
    public NhanVien getNhanVienTheoTaiKhoan(String username, boolean authentication) throws RemoteException {
        return taiKhoanDAO.getNhanVienTheoTaiKhoan(username, authentication);
    }

    @Override
    public boolean doiMatKhau(String tenDangNhap, String matKhauHienTai, String matKhauMoi) throws RemoteException {
        return taiKhoanDAO.doiMatKhau(tenDangNhap, matKhauHienTai, matKhauMoi);
    }
}
