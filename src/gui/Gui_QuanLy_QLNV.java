package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import dao.Dao_NhanVien;
import dao.Dao_ThongBao;
import entity.NhanVien;
import entity.ThongBao;
import jnafilechooser.api.JnaFileChooser;
import rojerusan.RSTableMetro;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JList;

public class Gui_QuanLy_QLNV extends JFrame implements MouseListener, ActionListener, CaretListener, KeyListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnQLNV;
	private JTextField txt_MaNV;
	private JTextField txt_TenNV;
	private JTextField txt_SDT;
	private JTextField txt_Email;
	private JTextField txt_Email_ChamCom;
	private JTextField txt_TimNV;
	private Dao_NhanVien dao_NhanVien;
	private Dao_ThongBao dao_ThongBao;
	private DefaultTableModel dtm_ThongTinNV;
	private RSTableMetro tbl_ThongTinNV;
	private JComboBox<String> cbo_TimNV;
	private JLabel lbl_NhapTimKiem;
	private JButton btn_ChonAnh;
	private JButton btn_Huy;
	private JButton btn_Them;
	private JButton btn_Xoa;
	private JButton btn_Sua;
	private JButton btn_VietThongBao;
	private JButton btn_TimNV;
	private JButton btn_Clear;
	private JLabel lbl_Avatar;
	private File file_Avt;
	private JList<String> lst_TimKiem;
	private DefaultListModel<String> dlm_timKiem;
	private JPanel pn_List_Tim;
	private JScrollPane scr_List;
	private boolean kiemTra;
	private JPanel pn_ThongBao;
	private JRadioButton rad_TB_AllNhanVien;
	private JRadioButton rad_TB_NhanVienCuThe;
	private JButton btn_TB_ChonNV;
	private JTextArea txt_ThongBao;
	private JList<String> lst_TB_DSNV;
	private DefaultListModel<String> dlm_TB_DSNV;
	private JPanel pn_TB_ChonNVTuDS;
	private JList<String> lst_TB_DSNVSelect;
	private JList<String> lst_TB_DSNVAll;
	private JButton btn_RightAll;
	private JButton btn_Right;
	private JButton btn_Left;
	private JButton btn_LeftAll;
	private DefaultListModel<String> dlm_TB_DSNVAll;
	private DefaultListModel<String> dlm_TB_DSNVSelect;
	private int rowSelected;

	public Gui_QuanLy_QLNV() {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dao_NhanVien = new Dao_NhanVien();
		dao_ThongBao = new Dao_ThongBao();
		kiemTra=true;
		getContentPane().add(control_QLNV());
	}

	public Component control_QLNV() {
		pnQLNV = new JPanel() {
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
				Color color1 = new Color(255, 190, 190);
				Color color2 = new Color(255, 240, 240);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		pnQLNV.setBorder(null);
		pnQLNV.setBounds(0, 0, 1237, 664);
		pnQLNV.setLayout(null);
		
		pn_List_Tim = new JPanel();
		pn_List_Tim.setBounds(340, 264, 332, 160);
		pnQLNV.add(pn_List_Tim);
		pn_List_Tim.setLayout(null);
		pn_List_Tim.setVisible(false);
		
		scr_List = new JScrollPane();
		scr_List.setBounds(0, 0, 332, 160);
		pn_List_Tim.add(scr_List);
		
		lst_TimKiem = new JList<String>();
		lst_TimKiem.setModel(dlm_timKiem = new DefaultListModel<String>());
		lst_TimKiem.setSelectionMode(0);
		scr_List.setViewportView(lst_TimKiem);

		JPanel pn_ThongTinNV = new JPanel();
		pn_ThongTinNV.setOpaque(false);
		pn_ThongTinNV.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_ThongTinNV.setBounds(10, 10, 837, 200);
		pnQLNV.add(pn_ThongTinNV);
		pn_ThongTinNV.setLayout(null);

		JLabel lbl_MaNV = new JLabel("Mã nhân viên:");
		lbl_MaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MaNV.setBounds(50, 30, 120, 25);
		pn_ThongTinNV.add(lbl_MaNV);

		JLabel lbl_TenNV = new JLabel("Tên nhân viên:");
		lbl_TenNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_TenNV.setBounds(50, 68, 120, 25);
		pn_ThongTinNV.add(lbl_TenNV);

		JLabel lbl_SdtNV = new JLabel("Số điện thoại:");
		lbl_SdtNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_SdtNV.setBounds(50, 145, 120, 25);
		pn_ThongTinNV.add(lbl_SdtNV);

		JLabel lbl_Email = new JLabel("Email:");
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_Email.setBounds(50, 106, 48, 25);
		pn_ThongTinNV.add(lbl_Email);

		txt_MaNV = new JTextField();
		formatText(txt_MaNV);
		txt_MaNV.setBounds(180, 30, 100, 25);
		pn_ThongTinNV.add(txt_MaNV);
		txt_MaNV.setEditable(false);

		txt_TenNV = new JTextField();
		formatText(txt_TenNV);
		txt_TenNV.setBounds(180, 68, 450, 25);
		pn_ThongTinNV.add(txt_TenNV);

		txt_Email = new JTextField();
		formatText(txt_Email);
		txt_Email.setBounds(180, 106, 200, 25);
		pn_ThongTinNV.add(txt_Email);

		JLabel lbl_Email_ACong = new JLabel("@");
		lbl_Email_ACong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Email_ACong.setBounds(383, 106, 20, 25);
		pn_ThongTinNV.add(lbl_Email_ACong);

		txt_Email_ChamCom = new JTextField();
		formatText(txt_Email_ChamCom);
		txt_Email_ChamCom.setBounds(406, 106, 224, 25);
		txt_Email_ChamCom.setText("gmail.com");
		pn_ThongTinNV.add(txt_Email_ChamCom);

		txt_SDT = new JTextField();
		formatText(txt_SDT);
		txt_SDT.setBounds(180, 145, 450, 25);
		pn_ThongTinNV.add(txt_SDT);

		lbl_Avatar = new JLabel("");
		lbl_Avatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_Avatar.setBackground(Color.WHITE);
		lbl_Avatar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_Avatar.setBounds(667, 20, 120, 120);
		pn_ThongTinNV.add(lbl_Avatar);
		
		btn_ChonAnh = new JButton("Chọn Ảnh");
		formatButton(btn_ChonAnh);
		btn_ChonAnh.setIcon(new ImageIcon("D:\\WorkSpaceJava\\DoAn_QuanLyBanSach\\img\\IMG_LOGIN_SignIn\\img.png"));
		btn_ChonAnh.setBounds(672, 150, 110, 30);
		pn_ThongTinNV.add(btn_ChonAnh);

		btn_Clear = new JButton("");
		formatButton(btn_Clear);
		btn_Clear.setIcon(new ImageIcon("D:\\WorkSpaceJava\\DoAn_QuanLyBanSach\\img\\IMG_LOGIN_SignIn\\clear.png"));
		btn_Clear.setBounds(600, 30, 30, 30);
		pn_ThongTinNV.add(btn_Clear);

		JPanel pn_ChonTacVu = new JPanel();
		pn_ChonTacVu.setOpaque(false);
		pn_ChonTacVu.setBounds(857, 10, 370, 200);
		pnQLNV.add(pn_ChonTacVu);
		pn_ChonTacVu.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Ch\u1ECDn t\u00E1c v\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_ChonTacVu.setLayout(null);
		pn_ChonTacVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pn_List_Tim.setVisible(false);
			}
		});

		btn_Them = new JButton("Thêm Nhân Viên");
		formatButton(btn_Them);
		btn_Them.setBounds(30, 30, 150, 40);
		pn_ChonTacVu.add(btn_Them);

		btn_Sua = new JButton("Sửa Nhân Viên");
		formatButton(btn_Sua);
		btn_Sua.setBounds(30, 80, 150, 40);
		pn_ChonTacVu.add(btn_Sua);

		btn_Huy = new JButton("Hủy");
		formatButton(btn_Huy);
		btn_Huy.setBounds(190, 30, 150, 40);
		pn_ChonTacVu.add(btn_Huy);

		btn_Xoa = new JButton("Xóa Nhân Viên");
		formatButton(btn_Xoa);
		btn_Xoa.setBounds(190, 80, 150, 40);
		pn_ChonTacVu.add(btn_Xoa);

		btn_VietThongBao = new JButton("Viết thông báo");
		formatButton(btn_VietThongBao);
		btn_VietThongBao.setBounds(30, 130, 310, 40);
		pn_ChonTacVu.add(btn_VietThongBao);

		JPanel pn_TimNV = new JPanel();
		pn_TimNV.setOpaque(false);
		pn_TimNV.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"T\u00ECm ki\u1EBFm nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_TimNV.setBounds(10, 215, 1217, 65);
		pnQLNV.add(pn_TimNV);
		pn_TimNV.setLayout(null);

		lbl_NhapTimKiem = new JLabel("Nhập tên nhân viên:");
		lbl_NhapTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_NhapTimKiem.setBounds(50, 20, 166, 25);
		pn_TimNV.add(lbl_NhapTimKiem);
		
		JPanel pn_TimNV_TieuChiTim = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paint(g);
			}
		};
		pn_TimNV_TieuChiTim.setOpaque(false);
		pn_TimNV_TieuChiTim.setBounds(214, 20, 453, 29);
		pn_TimNV.add(pn_TimNV_TieuChiTim);
		pn_TimNV_TieuChiTim.setLayout(null);

		txt_TimNV = new JTextField();
		txt_TimNV.setBorder(null);
		txt_TimNV.setBounds(115, 2, 336, 25);
		pn_TimNV_TieuChiTim.add(txt_TimNV);
		txt_TimNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_TimNV.setColumns(10);
		
		cbo_TimNV = new JComboBox<String>();
		cbo_TimNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbo_TimNV.setBackground(new Color(255,255,245));
		cbo_TimNV.setBounds(2, 2, 110, 25);
		cbo_TimNV.setBorder(null);
		pn_TimNV_TieuChiTim.add(cbo_TimNV);
		cbo_TimNV.addItem("Mã nhân viên");
		cbo_TimNV.addItem("Tên nhân viên");
		cbo_TimNV.addItem("Số điện thoại");
		cbo_TimNV.addItem("Email");
		cbo_TimNV.setSelectedItem("Tên nhân viên");

		btn_TimNV = new JButton("");
		formatButton(btn_TimNV);
		btn_TimNV.setIcon(
				new ImageIcon("img\\IMG_LOGIN_SignIn\\Search_black.png"));
		btn_TimNV.setBounds(682, 18, 90, 30);
		pn_TimNV.add(btn_TimNV);
		
		changeColorButtonWhenEntered(btn_ChonAnh);
		changeColorButtonWhenEntered(btn_Clear);
		changeColorButtonWhenEntered(btn_Them);
		changeColorButtonWhenEntered(btn_Sua);
		changeColorButtonWhenEntered(btn_Huy);
		changeColorButtonWhenEntered(btn_Xoa);
		changeColorButtonWhenEntered(btn_VietThongBao);
		changeColorButtonWhenEntered(btn_TimNV);
		
		changeColorButtonWhenExited(btn_ChonAnh);
		changeColorButtonWhenExited(btn_Clear);
		changeColorButtonWhenExited(btn_Them);
		changeColorButtonWhenExited(btn_Sua);
		changeColorButtonWhenExited(btn_Huy);
		changeColorButtonWhenExited(btn_Xoa);
		changeColorButtonWhenExited(btn_VietThongBao);
		changeColorButtonWhenExited(btn_TimNV);
		
		dtm_ThongTinNV = new DefaultTableModel(
				new String[] { "Mã Nhân Viên", "Tên Nhân Viên", "Email", "Số Điện Thoại", "Chức Vụ" }, 0);
		JPanel pn_Table_ThongTinNV = new JPanel();
		pn_Table_ThongTinNV.setOpaque(false);
		pn_Table_ThongTinNV.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_Table_ThongTinNV.setBounds(10, 290, 1217, 364);
		pnQLNV.add(pn_Table_ThongTinNV);
		pn_Table_ThongTinNV.setLayout(null);

		JScrollPane scr_QLNV = new JScrollPane();
		scr_QLNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scr_QLNV.setBounds(20, 25, 1177, 319);
		pn_Table_ThongTinNV.add(scr_QLNV);
		scr_QLNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pn_List_Tim.setVisible(false);
			}
		});
		tbl_ThongTinNV = new RSTableMetro() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbl_ThongTinNV.setModel(dtm_ThongTinNV);
		scr_QLNV.setViewportView(tbl_ThongTinNV);
		tbl_ThongTinNV.setSelectionMode(0);
		tbl_ThongTinNV.setRowMargin(1);
		tbl_ThongTinNV.setRowHeight(30);
		tbl_ThongTinNV.setGrosorBordeFilas(0);
		tbl_ThongTinNV.setGridColor(Color.RED);
		tbl_ThongTinNV.setFuenteHead(new Font("Tahoma", Font.BOLD, 16));
		tbl_ThongTinNV.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 14));
		tbl_ThongTinNV.setFuenteFilas(new Font("Tahoma", Font.BOLD, 14));
		tbl_ThongTinNV.setColorSelBackgound(new Color(255, 99, 71));
		tbl_ThongTinNV.setColorFilasForeground2(Color.BLACK);
		tbl_ThongTinNV.setColorFilasForeground1(Color.BLACK);
		tbl_ThongTinNV.setColorFilasBackgound2(Color.WHITE);
		tbl_ThongTinNV.setColorFilasBackgound1(Color.WHITE);
		tbl_ThongTinNV.setColorBordeHead(new Color(204, 0, 51));
		tbl_ThongTinNV.setColorBordeFilas(Color.RED);
		tbl_ThongTinNV.setColorBackgoundHead(new Color(204, 0, 51));
		tbl_ThongTinNV.setAltoHead(30);

		pn_ThongBao = new JPanel();
		pn_ThongBao.setPreferredSize(new Dimension(609,185));
		pn_ThongBao.setLayout(null);
		
		JPanel pn_TB_ChonNhanVien = new JPanel();
		pn_TB_ChonNhanVien.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Ch\u1ECDn nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_TB_ChonNhanVien.setBounds(360, 10, 230, 160);
		pn_ThongBao.add(pn_TB_ChonNhanVien);
		pn_TB_ChonNhanVien.setLayout(null);
		
		rad_TB_AllNhanVien = new JRadioButton("Tất cả nhân viên");
		rad_TB_AllNhanVien.setBounds(10, 15, 143, 30);
		pn_TB_ChonNhanVien.add(rad_TB_AllNhanVien);
		rad_TB_AllNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rad_TB_AllNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rad_TB_AllNhanVien.setFocusable(false);
		rad_TB_AllNhanVien.setSelected(true);
		
		rad_TB_NhanVienCuThe = new JRadioButton("Chọn nhân viên:");
		rad_TB_NhanVienCuThe.setBounds(10, 50, 142, 30);
		pn_TB_ChonNhanVien.add(rad_TB_NhanVienCuThe);
		rad_TB_NhanVienCuThe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rad_TB_NhanVienCuThe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rad_TB_NhanVienCuThe.setFocusable(false);
		
		ButtonGroup btn = new ButtonGroup();
		btn.add(rad_TB_AllNhanVien);
		btn.add(rad_TB_NhanVienCuThe);
		
		JScrollPane scr_DSNV = new JScrollPane();
		scr_DSNV.setBounds(15, 80, 200, 70);
		pn_TB_ChonNhanVien.add(scr_DSNV);
		
		lst_TB_DSNV = new JList<String>();
		lst_TB_DSNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lst_TB_DSNV.setModel(dlm_TB_DSNV = new DefaultListModel<String>());
		lst_TB_DSNV.setSelectionMode(0);
		lst_TB_DSNV.setEnabled(false);
		scr_DSNV.setViewportView(lst_TB_DSNV);
		
		btn_TB_ChonNV = new JButton("");
		btn_TB_ChonNV.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\add.png"));
		btn_TB_ChonNV.setBounds(165, 55, 50, 20);
		btn_TB_ChonNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_TB_ChonNV.setBackground(Color.LIGHT_GRAY);
		btn_TB_ChonNV.setMargin(new Insets(2, 13, 2, 14));
		btn_TB_ChonNV.setFocusable(false);
		btn_TB_ChonNV.setEnabled(false);
		pn_TB_ChonNhanVien.add(btn_TB_ChonNV);
		
		JPanel pn_NoiDungThongBao = new JPanel();
		pn_NoiDungThongBao.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "N\u1ED9i dung", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_NoiDungThongBao.setBounds(10, 10, 342, 160);
		pn_ThongBao.add(pn_NoiDungThongBao);
		pn_NoiDungThongBao.setLayout(null);
		
		JScrollPane scr_ThongBao = new JScrollPane();
		scr_ThongBao.setBounds(10, 20, 322, 130);
		pn_NoiDungThongBao.add(scr_ThongBao);
		
		txt_ThongBao = new JTextArea();
		txt_ThongBao.setMargin(new Insets(2, 5, 2, 5));
		txt_ThongBao.setLineWrap(true);
		txt_ThongBao.setWrapStyleWord(true);
		txt_ThongBao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scr_ThongBao.setViewportView(txt_ThongBao);
		
		pn_TB_ChonNVTuDS = new JPanel();
		pn_TB_ChonNVTuDS.setPreferredSize(new Dimension(490,160));
		pn_TB_ChonNVTuDS.setLayout(null);
		
		JPanel pn_TB_DSNVAll = new JPanel();
		pn_TB_DSNVAll.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh s\u00E1ch nh\u00E2n vi\u00EAn ch\u01B0a ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pn_TB_DSNVAll.setBounds(10, 10, 200, 140);
		pn_TB_ChonNVTuDS.add(pn_TB_DSNVAll);
		pn_TB_DSNVAll.setLayout(null);
		
		JScrollPane scr_TB_DSNVAll = new JScrollPane();
		scr_TB_DSNVAll.setBounds(10, 20, 180, 110);
		pn_TB_DSNVAll.add(scr_TB_DSNVAll);
		
		lst_TB_DSNVAll = new JList<String>();
		scr_TB_DSNVAll.setViewportView(lst_TB_DSNVAll);
		lst_TB_DSNVAll.setModel(dlm_TB_DSNVAll = new DefaultListModel<String>());
		
		JPanel pn_TB_DSNVSelect = new JPanel();
		pn_TB_DSNVSelect.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh s\u00E1ch nh\u00E2n vi\u00EAn \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pn_TB_DSNVSelect.setBounds(280, 10, 200, 140);
		pn_TB_ChonNVTuDS.add(pn_TB_DSNVSelect);
		pn_TB_DSNVSelect.setLayout(null);
		
		JScrollPane scr_TB_DSNVSelect = new JScrollPane();
		scr_TB_DSNVSelect.setBounds(10, 20, 180, 110);
		pn_TB_DSNVSelect.add(scr_TB_DSNVSelect);
		
		lst_TB_DSNVSelect = new JList<String>();
		scr_TB_DSNVSelect.setViewportView(lst_TB_DSNVSelect);
		lst_TB_DSNVSelect.setModel(dlm_TB_DSNVSelect = new DefaultListModel<String>());
		
		btn_RightAll = new JButton(">>");
		btn_RightAll.setBounds(220, 22, 50, 20);
		pn_TB_ChonNVTuDS.add(btn_RightAll);
		
		btn_Right = new JButton(">");
		btn_Right.setBounds(220, 54, 50, 20);
		pn_TB_ChonNVTuDS.add(btn_Right);
		
		btn_Left = new JButton("<");
		btn_Left.setBounds(220, 86, 50, 20);
		pn_TB_ChonNVTuDS.add(btn_Left);
		
		btn_LeftAll = new JButton("<<");
		btn_LeftAll.setBounds(220, 118, 50, 20);
		pn_TB_ChonNVTuDS.add(btn_LeftAll);
		
		loaData(dao_NhanVien.getDsNhanVien());
		if(tbl_ThongTinNV.getRowCount()>0)
