package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class KhachHangDAO extends GenericDAO<KhachHang, Integer> {
    public KhachHangDAO(EntityManager em, Class<KhachHang> cls) {
        super(em, cls);
    }

    public KhachHangDAO(Class<KhachHang> cls) {
        super(cls);
    }
}
