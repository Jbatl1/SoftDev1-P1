import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private ArrayList<Room> rooms = new ArrayList<>();
    private int currentRoom;


    public Game() {}



    public void loadGame(String f) {

        ArrayList<Room> rooms = new ArrayList<>();

        try (Scanner in = new Scanner(new FileInputStream("Rooms.csv"))) {
            while (in.hasNext()) {
                String line = in.nextLine();
                String[] sl = line.split(",");
                HashMap<String, Integer> exits = new HashMap<>();
                exits.putIfAbsent(sl[5], Integer.parseInt(sl[6]));
                exits.putIfAbsent(sl[7], Integer.parseInt(sl[8]));
                exits.putIfAbsent(sl[9], Integer.parseInt(sl[10]));
                exits.putIfAbsent(sl[11], Integer.parseInt(sl[12]));
                Room r1 = new Room(Integer.parseInt(sl[0]), sl[1], sl[2], Boolean.parseBoolean(sl[3]), exits);
                rooms.add(r1);
            }
            this.rooms = rooms;
        }
        catch (FileNotFoundException e) {
            System.out.println("Cant Find Rooms File");
            System.out.println(e.getMessage());
        }
    }
}
