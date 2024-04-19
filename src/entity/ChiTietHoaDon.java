package entity;


public class ChiTietHoaDon {
	private String maSP,maDH;
	private int soLuong;
	private float giaBan;
	
	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(String maSP, String maDH, int soLuong, float giaBan) {
		this.maSP = maSP;
		this.maDH = maDH;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}
	
	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getMaDH() {
		return maDH;
	}

	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public double tinhThanhTien() {
		return soLuong*giaBan;
	}
	


	@Override
	public String toString() {
		return "ChiTietHoaDon [maSP=" + maSP + ", maDH=" + maDH + ", soLuong=" + soLuong
				+ ", giaBan=" + giaBan + "]";
	}


	
}
