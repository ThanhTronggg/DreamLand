package service;

import entity.NhanVien;
import entity.TaiKhoan;

import java.rmi.RemoteException;

public interface TaiKhoanService extends GenericService<TaiKhoan, String> {
    TaiKhoan getTaiKhoanTheoUsername(String username) throws RemoteException;

    NhanVien getNhanVienTheoTaiKhoan(String username, boolean authentication) throws RemoteException;

    boolean doiMatKhau(String tenDangNhap, String matKhauHienTai, String matKhauMoi) throws RemoteException;
}
