package service.impl;

import dao.NhanVienDAO;
import dao.PhimDAO;
import entity.NhanVien;
import entity.Phim;

import java.rmi.RemoteException;

public class NhanVienServiceImpl extends GenericServiceImpl<NhanVien, Integer> implements service.NhanVienService {

    protected NhanVienDAO nhanVienDAO;

    public NhanVienServiceImpl(NhanVienDAO nhanVienDAO) throws RemoteException {
        super(nhanVienDAO);
        this.nhanVienDAO = nhanVienDAO;
    }
}
