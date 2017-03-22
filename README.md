# ImageSlider
![License](https://img.shields.io/github/license/gittools/gittools.core.svg)

## Synopsis

[AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)의 [Glide](https://github.com/bumptech/glide) library 버전
## Code Example

### Layout
```
<kr.co.userinsight.imageslider.SliderLayoutt
    android:id="@+id/slider"
    android:layout_width="match_parent"
    android:layout_height="200dp"/>

<kr.co.userinsight.imageslider.Indicators.PagerIndicator
    android:id="@+id/custom_indicator"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:gravity="center"/>
```
### Code
먼저 `BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener'를 클래스에 구현하고 아래 코드를 onCreate에 작성한다.
```
for (String name : url_maps.keySet()) {
    // TextSliderView textSliderView = new TextSliderView(this);
    DefaultSliderView defaultSliderView = new DefaultSliderView(this);
    // initialize a SliderLayout
    defaultSliderView
            .description(name)
            .image(url_maps.get(name))
            .setScaleType(BaseSliderView.ScaleType.Fit)
            .setOnSliderClickListener(this);

    //add your extra information
    defaultSliderView.bundle(new Bundle());
    defaultSliderView.getBundle()
            .putString("extra", name);

    mSliderLayout.addSlider(defaultSliderView);
}
mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
mSliderLayout.setDuration(4000);
mSliderLayout.addOnPageChangeListener(this);
```

## Installation
모듈의 build.grade > dependencies 부분에 `compile 'kr.co.userinsight.library:imageslider:0.1.0'` 추가

## Thanks
- [AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)
- [Glide](https://github.com/bumptech/glide)

## License
```
MIT License

Copyright (c) 2017 rimi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
