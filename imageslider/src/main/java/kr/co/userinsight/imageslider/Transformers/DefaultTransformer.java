package kr.co.userinsight.imageslider.Transformers;

import android.view.View;

/**
 * Created by rimi on 2017. 3. 20..
 * Copyright (c) 2017 UserInsight Corp.
 */

public class DefaultTransformer extends BaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }
}
