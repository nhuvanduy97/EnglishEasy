package nvduy1997.com.easytoeic.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MusicAlarm extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("RECEIVER", "onStartCommand: Hello Music");
        return START_NOT_STICKY;
    }
}
