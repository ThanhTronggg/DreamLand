package service.impl;

import dao.LichChieuDAO;
import dao.PhimDAO;
import entity.LichChieu;
import entity.Phim;

import java.rmi.RemoteException;

public class LichChieuServiceImpl extends GenericServiceImpl<LichChieu, String> implements service.LichChieuService {

    protected LichChieuDAO lichChieuDAO;

    public LichChieuServiceImpl(LichChieuDAO lichChieuDAO) throws RemoteException {
        super(lichChieuDAO);
        this.lichChieuDAO = lichChieuDAO;
    }
}
