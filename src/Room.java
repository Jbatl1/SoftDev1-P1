import java.util.HashMap;
import java.util.Map;

public class Room {
    int roomNumber;
    String name;
    String description;
    boolean visited;
    Map<String, Integer> exits;  // Directions and corresponding room numbers

    // Constructor
    public Room(int roomNumber, String name, String description, boolean visited, HashMap<String, Integer> exits) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.description = description;
        this.visited = visited;
        this.exits = exits;
    }
}
