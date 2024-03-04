import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    class User {
        private String userID;
        private String userPIN;
        private double accountBalance;

        public User(String userID, String userPIN, double accountBalance) {
            this.userID = userID;
            this.userPIN = userPIN;
            this.accountBalance = accountBalance;
        }

        public String getUserID() {
            return userID;
        }

        public String getUserPIN() {
            return userPIN;
        }

        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }
    }

    class ATM {
        private Map<String, User> users;
        private Scanner scanner;

        public ATM() {
            users = new HashMap<>();
            // Sample user data (in real application, data would come from a database)
            users.put("35698521455", new User("35698521455", "2580", 1000000.0));
            users.put("54851256325", new User("54851256325", "4321", 70000.0));
            scanner = new Scanner(System.in);
        }

        public void start() {
            System.out.println("Welcome !");
            System.out.print("Please enter your user ID: ");
            String userID = scanner.nextLine();
            System.out.print("Please enter your PIN: ");
            String userPIN = scanner.nextLine();

            if (authenticate(userID, userPIN)) {
                System.out.println("Authentication successful. Happy Transaction, " + userID + "!");
                displayMenu(userID);
            } else {
                System.out.println("Authentication failed. Invalid user ID or PIN.");
            }
        }

        private boolean authenticate(String userID, String userPIN) {
            User user = users.get(userID);
            return user != null && user.getUserPIN().equals(userPIN);
        }

        private void displayMenu(String userID) {
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit Money");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: " + users.get(userID).getAccountBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        if (withdrawAmount > 0 && withdrawAmount <= users.get(userID).getAccountBalance()) {
                            double newBalance = users.get(userID).getAccountBalance() - withdrawAmount;
                            users.get(userID).setAccountBalance(newBalance);
                            System.out.println("Withdrawal successful. New balance is: " + newBalance);
                        } else {
                            System.out.println("Invalid amount or insufficient funds.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();
                        if (depositAmount > 0) {
                            double newBalance = users.get(userID).getAccountBalance() + depositAmount;
                            users.get(userID).setAccountBalance(newBalance);
                            System.out.println("Deposit successful. New balance is: " + newBalance);
                        } else {
                            System.out.println("Invalid amount.");
                        }
                        break;
                    case 4:
                        System.out.println(" Thank you & ");
                        System.out.println(" Visit Again ");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }