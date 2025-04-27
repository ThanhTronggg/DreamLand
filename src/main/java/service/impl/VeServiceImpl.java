package service.impl;

import dao.PhimDAO;
import dao.VeDAO;
import entity.LichChieu;
import entity.Phim;
import entity.Ve;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VeServiceImpl extends GenericServiceImpl<Ve, String> implements service.VeService {

    protected VeDAO veDAO;

    public VeServiceImpl(VeDAO veDAO) throws RemoteException {
        super(veDAO);
        this.veDAO = veDAO;
    }

    @Override
    public ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) {
        return veDAO.getVeTheoLichChieu(lc);
    }
}
