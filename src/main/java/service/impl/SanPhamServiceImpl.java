package service.impl;

import dao.PhimDAO;
import dao.SanPhamDAO;
import entity.Phim;
import entity.SanPham;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SanPhamServiceImpl extends GenericServiceImpl<SanPham, String> implements service.SanPhamService {

    protected SanPhamDAO sanPhamDAO;

    public SanPhamServiceImpl(SanPhamDAO sanPhamDAO) throws RemoteException {
        super(sanPhamDAO);
        this.sanPhamDAO = sanPhamDAO;
    }

    @Override
    public boolean tangSoLuongSanPham(String maSanPham, int soLuong) throws RemoteException {
        return sanPhamDAO.tangSoLuongSanPham(maSanPham, soLuong);
    }

    @Override
    public boolean giamSoLuongSanPham(String maSanPham, int soLuongCanGiam) throws RemoteException {
        return sanPhamDAO.giamSoLuongSanPham(maSanPham, soLuongCanGiam);
    }

    @Override
    public ArrayList<SanPham> getSanPhamTheoLoaiSP(String loaiSanPham) throws RemoteException {
        return sanPhamDAO.getSanPhamTheoLoaiSP(loaiSanPham);
    }
}
