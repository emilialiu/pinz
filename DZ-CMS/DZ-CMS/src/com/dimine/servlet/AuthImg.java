// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AuthImg.java

package com.dimine.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthImg extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Font mFont;
	private static String imgCode = "";

	public AuthImg() {
		mFont = new Font("System", 1, 28);
	}

	public void init() throws ServletException {
		super.init();
	}

	public static String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		imgCode = imgCode;
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		response.setContentType("image/jpeg");
		int width = 78;
		int height = 26;
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.fillRect(1, 1, width - 1, height - 1);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String tmp = getRandomNumber();
			sRand = (new StringBuilder(String.valueOf(sRand))).append(tmp)
					.toString();
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(tmp, 15 * i + 7, 23);
		}

		HttpSession session = request.getSession(true);
		session.removeAttribute("validatecode");
		session.setAttribute("validatecode", sRand);
		setImgCode(sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	private String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2D);
		long itmp = 0L;
		char ctmp = '\0';
		switch (rand) {
		case 1: // '\001'
			itmp = Math.round(Math.random() * 25D + 65D);
			ctmp = (char) (int) itmp;
			return String.valueOf(ctmp);

		case 2: // '\002'
			itmp = Math.round(Math.random() * 25D + 97D);
			ctmp = (char) (int) itmp;
			return String.valueOf(ctmp);
		}
		itmp = Math.round(Math.random() * 9D);
		return String.valueOf(itmp);
	}

	private String getRandomNumber() {
		long itmp = 0L;
		itmp = Math.round(Math.random() * 9D);
		return String.valueOf(itmp);
	}

}
