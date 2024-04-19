package entity;

public class VanPhongPham extends SanPham {
	private String mau, chatLieu, thuongHieu;
	public VanPhongPham() {	}
	public VanPhongPham(String mau, String chatLieu, String thuongHieu) {
		super();
		this.mau = mau;
		this.chatLieu = chatLieu;
		this.thuongHieu = thuongHieu;
	}
	
	public VanPhongPham(String maSP, String tenSP, String MaLoai, String maNcc, String moTa, String hinhAnh,
			int soLuongTon, int giamGia, float giaNhap, float giaBan, float khoiLuong, String mau, String chatLieu,
			String thuongHieu) {
		super(maSP, tenSP, MaLoai, maNcc, moTa, hinhAnh, soLuongTon, giamGia, giaNhap, giaBan, khoiLuong);
		this.mau = mau;
		this.chatLieu = chatLieu;
		this.thuongHieu = thuongHieu;
	}
	public String getMau() {
		return mau;
	}
	public void setMau(String mau) {
		this.mau = mau;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	public String getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}
	@Override
	public String toString() {
		return "VanPhongPham [mau=" + mau + ", chatLieu=" + chatLieu + ", thuongHieu=" + thuongHieu + "]";
	}
	
	
}
