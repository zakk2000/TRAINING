package com.celizion.app.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 * <pre>
 * com.celizion.app.util
 * CommonUtilities.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 12. 21.
 */
public class CommonUtilities {

//	private static final Logger logger = LoggerFactory.getLogger(CommonUtilities.class);
	
	public static String getCurrentDateString(String fmt, Locale loc) {
		
		return new SimpleDateFormat(fmt, loc).format(new Date());
	
	}
	
	public static String getDayOfWeek() {
		
		LocalDate today = LocalDate.now();
		DayOfWeek dow = today.getDayOfWeek();
		
		return dow.getDisplayName(TextStyle.SHORT, Locale.getDefault());
	
	}
	
	public static double setDecimalRoundOff(double d, int newScale) {
        
        return BigDecimal.valueOf(d).setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    
    }

}
