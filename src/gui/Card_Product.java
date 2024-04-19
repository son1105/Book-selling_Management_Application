package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_DiaChi;
import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.QuanHuyen;
import entity.Sach;
import entity.SanPham;
import entity.TaiKhoan;
import entity.TinhThanh;
import entity.VanPhongPham;
import entity.XaPhuong;

public class Card_Product extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblExit;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private JLabel lblSoLuong;
	private JLabel lblGiam;
	private JLabel lblTang;
	private int SLTMax=0;
	private JLabel lblImgSP_1;
	private JLabel lblImgSP_2;
	private JLabel lblImgSP_3;
	private JLabel lblImgSP_4;
	private JLabel lblImgSP;
	private JPanel pnMuaHang;
	@SuppressWarnings("unused")
	private int indexQuanHuyen;
	private TaiKhoan taiKhoan;
	private SanPham sanPham;
	
	public Card_Product(SanPham sp ,TaiKhoan tk) {
		taiKhoan = tk;
		sanPham =sp;
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850,425);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
		
		SLTMax= sp.getSoLuongTon();
		JPanel pnCard_SanPham = new JPanel() {
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
				Color color2 = new Color(171,186,171);
				GradientPaint gp = new GradientPaint(0, 0, Color.white, w, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
			
		};
		pnCard_SanPham.setBounds(0, 0, 850, 425);
		getContentPane().add(pnCard_SanPham);
		pnCard_SanPham.setLayout(null);
		
		lblImgSP = new JLabel("");
		lblImgSP.setIcon(new ImageIcon(new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH)));
		lblImgSP.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY, null, null));
		lblImgSP.setBounds(20, 25, 300, 300);
		pnCard_SanPham.add(lblImgSP);
		
		String textTenSp = "<html><p>"+sp.getTenSP()+"</p></html>";
		JLabel lblTenSanPham = new JLabel();
		lblTenSanPham.setBorder(null);
		lblTenSanPham.setVerticalAlignment(SwingConstants.TOP);
		if (new String(sp.getTenSP()).length()>49) {
			lblTenSanPham.setFont(new Font("Times New Roman", Font.BOLD, 17));
		}else {
			lblTenSanPham.setFont(new Font("Times New Roman", Font.BOLD, 30));
		}
		lblTenSanPham.setText(textTenSp);
		lblTenSanPham.setBounds(340, 25, 479, 42);
		pnCard_SanPham.add(lblTenSanPham);
		
		JLabel lblNCC = new JLabel("Nhà Cung Cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCC.setBounds(340, 76, 85, 14);
		pnCard_SanPham.add(lblNCC);
		
		JLabel lblTenNCC = new JLabel(sp.getMaNcc());
		lblTenNCC.setBounds(425, 76, 120, 14);
		pnCard_SanPham.add(lblTenNCC);
		
		JLabel lblTenLoai = new JLabel(sp.getMaLoai());
		lblTenLoai.setBounds(372, 95, 169, 14);
		pnCard_SanPham.add(lblTenLoai);
		
		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoai.setBounds(340, 95, 30, 14);
		pnCard_SanPham.add(lblLoai);
		
		if (sp instanceof Sach) {
			JLabel lblTacGia = new JLabel("Tác Giả:");
			lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTacGia.setBounds(617, 76, 75, 14);
			pnCard_SanPham.add(lblTacGia);
			
			JLabel lblTenTacGia = new JLabel(  ((Sach)sp).getTacGia());
			lblTenTacGia.setBounds(697, 76, 143, 14);
			pnCard_SanPham.add(lblTenTacGia);
			
			JLabel lblSoTrang = new JLabel("Số Trang:");
			lblSoTrang.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSoTrang.setBounds(617, 95, 75, 14);
			pnCard_SanPham.add(lblSoTrang);
			
			JLabel lblSoLuongTrang = new JLabel( ((Sach)sp).getSoTrang()+"");
			lblSoLuongTrang.setBounds(697, 95, 143, 14);
			pnCard_SanPham.add(lblSoLuongTrang);
			
			JLabel lblSoNamXB = new JLabel( ((Sach)sp).getNamXB()+"");
			lblSoNamXB.setBounds(697, 115, 143, 14);
			pnCard_SanPham.add(lblSoNamXB);
			
			JLabel lblNamXB = new JLabel("Năm XB:");
			lblNamXB.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNamXB.setBounds(617, 115, 75, 14);
			pnCard_SanPham.add(lblNamXB);
			
			JLabel lblNXB = new JLabel("Nhà Xuất Bản:");
			lblNXB.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNXB.setBounds(617, 135, 85, 14);
			pnCard_SanPham.add(lblNXB);
			
			JLabel lblTenNXB = new JLabel( ((Sach)sp).getNhaXuatBan());
			lblTenNXB.setBounds(707, 135, 133, 14);
			pnCard_SanPham.add(lblTenNXB);
		}
		
		
		
		if (sp instanceof VanPhongPham) {
			JLabel lblThuongHieu = new JLabel("Thương Hiệu:");
			lblThuongHieu.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblThuongHieu.setBounds(617, 76, 75, 14);
			pnCard_SanPham.add(lblThuongHieu);
			
			JLabel lblTenThuongHieu = new JLabel(  ((VanPhongPham)sp).getThuongHieu());
			lblTenThuongHieu.setBounds(697, 76, 143, 14);
			pnCard_SanPham.add(lblTenThuongHieu);
			
			JLabel lblSoTrang = new JLabel("Màu Sắc:");
			lblSoTrang.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSoTrang.setBounds(617, 95, 75, 14);
			pnCard_SanPham.add(lblSoTrang);
			
			JLabel lblTenMau = new JLabel( ((VanPhongPham)sp).getMau());
			lblTenMau.setBounds(697, 95, 143, 14);
			pnCard_SanPham.add(lblTenMau);
			
			JLabel lblTenChatLieu = new JLabel( ((VanPhongPham)sp).getChatLieu());
			lblTenChatLieu.setBounds(697, 115, 143, 14);
			pnCard_SanPham.add(lblTenChatLieu);
			
			JLabel lblChatLieu = new JLabel("Chất Liệu:");
			lblChatLieu.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblChatLieu.setBounds(617, 115, 75, 14);
			pnCard_SanPham.add(lblChatLieu);
		}
		
		
		
		
		JLabel lblSoTrongLuong = new JLabel(sp.getKhoiLuong()+" gr");
		lblSoTrongLuong.setBounds(420, 115, 117, 14);
		pnCard_SanPham.add(lblSoTrongLuong);
		
		JLabel lblTrongLuong = new JLabel("Trọng Lượng: ");
		lblTrongLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrongLuong.setBounds(340, 115, 77, 14);
		pnCard_SanPham.add(lblTrongLuong);
		
		
		
		float x = sp.getGiaBan() - sp.getGiaBan()*((float)sp.getGiamGia()/100);
		
		JLabel lblDonGiaDaGiam = new JLabel(formatter.format(x)+" VNĐ");
		lblDonGiaDaGiam.setForeground(Color.RED);
		lblDonGiaDaGiam.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDonGiaDaGiam.setBounds(340, 156, 143, 25);
		pnCard_SanPham.add(lblDonGiaDaGiam);
		
		if (sp.getGiamGia()!=0) {
			String textDonGia = "<html><p style='text-decoration: line-through;'>"+formatter.format(sp.getGiaBan())+"VNĐ</p></html>";
			JLabel lblDonGia = new JLabel(textDonGia);
			lblDonGia.setVerticalAlignment(SwingConstants.TOP);
			lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblDonGia.setBounds(493, 162, 120, 20);
			pnCard_SanPham.add(lblDonGia);
			
			JPanel pnPhanTramGiam = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        //  Paint Border
			        g2.setColor(Color.red);
			        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
			        g2.setColor(Color.red);
			        //  Border set 2 Pix
			        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 3, 3);
			        super.paintComponent(g);
			     }

			};
			pnPhanTramGiam.setOpaque(false);
			FlowLayout fl_pnPhanTramGiam = (FlowLayout) pnPhanTramGiam.getLayout();
			fl_pnPhanTramGiam.setVgap(0);
			fl_pnPhanTramGiam.setHgap(0);
			pnPhanTramGiam.setBackground(Color.RED);
			pnPhanTramGiam.setBounds(617, 162, 45, 20);
			pnCard_SanPham.add(pnPhanTramGiam);
			
			JLabel lblPhanTramGiam = new JLabel("-"+sp.getGiamGia()+"%");
			lblPhanTramGiam.setForeground(Color.WHITE);
			lblPhanTramGiam.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhanTramGiam.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPhanTramGiam.setPreferredSize(new Dimension(45, 20));
			pnPhanTramGiam.add(lblPhanTramGiam);
			
		}
		JLabel lblSL = new JLabel("Số Lượng: ");
		lblSL.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSL.setBounds(340, 205, 60, 14);
		pnCard_SanPham.add(lblSL);
		
		JPanel pnTangGiamSoLuong = new JPanel();
		pnTangGiamSoLuong.setBackground(Color.WHITE);
		pnTangGiamSoLuong.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnTangGiamSoLuong.setBounds(410, 192, 120, 30);
		pnCard_SanPham.add(pnTangGiamSoLuong);
		pnTangGiamSoLuong.setLayout(null);
		
		lblGiam = new JLabel("");
		lblGiam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblGiam.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiam.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\minus.png"));
		lblGiam.setBounds(0, 0, 30, 30);
		pnTangGiamSoLuong.add(lblGiam);
		
		lblSoLuong = new JLabel("1");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setBounds(30, 0, 60, 30);
		pnTangGiamSoLuong.add(lblSoLuong);
		
		lblTang = new JLabel("");
		lblTang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTang.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\add.png"));
		lblTang.setBounds(90, 0, 30, 30);
		pnTangGiamSoLuong.add(lblTang);
		if (sp.getSoLuongTon()<=0) {
			pnTangGiamSoLuong.setVisible(false);
			JPanel pnHetHang = new JPanel();
			pnHetHang.setLayout(null);
			pnHetHang.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), null, null));
			pnHetHang.setBackground(Color.RED);
			pnHetHang.setBounds(410, 192, 120, 30);
			pnCard_SanPham.add(pnHetHang);
			
			JLabel lblHetHang = new JLabel("Hết Hàng");
			lblHetHang.setHorizontalAlignment(SwingConstants.CENTER);
			lblHetHang.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblHetHang.setForeground(Color.WHITE);
			lblHetHang.setBounds(0, 0, 120, 30);
			pnHetHang.add(lblHetHang);
		}
		
		JLabel lblMoTa = new JLabel("Mô tả: ");
		lblMoTa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMoTa.setBounds(340, 290, 53, 14);
		pnCard_SanPham.add(lblMoTa);
		
		JLabel lblTextMoTa = new JLabel("<html><p style=' text-indent: 37px'>"+sp.getMoTa()+"</p></html>");
		lblTextMoTa.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblTextMoTa.setVerticalAlignment(SwingConstants.TOP);
		lblTextMoTa.setBounds(340, 289, 500, 117);
		pnCard_SanPham.add(lblTextMoTa);
		
	
		
		
		
		if (tk.getLoaiTaiKhoan().equals("Khách Hàng")) {
			
			pnMuaHang = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        //  Paint Border
			        g2.setColor(Color.red);
			        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
			        g2.setColor(Color.red);
			        //  Border set 2 Pix
			        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 13, 13);
			        super.paintComponent(g);
			     }

			};
			pnMuaHang.setOpaque(false);
			pnMuaHang.setBackground(Color.RED);
			FlowLayout fl_pnMuaHang = (FlowLayout) pnMuaHang.getLayout();
			fl_pnMuaHang.setVgap(0);
			fl_pnMuaHang.setHgap(0);
			pnMuaHang.setBounds(554, 244, 174, 30);
			pnCard_SanPham.add(pnMuaHang);
			
			JLabel lblTextMuaNgay = new JLabel("Mua Ngay");
			lblTextMuaNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblTextMuaNgay.setForeground(Color.WHITE);
			lblTextMuaNgay.setHorizontalAlignment(SwingConstants.CENTER);
			lblTextMuaNgay.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTextMuaNgay.setPreferredSize(new Dimension(174, 30));
			pnMuaHang.add(lblTextMuaNgay);
			
			JLabel btnThemGiaHang = new JLabel("Thêm Vào Giỏ Hàng") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        //  Paint Border
			        g2.setColor(Color.red);
			        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
			        g2.setColor(Color.white);
			        //  Border set 2 Pix
			        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 13, 13);
			        super.paintComponent(g);
			     }

			};
			btnThemGiaHang.setOpaque(false);
			btnThemGiaHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnThemGiaHang.setIcon(new ImageIcon("img/IMG_LOGIN_SignIn/cart_red.png"));
			btnThemGiaHang.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnThemGiaHang.setHorizontalAlignment(SwingConstants.CENTER);
			btnThemGiaHang.setBounds(340, 244, 174, 30);
			pnCard_SanPham.add(btnThemGiaHang);
			
			btnThemGiaHang.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String s = Gui_KhachHang.capNhat(sp.getMaSP().trim());
					if (!s.equals("")) {
						new Dao_KhachHang().updateGioHang(s, tk.getMaKH().trim());
					}
				}
			});
			
			pnMuaHang.addMouseListener(this);
		}
		
		
		lblExit = new JLabel();
		lblExit.setBounds(818, 0, 32, 32);
		pnCard_SanPham.add(lblExit);
		lblExit.setVerticalAlignment(SwingConstants.TOP);
		lblExit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_black.png"));
		
		lblImgSP_1 = new JLabel("");
		lblImgSP_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImgSP_1.setBorder(new LineBorder(Color.RED));
		lblImgSP_1.setIcon(new ImageIcon(new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
		lblImgSP_1.setBounds(20, 336, 70, 70);
		pnCard_SanPham.add(lblImgSP_1);
		
		lblImgSP_2 = new JLabel("");
		lblImgSP_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImgSP_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImgSP_2.setIcon(new ImageIcon(new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
		lblImgSP_2.setBounds(96, 336, 70, 70);
		pnCard_SanPham.add(lblImgSP_2);
		
		lblImgSP_3 = new JLabel("");
		lblImgSP_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImgSP_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImgSP_3.setIcon(new ImageIcon(new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
		lblImgSP_3.setBounds(172, 336, 70, 70);
		pnCard_SanPham.add(lblImgSP_3);
		
		lblImgSP_4 = new JLabel("");
		lblImgSP_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImgSP_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImgSP_4.setIcon(new ImageIcon(new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
		lblImgSP_4.setBounds(248, 336, 70, 70);
		pnCard_SanPham.add(lblImgSP_4);
		
		lblExit.addMouseListener(this);
		lblTang.addMouseListener(this);
		lblGiam.addMouseListener(this);
		
		lblImgSP_1.addMouseListener(this);
		lblImgSP_2.addMouseListener(this);
		lblImgSP_3.addMouseListener(this);
		lblImgSP_4.addMouseListener(this);
	}
	
//	public static void main(String[] args) {
//		List<SanPham> ds = new Dao_SanPham().getDsSanPhamFull();
//		
//		new Card_Product(ds.get(new Random().nextInt(199 + 0))).setVisible(true);
//	}

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
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(lblExit)) {
			dispose();
		}else if (e.getSource().equals(lblGiam)) {
			int x = Integer.parseInt(lblSoLuong.getText());
			if (x>1) {
				x--;
				lblSoLuong.setText(x+"");
			}
		}else if (e.getSource().equals(lblTang)) {
			int x = Integer.parseInt(lblSoLuong.getText());
			if (x<SLTMax) {
				x++;
				lblSoLuong.setText(x+"");
			}
		}else if (e.getSource().equals(lblImgSP_1)) {
			lblImgSP_1.setBorder(new LineBorder(Color.RED));
			lblImgSP_2.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_3.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_4.setBorder(new LineBorder(Color.BLACK));
		}else if (e.getSource().equals(lblImgSP_2)) {
			lblImgSP_2.setBorder(new LineBorder(Color.RED));
			lblImgSP_1.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_3.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_4.setBorder(new LineBorder(Color.BLACK));

		}else if (e.getSource().equals(lblImgSP_3)) {
			lblImgSP_3.setBorder(new LineBorder(Color.RED));
			lblImgSP_2.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_1.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_4.setBorder(new LineBorder(Color.BLACK));

		}else if (e.getSource().equals(lblImgSP_4)) {
			lblImgSP_4.setBorder(new LineBorder(Color.RED));
			lblImgSP_2.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_3.setBorder(new LineBorder(Color.BLACK));
			lblImgSP_1.setBorder(new LineBorder(Color.BLACK));

		}else if (e.getSource().equals(pnMuaHang)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Muốn Thanh Toán.","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				JPanel pnDiaChi = new JPanel();
				pnDiaChi.setPreferredSize(new Dimension(310, 160));
				pnDiaChi.setBounds(40, 68, 310, 160);
				pnDiaChi.setLayout(null);
				
				JLabel lblTinhThanh = new JLabel("Tỉnh Thành: ");
				lblTinhThanh.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblTinhThanh.setBounds(10, 10, 90, 25);
				pnDiaChi.add(lblTinhThanh);
				
				JComboBox<String> cboTinhThanh = new JComboBox<String>();
				cboTinhThanh.setBackground(Color.WHITE);
				cboTinhThanh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboTinhThanh.setBounds(100, 10, 190, 25);
				pnDiaChi.add(cboTinhThanh);
				
				for (TinhThanh i: new Dao_DiaChi().getDsTinhThanh_1()) {
					cboTinhThanh.addItem(i.getTenTinhThanh());
				}
				JLabel lblQuanHuyen = new JLabel("Quận Huyện:");
				lblQuanHuyen.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblQuanHuyen.setBounds(10, 46, 90, 25);
				pnDiaChi.add(lblQuanHuyen);
				
				JComboBox<String> cboQuanHuyen = new JComboBox<String>();
				cboQuanHuyen.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboQuanHuyen.setBackground(Color.WHITE);
				cboQuanHuyen.setBounds(100, 46, 190, 25);
				pnDiaChi.add(cboQuanHuyen);
				
				for (QuanHuyen i: new Dao_DiaChi().getDsQuanHuyen_1(1)) {
					cboQuanHuyen.addItem(i.getTenQuanHuyen());
				}
				
				JLabel lblPhuongXa = new JLabel("Phường Xã: ");
				lblPhuongXa.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPhuongXa.setBounds(10, 82, 90, 25);
				pnDiaChi.add(lblPhuongXa);
				
				JComboBox<String> cboPhuongXa = new JComboBox<String>();
				cboPhuongXa.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cboPhuongXa.setBackground(Color.WHITE);
				cboPhuongXa.setBounds(100, 82, 190, 25);
				pnDiaChi.add(cboPhuongXa);
				
				for (XaPhuong i: new Dao_DiaChi().getDsXaPhuong_1(1)) {
					cboPhuongXa.addItem(i.getTenXaPhuong());
				}
				
				JLabel lblSoNha = new JLabel("Số Nhà: ");
				lblSoNha.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSoNha.setBounds(10, 118, 90, 25);
				pnDiaChi.add(lblSoNha);
				
				JTextField txtSoNha = new JTextField();
				txtSoNha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				txtSoNha.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtSoNha.setBounds(100, 118, 190, 25);
				pnDiaChi.add(txtSoNha);
				txtSoNha.setColumns(10);
				
				cboTinhThanh.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange()==ItemEvent.SELECTED) {
							cboQuanHuyen.removeAllItems();
							for (QuanHuyen i: new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1)) {
								cboQuanHuyen.addItem(i.getTenQuanHuyen());
							}
							cboPhuongXa.removeAllItems();
							int index = Integer.parseInt(new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1).get(0).getIdQuanHuyen());
							for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
								cboPhuongXa.addItem(i.getTenXaPhuong());
							}
						}
					}
				});
				 indexQuanHuyen = 1;
				cboQuanHuyen.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange()==ItemEvent.SELECTED) {
							cboPhuongXa.removeAllItems();
							int index = Integer.parseInt(new Dao_DiaChi().getDsQuanHuyen_1(cboTinhThanh.getSelectedIndex()+1).get(cboQuanHuyen.getSelectedIndex()).getIdQuanHuyen());
							indexQuanHuyen = index;
							for (XaPhuong i : new Dao_DiaChi().getDsXaPhuong_1(index)) {
								cboPhuongXa.addItem(i.getTenXaPhuong());
							}
						}
					}
				});
				Object[]r = {"OK","Bỏ qua"};
				HoaDon hoaDon = null;
				KhachHang khachHang = new Dao_KhachHang().timKiemKhachHanh(taiKhoan.getMaKH().trim());
				if (JOptionPane.showOptionDialog(null, pnDiaChi, "Nhập Địa Chỉ Giao Hàng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION,null,r, null)==JOptionPane.OK_OPTION) {
					String diaChi = txtSoNha.getText()+", "+cboPhuongXa.getSelectedItem()+", "+cboQuanHuyen.getSelectedItem()+", "+cboTinhThanh.getSelectedItem();
					 hoaDon = new HoaDon(taoMaHD(), "Chờ Xác Nhận", null, null, null, diaChi, null, khachHang.getMaKH().trim());
				}else {
					 hoaDon = new HoaDon(taoMaHD(), "Chờ Xác Nhận", null, null, null, khachHang.getDiaChi(), null, khachHang.getMaKH().trim());
				}
				if (hoaDon!=null) {
					new Dao_HoaDon().insertHoaDonKhachHang(hoaDon);
					new Dao_ChiTietHoaDon().insertChiTietHoaDon(new ChiTietHoaDon(sanPham.getMaSP().trim(), taoMaHD(), Integer.parseInt(lblSoLuong.getText()), sanPham.getGiaBan()));
				}
				JOptionPane.showMessageDialog(null, "Đơn Hàng Của Bạn Đang Chờ Xác Nhận.");
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
		if (e.getSource().equals(lblExit)) {
			lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_red.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(lblExit)) {
			lblExit.setIcon(new ImageIcon("img\\IMG_LOGIN_SignIn\\Close_black.png"));
		}
		
	}
}
