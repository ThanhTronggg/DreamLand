package service.impl;

import dao.LoaiGheDAO;
import dao.PhimDAO;
import entity.LoaiGhe;
import entity.Phim;

import java.rmi.RemoteException;

public class LoaiGheServiceImpl extends GenericServiceImpl<LoaiGhe, Integer> implements service.LoaiGheService {

    protected LoaiGheDAO loaiGheDAO;

    public LoaiGheServiceImpl(LoaiGheDAO loaiGheDAO) throws RemoteException {
        super(loaiGheDAO);
        this.loaiGheDAO = loaiGheDAO;
    }
}
