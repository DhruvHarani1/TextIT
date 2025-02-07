package TextIT;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Classes
        Scanner sc = new Scanner(System.in);
        SignUp[] SU = new SignUp[1]; // object for signup of users
        SignUp tasks = new SignUp(); // object of signup to perform task of signup page
        Login LG = new Login(); // object for login page
        MainPage MP = new MainPage(); // object for main page

        // Creating Object of Array of SignUp
        for (int i = 0; i < SU.length; i++) {
            SU[i] = new SignUp();
        }

        // variables
        int totalUser = 0; // it gives the count of passive user
        int userInput; // to select signup/login/exit

        // Main loop to select option signup/login/exit
        do {

            // entry message display(signup login exit)
            tasks.entryPage();

            userInput = sc.nextInt(); // user input to select between options

            // switch to select signup/login/exit
            switch (userInput) {
                case 1:
                    if (totalUser >= SU.length) { // to grow the array signup
                        SU = tasks.growArray(SU);
                    } else {
                        SU[totalUser].signUpDetails(SU); // register the user in app
                    }
                    SU[totalUser].display(); // to display the profile of user
                    SU[totalUser].id(totalUser); // to give unique id to every user
                    totalUser++; // increase the couter of passive user
                    break;

                case 2:
                    if (SU[0].userName == null) { // if thereare zero users in app till now
                        System.out.println("No user yet ");
                        System.out.println("Be the first one to use the app ");
                    } else {
                        LG.loginDetail(); // redirecting to login page
                        int id = LG.verifyLogin(SU); // id to know which user has loged in app
                        MP.homePage(SU[id - 1], SU, totalUser); // redirecting to main page (with the loged user SU ,
                                                                // whole array SU and totaluser count)
                        break;
                    }
                case 3:
                    // exit to get out of app
                    break;

                default:
                    System.out.println(" Please enter correct number!!!"); // if input is not between 1 to 3
                    break;
            }
        } while (userInput != 3);
    }
} // end of main class

// class SignUp
class SignUp {

    // variables
    int ID; // give unique id to each user
    int followCount; // number of followers
    int postcount = 0; // number of post
    String userName; // username
    String mobileNumber; // mobilenumber or user
    String password; // password to login
    String goodname = " "; // name of user(username is different for everuser but name can be same)
    String bio = " "; // bio of user
    Post[] P = new Post[100]; // array of object of post of a user
    String[] follower = new String[100]; // array of object of follower of an user
    // String SecurityQuestion;

    // Classes
    Scanner sc = new Scanner(System.in);

    // Method to take SignUp Details
    public void signUpDetails(SignUp[] SU) { // takes details like(username , mobilenumber , password)

        // variables
        boolean flag = true; // to validate the input fields
        String name; // the name input by user
        String Mobilenumber; // mobile number input by user
        String pass; // passwrod input by user

        // Loop until valid username is assigned to user
        do {
            System.out.print("Enter Username: ");
            name = sc.nextLine();
            flag = verifyUserName(name, SU); // to verify if username is available
        } while (!flag);
        {
            userName = name; // set username if valid
        }

        // loop until valid mobile number is assign to user
        do {
            System.out.print("Enter MobileNumber: ");
            Mobilenumber = sc.nextLine();
            flag = verifyMobileNumber(Mobilenumber, SU); // to verify mobilenumber
        } while (!flag);
        {
            mobileNumber = Mobilenumber; // set mobile number if valid
        }

        // loop for password validation
        do {
            System.out.print("Enter Password: ");
            pass = sc.nextLine();
            flag = verifyPassWord(pass); // to verify password
        } while (!flag);
        {
            password = pass; // set password if valid
        }
    }

    // Method to give unique id to all users
    void id(int totalUser) {
        ID = totalUser + 1; // assign id starting from 1;
    }

    // Method to Grow The array of object of Signup as a New User Comes
    SignUp[] growArray(SignUp[] grow) {
        SignUp[] newUser = new SignUp[grow.length + 1]; // new temperory array of signup
        for (int i = 0; i < grow.length; i++) {
            newUser[i] = grow[i]; // to store previous account in new array
        }
        newUser[grow.length] = new SignUp(); // object of new user
        newUser[grow.length].signUpDetails(newUser); // redirecting new user to sign up page
        return newUser; // retrun the new array of object
    }

    // Method to display entry point Message
    public void entryPage() {
        System.out.println("#============  LOGIN/SIGNUP  ============#");
        System.out.println("|					 |");
        System.out.println("|	Enter 1 for SignUp		 |");
        System.out.println("|	Enter 2 for Login		 |");
        System.out.println("|	Enter 3 for EXIT		 |");
        System.out.println("|				  	 |");
        System.out.println("#========================================#");
        System.out.print("Please enter number : ");
    }

