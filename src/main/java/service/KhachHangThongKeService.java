package service;

import org.jfree.data.category.DefaultCategoryDataset;
import entity.KhachHangThongKe;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface KhachHangThongKeService extends Remote {
    ArrayList<KhachHangThongKe> getThongKeKhachHangTheoNam(int year) throws RemoteException;
    ArrayList<KhachHangThongKe> getThongKeKhachHangTheoThang(int month, int year) throws RemoteException;
    DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int month, int year) throws RemoteException;
    DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int year) throws RemoteException;
    DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoThang() throws RemoteException;
    DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoQuy() throws RemoteException;
    DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoNam() throws RemoteException;
}