package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public abstract class GenericDAO<T, ID> {
    protected EntityManager em;
    private Class<T> cls;

    public GenericDAO(EntityManager em, Class<T> cls) {
        this.em = em;
        this.cls = cls;
    }
    public GenericDAO(Class<T> cls){
        this.em = JPAUtil.getEntityManager();
        this.cls = cls;
    }

    public boolean add(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public T findById(ID id) {
        return em.find(cls, id);
    }

    public List<T> findAll() {
        String query = "FROM " + cls.getSimpleName();
        return em.createQuery(query, cls).getResultList();
    }

    public boolean update(T t) {
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(ID id) {
        try {
            em.getTransaction().begin();
            T t = em.find(cls, id);
            if (t != null) {
                em.remove(t);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
