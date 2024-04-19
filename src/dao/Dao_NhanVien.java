package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.NhanVien;

public class Dao_NhanVien {
	private Connection con;
	
	public Dao_NhanVien() {
		con = MyConnection.getInstance().getConnection();
	}
	/**
	 * danh sách nhân viên còn làm
	 * @return
	 */
	public List<NhanVien> getDsNhanVien() {
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			PreparedStatement stmt = con.prepareStatement("select *  from NhanVien where conLam = 1 and chucVu = 'Nhân Viên'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV").trim(), rs.getString("tenNV"),rs.getString("sdt"), rs.getString("hinhAnh"), rs.getString("email"),rs.getString("chucVu"));
					ds.add(nv);
			}
		} catch (SQLException e) {
		}
		return ds;
	}
	
	/**
	 * lấy danh sách nhân viên dựa vào tìm kiếm
	 * @param value
	 * @param criteria
	 * @return
	 */
	public List<NhanVien> getDsNhanVien(String value, String criteria) {
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			PreparedStatement stmt = con.prepareStatement("select *  from NhanVien where conLam = 1 and chucVu = N'Nhân Viên' and "+criteria+" like N'%"+value+"%'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"),rs.getString("sdt"), rs.getString("hinhAnh"), rs.getString("email"),rs.getString("chucVu"));
					ds.add(nv);
			}
		} catch (SQLException e) {
		}
		return ds;
	}
	
	
	/**
	 * lấy đường dẫn hình ảnh của nhân viên
	 * @param maNV
	 * @return
	 */
	public String getPathAvatar(String maNV) {
		try {
			PreparedStatement stmt = con.prepareStatement("select hinhAnh from NhanVien where maNV = ?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("hinhAnh");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * tìm nhân viên bằng mã nhân viên
	 * @param maNV
	 * @return
	 */
	public NhanVien getNhanVien(String maNV) {
		NhanVien nv = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from NhanVien where maNV = ?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString("maNV").trim(), rs.getString("tenNV"),rs.getString("sdt"), rs.getString("hinhAnh"), rs.getString("email"),rs.getString("chucVu"),rs.getBoolean("conLam"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	public boolean updateNhanVien(NhanVien nv ) {
		try {
			PreparedStatement stmt = con.prepareStatement("update NhanVien set tenNV= ? , chucVu = ?, sdt=? ,hinhAnh =? , email= ? , conLam = ? where  maNV = ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getChucVu());
			stmt.setString(3, nv.getSdt());
			stmt.setString(4, nv.getHinhAnh());
			stmt.setString(5, nv.getEmail());
			stmt.setBoolean(6, nv.getConLam());
			stmt.setString(7, nv.getMaNV());
			
			int n =  stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	
	//son lun
	public boolean insertNhanVien(NhanVien nv) {
		try {
			PreparedStatement stmt = con.prepareStatement(
				"insert into NhanVien values(?,?,?,?,?,?,?)");
				stmt.setString(1, nv.getMaNV());
				stmt.setString(2, nv.getTenNV());
				stmt.setString(3, nv.getChucVu());
				stmt.setString(4, nv.getSdt());
				stmt.setString(5, nv.getHinhAnh());
				stmt.setString(6, nv.getEmail());
				stmt.setBoolean(7, nv.getConLam());
				int n = stmt.executeUpdate();
				if (n > 0)
					return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	/**
	 * Lấy mã nhân viên cuối cùng của năm
	 * @param year : năm cần lấy
	 * @return mã nhân viên cuối cùng của năm
	 */
	public String getNhanVienCuoi(String year) {
		try {
			PreparedStatement stmt = con.prepareStatement("select top(1) maNV from NhanVien where maNV like 'NV"+year+"%' order by maNV desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("maNV").trim();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "";
	}
	
	/**
	 * Cập nhật lại trạng thái còn làm của nhân viên là false
	 * @param maNV : mã nhân viên cần xóa
	 * @return kết quả xóa
	 */
	public boolean deleteNhanVien(String maNV) {
		try {
			PreparedStatement stmt = con.prepareStatement("update NhanVien set conLam = 0 where  maNV = ?");
			stmt.setString(1, maNV);
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
