package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.LoaiSanPhan;
import entity.NhaCungCap;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

public class ImportFileProduct {

	/**
	 * lấy danh sách các nhà cung cấp từ excel
	 * 
	 * @return list<NhaCungCap>
	 * @throws IOException
	 */
	public List<NhaCungCap> getDanhNhaCungExcel(String path) {
		List<NhaCungCap> ds = new ArrayList<>();
		try {
			FileInputStream ios = new FileInputStream(path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet("Nhà cung cấp");

			String ma = new DAO_NhaCungCap().getNhaCungMoi().getMaNCC();
			int xacNhan = 0;
			for (Row row : sheet) {
				if (xacNhan == 0) {
					xacNhan = 1;
				} else {
					int dem = 0;
					String x[] = new String[3];
					for (Cell cell : row) {
						x[dem] = cell + "";
						dem++;
					}
					ds.add(new NhaCungCap(taoMaNCC(ma), x[0], x[1], x[2]));
					ma = taoMaNCC(ma);
				}
			}
			ios.close();
		} catch (Exception e) {
		}

		return ds;
	}

	/**
	 * lấy danh sách loại sản phẩm
	 * 
	 * @return list<LoaiSanPham>
	 * @throws IOException
	 */
	public List<LoaiSanPhan> getDsLoaiExcel(String path) {

		List<LoaiSanPhan> ds = new ArrayList<>();
		try {
			FileInputStream ios = new FileInputStream(path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet("Loại");

			String ma = new DAO_LoaiSanPham().getloaiMoi().getMaLoai().trim();
			int xacNhan = 0;
			if (sheet != null) {
				for (Row row : sheet) {
					if (xacNhan == 0) {
						xacNhan = 1;
					} else {
						String x = "";
						for (Cell cell : row) {
							x = cell + "";
						}
						ds.add(new LoaiSanPhan(taoMaLoai(ma), x));
						ma = taoMaLoai(ma);
					}
				}
				ios.close();
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return ds;
	}

	/**
	 * tạo mã nhà cung cấp
	 * 
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
	 * Tạo mã Loại sản phẩm
	 * 
	 * @param ma
	 * @return String
	 */
	public String taoMaLoai(String ma) {
		int soMa = Integer.parseInt(ma.substring(1));
		soMa++;
		if (soMa < 10) {
			return "L000" + soMa;
		} else if (soMa < 100) {
			return "L00" + soMa;
		} else if (soMa < 1000) {
			return "L0" + soMa;
		}
		return "L" + soMa;
	}

	/**
	 * Tạo Mã Sản phẩm
	 * 
	 * @return String
	 */
	public static String taoMaSP(String spLast) {
		String year = new String(LocalDateTime.now().getYear() + "")
				.substring(new String(LocalDateTime.now().getYear() + "").length() - 2);
		String maSP = "";
		if (!spLast.equals("")) {
			int ma = 0;
			if (spLast.substring(2, 4).equals(year)) {
				ma = Integer.parseInt(spLast.substring(4, 8) + "") + 1;
			}
			if (ma < 10) {
				maSP = "SP" + year + "000" + ma;
			} else if (ma < 100) {
				maSP = "SP" + year + "00" + ma;
			} else if (ma < 1000) {
				maSP = "SP" + year + "0" + ma;
			} else if (ma < 10000) {
				maSP = "SP" + year + ma;
			}
		}else {
			maSP = "SP" + year + "0001";
		}

		return maSP;
	}

	

	public List<SanPham> getDsSanPhamExcel(String path) throws IOException {
		List<SanPham> ds = new ArrayList<>();
		try {
			FileInputStream ios = new FileInputStream(path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(4);
			int xacNhan = 0;
			String ma="";
			if (new Dao_SanPham().getSanPhamCuoi()!=null) {
				 ma = new Dao_SanPham().getSanPhamCuoi();
			}
			
			List<NhaCungCap> dsNcc = new ImportFileProduct().getDanhNhaCungExcel(path);
			List<LoaiSanPhan> dsLoai = new ImportFileProduct().getDsLoaiExcel(path);
			dsNcc.addAll(new DAO_NhaCungCap().getDsNhaCung());
			dsLoai.addAll(new DAO_LoaiSanPham().getDsloaiSp());

			for (Row row : sheet) {
				if (xacNhan == 0) {
					xacNhan = 1;
				} else {
					int dem = 0;
					SanPham sanPham = null;
					String x[] = new String[18];
					for (Cell cell : row) {
						x[dem] = cell + "";
						dem++;
					}
					if (x[17].equals("Sách")) {
						sanPham = new Sach();
						((Sach) sanPham).setSoTrang((int) Integer.parseInt(x[13].substring(0, x[13].lastIndexOf("."))));
						((Sach) sanPham).setTacGia(x[14].toString());
						((Sach) sanPham).setNhaXuatBan(x[15].toString());
						((Sach) sanPham).setNamXB(Integer.parseInt(x[16].substring(0, x[16].lastIndexOf("."))));
					} else if (x[17].equals("Văn Phòng Phẩm")) {
						sanPham = new VanPhongPham();
						((VanPhongPham) sanPham).setMau(x[10]);
						((VanPhongPham) sanPham).setChatLieu(x[11]);
						((VanPhongPham) sanPham).setThuongHieu(x[12]);
					}
					sanPham.setTenSP(x[0]);
					for (LoaiSanPhan i : dsLoai) {
						if (x[1].equals(i.getTenLoai())) {
							sanPham.setMaLoai(i.getMaLoai());
							break;
						}
					}
					for (NhaCungCap i : dsNcc) {
						if (x[2].equals(i.getTenNCC())) {
							sanPham.setMaNcc(i.getMaNCC());
							break;
						}
					}
					sanPham.setSoLuongTon(Integer.parseInt(x[4].substring(0, x[4].lastIndexOf("."))));
					sanPham.setGiaBan(Float.parseFloat(x[5]));
					sanPham.setGiaNhap(Float.parseFloat(x[3]));
					sanPham.setGiamGia(Integer.parseInt(x[6].substring(0, x[6].lastIndexOf("."))));
					sanPham.setMoTa(x[7]);
					sanPham.setHinhAnh(x[8]);
					sanPham.setKhoiLuong(Float.parseFloat(x[9]));
					sanPham.setNgayNhap(new java.sql.Date(System.currentTimeMillis()));
					sanPham.setMaSP(taoMaSP(ma));
					ma = taoMaSP(ma);
					ds.add(sanPham);
				}
			}
			ios.close();
		} catch (Exception e) {
		}

		return ds;
	}

}
