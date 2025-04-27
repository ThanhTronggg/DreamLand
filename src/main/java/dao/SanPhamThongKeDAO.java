/*
 * @(#) SanPhamThongKeDAO.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import entity.PhimThongKe;
import jakarta.persistence.EntityManager;
import org.jfree.data.category.DefaultCategoryDataset;
import util.JPAUtil;
import entity.SanPhamThongKe;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Data Access Object for product statistics using JPA
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */
public class SanPhamThongKeDAO extends GenericDAO<SanPhamThongKe, String>  {

	public SanPhamThongKeDAO(Class<SanPhamThongKe> cls) { super(cls); }

	public SanPhamThongKeDAO(EntityManager em, Class<SanPhamThongKe> cls) { super(em, cls);}


	public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoThang(int month, int year) {
		ArrayList<SanPhamThongKe> dsSanPhamThongKe = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT sp.maSanPham, sp.tenSanPham, SUM(ct.soLuong), SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('MONTH', hd.ngayDat) = :month AND FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY sp.maSanPham, sp.tenSanPham " +
									"ORDER BY SUM(ct.thanhTien) DESC, SUM(ct.soLuong) DESC", Object[].class)
					.setParameter("month", month)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maSanPham = (String) row[0];
				String tenSanPham = (String) row[1];
				long soLuongDaBan = (long) row[2];
				double tongTienBanDuoc = (double) row[3];
				SanPhamThongKe spThongKe = new SanPhamThongKe(maSanPham, tenSanPham, (int) soLuongDaBan, tongTienBanDuoc);
				dsSanPhamThongKe.add(spThongKe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSanPhamThongKe;
	}

	public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoNam(int year) {
		ArrayList<SanPhamThongKe> dsSanPhamThongKe = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT sp.maSanPham, sp.tenSanPham, SUM(ct.soLuong), SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY sp.maSanPham, sp.tenSanPham " +
									"ORDER BY SUM(ct.thanhTien) DESC, SUM(ct.soLuong) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maSanPham = (String) row[0];
				String tenSanPham = (String) row[1];
				long soLuongDaBan = (long) row[2];
				double tongTienBanDuoc = (double) row[3];
				SanPhamThongKe spThongKe = new SanPhamThongKe(maSanPham, tenSanPham, (int) soLuongDaBan, tongTienBanDuoc);
				dsSanPhamThongKe.add(spThongKe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsSanPhamThongKe;
	}

	public DefaultCategoryDataset getDoanhThuSanPhamTheoNamBD(int year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT sp.tenSanPham, SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY sp.tenSanPham " +
									"ORDER BY SUM(ct.thanhTien) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenSanPham = (String) row[0];
				double tongTienBanDuoc = (double) row[1];
				dataset.addValue(tongTienBanDuoc, "Tổng tiền bán được", tenSanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public DefaultCategoryDataset getDoanhThuSanPhamTheoThangBD(int year, int month) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT sp.tenSanPham, SUM(ct.thanhTien) " +
									"FROM ChiTietHoaDon ct " +
									"JOIN ct.sanPham sp " +
									"JOIN ct.hoaDon hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year AND FUNCTION('MONTH', hd.ngayDat) = :month " +
									"GROUP BY sp.tenSanPham " +
									"ORDER BY SUM(ct.thanhTien) DESC", Object[].class)
					.setParameter("year", year)
					.setParameter("month", month)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenSanPham = (String) row[0];
				double tongTienBanDuoc = (double) row[1];
				dataset.addValue(tongTienBanDuoc, "Tổng tiền bán được", tenSanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}
}