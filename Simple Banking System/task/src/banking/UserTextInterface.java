package banking;

import banking.db.DataBase;
import banking.logic.LuhnCardGenerator;
import java.util.Scanner;

public class UserTextInterface {
    private final Scanner scanner;
    private String cardNumber;
    private String pin;

    public UserTextInterface(Scanner scanner) {
        this.scanner = scanner;

    }

    public void start() {
        mainTextUI();
    }

    private void mainTextUI() {
        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {
                while (true) {
                    Account cardAccount = new Account();
                    this.cardNumber = cardAccount.getCardNumber().toString();
                    this.pin = cardAccount.getPinCode().toString();
                    int balance = cardAccount.getBalance();

                    if (DataBase.verifyAccount(cardNumber, pin)) {
                        continue;
                    }
                    DataBase.insert(cardNumber, pin, balance);
                    break;
                }
                System.out.println();
                System.out.println("Your card has been created");
                System.out.println("Your card number:\n" + cardNumber);
                System.out.println("Your card PIN:\n" + pin);
                System.out.println();

            } else if (input == 2) {
                String inputCardNumber;

                    System.out.println("Enter you card number:");
                    inputCardNumber = scanner.nextLine();

                System.out.println("Enter your PIN:");
                String inputPIN = scanner.nextLine();
                if (DataBase.verifyAccount(inputCardNumber, inputPIN)) {
                    System.out.println("You have successfully logged in!");
                    submenuLogIn(inputCardNumber);
                    return;
                } else {
                    System.out.println("Wrong card number or PIN!");
                }
            } else if (input == 0) {
                System.out.println("Buy!");
                return;
            }
            System.out.println();
        }

    }

    private void submenuLogIn(String inputCardNumber) {
        while (true) {
            System.out.println("1. Balance");
            System.out.println("2. Add Income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");

            int input = Integer.parseInt(scanner.nextLine());
            System.out.println();

            if (input == 1) {
                System.out.println("Balance: " + DataBase.read(inputCardNumber));
            } else if (input == 2) { // DEPOSIT  ADD MONEY
                System.out.println("Enter income:");
                int money = Integer.parseInt(scanner.nextLine());

                if (DataBase.addBalance(inputCardNumber, Math.abs(money)) > 0 ) {
                    System.out.println("Income was added!");
                } else {
                    System.out.println("Failed to add income!");
                }
                System.out.println();
            } else if (input == 3) { // DO TRANSFER
                System.out.println("Transfer\n" + "Enter card number:");
                StringBuilder transferCardNumber = new StringBuilder(scanner.nextLine());
                if (transferCardNumber.toString().equals(inputCardNumber)) {
                    System.out.println("You can't transfer money to the same account!");
                } else if (!LuhnCardGenerator.luhnNumberVerify(transferCardNumber)) {
                    System.out.println("Probably you made a mistake in the card number." +
                            " Please try again!");
                } else if (DataBase.exists(transferCardNumber.toString())) {
                    System.out.println("Enter how much money you want to transfer:");
                    int moneyToTransfer = Integer.parseInt(scanner.nextLine());
                    if (moneyToTransfer > DataBase.read(inputCardNumber)) {
                        System.out.println("Not enough money!");
                    } else {
                        DataBase.makeTransaction(inputCardNumber,transferCardNumber.toString(), moneyToTransfer);
                        System.out.println("Success!");
                    }
                } else {
                    System.out.println("Such a card does not exist.");
                }

            } else if (input == 4) { // CLOSE ACCOUNT
                DataBase.closeAccount(inputCardNumber);
                if (!DataBase.exists(inputCardNumber)) {
                    System.out.println("The account has been closed!");
                } else {
                    System.out.println("Failed to close the account!");
                }
            } else if (input == 5) {
                System.out.println("You have successfully logged out!");
                System.out.println();
                mainTextUI();
            } else if (input == 0) {
                System.out.println("Buy!");
                return;
            }
            System.out.println();
        }
    }
}