import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
    private static final String path = "music\\BackgroundMusic.wav";
    private Clip clip;

    public Music() {
        try {
            InputStream musicPath = Music.class.getResourceAsStream(path);
            BufferedInputStream bin = new BufferedInputStream(musicPath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bin);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOrStop(boolean play) {
        if(play)
            clip.start();
        else
            clip.stop();
    }
}
