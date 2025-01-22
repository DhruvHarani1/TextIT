package TextIT;

import java.util.*;

class t {

    public static void main(String[] args) {

        // Classes
        Scanner sc = new Scanner(System.in);
        SignUp[] SU = new SignUp[1]; // object for signup of users
        SignUp tasks = new SignUp(); // to perform task like(COmment modification in future)
        Login LG = new Login(); // to call login class
        MainPage MP = new MainPage(); // to call main page

        // Creating Object of Array of SignUp
        for (int i = 0; i < SU.length; i++) {
            SU[i] = new SignUp();
        }

        // variables
        int totalUser = 0; // it gives the count of passive user
        int userInput; // to select signup/login/exit

        // Main loop to select option signup/login/exit
        do {

            // entry message
            tasks.entryPage();

            userInput = sc.nextInt();

            // switch to select signup/login/exit
            switch (userInput) {
                case 1:
                    if (totalUser >= SU.length) {
                        SU = tasks.growArray(SU);
                    } else {
                        SU[totalUser].signUpDetails(SU);
                    }
                    SU[totalUser].display();
                    SU[totalUser].id(totalUser);
                    totalUser++;
                    break;

                case 2:
                    LG.loginDetail();
                    int id = LG.verifyLogin(SU);
                    MP.homePage(SU[id - 1], SU, totalUser);
                    break;
                case 3:
                    // exit
                    break;

                default:
                    System.out.println(" Please enter correct number!!!");
                    break;
            }
        } while (userInput != 3);
    }
} // end of main class

// class SignUp
class SignUp {

    // variables
    String mobileNumber;
    String userName;
    String goodname = " ";
    String bio = " ";
    String password;
    int ID;
    Post[] P = new Post[100];
    int postcount = 0;
    // String Email;
    // String SecurityQuestion;

    // Classes
    Scanner sc = new Scanner(System.in);

    // Method to take SignUp Details
    public void signUpDetails(SignUp[] SU) {

        // variables
        boolean flag = true;
        String name;
        String Mnumber; // give some other variable name
        String pass;

        // Loop for username
        do {
            System.out.print("Enter Username: ");
            name = sc.nextLine();
            flag = verifyUserName(name, SU);
        } while (!flag);

        {
            userName = name;
        }

        // loop for mobilenumber
        do {
            System.out.print("Enter MobileNumber: ");
            Mnumber = sc.nextLine();
            flag = verifyMobileNumber(Mnumber, SU);

            // for (int i = 0; i < SU.length; i++) {
            // if (Mnumber.equals(SU[i].mobileNumber)) {
            // System.out.println("User already exist go to Login page.");
            // return;
            // }
            // }
        } while (!flag);

        {
            mobileNumber = Mnumber;
        }

        // loop for password
        do {
            System.out.print("Enter Password: ");
            pass = sc.nextLine();
            flag = verifyPassWord(pass);
        } while (!flag);

        {
            password = pass;
        }
    }

    // Method to give id to all users
    void id(int totalUser) {
        ID = totalUser + 1;
    }

    // Method to Grow The array of object of Signup as a New User Comes
    SignUp[] growArray(SignUp[] grow) {

        SignUp[] newUser = new SignUp[grow.length + 1];
        for (int i = 0; i < grow.length; i++) {
            newUser[i] = grow[i];
        }
        newUser[grow.length] = new SignUp();
        newUser[grow.length].signUpDetails(newUser);
        return newUser;
    }

    // Method to display entry point Message
    public void entryPage() {
        System.out.println("#============  LOGIN/SIGNUP  ============#");
        System.out.println("|					 |");
        System.out.println("|	Enter 1 for Signin		 |");
        System.out.println("|	Enter 2 for Login		 |");
        System.out.println("|	Enter 3 for EXIT		 |");
        System.out.println("|				  	 |");
        System.out.println("#========================================#");
        System.out.print("Please enter number : ");
    }

    // Method To Display UserDetails
    public void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("#===========  USER Details  ============#");
        System.out.println("|                                       |");

        System.out.println("|	Username: " + userName + " 		|");
        System.out.println("|	Password: " + password + "		|");
        System.out.println("|	Mobile Number: " + mobileNumber + "       |");

        System.out.println("|                                       |");
        System.out.println("#=======================================#");

