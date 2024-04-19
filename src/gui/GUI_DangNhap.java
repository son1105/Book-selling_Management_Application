package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import dao.Dao_KhachHang;
import dao.Dao_NhanVien;
import dao.Dao_TaiKhoan;
import entity.TaiKhoan;

public class GUI_DangNhap extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = -9003570558247451142L;
	private JTextField txtMa;
	private JTextField txtsdtQmk;
	private JTextField txtTen;
	private JProgressBar pbrLoad;
	private Timer r;
	private JLabel lbASCII;
	private JTextField txtUser;
	private JPanel pntextPass;
	private JPasswordField txtMK;
	private JLabel lblIconShowHideText;
	private JPanel pnShowHideText;
	private JPanel pnClearTextUser;
	private JPanel pnDangNhap;
	private JLabel lblDangNhap;
	private JLabel lblDangKy;
	private JPanel pnDangKy;
	private JLabel lblQMK;
	private Dao_TaiKhoan taiKhoan = new Dao_TaiKhoan() ;
	private Dao_NhanVien nhanVien = new Dao_NhanVien();
	private TaiKhoan tk;
	private static Gui_KhachHang Gui_kh;

	/**
	 * Tạo Giao diện đăng nhập
	 */

	public GUI_DangNhap() {
		taiKhoan = new Dao_TaiKhoan();
		setFont(new Font("Times New Roman", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\IMG_LOGIN_SignIn\\BOOK.png"));
		setSize(1000, 500);
		setTitle("ĐĂNG NHẬP");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JLabel lbTitle = new JLabel("Chào Mừng Đến Với");
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lbTitle.setForeground(Color.PINK);
		lbTitle.setBounds(596, 67, 353, 49);
		getContentPane().add(lbTitle);

		JLabel ldTitle2 = new JLabel("Nhà Sách K2S");
		ldTitle2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		ldTitle2.setForeground(Color.PINK);
		ldTitle2.setBounds(648, 103, 248, 49);
		getContentPane().add(ldTitle2);

		JLabel lbIconUser = new JLabel("Tài Khoản:");
		lbIconUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lbIconUser.setForeground(Color.WHITE);
		lbIconUser.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\user.png"));
		lbIconUser.setBounds(652, 178, 128, 24);
		getContentPane().add(lbIconUser);

		JPanel pntextUser = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 175, 175));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pntextUser.setOpaque(false);
		pntextUser.setBounds(648, 205, 250, 36);
		getContentPane().add(pntextUser);
		pntextUser.setLayout(null);

		txtUser = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		txtUser.setText("NV2201");
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtUser.setBorder(null);
		txtUser.setOpaque(false);
		txtUser.setBounds(2, 2, 223, 32);
		pntextUser.add(txtUser);
		txtUser.setColumns(10);

		pnClearTextUser = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnClearTextUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout fl_pnClearTextUser = (FlowLayout) pnClearTextUser.getLayout();
		fl_pnClearTextUser.setAlignment(FlowLayout.RIGHT);
		pnClearTextUser.setOpaque(false);
		pnClearTextUser.setBounds(206, 2, 42, 32);
		pntextUser.add(pnClearTextUser);

		JLabel lblIconClearTextUser = new JLabel("");
		pnClearTextUser.add(lblIconClearTextUser);

		txtUser.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				if (!txtUser.getText().trim().equals("")) {
					lblIconClearTextUser.setIcon(
							new ImageIcon("img\\imgTitleBar\\exit_red.png"));
				} else {
					lblIconClearTextUser.setIcon(new ImageIcon(""));
				}
			}
		});

		JLabel ldIconPass = new JLabel("Mật Khẩu:");
		ldIconPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		ldIconPass.setForeground(Color.WHITE);
		ldIconPass.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\pass.png"));
		ldIconPass.setBounds(652, 250, 128, 24);
		getContentPane().add(ldIconPass);

		pntextPass = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 175, 175));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}

			
		};
		pntextPass.setLayout(null);
		pntextPass.setOpaque(false);
		pntextPass.setBounds(648, 277, 250, 36);
		getContentPane().add(pntextPass);

		txtMK = new JPasswordField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		txtMK.setOpaque(false);
		txtMK.setBorder(null);
		txtMK.setToolTipText("Nhập Vào Mật Khẩu của bạn");
		txtMK.setText("28052002");
		txtMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtMK.setEchoChar('*');
		txtMK.setColumns(10);
		txtMK.setBounds(2, 2, 212, 32);
		pntextPass.add(txtMK);

		pnShowHideText = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnShowHideText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout fl_pnShowHideText = (FlowLayout) pnShowHideText.getLayout();
		fl_pnShowHideText.setAlignment(FlowLayout.RIGHT);
		pnShowHideText.setOpaque(false);
		pnShowHideText.setBounds(194, 2, 54, 32);
		pntextPass.add(pnShowHideText);

		lblIconShowHideText = new JLabel("");
		lblIconShowHideText.setBorder(new MatteBorder(0, 1, 0, 0, (Color) Color.LIGHT_GRAY));
		lblIconShowHideText.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
		pnShowHideText.add(lblIconShowHideText);

		pnDangNhap = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Paint Border
				g2.setColor(new Color(255, 175, 175));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(getBackground());
				// Border set 2 Pix
				g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 13, 13);
				super.paintComponent(g);
			}

		};
		pnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout fl_pnDangNhap = (FlowLayout) pnDangNhap.getLayout();
		fl_pnDangNhap.setVgap(0);
		fl_pnDangNhap.setHgap(0);

		pnDangNhap.setBounds(648, 320, 120, 36);
		getContentPane().add(pnDangNhap);
		pnDangNhap.setOpaque(false);
		pnDangNhap.setBackground(Color.WHITE);

		lblDangNhap = new JLabel("Đăng Nhập");
		lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDangNhap.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\login.png"));
		pnDangNhap.add(lblDangNhap);

		pnDangKy = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Paint Border
				g2.setColor(new Color(255, 175, 175));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(getBackground());
				g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 13, 13);
				super.paintComponent(g);
			}

		};
		FlowLayout fl_pnDangKy = (FlowLayout) pnDangKy.getLayout();
		fl_pnDangKy.setVgap(2);
		fl_pnDangKy.setHgap(0);
		pnDangKy.setOpaque(false);
		pnDangKy.setBackground(Color.WHITE);
		pnDangKy.setBounds(778, 320, 120, 36);
		getContentPane().add(pnDangKy);

		lblDangKy = new JLabel("Đăng Ký");
		lblDangKy.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\sign-up.png"));
		lblDangKy.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnDangKy.add(lblDangKy);

		lblQMK = new JLabel();
		lblQMK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		String sQMK = "<html> <u>Quên Mật Khẩu ?</u></html>";
		lblQMK.setText(sQMK);
		lblQMK.setForeground(Color.WHITE);
		lblQMK.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblQMK.setBounds(724, 360, 99, 21);
		getContentPane().add(lblQMK);

		ImageIcon img3 = new ImageIcon("img\\IMG_LOGIN_SignIn\\welcome.jpg");
		Image image3 = img3.getImage();
		Image newimg3 = image3.getScaledInstance(158, 158, java.awt.Image.SCALE_SMOOTH);
		img3 = new ImageIcon(newimg3);

		pbrLoad = new JProgressBar();
		pbrLoad.setFont(new Font("Times New Roman", Font.BOLD, 11));
		pbrLoad.setStringPainted(true);
		pbrLoad.setForeground(new Color(71, 13, 175));
		pbrLoad.setMaximum(600);
		pbrLoad.setBorder(new LineBorder(new Color(173, 144, 228), 1, true)); 
		pbrLoad.setBounds(192, 440, 600, 13);

		r = new Timer(1, new ActionListener() {
			private int x = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pbrLoad.setValue(x * 10);
				x++;
				if (pbrLoad.getValue() == 600) {
					r.stop();
					setVisible(false);
					if (tk.getLoaiTaiKhoan().equals("Nhân Viên")) {
						new Gui_NhanVien(nhanVien.getNhanVien(tk.getMaNV().trim())).setVisible(true);
					}else if (tk.getLoaiTaiKhoan().equals("Quản Lý")) {
						new Gui_QuanLy(nhanVien.getNhanVien(tk.getMaNV().trim())).setVisible(true);
					}else if (tk.getLoaiTaiKhoan().equals("Khách Hàng")) {
						 Gui_kh = new Gui_KhachHang(new Dao_KhachHang().timKiemKhachHanh(tk.getMaKH().trim()));
						 Gui_kh.setVisible(true);
					}
				}
			}
		});

		JLabel lbIMg = new JLabel();
		lbIMg.setBounds(420, 250, 200, 200);
		lbIMg.setIcon(new ImageIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\userBook.png").getImage().getScaledInstance(210, 210, java.awt.Image.SCALE_SMOOTH)));
		getContentPane().add(lbIMg);


		JLabel imgLogin = new JLabel();
		imgLogin.setBounds(66, 43, 437, 367);
		imgLogin.setIcon(new ImageIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\imgBook.png").getImage().getScaledInstance(440, 390, java.awt.Image.SCALE_SMOOTH)));
		getContentPane().add(imgLogin);

		JLabel imgBG = new JLabel();
		imgBG.setBounds(0, 0, 984, 461);
		imgBG.setIcon(new ImageIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bg.jpg").getImage().getScaledInstance(984, 461, java.awt.Image.SCALE_SMOOTH)));
		getContentPane().add(imgBG);

		pnDangKy.addMouseListener(this);
		pnShowHideText.addMouseListener(this);
		pnClearTextUser.addMouseListener(this);
		pnDangNhap.addMouseListener(this);
		lblQMK.addMouseListener(this);
	}

	public String layPass(char a[]) {
		String s = "";
		for (int i = 0; i < a.length; i++) {
			s += a[i];
		}
		return s;
	}

	/**
	 * Kiểm tra tính hợp lệ của input của form đăng nhập
	 * 
	 * @param args
	 * @return boolean
	 */
	public boolean regexDN() {
		if (txtUser.getText().equals("") || layPass(txtMK.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(null, "Không Được Bỏ Trống.");
			return false;
		}
	
		return true;
	}


	/**
	 * Kiểm tra tính hợp lệ của input của form Quên Mật Khẩu
	 * 
	 * @return boolean
	 */
	public boolean regexQMK() {
		if (txtTen.getText().equals("") || txtsdtQmk.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Không Được Bỏ Trống.");
			return false;
		}
		if (!(txtsdtQmk.getText().matches("^0[0-9]{9}"))) {
			JOptionPane.showMessageDialog(null, "Số Điện Thoại Sai Cú Pháp.");
			return false;
		}
		if (!(txtMa.getText().equals(lbASCII.getText()))) {
			JOptionPane.showMessageDialog(null, "Mã Xác Nhận Sai.");
			return false;
		}
		return true;

	}

	public static void dangXuat() {
		Gui_kh.setVisible(false);
	}
	
	public static void main(String[] args) {
		new GUI_DangNhap().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnShowHideText)) {
			if (txtMK.getEchoChar() == '*') {
				lblIconShowHideText.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eye.png"));
				txtMK.setEchoChar((char) 0);
			} else {
				lblIconShowHideText.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
				txtMK.setEchoChar('*');
			}
		} else if (e.getSource().equals(pnClearTextUser)) {
			txtUser.setText("");
		} else if (e.getSource().equals(pnDangNhap)) {
			tk = taiKhoan.checkAccount(txtUser.getText(), layPass(txtMK.getPassword()));
			if (tk!=null) {
				if (regexDN()) {
					getContentPane().add(pbrLoad);
					r.start();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác.");
			}
		}else if (e.getSource().equals(pnDangKy)) {
			setVisible(false);
			new Gui_DangKy().setVisible(true);
		}else if (e.getSource().equals(lblQMK)) {
			setVisible(false);
			new Gui_QuenMatKhau().setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(pnDangNhap)) {
			pnDangNhap.setBackground(new Color(95, 33, 192));
			lblDangNhap.setForeground(Color.white);
		} else if (e.getSource().equals(pnDangKy)) {
			pnDangKy.setBackground(new Color(95, 33, 192));
			lblDangKy.setForeground(Color.white);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(pnDangNhap)) {
			pnDangNhap.setBackground(Color.white);
			lblDangNhap.setForeground(Color.black);
			
			
		} else if (e.getSource().equals(pnDangKy)) {
			pnDangKy.setBackground(Color.white);
			lblDangKy.setForeground(Color.black);
		}
	}
}
