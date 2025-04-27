/*
 * @(#) PhimThongKeServiceImpl.java 1.0 Nov 8, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package service.impl;

import dao.PhimThongKeDAO;
import org.jfree.data.category.DefaultCategoryDataset;
import entity.PhimThongKe;
import service.PhimThongKeService;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @description: Service implementation for movie statistics
 * @author: Thanh Trong
 * @date: Nov 8, 2024
 * @version: 1.0
 */
public class PhimThongKeServiceImpl extends GenericServiceImpl<PhimThongKe, String> implements PhimThongKeService {

    protected PhimThongKeDAO phimThongKeDAO;

    public PhimThongKeServiceImpl(PhimThongKeDAO phimThongKeDAO) throws RemoteException {
        super(phimThongKeDAO);
        this.phimThongKeDAO = phimThongKeDAO;
    }

    @Override
    public ArrayList<PhimThongKe> getThongKePhimTheoThang(int month, int year) throws RemoteException {
        return phimThongKeDAO.getThongKePhimTheoThang(month, year);
    }

    @Override
    public ArrayList<PhimThongKe> getThongKePhimTheoNam(int year) throws RemoteException {
        return phimThongKeDAO.getThongKePhimTheoNam(year);
    }

    @Override
    public DefaultCategoryDataset getThongKePhimTheoNamBD(int year) throws RemoteException {
        return phimThongKeDAO.getThongKePhimTheoNamBD(year);
    }

    @Override
    public DefaultCategoryDataset getThongKePhimTheoThangBD(int year, int month) throws RemoteException {
        return phimThongKeDAO.getThongKePhimTheoThangBD(year, month);
    }
}