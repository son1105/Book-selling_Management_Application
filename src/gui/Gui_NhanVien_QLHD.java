package gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.NhanVien;

public class Gui_NhanVien_QLHD extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVien nhanVien;
	private static JPanel pnXemHD;
	private static JPanel pnLapHoaDon;
	private static JPanel pnXacNhanDonHang;
	public Gui_NhanVien_QLHD(NhanVien nv) {
		nhanVien = nv;
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(QuanLySanPham());
	}

	public Component QuanLySanPham() {

		JPanel pnQLHD = new JPanel();
		pnQLHD.setBounds(0, 0, 1237, 664);
		pnQLHD.setLayout(null);
		
		 pnXemHD = new JPanel();
		pnXemHD.setBounds(0, 0, 1237, 664);
		pnQLHD.add(pnXemHD);
		pnXemHD.add(new Gui_XemHoaDon(nhanVien).controll_XemHoaDon());
		pnXemHD.setLayout(null);
		
		 pnLapHoaDon = new JPanel();
		pnLapHoaDon.setBounds(0, 0, 1237, 664);
		pnQLHD.add(pnLapHoaDon);
		pnLapHoaDon.setVisible(false);
		pnLapHoaDon.add(new Gui_NhanVien_LapHD(nhanVien).conTrolNhanVien_QLLAPHD());
		pnLapHoaDon.setLayout(null);
		
		 pnXacNhanDonHang = new JPanel();
		pnXacNhanDonHang.setBounds(0, 0, 1237, 664);
		pnXacNhanDonHang.setVisible(false);
		pnXacNhanDonHang.add(new Gui_NhanVien_XacNhanHD(nhanVien).conTrolXacNhanDH());
		pnQLHD.add(pnXacNhanDonHang);
		pnXacNhanDonHang.setLayout(null);
		
		return pnQLHD;
	}

	public static void chuyenTrang(int index) {
		if (index==1) {
			pnXemHD.setVisible(true);
			pnLapHoaDon.setVisible(false);
			pnXacNhanDonHang.setVisible(false);
		}else if (index==2) {
			pnXemHD.setVisible(false);
			pnLapHoaDon.setVisible(true);
			pnXacNhanDonHang.setVisible(false);
		}else if (index==3) {
			pnXemHD.setVisible(false);
			pnLapHoaDon.setVisible(false);
			pnXacNhanDonHang.setVisible(true);
		}
	}
	public static void main(String[] args) {
		
	}
}
