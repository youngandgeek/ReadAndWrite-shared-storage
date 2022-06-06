package com.example.maharatech1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {



    TextView nameDisplay,emailDisplay;
    Button backBtn,writeSharedBtn,readSharedBtn,writeInternalBtn,readInternalBtn,closeBtn;
    Intent incomingIntent;
    //to save and retrieve data from shared preferences data storage
    SharedPreferences preferences;
    //to save data from app in file in internal storage
    FileOutputStream fos;
    /**A data output stream lets an application write primitive Java data types to an output stream in a portable way.
    *An application can then use a data input stream to read the data back in
     **/
    DataOutputStream dos;
    //to retrieve data from file to app
    FileInputStream fis;
    DataInputStream dis;
    //file name
    private static final String FILE_NAME = "PHONE" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        incomingIntent=getIntent();
    nameDisplay=(TextView) findViewById(R.id.name_display) ;
    emailDisplay=(TextView) findViewById(R.id.email_display);
    nameDisplay.setText(incomingIntent.getStringExtra(MainActivity.NAME));
    emailDisplay.setText(incomingIntent.getStringExtra(MainActivity.EMAIL));

    writeSharedBtn=(Button)findViewById(R.id.writeShared_btn);
    writeSharedBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//shared preferences data storage saves data in xml files .
            //1-obj of Shared Preferences 2- save data by editor
            preferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= preferences.edit();
            //there are also putBoolean and putInt methods
            editor.putString(MainActivity.NAME,nameDisplay.getText().toString());
            editor.putString(MainActivity.EMAIL,emailDisplay.getText().toString());
            editor.commit();
            nameDisplay.setText("");
            emailDisplay.setText("");

        }
    });
    readSharedBtn=(Button)findViewById(R.id.readShared_btn);
readSharedBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //read data from shared preferences data storage
        preferences =getPreferences(Context.MODE_PRIVATE);
        //N/A default value if it's not found
        nameDisplay.setText(preferences.getString(MainActivity.NAME,"N/A"));
        emailDisplay.setText(preferences.getString(MainActivity.EMAIL,"N/A"));
    }
});


//write,save data in internal data storage as files from app (output from app to storage)

        writeInternalBtn=(Button)findViewById(R.id.writeInternal_btn);
        writeInternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // file stream reference is low level so we use dataoutput stream to store high level data like string,int
                try {
                    fos = openFileOutput(MainActivity2.FILE_NAME, Context.MODE_PRIVATE);
                    dos = new DataOutputStream(fos);
                    //utf for strings
                    dos.writeUTF(nameDisplay.getText().toString());
                    dos.writeUTF(emailDisplay.getText().toString());
                    //make sure you close the connection to file,reduces ram
                    dos.close();
                    fos.close();
                    nameDisplay.setText("");
                    emailDisplay.setText("");
                }
                 catch(FileNotFoundException e){
                        e.printStackTrace();

                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }

        });
//read,retrieve data from storage to app (input to app)

      readInternalBtn=(Button)findViewById(R.id.readInternal_btn);
      readInternalBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              try {
                  fis=openFileInput(MainActivity2.FILE_NAME);
                  dis=new DataInputStream(fis);
                  nameDisplay.setText(dis.readUTF());
                  emailDisplay.setText(dis.readUTF());

              }
              catch (FileNotFoundException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }



          }
      });


backBtn=(Button) findViewById(R.id.back_btn);
backBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent mainPage=new Intent(MainActivity2.this,MainActivity.class);
        startActivity(mainPage);
    }
});
    }


}