import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

public class Application implements Runnable{

    Thread gameThread;
    int FPS = 60;

    public static void main(String[] args) {
        System.out.println("Hello");
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000.0/FPS; //0.0166667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                Arena.draw();
                delta--;
            }

        }
    }

    public void update(){

    }
}
