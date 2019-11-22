package nvduy1997.com.easytoeic.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("RECEIVER", "onReceive: Hello World");

        Intent myIntent = new Intent(context, MusicAlarm.class);
        context.startService(myIntent);
    }
}
