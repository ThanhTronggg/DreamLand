package service.impl;

import dao.KhuyenMaiDAO;
import dao.PhimDAO;
import entity.KhuyenMai;
import entity.Phim;

import java.rmi.RemoteException;

public class KhuyenMaiServiceImpl extends GenericServiceImpl<KhuyenMai, Integer> implements service.KhuyenMaiService {

    protected KhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiServiceImpl(KhuyenMaiDAO khuyenMaiDAO) throws RemoteException {
        super(khuyenMaiDAO);
        this.khuyenMaiDAO = khuyenMaiDAO;
    }
}
