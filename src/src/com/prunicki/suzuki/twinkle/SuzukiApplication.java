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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.app.Application;

//TODO Consider creating a "listenable" annotation or interface.
public class SuzukiApplication extends Application {
    public static final String PROP_CHG_PLAYER = "propChgPlyr";
    
    private Player mCurrentPlayer;
    private SoundPlayer mSoundPlayer;
    private ScoreDAO mDAO;
    private ArrayList<WeakReference<PropertyChangeListener>> listeners;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        listeners = new ArrayList<WeakReference<PropertyChangeListener>>();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        
        releaseSoundPlayer();
        releaseDAO();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        
        releaseSoundPlayer();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        Utils.addPropertyChangeListener(listeners, listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        Utils.removePropertyChangeListener(listeners, listener);
    }
    
    public Player getCurrentPlayer() {
        return mCurrentPlayer;
    }
    
    public void setCurrentPlayer(Player player) {
        if (player != mCurrentPlayer) {
            Player oldPlayer = player;
            mCurrentPlayer = player;
            
            Utils.firePropertyChangeEvent(listeners, new PropertyChangeEvent(this, PROP_CHG_PLAYER, oldPlayer, player));
        }
    }
    
    public SoundPlayer getSoundPlayer() {
        if (mSoundPlayer == null) {
            mSoundPlayer = new SoundPlayer();
            mSoundPlayer.initialize(this);
        }
        
        return mSoundPlayer;
    }
    
    public ScoreDAO getDAO() {
        if (mDAO == null) {
            mDAO = new ScoreDAO(this);
            mDAO.open();
//            int id = mDAO.createPlayer("Anna");
//            id = mDAO.createPlayer("Emily");
//            id = mDAO.createPlayer("John");
//            id = mDAO.createPlayer("Levi");
//            id = mDAO.createPlayer("Rita");
//            id = mDAO.createPlayer("Fred");
//            id = mDAO.createPlayer("Francis");
//            id = mDAO.createPlayer("Frederick");
//            id = mDAO.createPlayer("Johanson");
        }
        
        return mDAO;
    }

    private void releaseSoundPlayer() {
        if (mSoundPlayer != null) {
            mSoundPlayer.release();
            mSoundPlayer = null;
        }
    }
    
    private void releaseDAO() {
        if (mDAO != null) {
            mDAO.release();
            mDAO = null;
        }
    }
}
