package dao;

import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ChiTietHoaDonDAO extends GenericDAO<ChiTietHoaDon, ChiTietHoaDonPK> {

    public ChiTietHoaDonDAO(Class<ChiTietHoaDon> cls){ super(cls); }

    public ChiTietHoaDonDAO(EntityManager em, Class<ChiTietHoaDon> cls)  { super(em, cls); }

}
