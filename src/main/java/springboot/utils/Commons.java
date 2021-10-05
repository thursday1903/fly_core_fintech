package springboot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import springboot.logs.Logs;

public class Commons {

	private static final String USER_AGENT = "Mozilla/5.0";
	final static Logs log4jLogger = new Logs(Commons.class);

	public Commons() {
		// TODO Auto-generated constructor stub
	}

	public static Object loadClassByName(String className) {
		Object chargingInterface = null;
		try {
			Class<?> clazz = Class.forName(className);

			// Get the private constructor.
			Constructor<?> cons = clazz.getDeclaredConstructor();

			// Since it is private, make it accessible.
			cons.setAccessible(true);

			// Create new object.
			chargingInterface = (Object) cons.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log4jLogger.fatal("LOAD CLASS FAIL[" + className + "]", e);
		}
		return chargingInterface;
	}

	public static String callHttpGet(String url) {
		log4jLogger.info("CALL HTTP GET:" + url);
		HttpClient client = null;
		StringBuffer result = new StringBuffer();
		try {
			// HttpClient client =
			// HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
			client = HttpClientBuilder.create().build();

			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response_ = client.execute(request);

			int responseCode = response_.getStatusLine().getStatusCode();
			result = new StringBuffer();
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // success
				BufferedReader rd = new BufferedReader(new InputStreamReader(response_.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				System.out.println(URLDecoder.decode(result.toString()));
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log4jLogger.fatal("CALL HTTP[" + url + "] GET FAIL", e);
		} finally {

		}
		log4jLogger.info("CALL HTTP GETRP:" + result.toString());
		return result.toString();
	}

	/**
	 * - Check a string is empty
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return (value == null || value.equals(""));
	}

	public static Boolean patternMatch(String regex, String input) throws Exception {
		try {
			return Pattern.matches(regex, input);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public static String getClientIp(@Context HttpServletRequest requestContext) {
		String clientIp = "N/A";
		try {
			String header = requestContext.getHeader("X-Forwarded-For");
			clientIp = header;
			if (clientIp == null) {
				clientIp = requestContext.getRemoteHost();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return clientIp;
	}

	/**
	 * @param date
	 *            (+ or -)
	 * @param hour
	 *            (+ or -)
	 * @param min(+
	 *            or -)
	 * @param second(+
	 *            or -)
	 * @return
	 */
	public static Date caculateDateAtSpecificTime(int date, int hour, int min, int second) {
		Calendar cal = Calendar.getInstance();
		if (date != 0) {
			cal.add(Calendar.DATE, date);
		} else if (hour != 0) {
			cal.add(Calendar.HOUR, hour);
		} else if (min != 0) {
			cal.add(Calendar.MINUTE, min);
		} else if (second != 0) {
			cal.add(Calendar.SECOND, second);
		}
		return cal.getTime();
	}

	public static String bytesToHex(final byte[] bytes) {
		final StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (final byte b : bytes) {
			final String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				buf.append("0");
			}
			buf.append(hex);
		}
		return buf.toString();
	}

	public static <T> List<T> toList(String json, Class<T> clazz) {
		if (null == json) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<T>() {
		}.getType());
	}

	public static void main(String[] args) {
		System.out.println(caculateDateAtSpecificTime(1, 16, 0, 0));

		System.out.println(isEmpty("z"));
	}
}
