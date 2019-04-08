public class Popstar extends Creature {

    public Popstar(Level.Room startRoom) {
        super(startRoom);
        this.name = "popstar";
   }

   @Override
    public void move() {
        moveCloser();
   }

    public void act() {
        System.out.println("TODO: Popstar act()");
    }
}
