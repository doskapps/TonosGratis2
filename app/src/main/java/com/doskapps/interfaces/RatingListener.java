package com.doskapps.interfaces;

public interface RatingListener {
    void onStart();
    void onEnd(String success, String message, int rating);
}
