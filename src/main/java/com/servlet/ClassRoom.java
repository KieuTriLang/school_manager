package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import com.model.ClassRoomModel;
import com.model.StudentModel;
import com.service.ClassRoomService;
import com.service.StudentService;
import com.service.SubjectService;

/**
 * Servlet implementation class ClassRoom
 */
@WebServlet(urlPatterns = {"/class"})
public class ClassRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ClassRoomService _classRoomService = new ClassRoomService();
    private static StudentService _studentService = new StudentService();
    private static SubjectService _subjectService = new SubjectService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameterMap().containsKey("type")) {
			if (request.getParameter("type").equals("add")) {
				request.setAttribute("web_title", "Thêm lớp");
			}
			if (request.getParameter("type").equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Sửa lớp");
				request.setAttribute("classRoom", _classRoomService.GetById(id));
				request.setAttribute("subjects", _subjectService.GetAll());
				request.setAttribute("classsubjects", _classRoomService.GetClassSubject(id));
			}
			if (request.getParameter("type").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Xác nhận xóa lớp");
				request.setAttribute("classRoom", _classRoomService.GetById(id));
				request.setAttribute("students", _studentService.GetStudentsByClassId(id));
				request.setAttribute("subjects", _subjectService.GetSubjectByClassId(id));
			}
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("web_content", "ClassRoom/Form.jsp");
		}
		if (!request.getParameterMap().containsKey("type") && request.getParameterMap().containsKey("id")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("web_title", "Thông tin lớp");
			request.setAttribute("web_content", "ClassRoom/Detail.jsp");
			request.setAttribute("classRoom", _classRoomService.GetById(id));
			request.setAttribute("students", _studentService.GetStudentsByClassId(id));
			request.setAttribute("subjects", _subjectService.GetSubjectByClassId(id));
		}
		request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if (request.getParameterMap().containsKey("type")) {
				HttpSession session = request.getSession();
				if (request.getParameter("type").equals("add") || request.getParameter("type").equals("edit")) {
					
					int id = -1;
					if(!request.getParameter("id").equals("")) {
						id = Integer.parseInt(request.getParameter("id"));
					}
					if(!request.getParameter("nameClass").equals("")) {
						String nameClass = request.getParameter("nameClass");
						
						ClassRoomModel newCl = new ClassRoomModel(id,nameClass);
						if(request.getParameter("type").equals("add")) {
							if(_classRoomService.Add(newCl)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Thêm lớp thành công");
								response.sendRedirect(request.getContextPath()+"/home?type=class");
							}else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Thêm lớp thất bại");
							}
						}else {
							if(_classRoomService.Update(newCl)) {
								String[] arr = request.getParameterValues("subject") != null?request.getParameterValues("subject"):new String[4];
								if(_classRoomService.AddSubjectToClass(id, arr)) {
									session.setAttribute("Notifi", "success");
									session.setAttribute("NotiMsg", "Cập nhật lớp thành công");
									response.sendRedirect("class?id="+id);							
								}else {

									session.setAttribute("Notifi", "danger");
									session.setAttribute("NotiMsg", "Cập nhật thất bại 1");
								}
							}else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Cập nhật thất bại 2");
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
					if(_classRoomService.DeleteById(id)) {
						session.setAttribute("Notifi", "warning");
						session.setAttribute("NotiMsg", "Xóa lớp thành công");
						response.sendRedirect(request.getContextPath()+"/home?type=class");
					}else {

						session.setAttribute("Notifi", "danger");
						session.setAttribute("NotiMsg", "Xóa lớp thất bại");
					}
				}
			}
		}catch(Exception e) {
			
		}
		
	}

}
