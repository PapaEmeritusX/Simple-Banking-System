package banking;


import banking.logic.LuhnCardGenerator;


public class Account {
    private StringBuilder cardNumber;
    private StringBuilder pinCode;
    private int balance;

    public Account() {
        LuhnCardGenerator register = new LuhnCardGenerator();
        this.cardNumber = register.generateCardNumber();
        this.pinCode = register.generatePinCode();
        this.balance = 0;
    }

    public StringBuilder getPinCode() {
        return pinCode;
    }

    public StringBuilder getCardNumber() {
        return cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return this.cardNumber.equals(account.cardNumber);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
