import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Frog frog = new Frog();
        Scanner scanner = new Scanner(System.in);
        String command;
        String[] arrCommand;
        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;
        int index = 0;

        while (true) {
            printCondition();
            command = scanner.nextLine();

            if ("0".equals(command)) {
                break;
            }

            if ("<<".equals(command)) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }
            } else if ("!!".equals(command)) {
                if (curCommand == -1) {
                    System.out.println("Нечего повторять!");
                } else {
                    commands.get(curCommand).doIt();
                }
            } else if (">>".equals(command)) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего повторять");
                } else {
                    curCommand++;
                    commands.get(curCommand).doIt();
                }
            } else {
                arrCommand = command.split("");
                if ("+".equals(arrCommand[0])) {
                    index = strToInt(arrCommand);
                    FrogCommand cmd = FrogCommands.jumpRightCommand(frog, index);
                    curCommand++;
                    commands.add(cmd);
                    cmd.doIt();
                } else if ("-".equals(arrCommand[0])) {
                    index = strToInt(arrCommand);
                    FrogCommand cmd = FrogCommands.jumpLeftCommand(frog, index);
                    curCommand++;
                    commands.add(cmd);
                    cmd.doIt();
                } else {
                    System.out.println("Неизвестная команда");
                }
            }
            
            System.out.println(frog.position);
        }


    }

    private static void printCondition() {
        System.out.println("+N - прыгни на N шагов направо");
        System.out.println("-N - прыгни на N шагов налево");
        System.out.println("<< - Undo (отмени последнюю команду)");
        System.out.println(">> - Redo (повтори отменённую команду)");
        System.out.println("!! - повтори последнюю команду");
        System.out.println("0 - выход");
    }

    private static int strToInt(String[] str) {
        int i = 0;
        if (str.length > 2) {
            String s = "";
            for (String j : str) {
                s = s + j;
            }
            i = Integer.parseInt(s);
        } else {
            i = Integer.parseInt(str[1]);
        }
        return i;
    }
}
