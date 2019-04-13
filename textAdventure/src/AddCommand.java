public class AddCommand extends Command {

    public boolean check() {
        return (player != null) && (commandWords.length >= 3);
    }

    public String execute() {
        if (player.getLevel().getRoom( commandWords[1] ) != null) { return help("room"); }

        String description = "";
        for (int i = 2; i < commandWords.length; i++) {
            description += commandWords[i] + " ";
        }
        player.getLevel().addRoom(commandWords[1], description);

        player.getLevel().addEdgeBidir(player.getCurrentRoom(), player.getLevel().getRoom(commandWords[1]));

        return help("ok");
    }

    public String help(String type) {
        if (type.equals("ok")) {
            String output = ("You can now go to:\n");
            for (String name : player.getCurrentRoom().getNeighborNames() ) {
                output += "- " + name + " (" + player.getLevel().getRoom(name).getDescription() + ")\n";
            }
            return output;
        }
        else if (type.equals("room")) {
            return "Hmm. Cannot find that room. Try another.";
        }
        return "";
    }
}
