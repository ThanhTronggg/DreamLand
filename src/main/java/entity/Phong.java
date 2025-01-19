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
@Table(name = "phong")
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_phong", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maPhong;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "ten_phong")
    private String tenPhong;

    @Column(nullable = false, name = "so_luong_ghe")
    private int soLuongGhe;

    @OneToMany(mappedBy = "phong")
    private Set<Ghe> danhSachGhe;

    @OneToMany(mappedBy = "phong")
    private Set<LichChieu> danhSachLichChieu;
}
