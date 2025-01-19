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
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhan_vien", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maNhanVien;

    @Column(name = "ho_ten", columnDefinition = "varchar(255)", nullable = false)
    private String hoTen;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "ngay_sinh", columnDefinition = "date", nullable = false)
    private LocalDate ngaySinh;

    @Column(columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String email;

    @Column(name = "so_dien_thoai", columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String soDienThoai;

    @Column(name = "vai_tro", columnDefinition = "varchar(255)", nullable = false)
    private String vaiTro;

    @Column(name = "ngay_bat_dau_lam", columnDefinition = "date", nullable = false)
    private LocalDate ngayBatDauLam;

    private double luong;

    @Column(name = "trang_thai", columnDefinition = "varchar(255)", nullable = false)
    private String trangThai;

    @OneToOne(mappedBy = "nhanVien")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "nhanVien")
    private Set<HoaDon> hoaDons;
}
