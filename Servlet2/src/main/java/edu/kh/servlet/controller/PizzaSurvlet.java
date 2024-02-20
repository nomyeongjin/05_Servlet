package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PizzaSurvlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 얻어오기
		String pizza = req.getParameter("pizza");
		String size = req.getParameter("size");
		String dou = req.getParameter("dou");
		String inputName = req.getParameter("inputName");
		String inputAddress = req.getParameter("inputAddress"); 
		
		// 배열로 받아옴
		String[] topping = req.getParameterValues("topping");
		
		System.out.println("[서버] /ex3 요청됨");
		
		//--------------------------------------------------------
		
		int price = 0;
		switch(pizza) {
		case "콤비네이션" : price+=15000;break;
		case "치즈" : price+=10000;break;
		case "하와이안" : price+=13000;break;
		case "포테이토" : price+=15000;break;
		}
		
		if(size.equals("large")) price+=3000;
		
		switch(dou) {
		case "original" : break;
		case "thin" : price+=2000; break;
		case "crust" : price+=3000; break;
		}
		
		if(topping!=null) {
			for(String top : topping) {
				switch(top){
				case "치즈" : price+=3000; break;
				case "옥수수" : price+=1000;break;
				case "소스" : price+=500; break;
				}
			}
		}
		
		// ==================================================================
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE>");
		
		sb.append("<head>");
		sb.append(String.format("<title> %s님 영수증</title>", inputName));
		sb.append("</head>");

		sb.append("<body>");
		sb.append(String.format("<h3>주문자명 : %s 님</h3>", inputName));
		sb.append(String.format("<h3>주소 : %s </h3>", inputAddress));
		
		sb.append("<ul>");
		sb.append(String.format("<h4>주문하신 피자 : %s</h4>", pizza));
		
		String temp2 = dou.equals("medium")?"M":"L";
		sb.append(String.format("<li>사이즈 : %s</li>", temp2));
		
		String temp = dou.equals("original")?dou.equals("thin")?"기본":"씬":"치즈크러스트";
		sb.append(String.format("<li>도우 : %s</li>", temp));
		
		if(topping!=null) {
			sb.append("<li>");
			sb.append("토핑 선택 : ");
			for(String top : topping) sb.append(top+" ");
		}
		sb.append("</li>");
		
		
		sb.append("</ul>");
		
		sb.append(String.format("<h3>금액 : %d원</h3>", price));
		
		sb.append("</body>");
		
		out.print(sb.toString());
		
	
	
	}
	
	
	

}
