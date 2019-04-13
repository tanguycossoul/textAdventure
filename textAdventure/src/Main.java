import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Level level = new Level("level 1");
        Player player = new Player("Tanguy", "the Tan-est of 'em all", level, "hall");

        Scanner in = new Scanner(System.in);
        String response;
        Command cmd;

        // Game loop
        do {
            System.out.println("> What do you want to do?");
            response = in.nextLine();

            try {
                String cmd_name = response.toLowerCase().split(" ")[0];
                String class_name = cmd_name.substring(0, 1).toUpperCase() + cmd_name.substring(1) + "Command";
                Class c = Class.forName( class_name );
                cmd = (Command) c.newInstance();
            } catch (Exception e) {
                cmd = new EmptyCommand();
            }

            if ( !cmd.init( response, player) ) {
                System.out.println( cmd.allHelp() );
            } else {
                System.out.println( cmd.execute() );
            }

            // Creatures
            level.updateAllCreatures();
        } while ( !cmd.quit() );
    }
}