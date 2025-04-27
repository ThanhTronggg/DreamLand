package service;

import entity.LichChieu;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface LichChieuService extends GenericService<LichChieu, String> {
    ArrayList<LichChieu> getLichChieuTheoNgay(LocalDate ngay) throws RemoteException;

    ArrayList<LichChieu> getLichChieuTheoPhong(String maPhong) throws RemoteException;
}
