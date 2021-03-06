/*
 * Copyright 2015 Azmeer Raja
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.raja.knowme.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.raja.knowme.variables.AppGlobalVariables;

public class AppButton extends Button {

    public AppButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(Typeface.createFromAsset(context.getAssets(),
                AppGlobalVariables.FONT_NAME));
        setHorizontalFadingEdgeEnabled(false);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(),
                AppGlobalVariables.FONT_NAME));
        setHorizontalFadingEdgeEnabled(false);
    }

    public AppButton(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(),
                AppGlobalVariables.FONT_NAME));
        setHorizontalFadingEdgeEnabled(false);
    }
}
