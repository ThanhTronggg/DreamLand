package service;

import entity.LichChieu;
import entity.Ve;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface VeService extends GenericService<Ve, String> {
    ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) throws RemoteException;
    void addVeWithCheck(Ve ve) throws RemoteException;
    void addMultipleVesWithCheck(Set<Ve> ves, LichChieu lichChieu) throws RemoteException;
}
