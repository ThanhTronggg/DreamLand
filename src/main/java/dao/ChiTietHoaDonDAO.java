package dao;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ChiTietHoaDonDAO extends GenericDAO<ChiTietHoaDon, Integer> {

    public ChiTietHoaDonDAO(Class<ChiTietHoaDon> cls){ super(cls); }

    public ChiTietHoaDonDAO(EntityManager em, Class<ChiTietHoaDon> cls)  { super(em, cls); }

}
