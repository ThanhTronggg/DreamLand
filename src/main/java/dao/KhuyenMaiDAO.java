package dao;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KhuyenMaiDAO extends GenericDAO<KhuyenMai, Integer> {
    public KhuyenMaiDAO(EntityManager em, Class<KhuyenMai> cls) {
        super(em, cls);
    }

    public KhuyenMaiDAO(Class<KhuyenMai> cls) {
        super(cls);
    }
}
