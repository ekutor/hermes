package com.co.iatech.crm.sugarmovil.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activities.tasks.GatewayPublisher;
import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTaskPublisher;
import com.co.iatech.crm.sugarmovil.activities.tasks.IObserverTask;
import com.co.iatech.crm.sugarmovil.activities.tasks.ITaskPublisher;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.CalendarModule;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Meeting;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.software.shell.fab.ActionButton;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class CalendarFragment extends Fragment implements IMovilModuleActions, IObserverTask,
CalendarModule, OnClickListener {

	private GlobalClass mGlobalVariable;
	
	/**
	 * UI References.
	 */
	private View rootView;
	private TextView mMainTextView;
	private SearchView mMainSearchView;

	private ActionButton actionButton;
	private ITaskPublisher taskPublisher;

	private TextView currentMonth;
	private Button selectedDayMonthYearButton;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellAdapter adapter;
	private Calendar _calendar;
	@SuppressLint("NewApi")
	private int month, year;
	@SuppressWarnings("unused")
	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";
	
	private Map<Integer,List<Meeting>> metingsMonth;


	public CalendarFragment() {

	}

	public static CalendarFragment newInstance() {
		CalendarFragment fragment = new CalendarFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			// Parametros
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
		try {

			// Variable Global
			mGlobalVariable = (GlobalClass) getActivity().getApplicationContext();
			mGlobalVariable.setSelectedItem(10);

			// Main Toolbar
			mMainTextView = ((MainActivity) getActivity()).getMainTextView();
			mMainTextView.setText("AGENDA");
			mMainTextView.setVisibility(View.VISIBLE);
			mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
			mMainSearchView.setVisibility(View.INVISIBLE);

			_calendar = Calendar.getInstance();
			month = _calendar.get(Calendar.MONTH);
			year = _calendar.get(Calendar.YEAR);

			selectedDayMonthYearButton = (Button) rootView.findViewById(R.id.selectedDayMonthYear);
			selectedDayMonthYearButton.setText("Selected: ");

			prevMonth = (ImageView) rootView.findViewById(R.id.prevMonth);
			prevMonth.setOnClickListener(this);

			currentMonth = (TextView) rootView.findViewById(R.id.currentMonth);
			currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));

			nextMonth = (ImageView) rootView.findViewById(R.id.nextMonth);
			nextMonth.setOnClickListener(this);
			
			
			calendarView = (GridView) rootView.findViewById(R.id.grid_calendar);

			adapter = new GridCellAdapter(this.getActivity().getApplicationContext(), R.id.calendar_day_gridcell, _calendar , metingsMonth);
			adapter.notifyDataSetChanged();
			calendarView.setAdapter(adapter);
			
			LinearLayout layoutDays = (LinearLayout) rootView.findViewById(R.id.grid_days_layout);
			
			LayoutParams lparams = new LayoutParams(
					150, LayoutParams.WRAP_CONTENT);
			lparams.gravity = Gravity.CENTER_HORIZONTAL;
			
			String[] days = {"    D","    L","    M","    X","    J","    V","    S"};
			for(String d : days){
				TextView tv = new TextView(this.getActivity().getApplicationContext());
				tv.setLayoutParams(lparams);
				tv.setText(d);
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
				tv.setTextColor(Color.DKGRAY);
				tv.setMinWidth(100);
				layoutDays.addView(tv);
			}

			this.applyActions();
			
			GatewayPublisher.getInstance().register(this);
			
			this.chargeViewInfo();
			
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getActivity());
		}
		return rootView;
	}

	@Override
	public void chargeViewInfo() {
		try {
        	GenericTaskPublisher tpCalendar = new GenericTaskPublisher(getActivity(),MODULE, 
        			TypeInfoServer.getMeeting, "Cargando Agenda...");
        
        	String[] params = { "currentUser", ControlConnection.userId , "dateStart" , Utils.convertTimetoStringBackend(_calendar).toString() };
        	
        	tpCalendar.execute(params);
        	
        } catch (Exception e) {
            Message.showShortExt(Utils.errorToString(e), getActivity());
        }
	}

	@Override
	public boolean chargeIdPreviousModule() {
		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		mMainSearchView.clearFocus();
		try {
			mMainSearchView.setIconified(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public ActionButton getActionButton() {
		return actionButton;
	}

	@Override
	public ImageButton getEditButton() {
		return null;
	}

	@Override
	public Modules getModule() {
		return MODULE;
	}

	@Override
	public String getAssignedUser() {
		return "";
	}

	@Override
	public Parcelable getBean() {
		return null;
	}

	public void applyActions() {
		actionButton = (ActionButton) rootView.findViewById(R.id.action_button);
		GlobalClass gc = (GlobalClass) getActivity().getApplicationContext();
		ActionsStrategy.definePermittedActions(this, this.getActivity(), gc);
	}

	/**
	 * 
	 * @param calendar
	 * 
	 */
	private void setGridCellAdapterToDate(Calendar c) {
		adapter = new GridCellAdapter(this.getActivity().getApplicationContext(), R.id.calendar_day_gridcell, c,metingsMonth);
		currentMonth.setText(DateFormat.format(dateTemplate, c.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	
	private void setGridCellAdapterToDate(int month, int year) {
		
		setGridCellAdapterToDate(_calendar);
	}

	@Override
	public void onClick(View v) {
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			
			//setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}

			//setGridCellAdapterToDate(month, year);
		}
		_calendar.set(year, month, _calendar.get(Calendar.DATE));
		this.chargeViewInfo();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	// Inner Class
	public class GridCellAdapter extends BaseAdapter implements OnClickListener {
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<String> list;
		private static final int DAY_OFFSET = 1;
		private final String[] weekdays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		private final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		private int daysInMonth;
		private int currentDayOfMonth;
		private int currentWeekDay;	
		private TextView gridcell;
		private RelativeLayout item;
		private TextView num_events_per_day;
		private final Map<Integer, List<Meeting>> eventsPerMonthMap;
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

		// Days in Current Month
		public GridCellAdapter(Context context, int textViewResourceId, Calendar c, Map<Integer, List<Meeting>> eventsPerMonth) {
			super();
			this._context = context;
			this.list = new ArrayList<String>();

			setCurrentDayOfMonth(c.get(Calendar.DAY_OF_MONTH));
			setCurrentWeekDay(c.get(Calendar.DAY_OF_WEEK));

			// Print Month
			printMonth(c.get(Calendar.MONTH), c.get(Calendar.YEAR));

			// Find Number of Events
			eventsPerMonthMap = eventsPerMonth;
		}

		private String getMonthAsString(int i) {
			return months[i];
		}

		private String getWeekDayAsString(int i) {
			return weekdays[i];
		}

		private int getNumberOfDaysOfMonth(int i) {
			return daysOfMonth[i];
		}

		public String getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		/**
		 * Prints Month
		 * 
		 * @param mm
		 * @param yy
		 */
		private void printMonth(int mm, int yy) {
			Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
			int trailingSpaces = 0;
			int daysInPrevMonth = 0;
			int prevMonth = 0;
			int prevYear = 0;
			int nextMonth = 0;
			int nextYear = 0;

			int currentMonth = mm ;
			String currentMonthName = getMonthAsString(currentMonth);
			daysInMonth = getNumberOfDaysOfMonth(currentMonth);

			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

			if (currentMonth == 11) {
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;

			} else if (currentMonth == 0) {
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;

			} else {
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);

			}

			int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			trailingSpaces = currentWeekDay;

			if (cal.isLeapYear(cal.get(Calendar.YEAR)))
				if (mm == 2)
					++daysInMonth;
				else if (mm == 3)
					++daysInPrevMonth;

			// Trailing Month days
			for (int i = 0; i < trailingSpaces; i++) {

				list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-"
						+ getMonthAsString(prevMonth) + "-" + prevYear);
			}

			// Current Month Days
			for (int i = 1; i <= daysInMonth; i++) {
				if (i == getCurrentDayOfMonth()) {
					list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
				} else {
					list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
				}
			}

			// Leading Month days
			for (int i = 0; i < list.size() % 7; i++) {

				list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
			}
		}


		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.screen_gridcell, parent, false);
			}

			// Get a reference to the Day gridcell
			gridcell = (TextView) row.findViewById(R.id.calendar_day_gridcell);
			// gridcell.setOnClickListener(this);
			item = (RelativeLayout) row.findViewById(R.id.calendar_day_layout);
			item.setOnClickListener(this);
			// ACCOUNT FOR SPACING

			String[] day_color = list.get(position).split("-");
			String theday = day_color[0];
			if(theday != null){
				theday = theday.trim();
			}
			String themonth = day_color[2];
			String theyear = day_color[3];
			try{
			num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);

			if ( eventsPerMonthMap != null && !eventsPerMonthMap.isEmpty() ) {
				int day = Integer.parseInt(theday);
				if (eventsPerMonthMap.containsKey(day) && !day_color[1].equals("GREY")) {
					int numEvents = eventsPerMonthMap.get(day).size();
					num_events_per_day.setText(String.valueOf(numEvents));
					LinearLayout layoutBackground = (LinearLayout) row.findViewById(R.id.layoutBackground);
					layoutBackground.setVisibility(View.VISIBLE);
				}
			}
			} catch (Exception e) {
				Message.showShortExt(Utils.errorToString(e), getActivity());
				e.printStackTrace();
			}
			// Set the Day GridCell
			gridcell.setText(theday);
			item.setTag(theday + "-" + themonth + "-" + theyear);

			if (day_color[1].equals("GREY")) {
				gridcell.setTextColor(getResources().getColor(R.color.lightgray));
			}
			if (day_color[1].equals("WHITE")) {
				gridcell.setTextColor(getResources().getColor(R.color.black));
			}
			if (day_color[1].equals("BLUE")) {
				gridcell.setTextColor(getResources().getColor(R.color.navy));
			}
			return row;
		}

		@Override
		public void onClick(View view) {
			try {
				String date_month_year = (String) view.getTag();
				selectedDayMonthYearButton.setText("Selected: " + date_month_year);
				ActivitiesMediator.getInstance().showActivity(getActivity(),MODULE, new ActivityBeanCommunicator(date_month_year, ""));
		         
				// Date parsedDate = dateFormatter.parse(date_month_year);

			} catch (Exception e) {
				Message.showShortExt(Utils.errorToString(e), getActivity());
				e.printStackTrace();
			}
		}

		public int getCurrentDayOfMonth() {
			return currentDayOfMonth;
		}

		private void setCurrentDayOfMonth(int currentDayOfMonth) {
			this.currentDayOfMonth = currentDayOfMonth;
		}

		public void setCurrentWeekDay(int currentWeekDay) {
			this.currentWeekDay = currentWeekDay;
		}

		public int getCurrentWeekDay() {
			return currentWeekDay;
		}
	}

	@Override
	public void getInfoFromMediator() {
		
	}

	@Override
	public void addInfo(String serverResp) {
		// TODO Auto-generated method stub

	}


	@Override
	public void update() {
		try{
			ActivityBeanCommunicator response = taskPublisher.getInfo();
			if(response.getModule() != MODULE){
			 return;
			}
			DataManager.getInstance().meetings.clear();
			metingsMonth = new HashMap<Integer, List<Meeting>>();
	        
			JSONObject jObj = new JSONObject(response.getAdditionalInfo());
	
	        JSONArray jArr = jObj.getJSONArray("results");
	        for (int i = 0; i < jArr.length(); i++) {
	            JSONObject obj = jArr.getJSONObject(i);
	            Meeting m = new Meeting(obj);
	            DataManager.getInstance().meetings.add(m);
	            int day = Utils.getDay(m.getDateStart());
	          
	            if(metingsMonth.containsKey(day)){
	            	metingsMonth.get(day).add(m);
	            }else{
	            	List<Meeting> lm = new ArrayList<Meeting>();
	            	lm.add(m);
	            	metingsMonth.put(day, lm);
	            }
	           
	        }
	        
	        setGridCellAdapterToDate(_calendar);
	        
	        
		} catch (Exception e) {
			Message.showFinalMessage(this.getFragmentManager(), Utils.errorToString(e), this.getActivity(), MODULE);
		}
	}

	@Override
	public void defineTasktoListen(ITaskPublisher publisher) {
		this.taskPublisher = publisher;
	}
}
