package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.model.StudentModel;
import com.service.ClassRoomService;
import com.service.ResultService;
import com.service.StudentService;
import com.service.SubjectService;

/**
 * Servlet implementation class Student
 */
@WebServlet(urlPatterns = { "/student" })
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StudentService _studentService = new StudentService();
	private static ClassRoomService _classRoomService = new ClassRoomService();
	private static ResultService _resultService = new ResultService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameterMap().containsKey("type")) {
			if (request.getParameter("type").equals("add")) {
				request.setAttribute("web_title", "Add student");
			}
			if (request.getParameter("type").equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Edit student");
				request.setAttribute("student", _studentService.GetById(id));
			}
			if (request.getParameter("type").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("web_title", "Confirm delete");
				request.setAttribute("student", _studentService.GetById(id));
				request.setAttribute("results", _resultService.GetByStudentId(id));
			}
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("classes", _classRoomService.GetAll());
			request.setAttribute("web_content", "Student/Form.jsp");
		}
		if (!request.getParameterMap().containsKey("type") && request.getParameterMap().containsKey("id")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("web_title", "Detail");
			request.setAttribute("web_content", "Student/Detail.jsp");
			request.setAttribute("student", _studentService.GetById(id));
			request.setAttribute("results", _resultService.GetByStudentId(id));
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
					HashMap<String,String> validateList = checkData(request);
					if(validateList.size() <=0) {
						int id = 0;
						if(!request.getParameter("id").equals("")) {
							id = Integer.parseInt(request.getParameter("id"));
						}
						
						String firstName = request.getParameter("firstName");
						String lastName = request.getParameter("lastName");
						Boolean gender = request.getParameter("gender").equals("male") ? true : false;
						String dob = request.getParameter("dob");
						String phone = request.getParameter("phone");
						String address = request.getParameter("address");
						int classId = Integer.parseInt(request.getParameter("classId"));
						
						StudentModel newSt = new StudentModel(id,firstName,lastName,gender,dob,phone,address,classId);
						if(request.getParameter("type").equals("add")) {
							if(_studentService.Add(newSt)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Thêm sinh viên thành công");
								response.sendRedirect(request.getContextPath() + "/home?type=student");
							}else {
								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Thêm sinh viên thất bại");
							}
						}else {
							if(_studentService.Update(newSt)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Cập nhật sinh viên thành công");
								response.sendRedirect("student?id="+id);
							}else {
								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Cập nhật sinh viên thất bại");
							}
						}
					}else {
						request.setAttribute("validateList", validateList);
						request.setAttribute("oldData", getOldData(request));
						doGet(request,response);
					}
					

				}
				if (request.getParameter("type").equals("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if(_studentService.DeleteById(id)) {
						session.setAttribute("Notifi", "warning");
						session.setAttribute("NotiMsg", "Xóa sinh viên thành công");
						response.sendRedirect(request.getContextPath() + "/home?type=student");
					}else {

						session.setAttribute("Notifi", "danger");
						session.setAttribute("NotiMsg", "Xóa sinh viên thất bại");
					}
				}
			}
		}catch(Exception e) {
			
		}		
	}
	private HashMap<String,String> checkData(HttpServletRequest request) {
		HashMap<String,String> validateList = new HashMap<>();
		boolean c2 = request.getParameter("firstName").equals("");
		if(c2) {
			validateList.put("vFirstName", "Không được để trống!");
		}
		boolean c3 = request.getParameter("lastName").equals("");
		if(c3) {
			validateList.put("vLastName", "Không được để trống!");
		}
		boolean c4 = request.getParameter("gender") == null;
		if(c4) {
			validateList.put("vGender", "Không được để trống!");
		}
		boolean c5 = request.getParameter("dob").equals("");
		if(c5) {
			validateList.put("vDob", "Không được để trống!");
		}
		boolean c6 = request.getParameter("phone").equals("");
		if(c6) {
			validateList.put("vPhone", "Không được để trống!");		
		}
		if(!c6 && _studentService.IsExistsPhone(request.getParameter("phone"))) {
			validateList.put("vPhone", "Số điện thoại đã tồn tại");
		}
		if(!c6 &&request.getParameter("phone").length() > 10) {
			validateList.put("vPhone", "Số điện thoại quá dài");
		}
		boolean c7 = request.getParameter("address").equals("");
		if(c7) {
			validateList.put("vAddress", "Không được để trống!");
		}
		boolean c8 = request.getParameter("classId") == null;
		if(c8) {
			validateList.put("vClassId", "Không được để trống!");
		}
		return validateList;
	}
	private HashMap<String,String> getOldData(HttpServletRequest request){
		HashMap<String,String> oldData = new HashMap<>();
		oldData.put("dFirstName", request.getParameter("firstName"));
		oldData.put("dLastName", request.getParameter("lastName"));
		oldData.put("dGender", request.getParameter("gender"));
		oldData.put("dDob", request.getParameter("dob"));
		oldData.put("dPhone", request.getParameter("phone"));
		oldData.put("dAddress", request.getParameter("address"));
		oldData.put("dClassId", request.getParameter("classId"));		
		return oldData;
	}
}
