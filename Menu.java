import java.util.Scanner;

// Things to polish: 
// Mechanism of the menu in terms of hopping from one menu to another
// Restriction on some functions
// Correct the repetition in narration restrictions
// Other function needed:
// Shops
// Inventory
class Menu extends Narration {
    static Scanner scanner = new Scanner(System.in);

    //Change these variables to private once encasulation is implemented (i think)
    static boolean hasVisitedShop = false;
    static boolean hasOpenedInventory = false;
    static boolean hasVisitedPrologue = false;
    static boolean hasVisitedAcademy = false;
    static boolean hasVisitedLibrary = false;
    static boolean hasVisitedCanteen = false;
    static boolean hasVisitedGym = false;
    static boolean hasVisitedOffice = false;
    static boolean hasVisitedArea1 = false;
    static boolean hasVisitedArea2 = false;
    static boolean hasVisitedArea3 = false;

    public static void defaultMainMenu(){
        boolean defaultMainMenu = true;
        
        while(defaultMainMenu){
            System.out.println("\n--- MYSTVALE ACADEMY MAIN MENU ---");
            System.out.println("1. Go to Academy");
            System.out.println("2. Shop");
            System.out.println("3. Inventory");
            System.out.println("4. Area 1");
            System.out.println("5. Area 2");
            System.out.println("6. Area 3");
            System.out.println("7. Exit Game");
            System.out.print("Enter your choice (1-7): ");
            
            String mainMenuChoice = scanner.nextLine();
            
            System.out.println();
            
            switch (mainMenuChoice){
                case "1":
                    if(!hasVisitedAcademy){
                        academyNarration();
                        hasVisitedAcademy = true;
                    } 
                    
                    System.out.println("------------------------");
                    System.out.println("Where do you want to go?");
                    System.out.println("------------------------");
                    academyMapMenu();
                    
                    break;

                case "2":
                    if(!hasVisitedShop){
                        shopNarration();
                        hasVisitedShop = true;
                    } 
                    
                    System.out.println("--------------------");
                    System.out.println("Welcome to the shop");
                    System.out.println("--------------------");
                    
                    
                    //shopFunction();
                    break;

                case "3":
                    if(!hasOpenedInventory){
                        inventoryNarration();
                        hasOpenedInventory = true;
                    } 
                    
                    boolean isInventoryEmpty = false;
                    
                    if(!isInventoryEmpty){
                        System.out.println("Hmmm. Nothing to see here.");
                        System.out.println("Go shop if you want to own items.");
                    } else{
                        //inventoryFunction();
                    }
                    break;

                case "4":
                    
                    // add restriction
                    
                    if(!hasVisitedArea1){
                        area1Narration();
                        hasVisitedArea1 = true;
                    }
                    
                    System.out.println("------------------------");
                    System.out.println("Beware of forest entities");
                    System.out.println("------------------------");
                
                    break;
                    
                case "5":
                    
                    // add restriction
                    
                    if(!hasVisitedArea2){
                        area2Narration();
                        hasVisitedArea2 = true;
                    }
                    
                    System.out.println("------------------------");
                    System.out.println("Beware of swamp entities");
                    System.out.println("------------------------");
                    
                    break;
                    
                case "6":
                    
                    // add restriction
                    
                    if(!hasVisitedArea3){
                        area3Narration();
                        hasVisitedArea3 = true;
                    }
                    
                    System.out.println("------------------------------------------");
                    System.out.println("Warning! You may or may not come out alive");
                    System.out.println("------------------------------------------");
                    
                    break;

                case "7":
                    System.out.println("\nAre you sure you want to quit playing? (y/n)");
                    
                    char ifWantToQuit = scanner.next().charAt(0);
                    char toLowerChoice = Character.toLowerCase(ifWantToQuit);
                    
                    if(toLowerChoice == 'y'){
                        System.out.println("Good Game!");
                        defaultMainMenu = false;
                        break;
                    } else if(toLowerChoice == 'n'){
                        System.out.println("Returning to Main Menu...");
                        break;
                    } else{
                        System.out.println("Invalid input. Try again!");
                    }
                    
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        
    }
    
    public static void academyMapMenu() {
        boolean academyMapMenu = true;

        while (academyMapMenu) {
            System.out.println("\n--- MYSTVALE ACADEMY MAP MENU ---");
            System.out.println("1. Library");
            System.out.println("2. Canteen");
            System.out.println("3. Gym");
            System.out.println("4. Principal's Office");
            System.out.println("5. Exit Current Location");
            System.out.println("6. Main Menu");
            System.out.print("Enter your choice (1-6): ");

            String academyMapMenuChoice = scanner.nextLine();

            switch (academyMapMenuChoice) {
                case "1":
                    if(!hasVisitedLibrary){
                        libraryNarration();
                        hasVisitedLibrary = true;
                    }
                    
                    System.out.println("------------------------------------");
                    System.out.println("Shh! Be mindful, others are studying");
                    System.out.println("------------------------------------");
                    
                    break;

                case "2":
                    if(!hasVisitedCanteen){
                        canteenNarration();
                        hasVisitedCanteen = true;
                    }
                    
                    System.out.println("--------------------");
                    System.out.println("Buy at your own risk");
                    System.out.println("--------------------");
                
                    break;

                case "3":
                    if(!hasVisitedGym){
                        gymNarration();
                        hasVisitedGym = true;
                    }
                    
                    System.out.println("----------------------------------");
                    System.out.println("Are you ready to be physically fit?");
                    System.out.println("----------------------------------");
                    
                    break;

                case "4":
                    
                    // add restrictions
                    
                    if(!hasVisitedOffice){
                        principalOfficeNarration();
                        hasVisitedOffice = true;
                    }
                    
                    System.out.println("-----------------------------");
                    System.out.println("May the odds be in your favor");
                    System.out.println("-----------------------------");
                    
                    break;

                case "5":
                    System.out.println("\nYou have left your current location. For now.");
                    academyMapMenu = false;
                    
                    // Polish this part
                    //defaultMainMenu();
                    //academyMapMenu();
                    break;
                    
                case "6":
                    defaultMainMenu();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        
    }
}
