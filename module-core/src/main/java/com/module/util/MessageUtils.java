package com.module.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Locale;

@RequiredArgsConstructor
@Component
public class MessageUtils {

	public static MessageSourceAccessor messageSource;

	private final MessageSourceAccessor messageSourceAccessor;

	@PostConstruct
	private void initMessageSource () {
		messageSource = this.messageSourceAccessor;
	}

	public static String getMessage(String code) {
		return messageSource.getMessage(code, Locale.getDefault());
	}

	public static String getMessage(String code, String... param) {
		String message = messageSource.getMessage(code, Locale.getDefault());

		if (param != null && param.length > 0) {
			MessageFormat messageFormat = new MessageFormat(message);
			message = messageFormat.format(param);
		}

		return message;
	}
}