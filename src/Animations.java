/**
 * Animation Class
 *
 * Houses static methods for printing animations.
 */
public class Animations {
    private static int sleepTime = 200;

    /**
     * Prints the licence to kill ASCII art to the console
     */
    public static void printLicenceToKill() {
        String[] art = {
                "===================================================================",
                "||                                                               ||",
                "||  █     █  ███  █████ █████ █   █  ████ █████     █████  ███   ||",
                "||  █     █   █   █     █     ██  █  █    █           █   █   █  ||",
                "||  █     █   █   █     ████  █ █ █  █    ████        █   █   █  ||",
                "||  █     █   █   █     █     █  ██  █    █           █   █   █  ||",
                "||  █████ █  ███  █████ █████ █   █  ████ █████       █    ███   ||",
                "||                                                               ||",
                "||                    █   █  ███  █     █                        ||",
                "||                    ██  █   █   █     █                        ||",
                "||                    ████    █   █     █                        ||",
                "||                    █  ██   █   █     █                        ||",
                "||                    █   █  ███  █████ █████                    ||",
                "||                                                               ||",
                "===================================================================",
        };
        printArt(art, "\u001B[1;31m");
    }

    /**
     * Prints the Double O Seven ASCII art to the console
     */
    public static void printDoubleOSevenArt() {
        String[] art = {
                "  .d8888b.   .d8888b.  d8888888P",
                "d88P  Y88b d88P  Y88b      d88P",
                "888    888 888    888     d88P",
                "888    888 888    888    d88P",
                "888    888 888    888   d88P",
                "888    888 888    888  d88P",
                "Y88b  d88P Y88b  d88P d88P",
                " \"Y8888P\"   \"Y8888P\"  d88P"
        };

        printArt(art, "\u001B[37m");
    }

    /**
     * Prints an array of strings to the console
     * @param art Array of strings to print
     * @param colour Needs to be a valid ANSI colour code
     */
    private static void printArt(String[] art, String colour) {
        for (String line : art) {
            System.out.println(colour + line);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("ERROR: " + e);
            }
        }
        System.out.print("\u001B[0m");
    }
}
