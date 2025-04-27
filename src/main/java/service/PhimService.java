package service;

import entity.Phim;

import java.rmi.RemoteException;

public interface PhimService extends GenericService<Phim, String> {
    boolean exists(String maPhim) throws RemoteException;

    String getNextMaPhim() throws RemoteException;
}
