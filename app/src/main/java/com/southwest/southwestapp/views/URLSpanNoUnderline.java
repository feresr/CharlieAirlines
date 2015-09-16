package com.southwest.southwestapp.views;

import android.text.TextPaint;
import android.text.style.URLSpan;


/**
 * Created by luisalfonsobejaranosanchez on 9/16/15.
 */
public class URLSpanNoUnderline extends URLSpan {

    public URLSpanNoUnderline(String url) {
        super(url);
    }

    public void updateDrawState(TextPaint draw) {
        super.updateDrawState(draw);
        draw.setUnderlineText(false);
    }

}
