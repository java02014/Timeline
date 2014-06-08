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
import android.content.res.TypedArray;
import android.database.Cursor;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class HorizontalTimeLine extends HorizontalScrollView implements CalendarDataInput {
	
	private ArrayList<CalendarEventPresentation> calendarPresentationEvents = new ArrayList<CalendarEventPresentation>();
	private int viewLength = 200;
	private int maxViewLength = Integer.MAX_VALUE;
	private long minimalNonZeroTimeDistance = Long.MAX_VALUE;
	private TimeConverter timeConverter;
	private LengthNormalizer lengthNormalizer;
	private EventViewFactory eventViewFactory;
	
	private LinearLayout scrollContainer;
	
	private boolean log = true;
	private String TAG = "HorizontalTimeLine";

	public HorizontalTimeLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		readAttributes(attrs);
		init();
	}
	
	private void readAttributes(AttributeSet attrs) {
		
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalTimeLine);
		try {
			viewLength = typedArray.getDimensionPixelSize(R.styleable.HorizontalTimeLine_minimal_event_width, 0);
			if (log) Log.d(TAG, "Event minimal length: " + viewLength);
			maxViewLength = typedArray.getDimensionPixelSize(R.styleable.HorizontalTimeLine_maximal_event_width, Integer.MAX_VALUE);
			if (log) Log.d(TAG, "Event maximal length: " + maxViewLength);
		} finally {
			typedArray.recycle();
		}		
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
		createPresentationEvents(eventList);
		lengthNormalizer = new LengthNormalizer(viewLength, minimalNonZeroTimeDistance);
		if (log) Log.d(TAG, "Events succesfully loaded");
		createViews();
		return true;
	}
	
	private void createPresentationEvents(List<CalendarEvent> eventList){
		
		long minimalTimeDistance = Long.MAX_VALUE;
		for (int i=0; i < eventList.size(); i++){

			CalendarEventPresentation calendarEventPresentation = new CalendarEventPresentation(eventList.get(i), (i == 0)? null : eventList.get(i - 1));
			calendarPresentationEvents.add(calendarEventPresentation);
			long timeAfterPrevious = calendarEventPresentation.getTimeAfterPrevious();
			if (log) Log.d("time", "time after previous: " + timeAfterPrevious);
			if (log) Log.d("time", "time converted " +  timeConverter.getConvertedTime( timeAfterPrevious ));
			if (timeAfterPrevious > 0)
				minimalTimeDistance = Math.min(minimalTimeDistance, timeConverter.getConvertedTime(timeAfterPrevious) );
		}
	}
	
	private void createViews(){
		
		eventViewFactory = new EventViewFactory(getContext(), timeConverter, lengthNormalizer, R.layout.event_layout_basic, viewLength);
		for (CalendarEventPresentation currentPresentationEvent : calendarPresentationEvents){
			scrollContainer.addView(eventViewFactory.getView(currentPresentationEvent, null /* no view recycling yet */));
		}
		this.requestLayout();
	}


}
