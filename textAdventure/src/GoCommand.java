public class GoCommand extends Command {

    public boolean check() {
        return (player != null) && commandWords.length > 1;
    }

    public String execute() {
        Level.Room destRoom = player.getCurrentRoom().getNeighbor(commandWords[1]);
        if (destRoom == null) { return help("room"); }

        player.moveToRoom(destRoom);
        if (player.getCurrentRoom().getNeighborNames() == null) {
            setQuit( true );
            return help("stuck");
        }
        return help("ok" + "," + commandWords[1]);
    }

    public String help(String type) {
        if (type.contains("ok")) {
            return "You are now in the " + type.split(",")[1] + ".";
        }
        if (type.equals( "room" )) {
            return "Hmm, you can't go there.";
        }
        if (type.equals("stuck")) {
            System.out.println("You're stuck and die there. The end.");
        }
        return "";
    }
}
