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
@Table(name = "ghe")
public class Ghe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ghe", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maGhe;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "vi_tri")
    private String viTri;

    @ManyToOne
    @JoinColumn(name = "ma_loai_ghe")
    private LoaiGhe loaiGhe;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    private Phong phong;

    @OneToMany(mappedBy = "ghe")
    private Set<Ve> danhSachVe;
}
