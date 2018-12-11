package net.streletsky.startup;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class OneTimeInitializerService extends IntentService {
    public OneTimeInitializerService() {
        super("One Time Initializer Service");
    }

    public void onCreate() {
        super.onCreate();

        resetFailedBootCount();
    }

    @Override
    protected void onHandleIntent(Intent intent) { }

    void resetFailedBootCount() {
        try {
            Process proc = Runtime.getRuntime().exec("busybox dd if=/dev/zero of=/dev/block/mmcblk0p8 bs=512 count=1");
            DataOutputStream os = new DataOutputStream(proc.getOutputStream());
            BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            os.writeBytes("\nexit\n");
            os.flush();
            proc.waitFor();

            in.close();
            os.close();
            err.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
