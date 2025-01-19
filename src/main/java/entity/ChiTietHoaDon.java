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
public class ChiTietHoaDon {

    @EmbeddedId
    private ChiTietHoaDonPK id;

    @ManyToOne
    @MapsId("hoaDon")
    @JoinColumn(name = "ma_hoa_don", insertable = false, updatable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @MapsId("sanPham")
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    private SanPham sanPham;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @Column(name = "thanh_tien", nullable = false)
    private double thanhTien;
}
