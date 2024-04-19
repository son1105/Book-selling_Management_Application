package entity;

public class KhachHang {
	private String maKH, sdt, hoTen,diaChi,gioHang,hinhAnh;

	public KhachHang() {}

	public KhachHang(String maKH, String sdt, String hoTen, String diaChi, String gioHang, String hinhAnh) {
		super();
		this.maKH = maKH;
		this.sdt = sdt;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.gioHang = gioHang;
		this.hinhAnh = hinhAnh;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioHang() {
		return gioHang;
	}

	public void setGioHang(String gioHang) {
		this.gioHang = gioHang;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", sdt=" + sdt + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", gioHang="
				+ gioHang + ", hinhAnh=" + hinhAnh + "]";
	}
	
	
	
}
