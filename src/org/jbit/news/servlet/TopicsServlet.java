package org.jbit.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbit.news.entity.Comments;
import org.jbit.news.entity.Newst;
import org.jbit.news.entity.Topic;
import org.jbit.news.service.NewstServicDao;
import org.jbit.news.service.TopicsService;
import org.jbit.news.service.impl.NewstServicDaoimpl;
import org.jbit.news.service.impl.TopicsServiceImpl;
import org.jbit.news.util.Page;

public class TopicsServlet extends HttpServlet {

	
	private static final String String = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		NewstServicDao newsDao = new NewstServicDaoimpl();
	  	TopicsService topicsService= new TopicsServiceImpl();  
	  	String opr=request.getParameter("opr");
	    String url=request.getParameter("url");
	  	PrintWriter out=response.getWriter();
		if("del".equals(opr)){
	  		String tid=request.getParameter("tid");
	  		try{
	  			int res=topicsService.deleteTopic(Integer.parseInt(tid));
	  			if(res==-1){
	  				out.print("<script>alert('该主题下还有文章，不能删除！');location.href='newspages/topic_list.jsp';</script>");
	  			}else if(res==0){
	  				out.print("<script>alert('未找到相关主题，点击确认返回！');location.href='newspages/topic_list.jsp';</script>");
	  			}else {
	  				out.print("<script>alert('已经成功删除主题，点击确定返回主题列表');location.href='TopicsServlet?opr=list';</script>");
	  			}
	  		}catch(Exception e){
	  			e.printStackTrace();
	  		}
	  	}else if(opr.equals("list")){
	  		List<Topic> list;
			try {
				list = topicsService.findAllTopics();
				request.setAttribute("list", list);
				request.getRequestDispatcher("newspages/topic_list.jsp").forward(request,
				response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	}else if(opr.equals("add")){
	  		String tid=request.getParameter("tid");
	  		String tname=request.getParameter("tname");
	  		Topic topic;
			try {
				topic = newsDao.selectTop(tname);
		  		if(topic==null){
		  			int res=newsDao.addTop(tname);
		  			if(res>0){
		  				out.print("<script>alert('添加成功！');location.href='TopicsServlet?opr=list'</script>");
					} else {
						out.print("<script>alert('添加失败！');location.href='newspages/topic_add.jsp'</script>");
					}
		  		} else {
					out.print("<script>alert('当前主题已存在，请输入不同的主题！');location.href='newspages/topic_add.jsp'</script>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	}else if("list".equals(opr)){
	       		List<Topic> list;
				try {
					list = newsDao.selectTop();
		       		request.setAttribute("list", list);
		       		request.getRequestDispatcher("newspages/topic_list.jsp").forward(request, response);
//		       		response.sendRedirect("newspages/topic_list.jsp");
//					String site=new String("newspages/topic_list.jsp");
//					response.setStatus(response.SC_MOVED_TEMPORARILY);
//					response.setHeader("", site);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }else if("listTitle".equals(opr)){
	     	//获得页数
			String pageIndex=request.getParameter("pageIndex");
			if(pageIndex==null || (pageIndex = pageIndex.trim()).length()==0){
				pageIndex="1";
			}
			int currPageNo =Integer.parseInt(pageIndex);
			//对首页与末页进行控制
			if(currPageNo<1){
				currPageNo=1;
			}
			//总页数需要查询记录总数后计算得到，故末控制在业务方法中进行
			Page pageObj= new Page();
			pageObj.setCurrPageNo(currPageNo);
			pageObj.setPageSize(15);
			NewstServicDao newsService= new NewstServicDaoimpl();
			try {
				newsService.findPageNews(pageObj);
				List<Newst> list1=newsDao.getLatestNewByTID(1, 5);
				List<Newst> list2=newsDao.getLatestNewByTID(2, 5);
				List<Newst> list3=newsDao.getLatestNewByTID(5, 5);
				List<Newst> list4=null;
				List<Topic> list=newsDao.selectTop();
				String tid=request.getParameter("tid");
					if(tid==null || (tid=tid.trim()).length()==0){
						//list4=newsDao.select("", 0);
						newsService.findPageNews(pageObj);
						list4=pageObj.getNewsList();
					}else{
						String sql = " where ntid = ?";
						list4=newsDao.select(sql,Integer.parseInt(tid));
					}
				request.setAttribute("list1", list1);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("list", list);
				request.setAttribute("list4", list4);
					
				request.setAttribute("list4", list4);
				request.setAttribute("pageObj", pageObj);
				
				String uri=request.getParameter("url");
				request.getRequestDispatcher(uri).forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("newsread".equals(opr)){
			String cnid = request.getParameter("cnid");
			String cauthor = request.getParameter("cauthor");
			String cip = request.getParameter("cip");
			String ccontent = request.getParameter("ccontent");
			Comments cs= new Comments();
			cs.setCnid(Integer.parseInt(cnid));
			cs.setCauthor(cauthor);
			cs.setCcontent(ccontent);
			cs.setCauthor(cauthor);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cs.setCdate(dateFormat.format(new Date()));
			try {
				if (newsDao.insert(cs) > 0) {
				response.sendRedirect("../news_read.jsp?nid=" + cnid);
				}else{
				response.sendRedirect("../news_read.jsp?nid=" + cnid);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("newsadd".equals(opr)){
			List<Topic> newsadd;
			try {
				newsadd = topicsService.findAllTopics();
				request.setAttribute("newsadd", newsadd);
				request.getRequestDispatcher("newspages/news_add.jsp").forward(request,
				response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
