package entity;

import java.util.Date;
import java.util.List;

public class HoaDon {
	private String maHD,trangThai,diaChi,maNV,maKH;
	private Date ngayLap,ngayLayHang,ngayGiaoHang;
	
	public HoaDon() {
	}

	public HoaDon(String maHD, String trangThai, Date ngayLap, Date ngayLayHang, Date ngayGiaoHang,String DiaChi,String MaNV,String MaKh) {
		super();
		this.maHD = maHD;
		this.trangThai = trangThai;
		this.ngayLap = ngayLap;
		this.ngayLayHang = ngayLayHang;
		this.ngayGiaoHang = ngayGiaoHang;
		this.diaChi= DiaChi;
		this.maNV=MaNV;
		this.maKH = MaKh;
	}

	
	
	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}


	public Date getNgayLayHang() {
		return ngayLayHang;
	}

	public void setNgayLayHang(Date ngayLayHang) {
		this.ngayLayHang = ngayLayHang;
	}

	public Date getNgayGiaoHang() {
		return ngayGiaoHang;
	}

	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public double tinhTongTien(List<ChiTietHoaDon> ds) {
		double tongTien = 0;
		 for (ChiTietHoaDon i : ds) {
			 tongTien+=i.tinhThanhTien();
		}
		 return tongTien;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", trangThai=" + trangThai + ", diaChi=" + diaChi + ", maNV=" + maNV + ", maKH="
				+ maKH + ", ngayLap=" + ngayLap + ", ngayLayHang=" + ngayLayHang + ", ngayGiaoHang=" + ngayGiaoHang
				+ "]";
	}

	

	
	
	
}
