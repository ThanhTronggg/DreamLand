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
@Table(name = "ve")
public class Ve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ve", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private int maVe;

    @Column(nullable = false, name = "ngay_phat_hanh")
    private LocalDate ngayPhatHanh;

    @ManyToOne
    @JoinColumn(name = "ma_ghe")
    private Ghe ghe;

    @ManyToOne
    @JoinColumn(name = "ma_lich_chieu")
    private LichChieu lichChieu;

    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    private HoaDon hoaDon;
}
