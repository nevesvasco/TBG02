import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class PlaySoundTest {

    private PlaySound playSound;

    @Before
    public void setUp() {
        playSound = new PlaySound();
    }

    @After
    public void tearDown() {
        playSound.stopSound();
    }

    @Test
    public void testPlaySound() {
        // Tenta reproduzir um som e verifica se o Clip está ativo
        playSound.playSound("test_sound.wav");
        assertNotNull(playSound.getClip());
        assertTrue(playSound.getClip().isActive());
    }

    @Test
    public void testStopSound() {
        // Tenta parar o som e verifica se o Clip é nulo após a parada
        playSound.playSound("test_sound.wav");
        playSound.stopSound();
        assertNull(playSound.getClip());
    }

    @Test
    public void testGetSetClip() {
        // Cria um Clip fictício e verifica se os métodos get e set funcionam corretamente
        Clip testClip = createMockClip();
        playSound.setClip(testClip);
        assertEquals(testClip, playSound.getClip());
    }

    private Clip createMockClip() {
        // Implemente um Clip fictício para uso nos testes
        return new Clip() {
            @Override
            public Line.Info getLineInfo() {
                return null;
            }

            @Override
            public void open() throws LineUnavailableException {

            }

            @Override
            public void close() {

            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public Control[] getControls() {
                return new Control[0];
            }

            @Override
            public boolean isControlSupported(Control.Type control) {
                return false;
            }

            @Override
            public Control getControl(Control.Type control) {
                return null;
            }

            @Override
            public void addLineListener(LineListener listener) {

            }

            @Override
            public void removeLineListener(LineListener listener) {

            }

            @Override
            public void drain() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }

            @Override
            public boolean isActive() {
                return false;
            }

            @Override
            public AudioFormat getFormat() {
                return null;
            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public int available() {
                return 0;
            }

            @Override
            public int getFramePosition() {
                return 0;
            }

            @Override
            public long getLongFramePosition() {
                return 0;
            }

            @Override
            public long getMicrosecondPosition() {
                return 0;
            }

            @Override
            public float getLevel() {
                return 0;
            }

            @Override
            public void open(AudioFormat format, byte[] data, int offset, int bufferSize) throws LineUnavailableException {

            }

            @Override
            public void open(AudioInputStream stream) throws LineUnavailableException, IOException {

            }

            @Override
            public int getFrameLength() {
                return 0;
            }

            @Override
            public long getMicrosecondLength() {
                return 0;
            }

            @Override
            public void setFramePosition(int frames) {

            }

            @Override
            public void setMicrosecondPosition(long microseconds) {

            }

            @Override
            public void setLoopPoints(int start, int end) {

            }

            @Override
            public void loop(int count) {

            }
            // Implemente os métodos necessários para o teste
        };
    }

    @Test
    public void testPlayInvalidSound() {
        // Tenta reproduzir um som inexistente e verifica se a exceção é tratada corretamente
        try {
            playSound.playSound("nonexistent_sound.wav");
        } catch (Exception e) {
            assertNotNull(e.getMessage());
        }
    }
}
