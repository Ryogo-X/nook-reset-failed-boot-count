package net.streletsky.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OneTimeInitializerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, OneTimeInitializerService.class));
    }
}