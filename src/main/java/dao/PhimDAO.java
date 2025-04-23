package dao;

import dao.GenericDAO;
import entity.Phim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PhimDAO extends GenericDAO<Phim, Integer> {

    public PhimDAO(EntityManager em, Class<Phim> cls) {
        super(em, cls);
    }

    public PhimDAO(Class<Phim> cls) {
        super(cls);
    }
}
