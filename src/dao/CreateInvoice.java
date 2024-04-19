package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;

public class CreateInvoice {

	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public void createOrder(List<SanPham> dsSP, List<ChiTietHoaDon> cTHD, HoaDon hd , NhanVien nv) throws DocumentException, IOException {
	
		Document document = new Document(PageSize.A5, 50, 50, 0, 50);

		BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, true);

		Font ft = new Font(baseFont);
		ft.setSize(10.f);
		ft.setColor(0, 0, 0);
		PdfWriter.getInstance(document,
				new FileOutputStream("img\\sample1.pdf"));
		document.open();
		Image img = Image.getInstance("img/IMG_LOGIN_SignIn/logo.png");
		img.scaleToFit(170, 170);
		img.setAlignment(Element.ALIGN_CENTER);
		document.add(img);
		
		
		Paragraph diaChi = new Paragraph(new String("1/6 Nguyễn Văn Nghi, Phường 4, Quận Gò Vấp,TP HCM"), ft);
		diaChi.setSpacingBefore(-55);
		diaChi.setAlignment(Element.ALIGN_CENTER);
		document.add(diaChi);
		
		Paragraph hr = new Paragraph("------------------------------------------------------------------");
		hr.setAlignment(Element.ALIGN_CENTER);
		document.add(hr);
		Font ftt = new Font(baseFont);
		ftt.setSize(15f);
		ftt.setStyle(Font.BOLD);
		Paragraph title = new Paragraph("HOÁ ĐƠN",ftt);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		
		Paragraph MaHD = new Paragraph("",ft);
		MaHD.setSpacingBefore(10);
		MaHD.setIndentationLeft(40);
		MaHD.add("Mã Hoá Đơn: ");
		MaHD.add(hd.getMaHD());
		document.add(MaHD);
		
		Paragraph ngayLap = new Paragraph("",ft);
		ngayLap.setIndentationLeft(40);
		ngayLap.add("Ngày Lập: ");
		ngayLap.add(new java.sql.Date(System.currentTimeMillis())+"");
		document.add(ngayLap);
		
		Paragraph tenNV = new Paragraph("",ft);
		tenNV.setIndentationLeft(40);
		tenNV.add("Nhân Viên: ");
		tenNV.add(nv.getTenNV());
		document.add(tenNV);
		
		document.add(hr);
		
		Font f = new Font(baseFont);
		f.setStyle(Font.BOLD);
		Paragraph titleSP =new Paragraph("",f);
		Chunk Distance = new Chunk(new VerticalPositionMark());
		titleSP.setIndentationLeft(40);
		titleSP.add("Số Lượng");
		titleSP.add(Distance);
		titleSP.add("Đơn Giá");
		titleSP.add(Distance);
		titleSP.add("Thành Tiền");
		titleSP.add(Distance);
		document.add(titleSP);
		double subTotal = 0;

		for (int i = 0; i < cTHD.size(); i++) {
			Paragraph tenSP = new Paragraph(dsSP.get(i).getTenSP(),ft);
			tenSP.setIndentationLeft(35);
			document.add(tenSP);
			
			Paragraph ttSP =new Paragraph("",ft);
			ttSP.setIndentationLeft(50);
			ttSP.add(cTHD.get(i).getSoLuong()+"");
			ttSP.add(Distance);
			ttSP.add(formatter.format(dsSP.get(i).getGiaBan())+" VNĐ");
			ttSP.add(Distance);
			ttSP.add(formatter.format(cTHD.get(i).getGiaBan()*cTHD.get(i).getSoLuong())+" VNĐ");
			ttSP.add(Distance);
			document.add(ttSP);
			subTotal+=cTHD.get(i).getGiaBan()*cTHD.get(i).getSoLuong();

		}
		document.add(hr);
		
		
		Font ft1 = new Font(baseFont);
		ft1.setStyle(Font.BOLD);
		ft1.setSize(13);
		Paragraph soLuong = new Paragraph("",ft1);
		soLuong.setIndentationLeft(100);
		soLuong.add("Tổng Số Sản Phẩm: ");
		soLuong.add(Distance);
		soLuong.add(dsSP.size()+"");
		soLuong.add(Distance);
		document.add(soLuong);
		
		Paragraph tongTien = new Paragraph("",ft1);
		tongTien.setIndentationLeft(100);
		tongTien.add("Tổng Tiền: ");
		tongTien.add(Distance);
		tongTien.add(formatter.format(Math.round(subTotal)*1000)+" VNĐ");
		tongTien.add(Distance);
		document.add(tongTien);
			
		document.close();
	}
	
	
}
