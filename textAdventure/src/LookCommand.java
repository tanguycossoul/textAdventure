public class LookCommand extends Command {

    public boolean check() {
        return (player != null);
    }

    public String execute() {
        return help("rooms") + help("creatures") + help("items");
    }

    public String help(String type) {
        String output = "";
        if (type.equals("rooms")) {
            output += "You are in the " + player.getCurrentRoom().getName() + ". You can go to:\n";
            for (String name : player.getCurrentRoom().getNeighborNames()) {
                output += "  - " + name + " (" + player.getLevel().getRoom(name).getDescription() + ")\n";
            }
        }
        else if (type.equals("creatures")) {
            if (player.getLevel().getCreatureNames(player.getCurrentRoom()).length() != 0) {
                output += "Creatures in the room: " + player.getLevel().getCreatureNames(player.getCurrentRoom()) + "\n";
            }
        }
        else if (type.equals("items")) {
            if (player.getCurrentRoom().getItems().size() != 0) {
                output += "Items in the room: " + player.getCurrentRoom().displayItems() + "\n";
            }
            if (player.getItems().size() != 0) {
                output += "You have: " + player.listInventory();
            }
        }
        return output;
    }
}
