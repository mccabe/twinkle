package com.prunicki.suzuki.twinkle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.prunicki.suzuki.twinkle.db.ScoreDAO;

public class NewPlayerDialog extends TwinkleDialog {
    private TwinkleApplication mAppCtx;
    private EditText mName;
    private View mAdd;
    private View mCancel;
    long mNewPlayerId;

    public NewPlayerDialog(Context context) {
        super(context);
        
        mNewPlayerId = -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newplayer);
        
        mAppCtx = (TwinkleApplication) getContext().getApplicationContext();
        
        setTitle("New Player");
        
        mName = (EditText) findViewById(R.id.Name);
        mAdd = findViewById(R.id.Add);
        mCancel = findViewById(R.id.Cancel);
        
        mAdd.setOnClickListener(mAddListener);
        
        mCancel.setOnClickListener(mCancelListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    
    public long getNewPlayerId() {
        return mNewPlayerId;
    }
    
    private View.OnClickListener mAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = mName.getText().toString();
            
            if (name.length() > 0) {
                ScoreDAO dao = mAppCtx.getDAO();
                long id = dao.createPlayer(name);
                dao.createScore(id);
                mNewPlayerId = id;
                dismiss();
            }
        }
    };
    
    private View.OnClickListener mCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cancel();
        }
    };
}
