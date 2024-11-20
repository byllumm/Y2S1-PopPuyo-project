import java.util.List;

public class Arena implements Drawable {

    static int rows = 11;
    static int columns = 6;
    private Puyo grid[][] = new Puyo[rows][columns];
    private PuyoPair activePuyo;
    int autoDropCounter = 0;

    public static int dropInterval = 60; //Puyo drop every 60 frames

    public Arena(PuyoPair activePuyo, List<Puyo> staticPuyos){
        this.activePuyo = activePuyo;
    }

    //Cleans the puyo off the cell
    public void clearCell(int row, int col){
        grid[row][col] = null;
    }

    //Checks if there's any puyos on the cell
    public boolean isEmpty(int row, int col){
        return grid[row][col] == null;
    }

    //Checks if puyos can go down next row
    public boolean canMoveDown(Puyo[][] grid, PuyoPair activePuyo){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        //do not know if I should put <= or <
        return(firstPos.getY() + 1 < Arena.rows && isEmpty(firstPos.getY() + 1, firstPos.getX()) &&
                secondPos.getY() + 1 < Arena.rows && isEmpty(secondPos.getY() + 1,secondPos.getX()));
    }

    //Makes puyos fall down
    public void moveDown(){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();
        firstPos.setY(firstPos.getY() + 1);
        secondPos.setY(firstPos.getY() + 1);
    }


    //Update game every frame, making puyos fall and checking if they hit the static puyos
    public void update(){

        //Process input function (need to make a function)
        //Also a rotate function to help the input function
        autoDropCounter++;
        if(autoDropCounter == Arena.dropInterval){
            if(canMoveDown(grid, activePuyo)) {
                Position firstPos = activePuyo.getFirstPos();
                Position secondPos = activePuyo.getSecondPos();

                firstPos.setY(firstPos.getY() + 1);
                secondPos.setY(secondPos.getY() + 1);
            }
            else{
                integrateGrid();
                boolean fell = applyGravity();
                while(fell){
                    fell = applyGravity();
                }
                //Spawn new puyo (need to make a function for this)
            }
            autoDropCounter = 0;
        }
    }

    //Locks active puyo to the static puyos
    public void integrateGrid(){
        Position firstPos = activePuyo.getFirstPos();
        Position secondPos = activePuyo.getSecondPos();

        grid[firstPos.getY()][firstPos.getX()] = activePuyo.getFirstPuyo();
        grid[secondPos.getY()][firstPos.getX()] = activePuyo.getSecondPuyo();

        activePuyo = null;
    }

    //Checks if any individual puyo can fall even more, after the pair has hit the static puyos
    public boolean applyGravity(){
        boolean fell = false;

        for(int col = 0; col < columns; col++){
            for(int row = rows - 2; row >= 0; row--){
                if(!isEmpty(row, col) && isEmpty(row + 1, col)){

                    Puyo puyo = grid[row][col];
                    grid[row + 1][col] = puyo;
                    clearCell(row, col);
                    fell = true;

                }
            }
        }
        return fell;
    }

    @Override
    // TO BE IMPLEMENTED
    public void draw() { }
}
