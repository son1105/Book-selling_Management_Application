package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.HoaDon;
import entity.NhanVien;

public class Dao_HoaDon {
	private Connection con;

	public Dao_HoaDon() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * Lấy danh sách hóa đơn theo ngày truyền vào
	 * 
	 * @input ngày bắt đầu, ngày kết thúc
	 * @return danh sách hóa đơn theo ngày
	 */
	public List<HoaDon> getDsHoaDon(String ngayLap_BD, String ngayLap_KT, NhanVien nv) {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		PreparedStatement stmt;
		String sql;
		try {
			if (ngayLap_KT.isEmpty())
				ngayLap_KT = ngayLap_BD;
			if (nv.getChucVu().equals("Quản Lý"))
				sql = "select * from HoaDon where ngayLap between '" + ngayLap_BD + "' and '" + ngayLap_KT
						+ "' order by ngayLap";
			else
				sql = "select * from HoaDon where maNV = '" + nv.getMaNV() + "' and ngayLap between '" + ngayLap_BD
						+ "' and '" + ngayLap_KT + "' order by ngayLap";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * Tính tổng tiền của các hóa đơn theo ngày
	 * 
	 * @input ngày cần tính
	 * @return tổng tiền các hóa đơn theo ngày
	 */
	public double getTongTienHDTheoNgay(String ngayLap, String typeTime, NhanVien nv) {
		PreparedStatement stmt;
		double tongTien = 0;
		String sql = "select tongTien = SUM(soLuong*giaBan) from HoaDon hd join ChitietHoaDon cthd on hd.maHD=cthd.maHD where ";
		if (nv.getChucVu().equals("Nhân Viên"))
			sql += "maNV = '" + nv.getMaNV() + "' and ";
		try {
			if (typeTime.equals("Date"))
				sql += "ngayLap = '" + ngayLap + "' group by ngayLap";
			else if (typeTime.equals("Month"))
				sql += "month(ngayLap) = " + Integer.parseInt(ngayLap.split("-")[1]) + " and year(ngayLap) = "
						+ Integer.parseInt(ngayLap.split("-")[0]) + " group by month(ngayLap)";
			else if (typeTime.equals("Quarter"))
				sql += "datepart(q,ngayLap)=" + Integer.parseInt(ngayLap.split("-")[0].charAt(1) + "")
						+ " and year(ngayLap)=" + Integer.parseInt(ngayLap.split("-")[1])
						+ " group by DATEPART(q,ngayLap), year(ngayLap)";
			else
				sql += "year(ngayLap)=" + Integer.parseInt(ngayLap) + " group by year(ngayLap)";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble("tongTien");
			}
			sql = sql.replace("ChitietHoaDon", "ChiTietHoaDonCu");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tongTien += rs.getDouble("tongTien");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return tongTien;
	}

	public List<String> getListDate(String ngayLapBD, String ngayLapKT, String typeTime, NhanVien nv) {
		PreparedStatement stmt;
		List<String> list = new ArrayList<String>();
		String sql = "";
		try {
			if (ngayLapBD.isEmpty())
				if (nv.getChucVu().equals("Quản Lý"))
					sql = "select ngay = format(year(ngayLap),'D4')+'-'+format(month(ngayLap),'D2')+'-'+format(day(ngayLap),'D2') from HoaDon group by ngayLap order by ngayLap";
				else
					sql = "select ngay = format(year(ngayLap),'D4')+'-'+format(month(ngayLap),'D2')+'-'+format(day(ngayLap),'D2') from HoaDon where maNV = '"
							+ nv.getMaNV() + "' group by ngayLap order by ngayLap";
			else {
				if (typeTime.equals("Date"))
					sql = "select ngay = format(year(ngayLap),'D4')+'-'+format(month(ngayLap),'D2')+'-'+format(day(ngayLap),'D2') from HoaDon where ngayLap between '"
							+ ngayLapBD + "' and '" + ngayLapKT + "' group by ngayLap order by ngayLap";
				else if (typeTime.equals("Month"))
					sql = "select ngay = format(year(ngayLap),'D4')+'-'+format(month(ngayLap),'D2') from HoaDon where ngayLap between '"
							+ ngayLapBD + "' and '" + ngayLapKT
							+ "' group by month(ngayLap), year(ngayLap) order by year(ngayLap), month(ngayLap)";
				else if (typeTime.equals("Quarter"))
					sql = "select ngay = 'Q'+convert(varchar,DATEPART(q,ngayLap))+'-'+format(year(ngayLap),'D4') from HoaDon where ngayLap between '"
							+ ngayLapBD + "' and '" + ngayLapKT
							+ "' group by DATEPART(q,ngayLap), year(ngayLap) order by year(ngayLap), DATEPART(q,ngayLap)";
				else
					sql = "select ngay = format(year(ngayLap),'D4') from HoaDon where ngayLap between '" + ngayLapBD
							+ "' and '" + ngayLapKT + "' group by year(ngayLap) order by year(ngayLap)";
				if (nv.getChucVu().equals("Nhân Viên"))
					sql = sql.replace("where", "where maNV = '" + nv.getMaNV() + "' and ");
			}
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("ngay"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}

	// sơn phạm

	/**
	 * Thêm hoá đơn mới
	 * 
	 * @param hd
	 * @return boolean
	 */
	public boolean insertHoaDon(HoaDon hd) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"insert into HoaDon(maHD,maKh,maNV,ngayLap,ngayLayHang,ngayGiao,trangThai,diaChi) values(?,null,?,?,?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaNV());
			stmt.setDate(3, new java.sql.Date(hd.getNgayLap().getTime()));
			stmt.setDate(4, new java.sql.Date(hd.getNgayLayHang().getTime()));
			stmt.setDate(5, new java.sql.Date(hd.getNgayGiaoHang().getTime()));
			stmt.setString(6, hd.getTrangThai());
			stmt.setString(7, hd.getDiaChi());

			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();

		}
		return false;
	}

	/**
	 * thêm hoá đơn đặt hàng của khách hàng
	 * 
	 * @param hd
	 * @return
	 */
	public boolean insertHoaDonKhachHang(HoaDon hd) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into HoaDon(maHD,maKh,trangThai,diaChi) values(?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaKH());
			stmt.setString(3, hd.getTrangThai());
			stmt.setString(4, hd.getDiaChi());

			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();

		}
		return false;
	}