        System.out.println("Press enter to go back to login/signup page....");
        sc.nextLine();
    }

    // Method to Verify UserName
    public boolean verifyUserName(String name, SignUp[] SU) {

        if (name.isEmpty()) {
            System.out.println("Please Enter A Valid UserName!");
            return false;
        }
        for (int i = 0; i < SU.length; i++) {
            if (name.equals(SU[i].userName)) {
                System.out.println("Username already exist try other username.");
                return false;
            }
        }
        return true;
    }

    // Method to verify MobileNumber
    public boolean verifyMobileNumber(String MobileNumber, SignUp[] SU) {

        if (MobileNumber.length() != 10) {
            System.out.println("Invalide mobile number it must be of 10 digit retry.");
            return false;
        }

        for (int i = 0; i < MobileNumber.length(); i++) {
            if (MobileNumber.charAt(i) < '0' || MobileNumber.charAt(i) > '9') {
                System.out.println("Invalide mobile number it must contain only digits retry.");
                return false;
            }
        }
        return true;
    }

    // Method To verify Password
    public boolean verifyPassWord(String Password) {

        if (Password.length() < 8 || Password.length() > 16) {
            System.out.println("Invalide password it must have char between 8 to 16.\nretry .");
            return false;
        }
        return true;
    }
}

// Class Login
class Login {

    // classes
    Scanner sc = new Scanner(System.in);

    // variables
    String loginName;
    String loginPassword;

    // Method to take Login details
    public void loginDetail() {

        // variables
        boolean flag = true;

        do {
            flag = true;
            System.out.print("Please enter UserName: ");
            loginName = sc.nextLine();

            if (loginName.isEmpty()) {
                System.out.println("UserName Can't Be Blank");
                flag = false;
            }
        } while (!flag);
        do {
            flag = true;
            System.out.print("Please enter Password: ");
            loginPassword = sc.nextLine();

            if (loginPassword.isEmpty()) {
                System.out.println("Password Can't Be Blank");
                flag = false;
            }
        } while (!flag);
    }

    // Method to verify Login
    public int verifyLogin(SignUp[] SU) {

        boolean flag = false;

        int i;
        for (i = 0; i < SU.length; i++) {
            if (loginName.equals(SU[i].userName) && SU[i].userName != null) {
                if (loginPassword.equals(SU[i].password)) {
                    flag = true;
                }
                break;
            }
        }

        if (flag) {
            // redirect to main page...
            System.out.println("Thank you for login in our Application.");
            return SU[i].ID;
        } else {
            i = 0;
            System.out.println("No User Found\n Either Wrong UserName or PassWord");
            loginDetail();
            verifyLogin(SU);
        }
        return SU[i].ID;
    }
}

// Class MainPage
class MainPage {


