package entity;

public class Sach extends SanPham{
	private int soTrang,namXB;
	private String nhaXuatBan,tacGia;
	
	
	
	public Sach(String maSP, String tenSP, String MaLoai, String maNcc, String moTa, String hinhAnh,
			int soLuongTon, int giamGia, float giaNhap, float giaBan, float khoiLuong, int soTrang, int namXB,
			String nhaXuatBan, String tacGia) {
		super(maSP, tenSP, MaLoai, maNcc, moTa, hinhAnh, soLuongTon, giamGia, giaNhap, giaBan, khoiLuong);
		this.soTrang = soTrang;
		this.namXB = namXB;
		this.nhaXuatBan = nhaXuatBan;
		this.tacGia = tacGia;
	}

	public Sach() {	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public int getNamXB() {
		return namXB;
	}

	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	@Override
	public String toString() {
		return "Sach [soTrang=" + soTrang + ", namXB=" + namXB + ", nhaXuatBan=" + nhaXuatBan + ", tacGia=" + tacGia
				+ super.toString()+"]";
	}
	
	public static void main(String[] args) {
	}
}
