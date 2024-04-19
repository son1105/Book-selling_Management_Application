package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import dao.Dao_NhanVien;
import dao.Dao_TaiKhoan;
import entity.NhanVien;
import jnafilechooser.api.JnaFileChooser;

public class FormThongTinNhanVien implements ActionListener {
	private JButton btnChonAnh;
	private JButton btnLuu;
	private JButton btnDoiMatKhau;
	private String newPath;
	private File a;
	private JLabel lblImg;
	private NhanVien nhanVien;
	private File sr;

	public Component conTrolThongTinNv(NhanVien nv) {
		nhanVien = nv;
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(484, 561));
		panel.setLayout(null);

		lblImg = new JLabel("");
		lblImg.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImg.setIcon(new ImageIcon(
				new ImageIcon(nv.getHinhAnh()).getImage().getScaledInstance(245, 250, java.awt.Image.SCALE_SMOOTH)));
		lblImg.setBounds(117, 11, 250, 250);
		panel.add(lblImg);

		btnChonAnh = new JButton("Chọn Ảnh");
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChonAnh.setBackground(new Color(255, 248, 220));
		btnChonAnh.setFocusable(false);
		btnChonAnh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChonAnh.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\img.png"));
		btnChonAnh.setBounds(192, 272, 101, 30);
		panel.add(btnChonAnh);

		JPanel pnThongTinChiTiet = new JPanel();
		pnThongTinChiTiet.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnThongTinChiTiet.setBounds(10, 313, 464, 193);
		panel.add(pnThongTinChiTiet);
		pnThongTinChiTiet.setLayout(null);

		JLabel lblMa = new JLabel("Mã Nhân Viên: ");
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMa.setBounds(32, 10, 150, 25);
		pnThongTinChiTiet.add(lblMa);

		JLabel lblMaNV = new JLabel(nv.getMaNV());
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaNV.setBounds(192, 10, 150, 25);
		pnThongTinChiTiet.add(lblMaNV);

		JLabel lblChucVuNV = new JLabel(nv.getChucVu());
		lblChucVuNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChucVuNV.setBounds(192, 46, 150, 25);
		pnThongTinChiTiet.add(lblChucVuNV);

		JLabel lblChucVu = new JLabel("Chức Vụ: ");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChucVu.setBounds(32, 46, 150, 25);
		pnThongTinChiTiet.add(lblChucVu);

		JLabel lblTenNV = new JLabel(nv.getTenNV());
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenNV.setBounds(192, 82, 150, 25);
		pnThongTinChiTiet.add(lblTenNV);

		JLabel lblTen = new JLabel("Họ Và Tên: ");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTen.setBounds(32, 82, 150, 25);
		pnThongTinChiTiet.add(lblTen);

		JLabel lblSdtNV = new JLabel(nv.getSdt());
		lblSdtNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdtNV.setBounds(192, 118, 150, 25);
		pnThongTinChiTiet.add(lblSdtNV);

		JLabel lblSdt = new JLabel("Số Điện Thoại");
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdt.setBounds(32, 118, 150, 25);
		pnThongTinChiTiet.add(lblSdt);

		JLabel lblEmailNV = new JLabel(nv.getEmail());
		lblEmailNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmailNV.setBounds(192, 154, 262, 25);
		pnThongTinChiTiet.add(lblEmailNV);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(32, 154, 150, 25);
		pnThongTinChiTiet.add(lblEmail);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLuu.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\save.png"));
		btnLuu.setFocusable(false);
		btnLuu.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLuu.setBackground(new Color(255, 248, 220));
		btnLuu.setBounds(117, 517, 101, 30);
		panel.add(btnLuu);

		btnDoiMatKhau = new JButton("Đổi Mật Khẩu");
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDoiMatKhau.setFocusable(false);
		btnDoiMatKhau.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDoiMatKhau.setBackground(new Color(255, 248, 220));
		btnDoiMatKhau.setBounds(266, 517, 101, 30);
		panel.add(btnDoiMatKhau);

