package it.goldmanager.common;

import org.apache.log4j.Logger;

public class GoldmanagerLogger {

	public static void debug(Class<?> cl, String error, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.debug(parseUserInfo(userInfo) + error);
		} catch (Throwable e) {
		}
	}

	public static void fatal(Class<?> cl, String error, Exception e, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.fatal(parseUserInfo(userInfo) + error, e);
		} catch (Throwable t) {
		}
	}

	public static void fatal(Class<?> cl, String error, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.fatal(parseUserInfo(userInfo) + error);
		} catch (Throwable t) {
		}
	}

	public static void error(Class<?> cl, String error, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.error(parseUserInfo(userInfo) + error);
		} catch (Throwable e) {
		}
	}

	public static void error(Class<?> cl, String error, Exception e, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.error(parseUserInfo(userInfo) + error, e);
		} catch (Throwable t) {
		}
	}

	public static void info(Class<?> cl, String error, String userInfo) {
		try {
			Logger logger = Logger.getLogger(cl);
			logger.info(parseUserInfo(userInfo) + error);
		} catch (Throwable e) {
		}
	}

	public static String parseUserInfo(String userInfo) {
		return ((userInfo == null || userInfo.equals("")) ? "" : (" < " + userInfo + " > "));
	}
}
