package com.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

 
import com.util.DividePage;
import com.util.UUIDTools;

public class ProductAction extends HttpServlet {

	private ProductService service;
	/**
	 * Constructor of the object.
	 */
	public ProductAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addProduct(request,response);
		}else if (action_flag.equals("search")) {
			listProduct(request,response);
		}else if (action_flag.equals("del")) {
			delProduct(request,response);
		}else if (action_flag.equals("view")) {
			viewProduct(request,response);
		}
		
		
		out.flush();
		out.close();
	}

	private void viewProduct(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String proid = request.getParameter("proid");
		Map<String, Object> map = service.viewProduct(proid);
		request.setAttribute("productMap", map);
		try {
			request.getRequestDispatcher("/viewProduct.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	/**????????????
	 * @param request
	 * @param response
	 */
	private void delProduct(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("????del");
		//??????????????
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			System.out.println("ids["+i+"]="+ids[i]);
		}
		boolean flag = service.delProduct(ids);
		System.out.println("????flag:"+flag);
		if (flag) {
			try {
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	private void listProduct(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String productName = request.getParameter("proname");	
		String pageNum = request.getParameter("pageNum");
		System.out.println("???? pageNum :"+pageNum);
		if (productName == null) {
			productName = "";
		}
		
		
		
		int totalRecord = service.getItemCount(productName); //??????????????
		int currentPage = 1;
		DividePage dividePage = new DividePage(5, totalRecord);//??????????????
		if (pageNum != null) {
			
			
			currentPage = Integer.parseInt(pageNum);
			
			dividePage.setCurrentPage(currentPage);
		}
		
		//????????????????
		int start = dividePage.fromIndex();
		//????????????
		int end = dividePage.toIndex();		
		
		System.out.println("currentPageNum :"+ dividePage.getCurrentPage() +", start = "+start +", end = "+end);
		
		List<Map<String, Object>> list = null;
		try {
			list = service.listProduct(productName , start , end);
			request.setAttribute("listProduct", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("productName",productName );
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//??????????????????
		String  path = request.getContextPath();		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3*1024*1024);//????????????????3M
		servletFileUpload.setSizeMax(6*1024*1024);//??????????????
		List<FileItem> list = null;		
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID()); // ?????? product????????
		try {
			//????request??????
			list = servletFileUpload.parseRequest(request);				
			//??????????????????????????????????????????
			for(FileItem fileItem : list){
				if (fileItem.isFormField()) {//??????????
					String fileItemName = fileItem.getFieldName(); //???? <input>?????? ????
					String fileItemValue = fileItem.getString("utf-8");//????<input>????????
					if (fileItemName.equals("proname")) {
						params.add(fileItemValue); //???????? proname
					}else if (fileItemName.equals("proprice")) {
						params.add(fileItemValue);//???????? proprice
					}else if (fileItemName.equals("proaddress")) {
						params.add(fileItemValue);////???????? proaddress
					}					
				}else{ //??????????					
					
					String imageName = fileItem.getName(); //????????????
					params.add(imageName);//????????  proimage			
					//String path = request.getRealPath("/upload");
					
					
					//??????
					//String upload_dir = request.getServletContext().getRealPath("/upload");//???????????? /upload ????
					//File uploadFile = new File(upload_dir+"/"+imageName);
					//System.out.println("---upload_dir--->>"+uploadFile);
					//fileItem.write(uploadFile);		
					//??????????
					File uploadFile = new File("/Users/xinxingwu/"+imageName);
					System.out.println("---upload_dir--->>"+uploadFile);
					fileItem.write(uploadFile);		
				}				
			}
			
			// ????????????????
			boolean flag = service.addProduct(params);
			if (flag) {
				
				response.sendRedirect(path+"/main.jsp");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new ProductDao();
	}

}