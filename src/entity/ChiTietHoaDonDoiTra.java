package entity;

public class ChiTietHoaDonDoiTra {
	private String maHD,maSP;
	private int soLuong,PhiDoiTra;
	private float giaBan;
	
	public ChiTietHoaDonDoiTra(String maHD, String maSP, int soLuong, int phiDoiTra, float giaBan) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.soLuong = soLuong;
		PhiDoiTra = phiDoiTra;
		this.giaBan = giaBan;
	}
	public ChiTietHoaDonDoiTra() {	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getPhiDoiTra() {
		return PhiDoiTra;
	}
	public void setPhiDoiTra(int phiDoiTra) {
		PhiDoiTra = phiDoiTra;
	}
	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDonDoiTra [maHD=" + maHD + ", maSP=" + maSP + ", soLuong=" + soLuong + ", PhiDoiTra="
				+ PhiDoiTra + ", giaBan=" + giaBan + "]";
	}
	
	
}
