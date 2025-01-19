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
@Table(name = "loai_ghe")
public class LoaiGhe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai_ghe", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maLoaiGhe;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "ten_loai_ghe", unique = true)
    private String tenLoaiGhe;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "mo_ta_loai_ghe")
    private String moTaLoaiGhe;

    @OneToMany(mappedBy = "loaiGhe")
    private Set<Ghe> danhSachGhe;
}
