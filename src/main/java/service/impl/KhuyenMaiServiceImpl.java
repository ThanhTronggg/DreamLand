package service.impl;

import dao.KhuyenMaiDAO;
import dao.PhimDAO;
import entity.KhuyenMai;
import entity.Phim;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class KhuyenMaiServiceImpl extends GenericServiceImpl<KhuyenMai, String> implements service.KhuyenMaiService {


    protected KhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiServiceImpl(KhuyenMaiDAO khuyenMaiDAO) throws RemoteException {
        super(khuyenMaiDAO);
        this.khuyenMaiDAO = khuyenMaiDAO;
    }

    @Override
    public KhuyenMai getKhuyenMaiConHanTheoTongTienToiThieu(double tongTien) throws RemoteException {
        return khuyenMaiDAO.getKhuyenMaiConHanTheoTongTienToiThieu(tongTien);
    }

    @Override
    public ArrayList<KhuyenMai> getNamKhuyenMaiSapHetHan() throws RemoteException {
        return khuyenMaiDAO.getNamKhuyenMaiSapHetHan();
    }

    @Override
    public ArrayList<KhuyenMai> getKhuyenMaiConHan() throws RemoteException{
        return khuyenMaiDAO.getKhuyenMaiConHan();
    }

    @Override
    public ArrayList<KhuyenMai> getKhuyenMaiHetHan() throws RemoteException{
        return khuyenMaiDAO.getKhuyenMaiHetHan();
    }
}
