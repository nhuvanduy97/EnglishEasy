package nvduy1997.com.easytoeic.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.AlarmReceiver;
import nvduy1997.com.easytoeic.activity.MainActivity;

public class SettingFragment extends Fragment {

    private Switch swReminder;
    private LinearLayout lnReminder;
    private Calendar calendar;
    private TimePicker tpTimeReminder;
    private Button btnTimeReminder;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private CheckBox cbTimeReminder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Setting");
        swReminder = view.findViewById(R.id.swReminder);
        lnReminder = view.findViewById(R.id.lnTimeReminder);
        tpTimeReminder = view.findViewById(R.id.tpTimeReminder);
        btnTimeReminder = view.findViewById(R.id.btnTimeReminder);
        cbTimeReminder = view.findViewById(R.id.cbTimeReminder);

        swReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lnReminder.setVisibility(View.VISIBLE);
                    btnTimeReminder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alarmClock();
                            String time = tpTimeReminder.getHour() + ":" + tpTimeReminder.getMinute();
                            Toast.makeText(getActivity(), "Bạn đã đặt lời nhắc vào " + time, Toast.LENGTH_SHORT).show();

                        }
                    });

                } else {
                    lnReminder.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
    }

    private void alarmClock() {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, tpTimeReminder.getHour());
        calendar.set(Calendar.MINUTE, tpTimeReminder.getMinute());
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        if (cbTimeReminder.isChecked()) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

}
