package com.toolkit.lang;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * 
 * @author qiaofeng
 * @version $Id: CookieUtil, v 0.1 2013-3-30 下午08:44:47 Exp $
 */
public class CookieUtil {

    public static String getLocalInfo(HttpServletRequest request, String key) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null)
            return null;

        String value = null;
        for (Cookie cookie : cookies) {
            String cname = cookie.getName();
            if (cname.equals(key)) {
                value = cookie.getValue();
            }
        }

        return value;
    }

    public static void setLocalInfo(HttpServletResponse response, String domain, String key,
                                    String value, int cookieAge) {
        Cookie cookieToken = new Cookie(key, value);
        cookieToken.setDomain(domain);
        cookieToken.setPath("/");
        cookieToken.setMaxAge(cookieAge);

        response.addCookie(cookieToken);
    }

    public static void removeLocalInfo(HttpServletResponse response, String domain, String key) {
        Cookie cookieToken = new Cookie(key, "");
        cookieToken.setDomain(domain);
        cookieToken.setPath("/");
        cookieToken.setMaxAge(0);
        response.addCookie(cookieToken);
    }
}
