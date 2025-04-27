/*
 * @(#) PhimThongKeDAO.java 1.0 Nov 8, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import entity.KhachHangThongKe;
import jakarta.persistence.EntityManager;
import org.jfree.data.category.DefaultCategoryDataset;
import util.JPAUtil;
import entity.PhimThongKe;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Data Access Object for movie statistics using JPA
 * @author: Thanh Trong
 * @date: Nov 8, 2024
 * @version: 1.0
 */
public class PhimThongKeDAO extends GenericDAO<PhimThongKe, String> {

	public PhimThongKeDAO(Class<PhimThongKe> cls) { super(cls); }

	public PhimThongKeDAO(EntityManager em, Class<PhimThongKe> cls) { super(em, cls);}


	public ArrayList<PhimThongKe> getThongKePhimTheoThang(int month, int year) {
		ArrayList<PhimThongKe> dsPhimThongKe = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT p.maPhim, p.tenPhim, COUNT(v.maVe), SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('MONTH', hd.ngayDat) = :month AND FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY p.maPhim, p.tenPhim " +
									"ORDER BY SUM(lc.giaMotGhe) DESC, COUNT(v.maVe) DESC", Object[].class)
					.setParameter("month", month)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maPhim = (String) row[0];
				String tenPhim = (String) row[1];
				long soLuotXem = (long) row[2];
				double tongTienVe = (double) row[3];
				PhimThongKe phimThongKe = new PhimThongKe(maPhim, tenPhim, (int) soLuotXem, tongTienVe);
				dsPhimThongKe.add(phimThongKe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhimThongKe;
	}

	public ArrayList<PhimThongKe> getThongKePhimTheoNam(int year) {
		ArrayList<PhimThongKe> dsPhimThongKe = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT p.maPhim, p.tenPhim, COUNT(v.maVe), SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY p.maPhim, p.tenPhim " +
									"ORDER BY SUM(lc.giaMotGhe) DESC, COUNT(v.maVe) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maPhim = (String) row[0];
				String tenPhim = (String) row[1];
				long soLuotXem = (long) row[2];
				double tongTienVe = (double) row[3];
				PhimThongKe phimThongKe = new PhimThongKe(maPhim, tenPhim, (int) soLuotXem, tongTienVe);
				dsPhimThongKe.add(phimThongKe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhimThongKe;
	}

	public DefaultCategoryDataset getThongKePhimTheoNamBD(int year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT p.tenPhim, SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY p.tenPhim " +
									"ORDER BY SUM(lc.giaMotGhe) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenPhim = (String) row[0];
				double tongTienVe = (double) row[1];
				dataset.addValue(tongTienVe, "Tổng tiền vé", tenPhim);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public DefaultCategoryDataset getThongKePhimTheoThangBD(int year, int month) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT p.tenPhim, SUM(lc.giaMotGhe) " +
									"FROM Ve v " +
									"JOIN v.lichChieu lc " +
									"JOIN lc.phim p " +
									"JOIN v.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month " +
									"GROUP BY p.tenPhim " +
									"ORDER BY SUM(lc.giaMotGhe) DESC", Object[].class)
					.setParameter("year", year)
					.setParameter("month", month)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenPhim = (String) row[0];
				double tongTienVe = (double) row[1];
				dataset.addValue(tongTienVe, "Tổng tiền vé", tenPhim);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}
}