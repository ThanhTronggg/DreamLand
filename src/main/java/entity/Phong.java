package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phong")
public class Phong {

    @Id
    @SequenceGenerator(name = "phong_seq", sequenceName = "PhongSequence", allocationSize = 1)
    @Column(name = "ma_phong", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maPhong;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "ten_phong")
    private String tenPhong;

    @Column(nullable = false, name = "so_luong_ghe")
    private int soLuongGhe;

    @OneToMany(mappedBy = "phong")
    private Set<Ghe> danhSachGhe;

    @OneToMany(mappedBy = "phong")
    private Set<LichChieu> danhSachLichChieu;
}
