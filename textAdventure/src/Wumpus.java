public class Wumpus extends Creature {

    public Wumpus(Level.Room startRoom) {
        super(startRoom);
        setName("wumpus");
   }

   @Override
   public void act() {
        moveAway();
   }
}
