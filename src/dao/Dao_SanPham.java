package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import connectDB.MyConnection;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

public class Dao_SanPham {
	private Connection con;

	public Dao_SanPham() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * Thống kê
	 * @return
	 */
	public String getTenSanPham(String maSP) {
		try {
			PreparedStatement stmt = con.prepareStatement("select tenSP from SanPham where maSP ='"+maSP+"' union (select tenSP from SanPhamNgungBan where maSP = '"+maSP+"')");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SanPham> getDsSanPhamTheoSLTon(int soLuong_BD, int soLuong_KT) {
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement("select maSP, tenSP, soLuongTon from SanPham where soLuongTon between "+soLuong_BD+" and "+soLuong_KT
					+ " union select maSP, tenSP, soLuongTon from SanPham where soLuongTon between "+soLuong_BD+" and "+soLuong_KT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
					SanPham vpp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), "", "", new Date(System.currentTimeMillis()), "", "", rs.getInt("soLuongTon"), 0, 0.f, 0.f, 0.f);
					ds.add(vpp);
			}
		} catch (SQLException e) {
		}
		return ds;
	}
	
	/**
	 * lấy danh sách sản phẩm theo giá giảm từ giaBD đến giaKT
	 * @param giaBD
	 * @param giaKT
	 * @return
	 */
	public List<SanPham> getDsSanPhamTheoGiamGia(int giaBD, int giaKT) {
		List<SanPham> ds = new ArrayList<SanPham>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("select maSP, tenSP, giamGia, soLuongTon from SanPham where giamGia between "+giaBD+" and "+giaKT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), "", "", new Date(System.currentTimeMillis()), "", "", rs.getInt("soLuongTon"), rs.getInt("giamGia"), 0.f, 0.f, 0.f);
				ds.add(sp);
			}
		} catch (SQLException e) {
		}
		return ds;
	}
	
	
	
	
	/**
	 * lấy danh sách sản phẩm từ database
	 * 
	 * @return list<SanPham>
	 */
	public List<SanPham> getDsSanPham() {
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement("select *  from SanPham where soLuongTon > 0");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;

	}

	/**
	 * lấy danh sách sản phẩm từ database
	 * 
	 * @return list<SanPham>
	 */
	public List<SanPham> getDsSanPhamFull() {
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT maSP, tenSP, tenLoai = (SELECT LoaiSP.tenLoai FROM LoaiSP where LoaiSP.maLoai = SanPham.maLoai), tenNCC =(SELECT NhaCungCap.tenNCC FROM NhaCungCap where NhaCungCap.maNCC = SanPham.maNCC), giaNhap, soLuongTon, donGia, giamGia, moTa, HinhAnh, khoiLuong, mau, chatLieu, thuongHieu, soTrang, tacGia, nhaXuatBan, namXuatBan,phanLoai FROM SanPham");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(ds, (a,b)-> a.getMaSP().compareTo(b.getMaSP()));
		return ds;
	}

	/**
	 * Xoá Sản phẩm
	 * @param maSP
	 * @return
	 */
	public boolean deleteSanPham(String maSP) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from SanPham where maSP = ?");
			stmt.setString(1, maSP);
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
	 * Thêm Sản phẩm
	 * @param sp
	 * @param phanLoai
	 * @return
	 */
	public boolean insertSanPham(SanPham sp, String phanLoai) {
		try {
			if (phanLoai.equals("Sách")) {
				PreparedStatement stmt = con.prepareStatement(
						"insert into SanPham (maSP,tenSP,maLoai,maNCC,ngayNhap,giaNhap,soLuongTon,donGia,moTa,HinhAnh,khoiLuong,soTrang,tacGia,nhaXuatBan,namXuatBan,phanLoai,giamGia)"
								+ "values(?,?,?,?,GETDATE(),?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, sp.getMaSP());
				stmt.setString(2, sp.getTenSP());
				stmt.setString(3, sp.getMaLoai());
				stmt.setString(4, sp.getMaNcc());
				stmt.setFloat(5, sp.getGiaNhap());
				stmt.setInt(6, sp.getSoLuongTon());
				stmt.setFloat(7, sp.getGiaBan());
				stmt.setString(8, sp.getMoTa());
				stmt.setString(9, sp.getHinhAnh());
				stmt.setFloat(10, sp.getKhoiLuong());
				stmt.setInt(11, ((Sach) sp).getSoTrang());
				stmt.setString(12, ((Sach) sp).getTacGia());
				stmt.setString(13, ((Sach) sp).getNhaXuatBan());
				stmt.setInt(14, ((Sach) sp).getNamXB());
				stmt.setString(15, phanLoai);
				stmt.setInt(16, sp.getGiamGia());
				int n = stmt.executeUpdate();
				if (n > 0) {
					return true;
				}
			} else if (phanLoai.equals("Văn Phòng Phẩm")) {
				PreparedStatement stmt = con.prepareStatement(
						"insert into SanPham (maSP,tenSP,maLoai,maNCC,ngayNhap,giaNhap,soLuongTon,donGia,moTa,HinhAnh,khoiLuong,chatLieu,mau,thuongHieu,phanLoai,giamGia)"
								+ "values(?,?,?,?,GETDATE(),?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, sp.getMaSP());
				stmt.setString(2, sp.getTenSP());
				stmt.setString(3, sp.getMaLoai());
				stmt.setString(4, sp.getMaNcc());
				stmt.setFloat(5, sp.getGiaNhap());
				stmt.setInt(6, sp.getSoLuongTon());
				stmt.setFloat(7, sp.getGiaBan());
				stmt.setString(8, sp.getMoTa());
				stmt.setString(9, sp.getHinhAnh());
				stmt.setFloat(10, sp.getKhoiLuong());
				stmt.setString(11, ((VanPhongPham) sp).getChatLieu());
				stmt.setString(12, ((VanPhongPham) sp).getMau());
				stmt.setString(13, ((VanPhongPham) sp).getThuongHieu());
				stmt.setString(14, phanLoai);
				stmt.setInt(15, sp.getGiamGia());
				int n = stmt.executeUpdate();
				if (n > 0) {
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * lấy sản phẩm mới nhất
	 * @return
	 */
	public String getSanPhamCuoi() {
		String ma1 = "", ma2 = "";
		try {
			PreparedStatement stmt = con.prepareStatement("select top(1) maSP from SanPham where YEAR(ngayNhap) = YEAR(GETDATE()) order by maSP desc");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ma1 = rs.getString("maSP");
			}

			stmt = con.prepareStatement("select top(1) maSP from SanPhamNgungBan order by maSP desc");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ma2 = rs.getString("maSP");
			}
			if (ma1.compareTo(ma2) >= 1) {
				return ma1;
			} else if (ma1.compareTo(ma2) <= -1) {
				return ma2;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * cập nhật sản phẩm
	 * @param sp
	 * @return
	 */
	public boolean updateSanPham(SanPham sp) {
		PreparedStatement stmt = null;
		try {
			if (sp instanceof Sach) {
				stmt = con.prepareStatement(
						"update SanPham set tenSP= ?,maLoai=?,maNCC=?,giaNhap=?,giamGia=?,donGia=?,soLuongTon=?,"
						+ " moTa=?,HinhAnh=?,khoiLuong=?,mau=null,chatLieu=null,thuongHieu=null,soTrang=?,tacGia=?,"
						+ "nhaXuatBan=?,namXuatBan=?,phanLoai=? where maSP=?");
				stmt.setInt(11,((Sach)sp).getSoLuongTon());
				stmt.setString(12, ((Sach)sp).getTacGia());
				stmt.setString(13, ((Sach)sp).getNhaXuatBan());
				stmt.setInt(14,((Sach)sp).getNamXB());
				stmt.setString(15, "Sách");
				stmt.setString(16, sp.getMaSP());

				
			}
			else if (sp instanceof VanPhongPham) {
				stmt = con.prepareStatement(
						"update SanPham set tenSP= ?,maLoai=?,maNCC=?,giaNhap=?,giamGia=?,donGia=?,soLuongTon=?,"
						+ " moTa=?,HinhAnh=?,khoiLuong=?,mau=?,chatLieu=?,thuongHieu=?,soTrang=null,tacGia=null,"
						+ "nhaXuatBan=null,namXuatBan=null,phanLoai=? where maSP=?");
				stmt.setString(11,((VanPhongPham)sp).getMau());
				stmt.setString(12,((VanPhongPham)sp).getChatLieu());
				stmt.setString(13,((VanPhongPham)sp).getThuongHieu());
				stmt.setString(14, "Văn Phòng Phẩm");
				stmt.setString(15, sp.getMaSP());
				
			}
			stmt.setString(1, sp.getTenSP());
			stmt.setString(2, sp.getMaLoai());
			stmt.setString(3, sp.getMaNcc());
			stmt.setFloat(4,sp.getGiaNhap());
			stmt.setInt(5, sp.getGiamGia());
			stmt.setFloat(6, sp.getGiaBan());
			stmt.setInt(7, sp.getSoLuongTon());
			stmt.setString(8,sp.getMoTa());
			stmt.setString(9, sp.getHinhAnh());
			stmt.setFloat(10, sp.getKhoiLuong());
			int n = stmt.executeUpdate();
			if (n>0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * lấy sản phẩm giảm giá
	 * @return
	 */
	public List<SanPham> getDsSanPhamGiamGia() {
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT maSP, tenSP, tenLoai = (SELECT LoaiSP.tenLoai FROM LoaiSP where LoaiSP.maLoai = SanPham.maLoai), tenNCC =(SELECT NhaCungCap.tenNCC FROM NhaCungCap where NhaCungCap.maNCC = SanPham.maNCC), giaNhap, soLuongTon, donGia, giamGia, moTa, HinhAnh, khoiLuong, mau, chatLieu, thuongHieu, soTrang, tacGia, nhaXuatBan, namXuatBan,phanLoai FROM SanPham where giamGia > 0");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(ds, (a,b)-> a.getMaSP().compareTo(b.getMaSP()));
		return ds;
	}
	
	/**
	 * tìm kiếm sản phẩm với mã
	 * @param maSP
	 * @return
	 */
	public SanPham timKiemSanPham(String maSP) {
		SanPham sanPham = null;
		try {
			PreparedStatement statement = con.prepareStatement("SELECT maSP, tenSP, tenLoai = (SELECT LoaiSP.tenLoai FROM LoaiSP where LoaiSP.maLoai = SanPham.maLoai), tenNCC =(SELECT NhaCungCap.tenNCC FROM NhaCungCap where NhaCungCap.maNCC = SanPham.maNCC), giaNhap, soLuongTon, donGia, giamGia, moTa, HinhAnh, khoiLuong, mau, chatLieu, thuongHieu, soTrang, tacGia, nhaXuatBan, namXuatBan,phanLoai FROM SanPham where maSP = ?");
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					sanPham = new Sach(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					sanPham = new VanPhongPham(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return sanPham;
	}
	
	/**
	 * tìm kiếm sản phẩm được mua nhiều nhất
	 * @return
	 */
	public List<String> getDsMaSPMuaNhieu() {
		List<String> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select maSp from ChitietHoaDon group by maSp order by sum(soLuong) desc");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(rs.getString("maSp"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
	
	public List<SanPham> getDsSanPhamMoi() {
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT maSP, tenSP, tenLoai = (SELECT LoaiSP.tenLoai FROM LoaiSP where LoaiSP.maLoai = SanPham.maLoai), tenNCC =(SELECT NhaCungCap.tenNCC FROM NhaCungCap where NhaCungCap.maNCC = SanPham.maNCC), giaNhap, soLuongTon, donGia, giamGia, moTa, HinhAnh, khoiLuong, mau, chatLieu, thuongHieu, soTrang, tacGia, nhaXuatBan, namXuatBan,phanLoai FROM SanPham where YEAR(ngayNhap) = year(getdate()) and MONTH(ngayNhap) = MONTH(GETDATE())");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP").trim(), rs.getString("tenSP"), rs.getString("tenLoai"),
							rs.getString("tenNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(ds, (a,b)-> a.getMaSP().compareTo(b.getMaSP()));
		return ds;
	}
	
	/*
	 * điều kiện xóa nhà cung cấp xem còn sp nào chứa ncc không
	 * */
	public List<SanPham> dieuKienXoaNCC(String maNCC){

		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT  * FROM SanPham WHERE maNCC =?");
			stmt.setString(1, maNCC);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	// Xét điều kiện xóa loại sản phẩm khi không còn số lượng tồn
	public List<SanPham> dieuKienXoaLSP(String maLSP){
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT  * FROM SanPham WHERE maLoai =?");
			stmt.setString(1, maLSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("phanLoai").equals("Sách")) {
					SanPham sach = new Sach(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getInt("soTrang"),
							rs.getInt("namXuatBan"), rs.getString("nhaXuatBan"), rs.getString("tacGia"));
					ds.add(sach);
				} else if (rs.getString("phanLoai").equals("Văn Phòng Phẩm")) {
					SanPham vpp = new VanPhongPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("maLoai"),
							rs.getString("maNCC"), rs.getString("moTa"), rs.getString("HinhAnh"),
							rs.getInt("soLuongTon"), rs.getInt("giamGia"), rs.getFloat("giaNhap"),
							rs.getFloat("donGia"), rs.getFloat("khoiLuong"), rs.getString("mau"),
							rs.getString("chatLieu"), rs.getString("thuongHieu"));
					ds.add(vpp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<SanPham> getAllDSSanPham() {
		List<SanPham> ds = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select maSP,tenSP,maLoai,maNCC, HinhAnh from SanPhamNgungBan union select maSP,tenSP,maLoai,maNCC, HinhAnh from SanPham");
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ds.add(new SanPham(rs.getString("maSP"),rs.getString("tenSP"), rs.getString("maLoai"),rs.getString("maNCC"), rs.getString("HinhAnh")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	
	public SanPham getSanPhamMa(String maSP) {
		SanPham sp = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select maSP,tenSP,maLoai,maNCC, HinhAnh from SanPhamNgungBan where maSP = ? union select maSP,tenSP,maLoai,maNCC, HinhAnh from SanPham  where maSP = ?");
			preparedStatement.setString(1, maSP);
			preparedStatement.setString(2, maSP);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				sp=new SanPham(rs.getString("maSP"),rs.getString("tenSP"), rs.getString("maLoai"),rs.getString("maNCC"), rs.getString("HinhAnh"));
			}
		} catch (Exception e) {
			
		}
		return sp;
	}
	public static void main(String[] args) {
		System.out.println(new Dao_SanPham().getSanPhamMa("SP220003  "));
	}
}