    // Method To Display to go back to signup/login/exit page
    public void display() {
        System.out.println("Press enter to go back to login/signup page....");
        sc.nextLine();
    }

    // Method to Verify UserName
    public boolean verifyUserName(String name, SignUp[] SU) {

        if (name.isEmpty()) { // check is input field is empty
            System.out.println("Please Enter A Valid UserName!");
            return false;
        }
        for (int i = 0; i < SU.length; i++) { // to ceheck if user with same username exist before
            if (name.equals(SU[i].userName)) {
                System.out.println("Username already exist try other username.");
                return false;
            }
        }
        return true; // username is valid
    }

    // Method to verify MobileNumber
    public boolean verifyMobileNumber(String MobileNumber, SignUp[] SU) {

        if (MobileNumber.length() != 10) { // to validate digit are 10
            System.out.println("Invalide mobile number it must be of 10 digit retry.");
            return false;
        }

        for (int i = 0; i < MobileNumber.length(); i++) { // to valide are there only integer values
            if (MobileNumber.charAt(i) < '0' || MobileNumber.charAt(i) > '9') {
                System.out.println("Invalide mobile number it must contain only digits retry.");
                return false;
            }
        }
        return true; // mobile number valid
    }

    // Method To verify Password
    public boolean verifyPassWord(String Password) {

        if (Password.length() < 8 || Password.length() > 16) { // to validate password length should be between 8 to 16
            System.out.println("Invalide password it must have char between 8 to 16.\nretry .");
            return false;
        }
        return true; // passwrod is valid
    }
}

// Class Login
class Login {

    // classes
    Scanner sc = new Scanner(System.in);

    // variables
    String loginNameOrNumber; // input field for username/number
    String loginPassword; // input field for password

    // Method to take Login details
    public void loginDetail() {

        // variables
        boolean flag = true; // to validate if user has right credentials(usename,passwrod) to login

        // loop until field is not empty
        do {
            flag = true;
            System.out.print("Please enter UserName/Mobile Number: ");
            loginNameOrNumber = sc.nextLine();

            if (loginNameOrNumber.isEmpty()) { // to ceheck if input field is empty
                System.out.println("\nUserName/Mobile number Can't Be Blank\n");
                flag = false;
            }
        } while (!flag);

        // loop until field is not empty
        do {
            flag = true;
            System.out.print("Please enter Password: ");
            loginPassword = sc.nextLine();

            if (loginPassword.isEmpty()) { // to check if input field is empty
                System.out.println("\nPassword Can't Be Blank\n");
                flag = false;
            }
        } while (!flag);
    }

    // Method to verify Login
    public int verifyLogin(SignUp[] SU) {

        boolean flag = false; // to validate login status

        int i;
        for (i = 0; i < SU.length; i++) {
            if (loginNameOrNumber.equals(SU[i].userName) || loginNameOrNumber.equals(SU[i].mobileNumber)) { // usename
                                                                                                            // match
                if (loginPassword.equals(SU[i].password)) { // passwrod attach to that usename/mobile number match
                    flag = true; // user can login
                }
                break;
            }
        }

        if (flag) { // login successful
            // redirect to main page...
            System.out.println("Thank you for login in our Application.");
            return SU[i].ID; // returning id of loged user
        } else { // login unsucessful
            i = 0;
            System.out.println("No User Found\n Either Wrong UserName or PassWord");
            loginDetail(); // recursion for take input again
            verifyLogin(SU);
        }
        return SU[i].ID; // return id of loged user
    }
}

// Class MainPage
class MainPage {

