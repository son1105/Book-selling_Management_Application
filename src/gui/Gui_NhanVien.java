package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import dao.Dao_ThongBao;
import entity.NhanVien;
import entity.ThongBao;

public class Gui_NhanVien extends JFrame implements MouseListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnNV;
	private JPanel pnMenu;
	private JLabel lblExtend;
	private JLabel lblTitle;
	private JLabel lblThongBao;
	private NhanVien nv;
	private JLabel lblSoLuongTB;
	private JPanel pnQLSP;
	private JLabel lblQLSP;
	private JPanel pnQLDH;
	private JLabel lblQLDH;
	private JPanel pnNcc;
	private JLabel lblNcc;
	private JPanel pnLoaiSp;
	private JLabel lblLoaiSp;
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
	private JPanel pnPisition;
	private JPanel pnTt;
	private JPanel pnTTsp;
	private JPanel pnTTHD;
	private JPanel pnTTLoaiSp;
	private JPanel pnTTNcc;
	private JPanel pnTTThongKe;
	private JPanel pnThongBao;
	private JPanel pnCoordinatesThongBao;

	public Gui_NhanVien(NhanVien NV) {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		 nv = NV;

		
		getContentPane().add(conTrolNhanVien_QLSP());
	}
	
	public Component conTrolNhanVien_QLSP() {
		pnNV = new JPanel();
		pnNV.setBounds(0, 0, 1284, 710);
		pnNV.setLayout(null);

		conTrolThongBao();
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

		lblTitle = new JLabel("Quản Lý Sản Phẩm");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(59, 7, 328, 32);
		pnHeader.add(lblTitle);

		lblThongBao = new JLabel("Thông Báo");
		lblThongBao.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\notification.png"));
		lblThongBao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThongBao.setIconTextGap(-8);
		if (new Dao_ThongBao().getDsThongBao(nv.getMaNV()).size() > 0) {
			JPanel pnNoti = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// Paint Border
					g2.setColor(Color.white);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
					g2.setColor(new Color(218, 85, 82));
					// Border set 2 Pix
					g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 5, 5);
					super.paintComponent(g);
				}
			};
			pnNoti.setOpaque(false);
			FlowLayout fl_pnNoti = (FlowLayout) pnNoti.getLayout();
			fl_pnNoti.setVgap(0);
			fl_pnNoti.setHgap(0);
			pnNoti.setBackground(Color.WHITE);
			pnNoti.setBounds(1151, 7, 15, 15);
			pnHeader.add(pnNoti);

			lblSoLuongTB = new JLabel(new Dao_ThongBao().getDsThongBaoNoSee(nv.getMaNV()).size() + "");
			lblSoLuongTB.setForeground(Color.WHITE);
			pnNoti.add(lblSoLuongTB);
		}
		lblThongBao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThongBao.setForeground(Color.WHITE);
		lblThongBao.setBounds(1127, 7, 114, 32);
		pnHeader.add(lblThongBao);

		pnQLSP = new JPanel();
		pnQLSP.setBackground(new Color(169, 169, 169));
		pnQLSP.setOpaque(false);
		pnQLSP.setBorder(null);
		pnQLSP.setBounds(0, 270, 200, 36);
		pnMenu.add(pnQLSP);
		pnQLSP.setLayout(null);

		lblQLSP = new JLabel("Quản Lý Sản Phẩm");
		lblQLSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblQLSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblQLSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQLSP.setIconTextGap(12);
		lblQLSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQLSP.setForeground(Color.WHITE);
		lblQLSP.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\product.png"));
		lblQLSP.setBounds(7, 2, 32, 32);
		pnQLSP.add(lblQLSP);

		pnQLDH = new JPanel();
		pnQLDH.setOpaque(false);
		pnQLDH.setBorder(null);
		pnQLDH.setLayout(null);
		pnQLDH.setBackground(Color.DARK_GRAY);
		pnQLDH.setBounds(0, 322, 200, 36);
		pnMenu.add(pnQLDH);

		lblQLDH = new JLabel("Quản Lý Hoá Đơn");
		lblQLDH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblQLDH.setHorizontalAlignment(SwingConstants.LEFT);
		lblQLDH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQLDH.setIconTextGap(12);
		lblQLDH.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\order.png"));
		lblQLDH.setForeground(Color.WHITE);
		lblQLDH.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQLDH.setBounds(7, 2, 32, 32);
		pnQLDH.add(lblQLDH);

		pnNcc = new JPanel();
		pnNcc.setOpaque(false);
		pnNcc.setBorder(null);
		pnNcc.setLayout(null);
		pnNcc.setBackground(Color.DARK_GRAY);
		pnNcc.setBounds(0, 374, 200, 36);
		pnMenu.add(pnNcc);

		lblNcc = new JLabel("Các Nhà Cung Cấp");
		lblNcc.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblNcc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNcc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNcc.setIconTextGap(12);
		lblNcc.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\supplier.png"));
		lblNcc.setForeground(Color.WHITE);
		lblNcc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNcc.setAlignmentX(0.5f);
		lblNcc.setBounds(7, 2, 32, 32);
		pnNcc.add(lblNcc);

		pnLoaiSp = new JPanel();
		pnLoaiSp.setOpaque(false);
		pnLoaiSp.setBorder(null);
		pnLoaiSp.setLayout(null);
		pnLoaiSp.setBackground(Color.DARK_GRAY);
		pnLoaiSp.setBounds(0, 426, 200, 36);
		pnMenu.add(pnLoaiSp);

		lblLoaiSp = new JLabel("Loại Sản Phẩm");
		lblLoaiSp.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblLoaiSp.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLoaiSp.setIconTextGap(12);
		lblLoaiSp.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\categories.png"));
		lblLoaiSp.setForeground(Color.WHITE);
		lblLoaiSp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoaiSp.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblLoaiSp.setBounds(7, 2, 32, 32);
		pnLoaiSp.add(lblLoaiSp);

		pnThongKe = new JPanel();
		pnThongKe.setOpaque(false);
		pnThongKe.setBorder(null);
		pnThongKe.setLayout(null);
		pnThongKe.setBackground(Color.DARK_GRAY);
		pnThongKe.setBounds(0, 478, 200, 36);
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

		pnPisition = new JPanel();
		pnPisition.setOpaque(false);
		pnPisition.setBounds(0, 46, 46, 664);
		pnNV.add(pnPisition);
		pnPisition.addMouseMotionListener(new MouseMotionAdapter() {
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
		
		pnTTsp = new JPanel();
		pnTTsp.setBounds(0, 0, 1237, 664);
		pnTTsp.setLayout(null);
		pnTTsp.add(new Gui_NhanVien_QLSP(nv).QuanLySanPham());
		pnTt.add(pnTTsp);
		
		pnTTHD = new JPanel();
		pnTTHD.setBounds(0, 0, 1237, 664);
		pnTTHD.setLayout(null);
		pnTTHD.add(new Gui_NhanVien_QLHD(nv).QuanLySanPham());
		pnTt.add(pnTTHD);

		pnTTLoaiSp = new JPanel();
		pnTTLoaiSp.setBounds(0, 0, 1237, 664);
		pnTTLoaiSp.setLayout(null);
		pnTTLoaiSp.setVisible(false);
		pnTTLoaiSp.add(new Gui_QuanLyLoaiSanPham().controll_QuanLyLoaiSanPham());
		pnTt.add(pnTTLoaiSp);

		pnTTNcc = new JPanel();
		pnTTNcc.setBounds(0, 0, 1237, 664);
		pnTTNcc.setLayout(null);
		pnTTNcc.add(new Gui_QuanLyNhaCungCap().controll_QuanLyNhaCungCap());
		pnTTNcc.setVisible(false);
		pnTt.add(pnTTNcc);

		pnTTThongKe = new JPanel();
		pnTTThongKe.setBounds(0, 0, 1237, 664);
		pnTTThongKe.setLayout(null);
		pnTTThongKe.setVisible(false);
		pnTTThongKe.add(new GUI_ThongKe(nv).control_ThongKe());
		pnTt.add(pnTTThongKe);
		
		lblExtend.addMouseListener(this);
		lblThongBao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pnThongBao.setVisible(true);
				pnCoordinatesThongBao.setVisible(true);
			}
		});
		pnTTHD.setVisible(false);
		pnTTNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, new FormThongTinNhanVien().conTrolThongTinNv(nv),
						"Thông Tin Nhân Viên", JOptionPane.DEFAULT_OPTION);
			}

		});
		pnTt.addMouseListener(this);
		pnQLSP.addMouseListener(this);
		pnQLDH.addMouseListener(this);
		pnNcc.addMouseListener(this);
		pnLoaiSp.addMouseListener(this);
		pnThongKe.addMouseListener(this);
		pnChayAnimation.addMouseListener(this);
		pnMenu.addMouseListener(this);
		return pnNV;

	}
	
	public void conTrolThongBao() {
		pnThongBao = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Paint Border
				g2.setColor(Color.BLACK);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(getBackground());
				// Border set 2 Pix
				g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 11, 11);
				super.paintComponent(g);
			}
		};
		pnThongBao.setVisible(false);
		pnThongBao.setOpaque(false);
		pnThongBao.setBackground(Color.WHITE);
		pnThongBao.setBounds(914, 40, 360, 447);
		getContentPane().add(pnThongBao);
		pnThongBao.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitleThongBao = new JLabel("Thông Báo");
		lblTitleThongBao.setPreferredSize(new Dimension(333, 20));
		lblTitleThongBao.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnThongBao.add(lblTitleThongBao);

		for (ThongBao i : new Dao_ThongBao().getDsThongBao("NV2201")) {
			JPanel pnTbChiTiet = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// Paint Border
					g2.setColor(Color.LIGHT_GRAY);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
					g2.setColor(getBackground());
					// Border set 2 Pix
					g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 13, 13);
					super.paintComponent(g);
				}
			};
			pnTbChiTiet.setOpaque(false);
			pnTbChiTiet.setPreferredSize(new Dimension(333, 55));
			pnTbChiTiet.setBounds(10, 36, 333, 55);
			pnThongBao.add(pnTbChiTiet);
			pnTbChiTiet.setLayout(null);

			if (!i.isTrangThai()) {
				pnTbChiTiet.setBackground(Color.WHITE);
			}

			String x = "<html><p style='text-indent: 15px; width: 239px'>" + i.getChiTiet() + "</p></html>";
			JLabel lblChiTiet = new JLabel(x);
			lblChiTiet.setHorizontalAlignment(SwingConstants.LEFT);
			lblChiTiet.setVerticalAlignment(SwingConstants.TOP);
			lblChiTiet.setBounds(10, 5, 318, 32);
			pnTbChiTiet.add(lblChiTiet);

			JLabel lblDate = new JLabel(i.getNgayThongBao() + "");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDate.setBounds(268, 40, 60, 14);
			pnTbChiTiet.add(lblDate);

			pnTbChiTiet.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(null, x, "Chi Tiết Thông Báo.", JOptionPane.DEFAULT_OPTION);
					new Dao_ThongBao().updateTrangThaiThongBao(i.getMaTB());
					pnTbChiTiet.setBackground(Color.WHITE);
					lblSoLuongTB.setText(new Dao_ThongBao().getDsThongBaoNoSee(nv.getMaNV()).size() + "");
				}

			});
		}
		pnCoordinatesThongBao = new JPanel();
		pnCoordinatesThongBao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnThongBao.setVisible(false);
				pnCoordinatesThongBao.setVisible(false);
				
			}
		});
		pnCoordinatesThongBao.setVisible(false);
		pnCoordinatesThongBao.setOpaque(false);
		pnCoordinatesThongBao.setBounds(0, 40, 1284, 670);
		getContentPane().add(pnCoordinatesThongBao);
		
		pnThongBao.addMouseListener(this);
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
							pnPisition.setBounds(0, 46, 77 + i * 10, 664);
							if (pnMenu.getWidth() == 200) {
								pnTTNV.setBounds(0, 11, 200, 218);
								lblIconTTNV.setBounds(0, 0, 0, 0);

								pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 200, 36);
								pnQLSP.setBounds(pnQLSP.getX(), pnQLSP.getY(), 200, 36);
								lblQLSP.setBounds(7, 2, 186, 32);
								pnQLDH.setBounds(pnQLDH.getX(), pnQLDH.getY(), 200, 36);
								lblQLDH.setBounds(7, 2, 186, 32);
								pnNcc.setBounds(pnNcc.getX(), pnNcc.getY(), 200, 36);
								lblNcc.setBounds(7, 2, 186, 32);
								pnLoaiSp.setBounds(pnLoaiSp.getX(), pnLoaiSp.getY(), 200, 36);
								lblLoaiSp.setBounds(7, 2, 186, 32);

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
							pnPisition.setBounds(0, 46, 77 - i * 10, 664);
							if (pnMenu.getWidth() == 46) {
								lblIconTTNV.setBounds(7, 11, 32, 32);
								pnTTNV.setBounds(0, 0, 0, 0);

								pnChayAnimation.setBounds(pnChayAnimation.getX(), pnChayAnimation.getY(), 46, 36);

								pnQLSP.setBounds(0, 270, 46, 36);
								lblQLSP.setBounds(7, 2, 32, 32);

								pnQLDH.setBounds(0, 322, 46, 36);
								lblQLDH.setBounds(7, 2, 32, 32);

								pnNcc.setBounds(0, 374, 46, 36);
								lblNcc.setBounds(7, 2, 32, 32);

								pnLoaiSp.setBounds(0, 426, 46, 36);
								lblLoaiSp.setBounds(7, 2, 32, 32);

								pnThongKe.setBounds(0, 478, 46, 36);
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
		if (e.getSource().equals(pnQLDH)) {
			pnChayAnimation.setBounds(pnQLDH.getX(), pnQLDH.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnQLDH.setOpaque(false);
			pnTTsp.setVisible(false);
			pnTTLoaiSp.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnTTNcc.setVisible(false);
			pnTTHD.setVisible(true);
			curtail();
			lblTitle.setText("Quản Lý Hoá Đơn");
		} else if (e.getSource().equals(pnNcc)) {

			pnChayAnimation.setBounds(pnNcc.getX(), pnNcc.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnNcc.setOpaque(false);
			pnTTsp.setVisible(false);
			pnTTHD.setVisible(false);
			pnTTLoaiSp.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnTTNcc.setVisible(true);
			curtail();
			lblTitle.setText("Quản Lý Nhà Cung Cấp");
		} else if (e.getSource().equals(pnLoaiSp)) {

			pnChayAnimation.setBounds(pnLoaiSp.getX(), pnLoaiSp.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnLoaiSp.setOpaque(false);

			pnTTsp.setVisible(false);
			pnTTNcc.setVisible(false);
			pnTTHD.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnTTLoaiSp.setVisible(true);
			curtail();
			lblTitle.setText("Quản Lý Loại Sản Phẩm");
		}

		else if (e.getSource().equals(pnQLSP)) {

			pnChayAnimation.setBounds(pnQLSP.getX(), pnQLSP.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnQLSP.setOpaque(false);

			pnTTNcc.setVisible(false);
			pnTTHD.setVisible(false);
			pnTTThongKe.setVisible(false);
			pnTTLoaiSp.setVisible(false);
			pnTTsp.setVisible(true);
			curtail();
			lblTitle.setText("Quản Lý Sản Phẩm");

		} else if (e.getSource().equals(pnThongKe)) {

			pnChayAnimation.setBounds(pnThongKe.getX(), pnThongKe.getY(), pnChayAnimation.getWidth(),
					pnChayAnimation.getHeight());
			pnThongKe.setOpaque(false);

			pnTTsp.setVisible(false);
			pnTTHD.setVisible(false);
			pnTTNcc.setVisible(false);
			pnTTLoaiSp.setVisible(false);
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
		if (e.getSource().equals(pnQLSP)) {
			if (pnChayAnimation.getBounds().getY() != pnQLSP.getBounds().getY()) {
				pnQLSP.setOpaque(true);
				pnQLSP.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnQLDH)) {
			if (pnChayAnimation.getBounds().getY() != pnQLDH.getBounds().getY()) {
				pnQLDH.setOpaque(true);
				pnQLDH.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnNcc)) {

			if (pnChayAnimation.getBounds().getY() != pnNcc.getBounds().getY()) {
				pnNcc.setOpaque(true);
				pnNcc.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnLoaiSp)) {
			if (pnChayAnimation.getBounds().getY() != pnLoaiSp.getBounds().getY()) {
				pnLoaiSp.setOpaque(true);
				pnLoaiSp.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnThongKe)) {
			if (pnChayAnimation.getBounds().getY() != pnThongKe.getBounds().getY()) {
				pnThongKe.setOpaque(true);
				pnThongKe.setBackground(new Color(169, 169, 169));
			}
			Extend();
		} else if (e.getSource().equals(pnMenu)) {
			Extend();
		} else  if (!e.getSource().equals(pnThongBao)) {
			pnThongBao.setVisible(false);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(pnQLSP)) {
			pnQLSP.setOpaque(false);
			pnQLSP.setBackground(new Color(169, 0, 0));

		} else if (e.getSource().equals(pnQLDH)) {
			pnQLDH.setOpaque(false);
			pnQLDH.setBackground(new Color(169, 0, 0));

		} else if (e.getSource().equals(pnNcc)) {
			pnNcc.setOpaque(false);
			pnNcc.setBackground(new Color(169, 0, 0));

		} else if (e.getSource().equals(pnLoaiSp)) {
			pnLoaiSp.setOpaque(false);
			pnLoaiSp.setBackground(new Color(169, 0, 0));
		} else if (e.getSource().equals(pnThongKe)) {
			pnThongKe.setOpaque(false);
			pnThongKe.setBackground(new Color(169, 0, 0));
		}
		
	}
	public static void main(String[] args) {
		new Gui_NhanVien(new NhanVien("NV2201", "Phạm Thanh Sơn", "0346676956", "img/AvtUser/NhanVien/NV2201.jpg",
				"sonpham28052002@gmail.com", "Nhân Viên")).setVisible(true);
	}
}
