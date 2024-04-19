package entity;

public class XaPhuong {
	private String idXaPhuong,tenXaPhuong;

	public XaPhuong() {
	}

	public XaPhuong(String idXaPhuong, String tenXaPhuong) {
		super();
		this.idXaPhuong = idXaPhuong;
		this.tenXaPhuong = tenXaPhuong;
	}

	public String getIdXaPhuong() {
		return idXaPhuong;
	}

	public void setIdXaPhuong(String idXaPhuong) {
		this.idXaPhuong = idXaPhuong;
	}

	public String getTenXaPhuong() {
		return tenXaPhuong;
	}

	public void setTenXaPhuong(String tenXaPhuong) {
		this.tenXaPhuong = tenXaPhuong;
	}

	@Override
	public String toString() {
		return "XaPhuong [idXaPhuong=" + idXaPhuong + ", tenXaPhuong=" + tenXaPhuong + "]";
	}
	
}
