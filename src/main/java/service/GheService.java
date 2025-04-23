package service;

import entity.Ghe;
import entity.Phong;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GheService extends GenericService<Ghe, Integer> {
    Ghe timTheoViTri(String viTri) throws RemoteException;
    ArrayList<Ghe> getDanhSachGheTheoPhong(Phong phong) throws RemoteException;
}
