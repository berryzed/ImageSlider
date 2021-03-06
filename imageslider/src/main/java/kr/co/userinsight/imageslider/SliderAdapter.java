package kr.co.userinsight.imageslider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.userinsight.imageslider.SliderTypes.BaseSliderView;


/**
 * Created by rimi on 2017. 3. 20..
 * Copyright (c) 2017 UserInsight Corp.
 * <p>
 * A slider adapter
 */

public class SliderAdapter extends PagerAdapter implements BaseSliderView.ImageLoadListener {
    private Context mContext;
    private ArrayList<BaseSliderView> mImageContents;

    public SliderAdapter(Context context) {
        mContext = context;
        mImageContents = new ArrayList<>();
    }

    public <T extends BaseSliderView> void addSlider(T slider) {
        slider.setOnImageLoadListener(this);
        mImageContents.add(slider);
        notifyDataSetChanged();
    }

    public BaseSliderView getSliderView(int position) {
        if (position < 0 || position >= mImageContents.size()) {
            return null;
        } else {
            return mImageContents.get(position);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public <T extends BaseSliderView> void removeSlider(T slider) {
        if (mImageContents.contains(slider)) {
            mImageContents.remove(slider);
            notifyDataSetChanged();
        }
    }

    public void removeSliderAt(int position) {
        if (mImageContents.size() > position) {
            mImageContents.remove(position);
            notifyDataSetChanged();
        }
    }

    public void removeAllSliders() {
        mImageContents.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageContents.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseSliderView b = mImageContents.get(position);
        View v = b.getView();
        container.addView(v);
        return v;
    }

    @Override
    public void onStart(BaseSliderView target) {

    }

    /**
     * When image download error, then remove.
     *
     * @param result boolean
     * @param target BaseSliderView
     */
    @Override
    public void onEnd(boolean result, BaseSliderView target) {
        if (!target.isErrorDisappear() || result) {
            return;
        }
        for (BaseSliderView slider : mImageContents) {
            if (slider.equals(target)) {
                removeSlider(target);
                break;
            }
        }
    }
}
