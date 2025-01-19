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
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khach_hang", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maKhachHang;

    @Column(name = "ten_khach_hang", columnDefinition = "varchar(255)", nullable = false)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String soDienThoai;

    @Column(columnDefinition = "varchar(255)", unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons;
}
