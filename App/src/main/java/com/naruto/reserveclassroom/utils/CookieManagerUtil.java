package com.naruto.reserveclassroom.utils;
/*
 * Created by NARUTO on 2016/11/8.
 */

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManagerUtil implements CookieJar {

	private final List<Cookie> allCookies = new ArrayList<Cookie>();

	private static List<Cookie> cookies;

	public static List<Cookie> getCookies() {

		return cookies != null ? cookies : new ArrayList<Cookie>();
	}

	public static void setCookies(List<Cookie> cookies) {

		CookieManagerUtil.cookies = cookies;
	}

	public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

		allCookies.addAll(cookies);

		setCookies(cookies);
	}

	@Override
	public synchronized List<Cookie> loadForRequest(HttpUrl url) {

		List<Cookie> result = new ArrayList<Cookie>();
		for (Cookie cookie : allCookies) {

			if (cookie.matches(url)) {

				result.add(cookie);
			}
		}
		return result;
	}
}
