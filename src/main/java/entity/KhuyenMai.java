package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khuyen_mai_id", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maKhuyenMai;

    @Column(columnDefinition = "varchar(255)")
    private String tenKhuyenMai;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate ngayBatDau;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(columnDefinition = "float", nullable = false)
    private double phamTramKhuyenMai;

    @Column(columnDefinition = "float", nullable = false)
    private double tongHoaDonToiThieu;
}
