package gui;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import dao.Dao_DiaChi;
import dao.Dao_KhachHang;
import dao.Dao_TaiKhoan;
import entity.DiaChi;
import entity.KhachHang;
import entity.QuanHuyen;
import entity.TaiKhoan;
import entity.TinhThanh;
import entity.XaPhuong;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SwingConstants;

public class Gui_DangKy extends JFrame implements MouseListener, CaretListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFullName;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JPasswordField txtRMatKhau;
	private JTextField txtSDT;
	private JPanel pnClearTextFullName;
	private JLabel lblClearTextFullName;
	private JPanel pnClearTextTaiKhoan;
	private JLabel lblClearTextTaiKhoan;
	private JPanel pnClearTextSDT;
	private JLabel lblClearTextSDT;
	private JPanel pnShowHideTextMatKhau;
	private JPanel pnShowHideTextRMatKhau;
	private JLabel lblShowHideTextMatKhau;
	private JLabel lblShowHideTextRMatKhau;
	private JLabel lblBack;
	private JPanel DangKy;
	private JPanel pnDangKy;
	private JPanel pnFullName;
	private JPanel pnTaiKhoan;
	private JPanel pnRMatKhau;
	private JPanel pnSDT;
	private JLabel lblCheckTextRMatKhau;
	private JLabel lblCheckTextSDT;

	private boolean checkSdt=false, checkRMK=false, checkTK = false, checkHoTen = false;
	private JLabel lblCheckTextTaiKhoan;
	private JLabel lblCheckTextHoTen;
	private int indexQuanHuyen;
	
	public Gui_DangKy() {

		setUndecorated(true);
		setVisible(true);
		setSize(950, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
		DangKy = new JPanel() {
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
				Color color1 = new Color(153, 0, 153);
				Color color2 = new Color(0, 204, 204);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		getContentPane().add(DangKy, BorderLayout.CENTER);
		DangKy.setLayout(null);

		JPanel pnSignUp = new JPanel() {
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
		pnSignUp.setForeground(Color.LIGHT_GRAY);
		pnSignUp.setOpaque(false);
		pnSignUp.setBounds(532, 32, 384, 500);
		DangKy.add(pnSignUp);
		pnSignUp.setLayout(null);

		JLabel lblFullName = new JLabel("Họ Tên :");
		lblFullName.setIcon(new ImageIcon("D:\\JavaSwing\\DoAn_QuanLyBanSach\\img\\IMG_LOGIN_SignIn\\user.png"));
		lblFullName.setForeground(Color.LIGHT_GRAY);
		lblFullName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblFullName.setBounds(16, 72, 255, 24);
		pnSignUp.add(lblFullName);

		pnFullName = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(153, 0, 153));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnFullName.setOpaque(false);
		pnFullName.setBounds(10, 99, 320, 36);
		pnSignUp.add(pnFullName);
		pnFullName.setLayout(null);

		txtFullName = new JTextField() {
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
		txtFullName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFullName.setBorder(null);
		txtFullName.setOpaque(false);
		txtFullName.setBounds(2, 2, 290, 32);
		pnFullName.add(txtFullName);
		txtFullName.setColumns(10);

		pnClearTextFullName = new JPanel() {
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
		pnClearTextFullName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnClearTextFullName.setOpaque(false);
		FlowLayout fl_pnClearTextFullName = (FlowLayout) pnClearTextFullName.getLayout();
		fl_pnClearTextFullName.setAlignment(FlowLayout.RIGHT);
		pnClearTextFullName.setBounds(268, 2, 50, 32);
		pnFullName.add(pnClearTextFullName);

		lblClearTextFullName = new JLabel("");
		pnClearTextFullName.add(lblClearTextFullName);

		lblCheckTextHoTen = new JLabel("New label");
		lblCheckTextHoTen.setBounds(323, 7, 20, 20);
		pnFullName.add(lblCheckTextHoTen);
		

		pnTaiKhoan = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(153, 0, 153));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};

		pnTaiKhoan.setLayout(null);
		pnTaiKhoan.setOpaque(false);
		pnTaiKhoan.setBounds(10, 173, 320, 36);
		pnSignUp.add(pnTaiKhoan);

		txtTaiKhoan = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		txtTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTaiKhoan.setOpaque(false);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBorder(null);
		txtTaiKhoan.setBounds(2, 2, 290, 32);
		pnTaiKhoan.add(txtTaiKhoan);

		pnClearTextTaiKhoan = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		FlowLayout flowLayout = (FlowLayout) pnClearTextTaiKhoan.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnClearTextTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnClearTextTaiKhoan.setOpaque(false);
		pnClearTextTaiKhoan.setBounds(268, 2, 50, 32);
		pnTaiKhoan.add(pnClearTextTaiKhoan);

		lblClearTextTaiKhoan = new JLabel("");
		pnClearTextTaiKhoan.add(lblClearTextTaiKhoan);

		lblCheckTextTaiKhoan = new JLabel("");
		lblCheckTextTaiKhoan.setBounds(323, 7, 20, 20);
		pnTaiKhoan.add(lblCheckTextTaiKhoan);

		JLabel lblTaiKhoan = new JLabel("Tài Khoản:");
		lblTaiKhoan.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\user.png"));
		lblTaiKhoan.setForeground(Color.LIGHT_GRAY);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblTaiKhoan.setBounds(16, 146, 255, 24);
		pnSignUp.add(lblTaiKhoan);

		JPanel pnMatKhau = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(153, 0, 153));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnMatKhau.setLayout(null);
		pnMatKhau.setOpaque(false);
		pnMatKhau.setBounds(10, 247, 320, 36);
		pnSignUp.add(pnMatKhau);

		txtMatKhau = new JPasswordField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		txtMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtMatKhau.setOpaque(false);
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(null);
		txtMatKhau.setBounds(2, 2, 284, 32);
		pnMatKhau.add(txtMatKhau);

		pnShowHideTextMatKhau = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnShowHideTextMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout fl_pnShowHideTextMatKhau = (FlowLayout) pnShowHideTextMatKhau.getLayout();
		fl_pnShowHideTextMatKhau.setAlignment(FlowLayout.RIGHT);
		pnShowHideTextMatKhau.setOpaque(false);
		pnShowHideTextMatKhau.setBounds(268, 2, 50, 32);
		pnMatKhau.add(pnShowHideTextMatKhau);

		lblShowHideTextMatKhau = new JLabel("");
		lblShowHideTextMatKhau.setBorder(new MatteBorder(0, 1, 0, 0, (Color) Color.GRAY));
		lblShowHideTextMatKhau
				.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
		pnShowHideTextMatKhau.add(lblShowHideTextMatKhau);

		JLabel lblCheckTextMatKhau = new JLabel("");
		lblCheckTextMatKhau.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
		lblCheckTextMatKhau.setBounds(323, 7, 20, 20);
		pnMatKhau.add(lblCheckTextMatKhau);

		JLabel lblMatKhau = new JLabel("Mật Khẩu :");
		lblMatKhau.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\pass.png"));
		lblMatKhau.setForeground(Color.LIGHT_GRAY);
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblMatKhau.setBounds(16, 220, 255, 24);
		pnSignUp.add(lblMatKhau);

		pnRMatKhau = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(153, 0, 153));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnRMatKhau.setLayout(null);
		pnRMatKhau.setOpaque(false);
		pnRMatKhau.setBounds(10, 321, 320, 36);
		pnSignUp.add(pnRMatKhau);

		txtRMatKhau = new JPasswordField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		txtRMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtRMatKhau.setOpaque(false);
		txtRMatKhau.setColumns(10);
		txtRMatKhau.setBorder(null);
		txtRMatKhau.setBounds(2, 2, 284, 32);
		pnRMatKhau.add(txtRMatKhau);

		pnShowHideTextRMatKhau = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnShowHideTextRMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FlowLayout fl_pnShowHideTextRMatKhau = (FlowLayout) pnShowHideTextRMatKhau.getLayout();
		fl_pnShowHideTextRMatKhau.setAlignment(FlowLayout.RIGHT);
		pnShowHideTextRMatKhau.setOpaque(false);
		pnShowHideTextRMatKhau.setBounds(268, 2, 50, 32);
		pnRMatKhau.add(pnShowHideTextRMatKhau);

		lblShowHideTextRMatKhau = new JLabel("");
		lblShowHideTextRMatKhau.setBorder(new MatteBorder(0, 1, 0, 0, (Color) Color.GRAY));
		lblShowHideTextRMatKhau
				.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
		pnShowHideTextRMatKhau.add(lblShowHideTextRMatKhau);

		lblCheckTextRMatKhau = new JLabel("");
		lblCheckTextRMatKhau.setBounds(323, 7, 20, 20);
		pnRMatKhau.add(lblCheckTextRMatKhau);

		JLabel lblRMatKhau = new JLabel("Nhập Lại Mật Khẩu :");
		lblRMatKhau.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\pass.png"));
		lblRMatKhau.setForeground(Color.LIGHT_GRAY);
		lblRMatKhau.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblRMatKhau.setBounds(16, 294, 255, 24);
		pnSignUp.add(lblRMatKhau);

		pnSDT = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(153, 0, 153));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnSDT.setLayout(null);
		pnSDT.setOpaque(false);
		pnSDT.setBounds(10, 395, 320, 36);
		pnSignUp.add(pnSDT);

		txtSDT = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSDT.setOpaque(false);
		txtSDT.setColumns(10);
		txtSDT.setBorder(null);
		txtSDT.setBounds(2, 2, 290, 32);
		pnSDT.add(txtSDT);

		pnClearTextSDT = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		FlowLayout flowLayout_1 = (FlowLayout) pnClearTextSDT.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnClearTextSDT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnClearTextSDT.setOpaque(false);
		pnClearTextSDT.setBounds(268, 2, 50, 32);
		pnSDT.add(pnClearTextSDT);

		lblClearTextSDT = new JLabel("");
		pnClearTextSDT.add(lblClearTextSDT);

		lblCheckTextSDT =new JLabel("");
		lblCheckTextSDT.setBounds(323, 7, 20, 20);
		pnSDT.add(lblCheckTextSDT);

		JLabel lblSDT = new JLabel("Số Điện Thoại :");
		lblSDT.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\phone.png"));
		lblSDT.setForeground(Color.LIGHT_GRAY);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblSDT.setBounds(16, 368, 255, 24);
		pnSignUp.add(lblSDT);

		JLabel lblTitle = new JLabel("ĐĂNG KÝ");
		lblTitle.setForeground(Color.GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitle.setBounds(16, 11, 99, 30);
		pnSignUp.add(lblTitle);

		JLabel lblTitle_chil = new JLabel("Chào Mừng Đến Với Nhà Sách K2S");
		lblTitle_chil.setForeground(Color.GRAY);
		lblTitle_chil.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle_chil.setBounds(16, 34, 255, 20);
		pnSignUp.add(lblTitle_chil);

		JLabel lblHR = new JLabel("");
		lblHR.setBorder(new LineBorder(Color.GRAY));
		lblHR.setBounds(10, 60, 380, 1);
		pnSignUp.add(lblHR);

		pnDangKy = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int w = getWidth(), h = getHeight();
				Color color2 = new Color(153, 0, 153);
				Color color1 = new Color(0, 204, 204);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);

				g2d.setPaint(gp);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

			}
		};
		pnDangKy.setOpaque(false);
		pnDangKy.setBounds(111, 442, 160, 36);
		pnSignUp.add(pnDangKy);
		pnDangKy.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("Đăng Ký");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnDangKy.add(lblNewLabel_1);

		JLabel lblImg = new JLabel("New label");
		lblImg.setIcon(new ImageIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bg.png").getImage().getScaledInstance(500,
				500, java.awt.Image.SCALE_SMOOTH)));
		lblImg.setBounds(22, 84, 500, 500);
		DangKy.add(lblImg);

		lblBack = new JLabel("Quay Lại");
		lblBack.setForeground(Color.WHITE);
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBack.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\back.png"));
		lblBack.setBounds(10, 11, 177, 32);
		DangKy.add(lblBack);

		txtFullName.addCaretListener(this);
		txtTaiKhoan.addCaretListener(this);
		txtSDT.addCaretListener(this);
		txtRMatKhau.addCaretListener(this);
		txtMatKhau.addCaretListener(this);
		
		pnClearTextFullName.addMouseListener(this);
		pnClearTextTaiKhoan.addMouseListener(this);
		pnShowHideTextMatKhau.addMouseListener(this);
		pnShowHideTextRMatKhau.addMouseListener(this);
		pnClearTextSDT.addMouseListener(this);
		lblBack.addMouseListener(this);
		pnDangKy.addMouseListener(this);
		
	}

	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnClearTextFullName)) {
			txtFullName.setText("");
		} else if (e.getSource().equals(pnClearTextTaiKhoan)) {
			txtTaiKhoan.setText("");
		} else if (e.getSource().equals(pnClearTextSDT)) {
			txtSDT.setText("");
		} else if (e.getSource().equals(pnShowHideTextMatKhau)) {
			if (txtMatKhau.getEchoChar() == '•') {
				lblShowHideTextMatKhau
						.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eye.png"));
				txtMatKhau.setEchoChar((char) 0);
			} else {
				lblShowHideTextMatKhau.setIcon(
						new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
				txtMatKhau.setEchoChar('•');
			}
		} else if (e.getSource().equals(pnShowHideTextRMatKhau)) {
			if (txtRMatKhau.getEchoChar() == '•') {
				lblShowHideTextRMatKhau
						.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\eye.png"));
				txtRMatKhau.setEchoChar((char) 0);
			} else {
				lblShowHideTextRMatKhau.setIcon(
						new ImageIcon("img\\IMG_LOGIN_SignIn\\eyeClose.png"));
				txtRMatKhau.setEchoChar('•');
			}
		} else if (e.getSource().equals(lblBack)) {
			setVisible(false);
			new GUI_DangNhap().setVisible(true);
		}else if (e.getSource().equals(pnDangKy)) {
			if (checkHoTen && checkTK && checkSdt && checkRMK) {
				JPanel pnDiaChi = new JPanel();
				pnDiaChi.setPreferredSize(new Dimension(310, 160));
				pnDiaChi.setBounds(40, 68, 310, 160);
				pnDiaChi.setLayout(null);
				
				JLabel lblTinhThanh = new JLabel("Tỉnh Thành: ");
				lblTinhThanh.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblTinhThanh.setBounds(10, 10, 90, 25);
				pnDiaChi.add(lblTinhThanh);
				
				JComboBox<String> cboTinhThanh = new JComboBox<String>();
				cboTinhThanh.setBackground(Color.WHITE);
				cboTinhThanh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboTinhThanh.setBounds(100, 10, 190, 25);
				pnDiaChi.add(cboTinhThanh);
				
				for (TinhThanh i: new Dao_DiaChi().getDsTinhThanh_1()) {
					cboTinhThanh.addItem(i.getTenTinhThanh());
				}
				JLabel lblQuanHuyen = new JLabel("Quận Huyện:");
				lblQuanHuyen.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblQuanHuyen.setBounds(10, 46, 90, 25);
				pnDiaChi.add(lblQuanHuyen);
				
				JComboBox<String> cboQuanHuyen = new JComboBox<String>();
				cboQuanHuyen.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboQuanHuyen.setBackground(Color.WHITE);
				cboQuanHuyen.setBounds(100, 46, 190, 25);
				pnDiaChi.add(cboQuanHuyen);
				
				for (QuanHuyen i: new Dao_DiaChi().getDsQuanHuyen_1(1)) {
					cboQuanHuyen.addItem(i.getTenQuanHuyen());
				}
				
				JLabel lblPhuongXa = new JLabel("Phường Xã: ");
				lblPhuongXa.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPhuongXa.setBounds(10, 82, 90, 25);
				pnDiaChi.add(lblPhuongXa);
				
				JComboBox<String> cboPhuongXa = new JComboBox<String>();
				cboPhuongXa.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboPhuongXa.setBackground(Color.WHITE);
				cboPhuongXa.setBounds(100, 82, 190, 25);
				pnDiaChi.add(cboPhuongXa);
				
				for (XaPhuong i: new Dao_DiaChi().getDsXaPhuong_1(1)) {
					cboPhuongXa.addItem(i.getTenXaPhuong());
				}
				
				JLabel lblSoNha = new JLabel("Số Nhà: ");
				lblSoNha.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSoNha.setBounds(10, 118, 90, 25);
				pnDiaChi.add(lblSoNha);
				
				JTextField txtSoNha = new JTextField();
				txtSoNha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				txtSoNha.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtSoNha.setBounds(100, 118, 190, 25);
				pnDiaChi.add(txtSoNha);
				txtSoNha.setColumns(10);
				
				cboTinhThanh.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange()==ItemEvent.SELECTED) {
							cboQuanHuyen.removeAllItems();
							for (QuanHuyen i: new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1)) {
								cboQuanHuyen.addItem(i.getTenQuanHuyen());
							}
							cboPhuongXa.removeAllItems();
							int index = Integer.parseInt(new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1).get(0).getIdQuanHuyen());
							for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
								cboPhuongXa.addItem(i.getTenXaPhuong());
							}
						}
					}
				});
				 indexQuanHuyen = 1;
				cboQuanHuyen.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange()==ItemEvent.SELECTED) {
							cboPhuongXa.removeAllItems();
							int index = Integer.parseInt(new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1).get(cboQuanHuyen.getSelectedIndex()).getIdQuanHuyen());
							indexQuanHuyen = index;
							for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
								cboPhuongXa.addItem(i.getTenXaPhuong());
							}
						}
					}
				});
				
				JOptionPane.showMessageDialog(null, pnDiaChi,"Nhập Địa Chỉ", JOptionPane.DEFAULT_OPTION);
				String diaChi = txtSoNha.getText()+", "+cboPhuongXa.getSelectedItem()+", "+cboQuanHuyen.getSelectedItem()+", "+cboTinhThanh.getSelectedItem();
				String maKH = taoMaKhachHang();
				new Dao_DiaChi().insertDiaChi(new DiaChi(maKH, txtSoNha.getText(), new Dao_DiaChi().getDsXaPhuong_1(indexQuanHuyen).get(cboPhuongXa.getSelectedIndex()).getIdXaPhuong()));
				new Dao_KhachHang().insertKhachHang(new KhachHang(maKH,txtSDT.getText().trim(), txtFullName.getText().trim(), diaChi, null, null));
				new Dao_TaiKhoan().insertTaiKhoanKhach(new TaiKhoan(txtTaiKhoan.getText().trim(), String.copyValueOf(txtMatKhau.getPassword()).trim(), "Khách Hàng", maKH, null));
				
			}
		}
	}

	public String taoMaKhachHang() {
		KhachHang hang =new Dao_KhachHang().getKhachHangMoi();
		if (hang!=null) {
			if (Integer.parseInt(hang.getMaKH().substring(2))+1<10) {
				return  "KH0000000"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<100) {
				return "KH000000"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<1000) {
				return "KH00000"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<10000) {
				return "KH0000"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<100000) {
				return "KH000"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<1000000) {
				return "KH00"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<10000000) {
				return "KH0"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}else if (Integer.parseInt(hang.getMaKH().substring(2))+1<100000000) {
				return "KH"+(Integer.parseInt(hang.getMaKH().substring(2))+1);
			}
		}
		return  "KH00000001";
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(pnDangKy)) {
			pnDangKy.setBounds(109, 440, 164, 40);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(pnDangKy)) {
			pnDangKy.setBounds(111, 442, 160, 36);
		}
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		if (e.getSource().equals(txtFullName)) {
			if (!txtFullName.getText().trim().equals("")) {
				lblClearTextFullName.setIcon(new ImageIcon("img\\imgTitleBar\\exit_red.png"));
				if (txtFullName.getText().trim().matches("^([A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựyýỳỷỹỵ]*(\\s){0,1})+$")) {
					lblCheckTextHoTen.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
					pnFullName.setBounds(10, 99, 348, 36);
					checkHoTen=true;
				}else {
					checkHoTen=false;
					lblCheckTextHoTen.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
					pnFullName.setBounds(10, 99, 348, 36);
				}
				
			} else {
				checkHoTen=false;
				lblClearTextFullName.setIcon(new ImageIcon(""));
				pnFullName.setBounds(10, 99, 320, 36);

			}
		} else if (e.getSource().equals(txtTaiKhoan)) {
			if (!txtTaiKhoan.getText().trim().equals("")) {
				lblClearTextTaiKhoan.setIcon(new ImageIcon("img\\imgTitleBar\\exit_red.png"));
				if (txtTaiKhoan.getText().matches("^[a-zA-Z0-9_-]{8,16}$")) {
					checkTK = true;
					lblCheckTextTaiKhoan.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
					pnTaiKhoan.setBounds(10, 173, 348, 36);
				}else{
					checkTK = false;
					lblCheckTextTaiKhoan.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
					pnTaiKhoan.setBounds(10, 173, 348, 36);
				}
			} else {
				checkTK = false;
				lblClearTextTaiKhoan.setIcon(new ImageIcon(""));
				pnTaiKhoan.setBounds(10, 173, 320, 36);

			}
		} else if (e.getSource().equals(txtSDT)) {
			if (!txtSDT.getText().trim().equals("")) {
				lblClearTextSDT.setIcon(new ImageIcon("img\\imgTitleBar\\exit_red.png"));
				if (!txtSDT.getText().matches("^0[0-9]{9}")) {
					checkSdt=false;
					lblCheckTextSDT.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
					pnSDT.setBounds(10, 395, 348, 36);
				}else {
					checkSdt=true;
					pnSDT.setBounds(10, 395, 348, 36);
					lblCheckTextSDT.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
				
				}
				
			} else {
				lblClearTextSDT.setIcon(new ImageIcon(""));
				pnSDT.setBounds(10, 395, 320, 36);
			}
		} else if (e.getSource().equals(txtRMatKhau)) {
			String mk =String.copyValueOf(txtRMatKhau.getPassword()), rmk = String.copyValueOf(txtMatKhau.getPassword());
			
			if (rmk.equals(mk)) {
				checkRMK = true;
				lblCheckTextRMatKhau.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
				pnRMatKhau.setBounds(10, 321, 348, 36);
			} else {
				checkRMK = false;
				lblCheckTextRMatKhau.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
				pnRMatKhau.setBounds(10, 321, 348, 36);
			}
		}else if (e.getSource().equals(txtMatKhau)) {
			String mk =String.copyValueOf(txtRMatKhau.getPassword()), rmk = String.copyValueOf(txtMatKhau.getPassword());
			
			if (rmk.equals(mk)) {
				checkRMK = true;
				lblCheckTextRMatKhau.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
				pnRMatKhau.setBounds(10, 321, 348, 36);
			} else {
				checkRMK = false;
				lblCheckTextRMatKhau.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
				pnRMatKhau.setBounds(10, 321, 348, 36);
			}
		}
		
	}
	
}
