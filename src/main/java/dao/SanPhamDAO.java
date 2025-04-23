package dao;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SanPhamDAO extends GenericDAO<SanPham, Integer>{

    public SanPhamDAO(EntityManager em, Class<SanPham> cls) {
        super(em, cls);
    }

    public SanPhamDAO(Class<SanPham> cls) {
        super(cls);
    }
}
