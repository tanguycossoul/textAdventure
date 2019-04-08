public class Wumpus extends Creature {

    public Wumpus(Level.Room startRoom) {
        super(startRoom);
        this.name = "wumpus";
   }

   @Override
   public void move() {
        moveAway();
   }
   public void act() {
        System.out.println("TODO: Wumpus act()");
    }
}
