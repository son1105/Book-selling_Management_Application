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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DAO_NhaCungCap;
import dao.Dao_SanPham;
import entity.NhaCungCap;
import rojerusan.RSTableMetro;
public class Gui_QuanLyNhaCungCap extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RSTableMetro tbl;
	public JTextField txt_Ma;
	public JTextField txt_Ten;
	public JTextField txt_SDT;
	public JTextField txt_DiaChi;
	private DefaultTableModel dfm;
	private DAO_NhaCungCap dao_NhaCungCap;
	private JButton btnThem;
	private JButton btn_CapNhat;
	private JButton   btnXoa;
	private Dao_SanPham dao_SanPham;
	private JPanel pn_tim;
	private JTextField txtTim;
	private JButton btnTim;

	public Gui_QuanLyNhaCungCap() {
		getContentPane().setLayout(null);
		this.setSize(1300, 749);
		 dao_NhaCungCap = new DAO_NhaCungCap();
		 dao_SanPham = new Dao_SanPham();
		getContentPane().add(controll_QuanLyNhaCungCap());
		
		
	}

	public Component controll_QuanLyNhaCungCap() {
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


		JScrollPane scr_table = new JScrollPane();
		scr_table.setOpaque(false);
		scr_table.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "Danh s\u00E1ch nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		scr_table.setBounds(10, 291, 1217, 373);
		pn.add(scr_table);

		tbl = formatTable(scr_table, dfm = new DefaultTableModel(new String[] {
				"Mã nhà cung cấp", "Tên nhà cung cấp" , "Số điện thoại", "Địa chỉ"
		}, 0));
		tbl.getColumnModel().getColumn(0).setPreferredWidth(118);
		tbl.getColumnModel().getColumn(0).setMinWidth(27);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(106);
		
		JPanel pn_Thongtin = new JPanel();
		pn_Thongtin.setOpaque(false);
		pn_Thongtin.setBounds(10, 11, 1217, 218);
		pn_Thongtin.setBorder(new LineBorder(Color.RED, 3));
		pn.add(pn_Thongtin);
		pn_Thongtin.setLayout(null);

		JLabel lbl_thongtin = new JLabel("Thông tin Nhà cung cấp");
		lbl_thongtin.setBackground(Color.RED);
		lbl_thongtin.setForeground(Color.RED);
		lbl_thongtin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_thongtin.setBounds(531, 11, 254, 32);
		pn_Thongtin.add(lbl_thongtin);

		JLabel lbl_ma = new JLabel("Mã nhà cung cấp");
		lbl_ma.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_ma.setBounds(141, 54, 159, 25);
		pn_Thongtin.add(lbl_ma);

		JLabel lbl_ten = new JLabel("Tên nhà cung cấp");
		lbl_ten.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_ten.setBounds(141, 104, 167, 25);
		pn_Thongtin.add(lbl_ten);

		JLabel lbl_sdt = new JLabel("SDT");
		lbl_sdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_sdt.setBounds(696, 54, 139, 25);
		pn_Thongtin.add(lbl_sdt);

		JLabel lbl_diachi = new JLabel("Địa chỉ");
		lbl_diachi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_diachi.setBounds(696, 104, 70, 25);
		pn_Thongtin.add(lbl_diachi);

		txt_Ma = new JTextField();
		txt_Ma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Ma.setEditable(false);
		txt_Ma.setBounds(310, 54, 236, 25);
		pn_Thongtin.add(txt_Ma);
		txt_Ma.setColumns(10);

		txt_Ten = new JTextField();
		txt_Ten.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Ten.setColumns(10);
		txt_Ten.setBounds(310, 104, 236, 25);
		pn_Thongtin.add(txt_Ten);

		txt_SDT = new JTextField();
		txt_SDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_SDT.setColumns(10);
		txt_SDT.setBounds(762, 54, 236, 25);
		pn_Thongtin.add(txt_SDT);

		 btn_CapNhat = new JButton("Cập Nhật");
		btn_CapNhat.setBounds(884, 153, 114, 32);
		pn_Thongtin.add(btn_CapNhat);
		formatButton(btn_CapNhat, "");
		changeColorButtonWhenEntered(btn_CapNhat);
		changeColorButtonWhenExited(btn_CapNhat);

		txt_DiaChi = new JTextField();
		txt_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_DiaChi.setColumns(10);
		txt_DiaChi.setBounds(762, 104, 236, 25);
		pn_Thongtin.add(txt_DiaChi);

	    btnXoa = new JButton("Xóa");
		btnXoa.setBounds(559, 155, 130, 31);
		pn_Thongtin.add(btnXoa);
		formatButton(btnXoa, "");
		changeColorButtonWhenEntered(btnXoa);
		changeColorButtonWhenExited(btnXoa);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(232, 153, 136, 32);
		pn_Thongtin.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		formatButton(btnThem, "");
		changeColorButtonWhenEntered(btnThem);
		changeColorButtonWhenExited(btnThem);
		
		pn_tim = new JPanel();
		pn_tim.setOpaque(false);
		pn_tim.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 0, 0)));
		pn_tim.setBounds(10, 240, 1217, 51);
		pn.add(pn_tim);
		pn_tim.setLayout(null);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTim.setBounds(322, 12, 415, 29);
		txtTim.setColumns(10);
		pn_tim.add(txtTim);
		
		btnTim = new JButton("Tìm kiếm");
		btnTim.setBounds(745, 11, 117, 29);
		pn_tim.add(btnTim);
		formatButton(btnTim, "");
		changeColorButtonWhenEntered(btnTim);
		changeColorButtonWhenExited(btnTim);
		
		JLabel lbl_timKiem = new JLabel("Nhập mã nhà cung cấp:");
		lbl_timKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_timKiem.setBounds(100, 11, 251, 30);
		pn_tim.add(lbl_timKiem);
		
		
		btnTim.addActionListener(this);
		btn_CapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btn_CapNhat.addActionListener(this);
		
		loadData();
		
		return pn;
		
	}
	

	public void clearText() {
		txt_Ma.setText("");
		txt_Ten.setText("");
		txt_SDT.setText("");
		txt_DiaChi.setText("");

	}
	public void loadData()
	{
		dfm.setRowCount(0);
		for (NhaCungCap ncc: dao_NhaCungCap.getDsNhaCung()){		
		String row[] = {ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSdt(), ncc.getDiaChi()};
		themDongTable(row);
		}
	}
	public void themDongTable(String row[]) {
		dfm.addRow(row);
	}
	public void  xoaDongTable(int row) {
		dfm.removeRow(row);
	}

	

	public int thucHienTim(String maNCCTimKiem) {
			
			// Thuc hien tim kiem
			
			int soLuongDong = tbl.getRowCount();
				for (int i = 0; i < soLuongDong; i++) {
					String ma =dfm.getValueAt(i, 0)+"";
					if (maNCCTimKiem.equals(ma)) {
						return i;
					}
				}
			return -1;
}
	

	
	public void xoaNCC() {
		int i_row = tbl.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa sinh viên này",
                "Xác nhận", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if (luaChon == JOptionPane.YES_OPTION) {
			
			xoaDongTable(i_row);
			clearText();

	}
	if(luaChon==JOptionPane.NO_OPTION)
		{
			this.clearText();
		}
	}
	
	/**
	 * tạo mã nhà cung cấp
	 * @param ma
	 * @return String
	 */
	public String taoMaNCC(String ma) {
		int soMa = Integer.parseInt(ma.substring(3));
		soMa++;
		if (soMa < 10) {
			return "NCC000" + soMa;
		} else if (soMa < 100) {
			return "NCC00" + soMa;
		} else if (soMa < 1000) {
			return "NCC0" + soMa;
		}
		return "NCC" + soMa;
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
		btn.setBackground(new Color(255, 185, 168));
		btn.setMargin(new Insets(2, 0, 2, 14));
		btn.setIcon(new ImageIcon(img));
		btn.setFocusable(false);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public void changeColorButtonWhenEntered(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(new Color(255, 209, 209));
				btn.setBorder(new LineBorder(Color.YELLOW, 2, true));
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
				btn.setBackground(new Color(255, 185, 168));
				btn.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ac = e.getSource();
		

		if (ac.equals(btnThem)) {
			
			int n=thucHienTim(txt_Ma.getText());
			if ( this.txt_Ten.getText().equals("")
					|| this.txt_SDT.getText().equals("")
					|| this.txt_DiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Điền không đủ thông tin");
			}else if(n!=-1)
			
			{
				
				String maNCC =  (String) tbl.getValueAt(n, 0);
				String tenNCC = this.txt_Ten.getText();
				String sdt = this.txt_SDT.getText();
				String diaChi = this.txt_DiaChi.getText();
				String rowThem[] = {maNCC, tenNCC, sdt, diaChi};
				xoaDongTable(n);
				themDongTable(rowThem);
				NhaCungCap ncc= new NhaCungCap(maNCC, tenNCC, sdt, diaChi);
				dao_NhaCungCap.updateNhaCungCap(ncc);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				
			}
			
			
			else if(n==-1) {
				
				String maNCC = taoMaNCC(new DAO_NhaCungCap().getNhaCungMoi().getMaNCC());
				String tenNCC = this.txt_Ten.getText();
				String sdt = this.txt_SDT.getText();
				String diaChi = this.txt_DiaChi.getText();
				String rowThem[] = {maNCC, tenNCC, sdt, diaChi};
				themDongTable(rowThem);
				NhaCungCap ncc= new NhaCungCap(maNCC, tenNCC, sdt, diaChi);
				dao_NhaCungCap.insertNcc(ncc);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			}
			clearText();

		} else if (ac.equals(btnXoa)) {
			int n = tbl.getSelectedRow();
			if (n==-1)
			{
				JOptionPane.showMessageDialog(this, "Cần chọn nhà cung cấp nhà cung cấp");
			}	else {
				if(dao_SanPham.dieuKienXoaNCC(dfm.getValueAt(n, 0)+"").size()==0)
				{
					
					
					NhaCungCap ncc= new NhaCungCap(dfm.getValueAt(n, 0)+"", dfm.getValueAt(n, 1)+"", dfm.getValueAt(n, 2)+"", dfm.getValueAt(n, 3)+"");
					dao_NhaCungCap.deleteNhaCungCap(ncc);
					this.xoaNCC();
					JOptionPane.showMessageDialog(this, "Đã xóa nhà cung cấp");
				}else 
			
				{
					JOptionPane.showMessageDialog(this, "Còn tồn tại sản phẩm không thể xóa nhà cung cấp");
				}
				clearText();
			}

		} else if (ac.equals(btn_CapNhat)) {
			
			int n = tbl.getSelectedRow();
			if(n==-1)
			{
				JOptionPane.showMessageDialog(null, "Cần chọn đối tượng cập nhật");
			}
			else
			{
			txt_Ma.setText(dfm.getValueAt(n, 0)+"");
			txt_Ten.setText(dfm.getValueAt(n, 1)+"");
			txt_SDT.setText(dfm.getValueAt(n, 2)+"");
			txt_DiaChi.setText(dfm.getValueAt(n, 3)+"");
			tbl.setRowSelectionInterval(n, n);
			}
			
			
		} else if (ac.equals(btnTim)) {
				int dongTimDuoc = thucHienTim(txtTim.getText());
				if(dongTimDuoc==-1)
					JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp");
				else {
					
					txt_Ma.setText(dfm.getValueAt(dongTimDuoc, 0)+"");
					txt_Ten.setText(dfm.getValueAt(dongTimDuoc, 1)+"");
					txt_SDT.setText(dfm.getValueAt(dongTimDuoc, 2)+"");
					txt_DiaChi.setText(dfm.getValueAt(dongTimDuoc, 3)+"");
					tbl.setRowSelectionInterval(dongTimDuoc, dongTimDuoc);
				}
		}

	}
	public static void main(String[] args) {
		
		new Gui_QuanLyNhaCungCap().setVisible(true);
	}
}

