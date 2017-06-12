package group33.goldenlist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final int MY_NOTIFICATION_ID = 12345;

    private static final int MY_REQUEST_CODE = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
//        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context);
//        notBuilder.setAutoCancel(true)
//                  .setSmallIcon(R.mipmap.ic_launcher)
//                  .setTicker("Golden List")
//                  .setWhen(System.currentTimeMillis() + 1)
//                  .setContentTitle(intent.getExtras().getString("TIME_NAME"))
//                  .setContentText(intent.getExtras().getString("TIME_NOTE"));
//
//        Intent resultIntent = new Intent(context, Passcode.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, MY_REQUEST_CODE, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notBuilder.setContentIntent(pendingIntent);
//
//        NotificationManager notificationService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = notBuilder.build();
//        notificationService.notify(MY_NOTIFICATION_ID, notification);

        Intent iAler = new Intent(context, Alarm.class);
        iAler.putExtra("TIME_ID", intent.getExtras().getString("TIME_ID"));
        iAler.putExtra("TIME_NAME", intent.getExtras().getString("TIME_NAME"));
        iAler.putExtra("TIME_NOTE", intent.getExtras().getString("TIME_NOTE"));
        iAler.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(iAler);
    }
}