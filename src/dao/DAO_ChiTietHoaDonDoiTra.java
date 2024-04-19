package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.ChiTietHoaDonDoiTra;

public class DAO_ChiTietHoaDonDoiTra {
	private Connection con;
	
	public DAO_ChiTietHoaDonDoiTra() {
		con = MyConnection.getInstance().getConnection();
	}
	
	public List<ChiTietHoaDonDoiTra> getCTHDTheoMa(String maHD) {
		List<ChiTietHoaDonDoiTra> ds = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from ChiTietHoaDonDoiTra where maHD = ?");
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new ChiTietHoaDonDoiTra(rs.getString("maHD"), rs.getString("maSP"),rs.getInt("soLuong"),rs.getInt("phiDoiTra"), rs.getFloat("giaBan") ));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	/**
	 * Thêm hoá đơn đổi trả
	 * @param x
	 * @return
	 */
	public boolean insertChiTietHoaDonDoiTra(ChiTietHoaDonDoiTra x) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("insert into ChiTietHoaDonDoiTra(maHD,maSP,soLuong,giaBan,phiDoiTra)values(?,?,?,?,?)");
			preparedStatement.setString(1, x.getMaHD());
			preparedStatement.setString(2, x.getMaSP());
			preparedStatement.setInt(3, x.getSoLuong());
			preparedStatement.setFloat(4, x.getGiaBan());
			preparedStatement.setInt(5,x.getPhiDoiTra());
			
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
	
	public ChiTietHoaDonDoiTra timCthdDoiTra(String maHD, String maSP) {
		ChiTietHoaDonDoiTra chiTietHoaDonDoiTra = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from ChiTietHoaDonDoiTra where maHD = ? and maSP = ?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				chiTietHoaDonDoiTra=new ChiTietHoaDonDoiTra(rs.getString("maHD"), rs.getString("maSP"),rs.getInt("soLuong"),rs.getInt("phiDoiTra"), rs.getFloat("giaBan") );
			}	
		} catch (Exception e) {
			
		}
		return chiTietHoaDonDoiTra;
	}
	public static void main(String[] args) {
		System.out.println(new DAO_ChiTietHoaDonDoiTra().timCthdDoiTra("HD00000031", "SP220005"));
	}
}
