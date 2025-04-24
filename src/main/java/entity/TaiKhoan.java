package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tai_khoan")
public class TaiKhoan {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "taikhoan_seq", sequenceName = "TaiKhoanSequence", allocationSize = 1)
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "tai_khoan", columnDefinition = "varchar(255)", nullable = false)
    private String taiKhoan;

    @Column(name = "mat_khau", columnDefinition = "varchar(255)")
    private String matKhau;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_Nhan_Vien", unique = true)
    private NhanVien nhanVien;

}