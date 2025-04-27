package service;

import entity.LichChieu;
import entity.Ve;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface VeService extends GenericService<Ve, String> {
    ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) throws RemoteException;
}
