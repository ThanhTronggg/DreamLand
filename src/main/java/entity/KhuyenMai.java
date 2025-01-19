package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khuyen_mai", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maKhuyenMai;

    @Column(columnDefinition = "varchar(255)", name = "ten_khuyen_mai")
    private String tenKhuyenMai;

    @Column(columnDefinition = "date", nullable = false, name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(columnDefinition = "date", nullable = false, name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(columnDefinition = "float", nullable = false, name = "phan_tram_khuyen_mai")
    private double phamTramKhuyenMai;

    @Column(columnDefinition = "float", nullable = false, name = "tong_hoa_don_toi_thieu")
    private double tongHoaDonToiThieu;
}
