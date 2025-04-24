package entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phim")
public class Phim {

    @Id
    @SequenceGenerator(name = "phim_seq", sequenceName = "PhimSequence", allocationSize = 1)
    @Column(name = "ma_phim", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maPhim;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "ten_phim")
    private String tenPhim;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "the_loai")
    private String theLoai;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "dao_dien")
    private String daoDien;

    @Column(nullable = false, name = "thoi_luong")
    private int thoiLuong;

    @Column(columnDefinition = "date", nullable = false, name = "ngay_cong_chieu")
    private LocalDate ngayCongChieu;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "ngon_ngu")
    private String ngonNgu;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "quoc_gia")
    private String quocGia;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "trang_thai")
    private String trangThai;

    @Column(nullable = false, name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(nullable = false, name = "gia_thau")
    private double giaThau;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "anh")
    private String anh;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "trailer")
    private String trailer;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "tom_tat")
    private String tomTat;

    @OneToMany(mappedBy = "phim")
    @ToString.Exclude
    private Set<LichChieu> danhSachLichChieu;
}
