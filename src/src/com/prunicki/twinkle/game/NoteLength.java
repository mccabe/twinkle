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
package com.prunicki.twinkle.game;

import android.view.ViewGroup;

import com.prunicki.twinkle.GameRoundCallback;
import com.prunicki.twinkle.widget.SimpleNoteView;

public class NoteLength extends AbstractLengthRound {
    public NoteLength(ViewGroup viewGroup, GameRoundCallback callback) {
        super(viewGroup, callback, "note");
    }
    
    @Override
    protected void showNextView() {
        char noteChar;
        
        switch(mNote) {
            case WHOLE_NOTE:
                noteChar = SimpleNoteView.WHOLE_NOTE;
                break;
            case HALF_NOTE:
                noteChar = SimpleNoteView.HALF_NOTE;
                break;
            case QUARTER_NOTE:
                noteChar = SimpleNoteView.QUARTER_NOTE;
                break;
            case EIGHTH_NOTE:
                noteChar = SimpleNoteView.EIGHTH_NOTE;
                break;
            case SIXTEENTH_NOTE:
                noteChar = SimpleNoteView.SIXTEENTH_NOTE;
                break;
            default:
                throw new IllegalStateException("Invalid note " + mNote);
        }
        
        mStaffView.setNote(noteChar);
    }
}
