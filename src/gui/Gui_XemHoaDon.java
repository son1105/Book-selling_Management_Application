package gui;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import rojerusan.RSTableMetro;

import javax.swing.border.TitledBorder;

public class Gui_XemHoaDon extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbl_DSHD;
	@SuppressWarnings("unused")
	private JTable tbl_ChiTiet;
	private DefaultTableModel dfm;
	private DefaultTableModel dfm_chitietHD;
	private JButton btnTim;
	private Dao_HoaDon dao_HoaDon;
	private Dao_ChiTietHoaDon dao_ChiTietHoaDon;
	private NhanVien nv;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JDateChooser date_Star;
	private JDateChooser date_END;
	private JButton btnCapNhat;
	private JButton btnLapHD;

	public Gui_XemHoaDon(NhanVien nhanVien) {

		getContentPane().setLayout(null);
		this.setSize(1300, 749);
		
		nv = nhanVien;
		getContentPane().add(controll_XemHoaDon());
		dao_HoaDon = new Dao_HoaDon();
		dao_ChiTietHoaDon = new Dao_ChiTietHoaDon();
		
	}

	public Component controll_XemHoaDon() {
		
		JPanel pnXemHD = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				int w = getWidth(), h = getHeight();
				Color color1 = new Color(255, 190, 190);
				Color color2 = new Color(255, 240, 240);
				GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		pnXemHD.setBounds(0, 0, 1237, 664);
		pnXemHD.setLayout(null);
		
		JScrollPane scr_DSHD = new JScrollPane();
		scr_DSHD.setOpaque(false);
		scr_DSHD.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		scr_DSHD.setBounds(0, 62, 751, 591);
		pnXemHD.add(scr_DSHD);

		tbl_DSHD = formatTable(scr_DSHD, dfm = new DefaultTableModel(
				new String[] { "M\u00E3 HD", "M\u00E3 NV", "Ng\u00E0y \u0111\u1EB7t", "Ng\u00E0y l\u1EA5y h\u00E0ng",
						"Ng\u00E0y giao", "Tr\u1EA1ng th\u00E1i", "\u0110\u1ECBa ch\u1EC9" },
				0));
		

		JScrollPane scr_chitiet = new JScrollPane();
		scr_chitiet.setOpaque(false);
		scr_chitiet.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2),
				"Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		scr_chitiet.setBounds(761, 62, 466, 591);
		pnXemHD.add(scr_chitiet);

		tbl_ChiTiet = formatTable(scr_chitiet, dfm_chitietHD = new DefaultTableModel(new String[] { "M\u00E3 H\u00F3a \u0110\u01A1n", "M\u00E3 s\u1EA3n ph\u1EA9m",
						"S\u1ED1 l\u01B0\u1EE3ng", "G\u00EDa b\u00E1n" },0));

		JPanel pnMenu = new JPanel();
		pnMenu.setOpaque(false);
		pnMenu.setBorder(new LineBorder(Color.RED, 2));
		pnMenu.setBounds(0, 11, 1227, 47);
		pnXemHD.add(pnMenu);
		pnMenu.setLayout(null);

		JLabel lbl_ngaybd = new JLabel("Từ ngày");
		lbl_ngaybd.setBounds(10, 10, 112, 25);
		pnMenu.add(lbl_ngaybd);
		lbl_ngaybd.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lbl_den = new JLabel("Đến");
		lbl_den.setBounds(281, 10, 46, 25);
		pnMenu.add(lbl_den);
		lbl_den.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnTim = new JButton("Tìm kiếm");
		btnTim.setBounds(543, 5, 106, 35);
		pnMenu.add(btnTim);
		formatButton(btnTim, "");
		changeColorButtonWhenEntered(btnTim);
		changeColorButtonWhenExited(btnTim);

		date_Star = new JDateChooser();
		date_Star.setBounds(89, 10, 157, 25);
		pnMenu.add(date_Star);
		formatDateChooser(date_Star);

		date_END = new JDateChooser();
		date_END.setBounds(325, 10, 151, 25);
		pnMenu.add(date_END);
		formatDateChooser(date_END);

		 btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(877, 5, 100, 35);
		pnMenu.add(btnCapNhat);
		formatButton(btnCapNhat, "");
		changeColorButtonWhenEntered(btnCapNhat);
		changeColorButtonWhenExited(btnCapNhat);
		
		
		 btnLapHD = new JButton("Lập hóa đơn");
		btnLapHD.setBounds(704, 5, 112, 35);
		pnMenu.add(btnLapHD);
		formatButton(btnLapHD, "");
		changeColorButtonWhenEntered(btnLapHD);
		changeColorButtonWhenExited(btnLapHD);

		if(nv.getChucVu().equals("Quản Lý"))
		{
			btnLapHD.setEnabled(false);
			btnLapHD.setBackground(new Color(255, 245, 228));
		}
		
		btnTim.addActionListener(this);
		tbl_DSHD.addMouseListener(this);
		btnCapNhat.addActionListener(this);
		btnLapHD.addActionListener(this);
		return pnXemHD;
	}

	/**
	 * Định dạng cho DateChooser
	 * 
	 * @param dC : dateChooser cần định dạng
	 */
	public void formatDateChooser(JDateChooser dC) {
		dC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dC.setOpaque(false);
		((JTextField) dC.getDateEditor().getUiComponent()).setEditable(false);
		((JTextField) dC.getDateEditor().getUiComponent()).setHorizontalAlignment((int) CENTER_ALIGNMENT);
		dC.setBorder(new LineBorder(new Color(122, 138, 153)));
		dC.setDateFormatString("dd-MM-y");
		dC.setDate(new Date(System.currentTimeMillis()));
		dC.setMaxSelectableDate(new Date(System.currentTimeMillis()));
		((JTextField) dC.getDateEditor().getUiComponent()).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dC.getCalendarButton().doClick();
			}
		});
	}

	/**
	 * Định dạng cho button
	 * 
	 * @param btn : button cần định dạng
	 * @param img : icon của button
	 */
	public void formatButton(JButton btn, String img) {
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(new LineBorder(new Color(255, 0, 255), 2, true));
		btn.setBackground(new Color(250, 112, 112));
		btn.setMargin(new Insets(2, 0, 2, 14));
		// btn.setIcon(new ImageIcon(img));
		btn.setFocusable(false);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public void changeColorButtonWhenEntered(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btn.isEnabled()) {
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
				if (btn.isEnabled()) {
					btn.setBackground(new Color(250, 112, 112));
					btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
				}
			}
		});
	}
	
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
	

	public static void main(String[] args) {
		new Gui_XemHoaDon(new NhanVien("NV22000", null, null, null, null, "Quản Lý")).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnTim)) {
			dfm.setRowCount(0);
			for (HoaDon hd : dao_HoaDon.getDsHoaDon(sdf.format(date_Star.getDate()),
					sdf.format(date_END.getDate()), nv)) {
				String row[] = { hd.getMaHD(), hd.getMaNV(), sdf.format(hd.getNgayLap()), "", "", hd.getTrangThai(),
						hd.getDiaChi() };

				if (hd.getNgayLayHang() != null)
					row[3] = sdf.format(hd.getNgayLayHang());
				if (hd.getNgayGiaoHang() != null)
					row[4] = sdf.format(hd.getNgayGiaoHang());
				dfm.addRow(row);

			}
		}else if (e.getSource().equals(btnLapHD)) {
			Gui_NhanVien_QLHD.chuyenTrang(2);
		}else if (e.getSource().equals(btnCapNhat)) {
			Gui_NhanVien_QLHD.chuyenTrang(3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tbl_DSHD)) {

			int n = tbl_DSHD.getSelectedRow();
			dfm_chitietHD.setRowCount(0);
			String maHD = tbl_DSHD.getValueAt(n, 0) + "";
			for (ChiTietHoaDon cthd : dao_ChiTietHoaDon.getDsChiTietHoaDon(maHD)) {
				Object row[] = { cthd.getMaDH(), cthd.getMaSP(), cthd.getSoLuong(), cthd.getGiaBan() };
				dfm_chitietHD.addRow(row);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(tbl_DSHD)) {
			if (tbl_DSHD.getSelectedRow() != -1) {
				dfm_chitietHD.setRowCount(0);
				String maHD = tbl_DSHD.getValueAt(tbl_DSHD.getSelectedRow(), 0) + "";
				for (ChiTietHoaDon cthd : dao_ChiTietHoaDon.getDsChiTietHoaDon(maHD)) {
					Object row[] = { cthd.getMaDH(), cthd.getMaSP(), cthd.getSoLuong(), cthd.getGiaBan() };
					dfm_chitietHD.addRow(row);
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
}
