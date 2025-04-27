package service;

import entity.KhachHang;

import java.rmi.RemoteException;

public interface KhachHangService extends GenericService<KhachHang, String> {
    String capNhatTenVaEmailKhachHangTheoSoDienThoai(String soDienThoai, String tenKhachHangMoi, String emailMoi) throws RemoteException;

    boolean kiemTraSoDienThoaiTonTai(String soDienThoai) throws RemoteException;

    KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) throws RemoteException;
}
