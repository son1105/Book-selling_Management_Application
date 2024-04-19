package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import dao.DAO_LoaiSanPham;
import dao.DAO_NhaCungCap;
import dao.Dao_SanPham;
import dao.ImportFileProduct;
import entity.LoaiSanPhan;
import entity.NhaCungCap;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;
import rojerusan.RSTableMetro;

public class FormNhapFileSanPham extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel dfmNCC;
	private static DefaultTableModel dfmLoaiSp;
	private static DefaultTableModel dfmSanPham;
	private static DecimalFormat formatter = new DecimalFormat("###,###,###");
	private RSTableMetro tblLoaiSP;
	private RSTableMetro tblNcc;
	private RSTableMetro tblSanPham;
	private static List<NhaCungCap> dsNCC;
	private static List<LoaiSanPhan> loaiSanPhan;
	private static List<SanPham> dsSanPham;
	private JButton btnThemSanPham;
	private JButton btnThemLoai;
	private JButton btnThemNCC;

	public FormNhapFileSanPham() {
		setSize(1258, 719);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(conTrolNhapFile());

	}

	public static void loadDaTa(String Path) {
		dfmLoaiSp.setRowCount(0);
		dfmNCC.setRowCount(0);
		dfmSanPham.setRowCount(0);
		try {
			dsNCC = new ImportFileProduct().getDanhNhaCungExcel(Path);
			loaiSanPhan = new ImportFileProduct().getDsLoaiExcel(Path);
			dsSanPham = new ImportFileProduct().getDsSanPhamExcel(Path);
		} catch (IOException e) {
		}
		loadNcc(dsNCC);
		loadLoaiSp(loaiSanPhan);
		loadSanPhan(dsSanPham);
	}

	public Component conTrolNhapFile() {

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 1237, 664);
		panel.setLayout(null);

		JScrollPane scrDsSanPham = new JScrollPane();
		scrDsSanPham.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		scrDsSanPham.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh S\u00E1ch S\u1EA3n Ph\u1EA9m M\u1EDBi",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		scrDsSanPham.setBounds(10, 320, 1217, 333);
		panel.add(scrDsSanPham);

		tblSanPham = new RSTableMetro();
		tblSanPham.setFuenteFilas(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblSanPham.setFuenteFilasSelect(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblSanPham.setFuenteHead(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		tblSanPham.setModel(dfmSanPham = new DefaultTableModel(new String[] { "M\u00E3 Sản Phẩm", "T\u00EAn Sản Phẩm",
				"Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "Nh\u00E0 Cung C\u1EA5p", "S\u1ED1 L\u01B0\u1EE3ng",
				"Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "", "" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Class<?> getColumnClass(int column) {
				return (column == 7 || column == 8) ? Icon.class : Object.class;
			}
		});
		tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(175);
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblSanPham.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblSanPham.getColumnModel().getColumn(7).setPreferredWidth(0);
		tblSanPham.getColumnModel().getColumn(8).setPreferredWidth(0);
		tblSanPham.setGrosorBordeFilas(0);
		tblSanPham.setRowHeight(25);
		tblSanPham.setRowMargin(1);
		tblSanPham.setColorBordeHead(Color.RED);
		tblSanPham.setColorBordeFilas(Color.RED);
		tblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblSanPham.setGridColor(Color.RED);

		tblSanPham.setColorFilasForeground2(Color.BLACK);
		tblSanPham.setColorFilasForeground1(Color.BLACK);
		tblSanPham.setColorSelBackgound(new Color(255, 99, 71));
		tblSanPham.setColorBackgoundHead(Color.RED);
		tblSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSanPham.setColorFilasBackgound1(Color.WHITE);
		tblSanPham.setColorFilasBackgound2(Color.WHITE);
		tblSanPham.setAltoHead(30);
		scrDsSanPham.setViewportView(tblSanPham);

		JScrollPane scrNCC = new JScrollPane();
		scrNCC.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Danh S\u00E1ch Nh\u00E0 Cung C\u1EA5p M\u1EDBi", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.RED));
		scrNCC.setBounds(10, 65, 742, 250);
		panel.add(scrNCC);

		tblNcc = new RSTableMetro();
		tblNcc.setModel(dfmNCC = new DefaultTableModel(new String[] { "M\u00E3 ", "T\u00EAn Nh\u00E0 Cung C\u1EA5p",
				"S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "", "" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Class<?> getColumnClass(int column) {
				return (column == 4 || column == 5) ? Icon.class : Object.class;
			}
		});

		tblNcc.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblNcc.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblNcc.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblNcc.getColumnModel().getColumn(3).setPreferredWidth(250);

		tblNcc.setFocusable(false);
		tblNcc.getColumnModel().getColumn(4).setPreferredWidth(30);
		tblNcc.getColumnModel().getColumn(5).setPreferredWidth(30);
		tblNcc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNcc.setRowMargin(1);
		tblNcc.setRowHeight(25);
		tblNcc.setGrosorBordeFilas(0);
		tblNcc.setGridColor(Color.RED);
		tblNcc.setFuenteHead(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		tblNcc.setFuenteFilasSelect(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblNcc.setFuenteFilas(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblNcc.setColorSelBackgound(new Color(255, 99, 71));
		tblNcc.setColorFilasForeground2(Color.BLACK);
		tblNcc.setColorFilasForeground1(Color.BLACK);
		tblNcc.setColorFilasBackgound2(Color.WHITE);
		tblNcc.setColorFilasBackgound1(Color.WHITE);
		tblNcc.setColorBordeHead(Color.RED);
		tblNcc.setColorBordeFilas(Color.RED);
		tblNcc.setColorBackgoundHead(Color.RED);
		tblNcc.setAltoHead(30);
		tblNcc.setBounds(0, 0, 1215, 1);
		scrNCC.setViewportView(tblNcc);

		JScrollPane scrLoaiSP = new JScrollPane();
		scrLoaiSP.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Danh S\u00E1ch Lo\u1EA1i S\u1EA3n Ph\u1EA9m M\u1EDBi", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		scrLoaiSP.setBounds(762, 65, 465, 250);
		panel.add(scrLoaiSP);

		tblLoaiSP = new RSTableMetro();
		tblLoaiSP.setModel(dfmLoaiSp = new DefaultTableModel(new String[] { "M\u00E3 Lo\u1EA1i S\u1EA3n Ph\u1EA9m",
				"T\u00EAn Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "", "" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Class<?> getColumnClass(int column) {
				return (column == 2 || column == 3) ? Icon.class : Object.class;
			}
		});

		tblLoaiSP.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblLoaiSP.getColumnModel().getColumn(1).setPreferredWidth(175);

		tblLoaiSP.setFocusable(false);
		tblLoaiSP.getColumnModel().getColumn(2).setPreferredWidth(15);
		tblLoaiSP.getColumnModel().getColumn(3).setPreferredWidth(15);
		tblLoaiSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblLoaiSP.setRowMargin(1);
		tblLoaiSP.setRowHeight(25);
		tblLoaiSP.setGrosorBordeFilas(0);
		tblLoaiSP.setGridColor(Color.RED);
		tblLoaiSP.setFuenteHead(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		tblLoaiSP.setFuenteFilasSelect(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblLoaiSP.setFuenteFilas(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		tblLoaiSP.setColorSelBackgound(new Color(255, 99, 71));
		tblLoaiSP.setColorFilasForeground2(Color.BLACK);
		tblLoaiSP.setColorFilasForeground1(Color.BLACK);
		tblLoaiSP.setColorFilasBackgound2(Color.WHITE);
		tblLoaiSP.setColorFilasBackgound1(Color.WHITE);
		tblLoaiSP.setColorBordeHead(Color.RED);
		tblLoaiSP.setColorBordeFilas(Color.RED);
		tblLoaiSP.setColorBackgoundHead(Color.RED);
		tblLoaiSP.setAltoHead(30);
		tblLoaiSP.setBounds(0, 0, 730, 25);
		scrLoaiSP.setViewportView(tblLoaiSP);

		JLabel lblBack = new JLabel("Quay Lại");
		lblBack.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\back.png"));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui_NhanVien_QLSP.chuyenTrang(false);
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBack.setBounds(10, 11, 102, 32);
		panel.add(lblBack);

		JPanel pnTacVu = new JPanel();
		pnTacVu.setBorder(new LineBorder(Color.RED));
		pnTacVu.setBounds(150, 11, 600, 48);
		panel.add(pnTacVu);
		pnTacVu.setLayout(null);

		btnThemSanPham = new JButton("Thêm Sản Phẩm");
		btnThemSanPham.setToolTipText(
				"Có Sản Phẩm Của Nhà Cung Cấp Mới Hoặc Loại Mới.\n Thêm Nhà Cung Cấp hoặc Loại Vào Trước.");
		btnThemSanPham.setEnabled(false);
		btnThemSanPham.setFocusable(false);
		btnThemSanPham.setBackground(new Color(245, 245, 220));
		btnThemSanPham.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThemSanPham.setBounds(415, 7, 170, 30);

		pnTacVu.add(btnThemSanPham);

		btnThemLoai = new JButton("Thêm Loại Sản Phẩm");
		btnThemLoai.setFocusable(false);
		btnThemLoai.setBackground(new Color(245, 245, 220));
		btnThemLoai.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThemLoai.setBounds(215, 7, 170, 30);
		pnTacVu.add(btnThemLoai);

		btnThemNCC = new JButton("Thêm Nhà Cung Cấp");
		btnThemNCC.setFocusable(false);
		btnThemNCC.setBackground(new Color(245, 245, 220));
		btnThemNCC.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThemNCC.setBounds(15, 7, 170, 30);
		pnTacVu.add(btnThemNCC);

		tblLoaiSP.addMouseListener(this);
		tblSanPham.addMouseListener(this);
		tblNcc.addMouseListener(this);

		btnThemLoai.addActionListener(this);
		btnThemNCC.addActionListener(this);
		btnThemSanPham.addActionListener(this);
		return panel;
	}

	public static void main(String[] args) {
		new FormNhapFileSanPham().setVisible(true);
	}

	public static void loadNcc(List<NhaCungCap> ds) {
		for (NhaCungCap i : ds) {
			Object[] ncc = { i.getMaNCC(), i.getTenNCC(), i.getSdt(), i.getDiaChi(),
					new ImageIcon("img/IMG_LOGIN_SignIn/edit.png"),
					new ImageIcon("img/IMG_LOGIN_SignIn/delete_red.png") };
			dfmNCC.addRow(ncc);
		}
	}

	public static void loadLoaiSp(List<LoaiSanPhan> ds) {
		for (LoaiSanPhan i : ds) {
			Object[] loai = { i.getMaLoai(), i.getTenLoai(), new ImageIcon("img/IMG_LOGIN_SignIn/edit.png"),
					new ImageIcon("img/IMG_LOGIN_SignIn/delete_red.png") };
			dfmLoaiSp.addRow(loai);
		}
	}

	public static void loadSanPhan(List<SanPham> ds) {
		for (SanPham i : ds) {
			Object[] loai = { i.getMaSP(), i.getTenSP(), i.getMaLoai(), i.getMaNcc(), i.getSoLuongTon(),
					formatter.format(i.getGiaNhap()) + " VNĐ", formatter.format(i.getGiaBan()) + " VNĐ",
					new ImageIcon("img/IMG_LOGIN_SignIn/edit.png"),
					new ImageIcon("img/IMG_LOGIN_SignIn/delete_red.png") };
			dfmSanPham.addRow(loai);
		}
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

	public String timTenLoai(String maLoai) {
		List<LoaiSanPhan> ds = new DAO_LoaiSanPham().getDsloaiSp();
		ds.addAll(loaiSanPhan);
		for (LoaiSanPhan i : ds) {
			if (i.getMaLoai().trim().equals(maLoai.trim())) {
				return i.getTenLoai();
			}
		}
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tblSanPham)) {
			if (((JTable) e.getComponent()).getSelectedColumn() == 7) {
				new FormNhapThongTinSanPham("Sửa", dsSanPham.get(tblSanPham.getSelectedRow()));

			} else if (((JTable) e.getComponent()).getSelectedColumn() == 8) {
				if (JOptionPane.showConfirmDialog(null, "Bạn Muốn Xoá Sản Phẩm Này.", "Delete",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dsSanPham.remove(tblSanPham.getSelectedRow());
					dfmSanPham.removeRow(tblSanPham.getSelectedRow());
				}
			}
		} else if (e.getSource().equals(tblLoaiSP)) {
			if (((JTable) e.getComponent()).getSelectedColumn() == 2) {
				JPanel pnEditLoai = new JPanel();
				pnEditLoai.setBounds(31, 52, 320, 45);
				pnEditLoai.setPreferredSize(new Dimension(320, 45));
				getContentPane().add(pnEditLoai);
				pnEditLoai.setLayout(null);

				JLabel lblTenLoai = new JLabel("Tên Loại Sản Phẩm:");
				lblTenLoai.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTenLoai.setBounds(10, 10, 110, 25);
				pnEditLoai.add(lblTenLoai);

				JTextField txtTenLoai = new JTextField();
				txtTenLoai.setBounds(125, 10, 185, 25);
				pnEditLoai.add(txtTenLoai);
				txtTenLoai.setColumns(10);
				txtTenLoai.setText(tblLoaiSP.getValueAt(tblLoaiSP.getSelectedRow(), 1).toString());
				if (JOptionPane.showConfirmDialog(null, pnEditLoai, "Chỉnh Sửa Tên Loại Sản Phẩm.",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					loaiSanPhan.get(tblLoaiSP.getSelectedRow()).setTenLoai(txtTenLoai.getText());
					dfmLoaiSp.setValueAt(txtTenLoai.getText(), tblLoaiSP.getSelectedRow(), 1);
				}

			} else if (((JTable) e.getComponent()).getSelectedColumn() == 3) {
				if (JOptionPane.showConfirmDialog(null,
						"                Bạn Muốn Xoá Loại Sản Phẩm Này? \n Lưu Ý: Các Sản Phẩm Thuộc Loại Nãy Sẽ Xoá Theo.",
						"Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					for (int i = 0; i < tblSanPham.getRowCount(); i++) {

						if (tblSanPham.getValueAt(i, 2).toString().trim()
								.equals(loaiSanPhan.get(tblLoaiSP.getSelectedRow()).getMaLoai().trim())) {
							dsSanPham.remove(i);
							dfmSanPham.removeRow(i);
							i--;
						}
					}
					loaiSanPhan.remove(tblLoaiSP.getSelectedRow());
					dfmLoaiSp.removeRow(tblLoaiSP.getSelectedRow());
				}
			}
		} else if (e.getSource().equals(tblNcc)) {
			if (((JTable) e.getComponent()).getSelectedColumn() == 4) {

				JPanel pnEditNcc = new JPanel();
				pnEditNcc.setBounds(31, 52, 304, 120);
				pnEditNcc.setPreferredSize(new Dimension(304, 120));
				pnEditNcc.setLayout(null);

				JLabel lblTenNCC = new JLabel("Tên Nhà Cung Câp:");
				lblTenNCC.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTenNCC.setBounds(10, 10, 110, 25);
				pnEditNcc.add(lblTenNCC);

				JTextField txtTenNCC = new JTextField();
				txtTenNCC.setBounds(120, 10, 174, 25);
				pnEditNcc.add(txtTenNCC);
				txtTenNCC.setColumns(10);

				JTextField txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(120, 46, 174, 25);
				pnEditNcc.add(txtSDT);

				JLabel lblSDT = new JLabel("Số Điện Thoại:");
				lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblSDT.setBounds(10, 46, 110, 25);
				pnEditNcc.add(lblSDT);

				JTextField txtDiaChi = new JTextField();
				txtDiaChi.setColumns(10);
				txtDiaChi.setBounds(120, 82, 174, 25);
				pnEditNcc.add(txtDiaChi);

				JLabel lblDiaChi = new JLabel("Địa Chỉ:");
				lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblDiaChi.setBounds(10, 82, 110, 25);
				pnEditNcc.add(lblDiaChi);

				txtTenNCC.setText(tblNcc.getValueAt(tblNcc.getSelectedRow(), 1).toString());
				txtSDT.setText(tblNcc.getValueAt(tblNcc.getSelectedRow(), 2).toString());
				txtDiaChi.setText(tblNcc.getValueAt(tblNcc.getSelectedRow(), 3).toString());

				if (JOptionPane.showConfirmDialog(null, pnEditNcc, "Chỉnh Sửa Thông Tin Nhà Cung Cấp.",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					dsNCC.get(tblNcc.getSelectedRow()).setTenNCC(txtTenNCC.getText());
					dsNCC.get(tblNcc.getSelectedRow()).setSdt(txtSDT.getText());
					dsNCC.get(tblNcc.getSelectedRow()).setDiaChi(txtDiaChi.getText());

					dfmNCC.setValueAt(txtTenNCC.getText(), tblNcc.getSelectedRow(), 1);
					dfmNCC.setValueAt(txtSDT.getText(), tblNcc.getSelectedRow(), 2);
					dfmNCC.setValueAt(txtDiaChi.getText(), tblNcc.getSelectedRow(), 3);
				}

			} else if (((JTable) e.getComponent()).getSelectedColumn() == 5) {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn Muốn Xoá Nhà Cung Cấp Này? \n Lưu Ý: Các Sản Phẩm Thuộc Nhà Cung Cấp Nãy Sẽ Xoá Theo.",
						"Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					for (int i = 0; i < tblSanPham.getRowCount(); i++) {
						if (tblSanPham.getValueAt(i, 3).toString().trim()
								.equals(dsNCC.get(tblNcc.getSelectedRow()).getMaNCC().trim())) {
							dsSanPham.remove(i);
							dfmSanPham.removeRow(i);
							i--;
						}
					}
					dsNCC.remove(tblNcc.getSelectedRow());
					dfmNCC.removeRow(tblNcc.getSelectedRow());
				}
			}
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
		if (e.getSource().equals(btnThemNCC)) {
			for (NhaCungCap i : dsNCC) {
				new DAO_NhaCungCap().insertNcc(i);
			}
			dfmNCC.setRowCount(0);
			dsNCC.removeAll(dsNCC);
			JOptionPane.showMessageDialog(null, "Thêm Các Nhà Cung Cấp Mới Thành Công.");
			if (dsNCC.size() == 0 && loaiSanPhan.size() == 0) {
				btnThemSanPham.setEnabled(true);
			}

		} else if (e.getSource().equals(btnThemLoai)) {
			for (LoaiSanPhan i : loaiSanPhan) {
				new DAO_LoaiSanPham().insertLoaiSanPham(i);
			}
			dfmLoaiSp.setRowCount(0);
			loaiSanPhan.removeAll(loaiSanPhan);
			JOptionPane.showMessageDialog(null, "Thêm Các Loại Sản Phẩm Mới Thành Thành Công.");
			if (dsNCC.size() == 0 && loaiSanPhan.size() == 0) {
				btnThemSanPham.setEnabled(true);
			}
		} else if (e.getSource().equals(btnThemSanPham)) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Chọn thư mục hình ảnh sản phẩm: ");
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.showOpenDialog(null);
			boolean kiemtra = true;
			if (jfc.getSelectedFile()!=null) {
				String path = jfc.getSelectedFile().getAbsolutePath();
				for (SanPham i : dsSanPham) {
					System.out.println(path + "\\" + i.getHinhAnh());
					File lay = new File(path + "\\" + i.getHinhAnh());
					String x = "img\\SanPham\\";
					String phanLoai ="";
					if (i instanceof Sach) {
						x += "Sach";
						phanLoai="Sách";
					} else if (i instanceof VanPhongPham) {
						x += "VanPhongPham";
						phanLoai="Văn Phòng Phẩm";
					}
					x += "\\" + chuyenDoiKyTu(timTenLoai(i.getMaLoai()));
					File kt = new File(x);
					x += "\\" + i.getHinhAnh();
					File den = new File(x);
					if (!kt.exists()) {
						System.out.println("s");
						kt.mkdirs();
					}
					
					try {
						Files.copy(lay.toPath(), den.toPath());
						i.setHinhAnh(den.toPath().toString());
						kiemtra = new Dao_SanPham().insertSanPham(i, phanLoai);
						if (kiemtra == false) {
							JOptionPane.showMessageDialog(null, "Sản Phẩm"+i.getMaSP()+" Không Thể Thêm Vào.");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (kiemtra) {
					JOptionPane.showMessageDialog(null, "Thêm danh sách sản phẩm thành công");
				}
				dfmSanPham.setRowCount(0);
				Gui_NhanVien_QLSP.loatDataSp();
			}
			
		}
	}
}
