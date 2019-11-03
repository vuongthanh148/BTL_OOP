package Util;

import UI.UI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import javax.annotation.Resource;
import java.io.IOException;


public class Sound {
    public static Audio backgroundSound;
    public static Audio sniperSound;
    public static Audio machinegunSound;

    static {
        try {
            backgroundSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("vietnam.ogg"));
            machinegunSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("machinegun.ogg"));
            sniperSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sniper.ogg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void backgroundSound(){
        backgroundSound.playAsSoundEffect(1.0f, 0.2f, true);
    }

    public static void machinegunSound(){
        machinegunSound.playAsSoundEffect(1.5f, 0.7f, false);
    }

    public static void sniperSound(){
        sniperSound.playAsSoundEffect(1.0f, 3f, false);
    }

}