package service.impl;

import dao.LichChieuDAO;
import dao.PhimDAO;
import entity.LichChieu;
import entity.Phim;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LichChieuServiceImpl extends GenericServiceImpl<LichChieu, String> implements service.LichChieuService {

    protected LichChieuDAO lichChieuDAO;

    public LichChieuServiceImpl(LichChieuDAO lichChieuDAO) throws RemoteException {
        super(lichChieuDAO);
        this.lichChieuDAO = lichChieuDAO;
    }

    @Override
    public ArrayList<LichChieu> getLichChieuTheoNgay(LocalDate ngay) {
        return lichChieuDAO.getLichChieuTheoNgay(ngay);
    }

    @Override
    public ArrayList<LichChieu> getLichChieuTheoPhong(String maPhong) {
        return lichChieuDAO.getLichChieuTheoPhong(maPhong);
    }
}
