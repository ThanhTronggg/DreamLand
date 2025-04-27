package rmi;

import dao.*;
import entity.*;
import service.*;
import service.impl.*;
import util.JPAUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO(ChiTietHoaDon.class);
        GheDAO gheDao = new GheDAO(Ghe.class);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(HoaDon.class);
        KhachHangDAO khachHangDAO = new KhachHangDAO(KhachHang.class);
        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO(KhuyenMai.class);
        LichChieuDAO lichChieuDAO = new LichChieuDAO(LichChieu.class);
        LoaiGheDAO loaiGheDAO = new LoaiGheDAO(LoaiGhe.class);
        NhanVienDAO nhanVienDAO = new NhanVienDAO(NhanVien.class);
        PhimDAO phimDAO = new PhimDAO(Phim.class);
        PhongDAO phongDAO = new PhongDAO(Phong.class);
        SanPhamDAO sanPhamDAO = new SanPhamDAO(SanPham.class);
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(TaiKhoan.class);
        VeDAO veDAO = new VeDAO(Ve.class);

        DoanhThuDAO doanhThuDAO = new DoanhThuDAO(DoanhThu.class);
        KhachHangThongKeDAO khachHangThongKeDAO = new KhachHangThongKeDAO(KhachHangThongKe.class);
        PhimThongKeDAO phimThongKeDAO = new PhimThongKeDAO(PhimThongKe.class);
        SanPhamThongKeDAO sanPhamThongKeDAO = new SanPhamThongKeDAO(SanPhamThongKe.class);

        ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl(chiTietHoaDonDAO);
        GheService gheService = new GheServiceImpl(gheDao);
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDonDAO);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDAO);
        KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl(khuyenMaiDAO);
        LichChieuService lichChieuService = new LichChieuServiceImpl(lichChieuDAO);
        LoaiGheService loaiGheService = new LoaiGheServiceImpl(loaiGheDAO);
        NhanVienService nhanVienService = new NhanVienServiceImpl(nhanVienDAO);
        PhimService phimService = new PhimServiceImpl(phimDAO);
        PhongService phongService = new PhongServiceImpl(phongDAO);
        SanPhamService sanPhamService = new SanPhamServiceImpl(sanPhamDAO);
        TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl(taiKhoanDAO);
        VeService veService = new VeServiceImpl(veDAO);
        IdGeneratorService idGeneratorService = new IdGeneratorServiceImpl();

        DoanhThuService doanhThuService = new DoanhThuServiceImpl(doanhThuDAO);
        KhachHangThongKeService khachHangThongKeService = new KhachHangThongKeServiceImpl(khachHangThongKeDAO);
        PhimThongKeService phimThongKeService = new PhimThongKeServiceImpl(phimThongKeDAO);
        SanPhamThongKeService sanPhamThongKeService = new SanPhamThongKeServiceImpl(sanPhamThongKeDAO);

        context.bind("rmi://XXXXXX:9090/chiTietHoaDonService", chiTietHoaDonService);
        context.bind("rmi://XXXXXX:9090/gheService", gheService);
        context.bind("rmi://XXXXXX:9090/hoaDonService", hoaDonService);
        context.bind("rmi://XXXXXX:9090/khachHangService", khachHangService);
        context.bind("rmi://XXXXXX:9090/khuyenMaiService", khuyenMaiService);
        context.bind("rmi://XXXXXX:9090/lichChieuService", lichChieuService);
        context.bind("rmi://XXXXXX:9090/loaiGheService", loaiGheService);
        context.bind("rmi://XXXXXX:9090/nhanVienService", nhanVienService);
        context.bind("rmi://XXXXXX:9090/phimService", phimService);
        context.bind("rmi://XXXXXX:9090/phongService", phongService);
        context.bind("rmi://XXXXXX:9090/sanPhamService", sanPhamService);
        context.bind("rmi://XXXXXX:9090/taiKhoanService", taiKhoanService);
        context.bind("rmi://XXXXXX:9090/veService", veService);
        context.bind("rmi://XXXXXX:9090/idGeneratorService", idGeneratorService);

        context.bind("rmi://XXXXXX:9090/doanhThuService", doanhThuService);
        context.bind("rmi://XXXXXX:9090/khachHangThongKeService", khachHangThongKeService);
        context.bind("rmi://XXXXXX:9090/phimThongKeService", phimThongKeService);
        context.bind("rmi://XXXXXX:9090/sanPhamThongKeService", sanPhamThongKeService);

        System.out.println("Server starting...");
    }

}
