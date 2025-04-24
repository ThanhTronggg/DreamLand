package dao;

import entity.Ghe;
import entity.TaiKhoan;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.mindrot.jbcrypt.BCrypt;
import util.JPAUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaiKhoanDAO extends GenericDAO<TaiKhoan, Integer>{

    public TaiKhoanDAO(EntityManager em, Class<TaiKhoan> cls) {
        super(em, cls);
    }

    public TaiKhoanDAO(Class<TaiKhoan> cls) {
        super(cls);
    }

    public TaiKhoan getTaiKhoanTheoUsername(String username) {
        String query = "SELECT tk FROM TaiKhoan tk WHERE tk.taiKhoan = :taiKhoan";
        try {
            return em.createQuery(query, TaiKhoan.class)
                    .setParameter("taiKhoan", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public NhanVien getNhanVienTheoTaiKhoan(String username, boolean authentication) {
        if (getTaiKhoanTheoUsername(username) != null && authentication) {
            String query = "SELECT tk.nhanVien FROM TaiKhoan tk WHERE tk.taiKhoan = :taiKhoan";
            try {
                return em.createQuery(query, TaiKhoan.class)
                        .setParameter("taiKhoan", username)
                        .getSingleResult().getNhanVien();
            } catch (NoResultException e) {
                return null;
            }
        }
        return null;
    }

    public boolean doiMatKhau(String tenDangNhap, String matKhauHienTai, String matKhauMoi) {
        EntityTransaction transaction = em.getTransaction();
        try {
            // Begin transaction
            transaction.begin();

            // Find account by username
            TaiKhoan taiKhoan = getTaiKhoanTheoUsername(tenDangNhap);
            if (taiKhoan == null) {
                System.out.println("Tài khoản không tồn tại.");
                return false;
            }

            // Verify current password
            if (!BCrypt.checkpw(matKhauHienTai, taiKhoan.getMatKhau())) {
                System.out.println("Mật khẩu hiện tại không đúng.");
                return false;
            }

            // Hash new password
            String hashedNewPassword = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt());

            // Update password
            taiKhoan.setMatKhau(hashedNewPassword);
            em.merge(taiKhoan);

            // Commit transaction
            transaction.commit();
            System.out.println("Đổi mật khẩu thành công!");
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
