package info.pratham.speechtotext;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by PEF-2 on 29/10/2015.
 */
public class BackupDatabase {

    public static void backup(Context mContext) {
        try {


            File sd = new File(Environment.getExternalStorageDirectory().toString() + "/.PrathamSTT/STTContent/");//Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            String currentDBPath = "//data//info.pratham.speechtotext//databases//" + "PrathamSTTDB.db";
            String backupDBPath = "PrathamSTTDB.db";
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd, backupDBPath);
            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            throw new Error("Copying Failed");
        }
    }

}
