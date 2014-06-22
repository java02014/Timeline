package pl.patecki.timeline;

import java.util.List;

import pl.patecki.timeline.contract.CalendarEvent;
import pl.patecki.timeline.data.PresentationEventsData;
import pl.patecki.timeline.presentation.CalendarEventPresentation;
import pl.patecki.timeline.presentation.EventViewFactory;
import pl.patecki.timeline.presentation.LogarythmicTimeConverter;
import pl.patecki.timeline.presentation.OnItemClickListener;
import pl.patecki.timeline.presentation.TimeConverter;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HorizontalTimeLine extends HorizontalScrollView implements OnItemClickListener, CalendarDataInput{
	
	private PresentationEventsData presentationEventsData = new PresentationEventsData();
	private int viewLength = 200;
	private int markerSize = 10;
	private float screenDensity = 1.5f; // Use HDPi as default
	private int maxViewLength = Integer.MAX_VALUE;
	private TimeConverter timeConverter;
	private EventViewFactory eventViewFactory;
	private RelativeLayout scrollContainer;
	private LinearLayout eventsContainer;
	private LinearLayout timelineContainer;
	
	private OnItemClickListener onItemClickListener;
	
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
		timeConverter.setNormalizationData(viewLength, presentationEventsData.getMinTimeDistance(), presentationEventsData.getMaxTimeDistance(), -1);
		createViews();
		return true;
	}
	
	private void init(){
		
//		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		screenDensity = metrics.density;
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		
		scrollContainer = new RelativeLayout(getContext());
		scrollContainer.setLayoutParams(layoutParams);
		
		eventsContainer = new LinearLayout(getContext());
		eventsContainer.setLayoutParams(layoutParams);
		eventsContainer.setOrientation(LinearLayout.HORIZONTAL);
		
		LayoutParams timelineLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		timelineContainer = new LinearLayout(getContext());
		timelineContainer.setLayoutParams(timelineLayoutParams);
		timelineContainer.setOrientation(LinearLayout.HORIZONTAL);
		
		scrollContainer.addView(eventsContainer);
		scrollContainer.addView(timelineContainer);
		
		this.addView(scrollContainer);
		
		timeConverter = new LogarythmicTimeConverter();
	}

	@Override
	public boolean setData(List<CalendarEvent> eventList, boolean isSorted) {
		
		presentationEventsData.setData(eventList, isSorted);
		timeConverter.setNormalizationData(200, presentationEventsData.getMinTimeDistance(), presentationEventsData.getMaxTimeDistance(), -1);
		clearViews();
		createViews();
		return true;
	}
	
	private void clearViews(){
		eventsContainer.removeAllViews();
		timelineContainer.removeAllViews();
	}
	
	private void createViews(){
		
		eventsContainer.setPadding((int)((markerSize / 2) * screenDensity), 0, 0, 0); // Compensate event marker thickness
		eventViewFactory = new EventViewFactory(getContext(), timeConverter, R.layout.event_layout_basic, R.layout.timeline_layout_basic); 
		for (CalendarEventPresentation currentPresentationEvent : presentationEventsData.getPresentationEvents()){
			eventsContainer.addView(eventViewFactory.getEventView(currentPresentationEvent, null ));
			timelineContainer.addView(eventViewFactory.getTimelineView(currentPresentationEvent, null ));
		}
		applyArrowEnd();
		applyArrowStart();
		this.requestLayout();
	}
	
	private void applyArrowEnd(){
		
	}
	
	private void applyArrowStart(){
		
	}
	
	@Override
	public void onItemClicked(int position, CalendarEvent calendarEvent){
		
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickedListener){
		this.onItemClickListener = onItemClickedListener;
	}
	
}
