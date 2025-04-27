/*
 * @(#) DoanhThuDAO.java 1.0 Nov 7, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import entity.Ghe;
import jakarta.persistence.EntityManager;
import org.jfree.data.general.DefaultPieDataset;
import util.JPAUtil;
import entity.DoanhThu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @description: Data Access Object for retrieving revenue statistics using JPA
 * @author: Thanh Trong
 * @date: Nov 7, 2024
 * @version: 1.0
 */
public class DoanhThuDAO extends GenericDAO<DoanhThu, String>{


	public DoanhThuDAO(Class<DoanhThu> cls) { super(cls); }

	public DoanhThuDAO(EntityManager em, Class<DoanhThu> cls) { super(em, cls);}

	public DefaultPieDataset<String> getThongKeDoanhThuTheoNamBD(int year) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		try {
			Double tongTienSP = em.createQuery(
							"SELECT SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year", Double.class)
					.setParameter("year", year)
					.getSingleResult();
			if (tongTienSP == null) tongTienSP = 0.0;

			Double tongTienBanVe = em.createQuery(
							"SELECT SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year", Double.class)
					.setParameter("year", year)
					.getSingleResult();
			if (tongTienBanVe == null) tongTienBanVe = 0.0;

			dataset.setValue("Tổng tiền bán vé", tongTienBanVe);
			dataset.setValue("Tổng tiền bán đồ ăn & uống", tongTienSP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public DefaultPieDataset<String> getThongKeDoanhThuTheoThangBD(int month, int year) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		try {
			Double tongTienSP = em.createQuery(
							"SELECT SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month", Double.class)
					.setParameter("year", year)
					.setParameter("month", month)
					.getSingleResult();
			if (tongTienSP == null) tongTienSP = 0.0;

			Double tongTienBanVe = em.createQuery(
							"SELECT SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month", Double.class)
					.setParameter("year", year)
					.setParameter("month", month)
					.getSingleResult();
			if (tongTienBanVe == null) tongTienBanVe = 0.0;

			dataset.setValue("Tổng tiền bán vé", tongTienBanVe);
			dataset.setValue("Tổng tiền bán đồ ăn & uống", tongTienSP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public ArrayList<DoanhThu> getThongKeDoanhThuTheoThang(int month, int year) {
		ArrayList<DoanhThu> dsDoanhThu = new ArrayList<>();
		try {
			Double tongTienSP = em.createQuery(
							"SELECT SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month", Double.class)
					.setParameter("year", year)
					.setParameter("month", month)
					.getSingleResult();
			if (tongTienSP == null) tongTienSP = 0.0;

			Double tongTienBanVe = em.createQuery(
							"SELECT SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month", Double.class)
					.setParameter("year", year)
					.setParameter("month", month)
					.getSingleResult();
			if (tongTienBanVe == null) tongTienBanVe = 0.0;

			double tongDoanhThu = tongTienBanVe + tongTienSP;

			DoanhThu dt = new DoanhThu(tongTienSP, tongTienBanVe, tongDoanhThu);
			dsDoanhThu.add(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDoanhThu;
	}

	public ArrayList<DoanhThu> getThongKeDoanhThuTheoNam(int year) {
		ArrayList<DoanhThu> dsDoanhThu = new ArrayList<>();
		try {
			Double tongTienSP = em.createQuery(
							"SELECT SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year", Double.class)
					.setParameter("year", year)
					.getSingleResult();
			if (tongTienSP == null) tongTienSP = 0.0;

			Double tongTienBanVe = em.createQuery(
							"SELECT SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year", Double.class)
					.setParameter("year", year)
					.getSingleResult();
			if (tongTienBanVe == null) tongTienBanVe = 0.0;

			double tongDoanhThu = tongTienBanVe + tongTienSP;

			DoanhThu dt = new DoanhThu(tongTienSP, tongTienBanVe, tongDoanhThu);
			dsDoanhThu.add(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDoanhThu;
	}
}