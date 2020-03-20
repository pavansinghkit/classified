package adminPackage;

import DBQueries.DBQueryExecuter;

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
                System.out.println("Welcome Admin! Please choose one of the below options");
                while(option !=5) {
                    Thread.sleep(250);
                    System.out.println("1) Activate/Deactivate User");
                    Thread.sleep(250);
                    System.out.println("2) Approve/Disapprove Classifieds");
                    Thread.sleep(250);
                    System.out.println("3) Add/Remove Classifieds");
                    Thread.sleep(250);
                    System.out.println("4) Generate Report");
                    Thread.sleep(250);
                    System.out.println("5) Logout");
                    System.out.println();
                    System.out.print("Enter Option [1-5]: ");
                    option = sc.nextInt();
                    switch(option) {
                        case 1:
                            System.out.println("Enter \"A\" to Activate or Enter \"B\" to Deactivate User");
                            String ch = sc.next();
                            if("A".equalsIgnoreCase(ch)) {
                                System.out.println("Here's a list of Users with Pending state:");
                                Thread.sleep(400);
                                DBQueryExecuter.getUsersData();
                                Thread.sleep(400);
                                System.out.println("Enter \"Y\" to approve");
                                String choose = sc.next();
                                if("Y".equalsIgnoreCase(choose)) {
                                    System.out.println("User(s) Activated Successfully!");
                                    System.out.println("");
                                    Thread.sleep(400);
                                } else {
                                    break;
                                }
                                System.out.println("Taking back to the Main Menu:");
                                Thread.sleep(500);
                                break;
                            } else if ("B".equalsIgnoreCase(ch)) {
                                System.out.println("User Deactivated Successfully!");
                                System.out.println("");
                                Thread.sleep(400);
                                System.out.println("Taking back to the Main Menu:");
                                Thread.sleep(500);
                                break;
                                } else {
                                System.out.println("Invalid Entry");
                                Thread.sleep(250);
                                break;
                                }
                        case 2:
                            System.out.println("Enter \"A\" to Approve or Enter \"B\" to Disapprove a Classified");
                            ch = sc.next();
                            if("A".equalsIgnoreCase(ch)) {
                                System.out.println("Here's a list of classifieds with pending status");
                                Thread.sleep(400);
                                DBQueryExecuter.getClassifiedsData();
                                System.out.println("Enter \"Y\" to approve all the classifieds");
                                String choose = sc.next();
                                if("Y".equalsIgnoreCase(choose)) {
                                System.out.println("Classified Approved Successfully!");
                                System.out.println("");
                                Thread.sleep(400);} else {
                                    System.out.println("Taking back to the Main Menu:");
                                    Thread.sleep(500);
                                }
                            } if ("B".equalsIgnoreCase(ch)) {
                                System.out.println("Classified Disapproved Successfully!");
                                System.out.println("");
                                Thread.sleep(400);
                                System.out.println("Taking back to the Main Menu:");
                                Thread.sleep(500);
                                break;
                            } else {
                            break;}
                        case 3:
                            System.out.println("Enter \"A\" to Add a Classified or Select \"B\" to Remove a Classified");
                            ch = sc.next();
                            if("A".equalsIgnoreCase(ch)) {
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
}

