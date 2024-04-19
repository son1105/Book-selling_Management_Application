package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.HoaDonDoiTra;

public class DAO_HoaDonDoiTra {
	private Connection con ;
	public DAO_HoaDonDoiTra() {
		con = MyConnection.getInstance().getConnection();
	}
	
	/**
	 * lấy danh sách hoá đơn cần đổi tra
	 * @return List<HoaDonDoiTra>
	 */
	public List<HoaDonDoiTra> getAll(String maNV) {
		List<HoaDonDoiTra> ds = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from HoaDonDoiTra where maNV = ?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new HoaDonDoiTra(rs.getString("maHD"),rs.getString("maKh"),rs.getString("maNV"),rs.getString("diaChi"),rs.getDate("ngayGiao")));
			}
		} catch (Exception e) {
		}
		return ds;
	}
	
	/**
	 * xoá hoá đơn đổi trả
	 * @param maHD
	 * @return
	 */
	public boolean deleteHoaDonDoiTra(String maHD) {
		try {
			PreparedStatement statement = con.prepareStatement("delete HoaDonDoiTra where maHD = ?");
			statement.setString(1, maHD);
			int n= statement.executeUpdate();
			if (n> 0) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * tìm kiếm hoá đơn
	 * @param maHD
	 * @return
	 */
	public HoaDonDoiTra timHoaDonDoiTra(String maHD) {
		HoaDonDoiTra hoaDonDoiTra =null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from HoaDonDoiTra where maHD = ?");
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				hoaDonDoiTra = new HoaDonDoiTra(rs.getString("maHD"),rs.getString("maKh"),rs.getString("maNV"),rs.getString("diaChi"),rs.getDate("ngayGiao"));
			}
		} catch (Exception e) {
		}
		return hoaDonDoiTra;
	}
	
	/**
	 * Thêm hoá đơn đổi trả
	 * @param x
	 * @return
	 */
	public boolean insertHoaDonDoiTra(HoaDonDoiTra x) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("insert into HoaDonDoiTra(maHD,maKh,maNV,diaChi,ngayGiao) values(?,?,?,?,?)");
			preparedStatement.setString(1, x.getMaHD());
			preparedStatement.setString(2, x.getMaKh());
			preparedStatement.setString(3, x.getMaNV());
			preparedStatement.setString(4, x.getDiaChi());
			preparedStatement.setDate(5,(java.sql.Date) x.getNgayGiao());
			
			int n = preparedStatement.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println( ((float)30/100));
	}
}
