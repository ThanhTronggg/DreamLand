package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai implements Serializable {
    @Id
    @SequenceGenerator(name = "khuyenmai_seq", sequenceName = "KhuyenMaiSequence", allocationSize = 1)
    @Column(name = "ma_khuyen_mai", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maKhuyenMai;

    @Column(columnDefinition = "varchar(255)", name = "ten_khuyen_mai")
    private String tenKhuyenMai;

    @Column(columnDefinition = "date", nullable = false, name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(columnDefinition = "date", nullable = false, name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(columnDefinition = "float", nullable = false, name = "phan_tram_khuyen_mai")
    private double phanTramKhuyenMai;

    @Column(columnDefinition = "float", nullable = false, name = "tong_hoa_don_toi_thieu")
    private double tongHoaDonToiThieu;

    @OneToMany(mappedBy = "khuyenMai")
    @ToString.Exclude
    private Set<HoaDon> hoaDons;

    public KhuyenMai(String tenKhuyenMai, LocalDate ngayBatDau, LocalDate ngayKetThuc, double phanTramKhuyenMai, double tongHoaDonToiThieu) {
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.tongHoaDonToiThieu = tongHoaDonToiThieu;
    }

    public KhuyenMai() {

    }
}
