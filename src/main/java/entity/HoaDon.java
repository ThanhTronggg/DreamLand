package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @SequenceGenerator(name = "hoadon_seq", sequenceName = "HoaDonSequence", allocationSize = 1)
    @Column(name = "ma_hoa_don", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maHoaDon;

    @Column(name = "ngay_dat")
    private LocalDate ngayDat;

    @Column(name = "so_ghe")
    private int soGhe;

    @Column(columnDefinition = "varchar(255)", name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    @ToString.Exclude
    private KhachHang khachHang;

    @ManyToOne()
    @JoinColumn(name = "ma_nhan_vien")
    @ToString.Exclude
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDon> danhSachChiTietHD;

    @Column(name = "tongTien")
    private double tongTien;

    @ManyToOne()
    @JoinColumn(name = "ma_khuyen_mai")
    @ToString.Exclude
    private KhuyenMai khuyenMai;

    private double VAT;

    @OneToMany(mappedBy = "hoaDon")
    private Set<Ve> danhSachVe;

    public void setTongTien(ArrayList<ChiTietHoaDon> dschiTietHD, ArrayList<Ghe> dsVe, LichChieu lichChieu) {
        double tongTien = 0;
        for (ChiTietHoaDon chiTietHD : dschiTietHD) {
            tongTien += chiTietHD.getThanhTien();
        }
        for (Ghe ghe : dsVe) {
            if(ghe.getLoaiGhe().getTenLoaiGhe().equals("Ghế đôi Sweetbox")) {
                tongTien += lichChieu.getGiaMotGhe()*2;
            }
            if(ghe.getLoaiGhe().getTenLoaiGhe().equals("Ghế VIP")) {
                tongTien += lichChieu.getGiaMotGhe()*1.5;
            }
            if(ghe.getLoaiGhe().getTenLoaiGhe().equals("Ghế thường")) {
                tongTien += lichChieu.getGiaMotGhe();
            }
        }
        double phanTram = 0;
        if (khuyenMai != null) {
            phanTram = khuyenMai.getPhanTramKhuyenMai();
        }
        this.tongTien = (tongTien + tongTien*VAT)*(1-phanTram);
    }
}
