package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import com.model.SubjectModel;
import com.service.SubjectService;

/**
 * Servlet implementation class Subject
 */
@WebServlet(urlPatterns = {"/subject"})
public class Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SubjectService _subjectService = new SubjectService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("type")) {
			if (request.getParameter("type").equals("add")) {
				request.setAttribute("web_title", "Add subject");
			}
			if (request.getParameter("type").equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Edit subject");
				request.setAttribute("subject", _subjectService.GetById(id));
			}
			if (request.getParameter("type").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Confirm delete");
				request.setAttribute("subject", _subjectService.GetById(id));
			}
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("web_content", "Subject/Form.jsp");
		}
		if (!request.getParameterMap().containsKey("type") && request.getParameterMap().containsKey("id")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("web_title", "Detail subject");
			request.setAttribute("web_content", "Subject/Detail.jsp");
			request.setAttribute("subject", _subjectService.GetById(id));
		}
		request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameterMap().containsKey("type")) {
				HttpSession session = request.getSession();
				if (request.getParameter("type").equals("add") || request.getParameter("type").equals("edit")) {

					int id = -1;
					if (!request.getParameter("id").equals("")) {
						id = Integer.parseInt(request.getParameter("id"));
					}
					if(!request.getParameter("nameSubject").equals("")) {
						String nameClass = request.getParameter("nameSubject");

						SubjectModel newSj = new SubjectModel(id, nameClass);
						if (request.getParameter("type").equals("add")) {
							if (_subjectService.Add(newSj)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Thêm môn học thành công");
								response.sendRedirect("home?type=subject");
							}else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Thêm môn học thất bại");
							}
						} else {
							if (_subjectService.Update(newSj)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Cập nhật môn học thành công");
								response.sendRedirect("home?type=subject");
							}else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Cập nhật môn học thất bại");
							}
						}
					}else {
						HashMap<String,String> validateList = new HashMap<>();
						validateList.put("vName", "Không được để trống!");
						request.setAttribute("validateList", validateList);
						doGet(request,response);
					}
					

				}
				if (request.getParameter("type").equals("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if (_subjectService.DeleteById(id)) {
						session.setAttribute("Notifi", "warning");
						session.setAttribute("NotiMsg", "Xóa môn học thành công");
						response.sendRedirect("home?type=subject");
					}else {

						session.setAttribute("Notifi", "danger");
						session.setAttribute("NotiMsg", "Xóa môn học thất bại");
					}
				}
			}
		}catch(Exception e) {
			
		}		
	}

}
