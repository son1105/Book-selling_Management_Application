package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import dao.CreateInvoice;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import rojerusan.RSTableMetro;

public class Gui_NhanVien_LapHD extends JFrame implements PropertyChangeListener, ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dfmTblSanPham;
	private RSTableMetro TblSanPham;
	private HoaDon hd;
	private JLabel lblSoLuong;
	private JLabel lblSoLuongTong;
	private JLabel lblSoTienTong;
	private DefaultTableModel dfmHoaDon;
	private JButton btnClearHoaDon;
	private RSTableMetro tblHoaDon;
	private JScrollPane scrHoaDon;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private float tongTien;
	private JButton btnThanhToan;
	private NhanVien nv;

	private List<ChiTietHoaDon> dsChiTietHd;
	private List<HoaDon> dsHoaDon;
	private List<SanPham> dsSP;

	private Dao_HoaDon hoaDon;
	private Dao_SanPham sanPham;
	private Dao_ChiTietHoaDon chiTietHoaDon;
	private JPanel pnTieuChi;
	private JLabel lblTieuChi;
	private JList<String> lstTieuChi;
	private DefaultListModel<String> almSuggestSearch;
	private JScrollPane scrSuggetSearch;
	private JPanel pnLapHD;

	public Gui_NhanVien_LapHD(NhanVien nV) {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		nv = nV;
		hoaDon = new Dao_HoaDon();
		sanPham = new Dao_SanPham();
		chiTietHoaDon = new Dao_ChiTietHoaDon();
		getContentPane().add(conTrolNhanVien_QLLAPHD());

		dsSP = new ArrayList<SanPham>();
		dsChiTietHd = new ArrayList<ChiTietHoaDon>();
		dsHoaDon = hoaDon.getDsHoaDon();
	}

	public Component conTrolNhanVien_QLLAPHD() {
		pnLapHD = new JPanel();
		pnLapHD.setBorder(null);
		pnLapHD.setBackground(Color.WHITE);
		pnLapHD.setBounds(0, 0, 1237, 664);
		pnLapHD.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.WHITE);
		pnTitle.setBounds(1, 1, 795, 663);
		pnTitle.setLayout(null);
		pnLapHD.add(pnTitle);

		lstTieuChi = new JList<String>();
		lstTieuChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lstTieuChi.setVisible(false);
			}
		});
		lstTieuChi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					lblTieuChi.setText(lstTieuChi.getSelectedValue());

				}
			}
		});

		scrSuggetSearch = new JScrollPane();
		scrSuggetSearch.setVisible(false);
		scrSuggetSearch.setBounds(247, 34, 502, 164);
		pnTitle.add(scrSuggetSearch);

		JList<String> lstSuggetSearch = new JList<String>();
		scrSuggetSearch.setViewportView(lstSuggetSearch);
		lstSuggetSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lstSuggetSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSuggetSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstSuggetSearch.setModel(almSuggestSearch = new DefaultListModel<String>());

		lstTieuChi.setVisible(false);
		lstTieuChi.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Theo Mã Sản Phẩm", "Theo Tên Sản Phẩm" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		lstTieuChi.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lstTieuChi.setBounds(105, 34, 123, 38);
		pnTitle.add(lstTieuChi);

		JScrollPane srcSanPham = new JScrollPane();
		srcSanPham.setBackground(Color.WHITE);
		srcSanPham.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh S\u00E1ch S\u1EA3n Ph\u1EA9m",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		srcSanPham.setBounds(5, 40, 787, 623);
		pnTitle.add(srcSanPham);

		TblSanPham = new RSTableMetro();
		TblSanPham.setFocusable(false);
		TblSanPham.setBorder(BorderFactory.createLineBorder(Color.red));
		TblSanPham.setAutoCreateRowSorter(true);
		TblSanPham.setGrosorBordeFilas(0);
		TblSanPham.setRowHeight(20);
		TblSanPham.setRowMargin(1);
		TblSanPham.setColorBordeHead(Color.RED);
		TblSanPham.setColorBordeFilas(Color.RED);
		TblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		TblSanPham.setGridColor(Color.RED);
		TblSanPham.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		TblSanPham.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		TblSanPham.setColorFilasForeground2(Color.BLACK);
		TblSanPham.setColorFilasForeground1(Color.BLACK);
		TblSanPham.setColorSelBackgound(new Color(255, 99, 71));
		TblSanPham.setColorBackgoundHead(Color.RED);
		TblSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TblSanPham.setColorFilasBackgound1(Color.WHITE);
		TblSanPham.setColorFilasBackgound2(Color.WHITE);
		TblSanPham.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		TblSanPham.setAltoHead(30);
		TblSanPham.setModel(
				dfmTblSanPham = new DefaultTableModel(new String[] { "M\u00E3 ", "T\u00EAn", "\u0110\u01A1n G\u00EDa",
						"S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n", "Gi\u1EA3m G\u00EDa(%)", "Ch\u1ECDn" }, 0) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class,
							Object.class, Boolean.class };

					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});

		TblSanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
		TblSanPham.getColumnModel().getColumn(1).setPreferredWidth(170);
		TblSanPham.getColumnModel().getColumn(2).setPreferredWidth(30);
		TblSanPham.getColumnModel().getColumn(3).setPreferredWidth(30);
		TblSanPham.getColumnModel().getColumn(4).setPreferredWidth(30);
		TblSanPham.getColumnModel().getColumn(5).setPreferredWidth(20);

		srcSanPham.setViewportView(TblSanPham);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.WHITE);
		pnTimKiem.setBorder(null);
		pnTimKiem.setBounds(5, 1, 787, 40);
		pnTitle.add(pnTimKiem);
		pnTimKiem.setLayout(null);

		JPanel pnSearch = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
				g2.setColor(Color.white);
				// Border set 2 Pix
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
				super.paintComponent(g);
			}
		};
		pnSearch.setOpaque(false);
		pnSearch.setBounds(97, 5, 690, 30);
		pnTimKiem.add(pnSearch);
		pnSearch.setLayout(null);

		pnTieuChi = new JPanel();
		pnTieuChi.setOpaque(false);
		pnTieuChi.setBounds(0, 2, 141, 25);
		pnSearch.add(pnTieuChi);
		pnTieuChi.setLayout(null);

		lblTieuChi = new JLabel("Theo Tên Sản Phẩm");
		lblTieuChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTieuChi.setBounds(5, 5, 115, 14);
		pnTieuChi.add(lblTieuChi);

		JLabel lblIconTieuChi = new JLabel("");
		lblIconTieuChi.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bottom_down_Black.png"));
		lblIconTieuChi.setBounds(120, 5, 20, 15);
		pnTieuChi.add(lblIconTieuChi);

		JTextField txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					timSanPham(txtSearch.getText().trim());
					txtSearch.transferFocus();
				}
			}

		});
		txtSearch.setForeground(Color.LIGHT_GRAY);
		txtSearch.setText("Nhập nội dung Tìm Kiếm...");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSearch.setOpaque(false);
		txtSearch.setBorder(null);
		txtSearch.setBounds(138, 2, 518, 25);
		pnSearch.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().trim().equals("")) {
					txtSearch.setText("Nhập nội dung Tìm Kiếm...");
					txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
					txtSearch.setForeground(Color.LIGHT_GRAY);

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtSearch.setText("");
				txtSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
				txtSearch.setForeground(Color.BLACK);
			}
		});
		txtSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (!txtSearch.getText().equals("") && !txtSearch.getText().equals("Nhập nội dung Tìm Kiếm...")
						&& !lblTieuChi.getText().equals("Theo Mã Sản Phẩm")) {
					scrSuggetSearch.setVisible(true);
					almSuggestSearch.removeAllElements();
					for (SanPham i : sanPham.getDsSanPham()) {
						if (i.getTenSP().toUpperCase().contains(txtSearch.getText().toUpperCase())) {
							almSuggestSearch.addElement(i.getTenSP());
						}
					}
				} else {
					scrSuggetSearch.setVisible(false);
					almSuggestSearch.removeAllElements();
				}
			}
		});
		JLabel lblbtnSearch = new JLabel("");
		lblbtnSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblbtnSearch.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Search_black.png"));
		lblbtnSearch.setBounds(660, 2, 25, 25);
		pnSearch.add(lblbtnSearch);

		JLabel lblNewLabel_2 = new JLabel("Quay Lại");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_NhanVien_QLHD.chuyenTrang(1);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("D:\\JavaSwing\\DoAn_QuanLyBanSach\\img\\IMG_LOGIN_SignIn\\back.png"));
		lblNewLabel_2.setBounds(0, 0, 87, 40);
		pnTimKiem.add(lblNewLabel_2);

		JPanel pnOrder = new JPanel();
		pnOrder.setLayout(null);
		pnOrder.setPreferredSize(new Dimension(401, 318));
		pnOrder.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(192, 192, 192)));
		pnOrder.setBackground(Color.WHITE);
		pnOrder.setBounds(800, 1, 436, 663);
		pnLapHD.add(pnOrder);

		JLabel lblNewLabel = new JLabel("Hoá Đơn Hiện Tại");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(7, 6, 327, 33);
		pnOrder.add(lblNewLabel);

		btnClearHoaDon = new JButton("Clear All");
		btnClearHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClearHoaDon.setFocusable(false);
		btnClearHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnClearHoaDon.setBackground(new Color(255, 245, 238));
		btnClearHoaDon.setBounds(352, 9, 80, 30);
		pnOrder.add(btnClearHoaDon);

		scrHoaDon = new JScrollPane();
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrHoaDon.setAlignmentY(Component.TOP_ALIGNMENT);
		scrHoaDon.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrHoaDon.setBorder(new LineBorder(Color.WHITE));
		scrHoaDon.setBackground(Color.WHITE);
		scrHoaDon.setBounds(7, 50, 425, 32);
		pnOrder.add(scrHoaDon);

		tblHoaDon = new RSTableMetro();
		tblHoaDon.setFocusable(false);
		tblHoaDon.setColorBordeFilas(Color.RED);
		tblHoaDon.setGrosorBordeFilas(0);
		tblHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHoaDon.setShowVerticalLines(false);
		tblHoaDon.setColorFilasBackgound1(Color.WHITE);
		tblHoaDon.setColorFilasBackgound2(Color.WHITE);
		tblHoaDon.setColorSelBackgound(new Color(255, 99, 71));
		tblHoaDon.setColorFilasForeground2(Color.BLACK);
		tblHoaDon.setColorFilasForeground1(Color.BLACK);
		tblHoaDon.setRowHeight(30);
		tblHoaDon.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tblHoaDon.setColorForegroundHead(Color.WHITE);
		tblHoaDon.setBorder(null);
		tblHoaDon.setGrosorBordeHead(0);
		tblHoaDon.setColorBackgoundHead(Color.RED);
		tblHoaDon.setAltoHead(30);
		tblHoaDon.setModel(dfmHoaDon = new DefaultTableModel(
				new String[] { "T\u00CAN", "S\u1ED0 L\u01AF\u01A0NG", "TH\u00C0NH TI\u1EC0N", "" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Class<?> getColumnClass(int column) {
				return (column == 3) ? Icon.class : Object.class;
			}

		});
		tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(155);
		tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(85);
		tblHoaDon.getColumnModel().getColumn(3).setPreferredWidth(20);

		tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
		tblHoaDon.getColumnModel().getColumn(1).setResizable(false);

		JTextField lblNewLabel_1 = new JTextField("New label");
		lblNewLabel_1.setBounds(78, 388, 24, 24);

		DefaultCellEditor ds = new DefaultCellEditor(lblNewLabel_1);
		tblHoaDon.getColumnModel().getColumn(3).setCellEditor(ds);
		scrHoaDon.setViewportView(tblHoaDon);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFocusable(false);
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setBackground(Color.RED);
		btnThanhToan.setBorder(null);
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnThanhToan.setBounds(7, 625, 425, 35);
		pnOrder.add(btnThanhToan);

		JLabel lblTongtTien = new JLabel("Tổng Tiền: ");
		lblTongtTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongtTien.setBounds(7, 592, 81, 30);
		pnOrder.add(lblTongtTien);

		lblSoTienTong = new JLabel("0 VNĐ");
		lblSoTienTong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoTienTong.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSoTienTong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoTienTong.setBounds(98, 592, 334, 30);
		pnOrder.add(lblSoTienTong);

		JLabel lblTongSoLuong = new JLabel("Tổng Số Lượng: ");
		lblTongSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongSoLuong.setBounds(7, 567, 118, 30);
		pnOrder.add(lblTongSoLuong);

		lblSoLuongTong = new JLabel("0");
		lblSoLuongTong.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSoLuongTong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuongTong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuongTong.setBounds(135, 567, 297, 30);
		pnOrder.add(lblSoLuongTong);

		loadDataTableSanPham();

		lstSuggetSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timSanPham(lstSuggetSearch.getSelectedValue());
				txtSearch.transferFocus();
				txtSearch.setText(lstSuggetSearch.getSelectedValue());

				scrSuggetSearch.setVisible(false);

			}
		});

		tblHoaDon.addMouseListener(this);
		TblSanPham.addPropertyChangeListener(this);
		TblSanPham.addMouseListener(this);
		btnClearHoaDon.addActionListener(this);
		btnThanhToan.addActionListener(this);
		pnTieuChi.addMouseListener(this);
		pnLapHD.addMouseListener(this);
		return pnLapHD;
	}

	public Component ItemOrderDetail(ChiTietHoaDon ChiTietHD, SanPham sp) {
		JPanel pnOrder_Item = new JPanel();
		pnOrder_Item.setBackground(Color.WHITE);
		pnOrder_Item.setPreferredSize(new Dimension(397, 55));
		pnOrder_Item.setSize(397, 45);
		pnOrder_Item.setLayout(null);

		JLabel lblTenSP = new JLabel(sp.getTenSP());
		lblTenSP.setVerticalAlignment(SwingConstants.TOP);
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenSP.setBounds(10, 5, 377, 16);
		pnOrder_Item.add(lblTenSP);

		JLabel lblDonGia = new JLabel(formatter.format(ChiTietHD.getGiaBan()) + " VNĐ");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDonGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonGia.setBounds(10, 26, 90, 14);
		pnOrder_Item.add(lblDonGia);

		lblSoLuong = new JLabel(ChiTietHD.getSoLuong() + "");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoLuong.setBounds(150, 25, 80, 14);
		pnOrder_Item.add(lblSoLuong);

		JLabel lblThanhTien = new JLabel(formatter.format(ChiTietHD.tinhThanhTien()) + " VNĐ");
		lblThanhTien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThanhTien.setBounds(275, 25, 115, 14);
		pnOrder_Item.add(lblThanhTien);

		JLabel lblHr = new JLabel("");
		lblHr.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblHr.setBounds(25, 44, 347, 1);
		pnOrder_Item.add(lblHr);

		return pnOrder_Item;
	}

	public Component CreateOrder(List<ChiTietHoaDon> ds, List<SanPham> sp, float tienNhan, HoaDon hd) {

		JScrollPane scrOrder = new JScrollPane();
		scrOrder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrOrder.setBorder(null);
		scrOrder.setPreferredSize(new Dimension(451, 700));
		scrOrder.setBounds(1, 1, 434, 700);

		JPanel pnOrder = new JPanel();

		pnOrder.setPreferredSize(new Dimension(401, 318));
		pnOrder.setBackground(Color.WHITE);
		pnOrder.setBorder(new TitledBorder(new LineBorder(new Color(204, 0, 51), 2), "Ho\u00E1 \u0110\u01A1n",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(204, 0, 51)));
		pnOrder.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));

		scrOrder.setViewportView(pnOrder);

		JLabel lblImgLogo = new JLabel("");
		pnOrder.add(lblImgLogo);
		lblImgLogo.setPreferredSize(new Dimension(397, 64));
		lblImgLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgLogo.setIcon(new ImageIcon(new ImageIcon("img/IMG_LOGIN_SignIn/logo.png").getImage()
				.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH)));

		JLabel lblDiaChi = new JLabel("1/6 Nguyễn Văn Nghi, P4, Q.Gò Vấp, TP Hcm");
		pnOrder.add(lblDiaChi);
		lblDiaChi.setPreferredSize(new Dimension(397, 14));
		lblDiaChi.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHr = new JLabel(
				"----------------------------------------------------------------------------------------");
		lblHr.setHorizontalAlignment(SwingConstants.CENTER);
		lblHr.setPreferredSize(new Dimension(397, 10));
		pnOrder.add(lblHr);

		JPanel pnTTLL = new JPanel();
		pnTTLL.setBackground(Color.WHITE);
		pnTTLL.setPreferredSize(new Dimension(397, 70));
		pnOrder.add(pnTTLL);
		pnTTLL.setLayout(null);

		JLabel lblMaHD = new JLabel("Mã Hoá Đơn: ");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaHD.setBounds(0, 0, 74, 20);
		pnTTLL.add(lblMaHD);

		JLabel lblMa = new JLabel(hd.getMaHD());
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMa.setBounds(84, 0, 303, 20);
		pnTTLL.add(lblMa);

		JLabel lblTenNhanVien = new JLabel("Tên Nhân Viên: ");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNhanVien.setBounds(0, 20, 85, 20);
		pnTTLL.add(lblTenNhanVien);

		JLabel lbltenNV = new JLabel(nv.getTenNV());
		lbltenNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltenNV.setBounds(94, 20, 293, 20);
		pnTTLL.add(lbltenNV);

		JLabel lblNgayLap = new JLabel("Ngày Lập Hoá Đơn: ");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgayLap.setBounds(0, 40, 110, 20);
		pnTTLL.add(lblNgayLap);

		@SuppressWarnings("deprecation")
		JLabel lblNgayLapHD = new JLabel(new Date().toLocaleString() + "");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgayLapHD.setBounds(120, 40, 267, 20);
		pnTTLL.add(lblNgayLapHD);

		JLabel lblHr_1 = new JLabel(
				"----------------------------------------------------------------------------------------");
		lblHr_1.setPreferredSize(new Dimension(397, 14));
		lblHr_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnOrder.add(lblHr_1);

		JPanel pnOrder_Title = new JPanel();
		pnOrder_Title.setBackground(Color.WHITE);
		pnOrder_Title.setLayout(null);
		pnOrder_Title.setPreferredSize(new Dimension(397, 20));
		pnOrder.add(pnOrder_Title);

		JLabel lblTitle_DonGia = new JLabel("Đơn Giá(-%)");
		lblTitle_DonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle_DonGia.setBounds(10, 0, 80, 20);
		pnOrder_Title.add(lblTitle_DonGia);

		JLabel lblTile_SoLuong = new JLabel("Số Lượng");
		lblTile_SoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTile_SoLuong.setBounds(158, 0, 80, 20);
		pnOrder_Title.add(lblTile_SoLuong);

		JLabel lblTitle_ThanhTien = new JLabel("Thành Tiền");
		lblTitle_ThanhTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle_ThanhTien.setBounds(306, 0, 70, 20);
		pnOrder_Title.add(lblTitle_ThanhTien);

		JScrollPane srcDsChiTietHD = new JScrollPane();
		srcDsChiTietHD.setBackground(Color.WHITE);
		srcDsChiTietHD.setBorder(null);
		srcDsChiTietHD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		srcDsChiTietHD.setPreferredSize(new Dimension(420, 330));
		pnOrder.add(srcDsChiTietHD);

		JPanel pnDsChiTietHD = new JPanel();
		pnDsChiTietHD.setBackground(Color.WHITE);
		srcDsChiTietHD.setViewportView(pnDsChiTietHD);
		FlowLayout flowLayout = (FlowLayout) pnDsChiTietHD.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		pnDsChiTietHD.setPreferredSize(new Dimension(397, 0));

		JPanel pnTong = new JPanel();
		pnTong.setBackground(Color.WHITE);
		pnTong.setPreferredSize(new Dimension(397, 120));
		pnOrder.add(pnTong);
		pnTong.setLayout(null);

		JLabel lblHr_1_1 = new JLabel(
				"----------------------------------------------------------------------------------------");
		lblHr_1_1.setPreferredSize(new Dimension(397, 10));
		lblHr_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHr_1_1.setBounds(0, 0, 397, 14);
		pnTong.add(lblHr_1_1);

		JLabel lblTongTien = new JLabel("Tổng Tiền: ");
		lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(138, 45, 116, 25);
		pnTong.add(lblTongTien);

		JLabel lblTienTong = new JLabel();
		lblTienTong.setText("0");
		lblTienTong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienTong.setHorizontalAlignment(SwingConstants.CENTER);
		lblTienTong.setBounds(262, 45, 125, 25);
		pnTong.add(lblTienTong);

		JLabel lblSoLuong = new JLabel("Tổng Số Lượng: ");
		lblSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuong.setBounds(138, 20, 116, 25);
		pnTong.add(lblSoLuong);

		JLabel lblSLTong = new JLabel();
		lblSLTong.setText("0");
		lblSLTong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSLTong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLTong.setBounds(262, 20, 125, 25);
		pnTong.add(lblSLTong);

		JLabel lblTienNhan = new JLabel("Số Tiền Nhận: ");
		lblTienNhan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTienNhan.setBounds(138, 70, 116, 20);
		pnTong.add(lblTienNhan);

		JLabel lblSoTienNhan = new JLabel();
		lblSoTienNhan.setText(formatter.format(tienNhan) + " VNĐ");
		lblSoTienNhan.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoTienNhan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoTienNhan.setBounds(262, 70, 125, 25);
		pnTong.add(lblSoTienNhan);
		for (ChiTietHoaDon s : ds) {
			pnDsChiTietHD.add(ItemOrderDetail(s, timSanPham(s.getMaSP(), dsSP)));
			pnDsChiTietHD.setPreferredSize(new Dimension(397, pnDsChiTietHD.getPreferredSize().height + 55));
		}
		lblSLTong.setText(ds.size() + "");
		lblTienTong.setText(formatter.format(tongTien) + " VNĐ");

		JLabel lblTienThua = new JLabel("Tiền Thừa: ");
		lblTienThua.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTienThua.setBounds(138, 95, 116, 20);
		pnTong.add(lblTienThua);

		JLabel lblSoTienThua = new JLabel();
		lblSoTienThua.setText(formatter.format(tienNhan - tongTien) + " VNĐ");
		lblTienTong.setText(formatter.format(tongTien) + " VNĐ");

		lblSoTienThua.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoTienThua.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoTienThua.setBounds(262, 95, 125, 25);
		pnTong.add(lblSoTienThua);

		hd.setNgayGiaoHang(new java.sql.Date(System.currentTimeMillis()));
		hd.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
		hd.setNgayLayHang(new java.sql.Date(System.currentTimeMillis()));
		hd.setDiaChi("1/6 Nguyễn Văn Nghi, P4, Q.Gò Vấp, TP Hcm");
		hd.setTrangThai("Đã Giao");

		return scrOrder;

	}

	/**
	 * load data vào bảng sản phẩm
	 */
	public void loadDataTableSanPham() {
		
		for (SanPham s : sanPham.getDsSanPham()) {
			Object[] sp = { s.getMaSP(), s.getTenSP(), s.getGiaBan(), s.getSoLuongTon(), s.getGiamGia(), false };
			dfmTblSanPham.addRow(sp);
		}

	}

	public static void main(String[] args) {
		new Gui_NhanVien_LapHD(new NhanVien("NV2201", "Phạm Thanh Sơn", "0346676956", "img/AvtUser/NhanVien/NV2201.jpg",
				"sonpham28052002@gmail.com", "Nhân Viên")).setVisible(true);
	}

	public void timSanPham(String ten) {
		for (int i = 0; i < TblSanPham.getRowCount(); i++) {
			if (!lblTieuChi.getText().equals("Theo Mã Sản Phẩm")) {
				if (TblSanPham.getValueAt(i, 1).toString().equals(ten)) {
					TblSanPham.changeSelection(i, 0, false, false);
					break;
				}
			} else {
				if (TblSanPham.getValueAt(i, 0).toString().trim().equals(ten)) {
					TblSanPham.changeSelection(i, 0, false, false);
					break;
				}
			}
		}
	}

	/**
	 * tìm sản phẩm
	 * 
	 * @param maSP
	 * @param ds
	 * @return sản phẩm
	 */
	public SanPham timSanPham(String maSP, List<SanPham> ds) {
		for (SanPham i : ds) {
			if (i.getMaSP().equals(maSP)) {
				return i;
			}
		}
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().equals(TblSanPham)) {
			scrSuggetSearch.setVisible(false);
			if (("tableCellEditor".equals(evt.getPropertyName()))) {
				if (!TblSanPham.isEditing()) {
					SanPham s = new SanPham(dfmTblSanPham.getValueAt(TblSanPham.getSelectedRow(), 0).toString(),
							dfmTblSanPham.getValueAt(TblSanPham.getSelectedRow(), 1).toString(),
							Integer.parseInt(dfmTblSanPham.getValueAt(TblSanPham.getSelectedRow(), 3).toString()),
							Integer.parseInt(dfmTblSanPham.getValueAt(TblSanPham.getSelectedRow(), 4).toString()),
							Float.parseFloat(dfmTblSanPham.getValueAt(TblSanPham.getSelectedRow(), 2).toString()));
					hd = new HoaDon(taoMaHD(), getName(), null, null,
							null, getTitle(), nv.getMaNV(), nv.getMaNV());

					if (TblSanPham.getValueAt(TblSanPham.getSelectedRow(), 5).toString().equals("true")) {

						JPanel pnNhapSL = new JPanel();
						pnNhapSL.setBounds(0, 0, 250, 88);
						pnNhapSL.setPreferredSize(new Dimension(250, 88));
						getContentPane().add(pnNhapSL);

						JLabel lblTitle = new JLabel("Nhập Số Lượng Cho Cần Mua: ");
						lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
						lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblTitle.setBounds(10, 11, 230, 21);
						pnNhapSL.add(lblTitle);

						JSpinner txtSL = new JSpinner();
						txtSL.setBounds(10, 43, 230, 30);
						txtSL.setFont(new Font("Tahoma", Font.BOLD, 13));
						txtSL.setModel(new SpinnerNumberModel(1, 1, s.getSoLuongTon(), 1));
						txtSL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						txtSL.setPreferredSize(new Dimension(230, 30));
						pnNhapSL.add(txtSL);

						if (JOptionPane.showOptionDialog(null, pnNhapSL, "Nhập Số Lượng Cho Cần Mua: ",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null,
								JOptionPane.CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							tongTien = 0;
							dsSP.add(s);
							ChiTietHoaDon chiTietHD = new ChiTietHoaDon(s.getMaSP(), hd.getMaHD(),
									Integer.parseInt(txtSL.getValue() + ""),
									s.getGiaBan() - s.getGiaBan() * ((float) s.getGiamGia() / 100));
							dsChiTietHd.add(chiTietHD);

							Object[] elmChiTietHD = { s.getTenSP(), chiTietHD.getSoLuong() + "",
									formatter.format(chiTietHD.getGiaBan() * chiTietHD.getSoLuong()) + " VNĐ",
									new ImageIcon("img/IMG_LOGIN_SignIn/delete_red.png") };
							dfmHoaDon.addRow(elmChiTietHD);
							tblHoaDon.setRowSelectionInterval(0, 0);
							if (scrHoaDon.getHeight() < 510) {
								scrHoaDon.setBounds(7, 50, 425, scrHoaDon.getHeight() + 30);
							}
							for (ChiTietHoaDon chiTietHoaDon : dsChiTietHd) {
								tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
							}
							lblSoTienTong.setText(formatter.format(tongTien) + " VNĐ");
							lblSoLuongTong.setText(dsChiTietHd.size() + "");

						} else {
							TblSanPham.setValueAt(false, TblSanPham.getSelectedRow(), 5);
						}

					} else {
						int index = -1;
						for (ChiTietHoaDon i : dsChiTietHd) {
							if (i.getMaSP().equals(s.getMaSP())) {
								index = dsChiTietHd.indexOf(i);
							}
						}
						if (index != -1) {
							tongTien = 0;
							dfmHoaDon.removeRow(index);
							scrHoaDon.setBounds(7, 50, 425, scrHoaDon.getHeight() - 30);
							dsChiTietHd.remove(index);
							dsSP.remove(index);
							for (ChiTietHoaDon chiTietHoaDon : dsChiTietHd) {
								tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
							}
							lblSoTienTong.setText(formatter.format(tongTien) + " VNĐ");
							lblSoLuongTong.setText(dsChiTietHd.size() + "");
							if (dsChiTietHd.size() != 0) {
								tblHoaDon.setRowSelectionInterval(0, 0);
							}
						}
					}
				}
			}
		}

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
		return  "HD00000001";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnClearHoaDon)) {
			dfmHoaDon.setRowCount(0);
			scrHoaDon.setBounds(7, 50, 425, 32);
			lblSoLuongTong.setText("0");
			lblSoTienTong.setText("0 VNĐ");
			for (int i = 0; i < TblSanPham.getRowCount(); i++) {
				TblSanPham.setValueAt(false, i, 5);
			}
			dsChiTietHd.removeAll(dsChiTietHd);
			dsSP.removeAll(dsSP);
			TblSanPham.setRowSelectionInterval(0, 0);
			TblSanPham.changeSelection(0, 0, false, false);
		} else if (e.getSource().equals(btnThanhToan)) {
			if (dsChiTietHd.size() != 0) {
				JPanel pnNhapTienNhan = new JPanel();
				pnNhapTienNhan.setBounds(0, 0, 250, 88);
				pnNhapTienNhan.setPreferredSize(new Dimension(250, 88));
				getContentPane().add(pnNhapTienNhan);

				JLabel lblTitle = new JLabel("Nhập Số Tiền Nhận Được: ");
				lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTitle.setBounds(10, 11, 230, 21);
				pnNhapTienNhan.add(lblTitle);

				JSpinner txtTien = new JSpinner();
				txtTien.setBounds(10, 43, 230, 30);
				txtTien.setFont(new Font("Tahoma", Font.BOLD, 13));
				txtTien.setModel(new SpinnerNumberModel(tongTien, tongTien, null, 1000));
				txtTien.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				txtTien.setPreferredSize(new Dimension(230, 30));
				pnNhapTienNhan.add(txtTien);
				if (JOptionPane.showOptionDialog(null, pnNhapTienNhan, "Nhập Số Tiền Nhận Được: ",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null,
						null) == JOptionPane.OK_OPTION) {

					if (JOptionPane.showConfirmDialog(null,
							CreateOrder(dsChiTietHd, dsSP, Float.parseFloat(txtTien.getValue().toString()), hd),
							"Đơn Hàng", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION) {
						dsHoaDon.add(hd);
						if (hoaDon.insertHoaDon(hd)) {

							for (ChiTietHoaDon i : dsChiTietHd) {
								chiTietHoaDon.insertChiTietHoaDon(i);
							}

							dfmHoaDon.setRowCount(0);
							scrHoaDon.setBounds(7, 50, 425, 32);
							lblSoLuongTong.setText("0");
							lblSoTienTong.setText("0 VNĐ");
							for (int i = 0; i < TblSanPham.getRowCount(); i++) {
								TblSanPham.setValueAt(false, i, 5);
							}

							TblSanPham.setRowSelectionInterval(0, 0);
							if (JOptionPane.showConfirmDialog(null, "Bạn Có Muốn In Hoá Đơn Không", "",
									JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
								try {
									new CreateInvoice().createOrder(dsSP, dsChiTietHd, hd, nv);
								} catch (DocumentException | IOException e1) {
									e1.printStackTrace();
								}
							}
							hd = null;
							dsChiTietHd.removeAll(dsChiTietHd);
							dsSP.removeAll(dsSP);
							dfmTblSanPham.setRowCount(0);
							loadDataTableSanPham();
							GUI_ThongKe.loadStatistics();
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tblHoaDon)) {
			scrSuggetSearch.setVisible(false);
			if (e.getClickCount() == 2) {
				if (((JTable) e.getComponent()).getSelectedColumn() == 3) {
					tongTien = 0;
					scrHoaDon.setBounds(7, 50, 425, scrHoaDon.getHeight() - 30);
					dsChiTietHd.remove(tblHoaDon.getSelectedRow());
					for (int i = 0; i < TblSanPham.getRowCount(); i++) {
						if (TblSanPham.getValueAt(i, 0).toString().trim()
								.equals(dsSP.get(tblHoaDon.getSelectedRow()).getMaSP().trim())) {
							TblSanPham.setValueAt(false, i, 5);
							break;
						}

					}
					dsSP.remove(tblHoaDon.getSelectedRow());
					for (ChiTietHoaDon chiTietHoaDon : dsChiTietHd) {
						tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
					}
					dfmHoaDon.removeRow(tblHoaDon.getSelectedRow());
					lblSoTienTong.setText(formatter.format(tongTien) + " VNĐ");
					lblSoLuongTong.setText(dsChiTietHd.size() + "");

				} else {

					JPanel pnNhapsoLuong = new JPanel();
					pnNhapsoLuong.setBounds(0, 0, 250, 88);
					pnNhapsoLuong.setPreferredSize(new Dimension(250, 88));
					getContentPane().add(pnNhapsoLuong);

					JLabel lblTitle = new JLabel("Nhập Số Lượng Mới: ");
					lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
					lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblTitle.setBounds(10, 11, 230, 21);
					pnNhapsoLuong.add(lblTitle);

					JSpinner txtSL = new JSpinner();
					txtSL.setBounds(10, 43, 230, 30);
					txtSL.setFont(new Font("Tahoma", Font.BOLD, 13));

					txtSL.setModel(new SpinnerNumberModel(
							Integer.parseInt(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString()), 1,
							dsSP.get(tblHoaDon.getSelectedRow()).getSoLuongTon(), 1));
					txtSL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					txtSL.setPreferredSize(new Dimension(230, 30));
					pnNhapsoLuong.add(txtSL);
					if (JOptionPane.showOptionDialog(null, pnNhapsoLuong, "Nhập Số Lượng: ",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null,
							null) == JOptionPane.OK_OPTION) {
						tblHoaDon.setValueAt(Integer.parseInt(txtSL.getValue().toString()), tblHoaDon.getSelectedRow(),
								1);
						dsChiTietHd.get(tblHoaDon.getSelectedRow())
								.setSoLuong(Integer.parseInt(txtSL.getValue().toString()));
						tblHoaDon.setValueAt(
								formatter.format(dsChiTietHd.get(tblHoaDon.getSelectedRow()).getGiaBan()
										* dsChiTietHd.get(tblHoaDon.getSelectedRow()).getSoLuong()) + " VNĐ",
								tblHoaDon.getSelectedRow(), 2);
						tongTien = 0;
						for (ChiTietHoaDon chiTietHoaDon : dsChiTietHd) {
							tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
						}

						lblSoTienTong.setText(formatter.format(tongTien) + " VNĐ");
					}
				}
			}
		} else if (e.getSource().equals(pnTieuChi)) {
			lstTieuChi.setVisible(true);
		} else if (e.getSource().equals(TblSanPham)) {
			scrSuggetSearch.setVisible(false);
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
		if (e.getSource().equals(TblSanPham) || !e.getSource().equals(scrSuggetSearch)) {
			scrSuggetSearch.setVisible(false);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(lstTieuChi)) {
			lstTieuChi.setVisible(false);
		}

	}
}