    // Method to display Main Page Message
    void homePage(SignUp logedUser, SignUp[] allUser, int totalUser) {

        // classes
        Scanner sc = new Scanner(System.in);
        Profile PF = new Profile();

        // variables
        boolean flag = true;

        // main loop for
        do {

            int randomUser = (int) (Math.random() * totalUser);
            int randomPost = (int) (Math.random() * logedUser.postcount);

            // Design is modified and tested here(Vraj)..
            if (allUser[randomUser].P[randomPost] == null) {
                displayLogo();
                System.out.println("|1) Vraj\t\t\t|");
                System.out.println("|Hi, i am Vraj moving\t\t|");
                System.out.println("|to a new cityin Dubai.\t\t|");
                System.out.println("|I am Glad to be here\t\t|");
                System.out.println("|\t\t\t\t|");
                System.out.println("|-------------------------------|");
                System.out.println("|            ADD(+)\t        |");
                System.out.println("|-------------------------------|");
                System.out.println();
            } else {

                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|\t       TEXTIT    \t      |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                // BY :
                String topLine = "| BY: " + allUser[randomUser].userName
                        + " ".repeat(32 - allUser[randomUser].userName.length()) + "|";
                System.out.println(topLine);
                System.out.println("|                                     |");

                int contentWidth = 38; // Space for padding and borders
                String[] words = allUser[randomUser].P[randomPost].post.split(" ");
                StringBuffer line = new StringBuffer("|");

                for (int i = 0; i < words.length; i++) {
                    if (line.length() + words[i].length() + 1 > contentWidth) {
                        // Fill the remaining spaces in the current line
                        while (line.length() < contentWidth) {
                            line.append(" ");
                        }
                        System.out.println(line + "|");
                        line = new StringBuffer("| " + words[i]); // Start a new line with the current word
                    } else {
                        line.append(" ");
                        line.append(words[i]);
                    }
                }

                // Fill the remaining spaces in the last line
                while (line.length() < contentWidth) {
                    line.append(" ");
                }
                System.out.println(line + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                String left = "Like(*)";
                String center = "Comment(#)";
                String right = "Follow(@)";

                int spaceBetween = (40 - (left.length() + center.length() + right.length())) / 2;

                System.out.println(
                        "| " + left + " ".repeat(spaceBetween - 1) + center + " ".repeat(spaceBetween) + "\b\b\b"
                                + right + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
               
                String left1= "Profile(@)";
                String center1= "ADD(+)";
                String right1 ="Next(>)";
                int spaceBetween1 = (40 - (left1.length() + center1.length() + right1.length()))/2;
                System.out.println("| "+left1 +" ".repeat(spaceBetween1-1)+center1+" ".repeat(spaceBetween1-2)+right1+"|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                // displayOptions();
            }

            // --- TEMPORARY DESIGN (FOR TESTING)----
            // System.out.println("6) Press @ To Follow!!");
            System.out.println("7) Press  ^ To Exit!!");
            System.out.println();
            System.out.println("Enter Your choice :");

            String option = sc.next(); // takes option from above

            switch (option) {
                case "#":
                    allUser[randomUser].P[randomPost].createComment(logedUser);

                    break;
                case "*":
                    break;
                case ">":
                    break;
                case "+":
                    logedUser.P[logedUser.postcount] = new Post();
                    logedUser.P[logedUser.postcount].createPost();
                    logedUser.postcount++;
                    break;
                case "@":
                PF.profilePage(logedUser);
                    break;
                case "^":
                    flag = false;
                    break;

                default:
                    break;
            }
        } while (flag);
    }


    // Method to display LOGO
    void displayLogo() {
        System.out.println("|-------------------------------|");
        System.out.println("|\t    TEXTIT \t        |");
        System.out.println("|-------------------------------|");
    }

}

class Post {

    // variables
    int like;
    String post;
    String[] commenter = new String[100];
    int commentcount;
    String[] comment = new String[100];

    // class
    Scanner sc = new Scanner(System.in);

    // Method to Create Post
    void createPost() {
        System.out.println("Enter Post:");
        post = sc.nextLine();
    }

    // Method to display post
    void displaypost() {
        System.out.println(post);
    }

    // Method to Comment
    void createComment(SignUp loggedUser) {

        // Display 5 Comment At a time and Next Button for Next 5..

        commenter[commentcount] = loggedUser.userName;
        // Display When there is no Comment
        if (comment[0] == null) {
            System.out.println("-------------------------------");
            System.out.println("\t No Comments Yet");
            System.out.println("-------------------------------\n\n");

            displayCommentsChoice();
        } else {
            // Display all the Comments
            for (int i = 0; i < comment.length; i++) {
                if (comment[i] == null) {
                    // Nothing will happen.
                } else {
                    // display all comment

                    System.out.println("-------------------------------");
                    System.out.println("BY:" + commenter[i]);
                    System.out.println("  Replaying to @" + loggedUser.userName + "\n");
                    System.out.println("   " + comment[i]);
                    System.out.println("-------------------------------\n");

                }

            }
            displayCommentsChoice();

        }

    }

    void displayCommentsChoice() {

        System.out.println(" Back(^) \tADD(+)\n\n");
        System.out.print("Enter your Choice:");
        String choice = sc.nextLine();
        System.out.println();
        if (choice.equals("^")) {
            // return to MainPage..
            return;

        } else if (choice.equals("+")) {
            System.out.println("Enter your Comment: ");
            System.out.println("-------------------------------");
            comment[commentcount] = sc.nextLine();
            System.out.println("-------------------------------");
            commentcount++;
        } else {
            System.out.println("Enter a vaid Responce !!");

        }
        displayCommentsChoice();
    }

}

class Profile {

    // Classes
    Scanner sc = new Scanner(System.in);

    // Method of design of Profile
    void profilePage(SignUp SU) {

        int choice;

        do {
            System.out.println("");
            System.out.println("\t\t\t<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println("\t\t\t<>                                               <>");
            System.out.println("\t\t\t<>                  PROFILE                      <>");
            System.out.println("\t\t\t<>                                               <>");
            System.out.println("\t\t\t<*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*><*>");
            System.out.println();
            int boxWidth = 38;
            StringBuffer line = new StringBuffer("|");
            String[] words = SU.bio.split(" ");

            System.out.println("****************************************");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println("| UserName: " + SU.userName + " ".repeat(26 - SU.userName.length()) + " |");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println("| Name: " + SU.goodname + " ".repeat(30 - SU.goodname.length()) + " |");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println("| Bio: " + " ".repeat(boxWidth - 6) + "|");

            for (int i = 0; i < words.length; i++) {
                if (line.length() + words[i].length() + 1 > boxWidth) {
                    while (line.length() <= boxWidth) {
                        line.append(" ");
                    }
                    System.out.println(line + "|");
                    line = new StringBuffer("| " + words[i]);
                } else {
                    line.append(" ");
                    line.append(words[i]);
                }
            }
            // Fill the remaining spaces in the last line
            while (line.length() <= boxWidth) {
                line.append(" ");
            }

            System.out.println(line + "|");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println("****************************************");

            System.out.println("1. Edit Username");
            System.out.println("2. Edit Name");
            System.out.println("3. Edit Bio");
            System.out.println("4. Back to Menu");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    SU.goodname = sc.nextLine();
                    break;
                case 3:
                    System.out.println("Enter Your Bio");
                    SU.bio = sc.nextLine();
                    break;
                case 4:
                    break;

                default:
                    break;
            }
        } while (choice != 4);
    }
}
