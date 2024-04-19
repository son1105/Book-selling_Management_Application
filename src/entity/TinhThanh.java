package entity;

public class TinhThanh {
	private String idTinhThanh, tenTinhThanh;

	public TinhThanh() {
	}

	public TinhThanh(String idTinhThanh, String tenTinhThanh) {
		super();
		this.idTinhThanh = idTinhThanh;
		this.tenTinhThanh = tenTinhThanh;
	}
	
	public String getIdTinhThanh() {
		return idTinhThanh;
	}

	public void setIdTinhThanh(String idTinhThanh) {
		this.idTinhThanh = idTinhThanh;
	}

	public String getTenTinhThanh() {
		return tenTinhThanh;
	}

	public void setTenTinhThanh(String tenTinhThanh) {
		this.tenTinhThanh = tenTinhThanh;
	}

	@Override
	public String toString() {
		return "TinhThanh [idTinhThanh=" + idTinhThanh + ", tenTinhThanh=" + tenTinhThanh + "]";
	}
	
}
