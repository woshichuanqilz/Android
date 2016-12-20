package com.lizhe.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Socket
        Button buttonClear;
        final EditText EtCommand;


        final String dstAddress = "192.168.1.104";
        final int dstPort = 8300;
        String response = "Msg Send";
        final TextView textResponse = (TextView) findViewById(R.id.responseTV);

        // -----------------Button----------------
        final Button button = (Button) findViewById(R.id.SendMsgBT);
        buttonClear = (Button) findViewById(R.id.ClearButton);
        EtCommand = (EditText) findViewById(R.id.Command);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // EditText editText = (EditText)findViewById(R.id.Msg);
                // editText.setText("Google is your friend.", TextView.BufferType.EDITABLE);
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
