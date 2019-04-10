public class Chicken extends Creature {

    public Chicken(Level.Room startRoom) {
        super(startRoom);
        setName("chicken");
   }

    @Override
    // Chance to peck player and make them drop a random item
    // Display "you get attacked by a wild chicken and drop your <Item>
    public void act() {
        moveRandom();
        attack();
    }

    public void attack() {
        if ( getCurrentRoom().getRandPlayer() != null ) {
            getCurrentRoom().getRandPlayer().dropRandomItem(this.getName());
        }
    }
}
