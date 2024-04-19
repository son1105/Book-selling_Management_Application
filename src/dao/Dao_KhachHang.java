package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.MyConnection;
import entity.KhachHang;

public class Dao_KhachHang {
	private Connection con;

	public Dao_KhachHang() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * lấy khách hàng mới nhất
	 * 
	 * @return
	 */
	public KhachHang getKhachHangMoi() {
		KhachHang khachHang = null;
		try {
			PreparedStatement statement = con.prepareStatement("select top(1)* from KhachHang order by maKh desc");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				khachHang = new KhachHang();
				khachHang.setMaKH(rs.getString("maKh"));
				khachHang.setSdt(rs.getString("sdt"));
				khachHang.setHoTen(rs.getString("hoTen"));
				khachHang.setDiaChi(rs.getString("diaChi"));
				khachHang.setGioHang(rs.getString("gioHang"));
				khachHang.setHinhAnh(rs.getString("hinhAnh"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return khachHang;
	}

	public KhachHang timKiemKhachHanh(String maKH) {
		KhachHang khachHang = null;
		try {
			PreparedStatement statement = con.prepareStatement("select * from KhachHang where maKh = ?");
			statement.setString(1, maKH);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				khachHang = new KhachHang();
				khachHang.setMaKH(rs.getString("maKh"));
				khachHang.setSdt(rs.getString("sdt"));
				khachHang.setHoTen(rs.getString("hoTen"));
				khachHang.setDiaChi(rs.getString("diaChi"));
				khachHang.setGioHang(rs.getString("gioHang"));
				khachHang.setHinhAnh(rs.getString("hinhAnh"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return khachHang;
	}
	
	/**
	 * Thêm khách hàng mới
	 * 
	 * @param kh
	 * @return
	 */
	public boolean insertKhachHang(KhachHang kh) {
		try {
			PreparedStatement statement = con
					.prepareStatement("insert into KhachHang (maKh,sdt,hoTen,diaChi)values(?,?,?,?)");
			statement.setString(1, kh.getMaKH());
			statement.setString(2, kh.getSdt());
			statement.setString(3, kh.getHoTen());
			statement.setString(4, kh.getDiaChi());

			int n = statement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	/**
	 * cập nhật giỏ hàng
	 * @param maSP
	 * @param maKH
	 * @return
	 */
	public boolean updateGioHang(String maSP , String maKH) {
		try {
			PreparedStatement statement = con.prepareStatement("update KhachHang set gioHang =  ? where maKh =?");
			statement.setString(1, maSP);
			statement.setString(2, maKH);
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
	 * Cập nhật thông tin khách hàng
	 * @param kh : khách hàng cần cập nhật
	 * @return kết quả cập nhật
	 */
	public boolean updateKhachHang(KhachHang kh) {
		try {
			PreparedStatement stmt = con.prepareStatement("update KhachHang set sdt= ? , hoTen = ?, diaChi=? ,hinhAnh =? where  maKH = ?");
			stmt.setString(1, kh.getSdt());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getDiaChi());
			stmt.setString(4, kh.getHinhAnh());
			stmt.setString(5, kh.getMaKH());
			int n =  stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
}
