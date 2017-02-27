package life.memy.json;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import life.memy.exception.ConversionException;

public class DateParam {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat iso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
	private Date date;
	private String isodate;

	public DateParam(String dateStr) throws ConversionException {
		if (dateStr == null) {
			this.date = null;
			return;
		}

		try {
			date = df.parse(dateStr);
			isodate = iso.format(date);
		} catch (ParseException e) {
			throw new ConversionException(e);
		}
	}

	public Date getDate() {
		return date;
	}
	public String getIsoDate() {
		return isodate;
	}

}
