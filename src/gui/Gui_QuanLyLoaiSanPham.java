package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DAO_LoaiSanPham;
import dao.Dao_SanPham;
import entity.LoaiSanPhan;
import rojerusan.RSTableMetro;
public class Gui_QuanLyLoaiSanPham extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private RSTableMetro tbl;
	private JTextField txt_MaSP;
	private JTextField txt_TenSP;
	private DefaultTableModel dfm_lsp;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btn_CapNhat;
	private JButton btnXoa;
	private DAO_LoaiSanPham dao_LoaiSanPham;
	private Dao_SanPham dao_SanPham;
	public Gui_QuanLyLoaiSanPham() {
		getContentPane().setLayout(null);
		this.setSize(1300, 749);
		dao_LoaiSanPham = new DAO_LoaiSanPham();
		 dao_SanPham = new Dao_SanPham();
		getContentPane().add(controll_QuanLyLoaiSanPham());
		

	}

	public Component controll_QuanLyLoaiSanPham() {
		JPanel pn = new JPanel() {
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
		pn.setBounds(0, 0, 1237, 664);
		pn.setLayout(null);

		JPanel pn_menu = new JPanel();
		pn_menu.setOpaque(false);
		pn_menu.setBorder(new LineBorder(Color.PINK, 3));
		pn_menu.setForeground(Color.PINK);
		pn_menu.setBounds(10, 11, 1217, 51);
		pn.add(pn_menu);
		pn_menu.setLayout(null);

		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTim.setForeground(Color.RED);
		txtTim.setBackground(Color.WHITE);
		txtTim.setBorder(new LineBorder(Color.RED, 2));
		txtTim.setCaretColor(Color.RED);
		txtTim.setBounds(470, 11, 415, 30);
		pn_menu.add(txtTim);
		txtTim.setColumns(10);

		 btnTim = new JButton("Tìm kiếm");
		btnTim.setBounds(905, 11, 110, 29);
		pn_menu.add(btnTim);
		formatButton(btnTim, "");
		changeColorButtonWhenEntered(btnTim);
		changeColorButtonWhenExited(btnTim);
		
		JLabel lblTimKiem = new JLabel("Nhập mã loại sản phẩm");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimKiem.setBounds(247, 12, 211, 28);
		pn_menu.add(lblTimKiem);

		JPanel pn_danhsach = new JPanel();
		pn_danhsach.setOpaque(false);
		pn_danhsach.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh s\u00E1ch lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pn_danhsach.setBounds(10, 70, 608, 579);
		pn.add(pn_danhsach);
		pn_danhsach.setLayout(null);

		JScrollPane scr_Tbl = new JScrollPane();
		scr_Tbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		scr_Tbl.setBorder(null);
		scr_Tbl.setBounds(10, 20, 588, 549);
		pn_danhsach.add(scr_Tbl);

		tbl = formatTable(scr_Tbl, dfm_lsp = new DefaultTableModel(new String[] {
				"Mã loại sản phẩm", "Tên loại sản phẩm"
		}, 0));
		JPanel pn_ThongTin = new JPanel();
		pn_ThongTin.setOpaque(false);
		pn_ThongTin.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		pn_ThongTin.setBounds(661, 76, 566, 570);
		pn.add(pn_ThongTin);
		pn_ThongTin.setLayout(null);

		JLabel lblMaloai = new JLabel("Mã loại sản phẩm");
		lblMaloai.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaloai.setBounds(38, 162, 185, 28);
		pn_ThongTin.add(lblMaloai);

		JLabel lblThongtin = new JLabel("Thông tin Loại sản phẩm");
		lblThongtin.setForeground(Color.RED);
		lblThongtin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblThongtin.setBounds(141, 11, 346, 49);
		pn_ThongTin.add(lblThongtin);

		JLabel lblTenLSP = new JLabel("Tên loại sản phẩm");
		lblTenLSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenLSP.setBounds(38, 268, 185, 28);
		pn_ThongTin.add(lblTenLSP);

		 btnThem = new JButton("Thêm");
		btnThem.setBounds(38, 426, 120, 35);
		pn_ThongTin.add(btnThem);
		formatButton(btnThem, "");
		changeColorButtonWhenEntered(btnThem);
		changeColorButtonWhenExited(btnThem);

		btn_CapNhat = new JButton("Cập nhật");
		btn_CapNhat.setBounds(233, 426, 120, 35);
		pn_ThongTin.add(btn_CapNhat);
		formatButton(btn_CapNhat, "");
		changeColorButtonWhenEntered(btn_CapNhat);
		changeColorButtonWhenExited(btn_CapNhat);

		 btnXoa = new JButton("Xóa");
		btnXoa.setBounds(428, 426, 105, 35);
		pn_ThongTin.add(btnXoa);
		formatButton(btnXoa, "");
		changeColorButtonWhenEntered(btnXoa);
		changeColorButtonWhenExited(btnXoa);

		txt_MaSP = new JTextField();
		txt_MaSP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_MaSP.setEditable(false);
		txt_MaSP.setForeground(Color.RED);
		txt_MaSP.setBorder(new LineBorder(Color.RED));
		txt_MaSP.setBounds(233, 162, 300, 30);
		pn_ThongTin.add(txt_MaSP);
		txt_MaSP.setColumns(10);

		txt_TenSP = new JTextField();
		txt_TenSP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_TenSP.setForeground(Color.RED);
		txt_TenSP.setBorder(new LineBorder(Color.RED));
		txt_TenSP.setColumns(10);
		txt_TenSP.setBounds(233, 268, 300, 30);
		pn_ThongTin.add(txt_TenSP);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btn_CapNhat.addActionListener(this);
		loadData();
		return pn;
	}

	public void clearText() {
		txt_MaSP.setText("");
		txt_TenSP.setText("");

	}

	public void themDongTable(String row[]) {
		dfm_lsp.addRow(row);
	}

	public void xoaDongTable(int row) {
		dfm_lsp.removeRow(row);
	}

	public int thucHienTim(String maLSPTimKiem) {

		// Thuc hien tim kiem
		
		int soLuongDong = tbl.getRowCount();
		for (int i = 0; i < soLuongDong; i++) {
			String ma = dfm_lsp.getValueAt(i, 0) + "";
			if (maLSPTimKiem.equals(ma)) {
				return i;
			}
		}
		return -1;
	}

	public void xoaLSP() {
		int i_row = tbl.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sinh viên này", "Xác nhận",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (luaChon == JOptionPane.YES_OPTION) {

			xoaDongTable(i_row);
			clearText();

		}
		if (luaChon == JOptionPane.NO_OPTION) {
			this.clearText();
		}
	}
	public void loadData()
	{
		dfm_lsp.setRowCount(0);
		for (LoaiSanPhan lsp: dao_LoaiSanPham.getDsloaiSp()){		
		String row[] = {lsp.getMaLoai(), lsp.getTenLoai()};
		themDongTable(row);
		}
	}
	public String taoMaLSP( ) {
		
		int soDong =tbl.getRowCount()+1;
		if (soDong < 10) {
			return "L000" + soDong;
		} else if (soDong < 100) {
			return "L00" + soDong;
		} else if (soDong < 1000) {
			return "L0" + soDong;
		} 
		return "L" + soDong;
		}
	/**
	 * Định dạng cho button
	 * 
	 * @param btn : button cần định dạng
	 * @param img : icon của button
	 */
	public void formatButton(JButton btn, String img) {
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ac = e.getSource();
		
		

		if (ac.equals(btnThem)) {
			int n=thucHienTim(txt_MaSP.getText());
			if ( this.txt_TenSP.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Điền không đủ thông tin");
				
			} else if(n!=-1)
				
				{
					
					String maLSP =  (String) tbl.getValueAt(n, 0);
					String tenLSP = this.txt_TenSP.getText();
					String row[] = { maLSP, tenLSP};
					xoaDongTable(n);
					dfm_lsp.addRow(row);
					LoaiSanPhan lsp = new LoaiSanPhan(maLSP, tenLSP);
					dao_LoaiSanPham.updateLoaiSanPham(lsp);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					
				}
				
				
				else if(n==-1) {
					
					String maLSP = taoMaLSP();
					String tenLSP = this.txt_TenSP.getText();
					String rowThem[] = {maLSP, tenLSP};
					themDongTable(rowThem);
					LoaiSanPhan lsp= new LoaiSanPhan(maLSP, tenLSP);
					dao_LoaiSanPham.insertLoaiSanPham(lsp);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				}
			clearText();

		} else if (ac.equals(btnXoa)) {
			int n = tbl.getSelectedRow();
				if(n==-1)
					{JOptionPane.showMessageDialog(this, "Cần chọn loại sản phẩm");}
				else {
					if(dao_SanPham.dieuKienXoaLSP(dfm_lsp.getValueAt(n, 0)+"").size()==0)
					{
					LoaiSanPhan lsp= new LoaiSanPhan(dfm_lsp.getValueAt(n, 0)+"", dfm_lsp.getValueAt(n, 1)+"");
						dao_LoaiSanPham.deleteLoaiSanPham(lsp);
						this.xoaLSP();
						JOptionPane.showMessageDialog(this, "Đã xóa loại sản phẩm");
					}else
					{
						JOptionPane.showMessageDialog(this, "Còn tồn tại sản phẩm chưa không thể xóa");
					}
					
			}
				clearText();

		} else if (ac.equals(btn_CapNhat)) {
			int n = tbl.getSelectedRow();
			if(n==-1)
			{
				JOptionPane.showMessageDialog(null, "Cần chọn đối tượng cập nhật");
			}
			else
			{
			
			txt_MaSP.setText(dfm_lsp.getValueAt(n, 0) + "");
			txt_TenSP.setText(dfm_lsp.getValueAt(n, 1) + "");
			tbl.setRowSelectionInterval(n, n);
			}

		} else if (ac.equals(btnTim)) {
			int dongTimDuoc = thucHienTim(txtTim.getText());
			if (dongTimDuoc == -1)
				JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp");
			else {
				txt_MaSP.setText(dfm_lsp.getValueAt(dongTimDuoc, 0) + "");
				txt_TenSP.setText(dfm_lsp.getValueAt(dongTimDuoc, 1) + "");
				tbl.setRowSelectionInterval(dongTimDuoc, dongTimDuoc);
			}
		txtTim.setText("");
		}

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
	public static void main(String[] args) {
		new Gui_QuanLyLoaiSanPham().setVisible(true);
	}
}
