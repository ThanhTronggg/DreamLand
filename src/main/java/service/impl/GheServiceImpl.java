package service.impl;

import dao.GheDAO;
import dao.PhimDAO;
import entity.Ghe;
import entity.Phim;
import entity.Phong;
import jakarta.persistence.NoResultException;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GheServiceImpl extends GenericServiceImpl<Ghe, String> implements service.GheService {

    protected GheDAO gheDAO;

    public GheServiceImpl(GheDAO gheDAO) throws RemoteException {
        super(gheDAO);
        this.gheDAO = gheDAO;
    }

    @Override
    public Ghe timTheoViTri(String viTri) throws RemoteException {
        return gheDAO.timTheoViTri(viTri);
    }

    @Override
    public ArrayList<Ghe> getDanhSachGheTheoPhong(Phong phong) throws RemoteException {
        return gheDAO.getDanhSachGheTheoPhong(phong);
    }
}
