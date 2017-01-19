package com.jmajyo.adventure.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.JetPlayer;

public class JetPlayerUtil {

    public static void play(Context context, int jet) {
        JetPlayer jetPlayer = JetPlayer.getJetPlayer();
        AssetFileDescriptor afd = context.getResources().openRawResourceFd(jet);
        jetPlayer.loadJetFile(afd);
        byte segmentId = 0;
        // queue segment 0, repeat once, use General MIDI
        jetPlayer.queueJetSegment(0, -1, -1, 0, 0, (byte) 0);
        jetPlayer.play();
    }
}
