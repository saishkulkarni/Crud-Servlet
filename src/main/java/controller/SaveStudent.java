package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dto.Student;

@WebServlet("/savestudent")
public class SaveStudent extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date dob = Date.valueOf(req.getParameter("dob"));

		Student student = new Student();
		student.setAddress(req.getParameter("address"));
		student.setCountry(req.getParameter("country"));
		student.setName(req.getParameter("name"));
		student.setMobile(Long.parseLong(req.getParameter("mob")));
		student.setHobbies(req.getParameterValues("hobbies"));
		student.setGender(req.getParameter("gender"));
		student.setDob(dob);
		student.setEmail(req.getParameter("email"));
		student.setAge(Period.between(dob.toLocalDate(),LocalDate.now()).getYears());

		StudentDao dao=new StudentDao();
		dao.saveStudent(student);
		
		resp.getWriter().print("<h1>Data added Succesfully</h1>");
		
		req.getRequestDispatcher("home.html").include(req, resp);
		
	}
}
