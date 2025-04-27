package entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "nhan_vien")
public class NhanVien implements Serializable {
    @Id
    @SequenceGenerator(name = "nhanvien_seq", sequenceName = "NhanVienSequence", allocationSize = 1)
    @Column(name = "ma_nhan_vien", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maNhanVien;

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

    @Column(name = "trang_thai", columnDefinition = "varchar(255)", nullable = false)
    private String trangThai;

    @OneToOne(mappedBy = "nhanVien")
    @ToString.Exclude
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "nhanVien")
    @ToString.Exclude
    private Set<HoaDon> hoaDons;

}
