package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tai_khoan")
public class TaiKhoan implements Serializable {
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
    @JoinColumn(name = "ma_nhan_vien", unique = true)
    @ToString.Exclude
    private NhanVien nhanVien;

    @Override
    public String toString() {
        return taiKhoan;
    }
}