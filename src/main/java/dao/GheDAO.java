package dao;

import entity.Ghe;
import entity.Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.JPAUtil;

import java.util.ArrayList;

public class GheDAO extends GenericDAO<Ghe, Integer> {

    public GheDAO(Class<Ghe> cls) { super(cls); }

    public GheDAO(EntityManager em, Class<Ghe> cls) { super(em, cls);}

    public Ghe timTheoViTri(String viTri) {
        String query = "SELECT g FROM Ghe g WHERE g.viTri = :viTri";
        try {
            return em.createQuery(query, Ghe.class)
                    .setParameter("viTri", viTri)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Ghe> getDanhSachGheTheoPhong(Phong phong) {
        try {
            return (ArrayList<Ghe>) em.createQuery("SELECT p FROM Ghe p where p.phong = :phong", Ghe.class)
                    .setParameter("phong", phong).getResultList();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
