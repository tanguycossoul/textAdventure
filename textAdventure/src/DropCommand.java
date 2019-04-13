public class DropCommand extends Command {

    public boolean check() {
        return (player != null) && commandWords.length >= 2;
    }

    public String execute() {
        Item item = player.removeItem( commandWords[1] );
        if (item == null) {
            return help("no item");
        } else {
            player.getCurrentRoom().addItem( item );
            return help("ok");
        }
    }

    public String help(String type) {
        if (type.equals("ok")) {
            return "ok, dropped.";
        }
        if (type.equals("no item")) {
            return "Hmm, you don't have that on you.";
        }
        return "";
    }
}
