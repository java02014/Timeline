package pl.patecki.timeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.patecki.timeline.contract.CalendarEvent;
import pl.patecki.timeline.presentation.CalendarEventPresentation;
import pl.patecki.timeline.presentation.EventViewFactory;
import pl.patecki.timeline.presentation.LengthNormalizer;
import pl.patecki.timeline.presentation.LogarythmicTimeConverter;
import pl.patecki.timeline.presentation.TimeConverter;
import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class HorizontalTimeLine extends HorizontalScrollView implements CalendarDataInput {
	
	private ArrayList<CalendarEventPresentation> calendarPresentationEvents = new ArrayList<CalendarEventPresentation>();
	private int viewLength = 200;
	private TimeConverter timeConverter;
	private LengthNormalizer lengthNormalizer;
	private EventViewFactory eventViewFactory;
	
	private LinearLayout scrollContainer;
	
	private boolean log = true;
	private String TAG = "HorizontalTimeLine";

	public HorizontalTimeLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	@Override
	public boolean setData(Cursor cursor, boolean isSorted) {
		// TODO Auto-generated method stub
		// CURSOR TO ARRAY CONVERTER CLASS
		return false;
	}
	
	private void init(){
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		scrollContainer = new LinearLayout(getContext());
		scrollContainer.setLayoutParams(layoutParams);
		scrollContainer.setOrientation(LinearLayout.HORIZONTAL);
		this.addView(scrollContainer);
		timeConverter = new LogarythmicTimeConverter();
	}

	@Override
	public boolean setData(List<CalendarEvent> eventList, boolean isSorted) {
		
		if (!isSorted)
	        Collections.sort(eventList);
		
		int iCurrent = 0;
		long minimalTimeDistance = Long.MAX_VALUE;
		for (CalendarEvent calendarEvent : eventList){

			CalendarEventPresentation calendarEventPresentation = new CalendarEventPresentation(calendarEvent, (iCurrent == 0)? null : eventList.get(iCurrent - 1));
			calendarPresentationEvents.add(calendarEventPresentation);
			long timeAfterPrevious = calendarEventPresentation.getTimeAfterPrevious();
			Log.d("time", "time after previous: " + timeAfterPrevious);
			Log.d("time", "time converted " +  timeConverter.getConvertedTime( /*Math.min(minimalTimeDistance, timeAfterPrevious)*/ timeAfterPrevious ));
			if (timeAfterPrevious > 0)
				minimalTimeDistance = Math.min(minimalTimeDistance, timeConverter.getConvertedTime(timeAfterPrevious) );
			iCurrent++;
			
		}
		lengthNormalizer = new LengthNormalizer(viewLength, minimalTimeDistance);
		
		for(CalendarEventPresentation cep : calendarPresentationEvents)
			Log.d(TAG, "length: " + lengthNormalizer.getNormalizedSize( timeConverter.getConvertedTime( cep.getTimeAfterPrevious()) ));
		
		if (log)
			Log.d(TAG, "Events succesfully loaded");
		
		populate();
		return true;
	}
	
	private void populate(){
		
		eventViewFactory = new EventViewFactory(getContext(), timeConverter, lengthNormalizer, R.layout.event_layout_basic, viewLength);
		
		for (CalendarEventPresentation currentPresentationEvent : calendarPresentationEvents){
			Log.d("asd","asd");
			scrollContainer.addView(eventViewFactory.getView(currentPresentationEvent, null /* no view recycling yet */));
		}
		this.requestLayout();
	}


}
