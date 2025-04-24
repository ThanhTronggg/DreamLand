package dao;

import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PhongDAO extends GenericDAO<Phong, Integer>{

    public PhongDAO(EntityManager em, Class<Phong> cls) {
        super(em, cls);
    }

    public PhongDAO(Class<Phong> cls) {
        super(cls);
    }
}
