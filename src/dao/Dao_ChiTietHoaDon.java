package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ChiTietHoaDon;

public class Dao_ChiTietHoaDon {
	private Connection con;

	public Dao_ChiTietHoaDon() {
		con = MyConnection.getInstance().getConnection();
	}

	
	/**
	 * Thống kê
	 * @param maHD
	 * @return
	 */
	public List<ChiTietHoaDon> getDsChiTietHoaDon(String ngayLap_BD, String ngayLap_KT, int soLuong_BD, int soLuong_KT) {
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		PreparedStatement stmt;
		try {
			if(ngayLap_KT.equals(""))
				stmt = con.prepareStatement("select maSp, sum(soLuong) from ChiTietHoaDon cthd join HoaDon hd on cthd.maHD = hd.maHD"
						+ " where ngayLap = '"+ngayLap_BD+"' and (soLuong between "+soLuong_BD+" and "+soLuong_KT+") group by(maSp) having sum(soLuong) between "+soLuong_BD+" and "+soLuong_KT
						+"union (select maSp, sum(soLuong) from ChitietHoaDonCu cthd join HoaDon hd on cthd.maHD = hd.maHD"
						+ " where ngayLap = '"+ngayLap_BD+"' and (soLuong between "+soLuong_BD+" and "+soLuong_KT+") group by(maSp) having sum(soLuong) between "+soLuong_BD+" and "+soLuong_KT+")");
			else
				stmt = con.prepareStatement("select maSp, sum(soLuong) from ChiTietHoaDon cthd join HoaDon hd on cthd.maHD = hd.maHD"
					+ " where  ( ngayLap between '"+ngayLap_BD+"' and '"+ngayLap_KT+"') and (soLuong between "+soLuong_BD+" and "+soLuong_KT+") group by(maSp) having sum(soLuong) between "+soLuong_BD+" and "+soLuong_KT
					+"union (select maSp, sum(soLuong) from ChitietHoaDonCu cthd join HoaDon hd on cthd.maHD = hd.maHD"
					+ " where  ( ngayLap between '"+ngayLap_BD+"' and '"+ngayLap_KT+"') and (soLuong between "+soLuong_BD+" and "+soLuong_KT+") group by(maSp) having sum(soLuong) between "+soLuong_BD+" and "+soLuong_KT+")");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon x = new ChiTietHoaDon( rs.getString(1),"", rs.getInt(2), 0);
				ds.add(x);
			}
		} catch (Exception e) {
		}
		return ds;
	}

	/**
	 * lấy danh sách chi tiết hoá đơn theo mã hoá đơn
	 * @param maHD
	 * @return danh sách chi tiết hoá đơn
	 */
	public List<ChiTietHoaDon> getDsChiTietHoaDon(String maHD) {
		maHD = maHD.trim();
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("select * from ChiTietHoaDon where maHD = '"+maHD
					+ "' union (select * from ChiTietHoaDonCu where maHD = '"+maHD+"')");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon x = new ChiTietHoaDon( rs.getString("maSP"),maHD, rs.getInt("soLuong"), rs.getFloat("giaBan"));
				ds.add(x);
			}
		} catch (Exception e) {
		}
		return ds;
	}
	
	//sơn phạm
	public void insertChiTietHoaDon(ChiTietHoaDon cthd) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into ChitietHoaDon (maHD,maSp,giaBan,soLuong) values(?,?,?,?)");
			stmt.setString(1, cthd.getMaDH());
			stmt.setString(2, cthd.getMaSP());
			stmt.setFloat(3, cthd.getGiaBan());
			stmt.setInt(4, cthd.getSoLuong());
			stmt.executeUpdate();

		} catch (Exception e) {
		}
	}

	
	public List<ChiTietHoaDon> getDsChiTietHoaDon() {
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from ChiTietHoaDonCu union select * from  ChitietHoaDon");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon x = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maSp"), rs.getInt("soLuong"),
						rs.getFloat("giaBan"));
				ds.add(x);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	public ChiTietHoaDon timKiemChiTietHoaDon(String maHD, String maSP) {
		ChiTietHoaDon cthd = null;
		try {
			PreparedStatement stmt =con.prepareStatement("select * from ChiTietHoaDon where maHD = ? and maSp = ?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cthd = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maSp"), rs.getInt("soLuong"),
						rs.getFloat("giaBan"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cthd;
	}
	public ChiTietHoaDon timKiemChiTietHoaDonCu(String maHD, String maSP) {
		ChiTietHoaDon cthd = null;
		try {
			PreparedStatement stmt =con.prepareStatement("select * from ChiTietHoaDonCu where maHD = ? and maSp = ?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cthd = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maSp"), rs.getInt("soLuong"),
						rs.getFloat("giaBan"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cthd;
	}
	
	public boolean deleteChiTietHoaDon(ChiTietHoaDon x) {
		try {
			String sql = "";
			if (new Dao_ChiTietHoaDon().timKiemChiTietHoaDon(x.getMaDH().trim(), x.getMaSP().trim())!=null) {
				sql = "delete ChitietHoaDon where maHD = ? and maSp = ?";
			}else if (new Dao_ChiTietHoaDon().timKiemChiTietHoaDonCu(x.getMaDH().trim(), x.getMaSP().trim())!=null) {
				sql = "delete ChitietHoaDon where maHD = ? and maSp = ?";
			}
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, x.getMaDH().trim());
			stmt.setString(2, x.getMaSP().trim());
			int n = stmt.executeUpdate();
			if (n>0) {
				PreparedStatement stmt1 = con.prepareStatement("update SanPham set soLuongTon = soLuongTon + ? where maSp =?");
				stmt1.setInt(1, x.getSoLuong());
				stmt1.setString(2, x.getMaSP());
				int a = stmt1.executeUpdate();
				if (a>0) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
}
