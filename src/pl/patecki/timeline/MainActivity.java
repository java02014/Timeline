package pl.patecki.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.Years;

import pl.patecki.timeline.contract.CalendarEvent;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HorizontalTimeLine horizontalTimeLine = (HorizontalTimeLine) findViewById(R.id.horizontal);
		
		Calendar minEvent = Calendar.getInstance();
		minEvent.set(Calendar.YEAR, 20000);
		
		Calendar maxEvent = Calendar.getInstance();
		maxEvent.set(Calendar.YEAR, -20000);
		DateTime datetime = DateTime.now();
//		DateTime datetime2 = DateTime.now();
//		datetime2.minusYears(20);
		Calendar c = Calendar.getInstance();
		c.set(1998, 3, 3);
		DateTime dt2 = new DateTime(c);
		Period p = new Period(dt2, datetime);
		Log.d("period","period " + p.getYears());
		
		Log.d("period","period " + Years.yearsBetween(datetime, dt2));
		
		List<CalendarEvent> list = new ArrayList<CalendarEvent>();
		Calendar event1 = Calendar.getInstance();
		event1.set(Calendar.DAY_OF_MONTH, 3);
		Calendar event2 = Calendar.getInstance();
		event2.set(Calendar.DAY_OF_MONTH, 5);
		Calendar event3 = Calendar.getInstance();
		event3.set(Calendar.MONTH, 7);
		list.add(new CalendarEvent(event1, "National holiday", null, null));
		list.add(new CalendarEvent(event2, "Polsih HOliday", null, null));
		list.add(new CalendarEvent(event3, "Krakow holiday", null, null));
		Collections.sort(list);

		horizontalTimeLine.setData(list, true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
