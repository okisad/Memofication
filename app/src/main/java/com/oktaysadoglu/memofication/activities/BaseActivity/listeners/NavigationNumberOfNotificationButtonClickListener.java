package com.oktaysadoglu.memofication.activities.baseActivity.listeners;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.preferences.NotificationPreferences;
import com.oktaysadoglu.memofication.tools.DialogTools;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class NavigationNumberOfNotificationButtonClickListener implements View.OnClickListener{

    private BaseActivity baseActivity;

    public NavigationNumberOfNotificationButtonClickListener(BaseActivity baseActivity) {
        setBaseActivity(baseActivity);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity(), R.style.AppDialogTheme);

        View view = LayoutInflater.from(getBaseActivity()).inflate(R.layout.navigation_change_number_of_notification_dialog, null);

        NumberPicker picker = (NumberPicker) view.findViewById(R.id.navigation_change_number_of_notification_dialog_number_picker);

        setPickerValues(picker,0,10);

        setDisplayView(picker);

        AlertDialog alertDialog = builder
                .setView(view)
                .setTitle("Number Of Notifications")
                .setPositiveButton("ok", new DrawerNotificationNumberOkayClickListener(getBaseActivity(),picker))
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();


        alertDialog.show();

    }

    private void setPickerValues(NumberPicker numberPicker,int min,int max){

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        numberPicker.setValue(NotificationPreferences.getNotificationNumber(getBaseActivity()));

    }

    private void setDisplayView(NumberPicker numberPicker){

        DialogTools dialogTools = new DialogTools();

        dialogTools.setNumberPickerTextColor(numberPicker, ContextCompat.getColor(getBaseActivity(), R.color.grey_900));
        dialogTools.setDividerColor(numberPicker, ContextCompat.getColor(getBaseActivity(), R.color.grey_700));

    }
}
