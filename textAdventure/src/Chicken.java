public class Chicken extends Creature {

    public Chicken(Level.Room startRoom) {
        super(startRoom);
        this.name = "chicken";
   }

   @Override
    public void move() {
        moveRandom();
   }

    @Override
    // Chance to peck player and make them drop a random item
    // Display "you get attacked by a wild chicken and drop your <Item>
    public void act() {
        System.out.println("TODO: chicken act()");
    }
}
