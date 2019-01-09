package com.example.kike.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Integer, Integer, String> {

    private WeakReference<TextView> mTextView;
    private ProgressBar mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        this.mProgressBar = pb;
    }

    @Override
    protected String doInBackground(Integer... mVoids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        // Sleep for the random amount of time
        for (int cont = 1; cont <= mVoids[0]; cont++) {
            try {
                Thread.sleep(s);
                publishProgress(cont);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mProgressBar.setVisibility(View.GONE);
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText("Running " + values[0]);
        mProgressBar.setProgress(values[0]);
    }
}
