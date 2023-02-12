package com.ducdmd152.springboot.dsnackerstore.utils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
	public<T> T getAttributeFromSession(HttpSession session, String key) {
		if(session == null) {
			return null;
		}
		
		T result = (T) session.getAttribute(key);
		
		return result;
	}
}
