package service.impl;

import dao.HoaDonDAO;
import dao.PhimDAO;
import entity.HoaDon;
import entity.Phim;

import java.rmi.RemoteException;

public class HoaDonServiceImpl extends GenericServiceImpl<HoaDon, Integer> implements service.HoaDonService {

    protected HoaDonDAO hoaDonDAO;

    public HoaDonServiceImpl(HoaDonDAO hoaDonDAO) throws RemoteException {
        super(hoaDonDAO);
        this.hoaDonDAO = hoaDonDAO;
    }
}
