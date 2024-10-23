package com.module.util;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.UUID;

@Slf4j
public class CommonUtils {

	/**
	 * 유니크 키 값
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getUUIDLong() {
		return UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 아이피 주소 가져오기
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		if (ip != null && ip.length() != 0) {
			ip = ip.split(",")[0];
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * 헤더값 출력
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getRequestHeader(HttpServletRequest request) {

		JSONObject headers = new JSONObject();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headers.put(key, value);
		}

		return headers.toString();
	}

	public static String getDeviceName(HttpServletRequest request) {
		Device device = DeviceUtils.getCurrentDevice(request);
		String deviceName = "";

		if(null != device) {
			if(device.isMobile()) {
				deviceName = "MOBILE";
			} else if (device.isTablet()) {
				deviceName = "TABLET";
			} else if (device.isNormal()) {
				deviceName = "PC";
			} else {
				deviceName = "ETC";
			}
		}

		return deviceName;
	}

	public static String toCamelCase(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}

		StringBuilder result = new StringBuilder();
		boolean capitalizeNext = false;

		for (char currentChar : input.toCharArray()) {
			if (currentChar == '_' || currentChar == ' ') {
				capitalizeNext = true;
			} else {
				result.append(capitalizeNext ? Character.toUpperCase(currentChar) : Character.toLowerCase(currentChar));
				capitalizeNext = false;
			}
		}

		return result.toString();
	}

	/**
	 * urlEncode
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str){
		String returnVal = "";
		try {
			returnVal = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	/**
	 * urlDecode
	 * @param str
	 * @return
	 */
	public static String urlDecode(String str){
		String returnVal = "";
		try {
			returnVal = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnVal;
	}
}
