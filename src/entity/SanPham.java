package entity;

import java.util.Date;
import java.util.Objects;

public class SanPham {
	private String maSP, tenSP,maNcc,moTa,hinhAnh,maLoai;
	private int soLuongTon,giamGia;
	private float giaNhap, giaBan,khoiLuong;
	private Date ngayNhap;
	public SanPham() {
	}

	public SanPham(String maSP, String tenSP,String MaLoai, String maNcc, String moTa, String hinhAnh,
			int soLuongTon, int giamGia, float giaNhap, float giaBan, float khoiLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maNcc = maNcc;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.soLuongTon = soLuongTon;
		this.maLoai=MaLoai;
		this.giamGia = giamGia;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.khoiLuong = khoiLuong;
	}
	public SanPham(String maSP, String tenSP, String maLoai, String maNcc, Date ngayNhap , String moTa, String hinhAnh, int soLuongTon,
			int giamGia, float giaNhap, float giaBan, float khoiLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maNcc = maNcc;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.maLoai = maLoai;
		this.soLuongTon = soLuongTon;
		this.giamGia = giamGia;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.khoiLuong = khoiLuong;
		this.ngayNhap = ngayNhap;
	}
	
	
	public SanPham(String maSP, String tenSP, int soLuongTon, int giamGia, float giaBan) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuongTon = soLuongTon;
		this.giamGia = giamGia;
		this.giaBan = giaBan;
	}

	
	public SanPham(String maSP, String tenSP, String maLoai, String maNcc, String hinhAnh) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maNcc = maNcc;
		this.hinhAnh = hinhAnh;
		this.maLoai = maLoai;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaNcc() {
		return maNcc;
	}

	public void setMaNcc(String maNcc) {
		this.maNcc = maNcc;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public int getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(int giamGia) {
		this.giamGia = giamGia;
	}

	public float getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(float giaNhap) {
		this.giaNhap = giaNhap;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public float getKhoiLuong() {
		return khoiLuong;
	}

	public void setKhoiLuong(float khoiLuong) {
		this.khoiLuong = khoiLuong;
	}

	
	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	
	
	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", maNcc=" + maNcc + ", moTa=" + moTa + ", hinhAnh="
				+ hinhAnh + ", maLoai=" + maLoai + ", soLuongTon=" + soLuongTon + ", giamGia=" + giamGia + ", giaNhap="
				+ giaNhap + ", giaBan=" + giaBan + ", khoiLuong=" + khoiLuong + ", ngayNhap=" + ngayNhap + "]";
	}

	

	
	
}
