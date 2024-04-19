package entity;

import java.util.Date;

public class ThongBao {
	private String maTB , maNV , chiTiet ;
	private Date ngayThongBao;
	private boolean trangThai;
	public ThongBao() {	}
	public ThongBao(String maTB, String maNV, String chiTiet, Date ngayThongBao, boolean trangThai) {
		super();
		this.maTB = maTB;
		this.maNV = maNV;
		this.chiTiet = chiTiet;
		this.ngayThongBao = ngayThongBao;
		this.trangThai = trangThai;
	}
	public String getMaTB() {
		return maTB;
	}
	public void setMaTB(String maTB) {
		this.maTB = maTB;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getChiTiet() {
		return chiTiet;
	}
	public void setChiTiet(String chiTiet) {
		this.chiTiet = chiTiet;
	}
	public Date getNgayThongBao() {
		return ngayThongBao;
	}
	public void setNgayThongBao(Date ngayThongBao) {
		this.ngayThongBao = ngayThongBao;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "ThongBao [maTB=" + maTB + ", maNV=" + maNV + ", chiTiet=" + chiTiet + ", ngayThongBao=" + ngayThongBao
				+ ", trangThai=" + trangThai + "]";
	}
	
	
	
	

}
