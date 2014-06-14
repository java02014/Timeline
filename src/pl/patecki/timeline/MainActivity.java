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
		
		List<CalendarEvent> list = new ArrayList<CalendarEvent>();
		Calendar event1 = Calendar.getInstance();
		event1.set(Calendar.DAY_OF_MONTH, 3);
		Calendar event2 = Calendar.getInstance();
		event2.set(Calendar.DAY_OF_MONTH, 5);
		Calendar event3 = Calendar.getInstance();
		event3.set(Calendar.MONTH, 7);
		Calendar event4 = Calendar.getInstance();
		event4.set(Calendar.MONTH, 10);
		event4.set(Calendar.YEAR, 2015);
		Calendar event5 = Calendar.getInstance();
		event5.set(Calendar.MONTH, 11);
		event5.set(Calendar.YEAR, 2015);
		list.add(new CalendarEvent(new DateTime(event1), "National holiday", null, null));
		list.add(new CalendarEvent(new DateTime(event2), "Polsih HOliday", null, null));
		list.add(new CalendarEvent(new DateTime(event3), "Krakow holiday", null, null));
		list.add(new CalendarEvent(new DateTime(event4), "Got drunk", null, null));
		list.add(new CalendarEvent(new DateTime(event5), "Party hard", null, null));
		Collections.sort(list);

		horizontalTimeLine.setData(list, true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
