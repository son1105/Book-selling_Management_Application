package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dao.Dao_DiaChi;
import dao.Dao_KhachHang;
import dao.Dao_TaiKhoan;
import entity.KhachHang;
import entity.QuanHuyen;
import entity.TinhThanh;
import entity.XaPhuong;
import jnafilechooser.api.JnaFileChooser;

public class GUI_KhachHang_QuanLyThongTin extends JFrame implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtSDT;
	private JTextField txtTen;
	private KhachHang kh;
	private JTextArea txtDiaChi;
	private JLabel lbl_EditTen;
	private JLabel lbl_EditSdt;
	private JLabel lbl_EditDiaChi;
	private JComboBox<String> cbo_Xa;
	private JComboBox<String> cbo_Huyen;
	private JComboBox<String> cbo_Tinh;
	private JPanel pnDiaChi;
	private JTextArea txt_DiaChiCuThe;
	private Dao_DiaChi dao_DiaChi;
	private Dao_KhachHang dao_KhachHang;
	private JButton btn_ChonAnh;
	private File file_Avt = null;
	private JLabel lbl_Avt;
	private JButton btn_Luu;
	private JButton btn_DoiMatKhau;
	private JButton btn_XemLichMuaHang;

	public GUI_KhachHang_QuanLyThongTin(KhachHang khachHang) {
		setSize(685, 410);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		kh = khachHang;
		dao_DiaChi = new Dao_DiaChi();
		dao_KhachHang = new Dao_KhachHang();
		getContentPane().add(control_KhachHang_QuanLyThongTin());
		
	}
	
	/**
	 * Tạo pn gui quản lý thông tin khách hàng
	 * @return pn gui quản lý thông tin khách hàng
	 */
	public Component control_KhachHang_QuanLyThongTin() {
		JPanel pn_KH_QLTT = new JPanel();
		pn_KH_QLTT.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pn_KH_QLTT.setBounds(0, 0, 685, 410);
		pn_KH_QLTT.setPreferredSize(new Dimension(699,375));
		pn_KH_QLTT.setLayout(null);
		
		JPanel pn_Menu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth(), h = getHeight();
				Color color1 = new Color(100, 130, 140);
				Color color2 = new Color(255, 190, 190);
				GradientPaint gp = new GradientPaint(0, 0, color1, 200, 300, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		pn_Menu.setBorder(null);
		pn_Menu.setBounds(0, 0, 220, 410);
		pn_KH_QLTT.add(pn_Menu);
		pn_Menu.setLayout(null);
		
		JButton btn_HoSo = new JButton("Hồ Sơ");
		formatButton(btn_HoSo);
		btn_HoSo.setBackground(new Color(255,100,100));
		btn_HoSo.setBounds(50, 230, 120, 30);
		pn_Menu.add(btn_HoSo);
		
		btn_DoiMatKhau = new JButton("Đổi Mật Khẩu");
		formatButton(btn_DoiMatKhau);
		btn_DoiMatKhau.setBounds(50, 265, 120, 30);
		pn_Menu.add(btn_DoiMatKhau);
		
		btn_XemLichMuaHang = new JButton("Lịch Sử Mua Hàng");
		formatButton(btn_XemLichMuaHang);
		btn_XemLichMuaHang.setBounds(50, 300, 120, 30);
		pn_Menu.add(btn_XemLichMuaHang);
		
		btn_ChonAnh = new JButton("Chọn Ảnh");
		btn_ChonAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ChonAnh.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		btn_ChonAnh.setBackground(new Color(250, 112, 112));
		btn_ChonAnh.setMargin(new Insets(2, 0, 2, 14));
		btn_ChonAnh.setFocusable(false);
		btn_ChonAnh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_ChonAnh.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\img.png"));
		btn_ChonAnh.setBounds(50, 160, 120, 30);
		btn_ChonAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btn_ChonAnh.setBackground(new Color(250, 112, 112));
				btn_ChonAnh.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_ChonAnh.setBackground(new Color(255, 209, 209));
				btn_ChonAnh.setBorder(new LineBorder(Color.YELLOW, 2, true));
			}
		});
		pn_Menu.add(btn_ChonAnh);
		
		lbl_Avt = new JLabel("");
		lbl_Avt.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_Avt.setBounds(40, 10, 140, 140);
		pn_Menu.add(lbl_Avt);
		
		JPanel pnTT = new JPanel();
		pnTT.setBorder(null);
		pnTT.setBackground(new Color(255, 210, 190));
		pnTT.setBounds(220, 60, 465, 350);
		pn_KH_QLTT.add(pnTT);
		pnTT.setLayout(null);
		
		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHoTen.setBounds(30, 74, 67, 30);
		pnTT.add(lblHoTen);
		
		JLabel lblMaKH = new JLabel("Mã KH:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaKH.setBounds(30, 20, 67, 30);
		pnTT.add(lblMaKH);
		
		JLabel lbl_Sdt = new JLabel("Số điện thoại:");
		lbl_Sdt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_Sdt.setBounds(30, 128, 115, 30);
		pnTT.add(lbl_Sdt);
		
		JLabel lbl_DiaChi = new JLabel("Địa chỉ:");
		lbl_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_DiaChi.setBounds(30, 182, 106, 30);
		pnTT.add(lbl_DiaChi);
		
		txtMa = new JTextField();
		formatText(txtMa);
		txtMa.setBounds(150, 20, 130, 30);
		pnTT.add(txtMa);
		
		txtSDT = new JTextField();
		formatText(txtSDT);
		txtSDT.setBounds(150, 128, 235, 30);
		pnTT.add(txtSDT);
		txtSDT.setColumns(10);
		
		txtTen = new JTextField();
		formatText(txtTen);
		txtTen.setBounds(150, 74, 235, 30);
		pnTT.add(txtTen);
		
		lbl_EditTen = new JLabel("");
		lbl_EditTen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_EditTen.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\edit.png"));
		lbl_EditTen.setBounds(395, 77, 24, 24);
		pnTT.add(lbl_EditTen);
		
		lbl_EditSdt = new JLabel("");
		lbl_EditSdt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_EditSdt.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\edit.png"));
		lbl_EditSdt.setBounds(395, 131, 24, 24);
		pnTT.add(lbl_EditSdt);
		
		lbl_EditDiaChi = new JLabel("");
		lbl_EditDiaChi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_EditDiaChi.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\edit.png"));
		lbl_EditDiaChi.setBounds(395, 188, 24, 24);
		pnTT.add(lbl_EditDiaChi);
		
		JScrollPane scr_DiaChi = new JScrollPane();
		scr_DiaChi.setBorder(null);
		scr_DiaChi.setOpaque(false);
		scr_DiaChi.setBounds(150, 185, 235, 80);
		pnTT.add(scr_DiaChi);
		
		txtDiaChi = new JTextArea();
		txtDiaChi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDiaChi.setEditable(false);
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);
		scr_DiaChi.setViewportView(txtDiaChi);
		txtDiaChi.setBackground(new Color(240,240,240));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiaChi.setBackground(new Color(230, 230, 230));
		
		btn_Luu = new JButton("Lưu");
		btn_Luu.setBounds(150, 275, 150, 30);
		btn_Luu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Luu.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		btn_Luu.setBackground(new Color(250, 112, 112));
		btn_Luu.setMargin(new Insets(2, 0, 2, 14));
		btn_Luu.setFocusable(false);
		btn_Luu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_Luu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Luu.setBackground(new Color(250, 112, 112));
				btn_Luu.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Luu.setBackground(new Color(255, 209, 209));
				btn_Luu.setBorder(new LineBorder(Color.YELLOW, 2, true));
			}
		});
		pnTT.add(btn_Luu);
		
		JPanel pn_HoSo = new JPanel();
		pn_HoSo.setBackground(new Color(150, 150, 150));
		pn_HoSo.setBounds(220, 0, 465, 60);
		pn_KH_QLTT.add(pn_HoSo);
		pn_HoSo.setLayout(null);
		
		JLabel lbl_HoSo = new JLabel("Thông Tin Khách Hàng");
		lbl_HoSo.setForeground(Color.WHITE);
		lbl_HoSo.setBounds(55, 10, 340, 40);
		lbl_HoSo.setFont(new Font("Tahoma", Font.BOLD, 30));
		pn_HoSo.add(lbl_HoSo);
		
		JLabel lblExit = new JLabel("New label");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_red.png"));
		lblExit.setBounds(425, 2, 32, 30);
		pn_HoSo.add(lblExit);
		
		pnDiaChi = new JPanel();
		pnDiaChi.setPreferredSize(new Dimension(385,200));
		pnDiaChi.setBackground(new Color(255, 210, 190));
		pnDiaChi.setLayout(null);
		
		JLabel lblTinh = new JLabel("Tỉnh/Thành phố:");
		lblTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTinh.setBounds(10, 12, 140, 25);
		pnDiaChi.add(lblTinh);
		
		JLabel lblHuyen = new JLabel("Quận/Huyện:");
		lblHuyen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHuyen.setBounds(10, 52, 140, 25);
		pnDiaChi.add(lblHuyen);
		
		JLabel lblXa = new JLabel("Xã/Phường:");
		lblXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXa.setBounds(10, 92, 150, 25);
		pnDiaChi.add(lblXa);
		
		JLabel lblDiaChiCuThe = new JLabel("Địa chỉ cụ thể:");
		lblDiaChiCuThe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChiCuThe.setBounds(10, 128, 127, 25);
		pnDiaChi.add(lblDiaChiCuThe);
		
		cbo_Tinh = new JComboBox<String>();
		cbo_Tinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbo_Tinh.setBounds(155, 10, 220, 30);
		pnDiaChi.add(cbo_Tinh);
		
		cbo_Huyen = new JComboBox<String>();
		cbo_Huyen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbo_Huyen.setBounds(155, 50, 220, 30);
		pnDiaChi.add(cbo_Huyen);
		
		cbo_Xa = new JComboBox<String>();
		cbo_Xa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbo_Xa.setBounds(155, 90, 220, 30);
		pnDiaChi.add(cbo_Xa);
		
		txt_DiaChiCuThe = new JTextArea();
		txt_DiaChiCuThe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_DiaChiCuThe.setBounds(155, 130, 220, 60);
		pnDiaChi.add(txt_DiaChiCuThe);
		
		changeColorButtonWhenEntered(btn_HoSo);
		changeColorButtonWhenExited(btn_HoSo);
		changeColorButtonWhenEntered(btn_DoiMatKhau);
		changeColorButtonWhenExited(btn_DoiMatKhau);
		changeColorButtonWhenEntered(btn_XemLichMuaHang);
		changeColorButtonWhenExited(btn_XemLichMuaHang);
		
		JButton btn_DangXuat = new JButton("Đăng Xuất");
		btn_DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GUI_DangNhap.dangXuat();
				new GUI_DangNhap().setVisible(true);
				
			}
		});
		btn_DangXuat.setFocusable(false);
		btn_DangXuat.setBounds(50, 334, 120, 30);
		formatButton(btn_DangXuat);
		changeColorButtonWhenEntered(btn_DangXuat);
		changeColorButtonWhenExited(btn_DangXuat);
		pn_Menu.add(btn_DangXuat);
		
	
		loadData();
		
		btn_ChonAnh.addActionListener(this);
		lbl_EditTen.addMouseListener(this);
		lbl_EditSdt.addMouseListener(this);
		lbl_EditDiaChi.addMouseListener(this);
		cbo_Tinh.addActionListener(this);
		cbo_Huyen.addActionListener(this);
		cbo_Xa.addActionListener(this);
		btn_Luu.addActionListener(this);
		btn_DoiMatKhau.addActionListener(this);
		btn_XemLichMuaHang.addActionListener(this);
		return pn_KH_QLTT;
	}

	/**
	 * load dữ liệu vào text
	 */
	public void loadData() {
		txtMa.setText(kh.getMaKH());
		txtTen.setText(kh.getHoTen());
		txtSDT.setText(kh.getSdt());
		txtDiaChi.setText(kh.getDiaChi());
		lbl_Avt.setIcon(new ImageIcon(new ImageIcon(kh.getHinhAnh()).getImage()
				.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH)));
		for (TinhThanh tinhThanh : dao_DiaChi.getDsTinhThanh()) {
			cbo_Tinh.addItem(tinhThanh.getTenTinhThanh());
		}
		String diaChi[] = kh.getDiaChi().split(",");
		cbo_Tinh.setSelectedItem(diaChi[3].trim());
		for (QuanHuyen quanHuyen : dao_DiaChi.getDsQuanHuyen(dao_DiaChi.getIDTinhThanhPho(cbo_Tinh.getSelectedItem()+""))) {
			cbo_Huyen.addItem(quanHuyen.getTenQuanHuyen());
		}
		cbo_Huyen.setSelectedItem(diaChi[2].trim());
		for (XaPhuong xaPhuong : dao_DiaChi.getDsXaPhuong(dao_DiaChi.getIDQuanHuyen(cbo_Huyen.getSelectedItem()+""))) {
			cbo_Xa.addItem(xaPhuong.getTenXaPhuong());
		}
		cbo_Xa.setSelectedItem(diaChi[1].trim());
		txt_DiaChiCuThe.setText(diaChi[0]);
	}
	
	/**
	 * Định dạng button
	 * @param btn button cần định dạng
	 */
	public void formatButton(JButton btn) {
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		btn.setBackground(new Color(250, 200, 200));
		btn.setMargin(new Insets(2, 0, 2, 14));
		btn.setFocusable(false);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
	
	/**
	 * Định dạng text
	 * @param txt : text cần định dạng
	 */
	public void formatText(JTextField txt) {
		txt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt.setEditable(false);
		txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt.setColumns(10);
		txt.setBackground(new Color(230, 230, 230));
	}
	
	/**
	 * regex thông tin khách hàng
	 * @return kết quả thông báo lỗi
	 */
	public String regexKhachHang() {
		if(txtTen.getText().isBlank())
			return "Tên khách hàng không được rỗng!";
		if(txtSDT.getText().isBlank())
			return "Số điện thoại không được rỗng!";
		if(!txtTen.getText().matches("^([A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]*(\\s){0,1}){2,}$"))
			return "Tên khách hàng phải tối thiểu gồm 2 từ và bắt đầu bằng chữ in hoa";
		if(!txtSDT.getText().matches("[0-9]{9,10}"))
			return "Số điện thoại chỉ gồm 9 hoặc 10 số";
		return "";
	}
	
	/**
	 * Thay đổi màu cho button khi trỏ chuột vào
	 * @param btn : button cần đổi màu
	 */
	public void changeColorButtonWhenEntered(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!btn.getBackground().equals(new Color(255, 100, 100))) {
					btn.setBackground(new Color(255, 240, 240));
				}
				btn.setBorder(new LineBorder(Color.YELLOW, 2, true));
			}
		});
		
	}

	/**
	 * Đổi màu cho button khi trỏ chuột ra
	 * @param btn : button cần đổi màu
	 */
	public void changeColorButtonWhenExited(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btn.getBackground().equals(new Color(255, 100, 100))) {
					btn.setBackground(new Color(250, 210, 210));
				}
				btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
			}
		});
	}
	
	/**
	 * Hiển thị thông báo
	 * @param mess : thông báo cần hiển thị
	 */
	public void showMess(String mess) {
		JOptionPane.showMessageDialog(null, mess);
	}
	
	public static void main(String[] args) {
		new GUI_KhachHang_QuanLyThongTin(new KhachHang("KH0002", "0387866829", "Nguyễn Thanh Sơn", "345 Phạm Văn Đông, Phường Xuân Thành, Thị xã Sông Cầu, Tỉnh Phú Yên", "", "img/AvtUser/KhachHang/KH0002.jpg")).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(lbl_EditTen)) {
			txtTen.setBackground(Color.WHITE);
			txtTen.setEditable(true);
			txtTen.requestFocus();
			txtTen.selectAll();
		} else if(e.getSource().equals(lbl_EditSdt)) {
			txtSDT.setBackground(Color.WHITE);
			txtSDT.setEditable(true);
			txtSDT.requestFocus();
			txtSDT.selectAll();
		} else if(e.getSource().equals(lbl_EditDiaChi)) {
			boolean kt = false;
			while(!kt)
			{
				if(JOptionPane.showConfirmDialog(null, pnDiaChi, "Chọn địa chỉ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION)==JOptionPane.OK_OPTION)
					if(!txt_DiaChiCuThe.getText().isBlank() && !txt_DiaChiCuThe.getText().contains(","))
					{
						txtDiaChi.setText(txt_DiaChiCuThe.getText()+", "+cbo_Xa.getSelectedItem()+", "+cbo_Huyen.getSelectedItem()+", "+cbo_Tinh.getSelectedItem());
						kt = true;
					}
					else
						showMess("Địa chỉ cụ thể không được để trống và không chứa ký tự ','");
				else
					kt = true;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cbo_Tinh)) {
			cbo_Huyen.removeAllItems();
			for (QuanHuyen quanHuyen : dao_DiaChi.getDsQuanHuyen(dao_DiaChi.getIDTinhThanhPho(cbo_Tinh.getSelectedItem()+""))) {
				cbo_Huyen.addItem(quanHuyen.getTenQuanHuyen());
			}
		} else if(e.getSource().equals(cbo_Huyen)) {
			cbo_Xa.removeAllItems();
			for (XaPhuong xaPhuong : dao_DiaChi.getDsXaPhuong(dao_DiaChi.getIDQuanHuyen(cbo_Huyen.getSelectedItem()+""))) {
				cbo_Xa.addItem(xaPhuong.getTenXaPhuong());
			}
		} else if(e.getSource().equals(btn_ChonAnh)) {
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("jpg", "jpg");
			f.addFilter("png", "png");
			f.showOpenDialog(null);
			file_Avt = f.getSelectedFile();
			if (f.getSelectedFile() != null) {
				lbl_Avt.setIcon(new ImageIcon(new ImageIcon(file_Avt.getPath().toString()).getImage()
					.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
			}
		} else if(e.getSource().equals(btn_Luu)) {
			String regex = regexKhachHang();
			if(!regex.equals("")) {
				showMess(regex);
				if(regex.startsWith("Tên khách hàng"))
				{
					txtTen.requestFocus();
					txtTen.selectAll();
				}
				else if(regex.startsWith("Số điện thoại")) {
					txtSDT.requestFocus();
					txtSDT.selectAll();
				}
			} else {
			String fileName;
			String path="";
			if(file_Avt!=null) {
				fileName = file_Avt.getName();
				String newName = txtMa.getText().trim() + fileName.substring(fileName.lastIndexOf("."));
				File f1 = new File("img\\AvtUser\\KhachHang\\"+newName);
				try {
					Files.copy(file_Avt.toPath(), f1.toPath(), StandardCopyOption.REPLACE_EXISTING);
					path = f1.getPath();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else
				path = kh.getHinhAnh();
			if(dao_KhachHang.updateKhachHang(new KhachHang(txtMa.getText(), txtSDT.getText(), txtTen.getText(), txtDiaChi.getText(), kh.getGioHang(), path)))
			{
				showMess("Cập nhật thông tin khách hàng thành công");
				txtTen.setEditable(false);
				txtTen.setBackground(new Color(230, 230, 230));
				txtSDT.setEditable(false);
				txtSDT.setBackground(new Color(230, 230, 230));
			}
			else
				showMess("Không thể cập nhật thông tin khách hàng!");
			}
		}else if (e.getSource().equals(btn_DoiMatKhau)) {
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
					if (txtMatKhauCu.getText().trim().equals(new Dao_TaiKhoan().timKiemTaiKhoan(null, kh.getMaKH().trim()).getMatKhau().trim())) {
						if (txtMkMoi.getText().trim().equals(txtRMKMoi.getText().trim())) {
							kt=false;
							new Dao_TaiKhoan().updateMatKhau(new Dao_TaiKhoan().timKiemTaiKhoan(null, kh.getMaKH().trim()).getTaiKhoan().trim(), txtMkMoi.getText());
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
		}else if (e.getSource().equals(btn_XemLichMuaHang)) {
			JOptionPane.showMessageDialog(null,new Gui_KhachHang_LSMuaHang(kh).conTrol_LSMuaHang(), "Lịch Sử Mua Hàng",JOptionPane.DEFAULT_OPTION);
		}
	}
}
