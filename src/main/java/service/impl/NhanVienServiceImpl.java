package service.impl;

import dao.NhanVienDAO;
import dao.PhimDAO;
import entity.NhanVien;
import entity.Phim;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienServiceImpl extends GenericServiceImpl<NhanVien, String> implements service.NhanVienService {

    protected NhanVienDAO nhanVienDAO;

    public NhanVienServiceImpl(NhanVienDAO nhanVienDAO) throws RemoteException {
        super(nhanVienDAO);
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public List<NhanVien> searchNhanVien(String keyword) throws RemoteException {
        return nhanVienDAO.searchNhanVien(keyword);
    }
}