	/**
	 * thêm hoá đơn đặt hàng của khách hàng
	 * @param hd
	 * @return
	 */
	public boolean insertHoaDonKhachHangDoiTra(HoaDon hd) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into HoaDon(maHD,maKh,trangThai,diaChi,maNV,ngayLap) values(?,?,?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaKH());
			stmt.setString(3, hd.getTrangThai());
			stmt.setString(4, hd.getDiaChi());
			stmt.setString(5, hd.getMaNV());
			stmt.setDate(6,  (java.sql.Date)hd.getNgayLap());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();

		}
		return false;
	}
	
	
	/**
	 * lấy toàn bộ các hoá đơn
	 * 
	 * @return list <HoaDon>
	 */
	public List<HoaDon> getDsHoaDonFull() {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from HoaDon");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy toàn bộ các hoá đơn đã được xác nhận
	 * 
	 * @return list <HoaDon>
	 */
	public List<HoaDon> getDsHoaDon() {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from HoaDon where ngayLap is not null order by ngayLap");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách hoá đơn đặt hàng đang chờ xác nhận
	 * 
	 * @return list<HoaDon>
	 */
	public List<HoaDon> getDsHoaDonChoXacNhan() {
		List<HoaDon> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from HoaDon where ngayLap is null");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách hoá đơn đặt hàng chưa giao
	 * 
	 * @return list<HoaDon>
	 */
	public List<HoaDon> getDsHoaDonDatHangChuaGiao(String maNV) {
		List<HoaDon> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement(
					"select * from HoaDon where (maKh is NOT null and maNV is null ) or maHD in (select maHD from HoaDon where maNV = ? and trangThai not in (N'Đã Giao'))");
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách hoá đơn đặt hàng
	 * 
	 * @return list<HoaDon>
	 */
	public List<HoaDon> getDsHoaDonDatHang(String maNV) {
		List<HoaDon> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement(
					"select * from HoaDon where maKh is not null and trangThai in (N'Đã Giao') and maNV = ?");
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách hoá đơn đặt hàng theo trạng thái
	 * @return list<HoaDon>
	 */
	public  List<HoaDon> getDsHoaDonTheoTrangThai(String trangThai,String maNV) {
		List<HoaDon> ds = new ArrayList<>();
		try {
			PreparedStatement statement;
			if (trangThai.trim().equals("Chờ Xác Nhận")) {
				statement = con.prepareStatement("select * from HoaDon where  maKh is not null and trangThai = ?");
				statement.setString(1, trangThai);
			}else {
				statement = con.prepareStatement("select * from HoaDon where  maKh is not null and trangThai = ? and maNV = ?");
				statement.setString(1, trangThai);
			statement.setString(2, maNV);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"), rs.getDate("ngayLayHang"),rs.getDate("ngayGiao"), rs.getString("diaChi"),rs.getString("maNV"),rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * Tìm Kiếm Hoá đơn theo mã hoá đơn
	 * 
	 * @param hd
	 * @return HoaDon
	 */
	public HoaDon timKiemHoaDon(String maHD) {
		HoaDon hd = null;
		try {
			PreparedStatement statement = con.prepareStatement("select * from HoaDon where maHD = ?");
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return hd;
	}

	/**
	 * cập nhật ngày của hoá đơn
	 * 
	 * @param hd
	 * @return
	 */
	public boolean updateNgay(HoaDon hd) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"update HoaDon set ngayLap  = ? , ngayLayHang = ? ,ngayGiao = ?, trangThai = ? , maNV = ? where maHD = ? ");
			statement.setDate(1, (java.sql.Date) hd.getNgayLap());
			statement.setDate(2, (java.sql.Date) hd.getNgayLayHang());
			statement.setDate(3, (java.sql.Date) hd.getNgayGiaoHang());
			statement.setString(4, hd.getTrangThai());
			statement.setString(5, hd.getMaNV());
			statement.setString(6, hd.getMaHD());

			int n = statement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * lấy hoá đơn mới nhất
	 * 
	 * @return
	 */
	public HoaDon getHoaDonMoi() {
		HoaDon hd = null;
		try {
			PreparedStatement statement = con.prepareStatement("select top(1)* from HoaDon order by maHD desc");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return hd;
	}
	
	public List<HoaDon> getDsHoaDonCuaKhachHang(String maKH) {
		List<HoaDon> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement(
					"select * from HoaDon where maKh = ? order by ngayLap asc");
			statement.setString(1, maKH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"), rs.getString("trangThai"), rs.getDate("ngayLap"),
						rs.getDate("ngayLayHang"), rs.getDate("ngayGiao"), rs.getString("diaChi"), rs.getString("maNV"),
						rs.getString("maKh"));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}
}
