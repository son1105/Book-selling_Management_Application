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
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_NhanVien;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import jnafilechooser.api.JnaFileChooser;
import rojerusan.RSTableMetro;

public class GUI_ThongKe extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Khai báo biến tổng quát
	private static NhanVien nhanVien;
	private JPanel pnThongKe;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static Dao_HoaDon dao_HoaDon;
	private static Dao_ChiTietHoaDon dao_ChiTietHoaDon;
	private static Dao_SanPham dao_SanPham;
	private static Dao_NhanVien dao_NhanVien;
	private static JLabel lblIconTongNV;
	private static JLabel lblIconTongHD;
	private static JLabel lblIconTongDT;
	private JLabel lblTKTongSP;
	private static JLabel lblIconTongSP;
	private String btnSelected;
	private JButton btnTKSLMua;
	private JButton btnTKSLTon;
	private JButton btnTKHinhThucMua;
	private JButton btnTKDoanhThu;
	private JButton btnTKGiamGia;
	private static DecimalFormat formatter = new DecimalFormat("###,###,###");
	
	//Thống kê theo số lượng mua
	private JPanel pnChiTietTKSLMua;
	private JDateChooser dC_TKSLMua_BD;
	private JLabel lbl_TKSLMua_MuiTen_NgayMua;
	private JPanel pn_TKSLMua_KT_NgayMua;
	private JDateChooser dC_TKSLMua_KT;
	private JLabel lbl_TKSLMua_Reset;
	private JRadioButton rad_TKSLMua_0den20;
	private JRadioButton rad_TKSLMua_20den50;
	private JRadioButton rad_TKSLMua_50den100;
	private JRadioButton rad_TKSLMua_Tu100;
	private JRadioButton rad_TKSLMua_Khac;
	private JSpinner spn_TKSLMua_BD;
	private JLabel lbl_TKSLMua_MuiTen;
	private JPanel pn_TKSLMua_KT;
	private JSpinner spn_TKSLMua_KT;
	private JLabel lbl_TKSLMua_Infinity;
	private JButton btn_TKSLMua_ThongKe;
	private RSTableMetro tbl_TKSLMua;
	private DefaultTableModel dtm_TKSLMua;

	//Thống kê theo số lượng tồn
	private JPanel pnChiTietTKSLTon;
	private JRadioButton rad_TKSLTon_0den50;
	private JRadioButton rad_TKSLTon_50den100;
	private JRadioButton rad_TKSLTon_100den300;
	private JRadioButton rad_TKSLTon_300den500;
	private JRadioButton rad_TKSLTon_Khac;
	private JRadioButton rad_TKSLTon_Tu500;
	private JSpinner spn_TKSLTon_BD;
	private JLabel lbl_TKSLTon_MuiTen;
	private JPanel pn_TKSLTon_KT;
	private JSpinner spn_TKSLTon_KT;
	private JLabel lbl_TKSLTon_Infinity;
	private JButton btn_TKSLTon_ThongKe;
	private RSTableMetro tbl_TKSLTon;
	private DefaultTableModel dtm_TKSLTon;

	//Thống kê theo hình thức mua
	private JPanel pnChiTietTKHinhThucMua;
	private JDateChooser dC_TKHinhThucMua_BD;
	private JLabel lbl_TKHinhThucMua_MuiTen;
	private JPanel pn_TKHinhThucMua_KT;
	private JDateChooser dC_TKHinhThucMua_KT;
	private JLabel lbl_TKHinhThucMua_Reset;
	private JButton btn_TKHinhThucMua_ThongKe;
	private RSTableMetro tbl_TKHinhThucMua;
	private DefaultTableModel dtm_TKHinhThucMua;
	private JTextField txt_TKHinhThucMua_TongTienMuaTrucTiep;
	private JTextField txt_TKHinhThucMua_TongTienMuaOnline;

	//Thống kê giảm giá
	private JPanel pnChiTietTKGiamGia;
	private JRadioButton rad_TKGG_0;
	private JRadioButton rad_TKGG_10;
	private JRadioButton rad_TKGG_30;
	private JRadioButton rad_TKGG_50;
	private JRadioButton rad_TKGG_70;
	private JRadioButton rad_TKGG_Khac;
	private JSpinner spn_TKGG_BD;
	private JLabel lbl_TKGG_MuiTen;
	private JPanel pn_TKGG_KT;
	private JSpinner spn_TKGG_KT;
	private JLabel lbl_TKGG_Infinity;
	private JButton btn_TKGG_ThongKe;
	private RSTableMetro tbl_TKGG;
	private DefaultTableModel dtm_TKGG;

	//Thống kê doanh thu
	private JPanel pnChiTietTKDoanhThu;
	private JCheckBox chk_TKDT_ThoiGian;
	private JComboBox<String> cbo_TKDT_ThongKeTheo;
	private JPanel pn_TKDT_Ngay;
	private JPanel pn_TKDT_Thang;
	private JPanel pn_TKDT_Quy;
	private JPanel pn_TKDT_Nam;
	private JLabel lbl_TKDT_MuiTen;
	private JLabel lbl_TKDT_Reset;
	private JDateChooser dC_TKDT_Ngay_BD;
	private JDateChooser dC_TKDT_Ngay_KT;
	private JComboBox<String> cbo_TKDT_Thang_BD;
	private JYearChooser yC_TKDT_Thang_BD;
	private JComboBox<String> cbo_TKDT_Thang_KT;
	private JYearChooser yC_TKDT_Thang_KT;
	private JComboBox<String> cbo_TKDT_Quy_BD;
	private JYearChooser yC_TKDT_Quy_BD;
	private JComboBox<String> cbo_TKDT_Quy_KT;
	private JYearChooser yC_TKDT_Quy_KT;
	private JYearChooser yC_TKDT_Nam_BD;
	private JYearChooser yC_TKDT_Nam_KT;
	private JCheckBox chk_TKDT_DoanhThu;
	private JRadioButton rad_TKDT_comBoBox;
	private JComboBox<String> cbo_TKDT_DoanhThu;
	private JRadioButton rad_TKDT_Khac;
	private JSpinner spn_TKDT_BD;
	private JLabel lbl_TKDT_MuiTen_DoanhThu;
	private JPanel pn_TKDT_KT;
	private JSpinner spn_TKDT_KT;
	private JLabel lbl_TKDT_Infinity;
	private int doanhThu_BD;
	private int doanhThu_KT;
	private JButton btn_TKDT_ThongKe;
	private RSTableMetro tbl_TKDT;
	private DefaultTableModel dtm_TKDT;
	private JTextField txt_TKDT_TongDoanhThu;
	private String typeTime;
	private String ngayBD;
	private String ngayKT;
	
	//Biểu đồ
	private JPanel pnBieuDo;
	private JPanel pn_HinhBieuDo;
	private JComboBox<String> cbo_LoaiBieuDo;
	private JPanel pn_BDCot_ChonSP;
	private JRadioButton rad_TK_BDCot_15Nhieu;
	private JRadioButton rad_TK_BDCot_15It;
	private JRadioButton rad_TK_BDCot_TrungBinh;
	private JRadioButton rad_TK_BDCot_Chon;
	private DefaultTableModel dtm_ChiTietSanPham;
	private JPanel pn_BDTron_MoTa;
	private JLabel lbl_BDTron_MoTa;
	
	//Báo cáo
	private JButton btnBaoCao;

	public GUI_ThongKe(NhanVien nv) {
		setSize(1300, 749);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dao_HoaDon = new Dao_HoaDon();
		dao_ChiTietHoaDon = new Dao_ChiTietHoaDon();
		dao_SanPham = new Dao_SanPham();
		dao_NhanVien = new Dao_NhanVien();
		nhanVien = nv;
		getContentPane().add(control_ThongKe());
	}
	/**
	 * Thống kê
	 * @return pnl thống kê
	 */
	public Component control_ThongKe() {
		
		//pnlCha
		pnThongKe = new JPanel() {
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
		pnThongKe.setBounds(0, 0, 1237, 664);
		pnThongKe.setBorder(null);
		pnThongKe.setLayout(null);

		//pnl thống kê tổng quát
		JPanel pnTKTongQuat = new JPanel();
		pnTKTongQuat.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnTKTongQuat.setBackground(Color.WHITE);
		pnTKTongQuat.setBounds(10, 11, 1217, 158);
		pnThongKe.add(pnTKTongQuat);
		pnTKTongQuat.setLayout(null);
			//pnl thống kê tổng sản phẩm
		JPanel pnTKTongSP = new JPanel();
		pnTKTongSP.setBorder(new MatteBorder(20, 0, 0, 0, (Color) Color.ORANGE));
		pnTKTongSP.setBounds(30, 40, 180, 100);
		pnTKTongQuat.add(pnTKTongSP);
		pnTKTongSP.setLayout(null);

		lblTKTongSP = new JLabel("Tổng Sản Phẩm");
		lblTKTongSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTKTongSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTKTongSP.setBounds(30, 10, 180, 26);
		pnTKTongQuat.add(lblTKTongSP);
		
		lblIconTongSP = new JLabel("");
		lblIconTongSP.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\TK_product.png"));
		lblIconTongSP.setBounds(10, 25, 160, 64);
		lblIconTongSP.setIconTextGap(20);
		lblIconTongSP.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnTKTongSP.add(lblIconTongSP);

			//pnl thống kê tổng số nhân viên
		JPanel pnTKTongNV = new JPanel();
		pnTKTongNV.setBorder(new MatteBorder(20, 0, 0, 0, (Color) new Color(255, 0, 0)));
		pnTKTongNV.setBounds(340, 40, 180, 100);
		pnTKTongQuat.add(pnTKTongNV);
		pnTKTongNV.setLayout(null);

		JLabel lblTKTongNV = new JLabel("Tổng Nhân Viên");
		lblTKTongNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTKTongNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTKTongNV.setBounds(340, 10, 180, 26);
		pnTKTongQuat.add(lblTKTongNV);
		
		lblIconTongNV = new JLabel("");
		lblIconTongNV.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\TK_employee.png"));
		lblIconTongNV.setBounds(10, 21, 150, 79);
		lblIconTongNV.setIconTextGap(30);
		lblIconTongNV.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnTKTongNV.add(lblIconTongNV);

			//pnl thống kê tổng số hóa đơn
		JPanel pnTKTongHD = new JPanel();
		pnTKTongHD.setBorder(new MatteBorder(20, 0, 0, 0, (Color) Color.BLUE));
		pnTKTongHD.setBounds(640, 40, 190, 100);
		pnTKTongQuat.add(pnTKTongHD);
		pnTKTongHD.setLayout(null);

		JLabel lblTKTongHD = new JLabel();
		if(nhanVien.getChucVu().equals("Nhân Viên")) {
			lblTKTongHD.setText("Tổng HĐ Tháng "+nhanVien.getMaNV());
			lblTKTongHD.setBounds(625, 10, 220, 26);
		} else {
			lblTKTongHD.setText("Tổng HĐ Tháng");
			lblTKTongHD.setBounds(640, 10, 190, 26);
		}
		lblTKTongHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblTKTongHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnTKTongQuat.add(lblTKTongHD);

		lblIconTongHD = new JLabel("");
		lblIconTongHD.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\6141384_bill_checklist_invoice_receipt_icon (1).png"));
		lblIconTongHD.setBounds(10, 22, 150, 78);
		lblIconTongHD.setIconTextGap(25);
		lblIconTongHD.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnTKTongHD.add(lblIconTongHD);

			//pnl thống kê tổng doanh thu
		JPanel pnTKTongDoanhThu = new JPanel();
		pnTKTongDoanhThu.setBorder(new MatteBorder(20, 0, 0, 0, (Color) Color.GREEN));
		pnTKTongDoanhThu.setBounds(965, 40, 220, 100);
		pnTKTongQuat.add(pnTKTongDoanhThu);
		pnTKTongDoanhThu.setLayout(null);

		JLabel lblTKTongDoanhThu = new JLabel();
		if(nhanVien.getChucVu().equals("Nhân Viên")) {
			lblTKTongDoanhThu.setText("Tổng DT Tháng "+nhanVien.getMaNV()+"(nghìn)");
			lblTKTongDoanhThu.setBounds(930, 10, 280, 26);
		} else {
			lblTKTongDoanhThu.setText("Tổng DT Tháng(nghìn)");
			lblTKTongDoanhThu.setBounds(955, 10, 240, 26);
		}
		lblTKTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTKTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnTKTongQuat.add(lblTKTongDoanhThu);
		
		lblIconTongDT = new JLabel("");
		lblIconTongDT.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\TK_turnover.png"));
		lblIconTongDT.setBounds(10, 25, 205, 75);
		lblIconTongDT.setIconTextGap(15);
		lblIconTongDT.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnTKTongDoanhThu.add(lblIconTongDT);

		//load dữ liệu thống kê tổng quát
		loadStatistics();

		//tạo các pnl thống kê
		pnChiTietTKSLMua = thongKeSLMua();
		pnChiTietTKSLTon = thongKeSLTon();
		pnChiTietTKHinhThucMua = thongKeHinhThucMua();
		pnChiTietTKGiamGia = thongKeGiamGia();
		pnChiTietTKDoanhThu = thongKeDoanhThu();

		pnThongKe.add(pnChiTietTKSLMua);
		pnThongKe.add(pnChiTietTKSLTon);
		pnThongKe.add(pnChiTietTKHinhThucMua);
		pnThongKe.add(pnChiTietTKGiamGia);
		pnThongKe.add(pnChiTietTKDoanhThu);

		pnChiTietTKSLTon.setVisible(false);
		pnChiTietTKHinhThucMua.setVisible(false);
		pnChiTietTKGiamGia.setVisible(false);
		pnChiTietTKDoanhThu.setVisible(false);

		//pnl hình thức thống kê chứa các button
		JPanel pnHinhThucThongKe = new JPanel();
		pnHinhThucThongKe.setOpaque(false);
		pnHinhThucThongKe.setBackground(Color.WHITE);
		pnHinhThucThongKe.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "H\u00ECnh th\u1EE9c th\u1ED1ng k\u00EA",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnHinhThucThongKe.setBounds(10, 175, 870, 80);
		pnThongKe.add(pnHinhThucThongKe);
		pnHinhThucThongKe.setLayout(null);

		btnTKSLMua = new JButton("Số lượng mua");
		formatButton(btnTKSLMua, "img\\IMG_LOGIN_SignIn\\countbuy.png");
		btnTKSLMua.setBounds(15, 25, 155, 40);
		pnHinhThucThongKe.add(btnTKSLMua);

		btnTKSLTon = new JButton("Số lượng tồn");
		btnTKSLTon.setFont(new Font("Tahoma", Font.BOLD, 12));
		formatButton(btnTKSLTon, "img\\IMG_LOGIN_SignIn\\stock.png");
		btnTKSLTon.setIconTextGap(10);
		btnTKSLTon.setBounds(186, 25, 155, 40);
		pnHinhThucThongKe.add(btnTKSLTon);

		btnTKHinhThucMua = new JButton("Hình thức mua");
		btnTKHinhThucMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTKHinhThucMua.setIconTextGap(8);
		formatButton(btnTKHinhThucMua, "img\\IMG_LOGIN_SignIn\\Order_Online.png");
		btnTKHinhThucMua.setBounds(358, 25, 155, 40);
		pnHinhThucThongKe.add(btnTKHinhThucMua);

		btnTKGiamGia = new JButton("Giảm giá");
		btnTKGiamGia.setMargin(new Insets(2, 0, 2, 14));
		btnTKGiamGia.setIconTextGap(8);
		btnTKGiamGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		formatButton(btnTKGiamGia, "img\\IMG_LOGIN_SignIn\\discount.png");
		btnTKGiamGia.setBounds(529, 25, 155, 40);
		pnHinhThucThongKe.add(btnTKGiamGia);

		btnTKDoanhThu = new JButton("Doanh thu");
		btnTKDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		formatButton(btnTKDoanhThu, "img\\IMG_LOGIN_SignIn\\turnover.png");
		btnTKDoanhThu.setIconTextGap(8);
		btnTKDoanhThu.setBounds(700, 25, 155, 40);
		pnHinhThucThongKe.add(btnTKDoanhThu);

		//đổi màu cho button được click vào, mặc định là thống kê số lượng mua
		changeColorButtonWhenClick(btnTKSLMua);

		btn_TKSLMua_ThongKe.setBounds(btn_TKSLTon_ThongKe.getBounds());
		btn_TKSLMua_ThongKe.setFont(btn_TKSLTon_ThongKe.getFont());
		btn_TKSLMua_ThongKe.setFocusable(false);
		btn_TKDT_ThongKe.setBounds(btn_TKSLTon_ThongKe.getBounds());
		btn_TKDT_ThongKe.setFont(btn_TKSLTon_ThongKe.getFont());
		btn_TKDT_ThongKe.setFocusable(false);

		//pn biểu đồ
		pnBieuDo = createPnBieuDo();
		pnBieuDo.setOpaque(false);
		pnThongKe.add(pnBieuDo);
		
		//báo cáo
		btnBaoCao = new JButton("Báo cáo");
		btnBaoCao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBaoCao.setFocusable(false);
		btnBaoCao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBaoCao.setBounds(893, 618, 333, 35);
		btnBaoCao.setEnabled(false);
		btnBaoCao.setIcon(new ImageIcon("img/IMG_LOGIN_SignIn/excel.png"));
		btnBaoCao.setBackground(new Color(250, 112, 112));
		pnThongKe.add(btnBaoCao);

		//Sự kiện
		btnTKSLMua.addMouseListener(this);
		btnTKSLTon.addMouseListener(this);
		btnTKHinhThucMua.addMouseListener(this);
		btnTKGiamGia.addMouseListener(this);
		btnTKDoanhThu.addMouseListener(this);

		btnTKSLMua.addActionListener(this);
		btnTKSLTon.addActionListener(this);
		btnTKHinhThucMua.addActionListener(this);
		btnTKGiamGia.addActionListener(this);
		btnTKDoanhThu.addActionListener(this);

		//Số lượng mua
		lbl_TKSLMua_MuiTen_NgayMua.addMouseListener(this);
		lbl_TKSLMua_Reset.addMouseListener(this);
		rad_TKSLMua_0den20.addActionListener(this);
		rad_TKSLMua_20den50.addActionListener(this);
		rad_TKSLMua_50den100.addActionListener(this);
		rad_TKSLMua_Tu100.addActionListener(this);
		rad_TKSLMua_Khac.addActionListener(this);
		lbl_TKSLMua_MuiTen.addMouseListener(this);
		lbl_TKSLMua_Infinity.addMouseListener(this);
		btn_TKSLMua_ThongKe.addActionListener(this);

		//Số lượng tồn
		rad_TKSLTon_0den50.addActionListener(this);
		rad_TKSLTon_50den100.addActionListener(this);
		rad_TKSLTon_100den300.addActionListener(this);
		rad_TKSLTon_300den500.addActionListener(this);
		rad_TKSLTon_Tu500.addActionListener(this);
		rad_TKSLTon_Khac.addActionListener(this);
		lbl_TKSLTon_MuiTen.addMouseListener(this);
		lbl_TKSLTon_Infinity.addMouseListener(this);
		btn_TKSLTon_ThongKe.addActionListener(this);

		//Hình thức mua
		lbl_TKHinhThucMua_MuiTen.addMouseListener(this);
		lbl_TKHinhThucMua_Reset.addMouseListener(this);
		btn_TKHinhThucMua_ThongKe.addActionListener(this);
		
		//Giảm giá
		rad_TKGG_0.addActionListener(this);
		rad_TKGG_10.addActionListener(this);
		rad_TKGG_30.addActionListener(this);
		rad_TKGG_50.addActionListener(this);
		rad_TKGG_70.addActionListener(this);
		rad_TKGG_Khac.addActionListener(this);
		lbl_TKGG_MuiTen.addMouseListener(this);
		lbl_TKGG_Infinity.addMouseListener(this);
		btn_TKGG_ThongKe.addActionListener(this);
		
		//Doanh thu
		chk_TKDT_ThoiGian.addActionListener(this);
		cbo_TKDT_ThongKeTheo.addActionListener(this);
		lbl_TKDT_MuiTen.addMouseListener(this);
		lbl_TKDT_Reset.addMouseListener(this);
		chk_TKDT_DoanhThu.addActionListener(this);
		rad_TKDT_comBoBox.addActionListener(this);
		cbo_TKDT_DoanhThu.addMouseListener(this);
		rad_TKDT_Khac.addActionListener(this);
		lbl_TKDT_MuiTen_DoanhThu.addMouseListener(this);
		lbl_TKDT_Infinity.addMouseListener(this);
		btn_TKDT_ThongKe.addActionListener(this);

		//Biểu đồ
		rad_TK_BDCot_15Nhieu.addActionListener(this);
		rad_TK_BDCot_15It.addActionListener(this);
		rad_TK_BDCot_TrungBinh.addActionListener(this);
		cbo_LoaiBieuDo.addActionListener(this);
		
		//Báo cáo
		btnBaoCao.addActionListener(this);
		
		return pnThongKe;
	}

	/**
	 * Thống kê số lượng mua
	 * @return pnl số lượng mua
	 */
	public JPanel thongKeSLMua() {
		JPanel pnChiTietTKSLMua = new JPanel();
		pnChiTietTKSLMua.setOpaque(false);
		pnChiTietTKSLMua.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Th\u1ED1ng k\u00EA theo s\u1ED1 l\u01B0\u1EE3ng mua", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnChiTietTKSLMua.setBounds(10, 260, 870, 393);
		pnChiTietTKSLMua.setLayout(null);

		JScrollPane sCR_TKSLMua = new JScrollPane();
		sCR_TKSLMua.setBounds(20, 85, 828, 285);
		pnChiTietTKSLMua.add(sCR_TKSLMua);

		tbl_TKSLMua = formatTable(sCR_TKSLMua, dtm_TKSLMua = new DefaultTableModel(new String[] 
				{ "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Mua", "Đánh Giá Mức Độ" },0));
		tbl_TKSLMua.getColumnModel().getColumn(1).setPreferredWidth(87);
		tbl_TKSLMua.getColumnModel().getColumn(2).setPreferredWidth(79);
		tbl_TKSLMua.getColumnModel().getColumn(3).setPreferredWidth(95);

		JLabel lbl_TKSLMua_NgayMua = new JLabel("Ngày mua:");
		lbl_TKSLMua_NgayMua.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_TKSLMua_NgayMua.setBounds(47, 25, 92, 20);
		pnChiTietTKSLMua.add(lbl_TKSLMua_NgayMua);

		dC_TKSLMua_BD = new JDateChooser();
		formatDateChooser(dC_TKSLMua_BD);
		dC_TKSLMua_BD.setBounds(142, 25, 95, 20);
		pnChiTietTKSLMua.add(dC_TKSLMua_BD);

		lbl_TKSLMua_MuiTen_NgayMua = new JLabel("");
		lbl_TKSLMua_MuiTen_NgayMua = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				, "img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKSLMua_MuiTen_NgayMua.setBounds(115, 58, 30, 16);
		pnChiTietTKSLMua.add(lbl_TKSLMua_MuiTen_NgayMua);

		pn_TKSLMua_KT_NgayMua = new JPanel();
		pn_TKSLMua_KT_NgayMua.setOpaque(false);
		pn_TKSLMua_KT_NgayMua.setLayout(null);
		pn_TKSLMua_KT_NgayMua.setBounds(142, 55, 116, 20);
		dC_TKSLMua_KT = new JDateChooser();
		formatDateChooser(dC_TKSLMua_KT);
		dC_TKSLMua_KT.setBounds(0, 0, 95, 20);
		pn_TKSLMua_KT_NgayMua.add(dC_TKSLMua_KT);
		lbl_TKSLMua_Reset=createIcon("img\\IMG_LOGIN_SignIn\\TK_reset_black.png"
				,"img\\IMG_LOGIN_SignIn\\TK_reset_red.png");
		lbl_TKSLMua_Reset.setBounds(100, 3, 16, 16);
		pn_TKSLMua_KT_NgayMua.add(lbl_TKSLMua_Reset);
		pn_TKSLMua_KT_NgayMua.setVisible(false);
		pnChiTietTKSLMua.add(pn_TKSLMua_KT_NgayMua);

		JLabel lbl_TKSLMua_SoLuong = new JLabel("Số lượng:");
		lbl_TKSLMua_SoLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_TKSLMua_SoLuong.setBounds(270, 25, 77, 20);
		pnChiTietTKSLMua.add(lbl_TKSLMua_SoLuong);

		rad_TKSLMua_0den20 = new JRadioButton("0-20");
		formatRadio(rad_TKSLMua_0den20);
		rad_TKSLMua_0den20.setSelected(true);
		rad_TKSLMua_0den20.setBounds(360, 25, 60, 23);
		pnChiTietTKSLMua.add(rad_TKSLMua_0den20);

		rad_TKSLMua_20den50 = new JRadioButton("20-50");
		formatRadio(rad_TKSLMua_20den50);
		rad_TKSLMua_20den50.setBounds(440, 25, 70, 23);
		pnChiTietTKSLMua.add(rad_TKSLMua_20den50);

		rad_TKSLMua_50den100 = new JRadioButton("50-100");
		formatRadio(rad_TKSLMua_50den100);
		rad_TKSLMua_50den100.setBounds(530, 25, 77, 23);
		pnChiTietTKSLMua.add(rad_TKSLMua_50den100);

		rad_TKSLMua_Tu100 = new JRadioButton("100-");
		formatRadio(rad_TKSLMua_Tu100);
		rad_TKSLMua_Tu100.setBounds(630, 25, 67, 23);
		pnChiTietTKSLMua.add(rad_TKSLMua_Tu100);

		rad_TKSLMua_Khac = new JRadioButton("khác");
		formatRadio(rad_TKSLMua_Khac);
		rad_TKSLMua_Khac.setBounds(360, 55, 60, 23);
		pnChiTietTKSLMua.add(rad_TKSLMua_Khac);

		ButtonGroup btnGR = new ButtonGroup();
		btnGR.add(rad_TKSLMua_0den20);
		btnGR.add(rad_TKSLMua_20den50);
		btnGR.add(rad_TKSLMua_50den100);
		btnGR.add(rad_TKSLMua_Tu100);
		btnGR.add(rad_TKSLMua_Khac);

		btn_TKSLMua_ThongKe = new JButton("Thống Kê");
		btn_TKSLMua_ThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKSLMua.add(btn_TKSLMua_ThongKe);

		spn_TKSLMua_BD = new JSpinner();
		spn_TKSLMua_BD.setFocusable(false);
		((SpinnerNumberModel) spn_TKSLMua_BD.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKSLMua_BD.getModel()).setMaximum(9999);
		spn_TKSLMua_BD.setBounds(445, 55, 60, 20);
		spn_TKSLMua_BD.setEnabled(false);
		pnChiTietTKSLMua.add(spn_TKSLMua_BD);

		pn_TKSLMua_KT = new JPanel();
		pn_TKSLMua_KT.setOpaque(false);
		pn_TKSLMua_KT.setLayout(null);
		pn_TKSLMua_KT.setVisible(false);
		pn_TKSLMua_KT.setBounds(535,55,90,20);
		spn_TKSLMua_KT = new JSpinner();
		((SpinnerNumberModel) spn_TKSLMua_KT.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKSLMua_KT.getModel()).setMaximum(9999);
		spn_TKSLMua_KT.setFocusable(false);
		spn_TKSLMua_KT.setBounds(0, 0, 65, 20);
		spn_TKSLMua_KT.setValue(50);
		pn_TKSLMua_KT.add(spn_TKSLMua_KT);
		lbl_TKSLMua_Infinity = new JLabel();
		lbl_TKSLMua_Infinity=createIcon("img\\IMG_LOGIN_SignIn\\TK_Infinity_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_Infinity_red.png");
		lbl_TKSLMua_Infinity.setBounds(70, 0, 20, 20);
		pn_TKSLMua_KT.add(lbl_TKSLMua_Infinity);
		pnChiTietTKSLMua.add(pn_TKSLMua_KT);

		lbl_TKSLMua_MuiTen = new JLabel("");
		lbl_TKSLMua_MuiTen = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				,"img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKSLMua_MuiTen.setBounds(513, 58, 20, 16);
		lbl_TKSLMua_MuiTen.setEnabled(false);
		pnChiTietTKSLMua.add(lbl_TKSLMua_MuiTen);

		return pnChiTietTKSLMua;
	}

	/**
	 * Thống kê số lượng tồn
	 * @return pnl số lượng tồn
	 */
	public JPanel thongKeSLTon() {
		JPanel pnChiTietTKSLTon = new JPanel();
		pnChiTietTKSLTon.setOpaque(false);
		pnChiTietTKSLTon.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Thống kê theo số lượng tồn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnChiTietTKSLTon.setSize(870, 393);
		pnChiTietTKSLTon.setLocation(10, 260);
		pnChiTietTKSLTon.setLayout(null);

		JScrollPane sCR_TKSLTon = new JScrollPane();
		sCR_TKSLTon.setBounds(20, 85, 828, 285);
		pnChiTietTKSLTon.add(sCR_TKSLTon);

		tbl_TKSLTon = formatTable(sCR_TKSLTon, dtm_TKSLTon = new DefaultTableModel(new String[] 
				{ "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Tồn", "Đánh Giá Mức Độ" }, 0));
		tbl_TKSLTon.getColumnModel().getColumn(1).setPreferredWidth(87);
		tbl_TKSLTon.getColumnModel().getColumn(2).setPreferredWidth(79);
		tbl_TKSLTon.getColumnModel().getColumn(3).setPreferredWidth(95);

		JLabel lbl_TKSLTon_SoLuong = new JLabel("Số lượng:");
		lbl_TKSLTon_SoLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_TKSLTon_SoLuong.setBounds(47, 25, 77, 20);
		pnChiTietTKSLTon.add(lbl_TKSLTon_SoLuong);

		rad_TKSLTon_0den50 = new JRadioButton("0-50");
		formatRadio(rad_TKSLTon_0den50);
		rad_TKSLTon_0den50.setSelected(true);
		rad_TKSLTon_0den50.setBounds(150, 25, 60, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_0den50);

		rad_TKSLTon_50den100 = new JRadioButton("50-100");
		formatRadio(rad_TKSLTon_50den100);
		rad_TKSLTon_50den100.setBounds(240, 25, 77, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_50den100);

		rad_TKSLTon_100den300 = new JRadioButton("100-300");
		formatRadio(rad_TKSLTon_100den300);
		rad_TKSLTon_100den300.setBounds(350, 25, 85, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_100den300);

		rad_TKSLTon_300den500 = new JRadioButton("300-500");
		formatRadio(rad_TKSLTon_300den500);
		rad_TKSLTon_300den500.setBounds(465, 25, 85, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_300den500);

		rad_TKSLTon_Tu500 = new JRadioButton("500-");
		formatRadio(rad_TKSLTon_Tu500);
		rad_TKSLTon_Tu500.setBounds(580, 25, 67, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_Tu500);

		rad_TKSLTon_Khac = new JRadioButton("khác");
		formatRadio(rad_TKSLTon_Khac);
		rad_TKSLTon_Khac.setBounds(150, 55, 60, 23);
		pnChiTietTKSLTon.add(rad_TKSLTon_Khac);

		ButtonGroup btnGr = new ButtonGroup();
		btnGr.add(rad_TKSLTon_0den50);
		btnGr.add(rad_TKSLTon_50den100);
		btnGr.add(rad_TKSLTon_100den300);
		btnGr.add(rad_TKSLTon_300den500);
		btnGr.add(rad_TKSLTon_Tu500);
		btnGr.add(rad_TKSLTon_Khac);

		lbl_TKSLTon_MuiTen = new JLabel("");
		lbl_TKSLTon_MuiTen = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				, "img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKSLTon_MuiTen.setBounds(330, 57, 23, 16);
		lbl_TKSLTon_MuiTen.setEnabled(false);
		pnChiTietTKSLTon.add(lbl_TKSLTon_MuiTen);

		spn_TKSLTon_BD = new JSpinner();
		spn_TKSLTon_BD.setBounds(245, 55, 70, 20);
		((SpinnerNumberModel) spn_TKSLTon_BD.getModel()).setStepSize(5);
		((SpinnerNumberModel) spn_TKSLTon_BD.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKSLTon_BD.getModel()).setMaximum(9999);
		pnChiTietTKSLTon.add(spn_TKSLTon_BD);

		pn_TKSLTon_KT = new JPanel();
		pn_TKSLTon_KT.setOpaque(false);
		pn_TKSLTon_KT.setLayout(null);
		pn_TKSLTon_KT.setVisible(false);
		pn_TKSLTon_KT.setBounds(355,55,105,20);
		spn_TKSLTon_KT = new JSpinner();
		spn_TKSLTon_KT.setBounds(0, 0, 80, 20);
		spn_TKSLTon_KT.setValue(50);
		((SpinnerNumberModel) spn_TKSLTon_KT.getModel()).setStepSize(5);
		((SpinnerNumberModel) spn_TKSLTon_KT.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKSLTon_KT.getModel()).setMaximum(9999);
		pn_TKSLTon_KT.add(spn_TKSLTon_KT);
		lbl_TKSLTon_Infinity = new JLabel();
		lbl_TKSLTon_Infinity=createIcon("img\\IMG_LOGIN_SignIn\\TK_Infinity_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_Infinity_red.png");
		lbl_TKSLTon_Infinity.setBounds(85, 0, 20, 20);
		pn_TKSLTon_KT.add(lbl_TKSLTon_Infinity);
		pnChiTietTKSLTon.add(pn_TKSLTon_KT);

		spn_TKSLTon_BD.setEnabled(false);
		spn_TKSLTon_KT.setEnabled(false);

		btn_TKSLTon_ThongKe = new JButton("Thống Kê");
		btn_TKSLTon_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_TKSLTon_ThongKe.setFocusable(false);
		btn_TKSLTon_ThongKe.setBounds(720, 24, 120, 50);
		btn_TKSLTon_ThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKSLTon.add(btn_TKSLTon_ThongKe);

		return pnChiTietTKSLTon;
	}

	/**
	 * Thống kê theo hình thức mua
	 * @return pnl hình thức mua
	 */
	public JPanel thongKeHinhThucMua() {
		JPanel pnChiTietTKHinhThucMua = new JPanel();
		pnChiTietTKHinhThucMua.setOpaque(false);
		int i = 2;
		pnChiTietTKHinhThucMua.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 0, 0), i), "Th\u1ED1ng k\u00EA theo hình thức mua hàng",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnChiTietTKHinhThucMua.setSize(870, 393);
		pnChiTietTKHinhThucMua.setLocation(10, 260);
		pnChiTietTKHinhThucMua.setLayout(null);

		JScrollPane sCR_TKHinhThucMua = new JScrollPane();
		sCR_TKHinhThucMua.setBounds(20, 58, 828, 270);
		pnChiTietTKHinhThucMua.add(sCR_TKHinhThucMua);
		
		JLabel lbl_TKHinhThucMua_TienMuaTrucTiep = new JLabel("Tổng tiền mua trực tiếp:");
		lbl_TKHinhThucMua_TienMuaTrucTiep.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_TKHinhThucMua_TienMuaTrucTiep.setBounds(450, 335, 170, 20);
		pnChiTietTKHinhThucMua.add(lbl_TKHinhThucMua_TienMuaTrucTiep);
		
		txt_TKHinhThucMua_TongTienMuaTrucTiep = new JTextField();
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setText("0");
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setEditable(false);
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setBounds(630, 335, 217, 20);
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		pnChiTietTKHinhThucMua.add(txt_TKHinhThucMua_TongTienMuaTrucTiep);
		txt_TKHinhThucMua_TongTienMuaTrucTiep.setColumns(10);
		
		JLabel lbl_TKHinhThucMua_TienMuaOnline = new JLabel("Tổng tiền mua online:");
		lbl_TKHinhThucMua_TienMuaOnline.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_TKHinhThucMua_TienMuaOnline.setBounds(450, 365, 170, 20);
		pnChiTietTKHinhThucMua.add(lbl_TKHinhThucMua_TienMuaOnline);
		
		txt_TKHinhThucMua_TongTienMuaOnline = new JTextField();
		txt_TKHinhThucMua_TongTienMuaOnline.setText("0");
		txt_TKHinhThucMua_TongTienMuaOnline.setEditable(false);
		txt_TKHinhThucMua_TongTienMuaOnline.setBounds(630, 365, 217, 20);
		txt_TKHinhThucMua_TongTienMuaOnline.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		txt_TKHinhThucMua_TongTienMuaOnline.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnChiTietTKHinhThucMua.add(txt_TKHinhThucMua_TongTienMuaOnline);
		txt_TKHinhThucMua_TongTienMuaOnline.setColumns(10);
		
		tbl_TKHinhThucMua = formatTable(sCR_TKHinhThucMua, dtm_TKHinhThucMua = new DefaultTableModel(new String[] 
				{ "Mã Hóa Đơn", "Ngày Lập", "Hình Thức Mua", "Tổng Tiền" }, 0));
		tbl_TKHinhThucMua.getColumnModel().getColumn(1).setPreferredWidth(87);
		tbl_TKHinhThucMua.getColumnModel().getColumn(i).setPreferredWidth(79);
		tbl_TKHinhThucMua.getColumnModel().getColumn(3).setPreferredWidth(95);

		//sort table
		sortTableString(tbl_TKHinhThucMua, 2);
		
		btn_TKHinhThucMua_ThongKe = new JButton("Thống Kê");
		btn_TKHinhThucMua_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_TKHinhThucMua_ThongKe.setFocusable(false);
		btn_TKHinhThucMua_ThongKe.setBounds(470, 20, 92, 30);
		btn_TKHinhThucMua_ThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKHinhThucMua.add(btn_TKHinhThucMua_ThongKe);

		JLabel lbl_TKHinhThucMua_NgayNhap = new JLabel("Ngày mua:");
		lbl_TKHinhThucMua_NgayNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_TKHinhThucMua_NgayNhap.setBounds(50, 25, 130, 20);
		pnChiTietTKHinhThucMua.add(lbl_TKHinhThucMua_NgayNhap);

		dC_TKHinhThucMua_BD = new JDateChooser();
		formatDateChooser(dC_TKHinhThucMua_BD);
		dC_TKHinhThucMua_BD.setBounds(170, 25, 95, 20);
		pnChiTietTKHinhThucMua.add(dC_TKHinhThucMua_BD);

		lbl_TKHinhThucMua_MuiTen = new JLabel("");
		lbl_TKHinhThucMua_MuiTen = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				,"img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKHinhThucMua_MuiTen.setBounds(290, 28, 30, 16);
		pnChiTietTKHinhThucMua.add(lbl_TKHinhThucMua_MuiTen);
		
		pn_TKHinhThucMua_KT = new JPanel();
		pn_TKHinhThucMua_KT.setOpaque(false);
		pn_TKHinhThucMua_KT.setLayout(null);
		pn_TKHinhThucMua_KT.setBounds(330, 25, 116, 20);
		dC_TKHinhThucMua_KT = new JDateChooser();
		formatDateChooser(dC_TKHinhThucMua_KT);
		dC_TKHinhThucMua_KT.setBounds(0, 0, 95, 20);
		pn_TKHinhThucMua_KT.add(dC_TKHinhThucMua_KT);
		lbl_TKHinhThucMua_Reset=createIcon("img\\IMG_LOGIN_SignIn\\TK_reset_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_reset_red.png");
		lbl_TKHinhThucMua_Reset.setBounds(100, 3, 16, 16);
		pn_TKHinhThucMua_KT.add(lbl_TKHinhThucMua_Reset);
		pnChiTietTKHinhThucMua.add(pn_TKHinhThucMua_KT);

		return pnChiTietTKHinhThucMua;
	}

	/**
	 * Thống kê giảm giá
	 * @return pnl giảm giá
	 */
	public JPanel thongKeGiamGia() {
		JPanel pnChiTietTKGiamGia = new JPanel();
		pnChiTietTKGiamGia.setOpaque(false);
		pnChiTietTKGiamGia.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Th\u1ED1ng k\u00EA s\u1EA3n ph\u1EA9m gi\u1EA3m gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(255, 0, 0)));
		pnChiTietTKGiamGia.setSize(870, 393);
		pnChiTietTKGiamGia.setLocation(10, 260);
		pnChiTietTKGiamGia.setLayout(null);

		JScrollPane sCR_TKGG = new JScrollPane();
		sCR_TKGG.setBounds(20, 58, 828, 311);
		pnChiTietTKGiamGia.add(sCR_TKGG);

		tbl_TKGG = formatTable(sCR_TKGG, dtm_TKGG = new DefaultTableModel(new String[] 
				{ "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Tồn", "Khuyến Mãi" }, 0));
		tbl_TKGG.getColumnModel().getColumn(1).setPreferredWidth(87);
		tbl_TKGG.getColumnModel().getColumn(2).setPreferredWidth(79);
		tbl_TKGG.getColumnModel().getColumn(3).setPreferredWidth(79);

		JLabel lblKhuyenMai_TKGG = new JLabel("Khuyến mãi:");
		lblKhuyenMai_TKGG.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKhuyenMai_TKGG.setBounds(47, 25, 134, 20);
		pnChiTietTKGiamGia.add(lblKhuyenMai_TKGG);

		rad_TKGG_0 = new JRadioButton("0%");
		formatRadio(rad_TKGG_0);
		rad_TKGG_0.setSelected(true);
		rad_TKGG_0.setBounds(165, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_0);

		rad_TKGG_10 = new JRadioButton("10%");
		formatRadio(rad_TKGG_10);
		rad_TKGG_10.setBounds(235, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_10);

		rad_TKGG_30 = new JRadioButton("30%");
		formatRadio(rad_TKGG_30);
		rad_TKGG_30.setBounds(305, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_30);

		rad_TKGG_50 = new JRadioButton("50%");
		formatRadio(rad_TKGG_50);
		rad_TKGG_50.setBounds(375, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_50);

		rad_TKGG_70 = new JRadioButton("70%");
		formatRadio(rad_TKGG_70);
		rad_TKGG_70.setBounds(445, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_70);

		rad_TKGG_Khac = new JRadioButton("khác");
		formatRadio(rad_TKGG_Khac);
		rad_TKGG_Khac.setBounds(510, 25, 59, 23);
		pnChiTietTKGiamGia.add(rad_TKGG_Khac);

		ButtonGroup btnGR = new ButtonGroup();
		btnGR.add(rad_TKGG_0);
		btnGR.add(rad_TKGG_10);
		btnGR.add(rad_TKGG_30);
		btnGR.add(rad_TKGG_50);
		btnGR.add(rad_TKGG_70);
		btnGR.add(rad_TKGG_Khac);

		spn_TKGG_BD = new JSpinner();
		spn_TKGG_BD.setBounds(570, 25, 60, 20);
		((SpinnerNumberModel) spn_TKGG_BD.getModel()).setStepSize(5);
		((SpinnerNumberModel) spn_TKGG_BD.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKGG_BD.getModel()).setMaximum(100);
		pnChiTietTKGiamGia.add(spn_TKGG_BD);

		lbl_TKGG_MuiTen = new JLabel("");
		lbl_TKGG_MuiTen = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				, "img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKGG_MuiTen.setBounds(636, 28, 20, 16);
		lbl_TKGG_MuiTen.setEnabled(false);
		pnChiTietTKGiamGia.add(lbl_TKGG_MuiTen);

		pn_TKGG_KT = new JPanel();
		pn_TKGG_KT.setOpaque(false);
		pn_TKGG_KT.setLayout(null);
		pn_TKGG_KT.setVisible(false);
		pn_TKGG_KT.setBounds(658,25,85,20);
		spn_TKGG_KT = new JSpinner();
		spn_TKGG_KT.setBounds(0, 0, 60, 20);
		spn_TKGG_KT.setValue(20);
		((SpinnerNumberModel) spn_TKGG_KT.getModel()).setStepSize(5);
		((SpinnerNumberModel) spn_TKGG_KT.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKGG_KT.getModel()).setMaximum(100);
		pn_TKGG_KT.add(spn_TKGG_KT);
		lbl_TKGG_Infinity = new JLabel();
		lbl_TKGG_Infinity=createIcon("img\\IMG_LOGIN_SignIn\\TK_Infinity_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_Infinity_red.png");
		lbl_TKGG_Infinity.setBounds(65, 0, 20, 20);
		pn_TKGG_KT.add(lbl_TKGG_Infinity);
		pnChiTietTKGiamGia.add(pn_TKGG_KT);
		
		spn_TKGG_BD.setEnabled(false);
		spn_TKGG_KT.setEnabled(false);

		btn_TKGG_ThongKe = new JButton("Thống Kê");
		btn_TKGG_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_TKGG_ThongKe.setFocusable(false);
		btn_TKGG_ThongKe.setBounds(750, 21, 95, 30);
		btn_TKGG_ThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKGiamGia.add(btn_TKGG_ThongKe);

		return pnChiTietTKGiamGia;
	}

	/**
	 * Thống kê doanh thu
	 * @return pnl doanh thu
	 */
	public JPanel thongKeDoanhThu() {
		JPanel pnChiTietTKDoanhThu = new JPanel();
		pnChiTietTKDoanhThu.setOpaque(false);
		pnChiTietTKDoanhThu.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Th\u1ED1ng k\u00EA doanh thu", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnChiTietTKDoanhThu.setSize(870, 393);
		pnChiTietTKDoanhThu.setLocation(10, 260);
		pnChiTietTKDoanhThu.setLayout(null);

		JScrollPane sCR_TKDT = new JScrollPane();
		sCR_TKDT.setBounds(20, 88, 828, 251);
		pnChiTietTKDoanhThu.add(sCR_TKDT);

		tbl_TKDT = formatTable(sCR_TKDT, dtm_TKDT = new DefaultTableModel(new String[] 
				{ "Ngày Lập Hóa Đơn", "Tổng Tiền" }, 0));
		tbl_TKDT.getColumnModel().getColumn(1).setPreferredWidth(86);

		btn_TKDT_ThongKe = new JButton("Thống Kê");
		btn_TKDT_ThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKDoanhThu.add(btn_TKDT_ThongKe);

		cbo_TKDT_ThongKeTheo = new JComboBox<String>();
		cbo_TKDT_ThongKeTheo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbo_TKDT_ThongKeTheo.addItem("Ngày");
		cbo_TKDT_ThongKeTheo.addItem("Tháng");
		cbo_TKDT_ThongKeTheo.addItem("Quý");
		cbo_TKDT_ThongKeTheo.addItem("Năm");
		cbo_TKDT_ThongKeTheo.setBounds(195, 25, 100, 20);
		cbo_TKDT_ThongKeTheo.setFocusable(false);
		pnChiTietTKDoanhThu.add(cbo_TKDT_ThongKeTheo);

		pn_TKDT_Ngay = new JPanel();
		pn_TKDT_Ngay.setOpaque(false);
		pn_TKDT_Ngay.setBounds(345, 25, 362, 20);
		pnChiTietTKDoanhThu.add(pn_TKDT_Ngay);
		pn_TKDT_Ngay.setLayout(null);

		pn_TKDT_Thang = new JPanel();
		pn_TKDT_Thang.setOpaque(false);
		pn_TKDT_Thang.setBounds(345, 25, 362, 20);
		pnChiTietTKDoanhThu.add(pn_TKDT_Thang);
		pn_TKDT_Thang.setLayout(null);

		pn_TKDT_Quy = new JPanel();
		pn_TKDT_Quy.setOpaque(false);
		pn_TKDT_Quy.setBounds(345, 25, 362, 20);
		pnChiTietTKDoanhThu.add(pn_TKDT_Quy);
		pn_TKDT_Quy.setLayout(null);

		pn_TKDT_Nam = new JPanel();
		pn_TKDT_Nam.setOpaque(false);
		pn_TKDT_Nam.setBounds(345, 25, 362, 20);
		pnChiTietTKDoanhThu.add(pn_TKDT_Nam);
		pn_TKDT_Nam.setLayout(null);

		// Thống kê theo ngày
		dC_TKDT_Ngay_BD = new JDateChooser();
		formatDateChooser(dC_TKDT_Ngay_BD);
		dC_TKDT_Ngay_BD.setBounds(55, 0, 95, 20);
		pn_TKDT_Ngay.add(dC_TKDT_Ngay_BD);
		
		dC_TKDT_Ngay_KT = new JDateChooser();
		formatDateChooser(dC_TKDT_Ngay_KT);
		dC_TKDT_Ngay_KT.setVisible(false);
		dC_TKDT_Ngay_KT.setBounds(190, 0, 95, 20);
		pn_TKDT_Ngay.add(dC_TKDT_Ngay_KT);
		lbl_TKDT_Reset=createIcon("img\\IMG_LOGIN_SignIn\\TK_reset_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_reset_red.png");
		lbl_TKDT_Reset.setBounds(290, 3, 16, 16);
		lbl_TKDT_Reset.setVisible(false);
		pn_TKDT_Ngay.add(lbl_TKDT_Reset);
		
		// Thống kê theo tháng
		cbo_TKDT_Thang_BD = new JComboBox<String>();
		cbo_TKDT_Thang_BD.setBounds(0, 0, 90, 20);
		pn_TKDT_Thang.add(cbo_TKDT_Thang_BD);
		for (int i = 1; i <= 12; i++) {
			cbo_TKDT_Thang_BD.addItem("Tháng " + i);
		};
		cbo_TKDT_Thang_BD.setSelectedItem("Tháng " + LocalDate.now().getMonthValue());

		yC_TKDT_Thang_BD = new JYearChooser();
		yC_TKDT_Thang_BD.setBounds(95, 0, 55, 20);
		pn_TKDT_Thang.add(yC_TKDT_Thang_BD);

		cbo_TKDT_Thang_KT = new JComboBox<String>();
		cbo_TKDT_Thang_KT.setBounds(190, 0, 90, 20);
		cbo_TKDT_Thang_KT.setVisible(false);
		pn_TKDT_Thang.add(cbo_TKDT_Thang_KT);
		for (int i = 0; i < cbo_TKDT_Thang_BD.getItemCount(); i++) {
			cbo_TKDT_Thang_KT.addItem(cbo_TKDT_Thang_BD.getItemAt(i));
		}
		cbo_TKDT_Thang_KT.setSelectedIndex(cbo_TKDT_Thang_BD.getSelectedIndex());

		yC_TKDT_Thang_KT = new JYearChooser();
		yC_TKDT_Thang_KT.setVisible(false);
		yC_TKDT_Thang_KT.setBounds(286, 0, 55, 20);
		pn_TKDT_Thang.add(yC_TKDT_Thang_KT);
		
		// Thống kê theo Quý
		cbo_TKDT_Quy_BD = new JComboBox<String>();
		cbo_TKDT_Quy_BD.addItem("Quý 1");
		cbo_TKDT_Quy_BD.addItem("Quý 2");
		cbo_TKDT_Quy_BD.addItem("Quý 3");
		cbo_TKDT_Quy_BD.addItem("Quý 4");
		cbo_TKDT_Quy_BD.setBounds(0, 0, 90, 20);
		cbo_TKDT_Quy_BD.setSelectedItem("Quý "+ getQuarterOfDate(LocalDate.now()));
		pn_TKDT_Quy.add(cbo_TKDT_Quy_BD);

		yC_TKDT_Quy_BD = new JYearChooser();
		yC_TKDT_Quy_BD.setBounds(95, 0, 55, 20);
		pn_TKDT_Quy.add(yC_TKDT_Quy_BD);

		cbo_TKDT_Quy_KT = new JComboBox<String>();
		cbo_TKDT_Quy_KT.setVisible(false);
		for (int i = 0; i < cbo_TKDT_Quy_BD.getItemCount(); i++) {
			cbo_TKDT_Quy_KT.addItem(cbo_TKDT_Quy_BD.getItemAt(i));
		}
		cbo_TKDT_Quy_KT.setBounds(190, 0, 90, 20);
		cbo_TKDT_Quy_KT.setSelectedItem("Quý "+ getQuarterOfDate(LocalDate.now()));
		pn_TKDT_Quy.add(cbo_TKDT_Quy_KT);

		yC_TKDT_Quy_KT = new JYearChooser();
		yC_TKDT_Quy_KT.setVisible(false);
		yC_TKDT_Quy_KT.setBounds(285, 0, 55, 20);
		pn_TKDT_Quy.add(yC_TKDT_Quy_KT);
		
		// Thống kê theo năm
		yC_TKDT_Nam_BD = new JYearChooser();
		yC_TKDT_Nam_BD.setBounds(55, 0, 95, 20);
		pn_TKDT_Nam.add(yC_TKDT_Nam_BD);

		yC_TKDT_Nam_KT = new JYearChooser();
		yC_TKDT_Nam_KT.setVisible(false);
		yC_TKDT_Nam_KT.setBounds(190, 0, 95, 20);
		pn_TKDT_Nam.add(yC_TKDT_Nam_KT);

		lbl_TKDT_MuiTen = new JLabel("");
		lbl_TKDT_MuiTen = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				, "img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKDT_MuiTen.setBounds(160, 3, 16, 16);
		pn_TKDT_Ngay.add(lbl_TKDT_MuiTen);

		//Doanh thu
		chk_TKDT_ThoiGian = new JCheckBox("  Thời gian:");
		chk_TKDT_ThoiGian.setOpaque(false);
		chk_TKDT_ThoiGian.setSelected(true);
		chk_TKDT_ThoiGian.setFocusable(false);
		chk_TKDT_ThoiGian.setFont(new Font("Tahoma", Font.BOLD, 14));
		chk_TKDT_ThoiGian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chk_TKDT_ThoiGian.setBounds(20, 25, 110, 20);
		pnChiTietTKDoanhThu.add(chk_TKDT_ThoiGian);

		chk_TKDT_DoanhThu = new JCheckBox("  Doanh thu:");
		chk_TKDT_DoanhThu.setOpaque(false);
		chk_TKDT_DoanhThu.setSelected(true);
		chk_TKDT_DoanhThu.setFocusable(false);
		chk_TKDT_DoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		chk_TKDT_DoanhThu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chk_TKDT_DoanhThu.setBounds(20, 55, 115, 20);
		pnChiTietTKDoanhThu.add(chk_TKDT_DoanhThu);

		rad_TKDT_comBoBox = new JRadioButton("");
		formatRadio(rad_TKDT_comBoBox);
		rad_TKDT_comBoBox.setSelected(true);
		rad_TKDT_comBoBox.setBounds(170, 55, 20, 20);
		pnChiTietTKDoanhThu.add(rad_TKDT_comBoBox);

		cbo_TKDT_DoanhThu = new JComboBox<String>();
		cbo_TKDT_DoanhThu.addItem("0-1tr");
		cbo_TKDT_DoanhThu.addItem("1tr-3tr");
		cbo_TKDT_DoanhThu.addItem("3tr-5tr");
		cbo_TKDT_DoanhThu.addItem("5tr-10tr");
		cbo_TKDT_DoanhThu.addItem("10tr---");
		cbo_TKDT_DoanhThu.setBounds(195, 55, 100, 20);
		cbo_TKDT_DoanhThu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnChiTietTKDoanhThu.add(cbo_TKDT_DoanhThu);

		rad_TKDT_Khac = new JRadioButton("khác(nghìn)");
		formatRadio(rad_TKDT_Khac);
		rad_TKDT_Khac.setBounds(320, 55, 110, 20);
		pnChiTietTKDoanhThu.add(rad_TKDT_Khac);

		spn_TKDT_BD = new JSpinner();
		((SpinnerNumberModel) spn_TKDT_BD.getModel()).setStepSize(500);
		((SpinnerNumberModel) spn_TKDT_BD.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKDT_BD.getModel()).setMaximum(999999);
		spn_TKDT_BD.setEnabled(false);
		spn_TKDT_BD.setBounds(430, 55, 65, 20);
		pnChiTietTKDoanhThu.add(spn_TKDT_BD);

		lbl_TKDT_MuiTen_DoanhThu = new JLabel("");
		lbl_TKDT_MuiTen_DoanhThu = createIcon("img\\IMG_LOGIN_SignIn\\TKGG_arrow_black.png"
				, "img\\IMG_LOGIN_SignIn\\TKGG_arrow_red.png");
		lbl_TKDT_MuiTen_DoanhThu.setEnabled(false);
		lbl_TKDT_MuiTen_DoanhThu.setBounds(505, 58, 16, 16);
		pnChiTietTKDoanhThu.add(lbl_TKDT_MuiTen_DoanhThu);

		pn_TKDT_KT = new JPanel();
		pn_TKDT_KT.setOpaque(false);
		pn_TKDT_KT.setLayout(null);
		pn_TKDT_KT.setVisible(false);
		pn_TKDT_KT.setBounds(530, 55, 105, 20);
		spn_TKDT_KT = new JSpinner();
		((SpinnerNumberModel) spn_TKDT_KT.getModel()).setStepSize(500);
		((SpinnerNumberModel) spn_TKDT_KT.getModel()).setMinimum(0);
		((SpinnerNumberModel) spn_TKDT_KT.getModel()).setMaximum(999999);
		spn_TKDT_KT.setValue(2000);
		spn_TKDT_KT.setBounds(0, 0, 65, 20);
		pn_TKDT_KT.add(spn_TKDT_KT);
		lbl_TKDT_Infinity = new JLabel();
		lbl_TKDT_Infinity=createIcon("img\\IMG_LOGIN_SignIn\\TK_Infinity_black.png"
				, "img\\IMG_LOGIN_SignIn\\TK_Infinity_red.png");
		lbl_TKDT_Infinity.setBounds(78, 0, 20, 20);
		pn_TKDT_KT.add(lbl_TKDT_Infinity);
		pnChiTietTKDoanhThu.add(pn_TKDT_KT);
		
		ButtonGroup btnGR = new ButtonGroup();
		btnGR.add(rad_TKDT_comBoBox);
		btnGR.add(rad_TKDT_Khac);

		JLabel lbl_TKDT_TongDT = new JLabel("Tổng Doanh Thu:");
		lbl_TKDT_TongDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_TKDT_TongDT.setBounds(518, 355, 129, 20);
		pnChiTietTKDoanhThu.add(lbl_TKDT_TongDT);
		
		txt_TKDT_TongDoanhThu = new JTextField();
		txt_TKDT_TongDoanhThu.setText("0");
		txt_TKDT_TongDoanhThu.setEditable(false);
		txt_TKDT_TongDoanhThu.setBounds(645, 355, 185, 20);
		txt_TKDT_TongDoanhThu.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		txt_TKDT_TongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnChiTietTKDoanhThu.add(txt_TKDT_TongDoanhThu);
		txt_TKDT_TongDoanhThu.setColumns(10);

		pn_TKDT_Thang.setVisible(false);
		pn_TKDT_Quy.setVisible(false);
		pn_TKDT_Nam.setVisible(false);

		return pnChiTietTKDoanhThu;
	}
	
	/**
	 * tạo panel biểu đồ thống kê
	 * @return pnl biểu đồ
	 */
	public JPanel createPnBieuDo() {
		JPanel pnBieuDo = new JPanel();
		pnBieuDo.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Bi\u1EC3u \u0111\u1ED3",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnBieuDo.setBounds(891, 175, 336, 443);
		pnBieuDo.setLayout(null);
		
		pn_HinhBieuDo = new JPanel();
		pn_HinhBieuDo.setOpaque(false);
		pn_HinhBieuDo.setBounds(8, 190, 320, 250);
		pn_HinhBieuDo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pn_HinhBieuDo.setLayout(null);
		pnBieuDo.add(pn_HinhBieuDo);
		
		cbo_LoaiBieuDo = new JComboBox<String>();
		cbo_LoaiBieuDo.setFocusable(false);
		cbo_LoaiBieuDo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbo_LoaiBieuDo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		cbo_LoaiBieuDo.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbo_LoaiBieuDo.addItem("Biểu đồ cột");
		cbo_LoaiBieuDo.addItem("Biểu đồ tròn");
		cbo_LoaiBieuDo.setBounds(15, 27, 306, 33);
		pnBieuDo.add(cbo_LoaiBieuDo);
		
		pn_BDCot_ChonSP = new JPanel();
		pn_BDCot_ChonSP.setOpaque(false);
		pn_BDCot_ChonSP.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Ch\u1ECDn s\u1EA3n ph\u1EA9m(tối đa 15 sản phẩm)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_BDCot_ChonSP.setBounds(15, 75, 306, 110);
		pnBieuDo.add(pn_BDCot_ChonSP);
		pn_BDCot_ChonSP.setLayout(null);
		
		rad_TK_BDCot_15Nhieu = new JRadioButton("15 sản phẩm nhiều nhất");
		formatRadio(rad_TK_BDCot_15Nhieu);
		rad_TK_BDCot_15Nhieu.setSelected(true);
		rad_TK_BDCot_15Nhieu.setBounds(10, 20, 250, 25);
		pn_BDCot_ChonSP.add(rad_TK_BDCot_15Nhieu);
		
		rad_TK_BDCot_15It = new JRadioButton("15 sản phẩm ít nhất");
		formatRadio(rad_TK_BDCot_15It);
		rad_TK_BDCot_15It.setBounds(10, 45, 220, 25);
		pn_BDCot_ChonSP.add(rad_TK_BDCot_15It);
		
		rad_TK_BDCot_TrungBinh = new JRadioButton("Ít - Trung Bình - Nhiều");
		formatRadio(rad_TK_BDCot_TrungBinh);
		rad_TK_BDCot_TrungBinh.setBounds(10, 70, 190, 25);
		pn_BDCot_ChonSP.add(rad_TK_BDCot_TrungBinh);
		
		ButtonGroup btnGR = new ButtonGroup();
		btnGR.add(rad_TK_BDCot_15Nhieu);
		btnGR.add(rad_TK_BDCot_15It);
		btnGR.add(rad_TK_BDCot_TrungBinh);
		btnGR.add(rad_TK_BDCot_Chon);
		
		pn_BDTron_MoTa = new JPanel();
		pn_BDTron_MoTa.setOpaque(false);
		pn_BDTron_MoTa.setVisible(false);
		pn_BDTron_MoTa.setBounds(15, 75, 306, 110);
		pnBieuDo.add(pn_BDTron_MoTa);
		pn_BDTron_MoTa.setLayout(null);
		
		lbl_BDTron_MoTa = new JLabel("<html><p>[Biểu đồ tròn mô tả tỷ lệ phần trăm(%) số lượng mua của các sản phẩm trong khoảng thời gian]</p></html>");
		lbl_BDTron_MoTa.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_BDTron_MoTa.setBounds(0, 0, 306, 110);
		pn_BDTron_MoTa.add(lbl_BDTron_MoTa);
		
		return pnBieuDo;
	}

	/**
	 * 
	 * @param path : hình label khi trỏ chuột ra
	 * @param path_Entered : hình label khi trỏ chuột vào
	 * @return lbl đã được định dạng
	 */
	public JLabel createIcon(String path, String path_Entered) {
		JLabel lblIcon = new JLabel();
		lblIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIcon.setIcon(new ImageIcon(path));
		lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblIcon.setIcon(new ImageIcon(path_Entered));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblIcon.setIcon(new ImageIcon(path));
			}
		});
		return lblIcon;
	}
	
	/**
	 * Định dạng cho button
	 * @param btn : button cần định dạng
	 * @param img : icon của button
	 */
	public void formatButton(JButton btn, String img) {
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		btn.setBackground(new Color(255, 245, 228));
		btn.setMargin(new Insets(2, 0, 2, 14));
		btn.setIcon(new ImageIcon(img));
		btn.setFocusable(false);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	/**
	 * Định dạng cho radio
	 * @param rad : radio cần định dạng
	 */
	public void formatRadio(JRadioButton rad) {
		rad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rad.setOpaque(false);
		rad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rad.setFocusable(false);
	}
	
	/**
	 * Định dạng cho DateChooser
	 * @param dC : dateChooser cần định dạng
	 */
	public void formatDateChooser(JDateChooser dC) {
		dC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dC.setOpaque(false);
		((JTextField) dC.getDateEditor().getUiComponent()).setEditable(false);
		((JTextField) dC.getDateEditor().getUiComponent()).setHorizontalAlignment((int) CENTER_ALIGNMENT);
		dC.setBorder(new LineBorder(new Color(122, 138, 153)));
		dC.setDateFormatString("dd-MM-y");
		dC.setDate(new Date(new Date().getTime()));
		dC.setMaxSelectableDate(new Date(new Date().getTime()));
		((JTextField) dC.getDateEditor().getUiComponent()).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dC.getCalendarButton().doClick();
			}
		});
	}

	/**
	 * Định dạng table
	 * @param scr : thanh cuộn table
	 * @param dfm : model của table
	 * @return table đã được định dạng
	 */
	public RSTableMetro formatTable(JScrollPane scr, DefaultTableModel dfm) {
		RSTableMetro tbl = new RSTableMetro() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbl.setModel(dfm);
		tbl.setFocusable(false);
		scr.setViewportView(tbl);
		tbl.setSelectionMode(0);
		tbl.setRowMargin(1);
		tbl.setRowHeight(20);
		tbl.setGrosorBordeFilas(0);
		tbl.setGridColor(Color.RED);
		tbl.setFuenteHead(new Font("Tahoma", Font.BOLD, 12));
		tbl.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 11));
		tbl.setFuenteFilas(new Font("Tahoma", Font.BOLD, 11));
		tbl.setColorSelBackgound(new Color(255, 99, 71));
		tbl.setColorFilasForeground2(Color.BLACK);
		tbl.setColorFilasForeground1(Color.BLACK);
		tbl.setColorFilasBackgound2(Color.WHITE);
		tbl.setColorFilasBackgound1(Color.WHITE);
		tbl.setColorBordeHead(new Color(204, 0, 51));
		tbl.setColorBordeFilas(Color.RED);
		tbl.setColorBackgoundHead(new Color(204, 0, 51));
		tbl.setAltoHead(30);
		return tbl;
	}

	/**
	 * Đổi màu button khi trỏ chuột vào
	 * @param btn : button cần thực hiện
	 */
	public void changeColorButtonWhenEntered(JButton btn) {
		btn.setBackground(new Color(255, 209, 209));
		btn.setBorder(new LineBorder(Color.YELLOW, 2, true));
	}

	/**
	 * Đổi màu cho button khi trỏ chuột ra ngoài
	 * @param btn : button cần thực hiện
	 * @param isSelected : kiểm tra chức năng button có đang được lựa chọn để đổi màu cho phù hợp 
	 */
	public void changeColorButtonWhenExited(JButton btn, boolean isSelected) {
		if (!isSelected)
			btn.setBackground(new Color(255, 245, 228));
		else
			btn.setBackground(new Color(250, 112, 112));
		btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
	}

	/**
	 * Đổi màu cho button khi click vào chức năng
	 * @param btn : button cần thực hiện
	 */
	public void changeColorButtonWhenClick(JButton btn) {
		if (btnSelected == btnTKSLMua.getText())
			btnTKSLMua.setBackground(new Color(255, 245, 228));
		else if (btnSelected == btnTKSLTon.getText())
			btnTKSLTon.setBackground(new Color(255, 245, 228));
		else if (btnSelected == btnTKHinhThucMua.getText())
			btnTKHinhThucMua.setBackground(new Color(255, 245, 228));
		else if (btnSelected == btnTKGiamGia.getText())
			btnTKGiamGia.setBackground(new Color(255, 245, 228));
		else
			btnTKDoanhThu.setBackground(new Color(255, 245, 228));
		btn.setBackground(new Color(250, 112, 112));
		btnSelected = btn.getText();
	}
	
	/**
	 * Lấy danh sách chi tiết hóa đơn từ table, dùng trong chức năng thống kê số lượng mua
	 * @param tieuChi : tiêu chí lựa chọn để trả về danh sách phù hợp(phụ thuộc vào lựa chọn trên radio)
	 * @return danh sách đã được sắp xếp phù hợp với tiêu chí đã chọn trên radio
	 */
	public List<ChiTietHoaDon> getSPIntoListChiTietHoaDon(String tieuChi) {
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		List<ChiTietHoaDon> list_CTHD = new ArrayList<ChiTietHoaDon>();
		for (int i = 0; i < tbl_TKSLMua.getRowCount(); i++) {
			list.add(new ChiTietHoaDon((dtm_TKSLMua.getValueAt(i, 0)+"").trim(),"", Integer.parseInt(dtm_TKSLMua.getValueAt(i, 2)+""), 0));
		}
		Collections.sort(list, new Comparator<ChiTietHoaDon>() {
			@Override
			public int compare(ChiTietHoaDon o1, ChiTietHoaDon o2) {
				Integer s1 = o1.getSoLuong();
				Integer s2 = o2.getSoLuong();
				return s2.compareTo(s1);
			}
		});
		int count=0;
		if(list.size()>15)	count=15;
		else count = list.size();
		int i;
		if(tieuChi.equals("Nhỏ")) {
			i=list.size()-1;
			while(list_CTHD.size()<count) {
				list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
				i--;
			}
		}
		else if(tieuChi.equals("Lớn")) {
			for (i = 0; i < count; i++) {
				list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
			}
		}
		else if(tieuChi.equals("Trung Bình")) {
			if(count < 15) {
				for (i = count-1; i >= 0; i--) {
					list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
				}}
			else { 
				for (i = list.size()-1; i >= list.size()-7; i--)
					list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
				list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
				for (i = 6; i >= 0; i--)
					list_CTHD.add(new ChiTietHoaDon(list.get(i).getMaSP(), "", list.get(i).getSoLuong(), 0));
			}
		}
		else return list;
		return list_CTHD;
	}
	
	/**
	 * Lấy danh sách sản phẩm từ table add vào List, dùng trong chức năng thống kê số lượng tồn và giảm giá
	 * @param tieuChi : tiêu chí truyền vào để sắp xếp cho phù hợp với lựa chọn của radio
	 * @return danh sách đã được sắp xếp phù hợp với lựa chọn trên radio
	 */
	public List<SanPham> getSPIntoListSanPham(String tieuChi) {
		List<SanPham> list = new ArrayList<SanPham>();
		List<SanPham> list_SP = new ArrayList<SanPham>();
		if(pnChiTietTKSLTon.isVisible()) {
			for (int i = 0; i < tbl_TKSLTon.getRowCount(); i++)
				list.add(new SanPham((dtm_TKSLTon.getValueAt(i, 0)+"").trim(), "", Integer.parseInt(dtm_TKSLTon.getValueAt(i, 2)+""), 0, 0));
		} else {
			for (int i = 0; i < tbl_TKGG.getRowCount(); i++) {
				list.add(new SanPham((dtm_TKGG.getValueAt(i, 0)+"").trim(), "", 0, Integer.parseInt(dtm_TKGG.getValueAt(i, 3)+""), 0));
			}
		}
		Collections.sort(list, new Comparator<SanPham>() {
			@Override
			public int compare(SanPham o1, SanPham o2) {
				Integer s1 = 0;
				Integer s2 = 0;
				if(pnChiTietTKSLTon.isVisible()) {
					s1 = o1.getSoLuongTon();
					s2 = o2.getSoLuongTon();
					
				} else {
					s1 = o1.getGiamGia();
					s2 = o2.getGiamGia();
				}
				return s2.compareTo(s1);
			}
		});
		int count=0;
		if(list.size()>15)	count=15;
		else count = list.size();
		int i;
		if(tieuChi.equals("Nhỏ")) {
			i=list.size()-1;
			while(list_SP.size()<count) {
				list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
				i--;
			}
		}
		else if(tieuChi.equals("Lớn")) {
			for (i = 0; i < count; i++) {
				list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
			}
		}
		else if(tieuChi.equals("Trung Bình")) {
			if(count < 15)
				for (i = count-1; i >= 0; i--)
					list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
			else {
				for (i = list.size()-1; i >= list.size()-7; i--)
					list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
				list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
				for (i = 7; i >=0; i--)
					list_SP.add(new SanPham(list.get(i).getMaSP(), "", list.get(i).getSoLuongTon(), list.get(i).getGiamGia(), 0));
			}
		}
		else return list;
		return list_SP;
	}
	
	/**
	 * Thay đổi các panel cho phù hợp với lựa chọn của người dùng, chỉ sử dụng trong thống kê doanh thu
	 * @param pn : panel cần hiển thị lên
	 */
	public void changePanel(JPanel pn) {
		if(pn.equals(pnChiTietTKDoanhThu))
		{
			rad_TK_BDCot_15Nhieu.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu nhiều nhất");
			rad_TK_BDCot_15It.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu ít nhất");
		} else {
			rad_TK_BDCot_15Nhieu.setText("15 sản phẩm nhiều nhất");
			rad_TK_BDCot_15It.setText("15 sản phẩm ít nhất");
		}
		pnChiTietTKSLMua.setVisible(false);
		pnChiTietTKSLTon.setVisible(false);
		pnChiTietTKHinhThucMua.setVisible(false);
		pnChiTietTKGiamGia.setVisible(false);
		pnChiTietTKDoanhThu.setVisible(false);
		pn.setVisible(true);
	}

	/**
	 * Xác định xem panel của loại thời gian nào đang được chọn, chỉ sử dụng trong thống kê doanh thu
	 * @return pnl đang được hiển thị theo loại thời gian đang được lựa chọn
	 */
	public JPanel determinedCurrentPanel() {
		if(pn_TKDT_Ngay.isVisible())	return pn_TKDT_Ngay;
		if(pn_TKDT_Thang.isVisible())	return pn_TKDT_Thang;
		if(pn_TKDT_Quy.isVisible())		return pn_TKDT_Quy;
		return pn_TKDT_Nam;
	}
	
	/**
	 * Set enabel cho panel
	 * @param pn : panel cần setenable
	 * @param enable : chế độ enable
	 */
	public void setEnablePanel(JPanel pn, boolean enable) {
		for (int i = 0; i < pn.getComponentCount(); i++) {
			pn.getComponent(i).setEnabled(enable);
		}
	}
	
	/**
	 * Xác định quý của ngày truyền vào
	 * @param date : ngày cần xác định quý
	 * @return quý của ngày đó
	 */
	public int getQuarterOfDate(LocalDate date) {
		if(date.getMonthValue() == 1 || date.getMonthValue() == 2 || date.getMonthValue() == 3)
			return 1;
		if(date.getMonthValue() == 4 || date.getMonthValue() == 5 || date.getMonthValue() == 6)
			return 2;
		if(date.getMonthValue() == 7 || date.getMonthValue() == 8 || date.getMonthValue() == 9)
			return 3;
		return 4;
	}
	
	/**
	 * Load dữ liệu thống kê
	 */
	public static void loadStatistics() {
		lblIconTongSP.setText(dao_SanPham.getDsSanPham().size()+"");
		lblIconTongNV.setText(dao_NhanVien.getDsNhanVien().size()+"");
		lblIconTongHD.setText(dao_HoaDon.getDsHoaDon(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1)+"", LocalDate.now()+"", nhanVien).size()+"");
		double tongDoanhThuThang = 0;
		for (HoaDon hoaDon : dao_HoaDon.getDsHoaDon(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1)+"", LocalDate.now()+"", nhanVien)) {
			tongDoanhThuThang += hoaDon.tinhTongTien(dao_ChiTietHoaDon.getDsChiTietHoaDon(hoaDon.getMaHD()));
		}
		lblIconTongDT.setText(formatter.format(tongDoanhThuThang/1000));
	}
	
	
	
	/**
	 * Lấy giá trị từ JSpinner
	 * @param spn : JSpinner cần lấy giá trị
	 * @return giá trị của JSpinner
	 */
	public int getTextInJSpinner(JSpinner spn) {
		try {
			return Integer.parseInt(formatter.parseObject(((DefaultEditor)spn.getEditor()).getTextField().getText())+"");
		} catch (NumberFormatException e) {
		} catch (ParseException e) {
		}
		return 0;
	}
	
	/**
	 * Đánh giá mức độ của thống kê số lượng mua 
	 * @param numOfDate : số lượng ngày
	 * @param numOfQuantity: số lượng mua
	 * @return dựa vào tỉ lệ giữa số lượng mua và số lượng ngày, nếu tỉ lệ >= 10 thì bán chạy, <5 thì ít còn lại là bình thường
	 */
	public String reviewLevelOfSale(long numOfDate, int numOfQuantity) {
		return numOfQuantity/numOfDate >= 10?	"Bán chạy" : (numOfQuantity/numOfDate >= 5?	"Bình thường" : "Ít");
	}
	
	/**
	 * Hiển thị thông báo
	 */
	public void showMess(String mess) {
		JOptionPane.showMessageDialog(null, mess);
	}
	
	/**
	 * Vẽ biểu đồ thống kê
	 * @param loaiBD : loại biểu đồ cần vẽ
	 */
	public void drawChart(String loaiBD) {
		pn_HinhBieuDo.setVisible(true);
		pn_HinhBieuDo.removeAll();
		
		JFreeChart fCh = null;
		RSTableMetro tbl_BD_ChiTietSanPham;
		JPanel pn_BD_ChiTietSanPham = new JPanel();
		pn_BD_ChiTietSanPham.setLayout(null);
		
		JScrollPane scr_BD_ChiTietSanPham = new JScrollPane();
		pn_BD_ChiTietSanPham.add(scr_BD_ChiTietSanPham);
		
		if(loaiBD.equals("Cột")) {
			pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(1300, 498));
			String tieuChi = rad_TK_BDCot_15Nhieu.isSelected()? "Lớn" : (rad_TK_BDCot_15It.isSelected()? "Nhỏ" : "Trung Bình");
			scr_BD_ChiTietSanPham.setBounds(1000, 0, 300, 498);
			tbl_BD_ChiTietSanPham = formatTable(scr_BD_ChiTietSanPham, dtm_ChiTietSanPham = new DefaultTableModel(new String[] {
				"Mã SP", "Tên Sản Phẩm"}, 0));
			tbl_BD_ChiTietSanPham.setRowHeight(31);
			tbl_BD_ChiTietSanPham.getColumnModel().getColumn(1).setPreferredWidth(230);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if(pnChiTietTKSLMua.isVisible()) {
				if(dtm_TKSLMua.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				else {
				for (ChiTietHoaDon chiTietHoaDon : getSPIntoListChiTietHoaDon(tieuChi)) {
					dataset.setValue(chiTietHoaDon.getSoLuong(), "", chiTietHoaDon.getMaSP().substring(2));
					dtm_ChiTietSanPham.addRow(new String[] {chiTietHoaDon.getMaSP(), dao_SanPham.getTenSanPham(chiTietHoaDon.getMaSP())});
				}
				fCh = ChartFactory.createBarChart("", "Mã Sản Phẩm SP.....", "Số Lượng Mua", dataset, PlotOrientation.VERTICAL, false, false, false);
				}
			} else if(pnChiTietTKSLTon.isVisible()) {
				if(dtm_TKSLTon.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				for (SanPham sanPham : getSPIntoListSanPham(tieuChi)) {
					dataset.setValue(sanPham.getSoLuongTon(), "", sanPham.getMaSP().substring(2));
					dtm_ChiTietSanPham.addRow(new String[] {sanPham.getMaSP(), dao_SanPham.getTenSanPham(sanPham.getMaSP())});
				}
				fCh = ChartFactory.createBarChart("", "Mã Sản Phẩm SP.....", "Số Lượng Tồn", dataset, PlotOrientation.VERTICAL, false, false, false);
			} else if(pnChiTietTKHinhThucMua.isVisible()) {
				if(dtm_TKHinhThucMua.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				pn_BD_ChiTietSanPham.removeAll();
				pn_BD_ChiTietSanPham.repaint();
				pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(500, 400));
				double tongDTMuaTrucTiep = 0;
				double tongDTMuaOnline = 0;
				for (int i = 0; i < dtm_TKHinhThucMua.getRowCount(); i++) {
					if(dtm_TKHinhThucMua.getValueAt(i, 2).toString().equals("Mua Trực Tiếp"))
						try {
							tongDTMuaTrucTiep+=Double.parseDouble(formatter.parseObject(dtm_TKHinhThucMua.getValueAt(i, 3).toString()).toString());
						} catch (NumberFormatException e1) {
						} catch (ParseException e1) {
						}
					else
						try {
							tongDTMuaOnline+=Double.parseDouble(formatter.parseObject(dtm_TKHinhThucMua.getValueAt(i, 3).toString()).toString());
						} catch (NumberFormatException e1) {
						} catch (ParseException e1) {
						}
				}
				dataset.setValue(tongDTMuaTrucTiep, "", "Mua Trực Tiếp");
				dataset.setValue(tongDTMuaOnline, "", "Mua Online");
				fCh = ChartFactory.createBarChart("", "Hình thức mua", "Doanh thu", dataset, PlotOrientation.VERTICAL, false, false, false);
				fCh.setTitle(new TextTitle("Doanh thu các hình thức mua", new Font("Tahoma", Font.BOLD, 16)));
			} else if(pnChiTietTKGiamGia.isVisible()) {
				if(dtm_TKGG.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				for (SanPham sanPham : getSPIntoListSanPham(tieuChi)) {
					dataset.setValue(sanPham.getGiamGia(), "", sanPham.getMaSP().substring(2));
					dtm_ChiTietSanPham.addRow(new String[] {sanPham.getMaSP(), dao_SanPham.getTenSanPham(sanPham.getMaSP())});
				}
				fCh = ChartFactory.createBarChart("", "Mã Sản Phẩm SP.....", "Giảm Giá(%)", dataset, PlotOrientation.VERTICAL, false, false, false);
			} else {//thống kê doanh thu
				if(dtm_TKDT.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				pn_BD_ChiTietSanPham.removeAll();
				pn_BD_ChiTietSanPham.repaint();
				pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(1000, 498));
				List<Integer> list = getDataFromTableTKDTIntoListRowSortDoanhThu();
				try {
				if(tieuChi.equals("Trung Bình")) {
					if(list.size()<=15)
						for (int i = 0; i < list.size(); i++)
							dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""), "", dtm_TKDT.getValueAt(list.get(i), 0)+"");
					else {
						for (int i = 0; i < 7; i++)
							dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""), "", dtm_TKDT.getValueAt(list.get(i), 0)+"");
						dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(list.size()/2), 1)+""), "", dtm_TKDT.getValueAt(list.get(list.size()/2), 0)+"");
						for (int i = list.size()-7; i < list.size(); i++)
							dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""), "", dtm_TKDT.getValueAt(list.get(i), 0)+"");
					}
				} else {
					int rowBD = 0; int rowKT = 0;
					if(tieuChi.equals("Lớn")) {
						rowBD = list.size() - 1;
						rowKT =rowBD - 15;
						if(rowKT < 0) rowKT = 0;
						for(int i=rowBD;i>=rowKT;i--)
							dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""), "", dtm_TKDT.getValueAt(list.get(i), 0)+"");
					} else {
						rowBD = 0;
						rowKT = rowBD + 14;
						if(rowKT > list.size()-1) rowKT = list.size()-1;
						for(int i=rowBD;i<=rowKT;i++)
							dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""), "", dtm_TKDT.getValueAt(list.get(i), 0)+"");
					}
				}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				fCh = ChartFactory.createBarChart("", "Ngày Lập Hóa Đơn", "Doanh thu", dataset, PlotOrientation.VERTICAL, false, false, false);
			}
			if(pn_BDCot_ChonSP.isVisible()) {
				if(tieuChi.equals("Lớn")) {
					if(pnChiTietTKSLMua.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm mua nhiều nhất", new Font("Tahoma", Font.BOLD, 16)));
					else if(pnChiTietTKSLTon.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm tồn nhiều nhất", new Font("Tahoma", Font.BOLD, 16)));
					else if(pnChiTietTKGiamGia.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm giảm giá nhiều nhất", new Font("Tahoma", Font.BOLD, 16)));
					else
						fCh.setTitle(new TextTitle("Doanh thu nhiều nhất", new Font("Tahoma", Font.BOLD, 16)));
				}
				else if(tieuChi.equals("Nhỏ")) {
						if(pnChiTietTKSLMua.isVisible())
							fCh.setTitle(new TextTitle("Sản phẩm mua ít nhất", new Font("Tahoma", Font.BOLD, 16)));
						else if(pnChiTietTKSLTon.isVisible())
							fCh.setTitle(new TextTitle("Sản phẩm tồn ít nhất", new Font("Tahoma", Font.BOLD, 16)));
						else if(pnChiTietTKGiamGia.isVisible())
							fCh.setTitle(new TextTitle("Sản phẩm giảm giá ít nhất", new Font("Tahoma", Font.BOLD, 16)));
						else
							fCh.setTitle(new TextTitle("Doanh thu ít nhất", new Font("Tahoma", Font.BOLD, 16)));
				}
				else {
					if(pnChiTietTKSLMua.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm mua", new Font("Tahoma", Font.BOLD, 16)));
					else if(pnChiTietTKSLTon.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm tồn", new Font("Tahoma", Font.BOLD, 16)));
					else if(pnChiTietTKGiamGia.isVisible())
						fCh.setTitle(new TextTitle("Sản phẩm giảm giá", new Font("Tahoma", Font.BOLD, 16)));
					else
						fCh.setTitle(new TextTitle("Doanh thu", new Font("Tahoma", Font.BOLD, 16)));
				}
			}
			CategoryPlot cpl = fCh.getCategoryPlot();
			cpl.setRangeGridlinePaint(Color.LIGHT_GRAY);
		} else if(loaiBD.equals("Tròn")) {//biểu đồ tròn
			pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(775, 498));
			scr_BD_ChiTietSanPham.setBounds(355, 0, 420, 498);
			tbl_BD_ChiTietSanPham = formatTable(scr_BD_ChiTietSanPham, dtm_ChiTietSanPham = new DefaultTableModel(new String[] {
				"Mã SP", "Tên Sản Phẩm", (pnChiTietTKSLMua.isVisible()? "Số Lượng Mua" : (pnChiTietTKSLTon.isVisible()? "Số Lượng Tồn" : "Doanh Thu"))}, 0));
			tbl_BD_ChiTietSanPham.setRowHeight(31);
			tbl_BD_ChiTietSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
			tbl_BD_ChiTietSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);
			DefaultPieDataset dataset = new DefaultPieDataset();
			int count = 0;
			if(pnChiTietTKSLMua.isVisible()) {
				if(dtm_TKSLMua.getRowCount()==0)
					pn_HinhBieuDo.setVisible(false);
				else {
					List<ChiTietHoaDon> list = getSPIntoListChiTietHoaDon("Tròn");
					count = list.size();
					if(count<=15)
						for (ChiTietHoaDon chiTietHoaDon : list) {
							dataset.setValue(chiTietHoaDon.getMaSP(),chiTietHoaDon.getSoLuong());
							dtm_ChiTietSanPham.addRow(new String[] {chiTietHoaDon.getMaSP(), dao_SanPham.getTenSanPham(chiTietHoaDon.getMaSP()), chiTietHoaDon.getSoLuong()+""});
						}
					else {
						int i=0;
						int tong=0;
						while(i<count) {
							if(i<15)
								dataset.setValue(list.get(i).getMaSP(),list.get(i).getSoLuong());
							else tong+=list.get(i).getSoLuong();
							dtm_ChiTietSanPham.addRow(new String[] {list.get(i).getMaSP(), dao_SanPham.getTenSanPham(list.get(i).getMaSP()), list.get(i).getSoLuong()+""});
							i++;
						}
						if(tong>0)
							dataset.setValue("Khác",tong);
					}
				}
			} else if(pnChiTietTKSLTon.isVisible()) {
				if(dtm_TKSLTon.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				else {
					List<SanPham> list = getSPIntoListSanPham("Tròn");
					count = list.size();
					if(count<=15)
						for (SanPham sanPham : list) {
							dataset.setValue(sanPham.getMaSP(),sanPham.getSoLuongTon());
							dtm_ChiTietSanPham.addRow(new String[] {sanPham.getMaSP(), dao_SanPham.getTenSanPham(sanPham.getMaSP()), sanPham.getSoLuongTon()+""});
						}
					else {
						int i=0;
						int tong=0;
						while(i<count) {
							if(i<19)
								dataset.setValue(list.get(i).getMaSP(),list.get(i).getSoLuongTon());
							else tong+=list.get(i).getSoLuongTon();
							dtm_ChiTietSanPham.addRow(new String[] {list.get(i).getMaSP(), dao_SanPham.getTenSanPham(list.get(i).getMaSP()), list.get(i).getSoLuongTon()+""});
							i++;
						}
						if(tong>0)
							dataset.setValue("Khác",tong);
					}
				}
			} else if(pnChiTietTKHinhThucMua.isVisible()) {
				if(dtm_TKHinhThucMua.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				pn_BD_ChiTietSanPham.removeAll();
				pn_BD_ChiTietSanPham.repaint();
				pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(400, 430));
				double tongDTMuaTrucTiep = 0;
				double tongDTMuaOnline = 0;
				for (int i = 0; i < dtm_TKHinhThucMua.getRowCount(); i++) {
					if(dtm_TKHinhThucMua.getValueAt(i, 2).toString().equals("Mua Trực Tiếp"))
						try {
							tongDTMuaTrucTiep+=Double.parseDouble(formatter.parseObject(dtm_TKHinhThucMua.getValueAt(i, 3).toString()).toString());
						} catch (NumberFormatException e1) {
						} catch (ParseException e1) {
						}
					else
						try {
							tongDTMuaOnline+=Double.parseDouble(formatter.parseObject(dtm_TKHinhThucMua.getValueAt(i, 3).toString()).toString());
						} catch (NumberFormatException e1) {
						} catch (ParseException e1) {
						}
					}
					dataset.setValue("Mua Trực Tiếp", tongDTMuaTrucTiep);
					dataset.setValue("Mua Online", tongDTMuaOnline);
			} else if(pnChiTietTKGiamGia.isVisible()) {
				pn_HinhBieuDo.setVisible(false);
				return;
			} else {//thống kê doanh thu
				if(dtm_TKDT.getRowCount()==0) {
					pn_HinhBieuDo.setVisible(false);
					return;
				}
				pn_BD_ChiTietSanPham.removeAll();
				pn_BD_ChiTietSanPham.repaint();
				pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(400, 430));
				List<Integer> list = getDataFromTableTKDTIntoListRowSortDoanhThu();
				try {
				if(list.size()<=15)
					for(int i=list.size()-1; i>=0 ;i--)
						dataset.setValue(dtm_TKDT.getValueAt(list.get(i), 0)+"", formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""));
				else {
					int i=list.size()-1;
					int tong=0;
					while(i>=0) {
						if(i>list.size()-16)
							dataset.setValue(dtm_TKDT.getValueAt(list.get(i), 0)+"", formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+""));
						else
							tong+=Integer.parseInt(formatter.parse(dtm_TKDT.getValueAt(list.get(i), 1)+"")+"");
						i--;
					}
					if(tong>0)
						dataset.setValue("Khác",tong);
				}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			fCh = ChartFactory.createPieChart("", dataset, true, true, false);
			if(pnChiTietTKSLMua.isVisible())
				fCh.setTitle(new TextTitle("Sản Phẩm Mua", new Font("Tahoma", Font.BOLD, 16)));
			else if(pnChiTietTKSLTon.isVisible())
				fCh.setTitle(new TextTitle("Sản Phẩm Tồn", new Font("Tahoma", Font.BOLD, 16)));
			else if(pnChiTietTKHinhThucMua.isVisible())
				fCh.setTitle(new TextTitle("Hình Thức Mua", new Font("Tahoma", Font.BOLD, 16)));
			else if(pnChiTietTKDoanhThu.isVisible())
				fCh.setTitle(new TextTitle("Doanh Thu", new Font("Tahoma", Font.BOLD, 16)));
			PiePlot p = (PiePlot) fCh.getPlot();
			p.setLabelGenerator(null);
		} else {//biểu đồ đường
			if(dtm_TKDT.getRowCount()==0) {
				pn_HinhBieuDo.setVisible(false);
				return;
			}
			pn_BD_ChiTietSanPham.setPreferredSize(new Dimension(1200, 550));
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			try {
			if(tbl_TKDT.getRowCount()<=15)
				for (int i = 0; i < tbl_TKDT.getRowCount(); i++)
					dataset.setValue(formatter.parse(dtm_TKDT.getValueAt(i, 1)+""), "", dtm_TKDT.getValueAt(i, 0)+"");
			else return;
			} catch (ParseException e1) {
				e1.printStackTrace();
			}	
			fCh = ChartFactory.createLineChart("", "Ngày Lập Hóa Đơn", "Doanh thu", dataset, PlotOrientation.VERTICAL, false, false, false);
			CategoryPlot cpl = fCh.getCategoryPlot();
			cpl.setRangeGridlinePaint(Color.LIGHT_GRAY);
		}
		fCh.getPlot().setBackgroundPaint(Color.WHITE);
		ChartPanel pnC = new ChartPanel(fCh);
		pnC.setBounds(10,0,300,230);
		
		ChartPanel pnC2 = new ChartPanel(fCh);
		if(loaiBD.equals("Cột")) {
			if(pnChiTietTKSLMua.isVisible() || pnChiTietTKSLTon.isVisible() || pnChiTietTKGiamGia.isVisible() || pnChiTietTKDoanhThu.isVisible())
				pnC2.setBounds(0,0,1000,498);
			else if(pnChiTietTKHinhThucMua.isVisible())
				pnC2.setBounds(0, 0, 500, 400);
		}
		else if(loaiBD.equals("Tròn")) {
			if(pnChiTietTKSLMua.isVisible() || pnChiTietTKSLTon.isVisible() || pnChiTietTKGiamGia.isVisible())
				pnC2.setBounds(0, 0, 350, 498);
			else if(pnChiTietTKHinhThucMua.isVisible() || pnChiTietTKDoanhThu.isVisible())
				pnC2.setBounds(0, 0, 400, 430);
		} else
			pnC2.setBounds(0, 0, 1200, 550);
		pn_BD_ChiTietSanPham.add(pnC2);
		pn_HinhBieuDo.add(pnC);
		pnC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, pn_BD_ChiTietSanPham, "", JOptionPane.DEFAULT_OPTION);
			}
		});
		pn_HinhBieuDo.repaint();
	}
	
	/**
	 * Set visible cho pnl biểu đồ tùy thuộc vào lựa chọn của người dùng
	 */
	public void setVisiblePnBieuDo() {
		if(!pnChiTietTKDoanhThu.isVisible() && cbo_LoaiBieuDo.getSelectedIndex()==2)
			cbo_LoaiBieuDo.setSelectedIndex(1);
		if(cbo_LoaiBieuDo.getSelectedIndex()==0) {
			if(!pnChiTietTKDoanhThu.isVisible() && cbo_LoaiBieuDo.getItemCount()>2)
				cbo_LoaiBieuDo.removeItemAt(2);
			if(pnChiTietTKSLMua.isVisible() || pnChiTietTKSLTon.isVisible() || pnChiTietTKGiamGia.isVisible() || pnChiTietTKDoanhThu.isVisible()) {
				pn_BDCot_ChonSP.setVisible(true);
				pn_BDTron_MoTa.setVisible(false);
			} else if(pnChiTietTKHinhThucMua.isVisible()) {
				pn_BDCot_ChonSP.setVisible(false);
				pn_BDTron_MoTa.setVisible(true);
				lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ cột mô tả doanh thu của nhà sách thông qua các hình thức mua hàng khác nhau]</p></html>");
			}
			drawChart("Cột");
		} else if(cbo_LoaiBieuDo.getSelectedIndex()==1) {
			if(!pnChiTietTKDoanhThu.isVisible() && cbo_LoaiBieuDo.getItemCount()>2)
				cbo_LoaiBieuDo.removeItemAt(2);
			pn_BDCot_ChonSP.setVisible(false);
			pn_BDTron_MoTa.setVisible(true);
			if(pnChiTietTKSLMua.isVisible())
				lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ tròn mô tả tỷ lệ phần trăm(%) số lượng mua của các sản phẩm trong khoảng thời gian]</p></html>");
			else if(pnChiTietTKSLTon.isVisible())
				lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ tròn mô tả tỷ lệ phần trăm(%) số lượng tồn của các sản phẩm]</p></html>");
			else if(pnChiTietTKHinhThucMua.isVisible())
				lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ tròn mô tả tỉ lệ phần trăm(%) doanh thu của nhà sách thông qua các hình thức mua hàng khác nhau]</p></html>");
			else if(pnChiTietTKGiamGia.isVisible())
				lbl_BDTron_MoTa.setText("<html><p>[Thống kê giảm giá không thể vẽ biểu đồ tròn]</p></html>");
			else
				lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ tròn mô tả tỷ lệ phần trăm(%) doanh thu của nhà sách]</p></html>");
			drawChart("Tròn");
		} else {
			pn_BDCot_ChonSP.setVisible(false);
			pn_BDTron_MoTa.setVisible(true);
			lbl_BDTron_MoTa.setText("<html><p>[Biểu đồ đường mô tả độ dao động doanh thu của nhà sách(tối đa 15 mốc thời gian)]</p></html>");
			drawChart("Đường");
		}
	}
	
	/**
	 * Lấy ngày cuối cùng của tháng trong năm
	 * @param month : tháng
	 * @param year : năm
	 * @return ngày cuối cùng của tháng trong năm
	 */
	public String getEndDateOfMonth(int month, int year) {
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            return year+"-"+month+"-31";
		if(month==2) {
			if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				return year+"-"+month+"-29";
			return year+"-"+month+"-28";
		}
		return year+"-"+month+"-30";
	}
	
	/**
	 * Sắp xếp table theo cột
	 * @param tbl : table cần sắp xếp
	 * @param column : cột cần sắp xếp
	 */
	public void sortTableString(RSTableMetro tbl, int column) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbl.getModel());
		tbl.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(column, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}
	
	/**
	 * Lấy danh sách các dòng từ table trong thống kê doanh thu sau đó sắp xếp theo thứ tự các dòng có doanh thu tăng dần
	 * @return danh sách các dòng theo thứ tự doanh thu tăng dần
	 */
	
	public List<Integer> getDataFromTableTKDTIntoListRowSortDoanhThu() {
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list_Sort = new ArrayList<Integer>();
		try {
			for (int i = 0; i < tbl_TKDT.getRowCount(); i++)
				list.add(Integer.parseInt(formatter.parse(dtm_TKDT.getValueAt(i, 1)+"")+""));
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
			});
			int j;
			for (int k = 0 ; k < list.size(); k++) {
				for (int i = 0; i < tbl_TKDT.getRowCount(); i++) {
					j = Integer.parseInt(formatter.parse(dtm_TKDT.getValueAt(i, 1)+"")+"");
					if(list.get(k) == j && !list_Sort.contains(i))
						list_Sort.add(i);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list_Sort;
	}
	
	/**
	 * Kiểm tra điều kiện các JSpinner
	 * @param bd giá trị đầu
	 * @param kt giá trị cuối
	 * @return chuỗi thông báo
	 */
	public String checkSpinner(int bd, int kt) {
		if(bd<0) return "Số bắt đầu phải >= 0";
		if(kt<0) return "Số kết thúc phải >= 0";
		if(bd>kt) return "Số bắt đầu không lớn hơn số kết thúc!";
		return "";
	}
	
	public void exportFileExcel(RSTableMetro tbl, String path) {
		try {
			FileOutputStream file = new FileOutputStream(path);
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("Báo cáo của Sơn");
			XSSFRow row = sheet.createRow(2);
			Cell cell;
			for (int i = 0; i < tbl.getColumnCount(); i++) {
				cell = row.createCell(i+1, CellType.STRING);
				cell.setCellValue(tbl.getColumnName(i));	
			}
			boolean isNumber = false;
			int number = 0;
			for (int i = 0; i < tbl.getRowCount(); i++) {
				row = sheet.createRow(i+3);
				for (int j = 0; j < tbl.getColumnCount()-1; j++) {
					try {
						number = Integer.parseInt(tbl.getValueAt(i, j)+"");
						isNumber = true;
					} catch (Exception e) {
						isNumber = false;
					}
					if(!isNumber) {
						cell = row.createCell(j+1, CellType.STRING);
						cell.setCellValue(tbl.getValueAt(i, j)+"");
					} else {
						cell = row.createCell(j+1, CellType.NUMERIC);
						cell.setCellValue(number);
					}
				}
				if(pnChiTietTKSLMua.isVisible() || pnChiTietTKSLTon.isVisible()) {
					cell = row.createCell(tbl.getColumnCount(), CellType.STRING);
					cell.setCellValue(tbl.getValueAt(i, tbl.getColumnCount()-1)+"");
				} else {
					try {
						cell = row.createCell(tbl.getColumnCount(), CellType.NUMERIC);
						cell.setCellValue(Double.parseDouble(formatter.parse(tbl.getValueAt(i, tbl.getColumnCount()-1)+"")+""));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			workBook.write(file);
			workBook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new GUI_ThongKe(new NhanVien("NV2200", "Nguyễn Thanh Sơn", "0387866829", "", "thanhsonsmile2017@gmail.com", "Quản Lý")).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(btnTKSLMua) || e.getSource().equals(btnTKSLTon) || e.getSource().equals(btnTKHinhThucMua)
				|| e.getSource().equals(btnTKGiamGia) || e.getSource().equals(btnTKDoanhThu))
			changeColorButtonWhenClick((JButton) e.getSource());
		else if(e.getSource().equals(lbl_TKSLMua_MuiTen_NgayMua))
			pn_TKSLMua_KT_NgayMua.setVisible(!pn_TKSLMua_KT_NgayMua.isVisible());
		else if(e.getSource().equals(lbl_TKSLMua_Reset))
			dC_TKSLMua_KT.setDate(new Date(new Date().getTime()));
		else if(e.getSource().equals(lbl_TKSLMua_MuiTen) && lbl_TKSLMua_MuiTen.isEnabled())
			pn_TKSLMua_KT.setVisible(!pn_TKSLMua_KT.isVisible());
		else if(e.getSource().equals(lbl_TKSLMua_Infinity) && lbl_TKSLMua_Infinity.isEnabled())
			spn_TKSLMua_KT.setValue(9999);
		else if(e.getSource().equals(lbl_TKSLTon_MuiTen) && lbl_TKSLTon_MuiTen.isEnabled())
			pn_TKSLTon_KT.setVisible(!pn_TKSLTon_KT.isVisible());
		else if(e.getSource().equals(lbl_TKSLTon_Infinity) && lbl_TKSLTon_Infinity.isEnabled())
			spn_TKSLTon_KT.setValue(9999);
		else if(e.getSource().equals(lbl_TKHinhThucMua_MuiTen))
			pn_TKHinhThucMua_KT.setVisible(!pn_TKHinhThucMua_KT.isVisible());
		else if(e.getSource().equals(lbl_TKHinhThucMua_Reset))
			dC_TKHinhThucMua_KT.setDate(new Date(new Date().getTime()));
		else if(e.getSource().equals(lbl_TKGG_MuiTen) && lbl_TKGG_MuiTen.isEnabled())
			pn_TKGG_KT.setVisible(!pn_TKGG_KT.isVisible());
		else if(e.getSource().equals(lbl_TKDT_MuiTen) && lbl_TKDT_MuiTen.isEnabled()) {
			if(determinedCurrentPanel().equals(pn_TKDT_Ngay)) {
				dC_TKDT_Ngay_KT.setVisible(!dC_TKDT_Ngay_KT.isVisible());
			} else if(determinedCurrentPanel().equals(pn_TKDT_Thang)) {
				cbo_TKDT_Thang_KT.setVisible(!cbo_TKDT_Thang_KT.isVisible());
				yC_TKDT_Thang_KT.setVisible(!yC_TKDT_Thang_KT.isVisible());
			} else if(determinedCurrentPanel().equals(pn_TKDT_Quy)) {
				cbo_TKDT_Quy_KT.setVisible(!cbo_TKDT_Quy_KT.isVisible());
				yC_TKDT_Quy_KT.setVisible(!yC_TKDT_Quy_KT.isVisible());
			} else {
				yC_TKDT_Nam_KT.setVisible(!yC_TKDT_Nam_KT.isVisible());
			}
			lbl_TKDT_Reset.setVisible(!lbl_TKDT_Reset.isVisible());
		} else if(e.getSource().equals(lbl_TKGG_Infinity) && lbl_TKGG_Infinity.isEnabled())
			spn_TKGG_KT.setValue(100);
		else if(e.getSource().equals(lbl_TKDT_Reset) && lbl_TKDT_Reset.isEnabled()) {
			if(determinedCurrentPanel().equals(pn_TKDT_Ngay))
				dC_TKDT_Ngay_KT.setDate(new Date(new Date().getTime()));
			else if(determinedCurrentPanel().equals(pn_TKDT_Thang)) {
				cbo_TKDT_Thang_KT.setSelectedItem("Tháng " + LocalDate.now().getMonthValue());
				yC_TKDT_Thang_KT.setValue(LocalDate.now().getYear());
			} else if(determinedCurrentPanel().equals(pn_TKDT_Quy)) {
				cbo_TKDT_Quy_KT.setSelectedItem("Quý "+ getQuarterOfDate(LocalDate.now()));
				yC_TKDT_Quy_KT.setValue(LocalDate.now().getYear());
			} else yC_TKDT_Nam_KT.setValue(LocalDate.now().getYear());
		}
		else if(e.getSource().equals(lbl_TKDT_MuiTen_DoanhThu) && lbl_TKDT_MuiTen_DoanhThu.isEnabled())
			pn_TKDT_KT.setVisible(!pn_TKDT_KT.isVisible());
		else if(e.getSource().equals(cbo_TKDT_DoanhThu)) {
			rad_TKDT_comBoBox.setSelected(true);
			cbo_TKDT_DoanhThu.setEnabled(true);
			spn_TKDT_BD.setEnabled(false);
			lbl_TKDT_MuiTen_DoanhThu.setEnabled(false);
			spn_TKDT_KT.setEnabled(false);
		} else if(e.getSource().equals(lbl_TKDT_Infinity) && lbl_TKDT_Infinity.isEnabled())
			spn_TKDT_KT.setValue(999999);

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(btnTKSLMua) || e.getSource().equals(btnTKSLTon) || e.getSource().equals(btnTKHinhThucMua)
				|| e.getSource().equals(btnTKGiamGia) || e.getSource().equals(btnTKDoanhThu))
			changeColorButtonWhenEntered((JButton) e.getSource());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(btnTKSLMua) || e.getSource().equals(btnTKSLTon) || e.getSource().equals(btnTKHinhThucMua)
				|| e.getSource().equals(btnTKGiamGia) || e.getSource().equals(btnTKDoanhThu))
			if (!btnSelected.equals(((JButton) e.getSource()).getText()))
				changeColorButtonWhenExited((JButton) e.getSource(), false);
			else
				changeColorButtonWhenExited((JButton) e.getSource(), true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(rad_TKSLMua_Khac)) {
			spn_TKSLMua_BD.setEnabled(true);
			lbl_TKSLMua_MuiTen.setEnabled(true);
			spn_TKSLMua_KT.setEnabled(true);
		} else if (e.getSource().equals(rad_TKSLMua_0den20) || e.getSource().equals(rad_TKSLMua_20den50)
				|| e.getSource().equals(rad_TKSLMua_50den100) || e.getSource().equals(rad_TKSLMua_Tu100)) {
			spn_TKSLMua_BD.setEnabled(false);
			lbl_TKSLMua_MuiTen.setEnabled(false);
			spn_TKSLMua_KT.setEnabled(false);
		} else if (e.getSource().equals(rad_TKSLTon_Khac)) {
			spn_TKSLTon_BD.setEnabled(true);
			lbl_TKSLTon_MuiTen.setEnabled(true);
			spn_TKSLTon_KT.setEnabled(true);
		} else if (e.getSource().equals(rad_TKSLTon_0den50) || e.getSource().equals(rad_TKSLTon_50den100)
				|| e.getSource().equals(rad_TKSLTon_100den300) || e.getSource().equals(rad_TKSLTon_300den500)
				|| e.getSource().equals(rad_TKSLTon_Tu500)) {
			spn_TKSLTon_BD.setEnabled(false);
			lbl_TKSLTon_MuiTen.setEnabled(false);
			spn_TKSLTon_KT.setEnabled(false);
		} else if (e.getSource().equals(rad_TKGG_Khac)) {
			spn_TKGG_BD.setEnabled(true);
			lbl_TKGG_MuiTen.setEnabled(true);
			spn_TKGG_KT.setEnabled(true);
		} else if (e.getSource().equals(rad_TKGG_0) || e.getSource().equals(rad_TKGG_10)
				|| e.getSource().equals(rad_TKGG_30) || e.getSource().equals(rad_TKGG_50)
				|| e.getSource().equals(rad_TKGG_70) || e.getSource().equals(rad_TKGG_Khac)) {
			spn_TKGG_BD.setEnabled(false);
			lbl_TKGG_MuiTen.setEnabled(false);
			spn_TKGG_KT.setEnabled(false);
		} else if (e.getSource().equals(rad_TKDT_comBoBox)) {
			cbo_TKDT_DoanhThu.setEnabled(true);
			spn_TKDT_BD.setEnabled(false);
			lbl_TKDT_MuiTen_DoanhThu.setEnabled(false);
			spn_TKDT_KT.setEnabled(false);
		} else if (e.getSource().equals(rad_TKDT_Khac)) {
			cbo_TKDT_DoanhThu.setEnabled(false);
			spn_TKDT_BD.setEnabled(true);
			lbl_TKDT_MuiTen_DoanhThu.setEnabled(true);
			spn_TKDT_KT.setEnabled(true);
		} else if (e.getSource().equals(btnTKSLMua)) {
			changePanel(pnChiTietTKSLMua);
			setVisiblePnBieuDo();
			btnBaoCao.setEnabled(dtm_TKSLMua.getRowCount()>0? true : false);
		} else if (e.getSource().equals(btnTKSLTon)) {
			changePanel(pnChiTietTKSLTon);
			setVisiblePnBieuDo();
			btnBaoCao.setEnabled(dtm_TKSLTon.getRowCount()>0? true : false);
		} else if (e.getSource().equals(btnTKHinhThucMua)) {
			changePanel(pnChiTietTKHinhThucMua);
			setVisiblePnBieuDo();
			btnBaoCao.setEnabled(dtm_TKHinhThucMua.getRowCount()>0? true : false);
		} else if (e.getSource().equals(btnTKGiamGia)) {
			changePanel(pnChiTietTKGiamGia);
			setVisiblePnBieuDo();
			btnBaoCao.setEnabled(dtm_TKGG.getRowCount()>0? true : false);
		} else if (e.getSource().equals(btnTKDoanhThu)) {
			changePanel(pnChiTietTKDoanhThu);
			cbo_LoaiBieuDo.addItem("Biểu đồ đường");
			setVisiblePnBieuDo();
			btnBaoCao.setEnabled(dtm_TKDT.getRowCount()>0? true : false);
		} else if (e.getSource().equals(chk_TKDT_ThoiGian)) {
			if(chk_TKDT_ThoiGian.isSelected()) {
				rad_TK_BDCot_15Nhieu.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu nhiều nhất");
				rad_TK_BDCot_15It.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu ít nhất");
			} else
			{
				rad_TK_BDCot_15Nhieu.setText("15 ngày doanh thu nhiều nhất");
				rad_TK_BDCot_15It.setText("15 ngày doanh thu ít nhất");
			}
			cbo_TKDT_ThongKeTheo.setEnabled(!cbo_TKDT_ThongKeTheo.isEnabled());
			setEnablePanel(determinedCurrentPanel(), !determinedCurrentPanel().getComponent(0).isEnabled());
		} else if (e.getSource().equals(chk_TKDT_DoanhThu)) {
			rad_TKDT_comBoBox.setEnabled(chk_TKDT_DoanhThu.isSelected());
			cbo_TKDT_DoanhThu.setEnabled(chk_TKDT_DoanhThu.isSelected() && rad_TKDT_comBoBox.isSelected());
			rad_TKDT_Khac.setEnabled(chk_TKDT_DoanhThu.isSelected());
			spn_TKDT_BD.setEnabled(chk_TKDT_DoanhThu.isSelected() && rad_TKDT_Khac.isSelected());
			lbl_TKDT_MuiTen_DoanhThu.setEnabled(chk_TKDT_DoanhThu.isSelected() && rad_TKDT_Khac.isSelected());
			spn_TKDT_KT.setEnabled(chk_TKDT_DoanhThu.isSelected() && rad_TKDT_Khac.isSelected());
		} else if(e.getSource().equals(cbo_TKDT_ThongKeTheo)) {
			rad_TK_BDCot_15Nhieu.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu nhiều nhất");
			rad_TK_BDCot_15It.setText("15 "+cbo_TKDT_ThongKeTheo.getSelectedItem().toString().toLowerCase()+" doanh thu ít nhất");
			determinedCurrentPanel().remove(lbl_TKDT_MuiTen);
			determinedCurrentPanel().remove(lbl_TKDT_Reset);
			determinedCurrentPanel().setVisible(false);
			switch (cbo_TKDT_ThongKeTheo.getSelectedItem().toString()){
			case "Ngày": {
				pn_TKDT_Ngay.add(lbl_TKDT_MuiTen);
				lbl_TKDT_Reset.setBounds(290, 3, 16, 16);
				pn_TKDT_Ngay.add(lbl_TKDT_Reset);
				pn_TKDT_Ngay.setVisible(true);
				break;
			}
			case "Tháng":{
				pn_TKDT_Thang.add(lbl_TKDT_MuiTen);
				lbl_TKDT_Reset.setBounds(345, 3, 16, 16);
				pn_TKDT_Thang.add(lbl_TKDT_Reset);
				pn_TKDT_Thang.setVisible(true);
				break;
			}
			case "Quý":{
				pn_TKDT_Quy.add(lbl_TKDT_MuiTen);
				lbl_TKDT_Reset.setBounds(345, 3, 16, 16);
				pn_TKDT_Quy.add(lbl_TKDT_Reset);
				pn_TKDT_Quy.setVisible(true);
				break;
			}
			default:{
				pn_TKDT_Nam.add(lbl_TKDT_MuiTen);
				lbl_TKDT_Reset.setBounds(290, 3, 16, 16);
				pn_TKDT_Nam.add(lbl_TKDT_Reset);
				pn_TKDT_Nam.setVisible(true);
				break;
			}
			}
			lbl_TKDT_Reset.setVisible(determinedCurrentPanel().getComponent(determinedCurrentPanel().getComponentCount()-3).isVisible());
		} else if(e.getSource().equals(btn_TKSLMua_ThongKe)) {
			dtm_TKSLMua.setRowCount(0);
			if(pn_TKSLMua_KT_NgayMua.isVisible() && dC_TKSLMua_BD.getDate().after(dC_TKSLMua_KT.getDate())) {
				showMess("Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			int soLuong_BD = 0;
			int soLuong_KT = 0;
			if(rad_TKSLMua_0den20.isSelected())			{	soLuong_BD=0; soLuong_KT=20; }
			else if(rad_TKSLMua_20den50.isSelected())	{	soLuong_BD=20; soLuong_KT=50;	}
			else if(rad_TKSLMua_50den100.isSelected())	{	soLuong_BD=50; soLuong_KT=100;	}
			else if(rad_TKSLMua_Tu100.isSelected())		{	soLuong_BD=100; soLuong_KT=9999;	}
			else {	soLuong_BD= getTextInJSpinner(spn_TKSLMua_BD);	soLuong_KT= pn_TKSLMua_KT.isVisible()? getTextInJSpinner(spn_TKSLMua_KT) : getTextInJSpinner(spn_TKSLMua_BD);	}
			String check = checkSpinner(soLuong_BD, soLuong_KT);
			if(!check.isEmpty()) {
				showMess(check);
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			for (ChiTietHoaDon cthd : dao_ChiTietHoaDon.getDsChiTietHoaDon(sdf.format(dC_TKSLMua_BD.getDate()), pn_TKSLMua_KT_NgayMua.isVisible()? sdf.format(dC_TKSLMua_KT.getDate()) : "", soLuong_BD, soLuong_KT)) {
				String row[] = {cthd.getMaSP(), dao_SanPham.getTenSanPham(cthd.getMaSP()), cthd.getSoLuong()+"", reviewLevelOfSale((pn_TKSLMua_KT_NgayMua.isVisible()? (dC_TKSLMua_KT.getDate().getTime()-dC_TKSLMua_BD.getDate().getTime()) : 1 /(24 * 60 * 60 * 1000))+1, cthd.getSoLuong())};
				dtm_TKSLMua.addRow(row);
			}
			btnBaoCao.setEnabled(dtm_TKSLMua.getRowCount()>0? true : false);
			if(cbo_LoaiBieuDo.getSelectedIndex()==0) drawChart("Cột");
			else drawChart("Tròn");
			}
			}
		} else if(e.getSource().equals(btn_TKSLTon_ThongKe)) {
			dtm_TKSLTon.setRowCount(0);
			int soLuong_BD = 0;
			int soLuong_KT = 0;
			if(rad_TKSLTon_0den50.isSelected())			{	soLuong_BD=0; soLuong_KT=50; }
			else if(rad_TKSLTon_50den100.isSelected())	{	soLuong_BD=50; soLuong_KT=100;	}
			else if(rad_TKSLTon_100den300.isSelected())	{	soLuong_BD=100; soLuong_KT=300;	}
			else if(rad_TKSLTon_300den500.isSelected())		{	soLuong_BD=300; soLuong_KT=500;	}
			else if(rad_TKSLTon_Tu500.isSelected())		{	soLuong_BD=500; soLuong_KT=9999;	}
			else {	soLuong_BD= getTextInJSpinner(spn_TKSLTon_BD);	soLuong_KT= pn_TKSLTon_KT.isVisible()? getTextInJSpinner(spn_TKSLTon_KT) : getTextInJSpinner(spn_TKSLTon_BD);	}
			String check = checkSpinner(soLuong_BD, soLuong_KT);
			if(!check.isEmpty()) {
				showMess(check);
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			for (SanPham sanPham : dao_SanPham.getDsSanPhamTheoSLTon(soLuong_BD, soLuong_KT)) {
				String row[] = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuongTon()+"", sanPham.getSoLuongTon()==0? "Hết" : (sanPham.getSoLuongTon()<=10? "Sắp hết" : (sanPham.getSoLuongTon()<=100? "Nhiều": "Rất Nhiều"))};
				dtm_TKSLTon.addRow(row);
			}
			btnBaoCao.setEnabled(dtm_TKSLTon.getRowCount()>0? true : false);
			if(cbo_LoaiBieuDo.getSelectedIndex()==0) drawChart("Cột");
			else drawChart("Tròn");
			}
		} else if(e.getSource().equals(btn_TKHinhThucMua_ThongKe)) {
			dtm_TKHinhThucMua.setRowCount(0);
			if(pn_TKHinhThucMua_KT.isVisible() && dC_TKHinhThucMua_BD.getDate().after(dC_TKHinhThucMua_KT.getDate())) {
				showMess("Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			int tongTienMuaTrucTiep = 0;
			int tongTienMuaOnline = 0;
			for (HoaDon hoaDon : dao_HoaDon.getDsHoaDon(sdf.format(dC_TKHinhThucMua_BD.getDate()), pn_TKHinhThucMua_KT.isVisible()? sdf.format(dC_TKHinhThucMua_KT.getDate()) : "", nhanVien)) {
				String row[] = {hoaDon.getMaHD(), sdf.format(hoaDon.getNgayLap()), (hoaDon.getMaKH()+"").equals("null")? "Mua Trực Tiếp" : "Mua Online", formatter.format(hoaDon.tinhTongTien(dao_ChiTietHoaDon.getDsChiTietHoaDon(hoaDon.getMaHD())))};
				dtm_TKHinhThucMua.addRow(row);
				if((hoaDon.getMaKH()+"").equals("null"))
					tongTienMuaTrucTiep+=hoaDon.tinhTongTien(dao_ChiTietHoaDon.getDsChiTietHoaDon(hoaDon.getMaHD()));
				else
					tongTienMuaOnline+=hoaDon.tinhTongTien(dao_ChiTietHoaDon.getDsChiTietHoaDon(hoaDon.getMaHD()));
			}
			btnBaoCao.setEnabled(dtm_TKHinhThucMua.getRowCount()>0? true : false);
			txt_TKHinhThucMua_TongTienMuaTrucTiep.setText(formatter.format(tongTienMuaTrucTiep));
			txt_TKHinhThucMua_TongTienMuaOnline.setText(formatter.format(tongTienMuaOnline));
			if(cbo_LoaiBieuDo.getSelectedIndex()==0) drawChart("Cột");
			else drawChart("Tròn");
			}
		} else if(e.getSource().equals(btn_TKGG_ThongKe)) {
			dtm_TKGG.setRowCount(0);
			int giamGia_BD = 0;
			int giamGia_KT = 0;
			if(rad_TKGG_Khac.isSelected()) {	giamGia_BD= getTextInJSpinner(spn_TKGG_BD);	giamGia_KT= pn_TKGG_KT.isVisible()? getTextInJSpinner(spn_TKGG_KT) : getTextInJSpinner(spn_TKGG_BD);	}
			else {
				giamGia_BD = rad_TKGG_0.isSelected()? 0 : (rad_TKGG_10.isSelected()? 10 : (rad_TKGG_30.isSelected()? 30 : (rad_TKGG_50.isSelected()? 50 : 70)));
				giamGia_KT = giamGia_BD;
			}
			String check = checkSpinner(giamGia_BD, giamGia_KT);
			if(!check.isEmpty()) {
				showMess(check);
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			for (SanPham sanPham : dao_SanPham.getDsSanPhamTheoGiamGia(giamGia_BD, giamGia_KT)) {
				String row[] = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuongTon()+"", sanPham.getGiamGia()+""};
				dtm_TKGG.addRow(row);
			}
			btnBaoCao.setEnabled(dtm_TKGG.getRowCount()>0? true : false);
			if(cbo_LoaiBieuDo.getSelectedIndex()==0) drawChart("Cột");
			else drawChart("Tròn");
			}
		} else if(e.getSource().equals(btn_TKDT_ThongKe)) {
			dtm_TKDT.setRowCount(0);
			List<String> list = null;
			String row[] = {"",""};
			int tongTien = 0;
			doanhThu_BD = 0;
			doanhThu_KT = 0;
			double tienHD = 0;
			if(!chk_TKDT_ThoiGian.isSelected() && !chk_TKDT_DoanhThu.isSelected()) {
				pn_HinhBieuDo.setVisible(false);
				showMess("Vui lòng chọn tiêu chí thống kê");
			}
			else {
			if(chk_TKDT_DoanhThu.isSelected()) {
				if(rad_TKDT_comBoBox.isSelected()) {
					String selectOfCbo = (cbo_TKDT_DoanhThu.getSelectedItem()+"").replaceAll("tr", "000000");
					if(cbo_TKDT_DoanhThu.getSelectedIndex()<cbo_TKDT_DoanhThu.getItemCount()-1) {
						doanhThu_BD = Integer.parseInt(selectOfCbo.split("-")[0]);
						doanhThu_KT = Integer.parseInt(selectOfCbo.split("-")[1]);
					}
					else {
						doanhThu_BD = Integer.parseInt(selectOfCbo.substring(0, selectOfCbo.length()-3));
						doanhThu_KT = 999999000;
					}
				}
				else {
					doanhThu_BD = getTextInJSpinner(spn_TKDT_BD)*1000;
					doanhThu_KT = pn_TKDT_KT.isVisible()? getTextInJSpinner(spn_TKDT_KT)*1000 : getTextInJSpinner(spn_TKDT_BD);
				}
			} else {
				doanhThu_BD=0;
				doanhThu_KT = 999999000;
			}
			String check = checkSpinner(doanhThu_BD, doanhThu_KT);
			if(!check.isEmpty()) {
				showMess(check);
				pn_HinhBieuDo.setVisible(false);
			}
			else {
			typeTime = "";
			if(chk_TKDT_ThoiGian.isSelected()) {
				if(pn_TKDT_Ngay.isVisible()) {
					typeTime = "Date";
					ngayBD = sdf.format(dC_TKDT_Ngay_BD.getDate());
					ngayKT = dC_TKDT_Ngay_KT.isVisible()? sdf.format(dC_TKDT_Ngay_KT.getDate()) : ngayBD;
				}
				else if(pn_TKDT_Thang.isVisible()) {
					typeTime = "Month";
					ngayBD = yC_TKDT_Thang_BD.getValue()+"-"+(cbo_TKDT_Thang_BD.getSelectedIndex()+1)+"-01";
					ngayKT = "";
					if(cbo_TKDT_Thang_KT.isVisible())
						ngayKT = getEndDateOfMonth(cbo_TKDT_Thang_KT.getSelectedIndex()+1, yC_TKDT_Thang_KT.getValue());
					else
						ngayKT = getEndDateOfMonth(cbo_TKDT_Thang_BD.getSelectedIndex()+1, yC_TKDT_Thang_BD.getValue());
				}
				else if(pn_TKDT_Quy.isVisible()) {
					typeTime = "Quarter";
					if((cbo_TKDT_Quy_BD.getSelectedIndex()+1)==1)
						ngayBD = yC_TKDT_Quy_BD.getValue()+"-01-01";
					else if((cbo_TKDT_Quy_BD.getSelectedIndex()+1)==2)
						ngayBD = yC_TKDT_Quy_BD.getValue()+"-04-01";
					else if((cbo_TKDT_Quy_BD.getSelectedIndex()+1)==3)
						ngayBD = yC_TKDT_Quy_BD.getValue()+"-07-01";
					else
						ngayBD = yC_TKDT_Quy_BD.getValue()+"-10-01";
					if(!cbo_TKDT_Quy_KT.isVisible())
						ngayKT = getEndDateOfMonth(Integer.parseInt(ngayBD.split("-")[1])+2, Integer.parseInt(ngayBD.split("-")[0])); 
					else {
						if(cbo_TKDT_Quy_KT.getSelectedIndex()+1==1) ngayKT = getEndDateOfMonth(3, yC_TKDT_Quy_KT.getValue());
						else if(cbo_TKDT_Quy_KT.getSelectedIndex()+1==2) ngayKT = getEndDateOfMonth(6, yC_TKDT_Quy_KT.getValue());
						else if(cbo_TKDT_Quy_KT.getSelectedIndex()+1==3) ngayKT = getEndDateOfMonth(9, yC_TKDT_Quy_KT.getValue());
						else ngayKT = getEndDateOfMonth(12, yC_TKDT_Quy_KT.getValue());
					}
				}
				else {
					typeTime = "Year";
					ngayBD = yC_TKDT_Nam_BD.getValue()+"-01-01";
					ngayKT = yC_TKDT_Nam_KT.isVisible()? yC_TKDT_Nam_KT.getValue()+"-12-31" : yC_TKDT_Nam_BD.getValue()+"-12-31";
				}
				try {
					if(sdf.parse(ngayBD).after(sdf.parse(ngayKT)))
					{
						showMess("Thời gian bắt đầu phải trước hoặc bằng thời gian kết thúc!");
						pn_HinhBieuDo.setVisible(false);
						txt_TKDT_TongDoanhThu.setText("0");
						return;
					}
				} catch (ParseException e1) {
				}
				list = dao_HoaDon.getListDate(ngayBD, ngayKT, typeTime, nhanVien);
			}
			else//không có thời gian
			{
				typeTime = "Date";
				list = dao_HoaDon.getListDate("", "", "", nhanVien);
			}
			for (String date : list) {
				tienHD = dao_HoaDon.getTongTienHDTheoNgay(date, typeTime, nhanVien);
				if(tienHD>=doanhThu_BD && tienHD<=doanhThu_KT) {
					row[0]=date;
					row[1]=formatter.format(tienHD);
					tongTien+=tienHD;
					dtm_TKDT.addRow(row);
				}
			}
			btnBaoCao.setEnabled(dtm_TKDT.getRowCount()>0? true : false);
			txt_TKDT_TongDoanhThu.setText(formatter.format(tongTien));
			if(cbo_LoaiBieuDo.getSelectedIndex()==0) drawChart("Cột");
			else if(cbo_LoaiBieuDo.getSelectedIndex()==1) drawChart("Tròn");
			else drawChart("Đường");
			}
			}
		} else if(e.getSource().equals(rad_TK_BDCot_15Nhieu) || e.getSource().equals(rad_TK_BDCot_15It) || e.getSource().equals(rad_TK_BDCot_TrungBinh) || e.getSource().equals(rad_TK_BDCot_Chon)) {
				drawChart("Cột");
		} else if(e.getSource().equals(cbo_LoaiBieuDo)) {
			setVisiblePnBieuDo();
		} else if(e.getSource().equals(btnBaoCao)) {
			JnaFileChooser f = new JnaFileChooser();
			f.addFilter("xlsx", "xlsx");
			f.showSaveDialog(null);
			String path = "";
			if (f.getSelectedFile() != null) {
				path = f.getSelectedFile().getAbsolutePath();
				if(path.contains(".")) {
					if(!path.substring(path.lastIndexOf(".")).equals(".xlsx"))
						path += ".xlsx";
					}
				else path += ".xlsx";
				if(pnChiTietTKSLMua.isVisible())
					exportFileExcel(tbl_TKSLMua, path);
				else if(pnChiTietTKSLTon.isVisible())
					exportFileExcel(tbl_TKSLTon, path);
				else if(pnChiTietTKGiamGia.isVisible())
					exportFileExcel(tbl_TKGG, path);
				else exportFileExcel(tbl_TKDT, path);
				showMess("Export file vào "+path+" thành công");
			}
		}
		}
}
