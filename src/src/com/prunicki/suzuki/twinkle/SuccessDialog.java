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
package com.prunicki.suzuki.twinkle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SuccessDialog extends Dialog {
    private ImageView mSuccessImage;
    private Player mPlayer;
    
    public SuccessDialog(Context context) {
        super(context);
        Activity activity = (Activity) context;
        mPlayer = ((SuzukiApplication) activity.getApplication()).getPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        
        setTitle(R.string.dialog_success);
        setCanceledOnTouchOutside(true);
        setOnCancelListener(mCancelListener);
        
        mSuccessImage = (ImageView) findViewById(R.id.SuccessImage);
        mSuccessImage.setOnClickListener(mSuccessListener);
        
        mPlayer.playSuccess();
    }
    
    private OnCancelListener mCancelListener = new OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            mPlayer.pause();
        }
    };
    
    private View.OnClickListener mSuccessListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            SuccessDialog.this.cancel();
        }
    };
}