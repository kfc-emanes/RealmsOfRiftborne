import Menu.*;

public class Main {
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        CharacterMenu characterMenu = new CharacterMenu();
        Menu mainMenu = new Menu();

        boolean shouldStartGame = startMenu.displayStartMenu();

        if (!shouldStartGame) {
            return;
        }

        characterMenu.chooseCharacterMenu();

        while (true) {
            mainMenu.defaultMainMenu();
        }
    }
}