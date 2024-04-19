package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_DiaChi;
import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import dao.Dao_SanPham;
import dao.Dao_TaiKhoan;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.QuanHuyen;
import entity.SanPham;
import entity.TinhThanh;
import entity.XaPhuong;

public class Gui_KhachHang extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private JTextField txtSearch;
	private Dao_SanPham dao_SanPham;
	private KhachHang khachHang;
	private JLabel lblCart;
	private static String[] gioHang;
	private JLabel lblSoLuongSanPham;
	private JLabel lblTongTien;
	private List<SanPham> dsItemCart_Buy = new ArrayList<>();
	private static List<SanPham> dsItemCart = new ArrayList<>();
	private List<ChiTietHoaDon> dsCThd = new ArrayList<>();
	private JPanel pnDSSanPham;
	private float tongTien;
	private JLabel lblIndividual;
	protected int indexQuanHuyen;
	private JPanel pnSanPham_ButViet;
	private JPanel pnSanPham_DDHT;
	private JPanel pnSanPham_TapVo;
	private JPanel pnSanPham_VPP;
	private JPanel pnSanPham_Manga;
	private JPanel pnSanPham_VanHoc;
	private JPanel pnSanPham_KT;
	private JPanel pnSanPham_TamLy;
	private static JLabel lblSoLuong_SP;

	
	public Gui_KhachHang(KhachHang kh) {
		khachHang = kh;
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		dao_SanPham = new Dao_SanPham();

		JPanel pnQc = new JPanel();
		pnQc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnQc.setBounds(0, 0, 1284, 60);
		getContentPane().add(pnQc);
		pnQc.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(42, 2, 1200, 56);
		pnQc.add(panel);
		panel.setLayout(null);

		JLabel lbImgHeader = new JLabel("");
		lbImgHeader.setBounds(0, 0, 1200, 56);
		createIconImage(lbImgHeader, 1200, 56, "img\\ImgSp\\header.jpg");
		panel.add(lbImgHeader);

		JPanel pnMenu = new JPanel();
		pnMenu.setBackground(new Color(255, 69, 0));
		pnMenu.setBounds(0, 60, 1284, 60);
		getContentPane().add(pnMenu);
		pnMenu.setLayout(null);

		JLabel lblLogo = new JLabel("");
		createIconImage(lblLogo, 150, 150, "img/IMG_LOGIN_SignIn/logo.png");
		lblLogo.setBounds(53, 3, 150, 54);
		pnMenu.add(lblLogo);

		JPanel pnSearch = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnSearch.setOpaque(false);
		pnSearch.setBounds(412, 13, 663, 30);
		pnMenu.add(pnSearch);
		pnSearch.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setForeground(Color.LIGHT_GRAY);
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSearch.requestFocus();
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().trim().equals("")
						&& txtSearch.getText().trim().equals("Nhập Nội Dung Cần Tìm...")) {
					txtSearch.setText("Nhập Nội Dung Cần Tìm...");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().trim().equals("")
						|| txtSearch.getText().trim().equals("Nhập Nội Dung Cần Tìm...")) {
					txtSearch.setText("");
					txtSearch.setForeground(Color.BLACK);
					txtSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
				}
			}
		});
		txtSearch.requestFocus();
		txtSearch.setText("Nhập Nội Dung Cần Tìm...");
		txtSearch.setBorder(null);
		txtSearch.setBounds(148, 1, 477, 28);
		pnSearch.add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblIconSearch = new JLabel("");
		lblIconSearch.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Search_black.png"));
		lblIconSearch.setBounds(635, 1, 28, 28);
		pnSearch.add(lblIconSearch);

		JLabel lblIcon = new JLabel("New label");
		lblIcon.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bottom_down_Black.png"));
		lblIcon.setBounds(128, 8, 14, 14);
		pnSearch.add(lblIcon);

		JPanel pnTieuChi = new JPanel();
		pnTieuChi.setBackground(Color.WHITE);
		pnTieuChi.setBounds(5, 1, 128, 28);
		pnSearch.add(pnTieuChi);
		pnTieuChi.setLayout(null);

		JLabel lblTieuChi = new JLabel("Theo Tên Sản Phẩm");
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTieuChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuChi.setBounds(0, 1, 128, 28);
		pnTieuChi.add(lblTieuChi);

		JLabel lblHome = new JLabel("");
		lblHome.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\home page.png"));
		lblHome.setBounds(1096, 13, 35, 35);
		pnMenu.add(lblHome);

		lblIndividual = new JLabel("");
		lblIndividual.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\avt.png"));
		lblIndividual.setBounds(1141, 13, 35, 35);
		pnMenu.add(lblIndividual);

		lblCart = new JLabel("");
		lblCart.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\cart.png"));
		lblCart.setBounds(1186, 13, 35, 35);
		pnMenu.add(lblCart);

		JPanel pnSL_SanPham = new JPanel() {
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
		pnSL_SanPham.setOpaque(false);
		FlowLayout fl_pnSL_SanPham = (FlowLayout) pnSL_SanPham.getLayout();
		fl_pnSL_SanPham.setVgap(3);
		fl_pnSL_SanPham.setHgap(0);
		pnSL_SanPham.setBounds(1212, 3, 30, 20);
		pnMenu.add(pnSL_SanPham);

		lblSoLuong_SP = new JLabel("");
		lblSoLuong_SP.setText("0");
		lblSoLuong_SP.setForeground(Color.WHITE);
		lblSoLuong_SP.setFont(new Font("Tahoma", Font.BOLD, 13));

		if (khachHang.getGioHang() != null && !khachHang.getGioHang().trim().equals("")) {
			gioHang = khachHang.getGioHang().split(" ");
			for (int i = 0; i < gioHang.length; i++) {
				dsItemCart.add(dao_SanPham.timKiemSanPham(gioHang[i]));
			}
			lblSoLuong_SP.setText(gioHang.length + "");
		}
		else {
			gioHang = "".split(" ");
		}
		pnSL_SanPham.add(lblSoLuong_SP);
		lblIndividual.addMouseListener(this);
		lblCart.addMouseListener(this);
		conTrolSanPham();

	}

	
	public Component conTrolSanPham() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 120, 1284, 591);
		getContentPane().add(scrollPane);

		JPanel panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		WrapLayout wl_panel_3 = new WrapLayout(FlowLayout.CENTER, 10, 15);
		wl_panel_3.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(wl_panel_3);

		JPanel pnMucSanPham = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnMucSanPham.setPreferredSize(new Dimension(1245, 80));
		FlowLayout fl_pnMucSanPham = (FlowLayout) pnMucSanPham.getLayout();
		fl_pnMucSanPham.setVgap(2);
		fl_pnMucSanPham.setHgap(50);
		pnMucSanPham.setOpaque(false);
		pnMucSanPham.setBounds(157, 5, 950, 80);
		panel_3.add(pnMucSanPham);

		JPanel pnSale = new JPanel();
		pnSale.setOpaque(false);
		pnSale.setPreferredSize(new Dimension(100, 76));
		pnMucSanPham.add(pnSale);
		pnSale.setLayout(null);

		JLabel lblIcon_Sale = new JLabel("");
		createIconImage(lblIcon_Sale, 60, 60, "img\\icon_Danh_Muc\\Icon_FlashSale_Hot.png");
		lblIcon_Sale.setBounds(20, 0, 60, 60);
		pnSale.add(lblIcon_Sale);

		JLabel lblTitle_Sale = new JLabel("Sale");
		lblTitle_Sale.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_Sale.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_Sale.setBounds(0, 62, 100, 14);
		pnSale.add(lblTitle_Sale);

		JPanel pnKinhte = new JPanel();
		pnKinhte.setOpaque(false);

		pnKinhte.setLayout(null);
		pnKinhte.setPreferredSize(new Dimension(100, 76));
		pnMucSanPham.add(pnKinhte);

		JLabel lblIcon_KinhTe = new JLabel("");
		createIconImage(lblIcon_KinhTe, 60, 60, "img\\icon_Danh_Muc\\Icon_KinhTe_Hot.png");
		lblIcon_KinhTe.setBounds(20, 0, 60, 60);
		pnKinhte.add(lblIcon_KinhTe);

		JLabel lblTitle_KinhTe = new JLabel("Kinh Tế");
		lblTitle_KinhTe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_KinhTe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_KinhTe.setBounds(0, 62, 100, 14);
		pnKinhte.add(lblTitle_KinhTe);

		JPanel pnManga = new JPanel();
		pnManga.setOpaque(false);
		pnManga.setLayout(null);
		pnManga.setPreferredSize(new Dimension(100, 76));
		pnManga.setOpaque(false);
		pnMucSanPham.add(pnManga);

		JLabel lblIcon_Manga = new JLabel("");
		createIconImage(lblIcon_Manga, 60, 60, "img\\icon_Danh_Muc\\Icon_MangaCommic.png");
		lblIcon_Manga.setBounds(20, 0, 60, 60);
		pnManga.add(lblIcon_Manga);

		JLabel lblTitle_Manga = new JLabel("Manga");
		lblTitle_Manga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_Manga.setBounds(0, 62, 100, 14);
		pnManga.add(lblTitle_Manga);

		JPanel pnThieuNhi = new JPanel();
		pnThieuNhi.setOpaque(false);
		pnThieuNhi.setLayout(null);
		pnThieuNhi.setPreferredSize(new Dimension(100, 76));
		pnThieuNhi.setOpaque(false);
		pnMucSanPham.add(pnThieuNhi);

		JLabel lblIcon_ThieuNhi = new JLabel("");
		createIconImage(lblIcon_ThieuNhi, 60, 60, "img\\icon_Danh_Muc\\Icon_ThieuNhi_Moi.png");
		lblIcon_ThieuNhi.setBounds(20, 0, 60, 60);
		pnThieuNhi.add(lblIcon_ThieuNhi);

		JLabel lblTitle_ThieuNhi = new JLabel("Thiếu Nhi");
		lblTitle_ThieuNhi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_ThieuNhi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_ThieuNhi.setBounds(0, 62, 100, 14);
		pnThieuNhi.add(lblTitle_ThieuNhi);

		JPanel pnXuHuong = new JPanel();
		pnXuHuong.setOpaque(false);
		pnXuHuong.setLayout(null);
		pnXuHuong.setPreferredSize(new Dimension(100, 76));
		pnXuHuong.setOpaque(false);
		pnMucSanPham.add(pnXuHuong);

		JLabel lblIcon_XuHuong = new JLabel("");
		createIconImage(lblIcon_XuHuong, 60, 60, "img\\icon_Danh_Muc\\Icon_Trending_Moi.png");
		lblIcon_XuHuong.setBounds(20, 0, 60, 60);
		pnXuHuong.add(lblIcon_XuHuong);

		JLabel lblTitle_XuHuong = new JLabel("Xu Hướng");
		lblTitle_XuHuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_XuHuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_XuHuong.setBounds(0, 62, 100, 14);
		pnXuHuong.add(lblTitle_XuHuong);

		JPanel pnVPP = new JPanel();
		pnVPP.setOpaque(false);
		pnVPP.setLayout(null);
		pnVPP.setPreferredSize(new Dimension(100, 76));
		pnVPP.setOpaque(false);
		pnMucSanPham.add(pnVPP);

		JLabel lblIcon_VPP_DDHT = new JLabel("");
		createIconImage(lblIcon_VPP_DDHT, 60, 60, "img\\icon_Danh_Muc\\Icon_VanPhongPham.png");
		lblIcon_VPP_DDHT.setBounds(20, 0, 60, 60);
		pnVPP.add(lblIcon_VPP_DDHT);

		JLabel lblTitle_VPP_DDHT = new JLabel("VPP-DDHT");
		lblTitle_VPP_DDHT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_VPP_DDHT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle_VPP_DDHT.setBounds(0, 62, 100, 14);
		pnVPP.add(lblTitle_VPP_DDHT);

		JPanel pnDanhMuc = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2.dispose();
				super.paint(g);
			}
		};
		pnDanhMuc.setOpaque(false);
		pnDanhMuc.setPreferredSize(new Dimension(1245, 225));
		panel_3.add(pnDanhMuc);
		pnDanhMuc.setLayout(null);

		JPanel pnDanhMuc_Title = new JPanel();
		pnDanhMuc_Title.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		pnDanhMuc_Title.setBackground(Color.WHITE);
		FlowLayout fl_pnDanhMuc_Title = (FlowLayout) pnDanhMuc_Title.getLayout();
		fl_pnDanhMuc_Title.setHgap(0);
		fl_pnDanhMuc_Title.setAlignment(FlowLayout.LEFT);
		fl_pnDanhMuc_Title.setVgap(3);
		pnDanhMuc_Title.setBounds(10, 0, 1225, 35);
		pnDanhMuc.add(pnDanhMuc_Title);

		JLabel lblDanhMuc_Title_Icon = new JLabel("Danh Mục Sản Phẩm");
		lblDanhMuc_Title_Icon.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\filter_red.png"));
		lblDanhMuc_Title_Icon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblDanhMuc_Title_Icon.setPreferredSize(new Dimension(250, 30));
		pnDanhMuc_Title.add(lblDanhMuc_Title_Icon);

		JPanel pnDanhMuc_SanPham = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnDanhMuc_SanPham.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnDanhMuc_SanPham.setBackground(Color.WHITE);
		pnDanhMuc_SanPham.setBounds(0, 35, 1245, 190);
		pnDanhMuc.add(pnDanhMuc_SanPham);

		pnSanPham_ButViet = new JPanel();
		pnSanPham_ButViet.setOpaque(false);
		pnSanPham_ButViet.setPreferredSize(new Dimension(150, 180));
		pnDanhMuc_SanPham.add(pnSanPham_ButViet);
		pnSanPham_ButViet.setLayout(null);

		JLabel lblImgLoaiSP = new JLabel("");
		createIconImage(lblImgLoaiSP, 140, 140, "img\\SanPham\\VanPhongPham\\But-Viet\\ButLongVietBangSTABILODo.jpg");
		lblImgLoaiSP.setBounds(5, 5, 140, 140);
		pnSanPham_ButViet.add(lblImgLoaiSP);

		JLabel lblTenLoai = new JLabel("Bút - Viết");
		lblTenLoai.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai.setBounds(5, 151, 140, 14);
		pnSanPham_ButViet.add(lblTenLoai);

		pnSanPham_DDHT = new JPanel();
		pnSanPham_DDHT.setLayout(null);
		pnSanPham_DDHT.setPreferredSize(new Dimension(150, 180));
		pnSanPham_DDHT.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_DDHT);

		JLabel lblImgLoaiSP_DDHT = new JLabel("");
		createIconImage(lblImgLoaiSP_DDHT, 140, 140,
				"img\\SanPham\\VanPhongPham\\DoDungHocTap\\BaloHocSinhSAKOS_Xanh.jpg");
		lblImgLoaiSP_DDHT.setBounds(5, 5, 140, 140);
		pnSanPham_DDHT.add(lblImgLoaiSP_DDHT);

		JLabel lblTenLoai_DDHT = new JLabel("Đồ Dùng Học Tập");
		lblTenLoai_DDHT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_DDHT.setBounds(5, 151, 140, 14);
		pnSanPham_DDHT.add(lblTenLoai_DDHT);

		pnSanPham_TapVo = new JPanel();
		pnSanPham_TapVo.setLayout(null);
		pnSanPham_TapVo.setPreferredSize(new Dimension(150, 180));
		pnSanPham_TapVo.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_TapVo);

		JLabel lblImgLoaiSP_TapVo = new JLabel("");
		createIconImage(lblImgLoaiSP_TapVo, 140, 140, "img\\SanPham\\VanPhongPham\\TapVo\\VoHocSinh_B5S.jpg");
		lblImgLoaiSP_TapVo.setBounds(5, 5, 140, 140);
		pnSanPham_TapVo.add(lblImgLoaiSP_TapVo);

		JLabel lblTenLoai_2 = new JLabel("Tập Vở");
		lblTenLoai_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_2.setBounds(5, 151, 140, 14);
		pnSanPham_TapVo.add(lblTenLoai_2);

		pnSanPham_VPP = new JPanel();
		pnSanPham_VPP.setLayout(null);
		pnSanPham_VPP.setPreferredSize(new Dimension(150, 180));
		pnSanPham_VPP.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_VPP);

		JLabel lblImgLoaiSP_VPP = new JLabel("");
		createIconImage(lblImgLoaiSP_VPP, 140, 140,
				"img\\SanPham\\VanPhongPham\\DoDungVanPhong\\GiayPhotoDoubleAA4.jpg");
		lblImgLoaiSP_VPP.setBounds(5, 5, 140, 140);
		pnSanPham_VPP.add(lblImgLoaiSP_VPP);

		JLabel lblTenLoai_VPP = new JLabel("Văn Phòng Phẩm");
		lblTenLoai_VPP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_VPP.setBounds(5, 151, 140, 14);
		pnSanPham_VPP.add(lblTenLoai_VPP);

		pnSanPham_Manga = new JPanel();
		pnSanPham_Manga.setLayout(null);
		pnSanPham_Manga.setPreferredSize(new Dimension(150, 180));
		pnSanPham_Manga.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_Manga);

		JLabel lblImgLoaiSP_Manga = new JLabel("");
		createIconImage(lblImgLoaiSP_Manga, 140, 140, "img\\SanPham\\Sach\\Manga\\SpyXFamilyTap4.jpg");
		lblImgLoaiSP_Manga.setBounds(5, 5, 140, 140);
		pnSanPham_Manga.add(lblImgLoaiSP_Manga);

		JLabel lblTenLoai_Manga = new JLabel("Manga");
		lblTenLoai_Manga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_Manga.setBounds(5, 151, 140, 14);
		pnSanPham_Manga.add(lblTenLoai_Manga);

		pnSanPham_KT = new JPanel();
		pnSanPham_KT.setLayout(null);
		pnSanPham_KT.setPreferredSize(new Dimension(150, 180));
		pnSanPham_KT.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_KT);

		JLabel lblImgLoaiSP_KT = new JLabel("");
		createIconImage(lblImgLoaiSP_KT, 140, 140, "img\\SanPham\\Sach\\SachKinhTe\\BanLeThongMinh.jpg");
		lblImgLoaiSP_KT.setBounds(5, 5, 140, 140);
		pnSanPham_KT.add(lblImgLoaiSP_KT);

		JLabel lblTenLoai_KT = new JLabel("Sách Kinh Tế");
		lblTenLoai_KT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_KT.setBounds(5, 151, 140, 14);
		pnSanPham_KT.add(lblTenLoai_KT);

		pnSanPham_VanHoc = new JPanel();
		pnSanPham_VanHoc.setLayout(null);
		pnSanPham_VanHoc.setPreferredSize(new Dimension(150, 180));
		pnSanPham_VanHoc.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_VanHoc);

		JLabel lblImgLoaiSP_VanHoc = new JLabel("");
		createIconImage(lblImgLoaiSP_VanHoc, 140, 140, "img\\SanPham\\Sach\\SachVanHoc\\LamDi(TaiBan).jpg");
		lblImgLoaiSP_VanHoc.setBounds(5, 5, 140, 140);
		pnSanPham_VanHoc.add(lblImgLoaiSP_VanHoc);

		JLabel lblTenLoai_VanHoc = new JLabel("Sách Văn Học");
		lblTenLoai_VanHoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_VanHoc.setBounds(5, 151, 140, 14);
		pnSanPham_VanHoc.add(lblTenLoai_VanHoc);

		pnSanPham_TamLy = new JPanel();
		pnSanPham_TamLy.setLayout(null);
		pnSanPham_TamLy.setPreferredSize(new Dimension(150, 180));
		pnSanPham_TamLy.setOpaque(false);
		pnDanhMuc_SanPham.add(pnSanPham_TamLy);

		JLabel lblImgLoaiSP_TamLy = new JLabel("");
		createIconImage(lblImgLoaiSP_TamLy, 140, 140, "img\\SanPham\\Sach\\SachTamLy\\ThienTaiKeDien.jpg");
		lblImgLoaiSP_TamLy.setBounds(5, 5, 140, 140);
		pnSanPham_TamLy.add(lblImgLoaiSP_TamLy);

		JLabel lblTenLoai_TamLy = new JLabel("Sách Tâm Lý");
		lblTenLoai_TamLy.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenLoai_TamLy.setBounds(5, 151, 140, 14);
		pnSanPham_TamLy.add(lblTenLoai_TamLy);

		JPanel pnSanPham_Sale = new JPanel();
		pnSanPham_Sale.setPreferredSize(new Dimension(1245, 280));
		pnSanPham_Sale.setBackground(Color.WHITE);
		pnSanPham_Sale.setBounds(10, 400, 1245, 280);
		panel_3.add(pnSanPham_Sale);
		pnSanPham_Sale.setLayout(null);

		JPanel pnSanPham_Sale_Title = new JPanel();
		pnSanPham_Sale_Title.setBackground(new Color(255, 165, 0));
		pnSanPham_Sale_Title.setBounds(0, 0, 1245, 40);
		pnSanPham_Sale.add(pnSanPham_Sale_Title);
		pnSanPham_Sale_Title.setLayout(null);

		JLabel lblIconTitle_Sale = new JLabel("Flash Sale");
		lblIconTitle_Sale.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		createIconImage(lblIconTitle_Sale, 30, 30, "img\\icon_Danh_Muc\\Icon_FlashSale_Tron.png");
		lblIconTitle_Sale.setBounds(10, 5, 228, 30);
		pnSanPham_Sale_Title.add(lblIconTitle_Sale);

		JLabel lblSale_XemThem = new JLabel("Xem Thêm");
		lblSale_XemThem.setForeground(new Color(255, 69, 0));
		lblSale_XemThem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSale_XemThem.setBounds(1170, 16, 75, 14);
		pnSanPham_Sale_Title.add(lblSale_XemThem);
		lblSale_XemThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				conExtendSanPham(dao_SanPham.getDsSanPhamGiamGia());
			}
		});

		JPanel pnItem_SanPham_Sale = new JPanel();
		pnItem_SanPham_Sale.setBackground(Color.WHITE);
		pnItem_SanPham_Sale.setBounds(0, 40, 1245, 240);
		pnItem_SanPham_Sale.setLayout(new WrapLayout(FlowLayout.LEFT, 7, 5));
		pnSanPham_Sale.add(pnItem_SanPham_Sale);

		JPanel pnSanPham_XuHuong = new JPanel();
		pnSanPham_XuHuong.setPreferredSize(new Dimension(1245, 280));
		pnSanPham_XuHuong.setLayout(null);
		pnSanPham_XuHuong.setBackground(Color.WHITE);
		pnSanPham_XuHuong.setBounds(10, 400, 1245, 280);
		panel_3.add(pnSanPham_XuHuong);

		JPanel pnSanPham_XuHuong_Title = new JPanel();
		pnSanPham_XuHuong_Title.setLayout(null);
		pnSanPham_XuHuong_Title.setBackground(new Color(252, 221, 239));
		pnSanPham_XuHuong_Title.setBounds(0, 0, 1245, 40);
		pnSanPham_XuHuong.add(pnSanPham_XuHuong_Title);

		JLabel lblIconTitle_XuHuong = new JLabel("Xu Hướng Mua Sắm");
		lblIconTitle_XuHuong.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		createIconImage(lblIconTitle_XuHuong, 30, 30, "img\\icon_Danh_Muc\\ico_dealhot_tron.png");
		lblIconTitle_XuHuong.setBounds(10, 5, 298, 30);
		pnSanPham_XuHuong_Title.add(lblIconTitle_XuHuong);

		JLabel lblXuHuong_XemThem = new JLabel("Xem Thêm");
		lblXuHuong_XemThem.setForeground(new Color(255, 69, 0));
		lblXuHuong_XemThem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblXuHuong_XemThem.setBounds(1170, 16, 75, 14);
		pnSanPham_XuHuong_Title.add(lblXuHuong_XemThem);

		lblXuHuong_XemThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<SanPham> ds = new ArrayList<>();
				for (String i : dao_SanPham.getDsMaSPMuaNhieu()) {
					ds.add(dao_SanPham.timKiemSanPham(i));
				}
				conExtendSanPham(ds);
			}
		});

		JPanel pnItem_SanPham_XuHuong = new JPanel();
		pnItem_SanPham_XuHuong.setBackground(Color.WHITE);
		pnItem_SanPham_XuHuong.setBounds(0, 40, 1245, 240);
		pnSanPham_XuHuong.add(pnItem_SanPham_XuHuong);
		pnItem_SanPham_XuHuong.setLayout(new WrapLayout(0, 7, 5));

		JPanel pnSanPham_New = new JPanel();
		pnSanPham_New.setLayout(null);
		pnSanPham_New.setPreferredSize(new Dimension(1245, 280));
		pnSanPham_New.setBackground(Color.WHITE);
		panel_3.add(pnSanPham_New);

		JPanel pnSanPham_New_Title = new JPanel();
		pnSanPham_New_Title.setLayout(null);
		pnSanPham_New_Title.setBackground(new Color(250, 146, 254));
		pnSanPham_New_Title.setBounds(0, 0, 1245, 40);
		pnSanPham_New.add(pnSanPham_New_Title);

		JLabel lblIconTitle_New = new JLabel("New");

		lblIconTitle_New.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		createIconImage(lblIconTitle_New, 35, 35, "img\\icon_Danh_Muc\\icons_new.png");
		lblIconTitle_New.setBounds(10, 5, 298, 30);
		pnSanPham_New_Title.add(lblIconTitle_New);

		JLabel lblNew_XemThem = new JLabel("Xem Thêm");
		lblNew_XemThem.setForeground(new Color(255, 69, 0));
		lblNew_XemThem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNew_XemThem.setBounds(1170, 16, 75, 14);
		pnSanPham_New_Title.add(lblNew_XemThem);
		lblNew_XemThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				conExtendSanPham(dao_SanPham.getDsSanPhamMoi());
			}
		});

		JPanel pnItem_SanPham_New = new JPanel();
		pnItem_SanPham_New.setBackground(Color.WHITE);
		pnItem_SanPham_New.setBounds(0, 40, 1245, 240);
		pnSanPham_New.add(pnItem_SanPham_New);
		pnItem_SanPham_New.setLayout(new WrapLayout(0, 7, 5));

		for (int i = 0; i < 6; i++) {
			pnItem_SanPham_Sale.add(Item_SanPham(dao_SanPham.getDsSanPhamGiamGia().get(i)));
		}

		for (int i = 0; i < 6; i++) {
			pnItem_SanPham_XuHuong
					.add(Item_SanPham(dao_SanPham.timKiemSanPham(dao_SanPham.getDsMaSPMuaNhieu().get(i))));
		}
		if (dao_SanPham.getDsSanPhamMoi().size()!=0) {
			for (int i = 0; i < 6; i++) {
				pnItem_SanPham_New.add(Item_SanPham(dao_SanPham.getDsSanPhamMoi().get(i)));
			}
		}

		pnSanPham_ButViet.addMouseListener(this);
		pnSanPham_DDHT.addMouseListener(this);
		pnSanPham_VPP.addMouseListener(this);
		pnSanPham_Manga.addMouseListener(this);
		pnSanPham_KT.addMouseListener(this);
		pnSanPham_VanHoc.addMouseListener(this);
		pnSanPham_TamLy.addMouseListener(this);
		return scrollPane;
	}

	/**
	 * fix image
	 * 
	 * @param JLabel
	 * @param W
	 * @param H
	 * @param path
	 */
	public void createIconImage(JLabel x, int W, int H, String path) {
		ImageIcon image = new ImageIcon(
				new ImageIcon(path).getImage().getScaledInstance(W, H, java.awt.Image.SCALE_SMOOTH));
		x.setIcon(image);
		repaint();
		revalidate();
	}

	public Component Item_SanPham(SanPham s) {
		JPanel pnItem_SanPham = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(Color.white);
				// Border set 2 Pix
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 8, 8);
				super.paintComponent(g);
			}
		};
		pnItem_SanPham.setPreferredSize(new Dimension(200, 230));
		pnItem_SanPham.setOpaque(false);
		pnItem_SanPham.setBackground(Color.WHITE);
		pnItem_SanPham.setBounds(244, 166, 200, 230);
		pnItem_SanPham.setLayout(null);

		JLabel lblCuopon = new JLabel("");
		lblCuopon.setIcon(new ImageIcon(new ImageIcon("img/coupon/" + s.getGiamGia() + ".png").getImage()
				.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH)));
		lblCuopon.setBounds(157, -3, 45, 45);
		pnItem_SanPham.add(lblCuopon);

		JLabel lblImg_SanPham = new JLabel("");
		lblImg_SanPham.setIcon(new ImageIcon(
				new ImageIcon(s.getHinhAnh()).getImage().getScaledInstance(170, 150, java.awt.Image.SCALE_SMOOTH)));
		lblImg_SanPham.setBounds(15, 5, 170, 150);
		pnItem_SanPham.add(lblImg_SanPham);

		JLabel lblName_SanPham = new JLabel("<html><p style='text-align: center'>" + s.getTenSP() + "</p></html>");
		lblName_SanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_SanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName_SanPham.setVerticalAlignment(SwingConstants.TOP);
		lblName_SanPham.setBounds(10, 155, 170, 33);
		pnItem_SanPham.add(lblName_SanPham);

		JLabel lblGiaHienTai = new JLabel(
				formatter.format(s.getGiaBan() - s.getGiaBan() * ((float) s.getGiamGia() / 100)) + " VNĐ");
		lblGiaHienTai.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGiaHienTai.setForeground(new Color(255, 0, 0));
		lblGiaHienTai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGiaHienTai.setBounds(10, 186, 117, 25);
		pnItem_SanPham.add(lblGiaHienTai);

		JLabel lblGiaCu = new JLabel();
		lblGiaCu.setText("<html><p style='text-decoration: line-through;'> " + s.getGiaBan() + " </p></html>");
		lblGiaCu.setBounds(10, 210, 117, 14);
		pnItem_SanPham.add(lblGiaCu);

		pnItem_SanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Card_Product(s, new Dao_TaiKhoan().timKiemTaiKhoan(null, khachHang.getMaKH().trim()))
						.setVisible(true);
			}
		});
		return pnItem_SanPham;
	}

	public Component conTrol_CartSanPham() {
		JPanel pnCart = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Paint Border
				g2.setColor(Color.black);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(Color.white);
				// Border set 2 Pix
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 8, 8);
				super.paintComponent(g);
			}
		};
		pnCart.setOpaque(false);
		pnCart.setBackground(Color.WHITE);
		pnCart.setBounds(542, 100, 740, 564);
		pnCart.setPreferredSize(new Dimension(740, 564));
		pnCart.setLayout(null);

		JScrollPane scrSanPham = new JScrollPane();
		scrSanPham.setBorder(null);
		scrSanPham.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrSanPham.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrSanPham.setBounds(9, 5, 730, 500);
		pnCart.add(scrSanPham);

		pnDSSanPham = new JPanel();
		pnDSSanPham.setBackground(Color.WHITE);
		pnDSSanPham.setLayout(new WrapLayout());
		scrSanPham.setViewportView(pnDSSanPham);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnTacVu.setOpaque(false);
		pnTacVu.setBounds(0, 505, 740, 59);
		pnCart.add(pnTacVu);
		pnTacVu.setLayout(null);

		JLabel lblSLSanPham_Title = new JLabel("Số Lượng Sản Phẩm: ");
		lblSLSanPham_Title.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLSanPham_Title.setBounds(10, 10, 160, 20);
		pnTacVu.add(lblSLSanPham_Title);

		lblSoLuongSanPham = new JLabel("0");
		lblSoLuongSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuongSanPham.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuongSanPham.setBounds(161, 10, 189, 20);
		pnTacVu.add(lblSoLuongSanPham);

		lblTongTien = new JLabel("0 VNĐ");
		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(161, 35, 189, 20);
		pnTacVu.add(lblTongTien);

		JLabel lblTongTien_Title = new JLabel("Tổng Tiền: ");
		lblTongTien_Title.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien_Title.setBounds(10, 35, 150, 20);
		pnTacVu.add(lblTongTien_Title);

		JPanel pnThanhToan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnThanhToan.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		pnThanhToan.setBackground(Color.RED);
		pnThanhToan.setBounds(470, 10, 250, 40);
		pnTacVu.add(pnThanhToan);

		JLabel lblThanhToan = new JLabel("Thanh Toán");
		lblThanhToan.setPreferredSize(new Dimension(250, 40));
		lblThanhToan.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setForeground(Color.WHITE);
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThanhToan.add(lblThanhToan);
		if (dsItemCart.size() != 0) {
			for (SanPham i : dsItemCart) {
				pnDSSanPham.add(Item_Cart_Sanpham(dao_SanPham.timKiemSanPham(i.getMaSP())));
			}
		}
		pnThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dsCThd.size() == 0) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Sản Phẩm Để Thanh Toán.");
				} else {
					if (JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Muốn Thanh Toán.", "",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

						for (TinhThanh i : new Dao_DiaChi().getDsTinhThanh_1()) {
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

						for (QuanHuyen i : new Dao_DiaChi().getDsQuanHuyen_1(1)) {
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

						for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(1)) {
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
								if (e.getStateChange() == ItemEvent.SELECTED) {
									cboQuanHuyen.removeAllItems();
									for (QuanHuyen i : new Dao_DiaChi()
											.getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex() + 1)) {
										cboQuanHuyen.addItem(i.getTenQuanHuyen());
									}
									cboPhuongXa.removeAllItems();
									int index = Integer.parseInt(
											new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex() + 1)
													.get(0).getIdQuanHuyen());
									for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
										cboPhuongXa.addItem(i.getTenXaPhuong());
									}
								}
							}
						});
						indexQuanHuyen = 1;
						cboQuanHuyen.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									cboPhuongXa.removeAllItems();
									int index = Integer.parseInt(
											new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex() + 1)
													.get(cboQuanHuyen.getSelectedIndex()).getIdQuanHuyen());
									indexQuanHuyen = index;
									for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
										cboPhuongXa.addItem(i.getTenXaPhuong());
									}
								}
							}
						});
						Object[] r = { "OK", "Bỏ qua" };
						HoaDon hoaDon = null;
						if (JOptionPane.showOptionDialog(null, pnDiaChi, "Nhập Địa Chỉ Giao Hàng",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, r,
								null) == JOptionPane.OK_OPTION) {
							String diaChi = txtSoNha.getText() + ", " + cboPhuongXa.getSelectedItem() + ", "
									+ cboQuanHuyen.getSelectedItem() + ", " + cboTinhThanh.getSelectedItem();
							hoaDon = new HoaDon(taoMaHD(), "Chờ Xác Nhận", null, null, null, diaChi, null,
									khachHang.getMaKH().trim());
						} else {
							hoaDon = new HoaDon(taoMaHD(), "Chờ Xác Nhận", null, null, null, khachHang.getDiaChi(),
									null, khachHang.getMaKH().trim());
						}
						String chuoiGioHang = "";
						for (int i = 0; i < gioHang.length; i++) {
							chuoiGioHang += gioHang[i] + " ";
						}
						chuoiGioHang = chuoiGioHang.trim();

						if (hoaDon != null) {
							new Dao_HoaDon().insertHoaDonKhachHang(hoaDon);
							for (ChiTietHoaDon i : dsCThd) {
								new Dao_ChiTietHoaDon().insertChiTietHoaDon(i);
								pnDSSanPham.remove(dsItemCart.indexOf(new Dao_SanPham().timKiemSanPham(i.getMaSP())));
								pnDSSanPham.repaint();
								pnDSSanPham.revalidate();
								dsItemCart.remove(new Dao_SanPham().timKiemSanPham(i.getMaSP()));
								if (chuoiGioHang.lastIndexOf(i.getMaSP().trim()) + 9 > chuoiGioHang.length()) {
									chuoiGioHang = chuoiGioHang.replaceAll(
											chuoiGioHang.substring(chuoiGioHang.lastIndexOf(i.getMaSP().trim()),
													chuoiGioHang.lastIndexOf(i.getMaSP().trim()) + 8),
											"");
									gioHang = chuoiGioHang.split(" ");
									lblSoLuong_SP.setText(dsItemCart.size() + "");
								} else {
									chuoiGioHang = chuoiGioHang.replaceAll(
											chuoiGioHang.substring(chuoiGioHang.lastIndexOf(i.getMaSP().trim()),
													chuoiGioHang.lastIndexOf(i.getMaSP().trim()) + 9),
											"");
									gioHang = chuoiGioHang.split(" ");
									lblSoLuong_SP.setText(dsItemCart.size() + "");
								}

							}
							dsCThd.removeAll(dsCThd);
							lblTongTien.setText("0 VNĐ");
							lblSoLuongSanPham.setText("0");
						}

						JOptionPane.showMessageDialog(null, "Đơn Hàng Của Bạn Đang Chờ Xác Nhận.");
					}
				}
			}
		});

		return pnCart;
	}

	public Component Item_Cart_Sanpham(SanPham s) {
		JPanel pnCart_Item_SanPham = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(Color.white);
				// Border set 2 Pix
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 8, 8);
				super.paintComponent(g);
			}
		};
		pnCart_Item_SanPham.setMinimumSize(new Dimension(200, 230));
		pnCart_Item_SanPham.setOpaque(false);
		pnCart_Item_SanPham.setBackground(Color.WHITE);
		pnCart_Item_SanPham.setBounds(10, 166, 700, 150);
		pnCart_Item_SanPham.setPreferredSize(new Dimension(700, 150));
		pnCart_Item_SanPham.setLayout(null);

		JCheckBox chkChon = new JCheckBox("");
		chkChon.setBackground(Color.WHITE);
		chkChon.setOpaque(false);
		chkChon.setBounds(5, 60, 20, 30);
		pnCart_Item_SanPham.add(chkChon);

		JLabel lblImg_Cart_SanPham = new JLabel("");
		lblImg_Cart_SanPham.setIcon(new ImageIcon(new ImageIcon(s.getHinhAnh()).getImage().getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
		lblImg_Cart_SanPham.setBounds(10, 5, 140, 140);
		pnCart_Item_SanPham.add(lblImg_Cart_SanPham);

		JLabel lblCartName_SanPham = new JLabel("<html><p>" + s.getTenSP() + "</p></html>");
		lblCartName_SanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblCartName_SanPham.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCartName_SanPham.setVerticalAlignment(SwingConstants.TOP);
		lblCartName_SanPham.setBounds(157, 5, 409, 38);
		pnCart_Item_SanPham.add(lblCartName_SanPham);

		JLabel lblGiaHienTai = new JLabel(
				formatter.format(s.getGiaBan() - s.getGiaBan() * ((float) s.getGiamGia() / 100)) + " VNĐ");
		lblGiaHienTai.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGiaHienTai.setForeground(new Color(255, 0, 0));
		lblGiaHienTai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGiaHienTai.setBounds(157, 45, 117, 20);
		pnCart_Item_SanPham.add(lblGiaHienTai);

		if (s.getGiamGia() > 0) {
			JLabel lblGiaCu = new JLabel();
			lblGiaCu.setText("<html><p style='text-decoration: line-through;'>" + formatter.format(s.getGiaBan())
					+ " VNĐ</p></html>");
			lblGiaCu.setBounds(157, 70, 117, 15);
			pnCart_Item_SanPham.add(lblGiaCu);
		}
		JPanel pnTangGiamSoLuong = new JPanel();
		pnTangGiamSoLuong.setLayout(null);
		pnTangGiamSoLuong.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.LIGHT_GRAY, null, null));
		pnTangGiamSoLuong.setBackground(Color.WHITE);
		pnTangGiamSoLuong.setBounds(157, 90, 120, 30);
		pnCart_Item_SanPham.add(pnTangGiamSoLuong);

		JLabel lblGiam = new JLabel("");
		lblGiam.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\minus.png"));
		lblGiam.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiam.setBounds(0, 0, 30, 30);
		pnTangGiamSoLuong.add(lblGiam);

		JLabel lblSoLuong = new JLabel("1");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoLuong.setBounds(30, 0, 60, 30);
		pnTangGiamSoLuong.add(lblSoLuong);

		JLabel lblTang = new JLabel("");
		lblTang.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\add.png"));
		lblTang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTang.setBounds(90, 0, 30, 30);
		pnTangGiamSoLuong.add(lblTang);

		lblGiam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = Integer.parseInt(lblSoLuong.getText());
				if (x > 1) {
					x--;
					lblSoLuong.setText(x + "");
				}
				if (chkChon.isSelected()) {
					tongTien = 0.f;
					for (ChiTietHoaDon i : dsCThd) {
						if (i.getMaSP().trim().equals(s.getMaSP().trim())) {
							i.setSoLuong(x);
						}
						tongTien += i.getSoLuong() * i.getGiaBan();
					}
					lblTongTien.setText(formatter.format(tongTien) + " VNĐ");
				}
			}
		});

		lblTang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = Integer.parseInt(lblSoLuong.getText());
				if (x < s.getSoLuongTon()) {
					x++;
					lblSoLuong.setText(x + "");
				}
				if (chkChon.isSelected()) {
					tongTien = 0;
					for (ChiTietHoaDon i : dsCThd) {
						if (i.getMaSP().trim().equals(s.getMaSP().trim())) {
							i.setSoLuong(x);
						}
						tongTien += i.getSoLuong() * i.getGiaBan();
					}
					lblTongTien.setText(formatter.format(tongTien) + " VNĐ");
				}
			}
		});
		if (s.getSoLuongTon()<=0) {
			pnTangGiamSoLuong.setVisible(false);
			
			chkChon.setEnabled(false);
			JPanel pnHetHang = new JPanel();
			pnHetHang.setLayout(null);
			pnHetHang.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), null, null));
			pnHetHang.setBackground(Color.RED);
			pnHetHang.setBounds(157, 90, 120, 30);
			pnCart_Item_SanPham.add(pnHetHang);
			
			JLabel lblHetHang = new JLabel("Hết Hàng");
			lblHetHang.setHorizontalAlignment(SwingConstants.CENTER);
			lblHetHang.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblHetHang.setForeground(Color.WHITE);
			lblHetHang.setBounds(0, 0, 120, 30);
			pnHetHang.add(lblHetHang);
		}
		
		JLabel lblDelete_CartItem = new JLabel("");
		lblDelete_CartItem.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\delete_red.png"));
		lblDelete_CartItem.setBounds(665, 114, 25, 25);
		pnCart_Item_SanPham.add(lblDelete_CartItem);
		lblDelete_CartItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnDSSanPham.remove(dsItemCart.indexOf(s));
				pnDSSanPham.repaint();
				pnDSSanPham.revalidate();
				dsItemCart.remove(s);
				String chuoiGioHang = "";
				for (int i = 0; i < gioHang.length; i++) {
					chuoiGioHang += gioHang[i] + " ";
				}
				chuoiGioHang = chuoiGioHang.trim();
				if (chuoiGioHang.lastIndexOf(s.getMaSP().trim()) + 9 > chuoiGioHang.length()) {
					chuoiGioHang = chuoiGioHang.replaceAll(chuoiGioHang.substring(chuoiGioHang.lastIndexOf(s.getMaSP().trim()),
									chuoiGioHang.lastIndexOf(s.getMaSP().trim()) + 8), "");
					gioHang = chuoiGioHang.split(" ");
					lblSoLuong_SP.setText(dsItemCart.size() + "");
				} else {
					chuoiGioHang = chuoiGioHang
							.replaceAll(chuoiGioHang.substring(chuoiGioHang.lastIndexOf(s.getMaSP().trim()),
									chuoiGioHang.lastIndexOf(s.getMaSP().trim()) + 9), "");
					gioHang = chuoiGioHang.split(" ");
					lblSoLuong_SP.setText(dsItemCart.size() + "");
				}
				new Dao_KhachHang().updateGioHang(chuoiGioHang, khachHang.getMaKH().trim());

			}
		});
		chkChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkChon.isSelected()) {
					dsItemCart_Buy.add(s);
					dsCThd.add(new ChiTietHoaDon(s.getMaSP(), taoMaHD(), Integer.parseInt(lblSoLuong.getText()),
							s.getGiaBan() - s.getGiaBan() * ((float) s.getGiamGia() / 100)));
					float tongTien = 0;
					for (ChiTietHoaDon i : dsCThd) {
						tongTien += i.getSoLuong() * i.getGiaBan();
					}
					lblTongTien.setText(formatter.format(tongTien) + " VNĐ");
					lblSoLuongSanPham.setText(dsCThd.size() + "");
				} else {
					dsCThd.remove(dsItemCart_Buy.indexOf(s));
					dsItemCart_Buy.remove(dsItemCart_Buy.indexOf(s));
					tongTien = 0;
					for (ChiTietHoaDon i : dsCThd) {
						tongTien += i.getSoLuong() * i.getGiaBan();
					}
					lblTongTien.setText(formatter.format(tongTien) + " VNĐ");
					lblSoLuongSanPham.setText(dsCThd.size() + "");
				}
			}
		});

		return pnCart_Item_SanPham;
	}

	public void conExtendSanPham(List<SanPham> ds) {
		JFrame jfSanPham = new JFrame();
		jfSanPham.setUndecorated(true);
		jfSanPham.setSize(1320, 758);
		jfSanPham.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfSanPham.setLocationRelativeTo(null);
		jfSanPham.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
		jfSanPham.getContentPane().setLayout(null);
		jfSanPham.getContentPane().setBackground(Color.white);
		
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setBounds(0, 0, 1320, 758);
		jfSanPham.getContentPane().add(pn);

		JLabel lblExit = new JLabel("");
		lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_black.png"));
		lblExit.setBounds(1268, 0, 36, 34);
		pn.add(lblExit);

		JScrollPane scrSanPham = new JScrollPane();
		scrSanPham.setBounds(0, 36, 1300, 700);
		scrSanPham.setBorder(null);
		scrSanPham.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrSanPham.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel pnSanPham = new JPanel();
		pnSanPham.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));

		scrSanPham.setViewportView(pnSanPham);
		pn.add(scrSanPham);
		jfSanPham.setVisible(true);
		setVisible(false);
		
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(true);
				jfSanPham.setVisible(false);
			}
		});

		jfSanPham.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				for (SanPham i : ds) {
					pnSanPham.add(Item_SanPham(i));
					pnSanPham.repaint();
					pnSanPham.revalidate();
				}
			}
		});

	}

	/**
	 * Cập nhật giỏ hàng
	 * @param ma
	 * @return
	 */
	public static String capNhat(String ma) {
		String s = "";
		for (int i = 0; i < gioHang.length; i++) {
			s += gioHang[i].trim() + " ";
		}
		s = s.trim();
		if (!s.trim().contains(ma.trim())) {
			if (dsItemCart.size() == 0) {
				s += ma.trim();
			} else {
				s += " " + ma.trim();
			}

			gioHang = s.split(" ");
			dsItemCart.add(new Dao_SanPham().timKiemSanPham(ma.trim()));
			lblSoLuong_SP.setText(dsItemCart.size() + "");
			return s;
		} else {
			JOptionPane.showMessageDialog(null, "Sản Phẩm Này Đã Tồn Tại Trong Giò Hàng.");
		}
		return "";
	}

	/**
	 * tạo mã hoá đơn
	 * 
	 * @param dshd
	 * @return string
	 */
	public String taoMaHD() {
		HoaDon hd = new Dao_HoaDon().getHoaDonMoi();
		if (hd != null) {
			if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 10) {
				return "HD0000000" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 100) {
				return "HD000000" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 1000) {
				return "HD00000" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 10000) {
				return "HD0000" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 100000) {
				return "HD000" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 1000000) {
				return "HD00" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 10000000) {
				return "HD0" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			} else if (Integer.parseInt(hd.getMaHD().substring(2)) + 1 < 100000000) {
				return "HD" + (Integer.parseInt(hd.getMaHD().substring(2)) + 1);
			}
		}
		return "HD00000001";
	}

	
	
	public static void main(String[] args) {
		new Gui_KhachHang(new KhachHang("KH00000001", "0346676956", "Phạm Thanh Sơn",
				"1/6 Nguyễn Văn Nghi, Phường 04, Quận Gò Vấp, Thành phố Hồ Chí Minh",
				"SP220001 SP220031 SP220011 SP220006", null)).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(lblCart)) {
			JOptionPane.showMessageDialog(null, conTrol_CartSanPham(), "Giỏ Hàng", JOptionPane.DEFAULT_OPTION);
		} else if (e.getSource().equals(lblIndividual)) {
			 new GUI_KhachHang_QuanLyThongTin(khachHang).setVisible(true);
		} else if (e.getSource().equals(pnSanPham_ButViet)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Bút-Viết")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_DDHT)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Đồ Dùng Học Tập")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_VPP)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Đồ Dùng Văn Phòng")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_Manga)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Manga")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_KT)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Sách Kinh Tế")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_VanHoc)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Sách Văn Học")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
		} else if (e.getSource().equals(pnSanPham_TamLy)) {
			List<SanPham> ds = new ArrayList<>();
			for (SanPham i : dao_SanPham.getDsSanPhamFull()) {
				if (i.getMaLoai().trim().equals("Sách Tâm Lý")) {
					ds.add(i);
				}
			}
			conExtendSanPham(ds);
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
}
