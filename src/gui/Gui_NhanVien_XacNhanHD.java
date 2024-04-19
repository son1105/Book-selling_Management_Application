package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import java.text.DecimalFormat;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.DAO_ChiTietHoaDonDoiTra;
import dao.DAO_HoaDonDoiTra;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.NhanVien;
import rojerusan.RSTableMetro;

public class Gui_NhanVien_XacNhanHD extends JFrame implements MouseListener, ActionListener, PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnTieuChi;
	private JLabel lblTieuChi;
	private DefaultTableModel dfmHoaDon;
	private RSTableMetro tblHoaDon;
	private DefaultTableModel dfmChiTietHoaDon;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private JButton btnTatCa;
	private JButton btnXacNhanGiao;
	private JButton btnChoLayHang;
	private JButton btnChoXacNhan;
	private String thaoThac = "Tất Cả";
	private NhanVien nv;
	private JButton btnDaGiaoHang;
	private TableColumn column;
	private JList<String> lstTieuChi;
	private JButton btnDoiTra;
	private JScrollPane scrHoaDon;
	private DefaultTableModel dfmChiTietHoaDonDoiTra;
	private RSTableMetro tblHoaDonDoiTra;
	private JScrollPane scrHoaDonDoiTra;
	private DefaultTableModel dfmHoaDonDoiTra;
	private RSTableMetro tblChiTietHoaDonDoiTra;
	private JScrollPane scrChiTietHoaDon;
	private JPanel pnDoiTra;

	public Gui_NhanVien_XacNhanHD(NhanVien nhanVien) {
		setSize(1251, 705);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		nv = nhanVien;

		getContentPane().add(conTrolXacNhanDH());
	}

	public Component conTrolXacNhanDH() {
		JPanel pnLapHD = new JPanel();
		pnLapHD.setBorder(null);
		pnLapHD.setBackground(Color.WHITE);
		pnLapHD.setBounds(0, 0, 1237, 664);
		pnLapHD.setLayout(null);

		lstTieuChi = new JList<String>();
		lstTieuChi.setVisible(false);
		lstTieuChi.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstTieuChi.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Theo Mã Hoá Đơn", "Theo Mã Nhân Viên", "Theo Mã Khách Hàng" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		lstTieuChi.setSelectedIndex(0);
		lstTieuChi.setBounds(280, 30, 128, 55);
		pnLapHD.add(lstTieuChi);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.WHITE);
		pnTimKiem.setBorder(null);
		pnTimKiem.setBounds(263, 1, 710, 40);
		pnLapHD.add(pnTimKiem);
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
		pnSearch.setBounds(10, 5, 690, 30);
		pnTimKiem.add(pnSearch);
		pnSearch.setLayout(null);

		pnTieuChi = new JPanel();
		pnTieuChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstTieuChi.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lstTieuChi.setVisible(false);
			}
		});
		pnTieuChi.setOpaque(false);
		pnTieuChi.setBounds(0, 2, 158, 25);
		pnSearch.add(pnTieuChi);
		pnTieuChi.setLayout(null);

		lblTieuChi = new JLabel("Theo Mã Hoá Đơn");
		lblTieuChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTieuChi.setBounds(5, 5, 130, 14);
		pnTieuChi.add(lblTieuChi);

		JLabel lblIconTieuChi = new JLabel("");
		lblIconTieuChi.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bottom_down_Black.png"));
		lblIconTieuChi.setBounds(130, 5, 20, 15);
		pnTieuChi.add(lblIconTieuChi);

		JTextField txtSearch = new JTextField();
		txtSearch.transferFocus();

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
		txtSearch.setBounds(162, 2, 500, 25);
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
		JLabel lblbtnSearch = new JLabel("");
		lblbtnSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblbtnSearch.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Search_black.png"));
		lblbtnSearch.setBounds(660, 2, 25, 25);
		pnSearch.add(lblbtnSearch);

		scrHoaDon = new JScrollPane();
		scrHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrHoaDon.setOpaque(false);
		scrHoaDon.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh S\u00E1ch Ho\u00E1 \u0110\u01A1n",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		scrHoaDon.setBounds(5, 86, 805, 567);
		pnLapHD.add(scrHoaDon);

		tblHoaDon = new RSTableMetro();
		tblHoaDon.setModel(dfmHoaDon = new DefaultTableModel(new String[] { "M\u00E3 Ho\u00E1 \u0110\u01A1n",
				"M\u00E3 Nh\u00E2n Vi\u00EAn", "M\u00E3 Kh\u00E1ch H\u00E0ng", "Ng\u00E0y L\u1EADp",
				"Ng\u00E0y L\u1EA5y H\u00E0ng", "Ng\u00E0y Giao H\u00E0ng", "Trạng Th\u00E1i", "X\u00E1c Nh\u1EADn" },
				0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(69);
		tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(92);
		tblHoaDon.getColumnModel().getColumn(4).setPreferredWidth(83);
		tblHoaDon.getColumnModel().getColumn(5).setPreferredWidth(89);
		tblHoaDon.getColumnModel().getColumn(6).setPreferredWidth(78);
		tblHoaDon.getColumnModel().getColumn(7).setPreferredWidth(57);
		tblHoaDon.setFocusable(false);
		tblHoaDon.setBorder(new LineBorder(Color.RED));
		tblHoaDon.setColorBackgoundHead(Color.RED);
		tblHoaDon.setAltoHead(30);

		scrHoaDon.setViewportView(tblHoaDon);
		tblHoaDon.setGrosorBordeFilas(0);
		tblHoaDon.setRowHeight(25);
		tblHoaDon.setRowMargin(1);
		tblHoaDon.setColorBordeHead(Color.RED);
		tblHoaDon.setColorBordeFilas(Color.RED);
		tblHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblHoaDon.setGridColor(Color.RED);
		tblHoaDon.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tblHoaDon.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tblHoaDon.setColorFilasForeground2(Color.BLACK);
		tblHoaDon.setColorFilasForeground1(Color.BLACK);
		tblHoaDon.setColorSelBackgound(new Color(255, 99, 71));
		tblHoaDon.setColorBackgoundHead(Color.RED);
		tblHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHoaDon.setColorFilasBackgound1(Color.WHITE);
		tblHoaDon.setColorFilasBackgound2(Color.WHITE);
		tblHoaDon.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tblHoaDon.setAltoHead(30);
		for (HoaDon i : new Dao_HoaDon().getDsHoaDonDatHangChuaGiao(nv.getMaNV().trim())) {
			Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
					i.getNgayGiaoHang(), i.getTrangThai(), false };
			dfmHoaDon.addRow(x);
		}

		JLabel lblBack = new JLabel("Quay Lại");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_NhanVien_QLHD.chuyenTrang(1);
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBack.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\back.png"));
		lblBack.setBounds(10, 5, 96, 35);
		pnLapHD.add(lblBack);

		btnChoXacNhan = new JButton("Chờ Xác Nhận");
		btnChoXacNhan.setFocusable(false);
		btnChoXacNhan.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChoXacNhan.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChoXacNhan.setBackground(new Color(245, 245, 220));
		btnChoXacNhan.setBounds(290, 43, 140, 32);
		pnLapHD.add(btnChoXacNhan);

		btnChoLayHang = new JButton("Chờ Lấy Hàng");
		btnChoLayHang.setFocusable(false);
		btnChoLayHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChoLayHang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChoLayHang.setBackground(new Color(245, 245, 220));
		btnChoLayHang.setBounds(450, 43, 140, 32);
		pnLapHD.add(btnChoLayHang);

		btnTatCa = new JButton("Tất cả");
		btnTatCa.setIcon(new ImageIcon("C:\\Users\\Leon\\Downloads\\icons8-order-history-30.png"));
		btnTatCa.setFocusable(false);
		btnTatCa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTatCa.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnTatCa.setBackground(new Color(245, 245, 220));
		btnTatCa.setBounds(130, 43, 140, 32);
		pnLapHD.add(btnTatCa);

		btnXacNhanGiao = new JButton("Xác Nhận Giao Hàng");
		btnXacNhanGiao.setFocusable(false);
		btnXacNhanGiao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXacNhanGiao.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnXacNhanGiao.setBackground(new Color(245, 245, 220));
		btnXacNhanGiao.setBounds(610, 43, 140, 32);
		pnLapHD.add(btnXacNhanGiao);

		btnDaGiaoHang = new JButton("Đã Giao Hàng");
		btnDaGiaoHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDaGiaoHang.setFocusable(false);
		btnDaGiaoHang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDaGiaoHang.setBackground(new Color(245, 245, 220));
		btnDaGiaoHang.setBounds(770, 43, 140, 32);
		pnLapHD.add(btnDaGiaoHang);

		btnDoiTra = new JButton("Đơn Đổi Trả");
		btnDoiTra.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDoiTra.setFocusable(false);
		btnDoiTra.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDoiTra.setBackground(new Color(245, 245, 220));
		btnDoiTra.setBounds(930, 43, 140, 32);
		pnLapHD.add(btnDoiTra);

		scrChiTietHoaDon = new JScrollPane();
		scrChiTietHoaDon.setOpaque(false);
		scrChiTietHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrChiTietHoaDon.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Danh S\u00E1ch Chi Ti\u1EBFt Ho\u00E1 \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		scrChiTietHoaDon.setBounds(810, 86, 420, 567);
		pnLapHD.add(scrChiTietHoaDon);

		RSTableMetro tblChiTietHoaDon = new RSTableMetro();
		tblChiTietHoaDon.setModel(dfmChiTietHoaDon = new DefaultTableModel(new String[] { "M\u00E3 S\u1EA3n Ph\u1EA9m",
				"S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n Gi\u00E1", "Th\u00E0nh Ti\u1EC1n" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblChiTietHoaDon.getColumnModel().getColumn(0).setResizable(false);
		tblChiTietHoaDon.getColumnModel().getColumn(1).setResizable(false);
		tblChiTietHoaDon.getColumnModel().getColumn(2).setResizable(false);
		tblChiTietHoaDon.getColumnModel().getColumn(3).setResizable(false);

		tblChiTietHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblChiTietHoaDon.setRowMargin(1);
		tblChiTietHoaDon.setRowHeight(25);
		tblChiTietHoaDon.setGrosorBordeFilas(0);
		tblChiTietHoaDon.setGridColor(Color.RED);
		tblChiTietHoaDon.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tblChiTietHoaDon.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tblChiTietHoaDon.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tblChiTietHoaDon.setFocusable(false);
		tblChiTietHoaDon.setColorSelBackgound(new Color(255, 99, 71));
		tblChiTietHoaDon.setColorFilasForeground2(Color.BLACK);
		tblChiTietHoaDon.setColorFilasForeground1(Color.BLACK);
		tblChiTietHoaDon.setColorFilasBackgound2(Color.WHITE);
		tblChiTietHoaDon.setColorFilasBackgound1(Color.WHITE);
		tblChiTietHoaDon.setColorBordeHead(Color.RED);
		tblChiTietHoaDon.setColorBordeFilas(Color.RED);
		tblChiTietHoaDon.setColorBackgoundHead(Color.RED);
		tblChiTietHoaDon.setBorder(new LineBorder(Color.RED));
		tblChiTietHoaDon.setAltoHead(30);
		tblChiTietHoaDon.setBounds(0, 0, 793, 1);
		scrChiTietHoaDon.setViewportView(tblChiTietHoaDon);

		pnDoiTra = new JPanel();
		pnDoiTra.setBackground(Color.WHITE);
		pnDoiTra.setVisible(false);
		pnDoiTra.setBounds(5, 86, 1225, 567);
		pnLapHD.add(pnDoiTra);
		pnDoiTra.setLayout(null);

		scrHoaDonDoiTra = new JScrollPane();
		scrHoaDonDoiTra.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Ho\u00E1 \u0110\u01A1n \u0110\u1ED5i Tr\u1EA3", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		scrHoaDonDoiTra.setOpaque(false);
		scrHoaDonDoiTra.setBounds(0, 0, 801, 567);
		pnDoiTra.add(scrHoaDonDoiTra);

		tblHoaDonDoiTra = new RSTableMetro();
		tblHoaDonDoiTra.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tblHoaDonDoiTra.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tblHoaDonDoiTra.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tblHoaDonDoiTra.setModel(dfmHoaDonDoiTra = new DefaultTableModel(new String[] {
				"M\u00E3 Ho\u00E1 \u0110\u01A1n", "M\u00E3 Kh\u00E1ch H\u00E0ng", "M\u00E3 Nh\u00E2n Vi\u00EAn",
				"\u0110i\u1EA1 ch\u1EC9", "Ng\u00E0y Giao", "X\u00E1c Nh\u1EADn" }, 0) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblHoaDonDoiTra.getColumnModel().getColumn(5).setPreferredWidth(60);
		tblHoaDonDoiTra.setFocusable(false);
		tblHoaDonDoiTra.setColorBackgoundHead(Color.RED);
		tblHoaDonDoiTra.setAltoHead(30);

		scrHoaDonDoiTra.setViewportView(tblHoaDonDoiTra);
		tblHoaDonDoiTra.setGrosorBordeFilas(0);
		tblHoaDonDoiTra.setRowHeight(25);
		tblHoaDonDoiTra.setRowMargin(1);
		tblHoaDonDoiTra.setColorBordeHead(Color.RED);
		tblHoaDonDoiTra.setColorBordeFilas(Color.RED);
		tblHoaDonDoiTra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblHoaDonDoiTra.setGridColor(Color.RED);
		tblHoaDonDoiTra.setColorFilasForeground2(Color.BLACK);
		tblHoaDonDoiTra.setColorFilasForeground1(Color.BLACK);
		tblHoaDonDoiTra.setColorSelBackgound(new Color(255, 99, 71));
		tblHoaDonDoiTra.setColorBackgoundHead(Color.RED);
		tblHoaDonDoiTra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHoaDonDoiTra.setColorFilasBackgound1(Color.WHITE);
		tblHoaDonDoiTra.setColorFilasBackgound2(Color.WHITE);
		tblHoaDonDoiTra.setAltoHead(30);

		JScrollPane scrChiTietHoaDonDoiTra = new JScrollPane();
		scrChiTietHoaDonDoiTra.setOpaque(false);
		scrChiTietHoaDonDoiTra.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrChiTietHoaDonDoiTra.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Danh S\u00E1ch Chi Ti\u1EBFt Ho\u00E1 \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		scrChiTietHoaDonDoiTra.setBounds(806, 0, 419, 567);
		pnDoiTra.add(scrChiTietHoaDonDoiTra);

		tblChiTietHoaDonDoiTra = new RSTableMetro();
		tblChiTietHoaDonDoiTra
				.setModel(dfmChiTietHoaDonDoiTra = new DefaultTableModel(new String[] { "M\u00E3 S\u1EA3n Ph\u1EA9m",
						"S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n Gi\u00E1", "Ho\u00E0n Ph\u1EA7n Tr\u0103m" }, 0) {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tblChiTietHoaDonDoiTra.getColumnModel().getColumn(2).setPreferredWidth(55);
		tblChiTietHoaDonDoiTra.getColumnModel().getColumn(3).setPreferredWidth(96);

		tblChiTietHoaDonDoiTra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblChiTietHoaDonDoiTra.setRowMargin(1);
		tblChiTietHoaDonDoiTra.setRowHeight(25);
		tblChiTietHoaDonDoiTra.setGrosorBordeFilas(0);
		tblChiTietHoaDonDoiTra.setGridColor(Color.RED);
		tblChiTietHoaDonDoiTra.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tblChiTietHoaDonDoiTra.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tblChiTietHoaDonDoiTra.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tblChiTietHoaDonDoiTra.setFocusable(false);
		tblChiTietHoaDonDoiTra.setColorSelBackgound(new Color(255, 99, 71));
		tblChiTietHoaDonDoiTra.setColorFilasForeground2(Color.BLACK);
		tblChiTietHoaDonDoiTra.setColorFilasForeground1(Color.BLACK);
		tblChiTietHoaDonDoiTra.setColorFilasBackgound2(Color.WHITE);
		tblChiTietHoaDonDoiTra.setColorFilasBackgound1(Color.WHITE);
		tblChiTietHoaDonDoiTra.setColorBordeHead(Color.RED);
		tblChiTietHoaDonDoiTra.setColorBordeFilas(Color.RED);
		tblChiTietHoaDonDoiTra.setColorBackgoundHead(Color.RED);
		tblChiTietHoaDonDoiTra.setBorder(new LineBorder(Color.RED));
		tblChiTietHoaDonDoiTra.setAltoHead(30);
		tblChiTietHoaDonDoiTra.setBounds(0, 0, 793, 1);
		scrChiTietHoaDonDoiTra.setViewportView(tblChiTietHoaDonDoiTra);

		lstTieuChi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					lblTieuChi.setText(lstTieuChi.getSelectedValue());
					lstTieuChi.setVisible(false);
				}
			}
		});
		lstTieuChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lstTieuChi.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lstTieuChi.setVisible(true);
			}
		});

		tblHoaDon.addMouseListener(this);
		tblHoaDonDoiTra.addMouseListener(this);

		btnTatCa.addActionListener(this);
		btnChoXacNhan.addActionListener(this);
		btnChoLayHang.addActionListener(this);
		btnXacNhanGiao.addActionListener(this);
		btnDaGiaoHang.addActionListener(this);
		btnDoiTra.addActionListener(this);
		tblHoaDon.addPropertyChangeListener(this);
		tblHoaDonDoiTra.addPropertyChangeListener(this);

		return pnLapHD;
	}

	public static void main(String[] args) {
		new Gui_NhanVien_XacNhanHD(new NhanVien("NV2202", "Phạm Thanh Sơn", "0346676956",
				"img/AvtUser/NhanVien/NV2201.jpg", "sonpham28052002@gmail.com", "Nhân Viên")).setVisible(true);
	}

	public void timSanPham(String ma) {
		boolean flag = false;
		for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
			if (ma.trim().equals((tblHoaDon.getValueAt(i, lstTieuChi.getSelectedIndex()) + "").trim())) {
				flag = true;
				if (lstTieuChi.getSelectedIndex() == 0) {
					tblHoaDon.changeSelection(i, 7, false, false);

				} else {
					dfmHoaDon.setRowCount(0);
					dfmChiTietHoaDon.setRowCount(0);
					if (thaoThac.equals("Đã Giao")) {
						for (HoaDon j : new Dao_HoaDon().getDsHoaDonDatHang(nv.getMaNV().trim())) {
							Object[] x = { j.getMaHD(), j.getMaNV(), j.getMaKH(), j.getNgayLap(), j.getNgayLayHang(),
									j.getNgayGiaoHang(), j.getTrangThai() };
							dfmHoaDon.addRow(x);
						}
					} else if (thaoThac.equals("Tất Cả")) {
						if (lstTieuChi.getSelectedIndex() == 1) {
							for (HoaDon j : new Dao_HoaDon().getDsHoaDonDatHangChuaGiao(nv.getMaNV().trim())) {
								if (!(j.getMaNV() + "").equals("null")) {
									if (j.getMaNV().trim().equals(ma.trim())) {
										Object[] x = { j.getMaHD(), j.getMaNV(), j.getMaKH(), j.getNgayLap(),
												j.getNgayLayHang(), j.getNgayGiaoHang(), j.getTrangThai(), false };
										dfmHoaDon.addRow(x);
									}
								}

							}
						} else if (lstTieuChi.getSelectedIndex() == 2) {
							for (HoaDon j : new Dao_HoaDon().getDsHoaDonDatHangChuaGiao(nv.getMaNV().trim())) {
								if (j.getMaKH().trim().equals(ma.trim())) {
									Object[] x = { j.getMaHD(), j.getMaNV(), j.getMaKH(), j.getNgayLap(),
											j.getNgayLayHang(), j.getNgayGiaoHang(), j.getTrangThai(), false };
									dfmHoaDon.addRow(x);
								}
							}
						}

					} else {
						if (lstTieuChi.getSelectedIndex() == 1) {
							for (HoaDon j : new Dao_HoaDon().getDsHoaDonTheoTrangThai(thaoThac, nv.getMaNV().trim())) {
								if (j.getMaNV().trim().equals(ma.trim())) {
									Object[] x = { j.getMaHD(), j.getMaNV(), j.getMaKH(), j.getNgayLap(),
											j.getNgayLayHang(), j.getNgayGiaoHang(), j.getTrangThai(), false };
									dfmHoaDon.addRow(x);
								}
							}
						} else if (lstTieuChi.getSelectedIndex() == 2) {
							for (HoaDon j : new Dao_HoaDon().getDsHoaDonTheoTrangThai(thaoThac, nv.getMaNV().trim())) {
								if (j.getMaKH().trim().equals(ma.trim())) {
									Object[] x = { j.getMaHD(), j.getMaNV(), j.getMaKH(), j.getNgayLap(),
											j.getNgayLayHang(), j.getNgayGiaoHang(), j.getTrangThai(), false };
									dfmHoaDon.addRow(x);
								}
							}
						}
					}
				}
				break;
			}
		}
		if (!flag) {
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(tblHoaDon)) {
			if (tblHoaDon.getSelectedRow() != -1) {
				dfmChiTietHoaDon.setRowCount(0);
				for (ChiTietHoaDon i : new Dao_ChiTietHoaDon()
						.getDsChiTietHoaDon(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0) + "")) {
					Object[] x = { i.getMaSP(), i.getSoLuong(), formatter.format(i.getGiaBan()) + " VNĐ",
							formatter.format(i.getSoLuong() * i.getGiaBan()) + " VNĐ" };
					dfmChiTietHoaDon.addRow(x);
				}
			}
		} else if (e.getSource().equals(tblHoaDonDoiTra)) {
			if (tblHoaDonDoiTra.getSelectedRow() != -1) {
				dfmChiTietHoaDonDoiTra.setRowCount(0);
				for (ChiTietHoaDonDoiTra i : new DAO_ChiTietHoaDonDoiTra()
						.getCTHDTheoMa(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(), 0) + "")) {
					Object[] x = { i.getMaSP(), i.getSoLuong(), formatter.format(i.getGiaBan()) + " VNĐ",
							i.getPhiDoiTra() + "%" };
					dfmChiTietHoaDonDoiTra.addRow(x);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnTatCa)) {

			scrHoaDon.setVisible(true);
			scrChiTietHoaDon.setVisible(true);
			pnDoiTra.setVisible(false);

			if (thaoThac.equals("Đã Giao")) {
				tblHoaDon.addColumn(column);
			}
			thaoThac = "Tất Cả";
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
			for (HoaDon i : new Dao_HoaDon().getDsHoaDonDatHangChuaGiao(nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
						i.getNgayGiaoHang(), i.getTrangThai(), false };
				dfmHoaDon.addRow(x);
			}
		} else if (e.getSource().equals(btnChoXacNhan)) {

			scrHoaDon.setVisible(true);
			scrChiTietHoaDon.setVisible(true);
			pnDoiTra.setVisible(false);

			if (thaoThac.equals("Đã Giao")) {
				tblHoaDon.addColumn(column);
			}
			thaoThac = "Chờ Xác Nhận";
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
			for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai("Chờ Xác Nhận", nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
						i.getNgayGiaoHang(), i.getTrangThai(), false };
				dfmHoaDon.addRow(x);
			}
		} else if (e.getSource().equals(btnChoLayHang)) {

			scrHoaDon.setVisible(true);
			scrChiTietHoaDon.setVisible(true);
			pnDoiTra.setVisible(false);

			if (thaoThac.equals("Đã Giao")) {
				tblHoaDon.addColumn(column);
			}
			thaoThac = "Chờ Lấy Hàng";
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
			for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai("Chờ Lấy Hàng", nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
						i.getNgayGiaoHang(), i.getTrangThai(), false };
				dfmHoaDon.addRow(x);
			}
		} else if (e.getSource().equals(btnXacNhanGiao)) {

			scrHoaDon.setVisible(true);
			scrChiTietHoaDon.setVisible(true);
			pnDoiTra.setVisible(false);

			if (thaoThac.equals("Đã Giao")) {
				tblHoaDon.addColumn(column);
			}
			thaoThac = "Chờ Giao";
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
			for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai("Chờ Giao", nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
						i.getNgayGiaoHang(), i.getTrangThai(), false };
				dfmHoaDon.addRow(x);
			}
		} else if (e.getSource().equals(btnDaGiaoHang)) {

			scrHoaDon.setVisible(true);
			scrChiTietHoaDon.setVisible(true);
			pnDoiTra.setVisible(false);

			if (!thaoThac.equals("Đã Giao")) {
				column = tblHoaDon.getColumn("Xác Nhận");
				tblHoaDon.removeColumn(tblHoaDon.getColumn("Xác Nhận"));
			}
			thaoThac = "Đã Giao";
			dfmHoaDon.setRowCount(0);
			dfmChiTietHoaDon.setRowCount(0);
			for (HoaDon i : new Dao_HoaDon().getDsHoaDonDatHang(nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(), i.getNgayLayHang(),
						i.getNgayGiaoHang(), i.getTrangThai() };
				dfmHoaDon.addRow(x);
			}
		} else if (e.getSource().equals(btnDoiTra)) {
			scrHoaDon.setVisible(false);
			scrChiTietHoaDon.setVisible(false);
			pnDoiTra.setVisible(true);

			dfmChiTietHoaDonDoiTra.setRowCount(0);
			dfmHoaDonDoiTra.setRowCount(0);
			for (HoaDonDoiTra i : new DAO_HoaDonDoiTra().getAll(nv.getMaNV().trim())) {
				Object[] x = { i.getMaHD(), i.getMaKh(), i.getMaNV(), i.getDiaChi(), i.getNgayGiao(), false };
				dfmHoaDonDoiTra.addRow(x);
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
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().equals(tblHoaDon)) {
			if (("tableCellEditor".equals(evt.getPropertyName()))) {
				if (!tblHoaDon.isEditing()) {
					if (tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 7).toString().equals("true")) {
						if ((tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 3) + "").equals("null")) {

							if (JOptionPane.showConfirmDialog(null, "Bạn Chắc Chắn Muốn Xác Nhận Đơn Hàng Này ?",
									"Yêu Cầu Xác Nhận", JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
								HoaDon hd = new Dao_HoaDon()
										.timKiemHoaDon(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0) + "");
								hd.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
								hd.setNgayLayHang(null);
								hd.setNgayGiaoHang(null);
								hd.setTrangThai("Chờ Lấy Hàng");
								hd.setMaNV(nv.getMaNV());
								if (new Dao_HoaDon().updateNgay(hd)) {

									if (thaoThac.equals("Tất Cả")) {
										tblHoaDon.setValueAt(hd.getMaNV(), tblHoaDon.getSelectedRow(), 1);
										tblHoaDon.setValueAt(hd.getNgayLap(), tblHoaDon.getSelectedRow(), 3);
										tblHoaDon.setValueAt(hd.getTrangThai(), tblHoaDon.getSelectedRow(), 6);
										tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
									} else {
										tblHoaDon.clearSelection();
										dfmHoaDon.setRowCount(0);
										dfmChiTietHoaDon.setRowCount(0);
										for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai(thaoThac,
												nv.getMaNV().trim())) {
											Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(),
													i.getNgayLayHang(), i.getNgayGiaoHang(), i.getTrangThai(), false };
											dfmHoaDon.addRow(x);
										}
									}
									JOptionPane.showMessageDialog(null,
											"     Xác Nhận Đơn Hàng Thành Công. \n Đơn Hàng Chuyển Sang Giai Đoạn Lấy Hàng.");
								}
							} else {
								tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
							}

						} else if ((tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 4) + "").equals("null")) {
							if (JOptionPane.showConfirmDialog(null, "Bạn Chắc Chắn Muốn Xác Nhận?", "Yêu Cầu Xác Nhận",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
								HoaDon hd = new Dao_HoaDon()
										.timKiemHoaDon(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0) + "");
								hd.setNgayLayHang(new java.sql.Date(System.currentTimeMillis()));
								hd.setNgayGiaoHang(null);
								hd.setTrangThai("Chờ Giao");
								hd.setMaNV(nv.getMaNV());
								if (new Dao_HoaDon().updateNgay(hd)) {

									if (thaoThac.equals("Tất Cả")) {
										tblHoaDon.setValueAt(hd.getNgayLayHang(), tblHoaDon.getSelectedRow(), 4);
										tblHoaDon.setValueAt(hd.getTrangThai(), tblHoaDon.getSelectedRow(), 6);
										tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
									} else {
										tblHoaDon.clearSelection();
										dfmHoaDon.setRowCount(0);
										dfmChiTietHoaDon.setRowCount(0);
										for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai(thaoThac,
												nv.getMaNV().trim())) {
											Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(),
													i.getNgayLayHang(), i.getNgayGiaoHang(), i.getTrangThai(), false };
											dfmHoaDon.addRow(x);
										}
									}
									JOptionPane.showMessageDialog(null,
											"     Xác Nhận Đơn Hàng Thành Công. \n Đơn Hàng Chuyển Sang Giai Đoạn Giao Hàng.");
								}
							} else {
								tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
							}

						} else if ((tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 5) + "").equals("null")) {
							if (JOptionPane.showConfirmDialog(null, "Bạn Chắc Chắn Muốn Xác Nhận ?", "Yêu Cầu Xác Nhận",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
								HoaDon hd = new Dao_HoaDon()
										.timKiemHoaDon(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0) + "");
								hd.setNgayGiaoHang(new java.sql.Date(System.currentTimeMillis()));
								hd.setTrangThai("Đã Giao");
								hd.setMaNV(nv.getMaNV());
								if (new Dao_HoaDon().updateNgay(hd)) {

									if (thaoThac.equals("Tất Cả")) {
										tblHoaDon.setValueAt(hd.getNgayGiaoHang(), tblHoaDon.getSelectedRow(), 5);
										tblHoaDon.setValueAt(hd.getTrangThai(), tblHoaDon.getSelectedRow(), 6);
										tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
									} else {
										tblHoaDon.clearSelection();
										dfmHoaDon.setRowCount(0);
										dfmChiTietHoaDon.setRowCount(0);
										for (HoaDon i : new Dao_HoaDon().getDsHoaDonTheoTrangThai(thaoThac,
												nv.getMaNV().trim())) {
											Object[] x = { i.getMaHD(), i.getMaNV(), i.getMaKH(), i.getNgayLap(),
													i.getNgayLayHang(), i.getNgayGiaoHang(), i.getTrangThai(), false };
											dfmHoaDon.addRow(x);
										}
									}
									JOptionPane.showMessageDialog(null,
											"     Xác Nhận Đơn Hàng Giao Thành Thành Công.");
								}
							} else {
								tblHoaDon.setValueAt(false, tblHoaDon.getSelectedRow(), 7);
							}
						}
					}
				}
			}
		} else if (evt.getSource().equals(tblHoaDonDoiTra)) {
			if (("tableCellEditor".equals(evt.getPropertyName()))) {
				if (!tblHoaDonDoiTra.isEditing()) {
					if (tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(), 5).toString().equals("true")) {
						if (JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốc Xác Nhận Đổi Trả Cho Đơn Hàng?","",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
							HoaDon hoaDon = new HoaDon();
							hoaDon.setMaHD(taoMaHD());
							hoaDon.setMaKH(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),1)+"");
							hoaDon.setMaNV(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),2)+"");
							hoaDon.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
							hoaDon.setDiaChi(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),3)+"");
							hoaDon.setTrangThai("Chờ Lấy Hàng");
							boolean flag = true;
							for (ChiTietHoaDonDoiTra i :new DAO_ChiTietHoaDonDoiTra().getCTHDTheoMa(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),0)+"")) {
								if (new Dao_SanPham().timKiemSanPham(i.getMaSP().trim()).getSoLuongTon()-i.getSoLuong()<0) {
									System.out.println(new Dao_SanPham().timKiemSanPham(i.getMaSP().trim()));
									System.out.println(new Dao_SanPham().timKiemSanPham(i.getMaSP().trim()).getSoLuongTon());
									flag=false;
									break;
								}
							}
							if (flag) {
								if (new Dao_HoaDon().insertHoaDonKhachHangDoiTra(hoaDon)) {
									for (ChiTietHoaDonDoiTra i : new DAO_ChiTietHoaDonDoiTra().getCTHDTheoMa(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),0)+"")) {
										new Dao_ChiTietHoaDon().deleteChiTietHoaDon(new Dao_ChiTietHoaDon().timKiemChiTietHoaDon(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),0)+"", i.getMaSP()));
										new Dao_ChiTietHoaDon().insertChiTietHoaDon(new ChiTietHoaDon(i.getMaSP(), hoaDon.getMaHD(),i.getSoLuong(), i.getGiaBan()* ((float)i.getPhiDoiTra()/100)));
									}
									if (new DAO_HoaDonDoiTra().deleteHoaDonDoiTra(tblHoaDonDoiTra.getValueAt(tblHoaDonDoiTra.getSelectedRow(),0)+"")) {
										dfmChiTietHoaDonDoiTra.setRowCount(0);
										dfmHoaDonDoiTra.setRowCount(0);
										for (HoaDonDoiTra i : new DAO_HoaDonDoiTra().getAll(nv.getMaNV().trim())) {
											Object[] x = { i.getMaHD(), i.getMaKh(), i.getMaNV(), i.getDiaChi(), i.getNgayGiao(), false };
											dfmHoaDonDoiTra.addRow(x);
										}
								
										JOptionPane.showMessageDialog(null, "Đơn Hàng Đã Được Tạo.");
									}
								}
							}else {
								JOptionPane.showMessageDialog(null, "Không Đủ Số Lượng Sản Phẩm.");
							}
						}else {
							tblHoaDonDoiTra.setValueAt(false, tblHoaDonDoiTra.getSelectedRow(), 5);
						}
					}
				}
			}
		}
	}
}
