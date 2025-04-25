package service.impl;

import dao.ChiTietHoaDonDAO;
import dao. ChiTietHoaDonDAO;
import entity. ChiTietHoaDon;
import entity.ChiTietHoaDonPK;

import java.rmi.RemoteException;

public class ChiTietHoaDonServiceImpl extends GenericServiceImpl<ChiTietHoaDon, ChiTietHoaDonPK> implements service.ChiTietHoaDonService {

    protected ChiTietHoaDonDAO  chiTietHoaDonDAO;

    public ChiTietHoaDonServiceImpl( ChiTietHoaDonDAO  chiTietHoaDonDAO) throws RemoteException {
        super(chiTietHoaDonDAO);
        this. chiTietHoaDonDAO =  chiTietHoaDonDAO;
    }
}
