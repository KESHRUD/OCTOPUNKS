public class OctopunksGame {

    private level currentLevel;
    private GUI gui;

    public OctopunksGame(level level, GUI gui){
        this.currentLevel = level;
        this.gui = gui;
    }

    public void startGame(){

    }

    public void loadLevel(level level){
        this.currentLevel = level;
    }

    public void handleEvent(){

    }

    public level getCurrentLevel(){
        return this.currentLevel;
    }

    public GUI getGui(){
        return gui;
    }
}
