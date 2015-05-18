package com.siokas.threads;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    // Declare the widgets
    Button button;
    TextView textView;

    // Create a handler object and inside it override the handleMessage() method
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // Get the string from the message object and display it in the textview
            String message = msg.getData().getString("msg");
            textView.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate the widgets with their ids
        button = (Button) findViewById(R.id.bt);
        textView = (TextView) findViewById(R.id.tv);


        // Set an OnClickListener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a Thread and a Runnable object as parameter of the thread object
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // Call the sleep() method of the Thread class and pass 1 sec as parameter. Surround it with try catch block, catching the InterruptedException.
                        try {
                            Thread.sleep(5000);

                            // After the sleep() method send a message to the handler
                            // First create a Message object
                            Message message = new Message();

                            // Second create a Bundle object
                            Bundle bundle = new Bundle();

                            // Put the string in the bundle
                            bundle.putString("msg", "Message from the second thread!");

                            // Call the setData() method of the message object and pass the bundle as parameter
                            message.setData(bundle);

                            // Send the message to the handler
                            handler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // Start the thread
                thread.start();

            }
        });


    }

}
