package TextIT;

import java.util.*;

public class Main {

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
                    //MP.displayMainPage(); // It Display the Main Page and Give Option After Login(Sucessfully).
                    //MP.call(SU, id);
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
    String password;
    int id;
    Post[] P = new Post[100];
    // String Email;
    // String SecurityQuestion;

    // Classes
    Scanner sc = new Scanner(System.in);

    // Method to take SignUp Details
    public void signUpDetails(SignUp[] SU) {

        // variables
        boolean flag = true;
        String name;
        String Mnumber; //give some other variable name
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
            //     if (Mnumber.equals(SU[i].mobileNumber)) {
            //         System.out.println("User already exist go to Login page.");
            //         return;
            //     }
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
        id = totalUser + 1;
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

        if(name.isEmpty()){
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
        System.out.print("Please enter UserName: ");
        loginName = sc.nextLine();

        System.out.print("Please enter Password: ");
        loginPassword = sc.nextLine();
    }

    // Method to verify Login
    public int verifyLogin(SignUp[] SU) {

        boolean flag = false;

        if (loginName.isEmpty()) {
            System.out.println("UserName Can't Be Blank");
            loginDetail();
        }
        if (loginPassword.isEmpty()) {
            System.out.println("Password Can't Be Blank");
            loginDetail();
        }

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
            return SU[i].id;
        } else {
            System.out.println("No User Found\n Either Wrong UserName or PassWord");
            loginDetail();
            verifyLogin(SU);
        }
        return 0;
    }
}

//Class MainPage
class MainPage{

}

class Post{

}