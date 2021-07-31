# Simple Banking System
 
### About 
System that allows to create a banking account and manage an account passing login authorization.  

The system generates a valid, with accordance to [Luhn Algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm), unique, 16-digit credit card number with a 4-dig pin code. 
These numbers ensure easy payments, and they also help prevent payment errors and fraud. The very last digit of a credit card is the check digit or checksum. It is used to validate the credit card number using the Luhn algorithm, which we will explain in the next stage of this project. For now, the checksum can be any digit you like.

Starting the app, the user is provided with a text menu:
```
1. Create an account
2. Log into account
0. Exit
```
*  First option creates account, giving the user the card number and card pin number. Initial balance of the account is set to 0;
*  Second option asks user to input card number and pin of an existing account to login. As a result user is provided with a new many to manage the account:

```
1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
```

### Technical observations:
The app operates with SQLite database and managed with SQL queries using JDBC API. The application reads the database file name from the command line argument. Filename should be passed to the program using -fileName argument, for example, -fileName db.s3db. The database file is created when the program starts, if it hasn't yet been created. And all created cards are stored in the database.
The accounts are verified by mean of SQL queries as well as adding balance, deleting accounts, reading balance and making save transactions if one of the queries executions 

Example of the app in action:
```1. Create an account
2. Log into account
0. Exit
>1

Your card has been created
Your card number:
4000009455296122
Your card PIN:
1961

1. Create an account
2. Log into account
0. Exit
>1

Your card has been created
Your card number:
4000003305160034
Your card PIN:
5639

1. Create an account
2. Log into account
0. Exit
>2

Enter your card number:
>4000009455296122
Enter your PIN:
>1961

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>2

Enter income:
>10000
Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 10000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160035
Probably you made a mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305061034
Such a card does not exist.

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160034
Enter how much money you want to transfer:
>15000
Not enough money!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160034
Enter how much money you want to transfer:
>5000
Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 5000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit

>0
Bye!
```
