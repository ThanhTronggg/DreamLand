package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_san_pham", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maSanPham;

    @Column(columnDefinition = "varchar(255)", name = "ten_san_pham")
    private String tenSanPham;

    @Column(columnDefinition = "int", name = "so_luong")
    private int soLuong;

    @Column(columnDefinition = "float", name = "gia_mua")
    private double giaMua;

    @Column(columnDefinition = "varchar(255)", name = "loai_san_pham")
    private String loaiSanPham;

    @Column(columnDefinition = "varchar(255)")
    private String anh;

    @Column(columnDefinition = "float", name = "gia_ban")
    private double giaBan;

    @OneToMany(mappedBy = "sanPham")
    private Set<ChiTietHoaDon> danhSachChiTietHD;
}
