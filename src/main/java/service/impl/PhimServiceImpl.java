package service.impl;

import dao.GenericDAO;
import dao.PhimDAO;
import entity.Phim;

import java.rmi.RemoteException;

public class PhimServiceImpl extends GenericServiceImpl<Phim, Integer> implements service.PhimService {

    protected PhimDAO phimDAO;

    public PhimServiceImpl(PhimDAO phimDAO) throws RemoteException {
        super(phimDAO);
        this.phimDAO = phimDAO;
    }
}