    // Method to display Main Page Message
    void homePage(SignUp loggedUser, SignUp[] allUser, int totalUser) { // home page of app

        // loggeduser --> the person who just login in app
        // alluser --> ever account on app
        // totaluser --> count os all user in app

        // classes
        Scanner sc = new Scanner(System.in);
        Profile PF = new Profile();

        // variables
        boolean flag = true;

        // main loop for
        do {

            int randomUser = (int) (Math.random() * totalUser); // to select random user from all user
            int randomPost = (int) (Math.random() * loggedUser.postcount); // to select random post from all post on app

            // Design is modified and tested here(Vraj)..
            if (allUser[randomUser].P[randomPost] == null) { // if the chossen number(random) post is not available then
                                                             // this message is shown
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
                String[] words = allUser[randomUser].P[randomPost].post.split(" "); // spliting words of post and
                                                                                    // storring in an array
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
                String left = allUser[randomUser].P[randomPost].likecount + " Like(*)"; // to show like option
                String center = "Comment(#)"; // to show comment option
                String right = allUser[randomUser].followCount + " Follow(@)"; // to show follow option
                int spaceBetween = (40 - (left.length() + center.length() + right.length())) / 2; // spacing between the
                System.out.println("| " + left + " ".repeat(spaceBetween - 1) + center + " ".repeat(spaceBetween)
                        + "\b\b\b" + right + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                String left1 = "Share($)"; // profile option to see your profile
                String center1 = ""; // add post option to add your post
                String right1 = "Next(>)"; // next post option to view next post
                int spaceBetween1 = (40 - (left1.length() + center1.length() + right1.length())) / 2; // to give dynamic
                System.out.println("| " + left1 + " ".repeat(spaceBetween1 - 1) + center1
                        + " ".repeat(spaceBetween1 - 2) + right1 + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("");

                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                String left2 = "Search(&)"; // Exit option to exit the main page
                String center2 = "ADD(+)"; // add Share option to share a post
                String right2 = "Profile(!)"; // search option to search a post
                int spaceBetween2 = (40 - (left2.length() + center2.length() + right2.length())) / 2; // to give dynamic
                System.out.println("| " + left2 + " ".repeat(spaceBetween2 - 1) + center2
                        + " ".repeat(spaceBetween2 - 2) + right2 + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                String left3 = ""; // Exit option to exit the main page
                String center3 = "Exit(^)"; // add Share option to share a post
                String right3 = ""; // search option to search a post
                int spaceBetween3 = (40 - (left3.length() + center3.length() + right3.length())) / 2; // to give dynamic
                System.out.println("| " + left3 + " ".repeat(spaceBetween3 - 1) + center3
                        + " ".repeat(spaceBetween3 - 2) + right3 + "|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

            }

            System.out.println("Enter Your choice :");

            String option = sc.next(); // takes option from above

            switch (option) {
                case "#": // option for comment on a post
                    allUser[randomUser].P[randomPost].createComment(loggedUser);
                    break;
                case "*": // option to like post
                    allUser[randomUser].P[randomPost].likemonitor(loggedUser);
                    break;
                case ">":
                    // next post
                    break;
                case "+": // option to add post
                    loggedUser.P[loggedUser.postcount] = new Post(loggedUser); // object for new post
                    loggedUser.P[loggedUser.postcount].createPost();
                    loggedUser.postcount++;
                    break;
                case "!": // option to view profile
                    PF.profilePage(loggedUser);
                    break;
                case "@": // to follow the user whos post you are seeing
                    allUser[randomUser].P[randomPost].follow(loggedUser, allUser[randomUser]);
                    break;
                case "^": // to go back
                    flag = false;
                    break;
                case "$": // to share a post
                    System.out.println("");
                    System.out.println("Your Sharing Code: " + allUser[randomUser].P[randomPost].shareLink);
                    System.out.println();
                    sc.nextLine();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
                case "&": // to search a p
                    sc.nextLine();
                    System.out.print("Enter Link: ");
                    String search = sc.nextLine().trim();
                    int totalSpace = 40;
                    int spaceLeft = totalSpace - 10;
                    int spaceBetween = spaceLeft - search.length();
                    System.out.println("\t\t" + "-".repeat(totalSpace)); // first
                    System.out.println("\t\t" + "|Search: " + search + " ".repeat(spaceBetween) + "|"); // second
                    System.out.println("\t\t" + "-".repeat(totalSpace));
                    System.out.println(); // third
                    for (int i = 0; i < allUser.length; i++) {
                        for (int j = 0; j < allUser[i].postcount; j++) {
                            if( allUser[i].P[j].shareLink ==null){
                                break;
                            }
                            else if (allUser[i].P[j].shareLink.equals(search) ) {
                                System.out.println("\t\t|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                                System.out.println("\t\t|\t       TEXTIT    \t      |");
                                System.out.println("\t\t|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                                // BY :
                                String topLine = "\t\t| BY: " + allUser[randomUser].userName
                                        + " ".repeat(32 - allUser[randomUser].userName.length()) + "|";
                                System.out.println(topLine);
                                System.out.println("\t\t|                                     |");

                                int contentWidth = 38; // Space for padding and borders
                                String[] words = allUser[randomUser].P[randomPost].post.split(" "); // spliting words of
                                                                                                    // post and
                                                                                                    // storring in an
                                                                                                    // array
                                StringBuffer line = new StringBuffer("\t\t|");

                                for (int k = 0; k < words.length; k++) {
                                    if (line.length() + words[k].length() + 1 > contentWidth) {
                                        // Fill the remaining spaces in the current line
                                        while (line.length() < contentWidth) {
                                            line.append(" ");
                                        }
                                        System.out.println(line + "|");
                                        line = new StringBuffer("\t\t| " + words[k]); // Start a new line with the current
                                                                                  // word
                                    } else {
                                        line.append(" ");
                                        line.append(words[k]);
                                    }
                                }

                                // Fill the remaining spaces in the last line
                                while (line.length() < contentWidth) {
                                    line.append(" ");
                                }
                                System.out.println(line + "|");

                                System.out.println("\t\t|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                                String left = allUser[randomUser].P[randomPost].likecount + " Like(*)"; // to show like
                                                                                                        // option
                                String center = "Comment(#)"; // to show comment option
                                String right = allUser[randomUser].followCount + " Follow(@)"; // to show follow option
                                int spaceBetween1 = (40 - (left.length() + center.length() + right.length())) / 2; // spacing
                                                                                                                  // between
                                                                                                                  // the
                                System.out.println("\t\t| " + left + " ".repeat(spaceBetween1 - 1) + center
                                        + " ".repeat(spaceBetween1) + "\b\b\b" + right + "|");
                                System.out.println("\t\t|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");

                            }
                        }
                    }
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
    int likecount = 0; // like on a post
    int commentcount; // comment on a post
    String post; // the post to write
    String username;
    int id;
    String shareLink = "";
    String[] whoLiked = new String[100]; // th e user who liked the post
    String[] commentby = new String[100]; // the user who comment on post
    String[] comment = new String[100]; // array to store the comment

    // class
    Scanner sc = new Scanner(System.in);

    Post() {
        // default constructor
    }

    Post(SignUp user) {
        username = user.userName;
        id = (int) (Math.random() * 100000000 + 1);
        shareLink = username;
        shareLink = shareLink + id;
    }

    // Method to Create Post
    void createPost() {
        System.out.println("Enter Post:");
        post = sc.nextLine();
    }

    // Method to Comment
    void createComment(SignUp loggedUser) {

        commentby[commentcount] = loggedUser.userName;
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
                    System.out.println("BY:" + commentby[i]);
                    System.out.println("  Replaying to @" + username + "\n");
                    System.out.println("   " + comment[i]);
                    System.out.println("-------------------------------\n");

                }
            }
            displayCommentsChoice();
        }
    }

    void displayCommentsChoice() { // display option like add comment or go back

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

    // Mehtod To like a Post
    void likemonitor(SignUp loggedUser) {
        boolean flag = true;

        whoLiked[likecount] = loggedUser.userName;

        for (int i = 0; i < likecount; i++) { // if a user has arleady liked the post
            if (whoLiked[i].equals(whoLiked[likecount])) {
                System.out.println("You Have already Liked this post");
                System.out.println("Cant like more than Once");
                flag = false;
                break;
            }
        }
        if (flag) {
            likecount++;
        }
    }

    // Mehtod To follow a User using a post
    void follow(SignUp loggedUser, SignUp postUser) {
        boolean flag = true;

        for (int i = 0; i < postUser.followCount; i++) { // if user has arleady followed the account
            if (postUser.follower[i].equals(loggedUser.userName)) {
                System.out.println("Already a follower");
                flag = false;
                break;
            }
        }
        if (flag) {
            if (postUser.userName.equals(loggedUser.userName)) {
                System.out.println("Can't follow Your Self");
                flag = false;
            }
        }

        if (flag) {
            postUser.follower[postUser.followCount] = loggedUser.userName;
            postUser.followCount++;
        }
    }
}

class Profile {

    // Classes
    Scanner sc = new Scanner(System.in);

    // Method of design of Profile
    void profilePage(SignUp loggedUser) {

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
            String[] words = loggedUser.bio.split(" ");

            System.out.println("****************************************");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println(
                    "| UserName: " + loggedUser.userName + " ".repeat(26 - loggedUser.userName.length()) + " |");
            System.out.println("|" + " ".repeat(boxWidth) + "|");
            System.out.println("| Name: " + loggedUser.goodname + " ".repeat(30 - loggedUser.goodname.length()) + " |");
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
                    loggedUser.goodname = sc.nextLine();
                    break;
                case 3:
                    System.out.println("Enter Your Bio");
                    loggedUser.bio = sc.nextLine();
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }
}
