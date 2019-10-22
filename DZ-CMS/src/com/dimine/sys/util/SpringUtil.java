// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SpringUtil.java

package com.dimine.sys.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dimine.security.entity.LoginUserEntity;

public class SpringUtil {

	public SpringUtil() {
	}

	public static Object getBean(ServletContext context, String beanName) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		return ctx.getBean(beanName);
	}

	public static LoginUserEntity getUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth != null) {
			UserDetails user = (UserDetails) auth.getPrincipal();
			if (user instanceof LoginUserEntity) {
				LoginUserEntity usr = (LoginUserEntity) user;
				return usr;
			}
		}
		return null;
	}
}
