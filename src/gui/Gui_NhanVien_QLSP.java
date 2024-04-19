package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.Dao_SanPham;
import dao.Dao_TaiKhoan;
import entity.NhanVien;
import entity.SanPham;
import jnafilechooser.api.JnaFileChooser;
import rojerusan.RSTableMetro;

public class Gui_NhanVien_QLSP extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnMenu;
	private JLabel lblQLSP;
	private JLabel lblQLDH;
	private JLabel lblNcc;
	private JLabel lblLoaiSp;
	private JPanel pnChayAnimation;
	private JPanel pnQLDH;
	private JPanel pnNcc;
	private JPanel pnQLSP;
	private JPanel pnLoaiSp;
	private JPanel pnTTNV;
	private JLabel lblIconTTNV;
	private JScrollPane scrTtSp;
	private JPanel pnTacVu;
	private JButton btnThemSp;
	private JButton btnXoaSp;
	private JButton btnSuaTT;
	private JButton btnThemLoai;
	private JPanel pnTimKiem;
	private RSTableMetro tblTtSp;
	private JPanel pnPisition;
	private static DecimalFormat formatter = new DecimalFormat("###,###,###");

	private Dao_SanPham sanPham;

	private JPanel pnThongKe;
	private JLabel lblThongKe;
	private JPanel pnTieuChi;
	private JLabel lblTieuChi;
	private JList<String> lstTieuChi;
	private JScrollPane scrSuggetSearch;
	private DefaultListModel<String> almSuggestSearch;
	private JTextField txtSearch;
	private static JPanel  pnTTsp;
	private JPanel pnSp;
	private static JPanel pnNhapExcel;
	private static DefaultTableModel dfmSp;
	private NhanVien nv;

	public Gui_NhanVien_QLSP(NhanVien NV) {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		nv = NV;
		sanPham = new Dao_SanPham();

		
		getContentPane().add(QuanLySanPham());
		
		

	}

	public Component QuanLySanPham() {
		
		pnSp = new JPanel();
		pnSp.setBounds(0, 0, 1237, 664);
		pnSp.setLayout(null);
		
		
		pnTTsp = new JPanel();
		pnTTsp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTTsp.setBounds(0, 0, 1237, 664);
		pnTTsp.setLayout(null);
		pnSp.add(pnTTsp);
		tblTtSp = new RSTableMetro() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTtSp.setModel(dfmSp = new DefaultTableModel(new String[] { "M\u00E3 ", "T\u00EAn S\u1EA3n Ph\u1EA9m",
				"Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "Nh\u00E0 Cung C\u1EA5p", "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n",
				"Gi\u00E1 Nh\u1EADp", "Gi\u00E1 B\u00E1n", "Gi\u1EA3m Gi\u00E1(%)", "Kh\u1ED1i l\u01B0\u1EE3ng(gr)",
				"M\u00F4 T\u1EA3" }, 0));

		lstTieuChi = new JList<String>();
		lstTieuChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lstTieuChi.setVisible(false);
			}
		});

		lstTieuChi.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Theo Mã Sản Phẩm", "Theo Tên Sản Phẩm", "Theo Loại Sản Phẩm",
					"Theo Nhà Cung Cấp" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		lstTieuChi.setSelectedIndex(0);
		lstTieuChi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					lblTieuChi.setText(lstTieuChi.getSelectedValue());
					lstTieuChi.setVisible(false);
				}
			}
		});

		lstTieuChi.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstTieuChi.setBounds(15, 38, 130, 75);
		lstTieuChi.setVisible(false);
		pnTTsp.add(lstTieuChi);

		scrSuggetSearch = new JScrollPane();
		scrSuggetSearch.setBorder(new LineBorder(Color.BLACK));
		scrSuggetSearch.setVisible(false);
		scrSuggetSearch.setBounds(156, 40, 327, 164);
		pnTTsp.add(scrSuggetSearch);

		JList<String> lstSuggetSearch = new JList<String>();
		scrSuggetSearch.setViewportView(lstSuggetSearch);
		lstSuggetSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lstSuggetSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSuggetSearch.setBorder(null);
		lstSuggetSearch.setModel(almSuggestSearch = new DefaultListModel<String>());

		tblTtSp.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tblTtSp.setFocusable(false);
		tblTtSp.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tblTtSp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblTtSp.setColorSelBackgound(new Color(255, 69, 0));
		tblTtSp.setColorFilasForeground1(Color.BLACK);
		tblTtSp.setColorFilasForeground2(Color.BLACK);
		tblTtSp.setRowHeight(22);
		tblTtSp.setColorBackgoundHead(new Color(204, 0, 51));
		tblTtSp.setRowMargin(0);
		tblTtSp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTtSp.setColorBordeFilas(new Color(204, 0, 51));
		tblTtSp.setColorFilasBackgound2(Color.WHITE);
		tblTtSp.setIntercellSpacing(new Dimension(0, 0));
		tblTtSp.setColorBordeHead(new Color(204, 0, 51));
		tblTtSp.getTableHeader().setAlignmentY(CENTER_ALIGNMENT);
		tblTtSp.setAlignmentY(Component.TOP_ALIGNMENT);
		tblTtSp.setAlignmentX(Component.LEFT_ALIGNMENT);
		tblTtSp.setFuenteHead(new Font("Tahoma", Font.BOLD, 11));
		tblTtSp.setAltoHead(30);

		scrTtSp = new JScrollPane(tblTtSp);
		scrTtSp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTtSp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrTtSp.setBounds(5, 57, 1227, 607);

		pnTTsp.add(scrTtSp);

		pnTacVu = new JPanel();
		pnTacVu.setLayout(null);
		pnTacVu.setBorder(new LineBorder(new Color(255, 99, 71)));
		pnTacVu.setBounds(502, 5, 730, 46);
		pnTTsp.add(pnTacVu);

		btnThemSp = new JButton("Thêm Sản Phẩm");
		btnThemSp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemSp.setFocusable(false);
		btnThemSp.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\add_product.png"));
		btnThemSp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemSp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThemSp.setBackground(new Color(255, 245, 238));
		btnThemSp.setBounds(10, 7, 170, 32);
		pnTacVu.add(btnThemSp);

		btnXoaSp = new JButton("Xoá Sản Phẩm");
		btnXoaSp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaSp.setFocusable(false);
		btnXoaSp.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\delete.png"));
		btnXoaSp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoaSp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnXoaSp.setBackground(new Color(255, 245, 238));
		btnXoaSp.setBounds(190, 7, 170, 32);
		pnTacVu.add(btnXoaSp);

		btnSuaTT = new JButton("Sửa Thông Tìn");
		btnSuaTT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSuaTT.setFocusable(false);
		btnSuaTT.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\edit.png"));
		btnSuaTT.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSuaTT.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSuaTT.setBackground(new Color(255, 245, 238));
		btnSuaTT.setBounds(370, 7, 170, 32);
		pnTacVu.add(btnSuaTT);

		btnThemLoai = new JButton("Thêm File Sản Phẩm");
		btnThemLoai.setIcon(new ImageIcon("D:\\JavaSwing\\DoAn_QuanLyBanSach\\img\\IMG_LOGIN_SignIn\\excel.png"));
		btnThemLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemLoai.setFocusable(false);
		btnThemLoai.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemLoai.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThemLoai.setBackground(new Color(255, 245, 238));
		btnThemLoai.setBounds(550, 7, 170, 32);
		pnTacVu.add(btnThemLoai);

		pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnTimKiem.setBounds(5, 5, 487, 46);
		pnTTsp.add(pnTimKiem);
		pnTimKiem.setLayout(null);
		pnTimKiem.add(conTroltimKiem());

		btnXoaSp.addActionListener(this);
		btnSuaTT.addActionListener(this);
		btnThemLoai.addActionListener(this);
		btnThemSp.addActionListener(this);

		tblTtSp.addMouseListener(this);

		btnSuaTT.addMouseListener(this);
		btnThemLoai.addMouseListener(this);
		for (SanPham i : new Dao_SanPham().getDsSanPhamFull()) {
			Object[] x = { i.getMaSP(), i.getTenSP(), i.getMaLoai(), i.getMaNcc(), i.getSoLuongTon() + "",
					formatter.format(i.getGiaNhap()) + " VNĐ", formatter.format(i.getGiaBan()) + " VNĐ",
					i.getGiamGia() + "%", i.getKhoiLuong() + "", i.getMoTa() };
			dfmSp.addRow(x);
		}

		lstSuggetSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timSanPham(lstSuggetSearch.getSelectedValue(), tblTtSp);
				txtSearch.transferFocus();
				txtSearch.setText(lstSuggetSearch.getSelectedValue());

				scrSuggetSearch.setVisible(false);

			}
		});

		pnNhapExcel = new JPanel();
		pnNhapExcel.setBounds(0, 0, 1237, 664);
		pnNhapExcel.setVisible(false);
		pnNhapExcel.add(new FormNhapFileSanPham().conTrolNhapFile());
		pnNhapExcel.setLayout(null);
		pnSp.add(pnNhapExcel);
		
		return pnSp;

	}

	public static void main(String[] args) {
		NhanVien x = new NhanVien("NV2201", "Phạm Thanh Sơn", "0346676956", "img/AvtUser/NhanVien/NV2201.jpg",
				"sonpham28052002@gmail.com", "Nhân Viên");
		new Gui_NhanVien_QLSP(x).setVisible(true);
	}

	public static void chuyenTrang(boolean a) {
		pnNhapExcel.setVisible(a);
		pnTTsp.setVisible(!a);
		
	}
	
	public Component conTroltimKiem() {
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
		pnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lstTieuChi.setVisible(false);
			}
		});
		pnSearch.setOpaque(false);
		pnSearch.setBounds(10, 7, 470, 30);
		pnSearch.setLayout(null);

		pnTieuChi = new JPanel();
		pnTieuChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstTieuChi.setVisible(true);
			}

		});
		pnTieuChi.setOpaque(false);
		pnTieuChi.setBounds(0, 2, 140, 25);
		pnSearch.add(pnTieuChi);
		pnTieuChi.setLayout(null);

		lblTieuChi = new JLabel("Theo Mã Sản Phẩm");
		lblTieuChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTieuChi.setBounds(5, 5, 115, 14);
		pnTieuChi.add(lblTieuChi);

		JLabel lblIconTieuChi = new JLabel("");
		lblIconTieuChi.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\bottom_down_Black.png"));
		lblIconTieuChi.setBounds(120, 5, 20, 15);
		pnTieuChi.add(lblIconTieuChi);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					timSanPham(txtSearch.getText().trim(), tblTtSp);
					txtSearch.transferFocus();
				}
			}

		});
		txtSearch.setForeground(Color.LIGHT_GRAY);
		txtSearch.setText("Nhập nội dung Tìm Kiếm...");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSearch.setOpaque(false);
		txtSearch.setBorder(null);
		txtSearch.setBounds(150, 2, 290, 25);
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
						&& lstTieuChi.getSelectedIndex() != 0) {
					scrSuggetSearch.setVisible(true);
					almSuggestSearch.removeAllElements();
					if (lstTieuChi.getSelectedIndex() == 1) {
						for (int i = 0; i < tblTtSp.getRowCount(); i++) {
							if (tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString().toUpperCase()
									.contains(txtSearch.getText().toUpperCase())) {
								almSuggestSearch
										.addElement(tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString());
							}
						}
					} else if (lstTieuChi.getSelectedIndex() == 2 || lstTieuChi.getSelectedIndex() == 3) {
						Set<String> s = new HashSet<String>();
						for (int i = 0; i < tblTtSp.getRowCount(); i++) {
							if (tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString().toUpperCase()
									.contains(txtSearch.getText().toUpperCase())) {
								if (!s.contains(tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString())) {
									s.add(tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString());
									almSuggestSearch.addElement(
											tblTtSp.getValueAt(i, lstTieuChi.getSelectedIndex()).toString());
								}
							}
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
		lblbtnSearch.setBounds(440, 2, 25, 25);
		pnSearch.add(lblbtnSearch);
		return pnSearch;
	}

	public void timSanPham(String ten, JTable tblSanPham) {
		
		for (int i = 0; i < tblSanPham.getRowCount(); i++) {
			if (lstTieuChi.getSelectedIndex() != -1 && lstTieuChi.getModel().getSize() <= tblSanPham.getColumnCount()) {
				if (tblSanPham.getValueAt(i, lstTieuChi.getSelectedIndex()).toString().trim().equals(ten.trim())) {
					System.out.println(ten);
					tblSanPham.changeSelection(i, i, false, false);
					break;
				}
			}
		}
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
							Thread.sleep(3);
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
							Thread.sleep(3);
						}

					} catch (Exception e2) {
						e2.getMessage();
					}
				};
			};
			th.start();

		}
	}

	public SanPham timSanPham(String maSp, List<SanPham> ds) {
		for (SanPham i : ds) {
			if (i.getMaSP().equals(maSp)) {
				return i;
			}
		}
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tblTtSp)) {
			if (e.getClickCount() == 2) {
				for (SanPham sanPham : new Dao_SanPham().getDsSanPhamFull()) {
					if (sanPham.getMaSP().trim()
							.equals(tblTtSp.getValueAt(tblTtSp.getSelectedRow(), 0).toString().trim())) {
						new Card_Product(sanPham, new Dao_TaiKhoan().timKiemTaiKhoan(nv.getMaNV().trim(), null)).setVisible(true);
					}
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

	public static void loatDataSp() {
		dfmSp.setRowCount(0);
		for (SanPham i : new Dao_SanPham().getDsSanPhamFull()) {
			Object[] x = { i.getMaSP(), i.getTenSP(), i.getMaLoai(), i.getMaNcc(), i.getSoLuongTon() + "",
					formatter.format(i.getGiaNhap()) + " VNĐ", formatter.format(i.getGiaBan()) + " VNĐ",
					i.getGiamGia() + "%", i.getKhoiLuong() + "", i.getMoTa() };
			dfmSp.addRow(x);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnThemSp)) {
			new FormNhapThongTinSanPham("Thêm");
		} else if (e.getSource().equals(btnXoaSp)) {
			if (tblTtSp.getSelectedRow() != -1) {
				if (JOptionPane.showConfirmDialog(null,
						"Bạn Chắc Chắc Muốn xoá Sản phẩm " + tblTtSp.getValueAt(tblTtSp.getSelectedRow(), 0).toString(),
						"", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					if (sanPham.deleteSanPham(tblTtSp.getValueAt(tblTtSp.getSelectedRow(), 0).toString())) {
						loatDataSp();
						JOptionPane.showMessageDialog(null, "Xoá Sản Phẩm Thành Công.");
					} else {
						JOptionPane.showMessageDialog(null, "Xoá Sản Phẩm Thất Bại.");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn Cần Chọn Sản Phẩm Muốn Xoá");
			}

		} else if (e.getSource().equals(btnSuaTT)) {
			if (tblTtSp.getSelectedRow() != -1) {
				new FormNhapThongTinSanPham("Sửa", new Dao_SanPham().getDsSanPhamFull().get(tblTtSp.getSelectedRow()));
			}
		}else if (e.getSource().equals(btnThemLoai)) {
			
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("xlsx", "xlsx");
			f.showOpenDialog(null);
			File fl = f.getSelectedFile();
			if (f.getSelectedFile()!=null) {
				
				FormNhapFileSanPham.loadDaTa(fl.getAbsolutePath());
				 chuyenTrang(true);
				 repaint();
				 revalidate();
			}			
		}
	}
}
