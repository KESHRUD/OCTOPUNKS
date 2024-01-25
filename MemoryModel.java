
import java.util.HashMap;
import java.util.Map;

public class MemoryModel {
    //une map pour stocker les valeurs en fonction de l'addresse memoire
    private Map<Integer, Integer> memory;

    //initialisiation de la map
    public MemoryModel(){
        this.memory = new HashMap<>();
    }

    //lexture d'une valuer a une addresse donné
    public int read(int address){
        return memory.getOrDefault(address, 0);
    }

    //ecriture d'une valeur donné a une addresse donné
    public void write (int address, int value){
        memory.put(address, value);
    }


    //reinitialisiation de la memoire
    public void clearMmeory(){
        memory.clear();
    }

}