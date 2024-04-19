package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.NhaCungCap;

public class DAO_NhaCungCap {
	private Connection con;

	public DAO_NhaCungCap() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * lấy danh sách các nhà cung cấp
	 * 
	 * @return list<NhaCungCap>
	 */
	public List<NhaCungCap> getDsNhaCung() {
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from NhaCungCap");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new NhaCungCap(rs.getString("maNCC").trim(), rs.getString("tenNCC"), rs.getString("sdt"),
						rs.getString("diaChi")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy nhà cung cấp mới nhất
	 * 
	 * @return Nha Cung Cap
	 */
	public NhaCungCap getNhaCungMoi() {
		NhaCungCap ncc = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select top(1)* from NhaCungCap order by maNCC desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ncc = new NhaCungCap(rs.getString("maNCC").trim(), rs.getString("tenNCC"), rs.getString("sdt"),
						rs.getString("diaChi"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ncc;
	}

	/**
	 * thêm nhà cung cấp mới
	 * 
	 * @param ncc
	 * @return boolean
	 */
	public boolean insertNcc(NhaCungCap ncc) {
		try {
			PreparedStatement statement = con
					.prepareStatement("insert into NhaCungCap(maNCC,tenNCC,sdt,diaChi) values(?,?,?,?)");
			statement.setString(1, ncc.getMaNCC());
			statement.setString(2, ncc.getTenNCC());
			statement.setString(3, ncc.getSdt());
			statement.setString(4, ncc.getDiaChi());

			int n = statement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	// khải
	/**
	 * cập nhật nhà cung cấp
	 * @param ncc
	 * @return
	 */
	public boolean updateNhaCungCap(NhaCungCap ncc) {
		PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement("update NhaCungCap set  tenNCC=?,sdt=?,diaChi=? where MANCC =?");
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getSdt());
			stmt.setString(3, ncc.getDiaChi());
			stmt.setString(4, ncc.getMaNCC());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	/**
	 * xoá nhà cung cấp
	 * @param ncc
	 * @return
	 */
	public boolean deleteNhaCungCap(NhaCungCap ncc) {
		PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement("delete from NhaCungCap where maNCC = ?");
			stmt.setString(1, ncc.getMaNCC());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

}
