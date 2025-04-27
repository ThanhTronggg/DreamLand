/*
 * @(#) KhachHangThongKeDAO.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import entity.DoanhThu;
import jakarta.persistence.EntityManager;
import org.jfree.data.category.DefaultCategoryDataset;
import util.JPAUtil;
import entity.KhachHangThongKe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Data Access Object for customer statistics using JPA
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */
public class KhachHangThongKeDAO extends GenericDAO<KhachHangThongKe, String> {

	public KhachHangThongKeDAO(Class<KhachHangThongKe> cls) { super(cls); }

	public KhachHangThongKeDAO(EntityManager em, Class<KhachHangThongKe> cls) { super(em, cls);}

	public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoNam(int year) {
		ArrayList<KhachHangThongKe> dsKhachHang = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT kh.maKhachHang, kh.tenKhachHang, SUM(hd.tongTien) " +
									"FROM KhachHang kh " +
									"JOIN kh.hoaDons hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY kh.maKhachHang, kh.tenKhachHang " +
									"ORDER BY SUM(hd.tongTien) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maKhachHang = (String) row[0];
				String tenKhachHang = (String) row[1];
				double tongTienChi = (double) row[2];
				dsKhachHang.add(new KhachHangThongKe(maKhachHang, tenKhachHang, tongTienChi));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoThang(int month, int year) {
		ArrayList<KhachHangThongKe> dsKhachHang = new ArrayList<>();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT kh.maKhachHang, kh.tenKhachHang, SUM(hd.tongTien) " +
									"FROM KhachHang kh " +
									"JOIN kh.hoaDons hd " +
									"WHERE FUNCTION('MONTH', hd.ngayDat) = :month AND FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY kh.maKhachHang, kh.tenKhachHang " +
									"ORDER BY SUM(hd.tongTien) DESC", Object[].class)
					.setParameter("month", month)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String maKhachHang = (String) row[0];
				String tenKhachHang = (String) row[1];
				double tongTienChi = (double) row[2];
				dsKhachHang.add(new KhachHangThongKe(maKhachHang, tenKhachHang, tongTienChi));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int month, int year) {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT kh.tenKhachHang, SUM(hd.tongTien) " +
									"FROM KhachHang kh " +
									"JOIN kh.hoaDons hd " +
									"WHERE FUNCTION('MONTH', hd.ngayDat) = :month AND FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY kh.tenKhachHang " +
									"ORDER BY SUM(hd.tongTien) DESC", Object[].class)
					.setParameter("month", month)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenKhachHang = (String) row[0];
				double tongTienChi = (double) row[1];
				dsKhachHang.addValue(tongTienChi, "Tổng tiền đã chi", tenKhachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int year) {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
		try {
			List<Object[]> results = em.createQuery(
							"SELECT kh.tenKhachHang, SUM(hd.tongTien) " +
									"FROM KhachHang kh " +
									"JOIN kh.hoaDons hd " +
									"WHERE FUNCTION('YEAR', hd.ngayDat) = :year " +
									"GROUP BY kh.tenKhachHang " +
									"ORDER BY SUM(hd.tongTien) DESC", Object[].class)
					.setParameter("year", year)
					.setMaxResults(5)
					.getResultList();

			for (Object[] row : results) {
				String tenKhachHang = (String) row[0];
				double tongTienChi = (double) row[1];
				dsKhachHang.addValue(tongTienChi, "Tổng tiền đã chi", tenKhachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoThang() {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
		try {
			// Tính toán oneYearAgo với kiểu LocalDate
			LocalDate oneYearAgo = LocalDate.now().minusYears(1);

			// Thực hiện truy vấn
			List<Object[]> results = em.createQuery(
							"SELECT FUNCTION('MONTH', hd.ngayDat), FUNCTION('YEAR', hd.ngayDat), COUNT(DISTINCT kh.maKhachHang) " +
									"FROM HoaDon hd " +
									"JOIN hd.khachHang kh " +
									"WHERE hd.ngayDat >= :oneYearAgo AND hd.ngayDat <= CURRENT_DATE " +
									"GROUP BY FUNCTION('MONTH', hd.ngayDat), FUNCTION('YEAR', hd.ngayDat) " +
									"ORDER BY FUNCTION('YEAR', hd.ngayDat) DESC, FUNCTION('MONTH', hd.ngayDat) DESC", Object[].class)
					.setParameter("oneYearAgo", oneYearAgo) // Gán tham số kiểu LocalDate
					.getResultList();

			// Xử lý kết quả
			for (Object[] row : results) {
				int thang = ((Number) row[0]).intValue();
				int nam = ((Number) row[1]).intValue();
				long soKhachHang = ((Number) row[2]).longValue();
				String thangNam = thang + "/" + nam;
				dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", thangNam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	// Thống kê số lượng khách hàng riêng biệt theo quý
	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoQuy() {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
		try {
			LocalDate twoYearsAgo = LocalDate.now().minusYears(2);

			List<Object[]> results = em.createQuery(
							"SELECT FUNCTION('QUARTER', hd.ngayDat), FUNCTION('YEAR', hd.ngayDat), COUNT(DISTINCT kh.maKhachHang) " +
									"FROM HoaDon hd " +
									"JOIN hd.khachHang kh " +
									"WHERE hd.ngayDat >= :twoYearsAgo AND hd.ngayDat <= CURRENT_DATE " +
									"GROUP BY FUNCTION('QUARTER', hd.ngayDat), FUNCTION('YEAR', hd.ngayDat) " +
									"ORDER BY FUNCTION('YEAR', hd.ngayDat) DESC, FUNCTION('QUARTER', hd.ngayDat) DESC", Object[].class)
					.setParameter("twoYearsAgo", twoYearsAgo)
					.getResultList();

			for (Object[] row : results) {
				int quy = ((Number) row[0]).intValue();
				int nam = ((Number) row[1]).intValue();
				long soKhachHang = ((Number) row[2]).longValue();
				String quyNam = "Q" + quy + "/" + nam;
				dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", quyNam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	// Thống kê số lượng khách hàng riêng biệt theo năm
	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoNam() {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
		try {
			LocalDate eightYearsAgo = LocalDate.now().minusYears(8);

			List<Object[]> results = em.createQuery(
							"SELECT FUNCTION('YEAR', hd.ngayDat), COUNT(DISTINCT kh.maKhachHang) " +
									"FROM HoaDon hd " +
									"JOIN hd.khachHang kh " +
									"WHERE hd.ngayDat >= :eightYearsAgo AND hd.ngayDat <= CURRENT_DATE " +
									"GROUP BY FUNCTION('YEAR', hd.ngayDat) " +
									"ORDER BY FUNCTION('YEAR', hd.ngayDat) DESC", Object[].class)
					.setParameter("eightYearsAgo", eightYearsAgo)
					.getResultList();

			for (Object[] row : results) {
				int nam = ((Number) row[0]).intValue();
				long soKhachHang = ((Number) row[1]).longValue();
				dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", String.valueOf(nam));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public static void main(String[] args) {
		KhachHangThongKeDAO khachHangThongKeDAO = new KhachHangThongKeDAO(KhachHangThongKe.class);
		DefaultCategoryDataset dsKhachHang = khachHangThongKeDAO.getSoLuongKhachHangPhanBietTheoNam();

		// In ra dữ liệu từ dataset
		for (int i = 0; i < dsKhachHang.getRowCount(); i++) {
			String series = (String) dsKhachHang.getRowKey(i);
			for (int j = 0; j < dsKhachHang.getColumnCount(); j++) {
				String category = (String) dsKhachHang.getColumnKey(j);
				Number value = dsKhachHang.getValue(i, j);
				System.out.println("Series: " + series + ", Category: " + category + ", Value: " + value);
			}
		}
	}
}