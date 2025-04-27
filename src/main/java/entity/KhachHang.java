package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "khach_hang")
public class KhachHang implements Serializable {

    @Id
    @SequenceGenerator(name = "khachhang_seq", sequenceName = "KhachHangSequence", allocationSize = 1)
    @Column(name = "ma_khach_hang", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maKhachHang;

    @Column(name = "ten_khach_hang", columnDefinition = "varchar(255)", nullable = false)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String soDienThoai;

    @Column(columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons;
}
