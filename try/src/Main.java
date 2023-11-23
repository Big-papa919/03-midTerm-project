import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to run the code? (yes/no)");
        String userResponse = scanner.next();

        if ("yes".equalsIgnoreCase(userResponse)) {
            LaunchPage launchPage = new LaunchPage();
            launchPage.Success();
            launchPage.thanks();
            System.out.println("------------------------");
            System.out.println();
        } else {
            System.out.println("Code execution aborted by user");
        }
    }
}
