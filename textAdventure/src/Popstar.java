public class Popstar extends Creature {

    public Popstar(Level.Room startRoom) {
        super(startRoom);
        setName("popstar");
   }

   @Override
    public void act() {
        moveCloser();
   }
}
