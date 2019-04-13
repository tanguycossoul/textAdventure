import java.util.ArrayList;

public abstract class Command {
    protected String[] commandWords;
    protected Player player;
    private boolean quit = false;

    public abstract boolean check();
    public abstract String execute();
    public abstract String help(String type);

    public boolean init ( String commandLine, Player player ) {
        commandWords = commandLine.toLowerCase().split(" ");
        this.player = player;
        return check();
    }

    public boolean quit() {
        return quit;
    }

    protected void setQuit(boolean val) {
        quit = val;
    }

    // TODO Automate subclasses adding their own help message into a hashmap
    public String allHelp() {
        return  "Hmm, I didn't get that. You can ask:\n" +
                "  \"go <roomname>\" to go to a room\n" +
                "  \"look\" to display the neighbors\n" +
                "  \"add <room> <description>\" to add a room\n" +
                "  \"take <item>\" to pick up an item from the room\n" +
                "  \"drop <item>\" to drop an item in the room\n" +
                "  \"quit\" to leave the game";
    }
}
