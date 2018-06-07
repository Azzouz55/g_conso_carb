package com.pfe.gestioncarburant.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pfe.gestioncarburant.entities.User;
import com.pfe.gestioncarburant.services.UserProfileService;

@Component
@ManagedBean
@SessionScoped
public class UserProfileBean {
	@Autowired
	private UserProfileService userProfileService;
	private User user = new User();

	public void init() {
		try {
			user = userProfileService
					.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User getUser() {
		init();
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String displayUserInfo() {
		init();
		return user.getEmployee().getNom() + ", " + user.getEmployee().getPrenom();
	}
}
