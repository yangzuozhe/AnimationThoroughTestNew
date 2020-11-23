package com.example.animationthoroughtest;

import android.animation.TypeEvaluator;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2020-11-18
 */
public class HeightEvaluator implements TypeEvaluator<Height> {
    @Override
    public Height evaluate(float fraction, Height startValue, Height endValue) {
        int startHeight = startValue.getHeight();
        //当前变化中的高度
        int currHeight = (int) (startHeight + fraction * (endValue.getHeight() - startHeight));
        Height height = new Height();
        height.setHeight(currHeight);
        return height;
    }
}
