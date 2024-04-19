package entity;

import java.util.Date;

public class HoaDonDoiTra {
	private String maHD, maKh,maNV,diaChi;
	private Date ngayGiao;
	public HoaDonDoiTra(String maHD, String maKh, String maNV, String diaChi, Date ngayGiao) {
		super();
		this.maHD = maHD;
		this.maKh = maKh;
		this.maNV = maNV;
		this.diaChi = diaChi;
		this.ngayGiao = ngayGiao;
	}
	
	public HoaDonDoiTra() {	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaKh() {
		return maKh;
	}

	public void setMaKh(String maKh) {
		this.maKh = maKh;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	@Override
	public String toString() {
		return "HoaDonDoiTra [maHD=" + maHD + ", maKh=" + maKh + ", maNV=" + maNV + ", diaChi=" + diaChi + ", ngayGiao="
				+ ngayGiao + "]";
	}
	
}
