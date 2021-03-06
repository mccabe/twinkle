/*
 * Copyright 2010 Andrew Prunicki
 * 
 * This file is part of Twinkle.
 * 
 * Twinkle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Twinkle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Twinkle.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.prunicki.twinkle.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;

public class SimpleNoteView extends StaffView {
    private char mNoteChar;
    private Path mNotePath;

    public SimpleNoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        mNotePath = new Path();
    }

    @Override
    protected void drawCustom(Canvas canvas) {
        if (mNoteChar == WHOLE_REST) {
            float bottom = mLineY[2] + (mLineY[3] - mLineY[2]) / 2;
            mNotePath.addRect(mStartNoteX, mLineY[2], mStartNoteX + mFontWidth * 0.7f, bottom, Direction.CW);
            
            canvas.drawPath(mNotePath, mFontPaint);
        }
        else if (mNoteChar == HALF_REST) {
            float top = mLineY[2] + (mLineY[3] - mLineY[2]) / 2 - mStrokeWidth;
            mNotePath.addRect(mStartNoteX, top, mStartNoteX + mFontWidth * 0.7f, mLineY[3], Direction.CW);
            
            canvas.drawPath(mNotePath, mFontPaint);
        } else {
            float fontSize = (float) (mLineHeight * 1.2);
            mFontPaint.setTextSize(fontSize);
            
            canvas.drawText(Character.toString(mNoteChar), mStartNoteX, mLineY[3], mFontPaint);
        }
    }

    public void setNote(char note) {
        if (note != mNoteChar) {
            mNoteChar = note;
            postInvalidate();
        }
    }
}
