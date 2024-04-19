package entity;

public class DiaChi {
	private String maKH, diaChiCuThe, idXaPhuong;

	public DiaChi() {
	}

	public DiaChi(String maKH, String diaChiCuThe, String idXaPhuong) {
		super();
		this.maKH = maKH;
		this.diaChiCuThe = diaChiCuThe;
		this.idXaPhuong = idXaPhuong;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getDiaChiCuThe() {
		return diaChiCuThe;
	}

	public void setDiaChiCuThe(String diaChiCuThe) {
		this.diaChiCuThe = diaChiCuThe;
	}

	public String getIdXaPhuong() {
		return idXaPhuong;
	}

	public void setIdXaPhuong(String idXaPhuong) {
		this.idXaPhuong = idXaPhuong;
	}

	@Override
	public String toString() {
		return "DiaChi [maKH=" + maKH + ", diaChiCuThe=" + diaChiCuThe + ", idXaPhuong=" + idXaPhuong + "]";
	}
	
}
