package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.MyConnection;
import entity.DiaChi;
import entity.QuanHuyen;
import entity.TinhThanh;
import entity.XaPhuong;

public class Dao_DiaChi {
	private Connection con;

	public Dao_DiaChi() {
		con = MyConnection.getInstance().getConnection();
	}

	/**
	 * lấy danh sách tỉnh thành
	 * 
	 * @return
	 */
	public List<TinhThanh> getDsTinhThanh_1() {
		List<TinhThanh> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from TinhThanhPho");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new TinhThanh(rs.getString("id"), rs.getString("tenTinhThanhPho")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách quận huyện
	 * 
	 * @param id
	 * @return
	 */
	public List<QuanHuyen> getDsQuanHuyen_1(int id) {
		List<QuanHuyen> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from QuanHuyen where idTinhThanhPho = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new QuanHuyen(rs.getString("id"), rs.getString("tenQuanHuyen")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * lấy danh sách phường xã
	 * 
	 * @param id
	 * @return
	 */
	public List<XaPhuong> getDsXaPhuong_1(int id) {
		List<XaPhuong> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from XaPhuong where idQuanHuyen = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new XaPhuong(rs.getString("id"), rs.getString("tenXaPhuong")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	/**
	 * thêm địa chỉ
	 * @param diaChi
	 * @return
	 */
	public boolean insertDiaChi(DiaChi diaChi) {
		try {
			PreparedStatement statement = con
					.prepareStatement("insert into DiaChi (maKH ,diaChiCuThe,idXaPhuong) values(?,?,?)");
			statement.setString(1, diaChi.getMaKH());
			statement.setString(2, diaChi.getDiaChiCuThe());
			statement.setInt(3, Integer.parseInt(diaChi.getIdXaPhuong()));
			int n = statement.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	public List<TinhThanh> getDsTinhThanh() {
		List<TinhThanh> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from TinhThanhPho order by tenTinhThanhPho");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new TinhThanh(rs.getString("id"), rs.getString("tenTinhThanhPho")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	public List<QuanHuyen> getDsQuanHuyen(int id) {
		List<QuanHuyen> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from QuanHuyen where idTinhThanhPho = ? order by tenQuanHuyen");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new QuanHuyen(rs.getString("id"), rs.getString("tenQuanHuyen")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	public List<XaPhuong> getDsXaPhuong(int id) {
		List<XaPhuong> ds = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select * from XaPhuong where idQuanHuyen = ? order by tenXaPhuong");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ds.add(new XaPhuong(rs.getString("id"), rs.getString("tenXaPhuong")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ds;
	}

	public int getIDQuanHuyen(String tenQuanHyen) {
		try {
			PreparedStatement statement = con.prepareStatement("select id from QuanHuyen where tenQuanHuyen = N'"+tenQuanHyen+"'");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
	}
	
	public int getIDTinhThanhPho(String tenTinhThanhPho) {
		try {
			PreparedStatement statement = con.prepareStatement("select id from TinhThanhPho where tenTinhThanhPho = N'"+tenTinhThanhPho+"'");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
	}

}
