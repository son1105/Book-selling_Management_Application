package entity;

public class QuanHuyen {
	private String idQuanHuyen, tenQuanHuyen;

	public QuanHuyen() {
	}

	public QuanHuyen(String idQuanHuyen, String tenQuanHuyen) {
		super();
		this.idQuanHuyen = idQuanHuyen;
		this.tenQuanHuyen = tenQuanHuyen;
	}

	public String getIdQuanHuyen() {
		return idQuanHuyen;
	}

	public void setIdQuanHuyen(String idQuanHuyen) {
		this.idQuanHuyen = idQuanHuyen;
	}

	public String getTenQuanHuyen() {
		return tenQuanHuyen;
	}

	public void setTenQuanHuyen(String tenQuanHuyen) {
		this.tenQuanHuyen = tenQuanHuyen;
	}

	@Override
	public String toString() {
		return "QuanHuyen [idQuanHuyen=" + idQuanHuyen + ", tenQuanHuyen=" + tenQuanHuyen + "]";
	}
	
	
}
