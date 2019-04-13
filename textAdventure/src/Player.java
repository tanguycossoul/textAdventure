import java.util.ArrayList;

public class Player {

    private String name;
    private String description;
    private Level level;
    private ArrayList<Item> items = new ArrayList<>();
    private Level.Room currentRoom;

    public Player(String name, String description, Level level, String roomName) {
        this.name = name;
        this.description = description;
        this.level = level;
        moveToRoom( level.getRoom( roomName ) );
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem( String name ) { // remove item and return it
        for (Item item : items) {
            if (item.getName().equals( name )) {
                items.remove( item );
                return item;
            }
        }
        return null;
    }

    public boolean destroyItem( String name ) { // remove item without returning it
        return removeItem(name) != null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String listInventory() { // return items player is carrying
        String output = "";
        if (items == null) {
            output = "none";
        } else {
            for (int i = 0; i < items.size(); i++) {
                output += items.get(i).getName();
                if (i < items.size() - 1) {
                    output += ", ";
                }
            }
        }
        return output;
    }

    public Item dropRandomItem(String name) {
        if (items.size() < 1) { return null; }
        Item item = items.get( (int) (Math.random() * items.size()) );
        items.remove( item );
        currentRoom.addItem( item );
        System.out.println("You've been attacked by a " + name + ". You've dropped the " + item.getName());
        return item;
    }

    public Level.Room getCurrentRoom() { // return room player is currently in
        return this.currentRoom;
    }

    public boolean moveToRoom(Level.Room room_dst) { // set player’s current room to a new room
        if (room_dst == null) { return false; }
        if (currentRoom != null) {
            currentRoom.removePlayer(this);
        }
        room_dst.addPlayer( this );
        currentRoom = room_dst;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
