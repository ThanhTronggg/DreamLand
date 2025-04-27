package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "loai_ghe")
public class LoaiGhe implements Serializable {

    @Id
    @Column(name = "ma_loai_ghe", length = 6, nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maLoaiGhe;

    @Column(name = "ten_loai_ghe", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenLoaiGhe;

    @Column(name = "mo_ta_loai_ghe", columnDefinition = "NVARCHAR(500)", nullable = true)
    private String moTaLoaiGhe;

    @OneToMany(mappedBy = "loaiGhe")
    @ToString.Exclude
    private Set<Ghe> danhSachGhe;
}