import javafx.collections.SetChangeListener;

import java.util.ArrayList;

public abstract class Creature {
    protected String name;
    protected Level.Room currentRoom;
    protected double ODDS_ATTACK = 0.5;

    public Creature() {
    }

    public Creature(Level.Room startRoom) {
        currentRoom = startRoom;
    }

    public abstract void move();
    public abstract void act();

    // TODO: test
    protected void moveRandom() {
        ArrayList<Level.Room> neighbors = currentRoom.getNeighbors();
        Level.Room next = neighbors.get( (int) ( neighbors.size() * Math.random() ) );

        currentRoom.removeCreature( this );
        setCurrentRoom( next );
        currentRoom.addCreature( this );
    }

    protected void moveAway() {
        ArrayList<Level.Room> rooms_avail = new ArrayList<>();
        for (Level.Room r : currentRoom.getNeighbors() ) {
            if ( !r.hasPlayer() ) { rooms_avail.add( r ); }
        }

        // Case where cannot move: no room with no player
        if (rooms_avail.size() < 1) { return;}

        Level.Room next = rooms_avail.get( (int) ( Math.random() * rooms_avail.size() ) );

        currentRoom.removeCreature( this );
        setCurrentRoom( next );
        currentRoom.addCreature( this );
    }

    protected void moveCloser() {
        // 1-step
        for (Level.Room r : currentRoom.getNeighbors() ) {
            if ( r.hasPlayer() ) {
                setCurrentRoom( r );
                return;
            }
        }
        // 2-step
        for (Level.Room r1 : currentRoom.getNeighbors() ) {
            for (Level.Room r2 : r1.getNeighbors() ) {
                if ( r2.hasPlayer() ) {
                    setCurrentRoom(r1);
                    return;
                }
            }
        }
    }

    // TODO:
    // - add ArrayList<Player> in Level.Room
    // - add a dropRandomItem() to Player
//    public void actAttack() {
//        System.out.println("You've been attacked by a " + this.getName());
//        if (Math.random() < ODDS_ATTACK) {
//            int item_ind = (int) (Math.random() * inventory.size());
//            System.out.println("You've been hit and you drop a " + item_dropped);
//        } else {
//            System.out.println("You've pared the attack, good job.");
//        }
//    }

    private Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }
}