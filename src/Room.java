import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private ArrayList<Item> items = new ArrayList<>();
    int roomNumber;
    private String name;
    private String description;
    boolean visited;
    private Map<String, Integer> exits;  // Directions and corresponding room numbers

    // Constructor
    public Room(int roomNumber, String name, String description) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.description = description;
        this.visited = false;
        this.exits = new HashMap<String, Integer>();
    }


    public Map<String, Integer> getExits() {
        return this.exits;
    }
    public String getDescription() {
        return this.description;
    }
    public ArrayList<Item> getItems() {
        return this.items;
    }
    public String getName() {
        return this.name;
    }

    // Method to add an exit
    public void addExit(String direction, int roomNumber) {
        exits.put(direction, roomNumber);
    }

    // Method to mark the room as visited
    public void visit() {
        visited = true;
    }


    // This method returns an ArrayList of the exits for a room by iterating through each key in the rooms hashmap
    public ArrayList<String> getExitsArrayList() {
        ArrayList<String> listOfExits = new ArrayList<>();

        for(String exit : exits.keySet()) {
            listOfExits.add(exit);
        }
        return listOfExits;
    }

    public void explore() {
        System.out.print("[");
        for (Item i : this.items) {
            if (this.items.getLast() == i) System.out.print(i.getName());
            else System.out.print(i.getName() + ", ");
        }
        System.out.println("]");
    }

    public int removeItemFromRoom(Item item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
            return 1;
        }
        return 0;
    }
    public void addItemToRoom(Item item) {
        this.items.add(item);
    }
}
