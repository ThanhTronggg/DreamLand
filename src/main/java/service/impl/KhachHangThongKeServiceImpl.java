/*
 * @(#) KhachHangThongKeServiceImpl.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package service.impl;

import dao.KhachHangThongKeDAO;
import entity.DoanhThu;
import org.jfree.data.category.DefaultCategoryDataset;
import entity.KhachHangThongKe;
import service.KhachHangThongKeService;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @description: Service implementation for customer statistics
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */
public class KhachHangThongKeServiceImpl extends GenericServiceImpl<KhachHangThongKe, String> implements KhachHangThongKeService {

    protected KhachHangThongKeDAO khachHangThongKeDAO;

    public KhachHangThongKeServiceImpl(KhachHangThongKeDAO khachHangThongKeDAO) throws RemoteException {
        super(khachHangThongKeDAO);
        this.khachHangThongKeDAO = khachHangThongKeDAO;
    }

    @Override
    public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoNam(int year) throws RemoteException {
        return khachHangThongKeDAO.getThongKeKhachHangTheoNam(year);
    }

    @Override
    public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoThang(int month, int year) throws RemoteException {
        return khachHangThongKeDAO.getThongKeKhachHangTheoThang(month, year);
    }

    @Override
    public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int month, int year) throws RemoteException {
        return khachHangThongKeDAO.getTop5KhachHangTheoChiTieu(month, year);
    }

    @Override
    public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int year) throws RemoteException {
        return khachHangThongKeDAO.getTop5KhachHangTheoChiTieu(year);
    }

    @Override
    public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoThang() throws RemoteException {
        return khachHangThongKeDAO.getSoLuongKhachHangPhanBietTheoThang();
    }

    @Override
    public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoQuy() throws RemoteException {
        return khachHangThongKeDAO.getSoLuongKhachHangPhanBietTheoQuy();
    }

    @Override
    public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoNam() throws RemoteException {
        return khachHangThongKeDAO.getSoLuongKhachHangPhanBietTheoNam();
    }
}