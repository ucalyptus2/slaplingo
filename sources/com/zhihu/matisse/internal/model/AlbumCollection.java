package com.zhihu.matisse.internal.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.zhihu.matisse.internal.loader.AlbumLoader;
import java.lang.ref.WeakReference;

public class AlbumCollection implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 1;
    private static final String STATE_CURRENT_SELECTION = "state_current_selection";
    private AlbumCallbacks mCallbacks;
    private WeakReference<Context> mContext;
    private int mCurrentSelection;
    private boolean mLoadFinished;
    private LoaderManager mLoaderManager;

    public interface AlbumCallbacks {
        void onAlbumLoad(Cursor cursor);

        void onAlbumReset();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Context context = this.mContext.get();
        if (context == null) {
            return null;
        }
        this.mLoadFinished = false;
        return AlbumLoader.newInstance(context);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (this.mContext.get() != null && !this.mLoadFinished) {
            this.mLoadFinished = true;
            this.mCallbacks.onAlbumLoad(cursor);
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
        if (this.mContext.get() != null) {
            this.mCallbacks.onAlbumReset();
        }
    }

    public void onCreate(FragmentActivity fragmentActivity, AlbumCallbacks albumCallbacks) {
        this.mContext = new WeakReference<>(fragmentActivity);
        this.mLoaderManager = fragmentActivity.getSupportLoaderManager();
        this.mCallbacks = albumCallbacks;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mCurrentSelection = bundle.getInt(STATE_CURRENT_SELECTION);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(STATE_CURRENT_SELECTION, this.mCurrentSelection);
    }

    public void onDestroy() {
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
        }
        this.mCallbacks = null;
    }

    public void loadAlbums() {
        this.mLoaderManager.initLoader(1, null, this);
    }

    public int getCurrentSelection() {
        return this.mCurrentSelection;
    }

    public void setStateCurrentSelection(int i) {
        this.mCurrentSelection = i;
    }
}