		btnChonAnh.addActionListener(this);
		btnLuu.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnChonAnh)) {
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("jpg", "jpg");
			f.addFilter("png", "png");
			f.showOpenDialog(null);
			File fl = f.getSelectedFile();
			newPath = "img/AvtUser/NhanVien";
			if (f.getSelectedFile() != null) {
				String fileName = fl.getAbsolutePath();
				File directory = new File(newPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				String exc = fileName.substring(fileName.lastIndexOf("."));
				sr = new File(fileName);
				a = new File(newPath + "/" + nhanVien.getMaNV().trim() + exc);

				System.out.println(a.getPath());
				System.out.println(nhanVien.getHinhAnh());
				System.out.println(sr.getPath());
				lblImg.setIcon(new ImageIcon(new ImageIcon(sr.getPath().toString()).getImage().getScaledInstance(245,
						250, java.awt.Image.SCALE_SMOOTH)));
			}
		}
		else if (e.getSource().equals(btnLuu)) {
			if (a!=null) {
				if (!a.getPath().equals(nhanVien.getHinhAnh())) {
					
					File x = new File(nhanVien.getHinhAnh()+"");
					x.delete();
					try {
						Files.copy(sr.toPath(), a.toPath());
					} catch (IOException e1) {
					}
					nhanVien.setHinhAnh(a.getPath());
					System.out.println(nhanVien);
					
					if (new Dao_NhanVien().updateNhanVien(nhanVien)) {
						JOptionPane.showMessageDialog(btnChonAnh, e);
					}
				}
			}
		}else if (e.getSource().equals(btnDoiMatKhau)) {
			JPanel pnDMK = new JPanel();
			pnDMK.setPreferredSize(new Dimension(355, 120));
			pnDMK.setBounds(141, 260, 355, 120);
			pnDMK.setLayout(null);
			
			JLabel lblMkCu = new JLabel("Mật Khẩu Củ:");
			lblMkCu.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMkCu.setBounds(10, 10, 135, 25);
			pnDMK.add(lblMkCu);
			
			JTextField txtMatKhauCu = new JTextField();
			txtMatKhauCu.setFont(new Font("Tahoma", Font.BOLD, 11));
			txtMatKhauCu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtMatKhauCu.setBounds(148, 10, 200, 25);
			pnDMK.add(txtMatKhauCu);
			txtMatKhauCu.setColumns(10);
			
			JLabel lblMatKhauMoi = new JLabel("Mật Khẩu Mới");
			lblMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMatKhauMoi.setBounds(10, 46, 135, 25);
			pnDMK.add(lblMatKhauMoi);
			
			JTextField txtMkMoi = new JTextField();
			txtMkMoi.setColumns(10);
			txtMkMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
			txtMkMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtMkMoi.setBounds(148, 46, 200, 25);
			pnDMK.add(txtMkMoi);
			
			JLabel lblRMKMoi = new JLabel("Nhập Lại Mật Khẩu:");
			lblRMKMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblRMKMoi.setBounds(10, 82, 135, 25);
			pnDMK.add(lblRMKMoi);
			
			JTextField txtRMKMoi = new JTextField();
			txtRMKMoi.setColumns(10);
			txtRMKMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
			txtRMKMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtRMKMoi.setBounds(148, 82, 200, 25);
			pnDMK.add(txtRMKMoi);
			boolean kt = true;
			while (kt) {
				if (JOptionPane.showConfirmDialog(null, pnDMK, "Đổi Mật Khẩu", JOptionPane.OK_CANCEL_OPTION,JOptionPane.DEFAULT_OPTION)==JOptionPane.OK_OPTION) {
					if (txtMatKhauCu.getText().trim().equals(new Dao_TaiKhoan().timKiemTaiKhoan(nhanVien.getMaNV().trim(), null).getMatKhau().trim())) {
						if (txtMkMoi.getText().trim().equals(txtRMKMoi.getText().trim())) {
							kt=false;
							new Dao_TaiKhoan().updateMatKhau(new Dao_TaiKhoan().timKiemTaiKhoan(nhanVien.getMaNV().trim(), null).getTaiKhoan().trim(), txtMkMoi.getText());
							JOptionPane.showMessageDialog(null, "Thay Đổi Mật Khẩu Thành Công");
						}else {
							JOptionPane.showMessageDialog(null, "Mật Khẩu mới với nhập lại mật khẩu không giống.");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Mật Khẩu củ không chính xác.");
					}
				}else {
					kt=false;
				}
			}
		}
	}

}
