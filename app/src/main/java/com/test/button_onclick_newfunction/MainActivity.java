package com.test.button_onclick_newfunction;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private int count = 0 ;

    private TextView MyLabel;
    private Button MyButton;
    private ImageView MyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLabel = findViewById(R.id.MyLabel);
        MyButton = findViewById(R.id.MyButton);
        MyImage = findViewById(R.id.MyImage);

    }


    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (Exception e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




    public void ON(View View){

        Log.e("Pressed", "ON: I am here #" + String.valueOf(count));

        MyLabel.setText("This is MyLabel # " + String.valueOf(count));

        try{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } catch (Exception e) {
            Log.i("MTH_Morgan", e.toString());
        }

        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/MTH_Logging_Data//tmp/");
// creates if doesn't exists
        Log.e("MTH_Morgan", dir.toString());

        dir.mkdirs();
// create a File

        String getdata = "Hello world "+Integer.toString(count);
        Log.i("MTH_Morgan", getdata);

        try {
            File file = new File(dir, "Example.txt");
            Log.e("MTH_Morgan", file.toString());

            if (!file.exists())
                file.createNewFile();

            //True - a new line in the file.
            //False or w/o True - erase the file and write
            FileOutputStream outStream = new FileOutputStream(file, true);


            //method 1
            outStream.write(getdata.getBytes());
            outStream.write("\r\n".getBytes());
            outStream.close();

            //method 2
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
            //bw.write(getdata);
            //bw.newLine();
            //bw.close();
            //outStream.close();

            //method 3
            //try
            //{
            //    OutputStreamWriter myOutWriter = new OutputStreamWriter(outStream);
            //    myOutWriter.append(getdata);
            //    myOutWriter.append("\r\n");
            //    myOutWriter.close();
            //    outStream.flush();
            //    outStream.close();
            //}
            //catch (IOException e)
            //{
            //    Log.e("MTH_Morgan", "File write failed: " + e.toString());
            //}

        } catch (Exception e) {
            Log.i("MTH_Morgan", e.toString());
        }
        count++;
    }

}
