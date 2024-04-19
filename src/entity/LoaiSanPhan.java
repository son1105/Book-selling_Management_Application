package entity;

public class LoaiSanPhan {
	private String maLoai, tenLoai;

	
	
	public LoaiSanPhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoaiSanPhan(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "LoaiSanPhan [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
	
}
