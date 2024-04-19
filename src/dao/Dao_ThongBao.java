package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ThongBao;

public class Dao_ThongBao {
	private Connection con;
	
	public Dao_ThongBao() {
		con = MyConnection.getInstance().getConnection();
	}
	
	public List<ThongBao> getDsThongBao(String maNV) {
		List<ThongBao> ds = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select top(5)* from ThongBao where maNV = ? order by ngayThongBao desc");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new ThongBao(rs.getString("maTB"), rs.getString("maNV"),  rs.getString("chiTiet"),rs.getDate("ngayThongBao"), rs.getBoolean("trangThai")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	public List<ThongBao> getDsThongBaoNoSee(String maNV) {
		List<ThongBao> ds = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select top(5)* from ThongBao where maNV = ? and trangThai = 1 order by ngayThongBao desc");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new ThongBao(rs.getString("maTB"), rs.getString("maNV"),  rs.getString("chiTiet"),rs.getDate("ngayThongBao"), rs.getBoolean("trangThai")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	public boolean updateTrangThaiThongBao(String maTB) {
		try {
			PreparedStatement stmt = con.prepareStatement("update ThongBao set trangThai = 0 where maTB = ?");
			stmt.setString(1, maTB);
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	/**
	 * Lấy mã thông báo cuối cùng
	 * @return mã thông báo cuối cùng
	 */
	public String getMaTBCuoi() {
		try {
			PreparedStatement stmt = con.prepareStatement("select top(1) maTB from ThongBao order by maTB desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("maTB").trim();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "";
	}
	
	/**
	 * Thêm mới 1 thông báo
	 * @param tb : thông báo cần thêm
	 * @return kết quả thêm
	 */
	public boolean insertNotication(ThongBao tb) {
		try {
			String sql = "insert into ThongBao values(?,?,?,?,0)";
			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, tb.getMaTB());
				stmt.setString(2, tb.getMaNV());
				stmt.setDate(3, new java.sql.Date(tb.getNgayThongBao().getTime()));
				stmt.setString(4, tb.getChiTiet());
				int n = stmt.executeUpdate();
				if (n > 0)
					return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
