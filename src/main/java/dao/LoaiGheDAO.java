package dao;

import entity.LoaiGhe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoaiGheDAO extends GenericDAO<LoaiGhe,String>{


    public LoaiGheDAO(EntityManager em, Class<LoaiGhe> cls) {
        super(em, cls);
    }

    public LoaiGheDAO(Class<LoaiGhe> cls) {
        super(cls);
    }
}
