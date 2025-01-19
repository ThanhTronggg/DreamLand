package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "chi_tiet_hoa_don")
@IdClass(ChiTietHoaDonPK.class) // Chỉ định lớp làm khóa chính
public class ChiTietHoaDon {
    @Id
    @Column(name = "ma_hoa_don", nullable = false)
    private int maHoaDon;

    @Id
    @Column(name = "ma_san_pham", nullable = false)
    private int maSanPham;

    @ManyToOne
    @JoinColumn(name = "ma_hoa_don", insertable = false, updatable = false) // Khóa ngoại tới HoaDon
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false) // Khóa ngoại tới SanPham
    private SanPham sanPham;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @Column(name = "gia_ban", nullable = false)
    private double giaBan;
}
