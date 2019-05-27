package com.example.spring_lec6;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionTestController {
	@GetMapping("/add_session_data_page")
	public String addSessionDataPage() {
		return "add_session_data_page";
	}

	@PostMapping("/add_session_data")
	@ResponseBody
	public String addSessionData(@RequestParam Map<String, String> params, HttpSession session) {
		for (String key : params.keySet()) {
			session.setAttribute(key, params.get(key));
		}
// timeout 시간 설정 (단위: 초)
// 쿠키와는 다르게 특정 시간에 만료되는 개념이 아니고 매 요청시마다 만료 시간이 연장되는 개념
		session.setMaxInactiveInterval(60);
// System.out.println(session.getMaxInactiveInterval());
		return "Success";
	}

	@GetMapping("/show_session_data")
	@ResponseBody
	public String showSessionData(HttpServletRequest request, HttpSession session) {
		Enumeration<String> attrs = session.getAttributeNames();
// request 객체의 getSession 메소드 호출로 가져오는 것도 가능
// Enumeration<String> attrs = request.getSession().getAttributeNames();
		String result = "";
// 세션 ID는 쿠키 헤더에 담겨 전송되어 세션 정보를 찾는데 사용됨
		result += "getId : " + session.getId() + "\n";
// 세션 생성 시점 (unix time)
		result += "getCreationTime : " + session.getCreationTime() + "\n";
// 세션 마지막으로 접근한 시점
		result += "getLastAccessedTime : " + session.getLastAccessedTime() + "\n";
		result += "getMaxInactiveInterval : " + session.getMaxInactiveInterval() + "\n";
		while (attrs.hasMoreElements()) {
			String attr = attrs.nextElement();
			result += attr + " : " + session.getAttribute(attr) + "\n";
		}
		return result;
	}

	@GetMapping("/delete_all_session_data")
	@ResponseBody
	public String deleteAllSessionData(HttpSession session) {
// invalidate 메소드 호출하여 세션 정보 모두 삭제 (해당 세션은 파기되며 이후 세션을 요청하면 새로운 ID를 가진 새 새션이 생성됨)
		session.invalidate();
		return "Success";
	}

// 마찬가지로 특별히 세션 정보가 필요한 페이지가 아니더라도 JSESSIONID를 매 요청시마다 전송
	@GetMapping("/page2")
	@ResponseBody
	public String justPage() {
		return "";
	}
}