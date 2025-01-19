package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lich_chieu")
public class LichChieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_lich_chieu", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maLichChieu;

    @Column(nullable = false, name = "gio_bat_dau")
    private LocalDateTime gioBatDau;

    @Column(nullable = false, name = "gio_ket_thuc")
    private LocalDateTime gioKetThuc;

    @Column(nullable = false, name = "gia_mot_ghe")
    private double giaMotGhe;

    @ManyToOne
    @JoinColumn(name = "ma_phim")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    private Phong phong;

    @OneToMany(mappedBy = "lichChieu")
    private Set<Ve> danhSachVe;
}
