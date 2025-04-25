package service.impl;

import dao.PhimDAO;
import dao.PhongDAO;
import entity.Phim;
import entity.Phong;

import java.rmi.RemoteException;

public class PhongServiceImpl extends GenericServiceImpl<Phong, String> implements service.PhongService {

    protected PhongDAO phongDAO;

    public PhongServiceImpl(PhongDAO phongDAO) throws RemoteException {
        super(phongDAO);
        this.phongDAO = phongDAO;
    }
}
