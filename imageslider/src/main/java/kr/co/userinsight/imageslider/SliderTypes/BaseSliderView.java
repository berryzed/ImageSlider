package kr.co.userinsight.imageslider.SliderTypes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import kr.co.userinsight.imageslider.R;


/**
 * Created by rimi on 2017. 3. 20..
 * Copyright (c) 2017 UserInsight Corp.
 *
 * When you want to make your own slider view, you must extends from this class.
 * BaseSliderView provides some useful methods.
 * I provide two example: {@link kr.co.userinsight.imageslider.SliderTypes.DefaultSliderView} and
 * {@link kr.co.userinsight.imageslider.SliderTypes.TextSliderView}
 * if you want to show progressbar, you just need to set a progressbar id as @+id/loading_bar.
 */

public abstract class BaseSliderView {
    protected Context mContext;

    private Bundle mBundle;

    /**
     * Error place holder image.
     */
    private int mErrorPlaceHolderRes;

    /**
     * Empty imageView placeholder.
     */
    private int mEmptyPlaceHolderRes;

    private String mUrl;
    private File mFile;
    private int mRes;

    protected OnSliderClickListener mOnSliderClickListener;

    private boolean mErrorDisappear;

    private ImageLoadListener mLoadListener;

    private String mDescription;

    // Glide
    private Glide mGlide;

    /**
     * Scale type of the image.
     */
    private ScaleType mScaleType = ScaleType.Fit;

    public enum ScaleType {
        CenterCrop, Fit
    }

    protected BaseSliderView(Context context) {
        mContext = context;
    }

    /**
     * the placeholder image when loading image from url or file.
     *
     * @param resId Image resource id
     * @return BaseSliderView
     */
    public BaseSliderView empty(int resId) {
        mEmptyPlaceHolderRes = resId;
        return this;
    }

    /**
     * determine whether remove the image which failed to download or load from file
     *
     * @param disappear boolean
     * @return BaseSliderView
     */
    public BaseSliderView errorDisappear(boolean disappear) {
        mErrorDisappear = disappear;
        return this;
    }

    /**
     * if you set errorDisappear false, this will set a error placeholder image.
     *
     * @param resId image resource id
     * @return BaseSliderView
     */
    public BaseSliderView error(int resId) {
        mErrorPlaceHolderRes = resId;
        return this;
    }

    /**
     * the description of a slider image.
     *
     * @param description String
     * @return BaseSliderView
     */
    public BaseSliderView description(String description) {
        mDescription = description;
        return this;
    }

    /**
     * set a url as a image that preparing to load
     *
     * @param url String
     * @return BaseSliderView
     */
    public BaseSliderView image(String url) {
        if (mFile != null || mRes != 0) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mUrl = url;
        return this;
    }

    /**
     * set a file as a image that will to load
     *
     * @param file File
     * @return BaseSliderView
     */
    public BaseSliderView image(File file) {
        if (mUrl != null || mRes != 0) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mFile = file;
        return this;
    }

    public BaseSliderView image(int res) {
        if (mUrl != null || mFile != null) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mRes = res;
        return this;
    }

    /**
     * lets users add a bundle of additional information
     *
     * @param bundle Bundle
     * @return BaseSliderView
     */
    public BaseSliderView bundle(Bundle bundle) {
        mBundle = bundle;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public boolean isErrorDisappear() {
        return mErrorDisappear;
    }

    public int getEmpty() {
        return mEmptyPlaceHolderRes;
    }

    public int getError() {
        return mErrorPlaceHolderRes;
    }

    public String getDescription() {
        return mDescription;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * set a slider image click listener
     *
     * @param l OnSliderClickListener
     * @return BaseSliderView
     */
    public BaseSliderView setOnSliderClickListener(OnSliderClickListener l) {
        mOnSliderClickListener = l;
        return this;
    }

    /**
     * When you want to implement your own slider view, please call this method in the end in `getView()` method
     *
     * @param v               the whole view
     * @param targetImageView where to place image
     */
    protected void bindEventAndShow(final View v, ImageView targetImageView) {
        final BaseSliderView me = this;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSliderClickListener != null) {
                    mOnSliderClickListener.onSliderClick(me);
                }
            }
        });

        if (targetImageView == null)
            return;

        if (mLoadListener != null) {
            mLoadListener.onStart(me);
        }

        DrawableTypeRequest dtr;
        RequestManager rm = Glide.with(mContext);
        if (mUrl != null) {
            dtr = rm.load(mUrl);
        } else if (mFile != null) {
            dtr = rm.load(mFile);
        } else if (mRes != 0) {
            dtr = rm.load(mRes);
        } else {
            return;
        }

        if (dtr == null) {
            return;
        }

        if (getEmpty() != 0) {
            dtr.placeholder(getEmpty());
        }

        if (getError() != 0) {
            dtr.error(getError());
        }

        switch (mScaleType) {
            case Fit:
                dtr.fitCenter();
                break;
            case CenterCrop:
                dtr.centerCrop();
                break;
        }

        dtr.listener(new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                if (mLoadListener != null) {
                    mLoadListener.onEnd(false, me);
                }
                if (v.findViewById(R.id.loading_bar) != null) {
                    v.findViewById(R.id.loading_bar).setVisibility(View.INVISIBLE);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (v.findViewById(R.id.loading_bar) != null) {
                    v.findViewById(R.id.loading_bar).setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        dtr.thumbnail(0.1f).into(targetImageView);
    }


    public BaseSliderView setScaleType(ScaleType type) {
        mScaleType = type;
        return this;
    }

    public ScaleType getScaleType() {
        return mScaleType;
    }

    /**
     * the extended class have to implement getView(), which is called by the adapter,
     * every extended class response to render their own view.
     *
     * @return View
     */
    public abstract View getView();

    /**
     * set a listener to get a message , if load error.
     *
     * @param l ImageLoadListener
     */
    public void setOnImageLoadListener(ImageLoadListener l) {
        mLoadListener = l;
    }

    public interface OnSliderClickListener {
        void onSliderClick(BaseSliderView slider);
    }

    /**
     * when you have some extra information, please put it in this bundle.
     *
     * @return Bundle
     */
    public Bundle getBundle() {
        return mBundle;
    }

    public interface ImageLoadListener {
        void onStart(BaseSliderView target);

        void onEnd(boolean result, BaseSliderView target);
    }

    /**
     * Get the last instance set via setPicasso(), or null if no user provided instance was set
     *
     * @return The current user-provided Picasso instance, or null if none
     */
    public Glide getGlide() {
        return mGlide;
    }

    /**
     * Provide a Glide instance to use when loading pictures, this is useful if you have a
     * particular HTTP cache you would like to share.
     *
     * @param glide The Picasso instance to use, may be null to let the system use the default
     *              instance
     */
    public void setGlide(Glide glide) {
        mGlide = glide;
    }
}
