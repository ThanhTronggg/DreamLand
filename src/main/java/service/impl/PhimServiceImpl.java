package service.impl;

import dao.GenericDAO;
import dao.PhimDAO;
import entity.Phim;

import java.rmi.RemoteException;

public class PhimServiceImpl extends GenericServiceImpl<Phim, String> implements service.PhimService {

    protected PhimDAO phimDAO;

    public PhimServiceImpl(PhimDAO phimDAO) throws RemoteException {
        super(phimDAO);
        this.phimDAO = phimDAO;
    }
}
