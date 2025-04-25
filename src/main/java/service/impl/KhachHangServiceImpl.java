package service.impl;

import dao.KhachHangDAO;
import dao.PhimDAO;
import entity.KhachHang;
import entity.Phim;

import java.rmi.RemoteException;

public class KhachHangServiceImpl extends GenericServiceImpl<KhachHang, String> implements service.KhachHangService {

    protected KhachHangDAO khachHangDAO;

    public KhachHangServiceImpl(KhachHangDAO khachHangDAO) throws RemoteException {
        super(khachHangDAO);
        this.khachHangDAO = khachHangDAO;
    }
}
