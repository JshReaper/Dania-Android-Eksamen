package com.jshch.androidgameeksamen;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioController extends Component {

    MediaPlayer audio;
    Context context;
    public AudioController(GameObject go, Context context){
        super(go);
        this.context = context;
    }


    public void SetSound(int id){
        audio = MediaPlayer.create(context,id);
    }

    public void Play(){
        if(audio != null){
            audio.start();
        }
    }

    public void Stop(){
        if(audio != null){
            audio.stop();
        }
    }
}
