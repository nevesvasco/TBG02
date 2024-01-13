package RushDash;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TabBehaviour;

import java.io.IOException;

public class BasicScreenMock implements Screen {

    private boolean isCloseCalled = false;

    @Override
    public TextGraphics newTextGraphics() {
        // Implement as needed for your tests
        return null;
    }

    @Override
    public TextCharacter getFrontCharacter(int column, int row) {
        return null;
    }

    @Override
    public TextCharacter getFrontCharacter(TerminalPosition position) {
        return null;
    }

    @Override
    public TextCharacter getBackCharacter(int column, int row) {
        return null;
    }

    @Override
    public TextCharacter getBackCharacter(TerminalPosition position) {
        return null;
    }

    @Override
    public void refresh() throws IOException {

    }

    @Override
    public void refresh(RefreshType refreshType) throws IOException {

    }

    @Override
    public TerminalSize doResizeIfNecessary() {
        return null;
    }

    @Override
    public void scrollLines(int firstLine, int lastLine, int distance) {

    }

    @Override
    public void startScreen() throws IOException {

    }

    @Override
    public void close() throws IOException {
        isCloseCalled = true;
    }

    @Override
    public void stopScreen() throws IOException {

    }

    @Override
    public void clear() {

    }

    @Override
    public TerminalPosition getCursorPosition() {
        return null;
    }

    @Override
    public void setCursorPosition(TerminalPosition position) {

    }

    @Override
    public TabBehaviour getTabBehaviour() {
        return null;
    }

    @Override
    public void setTabBehaviour(TabBehaviour tabBehaviour) {

    }

    @Override
    public TerminalSize getTerminalSize() {
        return null;
    }

    @Override
    public void setCharacter(int column, int row, TextCharacter screenCharacter) {

    }

    @Override
    public void setCharacter(TerminalPosition position, TextCharacter screenCharacter) {

    }

    // Add other necessary methods from the Screen interface

    public boolean isCloseCalled() {
        return isCloseCalled;
    }

    @Override
    public KeyStroke pollInput() throws IOException {
        return null;
    }

    @Override
    public KeyStroke readInput() throws IOException {
        return null;
    }
}