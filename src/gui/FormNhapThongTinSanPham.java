package gui;

import java.awt.Color;
import java.awt.Container;
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
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import dao.DAO_LoaiSanPham;
import dao.DAO_NhaCungCap;
import dao.Dao_SanPham;
import entity.LoaiSanPhan;
import entity.NhaCungCap;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;
import jnafilechooser.api.JnaFileChooser;

public class FormNhapThongTinSanPham extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 267208832828484162L;
	private JPanel pnNhapTtSp;
	private JLabel lblTenSp;
	private JTextField txtTenSp;
	private JLabel lblLoaiSp;
	private JLabel lblShowAnh;
	private JButton btnChonAnh;
	private JComboBox<String> cboLoaiSp;
	private Container lblNCC;
	private JComboBox<String> cboNCC;
	private JSpinner txtSoLuong;
	private JSpinner txtDonGia;
	private JSpinner txtGiaGiam;
	private JSpinner txtKhoiLuong;
	private JTextArea txtMoTa;
	private JButton btnThemSp;
	private JLabel lblClose;

	private DAO_LoaiSanPham loaiSanPham;
	private DAO_NhaCungCap nhaCungCap;
	private SanPham sp;
	private JTextField txtMau;
	private JTextField txtChatLieu;
	private JTextField txtThuongHieu;
	private JSpinner txtSoTrang;
	private JSpinner txtNamXB;
	private JTextField txtNXB;
	private JTextField txtTacGia;
	private JButton btnXoatrangText;
	private JComboBox<String> cboPhanLoai;
	private JSpinner txtDonGiaNhap;
	private File sr;
	private File a;
	private JFrame jfThemSp;
	private String newPath;
	private String ChucNang;
	private SanPham sanPham;
	private String exc;

	/**
	 * @wbp.parser.constructor
	 */
	public FormNhapThongTinSanPham(String chucNang, SanPham sanpham) {
		ChucNang = chucNang;
		sanPham = sanpham;
		controlFromThongTinSanPham();
		setText(sanpham);
	}

	public FormNhapThongTinSanPham(String chucNang) {
		ChucNang = chucNang;
		controlFromThongTinSanPham();
	}

	@SuppressWarnings("removal")
	public void controlFromThongTinSanPham() {
		jfThemSp = new JFrame();
		jfThemSp.setUndecorated(true);
		jfThemSp.setFont(new Font("Tahoma", Font.BOLD, 11));
		jfThemSp.setSize(950, 428);
		jfThemSp.setLocationRelativeTo(null);
		jfThemSp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfThemSp.getContentPane().setLayout(null);
		jfThemSp.setShape(new RoundRectangle2D.Double(0, 0, 950, 428, 15, 15));
		sp = new Sach();
		newPath = "img\\SanPham\\Sach\\";
		nhaCungCap = new DAO_NhaCungCap();
		loaiSanPham = new DAO_LoaiSanPham();
		JPanel panel = new JPanel() {
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
				Color color2 = new Color(171, 186, 171);
				GradientPaint gp = new GradientPaint(0, 0, color2, w, h, Color.white);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}

		};
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 950, 428);
		jfThemSp.getContentPane().add(panel);
		panel.setLayout(null);

		pnNhapTtSp = new JPanel();
		pnNhapTtSp.setOpaque(false);
		pnNhapTtSp.setLayout(null);
		pnNhapTtSp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnNhapTtSp.setBounds(15, 37, 920, 334);

		panel.add(pnNhapTtSp);

		lblTenSp = new JLabel("Tên Sản Phẩm: ");
		lblTenSp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenSp.setBounds(11, 25, 100, 25);
		pnNhapTtSp.add(lblTenSp);

		txtTenSp = new JTextField();
		txtTenSp.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenSp.setColumns(10);
		txtTenSp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTenSp.setBounds(121, 25, 201, 25);
		pnNhapTtSp.add(txtTenSp);

		lblLoaiSp = new JLabel("Loại Sản Phẩm: ");
		lblLoaiSp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoaiSp.setBounds(11, 61, 100, 25);
		pnNhapTtSp.add(lblLoaiSp);

		lblShowAnh = new JLabel("");
		lblShowAnh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblShowAnh.setBounds(699, 25, 210, 210);
		pnNhapTtSp.add(lblShowAnh);

		btnChonAnh = new JButton("Chọn Ảnh");

		btnChonAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChonAnh.setForeground(Color.WHITE);
		btnChonAnh.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\img.png"));
		btnChonAnh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChonAnh.setBackground(new Color(143, 188, 143));
		btnChonAnh.setAlignmentY(0.0f);
		btnChonAnh.setBounds(759, 246, 90, 30);
		pnNhapTtSp.add(btnChonAnh);

		cboLoaiSp = new JComboBox<String>();
		cboLoaiSp.setMaximumRowCount(1000);
		cboLoaiSp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboLoaiSp.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboLoaiSp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cboLoaiSp.setBounds(121, 61, 201, 25);
		pnNhapTtSp.add(cboLoaiSp);

		lblNCC = new JLabel("Nhà Cung Cấp: ");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNCC.setBounds(11, 97, 100, 25);
		pnNhapTtSp.add(lblNCC);

		cboNCC = new JComboBox<String>();
		cboNCC.setMaximumRowCount(1000);
		cboNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		for (NhaCungCap i : nhaCungCap.getDsNhaCung()) {
			cboNCC.addItem(i.getTenNCC());
		}
		cboNCC.setFont(new Font("Tahoma", Font.BOLD, 12));
		cboNCC.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cboNCC.setBounds(121, 97, 201, 25);
		pnNhapTtSp.add(cboNCC);

		JLabel lblSoLuong = new JLabel("Số Lượng:  ");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuong.setBounds(356, 133, 100, 25);
		pnNhapTtSp.add(lblSoLuong);

		JLabel lblDonGia = new JLabel("Đơn Giá:  ");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDonGia.setBounds(356, 25, 100, 25);
		pnNhapTtSp.add(lblDonGia);

		JLabel lblGiaGiam = new JLabel("Giá Giảm(%):  ");
		lblGiaGiam.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiaGiam.setBounds(356, 97, 100, 25);
		pnNhapTtSp.add(lblGiaGiam);

		JLabel lblKhoiLuong = new JLabel("Trọng Lượng:  ");
		lblKhoiLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKhoiLuong.setBounds(11, 133, 100, 25);
		pnNhapTtSp.add(lblKhoiLuong);

		JLabel lblMoTa = new JLabel("Mô Tả:  ");
		lblMoTa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoTa.setBounds(11, 245, 638, 25);
		pnNhapTtSp.add(lblMoTa);

		txtSoLuong = new JSpinner();
		txtSoLuong.setModel(new SpinnerNumberModel(new Integer(1000), new Integer(0), null, new Integer(1)));
		txtSoLuong.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSoLuong.setBounds(466, 133, 201, 25);
		pnNhapTtSp.add(txtSoLuong);

		txtDonGia = new JSpinner();
		txtDonGia.setModel(new SpinnerNumberModel(new Integer(30000), new Integer(0), null, new Integer(1000)));
		txtDonGia.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDonGia.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDonGia.setBounds(466, 25, 201, 25);
		pnNhapTtSp.add(txtDonGia);

		txtGiaGiam = new JSpinner();
		txtGiaGiam.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		txtGiaGiam.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtGiaGiam.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtGiaGiam.setBounds(466, 97, 201, 25);
		pnNhapTtSp.add(txtGiaGiam);

		txtKhoiLuong = new JSpinner();
		txtKhoiLuong.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtKhoiLuong.setModel(new SpinnerNumberModel(new Double(200), null, null, new Double(2)));
		txtKhoiLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtKhoiLuong.setBounds(121, 133, 201, 25);
		pnNhapTtSp.add(txtKhoiLuong);

		JScrollPane scrMoTa = new JScrollPane();
		scrMoTa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrMoTa.setBounds(121, 248, 546, 61);
		pnNhapTtSp.add(scrMoTa);

		txtMoTa = new JTextArea();
		txtMoTa.setText(
				"“Địa Cầu” - lá bài độc nhất vô nhị thuộc sở hữu của Koyo - Quán quân giải vô địch đấu bài thế giới lần thứ 3. Đó cũng chính là lá bài mà cậu thiếu niên Judai đang nắm giữ. Bí mật về lá bài được trao đi ấy, mối quan hệ giữa quán quân Koyo và Judai thuở nhỏ, tất cả sẽ được sáng tỏ ngay trong tập này!!");
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMoTa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrMoTa.setViewportView(txtMoTa);
		txtMoTa.setLineWrap(true);
		txtMoTa.setRows(5);

		JLabel lblGiaNhap = new JLabel("Giá Nhập:  ");
		lblGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiaNhap.setBounds(356, 61, 100, 25);
		pnNhapTtSp.add(lblGiaNhap);

		txtDonGiaNhap = new JSpinner();
		txtDonGiaNhap.setModel(new SpinnerNumberModel(new Integer(20000), null, null, new Integer(1)));
		txtDonGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDonGiaNhap.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDonGiaNhap.setBounds(466, 61, 201, 25);
		pnNhapTtSp.add(txtDonGiaNhap);

		JPanel pnVanPhongPham = new JPanel();
		pnVanPhongPham.setOpaque(false);
		pnVanPhongPham.setBounds(0, 0, 0, 0);
		pnNhapTtSp.add(pnVanPhongPham);
		pnVanPhongPham.setLayout(null);

		JLabel lblMau = new JLabel("Màu Sắc: ");
		lblMau.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMau.setBounds(0, 0, 100, 25);
		pnVanPhongPham.add(lblMau);

		txtMau = new JTextField();
		txtMau.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMau.setColumns(10);
		txtMau.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtMau.setBounds(110, 0, 201, 25);
		pnVanPhongPham.add(txtMau);

		JLabel lblChatLieu = new JLabel("Chất Liệu: ");
		lblChatLieu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChatLieu.setBounds(0, 40, 100, 25);
		pnVanPhongPham.add(lblChatLieu);

		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtChatLieu.setColumns(10);
		txtChatLieu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtChatLieu.setBounds(110, 40, 201, 25);
		pnVanPhongPham.add(txtChatLieu);

		JLabel lblThuongHieu = new JLabel("Thương Hiệu: ");
		lblThuongHieu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThuongHieu.setBounds(345, 0, 100, 25);
		pnVanPhongPham.add(lblThuongHieu);

		txtThuongHieu = new JTextField();
		txtThuongHieu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtThuongHieu.setColumns(10);
		txtThuongHieu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtThuongHieu.setBounds(455, 0, 201, 25);
		pnVanPhongPham.add(txtThuongHieu);

		JPanel pnSach = new JPanel();
		pnSach.setOpaque(false);
		pnSach.setBounds(0, 0, 0, 0);
		pnNhapTtSp.add(pnSach);
		pnSach.setLayout(null);

		JLabel lblNamXB = new JLabel("Năm Xuất Bản:  ");
		lblNamXB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNamXB.setBounds(0, 0, 100, 25);
		pnSach.add(lblNamXB);

		JLabel lblSoTrang = new JLabel("Số Trang:  ");
		lblSoTrang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoTrang.setBounds(0, 40, 100, 25);
		pnSach.add(lblSoTrang);

		txtSoTrang = new JSpinner();
		txtSoTrang.setModel(new SpinnerNumberModel(new Integer(208), null, null, new Integer(1)));
		txtSoTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtSoTrang.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSoTrang.setBounds(110, 40, 201, 25);
		pnSach.add(txtSoTrang);

		txtNamXB = new JSpinner();
		txtNamXB.setModel(new SpinnerNumberModel(new Integer(2022), null, null, new Integer(1)));
		txtNamXB.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNamXB.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNamXB.setBounds(110, 0, 201, 25);
		pnSach.add(txtNamXB);

		JLabel lblTacGia = new JLabel("Tác Giả:   ");
		lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTacGia.setBounds(345, 0, 100, 25);
		pnSach.add(lblTacGia);

		JLabel lblNXB = new JLabel("Nhà Xuất Bản: ");
		lblNXB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNXB.setBounds(345, 36, 100, 25);
		pnSach.add(lblNXB);

		txtNXB = new JTextField();
		txtNXB.setText("Kim Đồng");
		txtNXB.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNXB.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNXB.setBounds(455, 36, 201, 25);
		pnSach.add(txtNXB);

		txtTacGia = new JTextField();
		txtTacGia.setText("Kazuki Takahashi");
		txtTacGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTacGia.setColumns(10);
		txtTacGia.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTacGia.setBounds(455, 1, 201, 25);
		pnSach.add(txtTacGia);

		btnXoatrangText = new JButton("Clear Text");
		btnXoatrangText.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnXoatrangText.setBackground(new Color(245, 245, 220));
		btnXoatrangText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnXoatrangText.setFocusable(false);
		btnXoatrangText.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\delete.png"));
		btnXoatrangText.setBounds(185, 382, 180, 35);
		panel.add(btnXoatrangText);

		btnThemSp = new JButton("Thêm Sản Phẩm");
		btnThemSp.setFocusable(false);
		btnThemSp.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\add_product.png"));
		btnThemSp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnThemSp.setBackground(new Color(245, 245, 220));
		btnThemSp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnThemSp.setBounds(385, 382, 180, 35);
		panel.add(btnThemSp);

		lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jfThemSp.dispose();
			}
		});
		lblClose.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_black.png"));
		lblClose.setBounds(918, 0, 32, 32);
		panel.add(lblClose);

		cboPhanLoai = new JComboBox<String>();
		cboPhanLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboPhanLoai.addItem("Sách");
		cboPhanLoai.addItem("Văn Phòng Phẩm");
		cboPhanLoai.setMaximumRowCount(1000);
		cboPhanLoai.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboPhanLoai.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cboPhanLoai.setBounds(15, 5, 138, 25);
		panel.add(cboPhanLoai);
		for (LoaiSanPhan i : loaiSanPham.getDSloai("Sách")) {
			cboLoaiSp.addItem(i.getTenLoai());
		}
		pnSach.setBounds(11, 169, 656, 66);
		cboPhanLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboPhanLoai.getSelectedItem().equals("Sách")) {
					newPath = "img\\SanPham\\Sach\\";
					pnSach.setBounds(11, 169, 656, 66);
					pnVanPhongPham.setBounds(0, 0, 0, 0);
					cboLoaiSp.removeAllItems();
					for (LoaiSanPhan i : loaiSanPham.getDSloai("Sách")) {
						cboLoaiSp.addItem(i.getTenLoai());
					}
					sp = new Sach();

				} else if (cboPhanLoai.getSelectedItem().equals("Văn Phòng Phẩm")) {
					newPath = "img\\SanPham\\VanPhongPham\\";
					pnVanPhongPham.setBounds(11, 169, 656, 66);
					pnSach.setBounds(0, 0, 0, 0);

					cboLoaiSp.removeAllItems();
					for (LoaiSanPhan i : loaiSanPham.getDSloai("Văn Phòng Phẩm")) {
						cboLoaiSp.addItem(i.getTenLoai());
					}
					sp = new VanPhongPham();
				}
			}
		});

		if (ChucNang.equals("Sửa")) {
			btnThemSp.setText("Lưu");
			btnXoatrangText.setBounds(0, 0, 0, 0);
			cboPhanLoai.setVisible(false);
			if (sanPham instanceof Sach) {
				cboPhanLoai.setSelectedItem("Sách");
				newPath = "img\\SanPham\\Sach\\";
				pnSach.setBounds(11, 169, 656, 66);
				pnVanPhongPham.setBounds(0, 0, 0, 0);
				cboLoaiSp.removeAllItems();
				for (LoaiSanPhan i : loaiSanPham.getDSloai("Sách")) {
					cboLoaiSp.addItem(i.getTenLoai());
				}
				sp = new Sach();

			} else if (sanPham instanceof VanPhongPham) {
				cboPhanLoai.setSelectedItem("Văn Phòng Phẩm");
				newPath = "img\\SanPham\\VanPhongPham\\";
				pnVanPhongPham.setBounds(11, 169, 656, 66);
				pnSach.setBounds(0, 0, 0, 0);

				cboLoaiSp.removeAllItems();
				for (LoaiSanPhan i : loaiSanPham.getDSloai("Văn Phòng Phẩm")) {
					cboLoaiSp.addItem(i.getTenLoai());
				}
				sp = new VanPhongPham();
			}

		}
		jfThemSp.setVisible(true);
		btnThemSp.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnXoatrangText.addActionListener(this);
	}

	/**
	 * Tạo Mã Sản phẩm
	 * 
	 * @return String
	 */
	public String taoMaSP() {
		String year = new String(LocalDateTime.now().getYear() + "")
				.substring(new String(LocalDateTime.now().getYear() + "").length() - 2);
		String spLast = "";
		String maSP = "";
		if (new Dao_SanPham().getSanPhamCuoi() != null) {
			spLast = new Dao_SanPham().getSanPhamCuoi();
			int ma = 0;
			if (spLast.substring(2, 4).equals(year)) {
				ma = Integer.parseInt(spLast.substring(4, 8) + "") + 1;
			}
			if (ma < 10) {
				maSP = "SP" + year + "000" + ma;
			} else if (ma < 100) {
				maSP = "SP" + year + "00" + ma;
			} else if (ma < 1000) {
				maSP = "SP" + year + "0" + ma;
			} else if (ma < 10000) {
				maSP = "SP" + year + ma;
			}
		} else {
			maSP = "SP" + year + "0001";
		}

		return maSP;
	}

	/**
	 * Tìm Mã Nhà cũng cấp
	 * 
	 * @param tenNCC
	 * @return String
	 */
	public String timNCC(String tenNCC) {
		for (NhaCungCap i : new DAO_NhaCungCap().getDsNhaCung()) {
			if (i.getTenNCC().equals(tenNCC)) {
				return i.getMaNCC().trim();
			}
		}
		return null;
	}

	/**
	 * Tìm Mã Loại Sản Phẩm
	 * 
	 * @param tenLoai
	 * @return String
	 */
	public String timLoai(String tenLoai) {
		for (LoaiSanPhan i : new DAO_LoaiSanPham().getDSloai(cboPhanLoai.getSelectedItem().toString())) {
			if (i.getTenLoai().trim().equals(tenLoai)) {
				return i.getMaLoai().trim();
			}
		}
		return null;
	}

	/**
	 * Chuyển đổi chuỗi có dấu về không dấu
	 * 
	 * @param chuoi
	 * @return String
	 */
	public String chuyenDoiKyTu(String chuoi) {
		String a = "AÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬ";
		String d = "DĐ";
		String e = "EÊẾỀỂỄỆÉÈẺẼẸ";
		String i = "IÍÌỈĨỊ";
		String y = "YÝỲỶỸỴ";
		String o = "OÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ";
		String u = "UÚÙỦŨỤƯỨỪỬỮỰ";
		String ChuoiLast = "";
		String[] str = { a, d, e, i, o, u, y };
		for (int k = 0; k < chuoi.length(); k++) {
			if ((int) chuoi.charAt(k) >= 65 && (int) chuoi.charAt(k) <= 90
					|| (int) chuoi.charAt(k) >= 97 && (int) chuoi.charAt(k) <= 122
					|| (int) chuoi.charAt(k) >= 40 && (int) chuoi.charAt(k) <= 47) {
				ChuoiLast += chuoi.charAt(k);
			} else {
				boolean xacNhan = false;
				for (String string : str) {
					for (int j = 0; j < string.length(); j++) {
						if ((chuoi.charAt(k) + "").equals((string.charAt(j) + ""))) {
							ChuoiLast += string.charAt(0) + "";
							xacNhan = true;
							break;
						} else if ((((chuoi.charAt(k) + "").toUpperCase()).equals((string.charAt(j) + "")))) {
							ChuoiLast += (string.charAt(0) + "").toLowerCase();
							xacNhan = true;
							break;
						}
					}
					if (xacNhan) {
						break;
					}
				}
			}
		}
		return ChuoiLast;
	}

	/**
	 * xoá nội dung các textfield
	 */
	public void xoaText() {
		cboLoaiSp.setSelectedIndex(0);
		cboNCC.setSelectedIndex(0);
		txtKhoiLuong.setValue(0);
		txtDonGia.setValue(0);
		txtDonGiaNhap.setValue(0);
		txtGiaGiam.setValue(0);
		txtNamXB.setValue(0);
		txtSoLuong.setValue(0);
		txtSoTrang.setValue(0);

		txtTenSp.setText("");
		txtTacGia.setText("");
		txtNXB.setText("");
		txtThuongHieu.setText("");
		txtMau.setText("");
		txtChatLieu.setText("");

		txtMoTa.setText("");
		lblShowAnh.setIcon(null);
	}

	/**
	 * Set text cho các field
	 * 
	 * @param s- SanPham
	 */
	public void setText(SanPham s) {
		txtTenSp.setText(s.getTenSP());
		cboLoaiSp.setSelectedItem(s.getMaLoai());
		cboNCC.setSelectedItem(s.getMaNcc());
		txtKhoiLuong.setValue(s.getKhoiLuong());
		txtDonGia.setValue(s.getGiaBan());
		txtDonGiaNhap.setValue(s.getGiaNhap());
		txtGiaGiam.setValue(s.getGiamGia());
		txtMoTa.setText(s.getMoTa());
		txtSoLuong.setValue(s.getSoLuongTon());
		lblShowAnh.setIcon(new ImageIcon(
				new ImageIcon(s.getHinhAnh()).getImage().getScaledInstance(210, 210, java.awt.Image.SCALE_SMOOTH)));
		if (s instanceof Sach) {
			txtSoTrang.setValue(((Sach) s).getSoTrang());
			txtNamXB.setValue(((Sach) s).getNamXB());
			txtNXB.setText(((Sach) s).getNhaXuatBan());
			txtTacGia.setText(((Sach) s).getTacGia());
		} else if (s instanceof VanPhongPham) {
			txtChatLieu.setText(((VanPhongPham) s).getChatLieu());
			txtMau.setText(((VanPhongPham) s).getMau());
			txtThuongHieu.setText(((VanPhongPham) s).getThuongHieu());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnThemSp) && btnThemSp.getText().trim().equals("Thêm Sản Phẩm")) {

			if (sp != null) {
				boolean kt = false;
				if (sp instanceof Sach) {
					((Sach) sp).setNamXB(Integer.parseInt(txtNamXB.getValue().toString()));
					((Sach) sp).setTacGia(txtTacGia.getText());
					((Sach) sp).setSoTrang(Integer.parseInt(txtSoTrang.getValue().toString()));
					((Sach) sp).setNhaXuatBan(txtNXB.getText());
					if (txtTenSp.getText().trim().equals("") || txtMoTa.getText().trim().equals("")
							|| txtTacGia.getText().trim().equals("") || txtNXB.getText().trim().equals("")
							|| Float.parseFloat(txtKhoiLuong.getValue() + "") == 0
							|| Integer.parseInt(txtNamXB.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") < Float
									.parseFloat(txtDonGiaNhap.getValue() + "")
							|| Float.parseFloat(txtDonGiaNhap.getValue() + "") == 0
							|| Integer.parseInt(txtSoLuong.getValue() + "") == 0
							|| Integer.parseInt(txtSoTrang.getValue() + "") == 0 || a == null) {
						JOptionPane.showMessageDialog(null, "Thông Tin Nhập Không Hợp Lệ hoặc Bỏ Trống.");
					} else {
						kt = true;
					}
				}
				if (sp instanceof VanPhongPham) {
					((VanPhongPham) sp).setThuongHieu(txtThuongHieu.getText());
					((VanPhongPham) sp).setChatLieu(txtChatLieu.getText());
					((VanPhongPham) sp).setMau(txtMau.getText());
					if (txtTenSp.getText().trim().equals("") || txtMoTa.getText().trim().equals("")
							|| txtChatLieu.getText().trim().equals("") || txtMau.getText().trim().equals("")
							|| txtThuongHieu.getText().trim().equals("")
							|| Float.parseFloat(txtKhoiLuong.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") < Float
									.parseFloat(txtDonGiaNhap.getValue() + "")
							|| Float.parseFloat(txtDonGiaNhap.getValue() + "") == 0
							|| Integer.parseInt(txtSoLuong.getValue() + "") == 0 || a == null) {
						JOptionPane.showMessageDialog(null, "Thông Tin Nhập Không Hợp Lệ hoặc Bỏ Trống.");
					} else {
						kt = true;
					}
				}
				if (kt) {
					sp.setMaSP(taoMaSP());
					sp.setTenSP(txtTenSp.getText().trim().toString());
					sp.setMaNcc(timNCC(cboNCC.getSelectedItem().toString()));
					sp.setMaLoai(timLoai(cboLoaiSp.getSelectedItem().toString()));
					sp.setMoTa(txtMoTa.getText());
					sp.setSoLuongTon(Integer.parseInt(txtSoLuong.getValue() + ""));
					sp.setGiamGia(Integer.parseInt(txtGiaGiam.getValue() + ""));
					sp.setGiaNhap(Float.parseFloat(txtDonGiaNhap.getValue() + ""));
					sp.setGiaBan(Float.parseFloat(txtDonGia.getValue() + ""));
					sp.setKhoiLuong(Float.parseFloat(txtKhoiLuong.getValue() + ""));
					sp.setHinhAnh(a.getPath());

					if (new Dao_SanPham().insertSanPham(sp, cboPhanLoai.getSelectedItem().toString())) {
						try {
							Files.copy(sr.toPath(), a.toPath());
						} catch (IOException e1) {
						}
						JOptionPane.showMessageDialog(null, "Sản Phẩm Đã Được Thêm Thành Công");
						xoaText();
						Gui_NhanVien_QLSP.loatDataSp();
					}
				}

			}
		} else if (e.getSource().equals(btnChonAnh)) {
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("jpg", "jpg");
			f.addFilter("png", "png");
			f.showOpenDialog(null);
			File fl = f.getSelectedFile();
			if (f.getSelectedFile() != null) {
				String fileName = fl.getAbsolutePath();
				File directory = new File(newPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				exc = fileName.substring(fileName.lastIndexOf("\\"));
				sr = new File(fileName);
				a = new File(newPath + chuyenDoiKyTu(cboLoaiSp.getSelectedItem() + "") + "\\" + exc);
				lblShowAnh.setIcon(new ImageIcon(new ImageIcon(sr.getPath().toString()).getImage()
						.getScaledInstance(210, 210, java.awt.Image.SCALE_SMOOTH)));
			}
		} else if (e.getSource().equals(btnXoatrangText)) {
			xoaText();
		} else if (e.getSource().equals(btnThemSp) && btnThemSp.getText().trim().equals("Lưu")) {
			if (sanPham != null) {
				System.out.println(sanPham);
				boolean kt = false;
				if (sanPham instanceof Sach) {
					((Sach) sanPham).setNamXB(Integer.parseInt(txtNamXB.getValue().toString()));
					((Sach) sanPham).setTacGia(txtTacGia.getText());
					((Sach) sanPham).setSoTrang(Integer.parseInt(txtSoTrang.getValue().toString()));
					((Sach) sanPham).setNhaXuatBan(txtNXB.getText());
					if (txtTenSp.getText().trim().equals("") || txtMoTa.getText().trim().equals("")
							|| txtTacGia.getText().trim().equals("") || txtNXB.getText().trim().equals("")
							|| Float.parseFloat(txtKhoiLuong.getValue() + "") == 0
							|| Integer.parseInt(txtNamXB.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") < Float.parseFloat(txtDonGiaNhap.getValue() + "")
							|| Float.parseFloat(txtDonGiaNhap.getValue() + "") == 0
							|| Integer.parseInt(txtSoLuong.getValue() + "") == 0
							|| Integer.parseInt(txtSoTrang.getValue() + "") == 0 ) {
						JOptionPane.showMessageDialog(null, "Thông Tin Nhập Không Hợp Lệ hoặc Bỏ Trống.");
					} else {
						kt = true;
					}
				}
				if (sanPham instanceof VanPhongPham) {
					((VanPhongPham) sanPham).setThuongHieu(txtThuongHieu.getText());
					((VanPhongPham) sanPham).setChatLieu(txtChatLieu.getText());
					((VanPhongPham) sanPham).setMau(txtMau.getText());
					if (txtTenSp.getText().trim().equals("") || txtMoTa.getText().trim().equals("")
							|| txtChatLieu.getText().trim().equals("") || txtMau.getText().trim().equals("")
							|| txtThuongHieu.getText().trim().equals("")
							|| Float.parseFloat(txtKhoiLuong.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") == 0
							|| Float.parseFloat(txtDonGia.getValue() + "") < Float
									.parseFloat(txtDonGiaNhap.getValue() + "")
							|| Float.parseFloat(txtDonGiaNhap.getValue() + "") == 0
							|| Integer.parseInt(txtSoLuong.getValue() + "") == 0 ) {
						JOptionPane.showMessageDialog(null, "Thông Tin Nhập Không Hợp Lệ hoặc Bỏ Trống.");
					} else {
						kt = true;
					}
				}
				if (kt) {
					sanPham.setTenSP(txtTenSp.getText().trim().toString());
					sanPham.setMaNcc(timNCC(cboNCC.getSelectedItem().toString()));
					sanPham.setMaLoai(timLoai(cboLoaiSp.getSelectedItem().toString()));
					sanPham.setMoTa(txtMoTa.getText());
					sanPham.setSoLuongTon(Integer.parseInt(txtSoLuong.getValue() + ""));
					sanPham.setGiamGia(Integer.parseInt(txtGiaGiam.getValue() + ""));
					sanPham.setGiaNhap(Float.parseFloat(txtDonGiaNhap.getValue() + ""));
					sanPham.setGiaBan(Float.parseFloat(txtDonGia.getValue() + ""));
					sanPham.setKhoiLuong(Float.parseFloat(txtKhoiLuong.getValue() + ""));


					a = new File(newPath + chuyenDoiKyTu(cboLoaiSp.getSelectedItem() + "") + "\\"
							+ sanPham.getHinhAnh().substring(sanPham.getHinhAnh().lastIndexOf("\\")));
					System.out.println(a);
					System.out.println(sanPham.getHinhAnh());
					if (!a.getPath().equals(sanPham.getHinhAnh())) {
						try {
							File x = new File(sanPham.getHinhAnh());
							Files.copy(x.toPath(), a.toPath());
						} catch (IOException e1) {
						}
						File x = new File(sanPham.getHinhAnh());
						x.delete();
						sanPham.setHinhAnh(a.getPath());

					} else {
						sanPham.setHinhAnh(sanPham.getHinhAnh());
					}
					if (new Dao_SanPham().updateSanPham(sanPham)) {
						JOptionPane.showMessageDialog(null, "Sản Phẩm Đã Được Sửa Thành Công");
						Gui_NhanVien_QLSP.loatDataSp();
						xoaText();
						jfThemSp.setVisible(false);
					}
				}
				
			}
		}
	}

}
