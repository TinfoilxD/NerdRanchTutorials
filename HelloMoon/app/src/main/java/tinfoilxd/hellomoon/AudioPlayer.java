package tinfoilxd.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;


public class AudioPlayer
{
    MediaPlayer sound;

    public void stop()
    {
        if(sound != null)
        {
            sound.release();
            sound = null;
        }
    }
    public void play(Context c)
    {
        stop();
        sound = MediaPlayer.create(c, R.raw.one_small_step);
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                stop();
            }
        });
        sound.start();
    }
}
