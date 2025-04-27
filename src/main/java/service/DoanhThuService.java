package service;

import org.jfree.data.general.DefaultPieDataset;
import entity.DoanhThu;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DoanhThuService extends Remote {
    DefaultPieDataset<String> getThongKeDoanhThuTheoNamBD(int year) throws RemoteException;
    DefaultPieDataset<String> getThongKeDoanhThuTheoThangBD(int month, int year) throws RemoteException;
    ArrayList<DoanhThu> getThongKeDoanhThuTheoThang(int month, int year) throws RemoteException;
    ArrayList<DoanhThu> getThongKeDoanhThuTheoNam(int year) throws RemoteException;
}