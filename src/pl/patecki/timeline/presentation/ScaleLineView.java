package pl.patecki.timeline.presentation;

import org.joda.time.DateTime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ScaleLineView extends View {
	
	private Float[] timeMarks;

	public ScaleLineView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ScaleLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ScaleLineView(Context context) {
		super(context);
	}
	
	public void setup(Float[] timeMarks){
		
		this.timeMarks = timeMarks;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.getWidth();
		canvas.getHeight();
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(5);
		canvas.drawLine(0, 0, 40, 40, paint);
		
		for (Float timeMark : timeMarks){
			
			float i = timeMark * canvas.getWidth();
			canvas.drawLine(i, 0, i, 40, paint);
		}
		
//		while (pos < 5000){
//			
//			canvas.drawLine(pos, 0, pos, 40, paint);
//			pos = (int) (pos * i * perUnitRatio);
//			i++;
//			Log.d("draw on","draw on " + pos);
//		}
	}
	
	static private class PositionCalculator{
		
		public int[] getLocations(int start, int end, int lengthDp, DateTime event){
			
			int monthOfYear = event.getMonthOfYear();
			
			
			return null;
		}
	}
}
