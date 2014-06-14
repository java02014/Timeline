package pl.patecki.timeline;

import java.util.List;

import pl.patecki.timeline.contract.CalendarEvent;
import pl.patecki.timeline.data.PresentationEventsData;
import pl.patecki.timeline.presentation.CalendarEventPresentation;
import pl.patecki.timeline.presentation.EventViewFactory;
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
	
	private PresentationEventsData presentationEventsData = new PresentationEventsData();
	private int viewLength = 200;
	private int maxViewLength = Integer.MAX_VALUE;
	private TimeConverter timeConverter;
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
		
		presentationEventsData.setData(cursor, isSorted);
		timeConverter.setNormalizationData(200, presentationEventsData.getMinTimeDistance(), presentationEventsData.getMaxTimeDistance(), -1);
		createViews();
		return true;
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
		
		presentationEventsData.setData(eventList, isSorted);
		timeConverter.setNormalizationData(200, presentationEventsData.getMinTimeDistance(), presentationEventsData.getMaxTimeDistance(), -1);
		createViews();
		return true;
	}
	
	private void createViews(){
		
		eventViewFactory = new EventViewFactory(getContext(), timeConverter, R.layout.event_layout_basic); 
		for (CalendarEventPresentation currentPresentationEvent : presentationEventsData.getPresentationEvents()){
			scrollContainer.addView(eventViewFactory.getView(currentPresentationEvent, null /* no view recycling yet */));
		}
		this.requestLayout();
	}


}
