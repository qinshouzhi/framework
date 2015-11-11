package com.newtouch.lion.util;



import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Description: String util class.
 * 
 * @author 银时 yinshi.nc@taobao.com
 */
public class DateUtil {

	
	 
	
	 
	
	
	
	

	public static long getTimeMillisToAfterDaysHour( int days, int hourOfTomorrow ) throws Exception {

		if ( 0 == hourOfTomorrow )
			hourOfTomorrow = 2;

		Calendar calendar = Calendar.getInstance();

		int yearOfToday = calendar.get( Calendar.YEAR );
		int monthOfToday = calendar.get( Calendar.MONTH ) + 1;
		int dayOfToday = calendar.get( Calendar.DAY_OF_MONTH );

		calendar.set( Calendar.DAY_OF_MONTH, dayOfToday + days );
		if ( 31 == dayOfToday && days >=1 ) {
			calendar.set( Calendar.MONTH, monthOfToday + 1 );
		}
		if ( 12 == monthOfToday && 31 == dayOfToday && days>=1 ) {
			calendar.set( Calendar.YEAR, yearOfToday + 1 );
		}

		int dayOfTomorrow  = calendar.get( Calendar.DAY_OF_MONTH );
		int monthOfTomorrow  = calendar.get( Calendar.MONTH );
		int yearOfTomorrow = calendar.get( Calendar.YEAR );

		Calendar calendarOfTomorrow = new GregorianCalendar( yearOfTomorrow, monthOfTomorrow, dayOfTomorrow, hourOfTomorrow, 0, 0 );
		long startTimeMillis = System.currentTimeMillis();
		
		
		long timeMillisToAfterDaysHour = calendarOfTomorrow.getTime().getTime() - startTimeMillis; 
		
		if( 0 > timeMillisToAfterDaysHour )
			throw new Exception( "时间差为负数，可能设置有误" );
		
		return timeMillisToAfterDaysHour;

	}
	
	
	public static long getTimeMillisToTodayHour( int hourOfTomorrow ) throws Exception {
		return DateUtil.getTimeMillisToAfterDaysHour( 0, hourOfTomorrow );
	}
	
	
	
	
	
	
	
	
	
	
	

}
