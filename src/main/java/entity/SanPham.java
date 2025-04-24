package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "san_pham")
public class SanPham {
    @Id
    @SequenceGenerator(name = "sanpham_seq", sequenceName = "SanPhamSequence", allocationSize = 1)
    @Column(name = "ma_san_pham", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maSanPham;

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

    public void dinhGiaBan() {
        this.giaBan = giaMua*2;
    }
}
