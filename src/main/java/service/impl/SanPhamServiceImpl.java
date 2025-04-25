package service.impl;

import dao.PhimDAO;
import dao.SanPhamDAO;
import entity.Phim;
import entity.SanPham;

import java.rmi.RemoteException;

public class SanPhamServiceImpl extends GenericServiceImpl<SanPham, String> implements service.SanPhamService {

    protected SanPhamDAO sanPhamDAO;

    public SanPhamServiceImpl(SanPhamDAO sanPhamDAO) throws RemoteException {
        super(sanPhamDAO);
        this.sanPhamDAO = sanPhamDAO;
    }
}
