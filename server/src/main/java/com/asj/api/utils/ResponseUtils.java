package com.asj.api.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

	public static Map<String, Object> createSuccessResponse(String message, Integer id) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("id", id);
		return response;
	}

	public static Map<String, String> createErrorResponse(String message) {
		Map<String, String> response = new HashMap<>();
		response.put("error", message);
		return response;
	}

}