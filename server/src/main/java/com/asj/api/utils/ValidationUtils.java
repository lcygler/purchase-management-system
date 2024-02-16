package com.asj.api.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

import com.asj.api.exceptions.ValidationErrorException;

public class ValidationUtils {

	public static void handleErrors(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				String field = error.getField();
				String errorMsg = error.getDefaultMessage();
				errors.put(field, errorMsg);
			});

			if (!errors.isEmpty()) {
				System.out.println(errors);
				throw new ValidationErrorException(errors);
			}
		}
	}

	public static void handlePartialErrors(BindingResult bindingResult, Object payload) {
		Map<String, String> errors = new HashMap<>();

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				String field = error.getField();

				if (isFieldPresent(field, payload)) {
					String errorMsg = error.getDefaultMessage();
					errors.put(field, errorMsg);
				}
			});

			if (!errors.isEmpty()) {
				System.out.println(errors);
				throw new ValidationErrorException(errors);
			}
		}
	}

	private static boolean isFieldPresent(String fieldName, Object payload) {
		try {
			Field field = payload.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(payload) != null;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			return false;
		}
	}

}