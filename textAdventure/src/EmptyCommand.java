public class EmptyCommand extends Command {

    public boolean check() {
        return true;
    }

    public String execute() {
        return help("");
    }

    public String help(String type) {
        return allHelp();
    }
}