//			tbl_ThongTinNV.setRowSelectionInterval(tbl_ThongTinNV.getRowCount()-1, tbl_ThongTinNV.getRowCount()-1);
		{
			tbl_ThongTinNV.changeSelection(tbl_ThongTinNV.getRowCount()-1, tbl_ThongTinNV.getColumnCount(), false, false);
			getDataFromTableIntoText(tbl_ThongTinNV.getSelectedRow());
		}
		lockText(false);
		
		btn_ChonAnh.addActionListener(this);
		btn_Clear.addActionListener(this);
		btn_Them.addActionListener(this);
		btn_Sua.addActionListener(this);
		btn_Huy.addActionListener(this);
		btn_Xoa.addActionListener(this);
		btn_VietThongBao.addActionListener(this);
		cbo_TimNV.addActionListener(this);
		tbl_ThongTinNV.addMouseListener(this);
		txt_TimNV.addCaretListener(this);
		txt_TimNV.addKeyListener(this);
		txt_TimNV.addMouseListener(this);
		lst_TimKiem.addListSelectionListener(this);
		lst_TimKiem.addKeyListener(this);
		btn_TimNV.addActionListener(this);
		pnQLNV.addMouseListener(this);
		rad_TB_AllNhanVien.addActionListener(this);
		rad_TB_NhanVienCuThe.addActionListener(this);
		btn_TB_ChonNV.addActionListener(this);
		txt_ThongBao.addKeyListener(this);
		btn_RightAll.addActionListener(this);
		btn_Right.addActionListener(this);
		btn_Left.addActionListener(this);
		btn_LeftAll.addActionListener(this);
		
		return pnQLNV;
	}

	public void loaData(List<NhanVien> list) {
		dtm_ThongTinNV.setRowCount(0);
		for (NhanVien nhanVien : list) {
			String row[] = {nhanVien.getMaNV().trim(), nhanVien.getTenNV().trim(), nhanVien.getEmail().trim(), nhanVien.getSdt().trim(), nhanVien.getChucVu()};
			dtm_ThongTinNV.addRow(row);
		}
	}
	
	/**
	 * Định dạng button
	 * @param btn button cần định dạng
	 */
	public void formatButton(JButton btn) {
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		btn.setBackground(new Color(250, 112, 112));
		btn.setMargin(new Insets(2, 0, 2, 14));
		btn.setFocusable(false);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
	
	public void formatText(JTextField txt) {
		txt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt.setColumns(10);
	}
	
	public void changeColorButtonWhenEntered(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btn.isEnabled()) {
					btn.setBackground(new Color(255, 209, 209));
					btn.setBorder(new LineBorder(Color.YELLOW, 2, true));
				}
			}
		});
		
	}

	/*
	 * Đổi màu cho button khi trỏ chuột ra khỏi button
	 */
	public void changeColorButtonWhenExited(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(btn.isEnabled()) {
				btn.setBackground(new Color(250, 112, 112));
				btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
				}
			}
		});
	}
	
	public void lockText(boolean lock) {
		txt_TenNV.setEditable(lock);
		txt_Email.setEditable(lock);
		txt_Email_ChamCom.setEditable(lock);
		txt_SDT.setEditable(lock);
		txt_TimNV.setEditable(!lock);
		cbo_TimNV.setEnabled(!lock);
		setEnableButton(btn_ChonAnh, lock);
		setEnableButton(btn_Clear, lock);
		setEnableButton(btn_Huy, lock);
		setEnableButton(btn_Xoa, !lock);
		setEnableButton(btn_VietThongBao, !lock);
		setEnableButton(btn_TimNV, !lock);
	}
	
	public void getDataFromTableIntoText(int row) {
		lbl_Avatar.setIcon(null);
		txt_MaNV.setText(dtm_ThongTinNV.getValueAt(row, 0)+"");
		txt_TenNV.setText(dtm_ThongTinNV.getValueAt(row, 1)+"");
		txt_Email.setText((dtm_ThongTinNV.getValueAt(row, 2)+"").split("@")[0]);
		txt_Email_ChamCom.setText((dtm_ThongTinNV.getValueAt(row, 2)+"").split("@")[1]);
		txt_SDT.setText(dtm_ThongTinNV.getValueAt(row, 3)+"");
		String pathAvt = dao_NhanVien.getPathAvatar(txt_MaNV.getText()); 
		try {
			lbl_Avatar.setIcon(new ImageIcon(new ImageIcon(pathAvt).getImage()
					.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH)));
		} catch (Exception e) {
		}
	}
	
	public void showMess(String mess) {
		JOptionPane.showMessageDialog(null, mess);
	}
	
	public void setEnableButton(JButton btn ,boolean enable) {
		btn.setEnabled(enable);
		btn.setBackground(btn.isEnabled()? new Color(250, 112, 112) : new Color(255, 245, 228));
	}
	
	public String regexNhanVien() {
		if(txt_TenNV.getText().isBlank())
			return "Tên nhân viên không được rỗng!";
		if(txt_SDT.getText().isBlank())
			return "Số điện thoại không được rỗng!";
		if(!txt_TenNV.getText().matches("^([A-ZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÊẾỀỂỄỆÉÈẺẼẸÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ][a-záàảãạăắằẳẵặâấầẩẫậđêếềểễệéèẻẽẹíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]*(\\s){0,1}){2,}$"))
			return "Tên nhân viên phải tối thiểu gồm 2 từ và bắt đầu bằng chữ in hoa";
		if(!txt_SDT.getText().matches("[0-9]{9,10}"))
			return "Số điện thoại chỉ gồm 9 hoặc 10 số";
		if(lbl_Avatar.getIcon()==null)
			return "Hãy chọn ảnh!";
		return "";
	}
	
	public void clearText() {
		txt_TenNV.setText("");
		txt_Email.setText("");
		txt_Email_ChamCom.setText("gmail.com");
		txt_SDT.setText("");
		lbl_Avatar.setIcon(null);
	}
	
	public List<Integer> searchNV(String criteria, String nameSearch) {
		List<Integer> list = new ArrayList<Integer>();
		int column = 0;
		if(criteria.equals("Tên nhân viên"))
			column = 1;
		else if(criteria.equals("Email"))
			column = 2;
		else if(criteria.equals("Số điện thoại")) column = 3;
		for (int i = 0; i < tbl_ThongTinNV.getRowCount(); i++) {
			if(dtm_ThongTinNV.getValueAt(i, column).equals(nameSearch))
				list.add(i);
		}
		return list;
	}
	
	public void makeAppearResultSearch(List<Integer> list) {
		if(list.size()==0)
			showMess("Không tìm thấy nhân viên!");
		else {
			showMess("Đã tìm thấy "+list.size()+" nhân viên");
			for (Integer integer : list) {
				String row[] = {dtm_ThongTinNV.getValueAt(integer, 0)+"", dtm_ThongTinNV.getValueAt(integer, 1)+"", dtm_ThongTinNV.getValueAt(integer, 2)+"",
						dtm_ThongTinNV.getValueAt(integer, 3)+"", dtm_ThongTinNV.getValueAt(integer, 4)+""};
				dtm_ThongTinNV.addRow(row);
			}
			
		}
	}
	
	public void appearPnList(String criteria) {
		dlm_timKiem.removeAllElements();
		String value = "";
		List<NhanVien> list = dao_NhanVien.getDsNhanVien();
		for (int i = 0; i < list.size(); i++) {
			if(criteria.equals("Mã nhân viên"))
				value = list.get(i).getMaNV();
			else if(criteria.equals("Tên nhân viên"))
				value = list.get(i).getTenNV();
			else if(criteria.equals("Email"))
				value = list.get(i).getEmail();
			else if(criteria.equals("Số điện thoại")) value = list.get(i).getSdt();;
			if(value.toLowerCase().contains(txt_TimNV.getText().toLowerCase()) && !dlm_timKiem.contains(value))
				dlm_timKiem.addElement(value);
		}
		if(dlm_timKiem.getSize()>=5) {
			pn_List_Tim.setBounds(340, 264, 332, 90);
			scr_List.setBounds(0, 0, 332, 90);
		} else {
			pn_List_Tim.setBounds(340, 264, 332, dlm_timKiem.getSize()*20);
			scr_List.setBounds(0, 0, 332, dlm_timKiem.getSize()*20);
		}
		pn_List_Tim.setVisible(true);
	}

	public void moveElement(JList<String> l1, JList<String> l2, String countElemnt) {
		if(countElemnt.equals("1")) {
			if(((DefaultListModel<String>)l1.getModel()).getSize()>0) {
				int index = l1.getSelectedIndex();
				((DefaultListModel<String>)l2.getModel()).addElement(((DefaultListModel<String>)l1.getModel()).getElementAt(index));
				l2.setSelectedIndex(((DefaultListModel<String>)l2.getModel()).getSize()-1);
				((DefaultListModel<String>)l1.getModel()).removeElementAt(index);
				if(index==((DefaultListModel<String>)l1.getModel()).getSize())
					index--;
				l1.setSelectedIndex(index);
			}
		} else {
			for (int i = 0; i < ((DefaultListModel<String>)l1.getModel()).getSize(); i++) {
				((DefaultListModel<String>)l2.getModel()).addElement(((DefaultListModel<String>)l1.getModel()).getElementAt(i));
			}
			((DefaultListModel<String>)l1.getModel()).removeAllElements();
			if(((DefaultListModel<String>)l2.getModel()).getSize()>0)
				l2.setSelectedIndex(((DefaultListModel<String>)l2.getModel()).getSize()-1);
		}
	}
	
	public static void main(String[] args) {
		new Gui_QuanLy_QLNV().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(tbl_ThongTinNV)) {
			pn_List_Tim.setVisible(false);
			if(!btn_Huy.isEnabled())
				getDataFromTableIntoText(tbl_ThongTinNV.getSelectedRow());
		} else if(e.getSource().equals(pnQLNV)) {
			if(e.getX()<pn_List_Tim.getX() || e.getX()>pn_List_Tim.getX() || e.getY()<pn_List_Tim.getY() || e.getY()>pn_List_Tim.getY()) {
				pn_List_Tim.setVisible(false);
				txt_TimNV.requestFocus(false);
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		pn_List_Tim.setVisible(false);
		if(e.getSource().equals(btn_ChonAnh)) {
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("jpg", "jpg");
			f.addFilter("png", "png");
			f.showOpenDialog(null);
			file_Avt = f.getSelectedFile();
			if (f.getSelectedFile() != null) {
				lbl_Avatar.setIcon(new ImageIcon(new ImageIcon(file_Avt.getPath().toString()).getImage()
					.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH)));
			}
		} else if(e.getSource().equals(btn_Clear)) {
			clearText();
		} else if(e.getSource().equals(btn_Them)) {
			if(btn_Them.getText().equals("Thêm Nhân Viên")) {
				if(dao_NhanVien.getDsNhanVien().size() > tbl_ThongTinNV.getRowCount())
				{
					loaData(dao_NhanVien.getDsNhanVien());
					tbl_ThongTinNV.setRowSelectionInterval(0, 0);
				}
				tbl_ThongTinNV.setRowSelectionAllowed(false);
				kiemTra = false;
				txt_TimNV.setText("");
				file_Avt = null;
				txt_TenNV.requestFocus();
				lbl_Avatar.setIcon(null);
				btn_Them.setText("Lưu");
				setEnableButton(btn_Sua, false);
				String year = LocalDate.now().getYear()+"";
				String maNVCuoi = dao_NhanVien.getNhanVienCuoi(year.substring(year.length()-2));
				int stt=0;
				if(!maNVCuoi.isEmpty())
					stt = Integer.parseInt(maNVCuoi.substring(maNVCuoi.length()-2))+1;
				String maNV = "NV"+year.substring(year.length()-2)+(stt<10? "0"+stt : stt);
				txt_MaNV.setText(maNV);
				txt_TenNV.setText("Nguyễn Thanh Sơn");
				txt_Email.setText("thanhsonsmile2017");
				txt_SDT.setText("038764565");
				lockText(true);
			}
			else {//Lưu
				String regex = regexNhanVien();
				if(!regex.equals("")) {
					showMess(regex);
					if(regex.startsWith("Tên nhân viên"))
					{
						txt_TenNV.requestFocus();;
						txt_TenNV.selectAll();
					}
					else if(regex.startsWith("Số điện thoại")) {
						txt_SDT.requestFocus();
						txt_SDT.selectAll();
					}
				}
				else {
					String fileName = file_Avt.getName();
					String newName = txt_MaNV.getText() + fileName.substring(fileName.lastIndexOf("."));
					File f1 = new File("img\\AvtUser\\NhanVien\\"+newName);
					try {
						Files.copy(file_Avt.toPath(), f1.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(dao_NhanVien.insertNhanVien(new NhanVien(txt_MaNV.getText(), txt_TenNV.getText(), txt_SDT.getText(), f1.getPath(), txt_Email.getText()+"@"+txt_Email_ChamCom.getText(), "Nhân Viên", true)))
					{
						showMess("Thêm nhân viên thành công");
						tbl_ThongTinNV.setRowSelectionAllowed(true);
						String row [] = {txt_MaNV.getText(), txt_TenNV.getText(), txt_Email.getText()+"@"+txt_Email_ChamCom.getText(), txt_SDT.getText(), "Nhân Viên"};
						dtm_ThongTinNV.addRow(row);
						lockText(false);
						file_Avt = null;
						tbl_ThongTinNV.changeSelection(tbl_ThongTinNV.getRowCount()-1, tbl_ThongTinNV.getColumnCount()-1, false, false);
						GUI_ThongKe.loadStatistics();
					}
					else
						showMess("Không thể thêm nhân viên");
					btn_Them.setText("Thêm Nhân Viên");
					setEnableButton(btn_Sua, true);
					kiemTra = true;
					}
			}
		} else if(e.getSource().equals(btn_Sua)) {
			if(btn_Sua.getText().equals("Sửa Nhân Viên")) {
				rowSelected = tbl_ThongTinNV.getSelectedRow();
				if(rowSelected == -1)
					showMess("Hãy chọn dòng trước khi sửa!");
				else {
				rowSelected = tbl_ThongTinNV.getSelectedRow();
				tbl_ThongTinNV.setRowSelectionAllowed(false);
				file_Avt = null;
				kiemTra = false;
				txt_TenNV.requestFocus();
				lockText(true);
				btn_Sua.setText("Lưu");
				setEnableButton(btn_Them, false);
				}
			}
			else {//Lưu
				String regex = regexNhanVien();
				if(!regex.equals("")) {
					showMess(regex);
					if(regex.startsWith("Tên nhân viên"))
					{
						txt_TenNV.requestFocus();;
						txt_TenNV.selectAll();
					}
					else if(regex.startsWith("Số điện thoại")) {
						txt_SDT.requestFocus();
						txt_SDT.selectAll();
					}
				}
				else {
					String fileName;
					String path="";
					if(file_Avt!=null) {
						fileName = file_Avt.getName();
						String newName = txt_MaNV.getText().trim() + fileName.substring(fileName.lastIndexOf("."));
						File f1 = new File("img\\AvtUser\\NhanVien\\"+newName);
						try {
							Files.copy(file_Avt.toPath(), f1.toPath(), StandardCopyOption.REPLACE_EXISTING);
							path = f1.getPath();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else
						path = dao_NhanVien.getPathAvatar(txt_MaNV.getText()).trim();
					if(dao_NhanVien.updateNhanVien(new NhanVien(txt_MaNV.getText(), txt_TenNV.getText(), txt_SDT.getText(), path, txt_Email.getText()+"@"+txt_Email_ChamCom.getText(), "Nhân Viên", true)))
					{
						showMess("Sửa thông tin nhân viên thành công");
						tbl_ThongTinNV.setRowSelectionAllowed(true);
						dtm_ThongTinNV.setValueAt(txt_TenNV.getText(), rowSelected, 1);
						dtm_ThongTinNV.setValueAt(txt_Email.getText()+"@"+txt_Email_ChamCom.getText(), rowSelected, 2);
						dtm_ThongTinNV.setValueAt(txt_SDT.getText(), rowSelected, 3);
						lockText(false);
						getDataFromTableIntoText(rowSelected);
						tbl_ThongTinNV.changeSelection(rowSelected, tbl_ThongTinNV.getColumnCount(), false, false);
					}
					else
						showMess("Không thể sửa nhân viên");
					btn_Sua.setText("Sửa Nhân Viên");
					setEnableButton(btn_Them, true);
					kiemTra = true;
					}
			}
		} else if(e.getSource().equals(btn_Huy)) {
			tbl_ThongTinNV.setRowSelectionAllowed(true);
			lockText(false);
			btn_Huy.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
			if(btn_Them.isEnabled()) {
				btn_Them.setText("Thêm Nhân Viên"); 
				btn_Sua.setEnabled(true);
				btn_Sua.setBackground(new Color(250, 112, 112));
			}
			else {
				btn_Sua.setText("Sửa Nhân Viên");
				btn_Them.setEnabled(true);
				btn_Them.setBackground(new Color(250, 112, 112));
			}
			if(tbl_ThongTinNV.getRowCount()>0)
				getDataFromTableIntoText(tbl_ThongTinNV.getSelectedRow());
			kiemTra = true;
		} else if(e.getSource().equals(btn_Xoa)) {
			int rowSelected = tbl_ThongTinNV.getSelectedRow();
			if(rowSelected==-1)
				showMess("Hãy chọn nhân viên cần xóa!");
			else {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên mã "+txt_MaNV.getText(), "Xóa nhân viên", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(dao_NhanVien.deleteNhanVien(txt_MaNV.getText())) {
						showMess("Xóa nhân viên thành công!");
//						loaData(dao_NhanVien.getDsNhanVien());
						dtm_ThongTinNV.removeRow(rowSelected);
						if(tbl_ThongTinNV.getRowCount()>0) {
							if(rowSelected==tbl_ThongTinNV.getRowCount())
								rowSelected -=1;
							tbl_ThongTinNV.setRowSelectionInterval(rowSelected, rowSelected);
							getDataFromTableIntoText(tbl_ThongTinNV.getSelectedRow());
						} else clearText();
					}
					else showMess("Xóa nhân viên không thành công");
				}
			}
		} else if(e.getSource().equals(btn_VietThongBao)) {
			boolean kt = false;
			while(!kt) {
				if(JOptionPane.showConfirmDialog(null, pn_ThongBao, "Viết thông báo cho nhân viên", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION)==JOptionPane.OK_OPTION){
					if(txt_ThongBao.getText().trim().length()==0)
						showMess("Nhập thông báo!");
					else if(rad_TB_NhanVienCuThe.isSelected() && dlm_TB_DSNV.getSize()==0)
						showMess("Chọn nhân viên!");
					else {
						ThongBao tb;
						String year = LocalDate.now().getYear()+"";
						String maTBCuoi = dao_ThongBao.getMaTBCuoi();
						int stt=0;
						if(!maTBCuoi.isEmpty())
							stt = Integer.parseInt(maTBCuoi.substring(maTBCuoi.length()-3))+1;
						String maTB = "TB"+year.substring(year.length()-2)+(stt<10? "00"+stt : (stt<100? "0"+stt : stt));
						List<NhanVien> dsnv = new ArrayList<NhanVien>();
						if(rad_TB_AllNhanVien.isSelected())
							dsnv = dao_NhanVien.getDsNhanVien();
						else for (int i = 0; i < dlm_TB_DSNV.getSize(); i++)
							dsnv.add(new NhanVien(dlm_TB_DSNV.getElementAt(i).split("-")[0]));
						for (NhanVien nv : dsnv) {
							tb = new ThongBao(maTB, nv.getMaNV(), txt_ThongBao.getText().trim(), new Date(System.currentTimeMillis()), false);
							dao_ThongBao.insertNotication(tb);
						}
						showMess("Thông báo cho "+dsnv.size()+" nhân viên thành công!");
						txt_ThongBao.setText("");
						kt=true;
					}
				} else kt=true;
			}
		} else if(e.getSource().equals(btn_TB_ChonNV)) {
			dlm_TB_DSNVSelect.removeAllElements();
			dlm_TB_DSNVAll.removeAllElements();
			for (int i = 0; i < dlm_TB_DSNV.getSize(); i++) {
				dlm_TB_DSNVSelect.addElement(dlm_TB_DSNV.elementAt(i));
			}
			String element = "";
			for (NhanVien nv : dao_NhanVien.getDsNhanVien()) {
				element = nv.getMaNV()+"-"+nv.getTenNV();
				if(!dlm_TB_DSNVSelect.contains(element))
					dlm_TB_DSNVAll.addElement(element);
			}
			if(dlm_TB_DSNVAll.getSize()>0) lst_TB_DSNVAll.setSelectedIndex(0);
			if(dlm_TB_DSNVSelect.getSize()>0) lst_TB_DSNVSelect.setSelectedIndex(0);
			if(JOptionPane.showConfirmDialog(null, pn_TB_ChonNVTuDS, "Chọn nhân viên từ danh sách", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION)==JOptionPane.OK_OPTION)
			{
				dlm_TB_DSNV.removeAllElements();
				for (int i = 0; i < dlm_TB_DSNVSelect.getSize(); i++) {
					dlm_TB_DSNV.addElement(dlm_TB_DSNVSelect.elementAt(i));
				}
			}
		} else if(e.getSource().equals(btn_RightAll)) {
			moveElement(lst_TB_DSNVAll, lst_TB_DSNVSelect, "All");
		} else if(e.getSource().equals(btn_Right)) {
			moveElement(lst_TB_DSNVAll, lst_TB_DSNVSelect, "1");
		} else if(e.getSource().equals(btn_Left)) {
			moveElement(lst_TB_DSNVSelect, lst_TB_DSNVAll, "1");
		} else if(e.getSource().equals(btn_LeftAll)) {
			moveElement(lst_TB_DSNVSelect, lst_TB_DSNVAll, "All");
		} else if(e.getSource().equals(cbo_TimNV)) {
			lbl_NhapTimKiem.setText("Nhập "+cbo_TimNV.getSelectedItem().toString().toLowerCase()+":");
		} else if(e.getSource().equals(btn_TimNV)) {
			String criteria = "";
			switch (cbo_TimNV.getSelectedItem()+"") {
			case "Mã nhân viên":
				criteria = "maNV";
				break;
			case "Tên nhân viên":
				criteria = "tenNV";
				break;
			case "Số điện thoại":
				criteria = "sdt";
				break;
			case "Email":
				criteria = "email";
				break;
			}
			List<NhanVien> list = dao_NhanVien.getDsNhanVien(txt_TimNV.getText().trim(), criteria);
			if(list.size()==0)
				showMess("Không tìm thấy nhân viên!");
			else {
				loaData(list);
				tbl_ThongTinNV.setRowSelectionInterval(0, 0);
				getDataFromTableIntoText(0);
			}
		} else if(e.getSource().equals(rad_TB_AllNhanVien)) {
			lst_TB_DSNV.setEnabled(false);
			btn_TB_ChonNV.setEnabled(false);
		}
		else if(e.getSource().equals(rad_TB_NhanVienCuThe)) {
			lst_TB_DSNV.setEnabled(true);
			btn_TB_ChonNV.setEnabled(true);
		} 
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		if(e.getSource().equals(txt_TimNV) && kiemTra)
			appearPnList(cbo_TimNV.getSelectedItem()+"");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource().equals(txt_ThongBao)) {
			int max = 1000;
		    if(txt_ThongBao.getText().length() > max) {
		        e.consume();
		    }
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(txt_TimNV) || e.getSource().equals(lst_TimKiem)) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				btn_TimNV.doClick();
			else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				int index = lst_TimKiem.getSelectedIndex();
				if(index>=0)
					lst_TimKiem.setSelectedIndex(index==dlm_timKiem.getSize()-1? 0 : index+1);
				else if(dlm_timKiem.getSize()>0)
					lst_TimKiem.setSelectedIndex(0);
			} else if(e.getKeyCode()==KeyEvent.VK_UP) {
				int index = lst_TimKiem.getSelectedIndex();
				if(index>=0)
					lst_TimKiem.setSelectedIndex(index==0? dlm_timKiem.getSize()-1 : index-1);
			}
		}
			
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource().equals(lst_TimKiem) && kiemTra) {
			int index = lst_TimKiem.getSelectedIndex();
			if(index>=0) {
				kiemTra = false;
				txt_TimNV.setText(lst_TimKiem.getSelectedValue());
				kiemTra = true;
			}
		}
	}
}
