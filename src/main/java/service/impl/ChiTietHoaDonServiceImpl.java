package service.impl;

import dao.ChiTietHoaDonDAO;
import dao. ChiTietHoaDonDAO;
import entity. ChiTietHoaDon;

import java.rmi.RemoteException;

public class ChiTietHoaDonServiceImpl extends GenericServiceImpl< ChiTietHoaDon, Integer> implements service.ChiTietHoaDonService {

    protected ChiTietHoaDonDAO  chiTietHoaDonDAO;

    public ChiTietHoaDonServiceImpl( ChiTietHoaDonDAO  chiTietHoaDonDAO) throws RemoteException {
        super( chiTietHoaDonDAO);
        this. chiTietHoaDonDAO =  chiTietHoaDonDAO;
    }
}
