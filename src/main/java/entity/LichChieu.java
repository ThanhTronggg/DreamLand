package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lich_chieu")
public class LichChieu {

    @Id
    @SequenceGenerator(name = "lichchieu_seq", sequenceName = "LichChieuSequence", allocationSize = 1)
    @Column(name = "ma_lich_chieu", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maLichChieu;

    @Column(nullable = false, name = "gio_bat_dau")
    private LocalDateTime gioBatDau;

    @Column(nullable = false, name = "gio_ket_thuc")
    private LocalDateTime gioKetThuc;

    @Column(nullable = false, name = "gia_mot_ghe")
    private double giaMotGhe;

    @ManyToOne
    @JoinColumn(name = "ma_phim")
    @ToString.Exclude
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    @ToString.Exclude
    private Phong phong;

    @OneToMany(mappedBy = "lichChieu")
    @ToString.Exclude
    private Set<Ve> danhSachVe;

    public LichChieu(LocalDateTime gioBatDau, LocalDateTime gioKetThuc, double giaMotGhe, Phong phong, Phim phim) {
        super();
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.giaMotGhe = giaMotGhe;
        this.phong = phong;
        this.phim = phim;
    }
}
