package com.sushma.multithreading;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        IncrementTask incrementTask = new IncrementTask();
        incrementTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        DecrementTask decrementTask = new DecrementTask();
        decrementTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        System.out.println(  "test onCreate Thread name"+Thread.currentThread().getName() +" id= " +Thread.currentThread().getId() );
        LinearLayout llParent = findViewById(R.id.ll_parent);
        llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(  "test onCreate dialog Thread name"+Thread.currentThread().getName() +" id= " +Thread.currentThread().getId() );
                new AlertDialog.Builder(activity).setMessage("Multithreading!").setPositiveButton("OK", null).setTitle("Alert").show();
            }
        });
    }


    private class IncrementTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            System.out.println(  "test IncrementTask Thread name"+Thread.currentThread().getName() +" id= " +Thread.currentThread().getId() );

            int count = 0;
            for (count = 0; count < 2147483647; count++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(count);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView value = findViewById(R.id.tv_increment_value);
            value.setText("" + values[0]);
        }
    }


    private class DecrementTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {


            int count;
            for (count = 2147483647; count >= 0; count--) {
                System.out.println( "test DecrementTask Thread name"+Thread.currentThread().getName() +" id= " + Thread.currentThread().getId() );
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(count);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView value = findViewById(R.id.tv_decrement_value);
            value.setText("" + values[0]);
        }
    }
}

