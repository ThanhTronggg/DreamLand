package dao;

import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class VeDAO extends GenericDAO<Ve, Integer>{
    public VeDAO(EntityManager em, Class<Ve> cls) {
        super(em, cls);
    }

    public VeDAO(Class<Ve> cls) {
        super(cls);
    }
}
