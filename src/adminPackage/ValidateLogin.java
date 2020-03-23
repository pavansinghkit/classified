package adminPackage;

import DBQueries.DBQueryExecuter;
import app.ClassifiedApp;
import service.ClassifiedService;
import service.impl.ClassifiedServiceImpl;

import java.util.Scanner;

import static java.lang.System.exit;

public class ValidateLogin implements ValidateLoginInterface {
    private String username, password, choice = "Y";
    private int option = 1;
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }
    public void setChoice(String choice){ this.choice = choice; }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getChoice(){return this.choice;}

    public void login() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        //Validate UserName
        choice = "Y";
        while("Y".equalsIgnoreCase(choice)){
        System.out.println("Enter User Name");
            username = sc.nextLine();
        if ("Admin".equals(username)) {
            System.out.println("Enter Password");
            password = sc.nextLine();
            if ("Password".equals(password)) {
                System.out.println(" **********  Welcome Admin! *********** ");
                System.out.println("Please choose one of the below options");
                while(option !=5) {
                    System.out.println("1) Activate/Deactivate User");
                    System.out.println("2) Approve/Disapprove Classifieds");
                    System.out.println("3) Add/Remove Classifieds");
                    System.out.println("4) Generate Report");
                    System.out.println("5) Logout");
                    System.out.println();
                    System.out.print("Enter Option [1-5]: ");
                    option = sc.nextInt();
                    switch(option) {
                        case 1:
                            System.out.println("1. Enter \"A\" to Activate User");
                            System.out.println("2. Enter \"B\" to De-activate User");
                            String ch = sc.next();
                            displayAndUpdateUsers(sc,ch);
                            break;
                        case 2:
                            System.out.println("1. Enter \"A\" to Approve Classified");
                            System.out.println("2. Enter \"B\" to Disapprove Classified");
                            ch= sc.next();
                            displayAndUpdateClassifieds(sc,ch);
                            break;
                        case 3:
                            ClassifiedService classifiedService = new ClassifiedServiceImpl();
                            System.out.println(" Enter \"A\" to Post a Classified");
                            System.out.println(" Enter \"B\" to Remove a Classified");
                            ch = sc.next();
                            if("A".equalsIgnoreCase(ch)) {
                                ClassifiedApp.createClassifiedAdmin(classifiedService, sc);
                                System.out.println("Classified Added Successfully!");
                                System.out.println("");
                                Thread.sleep(400);
                                System.out.println("Taking back to the Main Menu:");
                                Thread.sleep(500);
                            } if ("B".equalsIgnoreCase(ch)) {
                                System.out.println("Classified Removed Successfully!");
                                System.out.println("");
                                Thread.sleep(400);
                                System.out.println("Taking back to the Main Menu:");
                                Thread.sleep(500);
                            }
                            break;
                        case 4:
                            System.out.println("Generate Report");
                            System.out.println("Report Generated Successfully!");
                            System.out.println("");
                            Thread.sleep(400);
                            System.out.println("Taking back to the Main Menu:");
                            Thread.sleep(500);
                            break;
                        case 5:
                            System.out.println("Confirm Logout? Enter \"Y\" to continue or \"N\" to stay on this page");
                            ch = sc.next();
                            if("Y".equalsIgnoreCase(ch)) {
                                System.out.println("Logout Successful!");
                                System.out.println("");
                                Thread.sleep(250);
                                exit(0);
                            } if ("N".equalsIgnoreCase(ch)) {
                                Thread.sleep(250);
                                break;
                    }
                    break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + option);
                    }}
            } else {
                System.out.println("Incorrect Password");
                System.out.println("Would you like to continue? Enter Y or N");
                choice = sc.nextLine();
                }
        } else {
            System.out.println("Incorrect Username");
            System.out.println("Would you like to continue? Enter Y or N");
            choice = sc.nextLine();
            }
        }
        System.out.println("Taking back to the Main Menu");
        option = 5;
    }

    private void displayAndUpdateUsers(Scanner sc, String status) {
        String showStatus = "A".equalsIgnoreCase(status) ? "UA" : "A";
        String updateStatus = "A".equalsIgnoreCase(status) ? "A" : "D";
        System.out.println("-------- Here's a list of Users with current status -------");
        DBQueryExecuter.getUsersData(showStatus);
        System.out.println("Enter a \"Username\" to approve");
        String username = sc.next();
        DBQueryExecuter.updateUsersData(username, updateStatus);
        System.out.println("Do you wish to Continue?");
        System.out.println("Enter 1 to Continue");
        System.out.println("Enter 2 to go back to the previous menu");
        System.out.println("Enter 3 to go back to the main menu");
        System.out.println("Enter 4 to Logout");
        String choose = sc.next();
        if("Y".equalsIgnoreCase(choose)) {
            System.out.println("User(s) "+updateStatus+" Successfully!");
            System.out.println("");
        } else {
            return;
        }
        System.out.println("Taking back to the Main Menu:");
    }

    private void displayAndUpdateClassifieds(Scanner sc, String status) {
        String showStatus = "A".equalsIgnoreCase(status) ? "NA" : "A";
        String updateStatus = "A".equalsIgnoreCase(status) ? "ACTIVE" : "DEACTIVATED";
        System.out.println("-------- Here's a list of classifieds -------");
        DBQueryExecuter.getClassifiedsData(showStatus);
        System.out.println("Enter a \"Classified Id\" to change status");
        int classified_id = sc.nextInt();
        DBQueryExecuter.updateClassifiedsData(classified_id, updateStatus);
        System.out.println("Do you wish to Continue?");
        System.out.println("Enter 1 to Continue");
        System.out.println("Enter 2 to go back to the previous menu");
        System.out.println("Enter 3 to go back to the main menu");
        System.out.println("Enter 4 to Logout");
        String choose = sc.next();
        if("Y".equalsIgnoreCase(choose)) {
            System.out.println("Classified(s) "+updateStatus+" changed Successfully!");
            System.out.println("");
        } else {
            return;
        }
        System.out.println("Taking back to the Main Menu:");
    }
}

