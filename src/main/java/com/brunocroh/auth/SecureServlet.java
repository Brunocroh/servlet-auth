package com.brunocroh.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SecureServlet")
@ServletSecurity(
	@HttpConstraint(
		rolesAllowed= {"admin"},transportGuarantee=TransportGuarantee.CONFIDENTIAL
	)
)
public class SecureServlet extends HttpServlet{

	private static final long serialVersionUID = 8076565604615284626L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if(req.isSecure() 
				&& req.getUserPrincipal() != null
				&& req.isUserInRole("admin")) {
			try {
				resp.sendRedirect("/home.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
