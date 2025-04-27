package service;

import entity.SanPham;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SanPhamService extends GenericService<SanPham, String> {
    boolean tangSoLuongSanPham(String maSanPham, int soLuong) throws RemoteException;

    boolean giamSoLuongSanPham(String maSanPham, int soLuongCanGiam) throws RemoteException;

    ArrayList<SanPham> getSanPhamTheoLoaiSP(String loaiSanPham) throws RemoteException;
}
