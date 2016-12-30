package com.lizhe.test;

import java.util.regex.Pattern;

import android.opengl.ETC1;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        // Widget
        Button buttonClear;
        EditText EtCommand;
        TextView timerTextView;
        TextView textResponse;

        // Socket Info Config
        final String dstAddress = "192.168.1.104";
        final int dstPort = 8300;

        //runs without a timer by reposting this handler at the end of the runnable
        Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {
        public void run() {
            String CMDTmp = EtCommand.getText().toString();
            final Pattern sPattern = Pattern.compile(".*\\bover\\.*$", Pattern.CASE_INSENSITIVE);
            boolean res = sPattern.matcher(CMDTmp).matches();

            if (res) {
                timerTextView.setText("Success");
                Client myClient = new Client(dstAddress,
                        dstPort,
                        EtCommand.getText().toString(),
                        textResponse);
                myClient.execute();
                EtCommand.setText("");
            }
            else {
                timerTextView.setText("Failed");
            }

            timerHandler.postDelayed(this, 1000);
            }
        };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            textResponse = (TextView) findViewById(R.id.responseTV);
            String response = "Msg Send";

            // -----------------Button----------------
            final Button button = (Button) findViewById(R.id.SendMsgBT);
            buttonClear = (Button) findViewById(R.id.ClearButton);
            EtCommand = (EditText) findViewById(R.id.Command);
            timerTextView = (TextView) findViewById(R.id.timerTextView);

            // -----------------Timer GO----------------
            timerHandler.postDelayed(timerRunnable, 0);

            // -----------------SetListener----------------
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Client myClient = new Client(dstAddress,
                            dstPort,
                            EtCommand.getText().toString(),
                            textResponse);
                    myClient.execute();
                }
            });
            buttonClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textResponse.setText("");
                }
            });
        }
    }
