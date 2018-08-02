package org.jbit.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbit.news.entity.Newst;
import org.jbit.news.service.NewstServicDao;
import org.jbit.news.service.impl.NewstServicDaoimpl;

public class adminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewstServicDao nedao= new NewstServicDaoimpl();
     	List<Newst> list;
		try {
			list = nedao.infoNewst();
		
   		request.setAttribute("list", list);
   		request.getRequestDispatcher("../newspages/admin.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
