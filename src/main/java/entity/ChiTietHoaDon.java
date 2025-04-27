package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon implements Serializable {

    @EmbeddedId
    private ChiTietHoaDonPK id;

    @ManyToOne
    @MapsId("hoaDon")
    @JoinColumn(name = "ma_hoa_don", insertable = false, updatable = false)
    @ToString.Exclude
    private HoaDon hoaDon;

    @ManyToOne
    @MapsId("sanPham")
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    @ToString.Exclude
    private SanPham sanPham;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @Column(name = "thanh_tien", nullable = false)
    private double thanhTien;

    public ChiTietHoaDon(int soLuong, HoaDon hoaDon, SanPham sanPham) {
        this.soLuong = soLuong;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }

    public ChiTietHoaDon() {

    }
}
