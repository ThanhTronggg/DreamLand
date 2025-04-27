package service;

import entity.NhanVien;

import java.rmi.RemoteException;
import java.util.List;

public interface NhanVienService extends GenericService<NhanVien, String> {
    List<NhanVien> searchNhanVien(String keyword) throws RemoteException;
}
