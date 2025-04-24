package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ve")
public class Ve {

    @Id
    @SequenceGenerator(name = "ve_seq", sequenceName = "VeSequence", allocationSize = 1)
    @Column(name = "ma_ve", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String maVe;

    @Column(nullable = false, name = "ngay_phat_hanh")
    private LocalDate ngayPhatHanh;

    @ManyToOne
    @JoinColumn(name = "ma_ghe")
    @ToString.Exclude
    private Ghe ghe;

    @ManyToOne
    @JoinColumn(name = "ma_lich_chieu")
    @ToString.Exclude
    private LichChieu lichChieu;

    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    @ToString.Exclude
    private HoaDon hoaDon;
}
