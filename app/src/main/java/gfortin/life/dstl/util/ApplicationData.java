package gfortin.life.dstl.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import gfortin.life.dstl.model.Item;

public class ApplicationData {

	private static final Class<?>[] modelClasses = new Class[] {
			Item.class,
	};

	/**
	 * @return the modelclasses
	 */
	public static Class<?>[] getModelclasses() {
		return modelClasses;
	}

	public static void showAlert(Activity activity, String message) {

		TextView title = new TextView(activity);
		title.setText("Error");
		title.setPadding(10, 10, 10, 10);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.WHITE);
		title.setTextSize(20);

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// builder.setTitle("Title");
		builder.setCustomTitle(title);
		// builder.setIcon(R.drawable.alert_36);

		builder.setMessage(message);

		builder.setCancelable(false);
		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

}