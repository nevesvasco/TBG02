package RushDash;
import org.junit.jupiter.api.Test;
import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.*;

class PlaySoundTest {

    @Test
    void testPlaySound() {
        PlaySound playSound = new PlaySound();
        String soundFile = "platforming.wav";

        // Verifica se o método playSound não lança exceções
        assertDoesNotThrow(() -> playSound.playSound(soundFile));

        // Verifica se o Clip foi inicializado corretamente
        Clip clip = playSound.getClip();
        assertNotNull(clip);

        // Aguarde alguns segundos (tempo suficiente para o som ser reproduzido)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Pare o som e verifique se o Clip foi fechado
        playSound.stopSound();
        assertFalse(clip.isRunning());
    }

    @Test
    void testStopSound() {
        PlaySound playSound = new PlaySound();
        String soundFile = "platforming.wav";

        // Reproduza um som antes de chamar stopSound
        playSound.playSound(soundFile);

        // Verifique se o Clip está em execução antes de chamar stopSound
        Clip clip = playSound.getClip();
        assertNotNull(clip);

        // Pare o som e verifique se o Clip foi fechado
        playSound.stopSound();
        assertFalse(clip.isRunning());
    }
}