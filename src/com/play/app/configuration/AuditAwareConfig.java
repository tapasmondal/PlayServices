package com.play.app.configuration;

import java.util.Map;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.play.app.entity.User;

public class AuditAwareConfig implements AuditorAware<User> {

	@Override
	public User getCurrentAuditor() {
		User user = new User();
		// TODO - Revist logic
		Integer userId = 1;
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> authExtraInfo = (Map<String, Object>) SecurityContextHolder.getContext()
					.getAuthentication().getDetails();
			userId = (Integer) authExtraInfo.get("loggedInUserId");
		} catch (Exception e) {
			// TODO: handle exception
		}

		user.setUserId(userId);
		return user;
	}

}