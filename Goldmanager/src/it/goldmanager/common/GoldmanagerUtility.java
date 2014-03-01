package it.goldmanager.common;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GoldmanagerUtility {

	/**
	 * Ritorna il giorno ordierno.
	 * @return data odierna.
	 */
	public static Date today() {
		Calendar c1 = Calendar.getInstance(Locale.ITALY);
		c1.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		java.util.Date d = c1.getTime();
		return d;
	}

	public static Date getDataServerSommaPeriodo(int calendar, int somma) {
		Calendar dataServer = Calendar.getInstance();
		dataServer.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		dataServer.add(calendar, somma);
		java.util.Date d = dataServer.getTime();
		return d;
	}

	/**
	 * Trasforma un oggetto se null in una stringa vuota.
	 * @param o oggetto da trasformare.
	 * @return oggetto trasformato.
	 */
	public static String nullStr(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	/**
	 * Trasforma un BigDecimal se null in un bigdecimal 0.
	 * @param o BigDecimal da trasformare.
	 * @return BigDecimal trasformato.
	 */
	public static BigDecimal nullBd(BigDecimal o) {
		if (o == null) {
			return new BigDecimal(0);
		} else {
			return o;
		}
	}

	/**
	 * Trasforma un oggetto se null in una stringa con autorizzazione di default.
	 * @param o oggetto da trasformare.
	 * @return oggetto trasformato.
	 */
	public static String defaultAuth(Object o, String defauth) {
		if (o == null) {
			return defauth;
		} else {
			return o.toString();
		}
	}

	/**
	 * Trasforma un oggetto se null in una stringa con il colore di default.
	 * @param o oggetto da trasformare.
	 * @return oggetto trasformato.
	 */
	public static String defaultColor(Object o, String defCol) {
		if (o == null) {
			return defCol;
		} else {
			return o.toString();
		}
	}

	/**
	 * Aggiunge spazi bianchi alla fine della string.
	 * @param val string da valutare;
	 * @param c numero di spazi da aggiungere.
	 * @return string trasformata con spazi.
	 */
	public static String fillStringAtBlank(String val, int c) {
		if (val == null)
			val = "";
		StringBuffer buffer = new StringBuffer(val);
		while (buffer.length() < c)
			buffer.append(" ");
		return buffer.toString();
	}

	/**
	 * Aggiunge zero alla sinistra della string.
	 * @param inStr string da completare con zero;
	 * @param numChar numeri di zero da qggiungere.
	 * @return string trasformata.
	 */
	public static String fillZeroLeft(String inStr, int numChar) {
		String outStr = "";
		if (inStr == null)
			return null;
		int inStrLength = inStr.length();
		if (inStrLength >= numChar)
			return inStr;
		for (int i = 0; i < (numChar - inStrLength); i++)
			outStr += "0";
		outStr += inStr;
		return outStr;
	}

	/**
	 * Converte la data nel formato voluto
	 * @param _date data da convertire;
	 * @return string convertita.
	 */
	public static String convertDateToString(Date _date, String _format) {
		SimpleDateFormat sdf = new SimpleDateFormat(_format, Locale.ITALY);
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		String convert = sdf.format(_date);
		return convert;
	}

	/**
	 * Converte la data nel formato voluto
	 * @param _date data da convertire;
	 * @return string convertita.
	 */
	public static Date convertStringToDate(String _date, String _format) {
		SimpleDateFormat sdf = new SimpleDateFormat(_format, Locale.ITALY);
		Date convert = null;
		try {
			convert = sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convert;
	}

	public static String toUppercaseNotNull(String val) {
		if (val != null)
			return val.toUpperCase();
		return val;
	}

	public static String convertSesso(boolean _sesso) {
		if (_sesso) {
			return "1";
		}
		return "2";
	}

	public static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(0, 2), 16), Integer.valueOf(colorStr.substring(2, 4), 16), Integer.valueOf(colorStr.substring(4, 6), 16));
	}

	public static String Rgb2Hex(Color colorRgb) {
		return Integer.toHexString(colorRgb.getRGB() & 0x00ffffff);
	}

	// dai carati e il prezzo di base calcola il prezzo al grammo
	public static BigDecimal percentageForGold(BigDecimal base, BigDecimal pct) {
		BigDecimal bd = pct.divide(new BigDecimal(10));
		return bd.multiply(base).divide(new BigDecimal(100));
	}

	// dati i grammi totali e i carati restituisce quanti sono i grammi reali a 999
	public static BigDecimal percentageForGold(String _pesoNetto, String _carati) {
		BigDecimal vg = ((new BigDecimal(_pesoNetto).multiply(new BigDecimal(_carati)).divide(new BigDecimal(1000))));
		return vg;
	}
}