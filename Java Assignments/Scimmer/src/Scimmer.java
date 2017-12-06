
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scimmer {

	public static void main(String[] args) throws Exception 
	{


		String filename = "C:/BlogCategories.xls" ;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstSheet"); 

		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("Blog Title");
		rowhead.createCell(1).setCellValue("Blog Address");
		rowhead.createCell(2).setCellValue("Categories");

		int rowIndex = 1;

		String url = "https://www.gamesparks.com/blog";
		for(int page = 1; page < 14; page ++)
		{
			Document document = Jsoup.connect(url).get();


			List<ArrayList<String>> data = new ArrayList<>();
			ArrayList<String> a1 = new ArrayList<String>();
			ArrayList<String> a2 = new ArrayList<String>();
			ArrayList<String> a3 = new ArrayList<String>();

			data.add(a1); // blog title
			data.add(a2); // blog address
			data.add(a3); // categories



			Elements blogs = document.getElementsByTag("article");
			for (Element blog : blogs) 
			{

				HSSFRow row = sheet.createRow((short)rowIndex);
				String blogTitle = blog.select(".blog_post_excerpt__title").text();
				a1.add(blogTitle);
				Elements link = blog.select(" .blog_post_excerpt__title [href]");       	
				a2.add(link.attr("href"));
				String categories = blog.select(".blog_post_categories.right-text").text();
				System.out.println("Blog: " + blogTitle );
				System.out.println("Link: " + link.attr("href") );
				System.out.println("Categories: " + categories );
				row.createCell(0).setCellValue(blogTitle);
				row.createCell(1).setCellValue(link.attr("href"));
				row.createCell(2).setCellValue(categories);
				rowIndex++;

				url = "https://www.gamesparks.com/blog/page/" + page + "/";


			}
		}

		FileOutputStream fileOut = new FileOutputStream(filename);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		System.out.println("Your excel file has been generated!");
	}

}
