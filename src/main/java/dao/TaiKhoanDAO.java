package dao;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaiKhoanDAO extends GenericDAO<TaiKhoan, Integer>{

    public TaiKhoanDAO(EntityManager em, Class<TaiKhoan> cls) {
        super(em, cls);
    }

    public TaiKhoanDAO(Class<TaiKhoan> cls) {
        super(cls);
    }
}
