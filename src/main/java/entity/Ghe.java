package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ghe")
public class Ghe {

    @Id
    @Column(name = "ma_ghe", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maGhe;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "vi_tri")
    private String viTri;

    @ManyToOne
    @JoinColumn(name = "ma_loai_ghe")
    @ToString.Exclude
    private LoaiGhe loaiGhe;

    @ManyToOne
    @JoinColumn(name = "ma_phong")
    @ToString.Exclude
    private Phong phong;

    @OneToMany(mappedBy = "ghe")
    @ToString.Exclude
    private Set<Ve> danhSachVe;
}
