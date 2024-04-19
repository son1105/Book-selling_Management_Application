package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.LoaiSanPhan;

public class DAO_LoaiSanPham {
	private Connection con;
	
	public DAO_LoaiSanPham() {
		con = MyConnection.getInstance().getConnection();
	}
	
	/**
	 * lấy danh sách loại sản phẩm theo phân loại sản phẩm
	 * @param phanLoai
	 * @return loaiSanPham
	 */
	public List<LoaiSanPhan> getDSloai(String phanLoai) {
		List<LoaiSanPhan> ds = new ArrayList<LoaiSanPhan>();
		try {
			PreparedStatement stmt = con.prepareStatement("select distinct maLoai, tenLoai = (select LoaiSP.tenLoai from LoaiSP where LoaiSP.maLoai = SanPham.maLoai) from SanPham where phanLoai = ?");
			stmt.setString(1, phanLoai);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new LoaiSanPhan(rs.getString("maLoai").trim(),rs.getString("tenLoai").trim()));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	
	/**
	 * lấy loại sản phẩm mới nhất
	 * @return LoaiSanPham
	 */
	public LoaiSanPhan getloaiMoi() {
		LoaiSanPhan loaiSanPhan = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select top(1)* from LoaiSP order by maLoai desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				loaiSanPhan=new LoaiSanPhan(rs.getString("maLoai").trim(),rs.getString("tenLoai").trim());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return loaiSanPhan;
	}
	
	/**
	 * lấy danh sách loại sản phẩm
	 * @return List<LoaiSanPham>
	 */
	public List<LoaiSanPhan> getDsloaiSp() {
		List<LoaiSanPhan> ds = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from LoaiSP");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ds.add(new LoaiSanPhan(rs.getString("maLoai").trim(),rs.getString("tenLoai").trim()));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	/**
	 * Thêm sản phẩm mới 
	 * @param loaiSanPhan
	 * @return boolean
	 */
	public boolean insertLoaiSanPham(LoaiSanPhan loaiSanPhan) {
		try {
			PreparedStatement statement = con.prepareStatement("insert into LoaiSP(maLoai,tenLoai) values(?,?)");
			statement.setString(1, loaiSanPhan.getMaLoai());
			statement.setString(2, loaiSanPhan.getTenLoai());
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
		 * cập nhật loại sản phẩm
		 * @param lsp
		 * @return
		 */
			public boolean updateLoaiSanPham(LoaiSanPhan lsp) {
				PreparedStatement stmt = null;
				try {
					
						stmt = con.prepareStatement(
						"update LoaiSP set  tenLoai=? where maLoai =?");
						stmt.setString(1,lsp.getTenLoai());
						stmt.setString(2,lsp.getMaLoai());
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
		 * xoá loại sản phẩm
		 * @param lsp
		 * @return
		 */
			public boolean deleteLoaiSanPham(LoaiSanPhan lsp) {
				PreparedStatement stmt = null;
				try {
					
						stmt = con.prepareStatement(
						"delete from LoaiSP where maLoai = ?");
						stmt.setString(1,lsp.getMaLoai());
						int n = stmt.executeUpdate();
						if (n>0) {
							return true;
						}
					} catch (Exception e) {
						e.getStackTrace();
					}
					return false;
				}
}
