package entity;

public class NhanVien {
	private String maNV,tenNV, sdt, hinhAnh, email, chucVu;
	private boolean conLam;
	public NhanVien() {	}
	
	
	public NhanVien(String maNV, String tenNV, String sdt, String hinhAnh, String email, String chucVu,
			boolean conLam) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.hinhAnh = hinhAnh;
		this.email = email;
		this.chucVu = chucVu;
		this.conLam = conLam;
	}


	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}


	public NhanVien(String maNV, String tenNV, String sdt, String hinhAnh, String email, String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.hinhAnh = hinhAnh;
		this.email = email;
		this.chucVu = chucVu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	
	public boolean getConLam() {
		return conLam;
	}


	public void setConLam(boolean conLam) {
		this.conLam = conLam;
	}


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", sdt=" + sdt + ", hinhAnh=" + hinhAnh + ", email="
				+ email + ", chucVu=" + chucVu + ", conLam=" + conLam + "]";
	}
	
}
