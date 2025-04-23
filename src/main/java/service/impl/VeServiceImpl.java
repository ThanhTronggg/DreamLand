package service.impl;

import dao.PhimDAO;
import dao.VeDAO;
import entity.Phim;
import entity.Ve;

import java.rmi.RemoteException;

public class VeServiceImpl extends GenericServiceImpl<Ve, Integer> implements service.VeService {

    protected VeDAO veDAO;

    public VeServiceImpl(VeDAO veDAO) throws RemoteException {
        super(veDAO);
        this.veDAO = veDAO;
    }
}
