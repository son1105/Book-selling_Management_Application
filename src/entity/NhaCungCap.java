package entity;

public class NhaCungCap {
	private String maNCC, tenNCC, sdt, diaChi;

	
	
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaCungCap(String maNCC, String tenNCC, String sdt, String diaChi) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", sdt=" + sdt + ", diaChi=" + diaChi + "]";
	}
	
	
}
