package dao;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class HoaDonDAO extends GenericDAO<HoaDon, Integer> {

    public HoaDonDAO(EntityManager em, Class<HoaDon> cls) {
        super(em, cls);
    }

    public HoaDonDAO(Class<HoaDon> cls) {
        super(cls);
    }

}
