package org.jbit.news.servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jbit.news.entity.Newst;
import org.jbit.news.entity.Topic;
import org.jbit.news.service.NewstServicDao;
import org.jbit.news.service.TopicsService;
import org.jbit.news.service.impl.NewstServicDaoimpl;
import org.jbit.news.service.impl.TopicsServiceImpl;

public class NewstServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String opr=request.getParameter("opr");

		//TopicsDao topicsDao= new TopicsDaoimpl();
		//NewsDao newstDao= new NewstDaoimpl();
		TopicsService topicsDao= new TopicsServiceImpl();
		NewstServicDao newstDao= new NewstServicDaoimpl();
		if("listTitle".equals(opr)){
			List<Newst> list1;
			try {
				list1 = newstDao.getLatestNewByTID(1, 5);
				List<Newst> list2=newstDao.getLatestNewByTID(2, 5);
				List<Newst> list3=newstDao.getLatestNewByTID(5, 5);
				List<Topic> list=topicsDao.findAllTopics();
				List<Newst> list4=null;
				String tid=request.getParameter("tid");
				if(tid==null || (tid=tid.trim()).length()==0){
					list4=newstDao.getAllnews();
				}else{
					list4=newstDao.getAllnewsByTID(Integer.parseInt(tid));
				}
				request.setAttribute("list1", list1);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("list", list);
				request.setAttribute("list4", list4);
				request.getRequestDispatcher("../index.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("tijiao".equals(opr)){
		Newst newst= new Newst();
		String uploadFilName="";
		String fieldName="";
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		String uploadFilePath=request.getSession().getServletContext().getRealPath("upload/");
			PrintWriter out = response.getWriter();
			if(isMultipart){
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				//
				DiskFileItemFactory diskfatory=new DiskFileItemFactory();
				//设置缓冲区大小
				diskfatory.setSizeThreshold(4069);
				//
				upload.setSizeMax(1024*1024*5);
				
				try{
					List<FileItem> items=upload.parseRequest(request);
					Iterator<FileItem> iter=items.iterator();
					while(iter.hasNext()){
						FileItem item=(FileItem) iter.next();
						if(!item.isFormField()){
						//System.out.print("azzzz");
							String fiName=item.getName();
							List<String> filType=Arrays.asList("gif","bmp","jpg");
							String ext=fiName.substring(fieldName.lastIndexOf(".")+1);
							if(!filType.contains(ext)){
								out.print("<script>alert('上传失败，文件类型只能是gif、bmp、jpg');location.href='newspages/news_add.jsp;</script>");
							}else{
								//System.out.print("asdasdasdd");
								if(fieldName !=null && !fieldName.equals("")){
									File fullFile =new File(item.getName());
									File saveFile= new File(uploadFilePath,fullFile.getName());
									item.write(saveFile);
									uploadFilName=fullFile.getName();
									newst.setNpicPath(uploadFilName);
									//out.print("<script>alert('上传成功的文件名是："+uploadFilName+"');");
									
								}
								
							}
						}else{
							//System.out.print("a123132");
							fieldName=item.getFieldName();
							if(fieldName.equals("user")){
								out.print(item.getString("UTF-8")+"上传了文件。</br>");
							}
							if(item.getFieldName().equals("ntid")){
								newst.setNtid(Integer.parseInt(item.getString("UTF-8")));
							}else if(item.getFieldName().equals("ntitle")){
								newst.setNtitle(item.getString("UTF-8"));
							}else if(item.getFieldName().equals("nauthor")){
								newst.setNauthor(item.getString("UTF-8"));
							}else if(item.getFieldName().equals("npicPath")){
								newst.setNpicPath(item.getString("UTF-8"));
							}else if(item.getFieldName().equals("ncontent")){
								newst.setNcontent(item.getString("UTF-8"));
							}else if(item.getFieldName().equals("nsummary")){
								newst.setNsummary(item.getString("UTF-8"));
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			int i;
			try {
				i = newstDao.findINNSDE(newst);
				if(i>0){
					out.print("<script>alert('添加成功');location.href='newspages/admin.jsp';</script>");
				}else{
					out.print("<script>alert('添加失败');location.href='newspages/news_add.jsp';</script>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
