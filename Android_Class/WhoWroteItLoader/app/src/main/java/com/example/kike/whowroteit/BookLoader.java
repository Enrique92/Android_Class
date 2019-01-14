package com.example.kike.whowroteit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

public class BookLoader extends AsyncTaskLoader<String> implements LoaderManager.LoaderCallbacks<String> {

    private String mQueryString;

    BookLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int mI, @Nullable Bundle mBundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> mLoader, String mS) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> mLoader) {

    }
}
