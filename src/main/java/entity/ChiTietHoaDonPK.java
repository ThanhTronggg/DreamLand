package entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChiTietHoaDonPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;
}
