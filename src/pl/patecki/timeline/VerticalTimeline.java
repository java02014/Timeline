package pl.patecki.timeline;

import java.util.List;

import pl.patecki.timeline.contract.CalendarEvent;
import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.ListView;

public class VerticalTimeline extends ListView implements CalendarDataInput{

	public VerticalTimeline(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public VerticalTimeline(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public VerticalTimeline(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		
		this.setDivider(null);
	}

	@Override
	public boolean setData(Cursor cursor, boolean isSorted) {
		return false;
	}

	@Override
	public boolean setData(List<CalendarEvent> eventList, boolean isSorted) {
		
		return false;
	}

}
