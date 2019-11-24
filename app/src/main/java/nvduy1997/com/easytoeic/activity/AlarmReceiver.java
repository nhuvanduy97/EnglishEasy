package nvduy1997.com.easytoeic.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import nvduy1997.com.easytoeic.R;

public class AlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("RECEIVER", "onReceive: Hello World");

        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "Time Reminder";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("App Toeic PTT")
                .setContentText("Hãy trinh phục kì thi bằng cách mở App Toeic PTT nhé !")
                .setSmallIcon(R.drawable.toeic1)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pIntent)
                .setSound(sound)
                .build();

        NotificationManager mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNM.createNotificationChannel(mChannel);

        mNM.notify(notifyID, notification);


    }
}
