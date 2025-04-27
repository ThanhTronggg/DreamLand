package service;

import entity.KhuyenMai;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface KhuyenMaiService extends GenericService<KhuyenMai, String> {
    KhuyenMai getKhuyenMaiConHanTheoTongTienToiThieu(double tongTien) throws RemoteException;

    ArrayList<KhuyenMai> getNamKhuyenMaiSapHetHan() throws RemoteException;

    ArrayList<KhuyenMai> getKhuyenMaiConHan() throws RemoteException;

    ArrayList<KhuyenMai> getKhuyenMaiHetHan() throws RemoteException;
}
