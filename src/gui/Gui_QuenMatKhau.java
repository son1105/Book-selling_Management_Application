package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import dao.Dao_TaiKhoan;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Gui_QuenMatKhau extends JFrame implements CaretListener, MouseListener, ActionListener {
	private JPanel pnClearTextTaiKhoan;
	private JPanel pnSDT;
	private JLabel lblIconClearTextTaiKhoan;
	private JLabel lblIconClearTextSDT;
	private JLabel lblMa;
	private JLabel lblIconCheckTextMa;
	private JButton btnDoiMa;
	private JTextField txtTaiKhoan, txtSDT, txtNhapMa;
	private JPanel pnClearTextSDT;
	private JLabel lblBack;
	private JButton btnXacNhan;

	public Gui_QuenMatKhau() {
		setUndecorated(true);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 23, 23));

		JPanel QuenMatKhau = new JPanel() {
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
				Color color1 = new Color(74, 86, 157);
				Color color2 = new Color(220, 36, 36);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		getContentPane().add(QuenMatKhau, BorderLayout.CENTER);
		QuenMatKhau.setLayout(null);

		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\QMK.png").getImage().getScaledInstance(330,
				330, java.awt.Image.SCALE_SMOOTH)));
		lblImg.setBounds(75, 144, 330, 330);
		QuenMatKhau.add(lblImg);

		lblBack = new JLabel("Quay Lại.");
		lblBack.setForeground(Color.WHITE);
		lblBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblBack.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\back.png"));
		lblBack.setBounds(10, 11, 107, 32);
		QuenMatKhau.add(lblBack);

		JPanel pnThongTinNhap = new JPanel() {
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
		pnThongTinNhap.setOpaque(false);
		pnThongTinNhap.setBounds(503, 57, 330, 380);
		QuenMatKhau.add(pnThongTinNhap);
		pnThongTinNhap.setLayout(null);

		JLabel lblTitle = new JLabel("Quên Mật Khẩu.");
		lblTitle.setForeground(Color.GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTitle.setBounds(10, 11, 132, 27);
		pnThongTinNhap.add(lblTitle);

		JLabel lblTitle_chill = new JLabel("Lấy lại mật khẩu tài khoản nhà sách.");
		lblTitle_chill.setForeground(Color.GRAY);
		lblTitle_chill.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle_chill.setBounds(10, 36, 235, 14);
		pnThongTinNhap.add(lblTitle_chill);

		JLabel lblHr = new JLabel("");
		lblHr.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblHr.setBounds(10, 55, 340, 1);
		pnThongTinNhap.add(lblHr);

		JLabel lblTaiKhoan = new JLabel("Tài Khoản");
		lblTaiKhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblTaiKhoan.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\user.png"));
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTaiKhoan.setBounds(10, 60, 235, 20);
		pnThongTinNhap.add(lblTaiKhoan);

		JPanel pnTaiKhoan = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(74, 86, 157));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnTaiKhoan.setOpaque(false);
		pnTaiKhoan.setBounds(10, 85, 280, 34);
		pnThongTinNhap.add(pnTaiKhoan);
		pnTaiKhoan.setLayout(null);

		txtTaiKhoan = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 13, 13);
				g2.dispose();
				super.paint(g);
			}
		};
		txtTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTaiKhoan.setOpaque(false);
		txtTaiKhoan.setBorder(null);
		txtTaiKhoan.setBounds(2, 2, 245, 30);
		pnTaiKhoan.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		pnClearTextTaiKhoan = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 13, 13);
				g2.dispose();
				super.paint(g);
			}
		};
		pnClearTextTaiKhoan.setOpaque(false);
		FlowLayout fl_pnClearTextTaiKhoan = (FlowLayout) pnClearTextTaiKhoan.getLayout();
		fl_pnClearTextTaiKhoan.setAlignment(FlowLayout.RIGHT);
		pnClearTextTaiKhoan.setBounds(228, 2, 50, 30);
		pnTaiKhoan.add(pnClearTextTaiKhoan);

		lblIconClearTextTaiKhoan = new JLabel("");
		pnClearTextTaiKhoan.add(lblIconClearTextTaiKhoan);

		pnSDT = new JPanel() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(74, 86, 157));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnSDT.setLayout(null);
		pnSDT.setOpaque(false);
		pnSDT.setBounds(10, 155, 280, 34);
		pnThongTinNhap.add(pnSDT);

		txtSDT = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 13, 13);
				g2.dispose();
				super.paint(g);
			}
		};
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSDT.setOpaque(false);
		txtSDT.setColumns(10);
		txtSDT.setBorder(null);
		txtSDT.setBounds(2, 2, 245, 30);
		pnSDT.add(txtSDT);

		pnClearTextSDT = new JPanel() {
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
		pnClearTextSDT.setOpaque(false);
		FlowLayout fl_pnClearTextSDT = (FlowLayout) pnClearTextSDT.getLayout();
		fl_pnClearTextSDT.setAlignment(FlowLayout.RIGHT);
		pnClearTextSDT.setOpaque(false);
		pnClearTextSDT.setBounds(228, 2, 50, 30);
		pnSDT.add(pnClearTextSDT);

		lblIconClearTextSDT = new JLabel("");
		pnClearTextSDT.add(lblIconClearTextSDT);

		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		lblSDT.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\phone.png"));
		lblSDT.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSDT.setBounds(10, 130, 235, 20);
		pnThongTinNhap.add(lblSDT);

		JLabel lblNewLabel_6 = new JLabel("--------------------Mã Xác Nhận--------------------");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 200, 340, 20);
		pnThongTinNhap.add(lblNewLabel_6);

		btnDoiMa = new JButton("Đổi Mã");
		btnDoiMa.setFocusable(false);
		btnDoiMa.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDoiMa.setForeground(Color.WHITE);
		btnDoiMa.setBackground(new Color(123, 104, 238));
		btnDoiMa.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDoiMa.setBounds(10, 237, 73, 30);
		pnThongTinNhap.add(btnDoiMa);

		JPanel pnMa = new JPanel();
		pnMa.setOpaque(false);
		pnMa.setBorder(new TitledBorder(new LineBorder(new Color(74, 86, 157), 2, true), "M\u00E3 X\u00E1c Nh\u1EADn.",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(74, 86, 157)));
		pnMa.setBounds(93, 227, 197, 45);
		pnThongTinNhap.add(pnMa);
		pnMa.setLayout(new BorderLayout(0, 0));

		lblMa = new JLabel(taoMa());

		lblMa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnMa.add(lblMa, BorderLayout.CENTER);

		JPanel pnNhapMa = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(74, 86, 157));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.dispose();
				super.paint(g);
			}
		};
		pnNhapMa.setLayout(null);
		pnNhapMa.setOpaque(false);
		pnNhapMa.setBounds(10, 287, 280, 34);
		pnThongTinNhap.add(pnNhapMa);

		txtNhapMa = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 13, 13);
				g2.dispose();
				super.paint(g);
			}
		};

		txtNhapMa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtNhapMa.getText().equals("") || txtNhapMa.getText().equals("Nhập Mã Xác Nhận...")) {
					txtNhapMa.setText("Nhập Mã Xác Nhận...");
					txtNhapMa.setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtNhapMa.setText("");
				txtNhapMa.setFont(new Font("Tahoma", Font.BOLD, 12));
			}

		});
		txtNhapMa.setText("Nhập Mã Xác Nhận...");
		txtNhapMa.setOpaque(false);
		txtNhapMa.setColumns(10);
		txtNhapMa.setBorder(null);
		txtNhapMa.setBounds(2, 2, 240, 30);
		pnNhapMa.add(txtNhapMa);

		JPanel pnCheckTextMa = new JPanel() {
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
		FlowLayout fl_pnCheckTextMa = (FlowLayout) pnCheckTextMa.getLayout();
		fl_pnCheckTextMa.setAlignment(FlowLayout.RIGHT);
		pnCheckTextMa.setOpaque(false);
		pnCheckTextMa.setBounds(228, 2, 50, 30);
		pnNhapMa.add(pnCheckTextMa);

		lblIconCheckTextMa = new JLabel("");
		pnCheckTextMa.add(lblIconCheckTextMa);

		btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setBackground(new Color(74, 86, 157));
		btnXacNhan.setBorder(new LineBorder(new Color(220, 36, 36), 2));
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setFocusable(false);
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXacNhan.setBounds(110, 332, 110, 39);
		pnThongTinNhap.add(btnXacNhan);

		txtTaiKhoan.addCaretListener(this);
		txtNhapMa.addCaretListener(this);
		txtSDT.addCaretListener(this);

		pnClearTextTaiKhoan.addMouseListener(this);
		pnClearTextSDT.addMouseListener(this);
		lblBack.addMouseListener(this);
		btnDoiMa.addActionListener(this);
		btnXacNhan.addActionListener(this);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Gui_QuenMatKhau().setVisible(true);
	}

	/**
	 * Tạo mã xác nhận
	 * 
	 * @return String
	 */
	public String taoMa() {
		String s = "";
		Random a = new Random();
		int dem = 0;
		while (dem < 5) {
			int x = a.nextInt((122 - 48) + 1) + 48;
			if (x >= 48 && x <= 57 || x >= 65 && x <= 90 || x >= 97 && x <= 122) {
				s += (char) x;
				dem++;
			}
		}
		return s;

	}

	@Override
	public void caretUpdate(CaretEvent e) {
		if (e.getSource().equals(txtTaiKhoan)) {
			if (!txtTaiKhoan.getText().trim().equals("")) {
				lblIconClearTextTaiKhoan.setIcon(new ImageIcon("img\\imgTitleBar\\exit_red.png"));
			} else {
				lblIconClearTextTaiKhoan.setIcon(new ImageIcon(""));
			}
		} else if (e.getSource().equals(txtSDT)) {
			if (!txtSDT.getText().trim().equals("")) {
				lblIconClearTextSDT.setIcon(new ImageIcon("img\\imgTitleBar\\exit_red.png"));
			} else {
				lblIconClearTextSDT.setIcon(new ImageIcon(""));
			}
		} else if (e.getSource().equals(txtNhapMa)) {
			if (!txtNhapMa.getText().trim().equals("") && !txtNhapMa.getText().trim().equals("Nhập Mã Xác Nhận...")) {
				if (txtNhapMa.getText().trim().equals(lblMa.getText())) {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
				} else {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
				}
			} else {
				lblIconCheckTextMa.setIcon(new ImageIcon(""));
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnClearTextTaiKhoan)) {
			txtTaiKhoan.setText("");
		} else if (e.getSource().equals(pnClearTextSDT)) {
			txtSDT.setText("");
		} else if (e.getSource().equals(lblBack)) {
			setVisible(false);
			new GUI_DangNhap().setVisible(true);
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDoiMa)) {
			lblMa.setText(taoMa());
			if (!txtNhapMa.getText().trim().equals("") && !txtNhapMa.getText().trim().equals("Nhập Mã Xác Nhận...")) {
				if (txtNhapMa.getText().trim().equals(lblMa.getText())) {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
				} else {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
				}
			} else {
				lblIconCheckTextMa.setIcon(new ImageIcon(""));
			}
		} else if (e.getSource().equals(btnXacNhan)) {
			if (!txtNhapMa.getText().trim().equals("") && !txtNhapMa.getText().trim().equals("Nhập Mã Xác Nhận...")) {
				if (txtNhapMa.getText().trim().equals(lblMa.getText())) {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\Tick.png"));
					if (txtTaiKhoan.getText().trim().equals("") || txtSDT.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Không Được Bỏ Trống Tài Khoảnh hoặc Số Điện Thoại.",
								"WARNING", JOptionPane.WARNING_MESSAGE);
					} else {
						if (new Dao_TaiKhoan().timKiemAccount(txtTaiKhoan.getText().trim(),
								txtSDT.getText().trim()) != null) {
							JPanel pnMK = new JPanel();
							pnMK.setPreferredSize(new Dimension(352, 88));
							pnMK.setBounds(40, 68, 310, 160);
							pnMK.setLayout(null);

							JLabel lblMkMoi = new JLabel("Mật Khẩu Mới: ");
							lblMkMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
							lblMkMoi.setBounds(10, 11, 130, 25);
							pnMK.add(lblMkMoi);

							JTextField txtMkMoi = new JTextField();
							txtMkMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
							txtMkMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
							txtMkMoi.setBounds(138, 11, 194, 25);
							pnMK.add(txtMkMoi);
							txtMkMoi.setColumns(10);

							JLabel lblRMkMoi = new JLabel("Nhập Lại Mật Khẩu:  ");
							lblRMkMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
							lblRMkMoi.setBounds(10, 47, 130, 25);
							pnMK.add(lblRMkMoi);

							JTextField txtRMkMoi = new JTextField();
							txtRMkMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
							txtRMkMoi.setColumns(10);
							txtRMkMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
							txtRMkMoi.setBounds(138, 47, 194, 25);
							pnMK.add(txtRMkMoi);
							Boolean kt = true;
							while (kt) {
								JOptionPane.showMessageDialog(null, pnMK, "Nhập Mật Khẩu Mới.",JOptionPane.DEFAULT_OPTION);
								if (txtMkMoi.getText().trim().equals(txtRMkMoi.getText().trim())) {
									kt = false;
								}

							}
							new Dao_TaiKhoan().updateMatKhau(txtTaiKhoan.getText().trim(), txtMkMoi.getText().trim());
							JOptionPane.showMessageDialog(null, "Tạo Mật Khẩu Mới Thành Công.");
						}
					}
				} else {
					lblIconCheckTextMa.setIcon(new ImageIcon("img\\imgTitleBar\\eror.png"));
				}
			} else {
				lblIconCheckTextMa.setIcon(new ImageIcon(""));
				JOptionPane.showMessageDialog(null, "Không Được Bỏ Trống.", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}
