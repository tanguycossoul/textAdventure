public class QuitCommand extends Command {

    public boolean check() {
        return true;
    }

    public String execute() {
        setQuit( true );
        return help("bye");
    }

    public String help(String type) {
        return "Ok, bye.";
    }
}
