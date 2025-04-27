/*
 * @(#) DoanhThuServiceImpl.java 1.0 Nov 7, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package service.impl;

import dao.DoanhThuDAO;
import org.jfree.data.general.DefaultPieDataset;
import entity.DoanhThu;
import service.DoanhThuService;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @description: Service implementation for revenue statistics
 * @author: Thanh Trong
 * @date: Nov 7, 2024
 * @version: 1.0
 */
public class DoanhThuServiceImpl extends GenericServiceImpl<DoanhThu, String> implements DoanhThuService {

    protected DoanhThuDAO doanhThuDAO;

    public DoanhThuServiceImpl(DoanhThuDAO doanhThuDAO) throws RemoteException {
        super(doanhThuDAO);
        this.doanhThuDAO = doanhThuDAO;
    }

    @Override
    public DefaultPieDataset<String> getThongKeDoanhThuTheoNamBD(int year) throws RemoteException {
        return doanhThuDAO.getThongKeDoanhThuTheoNamBD(year);
    }

    @Override
    public DefaultPieDataset<String> getThongKeDoanhThuTheoThangBD(int month, int year) throws RemoteException {
        return doanhThuDAO.getThongKeDoanhThuTheoThangBD(month, year);
    }

    @Override
    public ArrayList<DoanhThu> getThongKeDoanhThuTheoThang(int month, int year) throws RemoteException {
        return doanhThuDAO.getThongKeDoanhThuTheoThang(month, year);
    }

    @Override
    public ArrayList<DoanhThu> getThongKeDoanhThuTheoNam(int year) throws RemoteException {
        return doanhThuDAO.getThongKeDoanhThuTheoNam(year);
    }
}