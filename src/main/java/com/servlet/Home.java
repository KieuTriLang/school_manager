package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.service.ClassRoomService;
import com.service.ResultService;
import com.service.StudentService;
import com.service.SubjectService;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static StudentService _studentService = new StudentService();
	private static ClassRoomService _classRoomService = new ClassRoomService();
	private static SubjectService _subjectService = new SubjectService();
	private static ResultService _resultService = new ResultService();

	public Home() {
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
		String type = request.getParameter("type");
		request.setAttribute("hd", "Quản lý trường học");
		int curPage = 0;
		int itemPerPage = 10;
		int totalPage = 0;
		try {
			curPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {

		}
		if (type != null) {
			switch (type) {
			case "student":
				request.setAttribute("hd", "Sinh viên");
				List<HashMap<String, String>> dataStudent = _studentService.GetAll();
				if (request.getParameter("sort") != null) {
					if (request.getParameter("sort").equals("name")) {
						dataStudent = _studentService.GetAllByAlphabet();
					}
					if (request.getParameter("sort").equals("dob")) {
						dataStudent = _studentService.GetAllByDob();
					}
					request.setAttribute("sort", request.getParameter("sort"));
				}

				if (request.getParameter("search") != null) {
					dataStudent = searchStudent(request.getParameter("search"), dataStudent);
				}

				totalPage = dataStudent.size() % 10 > 0 ? (dataStudent.size() / 10) + 1 : dataStudent.size() / 10;
				if (totalPage != 0) {
					dataStudent = dataStudent.subList((curPage - 1) * itemPerPage,
							(curPage == totalPage && dataStudent.size() / 10 >= 0) ? dataStudent.size()
									: curPage * itemPerPage);
				}
				request.setAttribute("sortUrl", getOldPath(request, "&sort=[\\w]+"));
				request.setAttribute("students", dataStudent);

				break;
			case "class":
				request.setAttribute("hd", "Lớp học");
				List<HashMap<String, String>> dataClass = _classRoomService.GetAll();
				if (request.getParameter("search") != null) {
					dataClass = searchClass(request.getParameter("search"), dataClass);
				}

				totalPage = dataClass.size() % 10 > 0 ? (dataClass.size() / 10) + 1 : dataClass.size() / 10;
				if (totalPage != 0) {
					dataClass = dataClass.subList((curPage - 1) * itemPerPage,
							(curPage == totalPage && dataClass.size() / 10 >= 0) ? dataClass.size()
									: curPage * itemPerPage);
				}
				request.setAttribute("classes", dataClass);
				break;
			case "subject":
				request.setAttribute("hd", "Môn học");
				List<HashMap<String, String>> dataSubject = _subjectService.GetAll();
				if (request.getParameter("search") != null) {
					dataSubject = searchSubject(request.getParameter("search"), dataSubject);
				}

				totalPage = dataSubject.size() % 10 > 0 ? (dataSubject.size() / 10) + 1 : dataSubject.size() / 10;
				if (totalPage != 0) {
					dataSubject = dataSubject.subList((curPage - 1) * itemPerPage,
							(curPage == totalPage && dataSubject.size() / 10 >= 0) ? dataSubject.size()
									: curPage * itemPerPage);
				}
				request.setAttribute("subjects", dataSubject);
				break;
			case "result":

				request.setAttribute("hd", "Kết quả thi");
				request.setAttribute("classes", _classRoomService.GetAll());
				request.setAttribute("subjects", _subjectService.GetAll());
				if (request.getParameterMap().containsKey("className")) {
					request.setAttribute("subjects",
							_subjectService.GetSubjectByClassName(request.getParameter("className")));
					if(request.getParameterMap().containsKey("subjectName")) {
						
					request.setAttribute("nameTests", _resultService.GetNameTestByClassSubject(request.getParameter("className"),request.getParameter("subjectName")));
					}
				}

				List<HashMap<String, String>> dataResult = _resultService.GetAll();
				if (request.getParameter("sort") != null) {
					if (request.getParameter("sort").equals("incMark")) {
						dataResult = _resultService.GetAllIncMark();
					}
					if (request.getParameter("sort").equals("desMark")) {
						dataResult = _resultService.GetAllDesMark();
					}
					request.setAttribute("sortName", request.getParameter("sort"));
				}

				if (request.getParameter("className") != null) {
					dataResult = filterResultByClass(request.getParameter("className"), dataResult);
					request.setAttribute("className", request.getParameter("className"));
				}
				if (request.getParameter("subjectName") != null) {
					dataResult = filterResultBySubject(request.getParameter("subjectName"), dataResult);
					request.setAttribute("subjectName", request.getParameter("subjectName"));
				}
				if (request.getParameter("search") != null) {
					dataResult = searchNameTest(request.getParameter("search"), dataResult);
				}

				totalPage = dataResult.size() % 10 > 0 ? (dataResult.size() / 10) + 1 : dataResult.size() / 10;
				if (totalPage != 0) {
					dataResult = dataResult.subList((curPage - 1) * itemPerPage,
							(curPage == totalPage && dataResult.size() / 10 >= 0) ? dataResult.size()
									: curPage * itemPerPage);
				}

				request.setAttribute("classUrl", getOldPath(request, "&className=[\\w\\+\\x{00C0}-\\x{00FF}\\x{1EA0}-\\x{1EFF}%20\\.]+"));
				request.setAttribute("subjectUrl", getOldPath(request, "&subjectName=[\\w\\+\\x{00C0}-\\x{00FF}\\x{1EA0}-\\x{1EFF}%20\\.]+"));
				request.setAttribute("sortUrl", getOldPath(request, "&sort=[\\w]+"));

				request.setAttribute("results", dataResult);
				break;
			default:
				break;
			}
		}
		setPagination(request, curPage, totalPage);
		if (request.getQueryString() != null) {
			request.setAttribute("pageUrl", getOldPath(request, "&page=[\\w]+"));
			request.setAttribute("searchUrl",
					getOldPath(request, "&search=[\\w,\\+,\\x{00C0}-\\x{00FF}\\x{1EA0}-\\x{1EFF}]+"));
		}
		request.setAttribute("type", type);
		request.setAttribute("web_title", "Trang chủ");
		request.setAttribute("web_content", "Home/Home.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private List<HashMap<String, String>> searchStudent(String search, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("LastName").toLowerCase().contains(search.toLowerCase())
					|| item.get("FirstName").toLowerCase().contains(search.toLowerCase())) {
				result.add(item);
			}
		}
		return result;
	}

	private List<HashMap<String, String>> searchClass(String search, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("NameClass").toLowerCase().contains(search.toLowerCase())) {
				result.add(item);
			}
		}
		return result;
	}

	private List<HashMap<String, String>> searchSubject(String search, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("NameSubject").toLowerCase().contains(search.toLowerCase())) {
				result.add(item);
			}
		}
		return result;
	}

	private List<HashMap<String, String>> searchNameTest(String search, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("NameTest").toLowerCase().contains(search.toLowerCase())) {
				result.add(item);
			}
		}
		return result;
	}

	private List<HashMap<String, String>> filterResultByClass(String key, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("NameClass").equals(key)) {
				result.add(item);
			}
		}
		return result;
	}

	private List<HashMap<String, String>> filterResultBySubject(String key, List<HashMap<String, String>> data) {
		List<HashMap<String, String>> result = new ArrayList<>();
		for (HashMap<String, String> item : data) {
			if (item.get("NameSubject").equals(key)) {
				result.add(item);
			}
		}
		return result;
	}

	private void setPagination(HttpServletRequest request, int curPage, int totalPage) {
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
	}

	private String getOldPath(HttpServletRequest request, String regex) {
		String url = request.getRequestURI();
		String parameters = request.getQueryString().replaceAll(regex, "");
		url += "?" + parameters;
		return url;
	}
}
