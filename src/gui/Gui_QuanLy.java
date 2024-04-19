package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import entity.NhanVien;

public class Gui_QuanLy extends JFrame implements MouseListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnNV;
	private JPanel pnMenu;
	private JLabel lblExtend;
	private JLabel lblTitle;
	private NhanVien nv;
	private JPanel pnQLNV;
	private JPanel pnQLTTNV;
	private JLabel lblQLNV;
	private JPanel pnXemLSBan;
	private JLabel lblXemLSBan;
	private JComponent pnThongKe;
	private JLabel lblThongKe;
	private JPanel pnExit;
	private JLabel lblExit;
	private JPanel pnChayAnimation;
	private JPanel pnTTNV;
	private JLabel lblIMG;
	private JLabel lblTenNV;
	private JLabel lblCV;
	private JLabel lblChucVu;
	private JLabel lblMa;
	private JLabel lblMaNV;
	private JLabel lblIconTTNV;
	private JPanel pnPosition;
	private JPanel pnTt;
	private JPanel pnTTThongKe;
	private JPanel pnTTXemLSBan;

	public Gui_QuanLy(NhanVien NV) {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		 nv = NV;
		getContentPane().add(conTrol_QuanLy());
	}
	
	public Component conTrol_QuanLy() {
		pnNV = new JPanel();
		pnNV.setBounds(0, 0, 1284, 710);
		pnNV.setLayout(null);

		JPanel pnHeader = new JPanel();
		pnHeader.setBackground(new Color(204, 0, 51));
		pnHeader.setBounds(0, 0, 1284, 46);
		pnNV.add(pnHeader);
		pnHeader.setLayout(null);

		pnMenu = new JPanel() {
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
				Color color1 = new Color(34, 11, 52);
				Color color2 = new Color(193, 30, 56);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		pnMenu.setBackground(new Color(51, 51, 51));
		pnMenu.setBounds(0, 46, 46, 664);
		pnNV.add(pnMenu);
		pnMenu.setLayout(null);

		lblExtend = new JLabel("");
		lblExtend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExtend.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\three_line.png"));
		lblExtend.setBounds(10, 7, 32, 32);
		pnHeader.add(lblExtend);

		JLabel lblBulkhead = new JLabel("");
		lblBulkhead.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		lblBulkhead.setBounds(46, 3, 3, 38);
		pnHeader.add(lblBulkhead);

		lblTitle = new JLabel("Quản Lý Nhân Viên");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(59, 7, 328, 32);
		pnHeader.add(lblTitle);

		pnQLNV = new JPanel();
		pnQLNV.setBackground(new Color(169, 169, 169));
		pnQLNV.setOpaque(false);
		pnQLNV.setBorder(null);
		pnQLNV.setBounds(0, 270, 200, 36);
		pnMenu.add(pnQLNV);
		pnQLNV.setLayout(null);

		lblQLNV = new JLabel("Quản Lý Nhân Viên");
		lblQLNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblQLNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblQLNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQLNV.setIconTextGap(12);
		lblQLNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQLNV.setForeground(Color.WHITE);
		lblQLNV.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\product.png"));
		lblQLNV.setBounds(7, 2, 32, 32);
		pnQLNV.add(lblQLNV);

		pnXemLSBan = new JPanel();
		pnXemLSBan.setOpaque(false);
		pnXemLSBan.setBorder(null);
		pnXemLSBan.setLayout(null);
		pnXemLSBan.setBackground(Color.DARK_GRAY);
		pnXemLSBan.setBounds(0, 322, 200, 36);
		pnMenu.add(pnXemLSBan);

		lblXemLSBan = new JLabel("Xem Lịch Sử Bán");
		lblXemLSBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblXemLSBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblXemLSBan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblXemLSBan.setIconTextGap(12);
		lblXemLSBan.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\order.png"));
		lblXemLSBan.setForeground(Color.WHITE);
		lblXemLSBan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblXemLSBan.setBounds(7, 2, 32, 32);
		pnXemLSBan.add(lblXemLSBan);

		pnThongKe = new JPanel();
		pnThongKe.setOpaque(false);
		pnThongKe.setBorder(null);
		pnThongKe.setLayout(null);
		pnThongKe.setBackground(Color.DARK_GRAY);
		pnThongKe.setBounds(0, 374, 200, 36);
		pnMenu.add(pnThongKe);

		lblThongKe = new JLabel("Thống Kê");
		lblThongKe.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThongKe.setIconTextGap(12);
		lblThongKe.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\statistics.png"));
		lblThongKe.setForeground(Color.WHITE);
		lblThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThongKe.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblThongKe.setBounds(7, 2, 32, 32);
		pnThongKe.add(lblThongKe);

		pnExit = new JPanel();
		pnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new GUI_DangNhap().setVisible(true);
			}
		});
		pnExit.setLayout(null);
		pnExit.setBackground(Color.BLUE);
		pnExit.setBounds(0, 617, 200, 36);
		pnMenu.add(pnExit);

		lblExit = new JLabel("Thoát.");
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExit.setIconTextGap(10);
		lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\exit.png"));
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExit.setAlignmentX(0.5f);
		lblExit.setBounds(8, 2, 190, 32);
		pnExit.add(lblExit);

		pnChayAnimation = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Paint Border
				g2.setColor(new Color(247, 148, 30));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(new Color(169, 0, 0));
				// Border set 2 Pix
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
				super.paintComponent(g);
			}
		};
		pnChayAnimation.setOpaque(false);
		pnChayAnimation.setLayout(null);
		pnChayAnimation.setBackground(new Color(169, 169, 169));
		pnChayAnimation.setBounds(0, 270, 46, 36);
		pnMenu.add(pnChayAnimation);

		pnTTNV = new JPanel();
		pnTTNV.setOpaque(false);
		pnTTNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		pnTTNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnTTNV.setBackground(new Color(51, 51, 51));
		pnTTNV.setBounds(0, 0, 0, 0);
		pnMenu.add(pnTTNV);
		pnTTNV.setLayout(null);

		lblIMG = new JLabel("");
		lblIMG.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblIMG.setIcon(new ImageIcon(
				new ImageIcon(nv.getHinhAnh()).getImage().getScaledInstance(143, 154, java.awt.Image.SCALE_SMOOTH)));
		lblIMG.setBounds(27, 0, 143, 154);
		pnTTNV.add(lblIMG);

		lblTenNV = new JLabel(nv.getTenNV());
		lblTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenNV.setForeground(Color.WHITE);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNV.setBounds(0, 156, 200, 23);
		pnTTNV.add(lblTenNV);

		lblCV = new JLabel("Chức Vụ:");
		lblCV.setForeground(Color.WHITE);
		lblCV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCV.setBounds(27, 195, 53, 14);
		pnTTNV.add(lblCV);

		lblChucVu = new JLabel(nv.getChucVu());
		lblChucVu.setForeground(Color.RED);
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChucVu.setBounds(82, 195, 120, 14);
		pnTTNV.add(lblChucVu);

		lblMa = new JLabel("Mã Nhân Viên:");
		lblMa.setForeground(Color.WHITE);
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMa.setBounds(27, 180, 78, 14);
		pnTTNV.add(lblMa);

		lblMaNV = new JLabel(nv.getMaNV());
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaNV.setForeground(Color.WHITE);
		lblMaNV.setBounds(107, 180, 83, 14);
		pnTTNV.add(lblMaNV);

		lblIconTTNV = new JLabel("");
		lblIconTTNV.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\avt.png"));
		lblIconTTNV.setBounds(7, 11, 32, 32);
		pnMenu.add(lblIconTTNV);

		pnPosition = new JPanel();
		pnPosition.setOpaque(false);
		pnPosition.setBounds(0, 46, 46, 664);
		pnNV.add(pnPosition);
		pnPosition.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				if (e.getX() > 200) {
					curtail();
				}
			}
		});

		pnTt = new JPanel();
		pnTt.setBorder(null);
		pnTt.setBounds(46, 46, 1238, 664);
		pnTt.setLayout(null);
		pnNV.add(pnTt);
		
		pnQLTTNV = new JPanel();
		pnQLTTNV.setBounds(0, 0, 1237, 664);
		pnQLTTNV.setLayout(null);
		pnQLTTNV.add(new Gui_QuanLy_QLNV().control_QLNV());
		pnTt.add(pnQLTTNV);
		
		pnTTXemLSBan = new JPanel();
		pnTTXemLSBan.setBounds(0, 0, 1237, 664);
		pnTTXemLSBan.setLayout(null);
		pnTTXemLSBan.setVisible(false);
		pnTTXemLSBan.add(new Gui_XemHoaDon(nv).controll_XemHoaDon());
		pnTt.add(pnTTXemLSBan);

		pnTTThongKe = new JPanel();
		pnTTThongKe.setBounds(0, 0, 1237, 664);
		pnTTThongKe.setLayout(null);
		pnTTThongKe.setVisible(false);
		pnTTThongKe.add(new GUI_ThongKe(nv).control_ThongKe());
		pnTt.add(pnTTThongKe);

		lblExtend.addMouseListener((MouseListener) this);
		
		pnTTNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, new FormThongTinNhanVien().conTrolThongTinNv(nv),
						"Thông Tin Nhân Viên", JOptionPane.DEFAULT_OPTION);
			}

		});
		pnTt.addMouseListener(this);
		pnQLNV.addMouseListener(this);
		pnXemLSBan.addMouseListener(this);
		pnThongKe.addMouseListener(this);
		pnChayAnimation.addMouseListener(this);
		pnMenu.addMouseListener(this);
		return pnNV;

	}
	
	public void Extend() {
		pnMenu.setVisible(true);
		if (pnMenu.getWidth() == 46) {

			lblIconTTNV.setBounds(7, 11, 0, 0);
			pnTTNV.setBounds(0, 11, 0, 0);
			Thread th = new Thread() {
				@Override
				public void run() {
					try {
						for (int i = 0; i <= 77; i++) {

							pnMenu.setBounds(0, 46, 46 + i * 2, 664);
							pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 46 + i * 2, 36);
							pnTTNV.setBounds(0, 11, 46 * i + 2, 218);
							pnPosition.setBounds(0, 46, 77 + i * 10, 664);
							if (pnMenu.getWidth() == 200) {
								pnTTNV.setBounds(0, 11, 200, 218);
								lblIconTTNV.setBounds(0, 0, 0, 0);

								pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 200, 36);
								pnQLNV.setBounds(pnQLNV.getX(), pnQLNV.getY(), 200, 36);
								lblQLNV.setBounds(7, 2, 186, 32);
								pnXemLSBan.setBounds(pnXemLSBan.getX(), pnXemLSBan.getY(), 200, 36);
								lblXemLSBan.setBounds(7, 2, 186, 32);
								
								pnThongKe.setBounds(pnThongKe.getX(), pnThongKe.getY(), 200, 36);
								lblThongKe.setBounds(7, 2, 186, 32);
							}
							Thread.sleep(2);
						}

					} catch (Exception e2) {
						e2.getMessage();
					}
				};
			};
			th.start();
		}

	}

	
	public void curtail() {
		pnMenu.setVisible(true);
		if (pnMenu.getWidth() == 200) {
			Thread th = new Thread() {
				@Override
				public void run() {
					try {
						for (int i = 0; i <= 77; i++) {
							pnMenu.setBounds(0, 46, 200 - i * 2, 664);
							pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 200 - i * 2, 36);
							pnPosition.setBounds(0, 46, 77 - i * 10, 664);
							if (pnMenu.getWidth() == 46) {
								lblIconTTNV.setBounds(7, 11, 32, 32);
								pnTTNV.setBounds(0, 0, 0, 0);

								pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 46, 36);

								pnQLNV.setBounds(0, 270, 46, 36);
								lblQLNV.setBounds(7, 2, 32, 32);

								pnXemLSBan.setBounds(0, 322, 46, 36);
								lblXemLSBan.setBounds(7, 2, 32, 32);

								pnThongKe.setBounds(0, 374, 200, 36);
								lblThongKe.setBounds(7, 2, 32, 32);
							}
							Thread.sleep(2);
						}

					} catch (Exception e2) {
						e2.getMessage();
					}
				};
			};
			th.start();

		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnXemLSBan)) {
			pnChayAnimation.setBounds(pnXemLSBan.getX(), pnXemLSBan.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnXemLSBan.setOpaque(false);
			pnQLTTNV.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnTTXemLSBan.setVisible(true);
			curtail();
			lblTitle.setText("Xem Lịch Sử Bán");
		} else if (e.getSource().equals(pnQLNV)) {
			pnChayAnimation.setBounds(pnQLNV.getX(), pnQLNV.getY(), pnChayAnimation.getWidth(), pnChayAnimation.getHeight());
			pnQLNV.setOpaque(false);
			pnTTXemLSBan.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnQLTTNV.setVisible(true);
			curtail();
			lblTitle.setText("Quản Lý Nhân Viên");
		} else if (e.getSource().equals(pnThongKe)) {
			pnChayAnimation.setBounds(pnThongKe.getX(), pnThongKe.getY(), pnChayAnimation.getWidth(), pnChayAnimation.getHeight());
			pnThongKe.setOpaque(false);
			pnQLTTNV.setVisible(false);
			pnTTXemLSBan.setVisible(false);
			pnTTThongKe.setVisible(true);
			curtail();
			lblTitle.setText("Thống Kê");

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
		if (e.getSource().equals(pnQLNV)) {
			if (pnChayAnimation.getBounds().getY() != pnQLNV.getBounds().getY()) {
				pnQLNV.setOpaque(true);
				pnQLNV.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnXemLSBan)) {
			if (pnChayAnimation.getBounds().getY() != pnXemLSBan.getBounds().getY()) {
				pnXemLSBan.setOpaque(true);
				pnXemLSBan.setBackground(new Color(169, 169, 169));
			}
			Extend();
		}else if (e.getSource().equals(pnThongKe)) {
			if (pnChayAnimation.getBounds().getY() != pnThongKe.getBounds().getY()) {
				pnThongKe.setOpaque(true);
				pnThongKe.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnMenu)) {
			Extend();
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(pnQLNV)) {
			pnQLNV.setOpaque(false);
			pnQLNV.setBackground(new Color(169, 0, 0));

		} else if (e.getSource().equals(pnXemLSBan)) {
			pnXemLSBan.setOpaque(false);
			pnXemLSBan.setBackground(new Color(169, 0, 0));

		} else if (e.getSource().equals(pnThongKe)) {
			pnThongKe.setOpaque(false);
			pnThongKe.setBackground(new Color(169, 0, 0));
		}
		
	}
	public static void main(String[] args) {
		new Gui_QuanLy(new NhanVien("NV2202", "Nguyễn Thanh Sơn", "0387866829", "img/AvtUser/NhanVien/NV2202.jpg",
				"thanhsonsmile2017@gmail.com", "Quản Lý")).setVisible(true);
	}
}