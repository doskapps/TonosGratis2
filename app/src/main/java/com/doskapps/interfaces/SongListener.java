package com.doskapps.interfaces;

import com.doskapps.item.ItemSong;

import java.util.ArrayList;

public interface SongListener {
    void onStart();

    void onEnd(String success, ArrayList<ItemSong> arrayListSong);
}
