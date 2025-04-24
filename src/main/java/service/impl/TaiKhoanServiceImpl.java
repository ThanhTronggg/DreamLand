package service.impl;

import dao.PhimDAO;
import dao.TaiKhoanDAO;
import entity.Phim;
import entity.TaiKhoan;

import java.rmi.RemoteException;

public class TaiKhoanServiceImpl extends GenericServiceImpl<TaiKhoan, Integer> implements service.TaiKhoanService {

    protected TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanServiceImpl(TaiKhoanDAO taiKhoanDAO) throws RemoteException {
        super(taiKhoanDAO);
        this.taiKhoanDAO = taiKhoanDAO;
    }
}
