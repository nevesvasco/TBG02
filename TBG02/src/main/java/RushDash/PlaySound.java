package RushDash;

import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class PlaySound {


    private Clip clip;

    void playSound(String soundFile) {
        File f = new File("./src/main/resources/" + soundFile);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());

            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }

    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }
}
