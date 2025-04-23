package dao;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class NhanVienDAO extends GenericDAO<NhanVien, Integer> {

    public NhanVienDAO(EntityManager em, Class<NhanVien> cls) {
        super(em, cls);
    }

    public NhanVienDAO(Class<NhanVien> cls) {
        super(cls);
    }
}
