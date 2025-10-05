public class Item {
    private int id;
    private String name;
    private String description;





    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void pickup(Player player) {
        int remove = player.getCurrentRoom().removeItemFromRoom(this);
        if (remove > 0) {
            System.out.println(this.name + " has been picked up from the room and successfully added to the player inventory.");
            player.addToInv(this);
        }
        else System.out.println("there is no " + this.name + " in this room" );
    }

    public void inspect() {
        System.out.println(this.description);
    }

    public void drop(Player player) {
        int remove = player.removeItemFromInv(this);
        if(remove > 0) {
            System.out.println(this.name + " has been dropped successfully from the player inventory and placed in " + player.getCurrentRoom().getName());
            player.getCurrentRoom().addItemToRoom(this);
        }

        else System.out.println("You don't have a " + this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}