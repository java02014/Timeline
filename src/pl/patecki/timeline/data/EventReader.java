package pl.patecki.timeline.data;

import java.util.ArrayList;
import java.util.List;

import pl.patecki.timeline.TimelineContract;

import android.database.Cursor;

public class EventReader {

	private int eventTitle = -1;
	private int eventDescription = -1;
	private int eventUri = -1;
	private int timeOverride = -1;

	public static EventReader getIntstance() {
		return null;
	}

	private EventReader() {

	}

	private void readColumnNumbers(Cursor cursor) {

		if (cursor == null || !cursor.isClosed())
			return;
		eventTitle = cursor.getColumnIndex(TimelineContract.EVENT_TITLE);
		eventDescription = cursor.getColumnIndex(TimelineContract.EVENT_DESCRIPTION);
		eventUri = cursor.getColumnIndex(TimelineContract.EVENT_MEDIA_URI);
		timeOverride = cursor.getColumnIndex(TimelineContract.EVENT_OVERRIDE_TIME_DESRIPTION);
	}

	public List<String[]> readCursor(Cursor cursor) {

		if (cursor == null || !cursor.isClosed())
			return null;
		readColumnNumbers(cursor);
		
		List<String[]> result = new ArrayList<String[]>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String[] row = new String[4];
			row[0] = (eventTitle != -1 )? cursor.getString(eventTitle) : null;
			row[1] = (eventDescription != -1 )? cursor.getString(eventDescription) : null;
			row[2] = (eventUri != -1 )? cursor.getString(eventUri) : null;
			row[3] = (timeOverride != -1 )? cursor.getString(timeOverride) : null;
			result.add(row);
		}
		return result;
	}

}
