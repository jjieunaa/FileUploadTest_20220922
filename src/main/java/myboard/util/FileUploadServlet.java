package myboard.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	// multipart인지 check
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html><html><body>");
		String contentType = req.getContentType();
		
		if (contentType!=null && contentType.toLowerCase().startsWith("multipart/")) {
			printPartInfo(req, writer);
		} else {
			writer.println("요청을 처리할 수 없습니다. contentType이 잘못되었습니다.");
		}
		writer.println("</body></html>");
		
	} // doPost

	private void printPartInfo(HttpServletRequest req, PrintWriter writer) throws IOException, ServletException {
		
		Collection<Part> parts = req.getParts();
		
		for (Part part : parts) {
			writer.println("<br /> name = " + part.getName());
			String contentType = part.getContentType();
			writer.println("<br /> contentType = " + contentType);
			
			if (part.getHeader("Content-Disposition").contains("filename=")) {
				writer.println("<br /> size = " + part.getSize());
				String fileName = part.getSubmittedFileName();	// 업로드한 파일명, servlet 3.1 이상
				writer.println("<br /> filename = " + fileName);
				
				if (part.getSize() > 0) {
					part.write("c:/filetemp/" + fileName);	// 여기에서 업로드가 일어남
					part.delete();
				}
			} else {
				String value = req.getParameter(part.getName());
				writer.println("<br /> value = " + value);
			}
			writer.println("<hr />");
		}
		
	} // printPartInfo
	
} // class
