package CommonLibs.utils;

import java.util.Date;

public class DateUtils {
	
	public static String getcurrentTime() {
		
		Date date = new Date();
		
		return String.valueOf(date.getTime());
		
	}

}
