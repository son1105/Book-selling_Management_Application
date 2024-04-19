package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import dao.DAO_ChiTietHoaDonDoiTra;
import dao.DAO_HoaDonDoiTra;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import dao.Dao_SanPham;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

public class Gui_KhachHang_LSMuaHang extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private KhachHang khachHang;
	private List<HoaDon> dsHD;
	private JPanel pnDS_ItemSanPham;
	private JButton btnCXN;
	private JButton btnLH;
	private JButton btnGH;
	private JButton btnDGH;
	private JButton btnTatCa;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public Gui_KhachHang_LSMuaHang(KhachHang kh) {
		khachHang = kh;
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Lịch Sử Mua Hàng");
		setSize(1000, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(conTrol_LSMuaHang());
		
	}

	public Component conTrol_LSMuaHang() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 711);
		panel.setPreferredSize(new Dimension(984, 711));
		panel.setLayout(null);
		
		JPanel pnlTacVu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlTacVu.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(30);
		pnlTacVu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlTacVu.setBounds(10, 0, 964, 50);
		panel.add(pnlTacVu);

		btnTatCa = new JButton("Tất Cả");
		btnTatCa.setPreferredSize(new Dimension(150, 30));
		btnTatCa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTatCa.setFocusable(false);
		btnTatCa.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnTatCa.setBackground(new Color(245, 245, 220));
		pnlTacVu.add(btnTatCa);

		btnCXN = new JButton("Chờ Xác Nhận");
		btnCXN.setFocusable(false);
		btnCXN.setBackground(new Color(245, 245, 220));
		btnCXN.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCXN.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCXN.setPreferredSize(new Dimension(150, 30));
		pnlTacVu.add(btnCXN);

		btnLH = new JButton("Đang Lấy Hàng");
		btnLH.setFocusable(false);
		btnLH.setBackground(new Color(245, 245, 220));
		btnLH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLH.setPreferredSize(new Dimension(150, 30));
		pnlTacVu.add(btnLH);

		btnGH = new JButton("Đang Giao Hàng");
		btnGH.setFocusable(false);
		btnGH.setBackground(new Color(245, 245, 220));
		btnGH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGH.setPreferredSize(new Dimension(150, 30));
		pnlTacVu.add(btnGH);

		btnDGH = new JButton("Đã Được Giao");
		btnDGH.setFocusable(false);
		btnDGH.setBackground(new Color(245, 245, 220));
		btnDGH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDGH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDGH.setPreferredSize(new Dimension(150, 30));
		pnlTacVu.add(btnDGH);

		JScrollPane scrDS_ItemSanPham = new JScrollPane();
		scrDS_ItemSanPham.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDS_ItemSanPham.setBounds(10, 61, 964, 639);
		panel.add(scrDS_ItemSanPham);

		pnDS_ItemSanPham = new JPanel();
		scrDS_ItemSanPham.setViewportView(pnDS_ItemSanPham);
		pnDS_ItemSanPham.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
		

		dsHD = new Dao_HoaDon().getDsHoaDonCuaKhachHang(khachHang.getMaKH().trim());
		Collections.sort(dsHD, new Comparator<HoaDon>() {
			@Override
			public int compare(HoaDon o1, HoaDon o2) {
				if (o1.getNgayLap() == null || o2.getNgayLap() == null) {
					return 0;
				}
				if (o1.getNgayLap().before(o2.getNgayLap())) {
					return 1;
				}
				return -1;
			}
		});
		loadTatCa(10, dsHD);
		
		btnCXN.addActionListener(this);
		btnLH.addActionListener(this);
		btnTatCa.addActionListener(this);
		btnGH.addActionListener(this);
		btnDGH.addActionListener(this);
		
		return panel;
	}
	
	public void loadTatCa(int n , List<HoaDon> HD) {
		
		pnDS_ItemSanPham.removeAll();;
		pnDS_ItemSanPham.repaint();
		pnDS_ItemSanPham.revalidate();
		
		
		List<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		for (HoaDon hoaDon : HD) {
			dsCTHD.addAll(new Dao_ChiTietHoaDon().getDsChiTietHoaDon(hoaDon.getMaHD().trim()));
		}
		
		if (n< dsCTHD.size()) {
			for (int i = 0; i < n; i++) {
				pnDS_ItemSanPham.add(ItemSanPham(dsCTHD.get(i),dsCTHD));
			}
			pnDS_ItemSanPham.add(xemThem(dsCTHD));
		}else {
			for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
				pnDS_ItemSanPham.add(ItemSanPham(chiTietHoaDon,dsCTHD));
			}
		}

	}
	
	public Component xemThem(List<ChiTietHoaDon> ds) {
		JPanel pnXemThem = new JPanel();
		pnXemThem.setBackground(Color.RED);
		pnXemThem.setBounds(10, 11, 924, 30);
		pnXemThem.setPreferredSize(new Dimension(924, 30));
		
		JLabel lblXemThem = new JLabel("Xem Thêm");
		lblXemThem.setForeground(Color.WHITE);
		lblXemThem.setHorizontalAlignment(SwingConstants.CENTER);
		lblXemThem.setPreferredSize(new Dimension(120, 20));
		lblXemThem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		pnXemThem.add(lblXemThem);
		
		pnXemThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for (int i = 10; i < ds.size(); i++) {
					pnDS_ItemSanPham.add(ItemSanPham(ds.get(i),ds));
					pnDS_ItemSanPham.repaint();
					pnDS_ItemSanPham.revalidate();
				}
				pnDS_ItemSanPham.remove(10);
				pnDS_ItemSanPham.repaint();
				pnDS_ItemSanPham.revalidate();
			}
		});
		return pnXemThem;
	}
	public Component ItemSanPham(ChiTietHoaDon cthd , List<ChiTietHoaDon> ds) {

		HoaDon hd = new Dao_HoaDon().timKiemHoaDon(cthd.getMaDH().trim());
		SanPham sp = new Dao_SanPham().getSanPhamMa(cthd.getMaSP().trim());
		JPanel pnItemSanPham = new JPanel();
		pnItemSanPham.setBackground(Color.WHITE);
		pnItemSanPham.setBounds(10, 11, 924, 197);
		pnItemSanPham.setPreferredSize(new Dimension(924, 197));
		pnItemSanPham.setLayout(null);

		JLabel lblImg_sp = new JLabel("");
		lblImg_sp.setBorder(new BevelBorder(BevelBorder.RAISED, null, Color.LIGHT_GRAY, null, Color.GRAY));
		lblImg_sp.setIcon(new ImageIcon(
				new ImageIcon(sp.getHinhAnh()).getImage().getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
		lblImg_sp.setBounds(10, 36, 150, 150);
		pnItemSanPham.add(lblImg_sp);

		JLabel lblTenSanPham = new JLabel("<html><p>" + sp.getTenSP() + "</p></html>");
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN | Font.BOLD, 17));
		lblTenSanPham.setVerticalAlignment(SwingConstants.TOP);
		lblTenSanPham.setBounds(170, 36, 740, 38);
		pnItemSanPham.add(lblTenSanPham);

		JLabel lblSoLuong = new JLabel("Số Lượng: ");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuong.setBounds(170, 85, 68, 15);
		pnItemSanPham.add(lblSoLuong);

		JLabel lblSoLuongMua = new JLabel("X" + cthd.getSoLuong());
		lblSoLuongMua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuongMua.setBounds(241, 85, 79, 14);
		pnItemSanPham.add(lblSoLuongMua);

		JLabel lblTitle_TongTien = new JLabel("Tổng Tiền: ");
		lblTitle_TongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle_TongTien.setBounds(170, 111, 68, 15);
		pnItemSanPham.add(lblTitle_TongTien);

		JLabel lblTongTien = new JLabel(formatter.format(cthd.getGiaBan()*cthd.getSoLuong()) + " VNĐ");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongTien.setBounds(241, 107, 251, 20);
		pnItemSanPham.add(lblTongTien);

		JPanel pnTitle_ItemSP = new JPanel();
		pnTitle_ItemSP.setBounds(2, 2, 920, 30);
		pnItemSanPham.add(pnTitle_ItemSP);
		pnTitle_ItemSP.setLayout(null);

		JLabel lblMaHd = new JLabel("#" + hd.getMaHD().trim());
		lblMaHd.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMaHd.setBounds(10, 5, 218, 20);
		pnTitle_ItemSP.add(lblMaHd);

		JLabel lblTrangThai = new JLabel();
		lblTrangThai.setBorder(new MatteBorder(0, 1, 0, 0, (Color) Color.LIGHT_GRAY));
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTrangThai.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangThai.setBounds(795, 5, 115, 20);
		pnTitle_ItemSP.add(lblTrangThai);

		JLabel lblDate = new JLabel();
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(700, 5, 95, 20);
		pnTitle_ItemSP.add(lblDate);

		if (hd.getNgayLap() == null) {
			lblTrangThai.setText("Đang xác nhận");
			JButton btnHuyDon = new JButton("Huỷ Đơn");
			btnHuyDon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = ds.indexOf(cthd);
					if (JOptionPane.showConfirmDialog(null, "Bạn muốn huỷ đơn sản phẩm này?","HUỶ ĐẶT SẢN PHẨM",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						if(new Dao_ChiTietHoaDon().deleteChiTietHoaDon(cthd)) {
							pnDS_ItemSanPham.remove(index);
							pnDS_ItemSanPham.repaint();
							pnDS_ItemSanPham.revalidate();
						}
					}
				}
			});
			btnHuyDon.setFocusable(false);
			btnHuyDon.setBackground(Color.RED);
			btnHuyDon.setForeground(Color.WHITE);
			btnHuyDon.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnHuyDon.setBounds(794, 156, 120, 30);
			pnItemSanPham.add(btnHuyDon);
		} else if (hd.getNgayLayHang() == null) {
			lblTrangThai.setText("Đang lấy hàng");
			lblDate.setText(hd.getNgayLap() + "");
		} else if (hd.getNgayGiaoHang() == null) {
			lblTrangThai.setText("Đang đang giao");
			lblDate.setText(hd.getNgayLayHang() + "");
		} else if (hd.getNgayGiaoHang() != null) {
			lblTrangThai.setText("Đã giao");
			lblDate.setText(hd.getNgayGiaoHang() + "");

			JButton btnDoiTra = new JButton("Đổi Trả");
			if (new Dao_SanPham().timKiemSanPham(sp.getMaSP())==null) {
				btnDoiTra.setText("Ngừng Bán");
			}
			if (new DAO_ChiTietHoaDonDoiTra().timCthdDoiTra(hd.getMaHD(), cthd.getMaSP())!=null) {
				btnDoiTra.setText("Chờ Đổi Trả");
			}
			btnDoiTra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnDoiTra.getText().trim().equals("Đổi Trả")) {
						Date date = new java.sql.Date(System.currentTimeMillis());
						int n = (int) ((date.getTime() - hd.getNgayGiaoHang().getTime())/ (24 * 60 * 60 * 1000));
						if (new Dao_SanPham().timKiemSanPham(sp.getMaSP())!=null) {
							if (new Dao_SanPham().timKiemSanPham(sp.getMaSP()) instanceof Sach) {
								if ( n > 9) {
									JOptionPane.showMessageDialog(null, "Đã Quá Hạng Đổi Trả(> 9 ngày)");
								}else {
									if(n <= 3) {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 3 Ngày Nên Sản Phẩm Được Giảm 30%","30%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											System.out.println(new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD()));
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 30, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 30, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}else if (n <= 6) {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 6 Ngày Nên Sản Phẩm Được Giảm 50%","50%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 50, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 50, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}else {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 9 Ngày Nên Sản Phẩm Được Giảm 80%","80%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 80, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 80, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}
								}
							}else if (new Dao_SanPham().timKiemSanPham(sp.getMaSP()) instanceof VanPhongPham) {
								if ( n > 15) {
									JOptionPane.showMessageDialog(null, "Đã Quá Hạng Đổi Trả(> 15 ngày)");
								}else {
									if(n <= 5) {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 5 Ngày Nên Sản Phẩm Được Giảm 30%","30%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											System.out.println(new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD()));
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 30, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 30, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}else if (n <= 10) {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 6 Ngày Nên Sản Phẩm Được Giảm 50%","50%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 70, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 70, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}else {
										if (JOptionPane.showConfirmDialog(null, "Vì Đơn Hàng Đã Nhận Được <= 15 Ngày Nên Sản Phẩm Được Giảm 80%","80%",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
											if (new DAO_HoaDonDoiTra().timHoaDonDoiTra(hd.getMaHD())==null) {
												HoaDonDoiTra doiTra = new HoaDonDoiTra(hd.getMaHD(), hd.getMaKH(),hd.getMaNV(),hd.getDiaChi(),hd.getNgayGiaoHang());
												if (new DAO_HoaDonDoiTra().insertHoaDonDoiTra(doiTra)) {
													if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 80, cthd.getGiaBan()))) {
														JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
														btnDoiTra.setText("Chờ Đổi Trả");
													}
												}
											}else {
												if (new DAO_ChiTietHoaDonDoiTra().insertChiTietHoaDonDoiTra(new ChiTietHoaDonDoiTra(hd.getMaHD(), sp.getMaSP(), cthd.getSoLuong(), 80, cthd.getGiaBan()))) {
													JOptionPane.showMessageDialog(null, "Chờ Xác Nhận Đổi Trả");
													btnDoiTra.setText("Chờ Đổi Trả");
												}
											}
										}
									}
								}
							}
						}
					}
					
				}
			});
			btnDoiTra.setForeground(Color.WHITE);
			btnDoiTra.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnDoiTra.setFocusable(false);
			btnDoiTra.setBackground(Color.RED);
			btnDoiTra.setBounds(794, 156, 120, 30);
			pnItemSanPham.add(btnDoiTra);
		}
		return pnItemSanPham;
	}

	public static void main(String[] args) {
		new Gui_KhachHang_LSMuaHang(new KhachHang("KH00000001", "0346676956", "Phạm Thanh Sơn",
				"1/6 Nguyễn Văn Nghi, Phường 04, Quận Gò Vấp, Thành phố Hồ Chí Minh",
				"SP220001 SP220031 SP220011 SP220006", null)).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCXN)) {
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (HoaDon i: dsHD) {
				if (i.getNgayLap()==null) {
					dsHoaDon.add(i);
				}
			}
			loadTatCa(10, dsHoaDon);
		}else if (e.getSource().equals(btnLH)) {
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (HoaDon i: dsHD) {
				if (i.getTrangThai().trim().equals("Chờ Lấy Hàng")) {
					dsHoaDon.add(i);
				}
			}
			loadTatCa(10, dsHoaDon);
		}else if (e.getSource().equals(btnGH)) {
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (HoaDon i: dsHD) {
				if (i.getTrangThai().trim().equals("Chờ Giao")) {
					dsHoaDon.add(i);
				}
			}
			loadTatCa(10, dsHoaDon);
		}else if (e.getSource().equals(btnDGH)) {
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (HoaDon i: dsHD) {
				if (i.getTrangThai().trim().equals("Đã Giao")) {
					dsHoaDon.add(i);
				}
			}
			loadTatCa(10, dsHoaDon);
		}else if (e.getSource().equals(btnTatCa)) {
			loadTatCa(10, dsHD);
		}
	}
}
