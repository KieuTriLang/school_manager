package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.ResultModel;
import com.model.ResultModelUpdate;
import com.model.StudentModel;
import com.service.ResultService;
import com.service.StudentService;
import com.service.SubjectService;

/**
 * Servlet implementation class Result
 */
@WebServlet(urlPatterns = { "/result" })
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultService _resultService = new ResultService();
	private static StudentService _studentService = new StudentService();
	private static SubjectService _subjectService = new SubjectService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Result() {
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
			int classId = Integer.parseInt(request.getParameter("classId")), subjectId = 0;
			if(request.getParameter("type").equals("edit") || request.getParameter("type").equals("delete")) {				
				subjectId = Integer.parseInt(request.getParameter("subjectId"));
			}			
			String nameTest = request.getParameter("nameTest");
			if (request.getParameter("type").equals("add")) {
				request.setAttribute("web_title", "Add result");
			}
			if (request.getParameter("type").equals("edit")) {
				request.setAttribute("web_title", "Edit result");
				request.setAttribute("results", _resultService.GetByClassSubjectNameTest(classId,subjectId,nameTest));
			}
			if (request.getParameter("type").equals("delete")) {
				request.setAttribute("deleteUrl", getOldPath(request,""));
				request.setAttribute("web_title", "Confirm delete");
				request.setAttribute("results", _resultService.GetByClassSubjectNameTest(classId,subjectId,nameTest));
			}
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("classId", classId);
			request.setAttribute("subjects", _subjectService.GetSubjectByClassId(classId));
			request.setAttribute("students", _studentService.GetStudentsByClassId(classId));
			request.setAttribute("web_content", "Result/Form.jsp");
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

					String[] id = null;
					if (request.getParameter("type").equals("edit")) {
						id = request.getParameterValues("id");
					}
					HashMap<String, String> validateList = checkData(request);
					if (validateList.size() == 0) {
						int classId = Integer.parseInt(request.getParameter("classId"));
						int subjectId = Integer.parseInt(request.getParameter("subjectId"));
						String[] studentId = request.getParameterValues("studentId");
						String nameTest = request.getParameter("nameTest");
						String[] mark = request.getParameterValues("mark");

						
						if (request.getParameter("type").equals("add")) {
							ResultModel newSt = new ResultModel(id, _resultService.GetClassSubjectId(classId, subjectId),
									studentId, nameTest, mark);
							if (_resultService.Add(newSt)) {
								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Thêm kết quả thành công");
								response.sendRedirect("home?type=result");
							} else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Thêm kết quả thất bại");
							}
						} else {
							if (updateResult(id, _resultService.GetClassSubjectId(classId, subjectId), studentId, nameTest, mark)) {

								session.setAttribute("Notifi", "success");
								session.setAttribute("NotiMsg", "Cập nhật kết quả thành công");
								response.sendRedirect("home?type=result");
							} else {

								session.setAttribute("Notifi", "danger");
								session.setAttribute("NotiMsg", "Cập nhật kết quả thất bại");
							}
						}
					} else {
						request.setAttribute("validateList", validateList);
						request.setAttribute("oldData", getOldData(request));
						request.setAttribute("oldMark", getOldMark(request));
						doGet(request, response);
					}

				}
				if (request.getParameter("type").equals("delete")) {
					String[] ids = request.getParameterValues("id");
					if (_resultService.DeleteRange(ids)) {
						session.setAttribute("Notifi", "warning");
						session.setAttribute("NotiMsg", "Xóa điểm thành công");
						response.sendRedirect("home?type=result");
					} else {

						session.setAttribute("Notifi", "danger");
						session.setAttribute("NotiMsg", "Xóa điểm thất bại");
					}
				}
			}
		} catch (Exception e) {

		}

	}

	private HashMap<String, String> checkData(HttpServletRequest request) {
		
		HashMap<String, String> list = new HashMap<>();

		boolean c2 = request.getParameter("subjectId").equals("0");
		if (c2) {
			list.put("vSubjectId", "Hãy chọn môn học!");
		}
		boolean c4 = request.getParameter("nameTest").equals("");
		if (c4) {
			list.put("vNameTest", "Không được để trống!");
		}
		
		if (!c2 && !c4 && _resultService.IsExistsNameTest(Integer.parseInt(request.getParameter("classId")), Integer.parseInt(request.getParameter("subjectId")), request.getParameter("nameTest"))) {
			list.put("vNameTest", "Bài thi đã tồn tại!");
		}
		String[] markArr = request.getParameterValues("mark") != null ? request.getParameterValues("mark") : null;
		if (markArr == null) {
			list.put("vMark", "Hãy nhập đủ thông tin");
		} else {
			for (String item : markArr) {
				try {
					Float.parseFloat(item);
				} catch (Exception e) {
					list.put("vMark", "Hãy nhập đúng thông tin");
				}
			}
		}

		return list;
	}

	private HashMap<String, String> getOldData(HttpServletRequest request) {
		HashMap<String, String> oldData = new HashMap<>();
		oldData.put("dSubjectId", request.getParameter("subjectId"));
		oldData.put("dNameTest", request.getParameter("nameTest"));
		
		return oldData;
	}
	private List<String> getOldMark(HttpServletRequest request){
		List<String> oldData = new ArrayList<>();
		for(String item : request.getParameterValues("mark")) {
			oldData.add(item);
		}
		return oldData;
	}
	private boolean updateResult(String[] id, int classSubject,String[] studentId,String nameTest,String[] mark) {
		for(int i=0;i<id.length;i++) {
			ResultModelUpdate model = new ResultModelUpdate(Integer.parseInt(id[i]),classSubject,Integer.parseInt(studentId[i]),nameTest,Float.parseFloat(mark[i]));
			if(!_resultService.Update(model)) {
				return false;
			}
		}
		return true;
	}
	private String getOldPath(HttpServletRequest request, String regex) {
		String url = request.getRequestURI();
		String parameters = request.getQueryString().replaceAll(regex, "");
		url += "?" + parameters;
		return url;
	}
}
