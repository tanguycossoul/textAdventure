public class TakeCommand extends Command {

    public boolean check() {
        return (player != null) && (commandWords.length >= 2);
    }

    public String execute() {
        Item item = player.getCurrentRoom().removeItem( commandWords[1] );
        if (item == null) { return help("no item"); }

        player.addItem( item );
        return help("ok");
    }

    public String help(String type) {
        if (type.equals( "ok" )) {
            return "You now have: " + player.listInventory() + ".";
        }
        else if (type.equals( "no item" )) {
            return "Hmm, couldn't take this item.";
        }
        return "";
    }
}
