package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.MyConnection;
import entity.TaiKhoan;

public class Dao_TaiKhoan {
	private Connection con;
	public Dao_TaiKhoan() {
		con = MyConnection.getInstance().getConnection();
	}
	
	
	/**
	 * lấy tài khoản
	 * @param tk
	 * @param mk
	 * @return TaiKhoan
	 */
	public TaiKhoan checkAccount(String tk, String mk) {
		TaiKhoan taiKhoan = null;
		try {
			PreparedStatement prstmt = con.prepareStatement("select * from TaiKhoan where taiKhoan = ? and matKhau = ?");
			prstmt.setString(1,tk);
			prstmt.setString(2, mk);
			ResultSet rs = prstmt.executeQuery();
			while (rs.next()) {
				taiKhoan = new TaiKhoan(rs.getString("taiKhoan"), rs.getString("matKhau"), rs.getString("loaiTaiKhoan"), rs.getString("maKH"), rs.getString("maNV"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoan; 
	}
	
	/**
	 * tìm tài khoản với tên tài khoản và số điện thoại
	 * @param tk
	 * @param sdt
	 * @return TaiKhoan
	 */
	public TaiKhoan timKiemAccount(String tk, String sdt) {
		TaiKhoan taiKhoan = null;
		try {
			PreparedStatement prstmt = con.prepareStatement(" select * from TaiKhoan where taiKhoan = ? and maKH = (select KhachHang.maKh from KhachHang where sdt = ?)");
			prstmt.setString(1,tk);
			prstmt.setString(2, sdt);
			ResultSet rs = prstmt.executeQuery();
			while (rs.next()) {
				taiKhoan = new TaiKhoan(rs.getString("taiKhoan").trim(), rs.getString("matKhau").trim(), rs.getString("loaiTaiKhoan").trim(), rs.getString("maKH").trim(), rs.getString("maNV")+"".trim() );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoan; 
	}
	
	
	
	
	/**
	 * Thêm tài khoản
	 * @param tk
	 * @return boolean
	 */
	public boolean insertTaiKhoanKhach(TaiKhoan tk ) {
		try {
			PreparedStatement statement = con.prepareStatement("insert into TaiKhoan(taiKhoan,matKhau,loaiTaiKhoan,maKH) values(?,?,?,?)");
			statement.setString(1, tk.getTaiKhoan());
			statement.setString(2, tk.getMatKhau());
			statement.setString(3, tk.getLoaiTaiKhoan());
			statement.setString(4, tk.getMaKH());
			int n = statement.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	
	/**
	 * cập nhật mật khẩu mới
	 * @param tenTk
	 * @param mkMoi
	 * @return boolean
	 */
	public boolean updateMatKhau(String tenTk, String mkMoi) {
		try {
			PreparedStatement statement = con.prepareStatement(" update TaiKhoan set matKhau = ? where taiKhoan = ?");
			statement.setString(1, mkMoi);
			statement.setString(2, tenTk);
			int n = statement.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * Tìm kiếm tài khoản
	 * @param maNV
	 * @param maKh
	 * @return
	 */
	public TaiKhoan timKiemTaiKhoan(String maNV, String maKh) {
		TaiKhoan tk = null;
		
		try {
			String sql = "";
			PreparedStatement statement;
			if (maNV==null && maKh !=null) {
				sql = "select * from TaiKhoan where maKh = ?";
				statement=con.prepareStatement(sql);
				statement.setString(1, maKh);
			}else {
				sql = "select * from TaiKhoan where maNV = ?";
				statement=con.prepareStatement(sql);
				statement.setString(1, maNV);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tk = new TaiKhoan(rs.getString("taiKhoan").trim(), rs.getString("matKhau").trim(), rs.getString("loaiTaiKhoan"), rs.getString("maKH"), rs.getString("maNV"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return tk;
	}
	
	
}
