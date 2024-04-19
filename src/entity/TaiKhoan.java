package entity;

public class TaiKhoan {
	private String taiKhoan, matKhau, loaiTaiKhoan, maKH, maNV;
	public TaiKhoan() {	}
	public TaiKhoan(String taiKhoan, String matKhau, String loaiTaiKhoan, String maKH, String maNV) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.loaiTaiKhoan = loaiTaiKhoan;
		this.maKH = maKH;
		this.maNV = maNV;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}
	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "TaiKhoan [taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", loaiTaiKhoan=" + loaiTaiKhoan + ", maKH="
				+ maKH + ", maNV=" + maNV + "]";
	}
	
	
}
