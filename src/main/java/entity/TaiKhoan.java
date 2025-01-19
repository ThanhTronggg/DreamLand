package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tai_khoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "tai_khoan", columnDefinition = "varchar(255)", nullable = false)
    private String taiKhoan;

    @Column(name = "mat_khau", columnDefinition = "varchar(255)")
    private String matKhau;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_Nhan_Vien", unique = true)
    private NhanVien nhanVien;

}