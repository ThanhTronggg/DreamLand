package dao;

import entity.LichChieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LichChieuDAO extends GenericDAO<LichChieu, Integer>{

    public LichChieuDAO(EntityManager em, Class<LichChieu> cls) {
        super(em, cls);
    }

    public LichChieuDAO(Class<LichChieu> cls) {
        super(cls);
    }
}
