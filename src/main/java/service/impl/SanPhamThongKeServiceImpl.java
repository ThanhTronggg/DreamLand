/*
 * @(#) SanPhamThongKeServiceImpl.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package service.impl;

import dao.SanPhamThongKeDAO;
import org.jfree.data.category.DefaultCategoryDataset;
import entity.SanPhamThongKe;
import service.SanPhamThongKeService;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @description: Service implementation for product statistics
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */
public class SanPhamThongKeServiceImpl extends GenericServiceImpl<SanPhamThongKe, String> implements SanPhamThongKeService {

    protected SanPhamThongKeDAO sanPhamThongKeDAO;

    public SanPhamThongKeServiceImpl(SanPhamThongKeDAO sanPhamThongKeDAO) throws RemoteException {
        super(sanPhamThongKeDAO);
        this.sanPhamThongKeDAO = sanPhamThongKeDAO;
    }

    @Override
    public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoThang(int month, int year) throws RemoteException {
        return sanPhamThongKeDAO.getThongKeSanPhamTheoThang(month, year);
    }

    @Override
    public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoNam(int year) throws RemoteException {
        return sanPhamThongKeDAO.getThongKeSanPhamTheoNam(year);
    }

    @Override
    public DefaultCategoryDataset getDoanhThuSanPhamTheoNamBD(int year) throws RemoteException {
        return sanPhamThongKeDAO.getDoanhThuSanPhamTheoNamBD(year);
    }

    @Override
    public DefaultCategoryDataset getDoanhThuSanPhamTheoThangBD(int year, int month) throws RemoteException {
        return sanPhamThongKeDAO.getDoanhThuSanPhamTheoThangBD(year, month);
    }
}