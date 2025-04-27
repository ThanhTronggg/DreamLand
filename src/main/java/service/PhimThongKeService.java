package service;

import org.jfree.data.category.DefaultCategoryDataset;
import entity.PhimThongKe;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PhimThongKeService extends Remote {
    ArrayList<PhimThongKe> getThongKePhimTheoThang(int month, int year) throws RemoteException;
    ArrayList<PhimThongKe> getThongKePhimTheoNam(int year) throws RemoteException;
    DefaultCategoryDataset getThongKePhimTheoNamBD(int year) throws RemoteException;
    DefaultCategoryDataset getThongKePhimTheoThangBD(int year, int month) throws RemoteException;
}