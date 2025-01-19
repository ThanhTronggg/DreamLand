package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maHoaDon;

    @Column(name = "ngay_dat")
    private LocalDate ngayDat;

    @Column(name = "so_ghe")
    private int soGhe;

    @Column(columnDefinition = "varchar(255)", name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

    @ManyToOne()
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDon> danhSachChiTietHD;

    @Column(name = "tongTien")
    private double tongTien;

    @OneToMany(mappedBy = "hoaDon")
    private Set<Ve> danhSachVe;
}
