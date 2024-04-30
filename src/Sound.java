
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {

    private Clip hitClip;
    private Clip destroyClip;

    public Sound() {
        try {

            AudioInputStream hitStream = AudioSystem
                    .getAudioInputStream(getClass().getClassLoader().getResource("sound/hit.wav"));
            hitClip = AudioSystem.getClip();
            hitClip.open(hitStream);

            AudioInputStream destroyStream = AudioSystem
                    .getAudioInputStream(getClass().getResourceAsStream("/sound/destroy.wav"));
            destroyClip = AudioSystem.getClip();
            destroyClip.open(destroyStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void soundHit() {
        hitClip.start();
    }

    public void soundDestroy() {
        destroyClip.start();
    }

}
