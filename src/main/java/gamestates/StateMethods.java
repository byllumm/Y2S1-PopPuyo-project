package gamestates;

import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface StateMethods {
    public void draw() throws IOException;
    public void processKey(KeyStroke key);

}
