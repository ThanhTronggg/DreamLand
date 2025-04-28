package rmi;

import dao.*;
import entity.*;
import service.*;
import service.impl.*;
import util.JPAUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue()));
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(ip);
        System.setProperty("java.rmi.server.hostname", EnvironmentVariable.IP.getValue()); // Thay bằng IP thực tế của server

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

        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/chiTietHoaDonService", chiTietHoaDonService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/gheService", gheService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/hoaDonService", hoaDonService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/khachHangService", khachHangService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/khuyenMaiService", khuyenMaiService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/lichChieuService", lichChieuService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/loaiGheService", loaiGheService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/nhanVienService", nhanVienService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/phimService", phimService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/phongService", phongService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/sanPhamService", sanPhamService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/taiKhoanService", taiKhoanService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/veService", veService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/idGeneratorService", idGeneratorService);

        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/doanhThuService", doanhThuService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/khachHangThongKeService", khachHangThongKeService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/phimThongKeService", phimThongKeService);
        context.bind("rmi://"+EnvironmentVariable.IP.getValue()+":"+Integer.parseInt(EnvironmentVariable.PORT_SERVER.getValue())+"/sanPhamThongKeService", sanPhamThongKeService);

        System.out.println("Server starting...");
    }

}
