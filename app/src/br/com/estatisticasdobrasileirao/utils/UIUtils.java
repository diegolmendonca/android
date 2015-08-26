package br.com.estatisticasdobrasileirao.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class UIUtils {

	public static final String getTextById(Activity a, Integer id) {
		return ((EditText) a.findViewById(id)).getText().toString();
	}
	
	public static final String getSpinnerText(Activity a, Integer id) {
		return ((Spinner) a.findViewById(id)).getSelectedItem().toString();
	}
	
	public static final String getSpinnerText(Fragment a, Integer id) {
		return ((Spinner) a.getView().findViewById(id)).getSelectedItem().toString();
	}
	
	public static void setEditViewText(Activity a, Integer textID, String value) {
		((EditText) a.findViewById(textID)).setText(value);
	}

	public static void setTextViewText(Activity a, Integer textID, String value) {
		((TextView) a.findViewById(textID)).setText(value);
	}
	
	public static void setImageResource(Activity a, Integer textID, Integer value) {
		((ImageView) a.findViewById(textID)).setImageResource(value);
	}
	
	public static void setImageResourceFragment(Fragment a, Integer textID, Integer value) {
		((ImageView) a.getView().findViewById(textID)).setImageResource(value);
	}
	
	
	public static void setTextViewText(View v, Integer textID, String value) {
		((TextView) v.findViewById(textID)).setText(value);
	}
	
	public static void setTextViewFragment(Fragment a, Integer id, String value) {
		((TextView)  a.getView().findViewById(id)).setText(value);
	}
	
	public static void setImageResource(View v, Integer textID, Integer value) {
		((ImageView) v.findViewById(textID)).setImageResource(value);
	}
	
}