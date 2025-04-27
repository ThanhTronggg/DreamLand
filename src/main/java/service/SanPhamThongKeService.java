package service;

import org.jfree.data.category.DefaultCategoryDataset;
import entity.SanPhamThongKe;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SanPhamThongKeService extends Remote {
    ArrayList<SanPhamThongKe> getThongKeSanPhamTheoThang(int month, int year) throws RemoteException;
    ArrayList<SanPhamThongKe> getThongKeSanPhamTheoNam(int year) throws RemoteException;
    DefaultCategoryDataset getDoanhThuSanPhamTheoNamBD(int year) throws RemoteException;
    DefaultCategoryDataset getDoanhThuSanPhamTheoThangBD(int year, int month) throws RemoteException;
}