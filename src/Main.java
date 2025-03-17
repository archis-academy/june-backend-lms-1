import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    static int Index = 50;
    static String[][] books = new String[Index][4]; // title , author , id , additional doc.
    static String[][] users = new String[Index][4]; // id , name , email, password
    static String[][] transactions = new String[Index][4]; // userId , bookId , date , status
    static String[][] requestBooks = new String[Index][4];
    static int bookQuantity = 0;
    static int transactionQuantity = 0;
    static int userQuantity = 0;
    static int requestBookQuantity = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static void userMenu() {


        String title, author, bookId, additionalDoc;
        String userId, name, email, password;
        String bookIdOrTitle;

        Scanner scan = new Scanner(System.in);
        System.out.println("--WELCOME TO USER MENU--");

        while (true) {

            String choice = scan.nextLine();

            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Update Book");
            System.out.println("4. View Available Book");
            System.out.println("5. View Book Details");
            System.out.println("6. Search Book");
            System.out.println("7. Count Total Books");
            System.out.println("8. Return Book");
            System.out.println("9. Check Book Return Deadline");
            System.out.println("10. Generate Book Recommendations");
            System.out.println("11. Check Out Book");
            System.out.println("12. Reserve Book");
            System.out.println("13. Update User Info");
            System.out.println("14. Delete User Information");
            System.out.println("15. Check User Eligibility For Checkout");
            System.out.println("16. Generate Reports");
            System.out.println("17. Exiting User Menu");

            switch (choice) {
                case "1":
                    System.out.println("Please enter the book title:");
                    title = scan.nextLine();
                    System.out.println("Please enter the book author:");
                    author = scan.nextLine();
                    System.out.println("Please enter the book Id:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the book aditional information:");
                    additionalDoc = scan.nextLine();
                    addBook("title", "author", "bookId", "additionalDoc");
                    break;

                case "2":
                    System.out.println("Please enter the Id number of the book you want to delete:");
                    bookId = scan.nextLine();
                    deleteBook("bookId");
                    break;

                case "3":
                    System.out.println("Please enter the book title for update:");
                    title = scan.nextLine();
                    System.out.println("Please enter the book author for update:");
                    author = scan.nextLine();
                    System.out.println("Please enter the book Id for update:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the book aditional information for update:");
                    additionalDoc = scan.nextLine();
                    updateBook("title", "author", "bookId", "additionalDoc");
                    break;

                case "4":
                    viewAvailableBooks();
                    break;

                case "5":
                    System.out.println("Please enter the book Id for view details:");
                    bookId = scan.nextLine();
                    viewBooksDetails("bookId");
                    break;

                case "6":
                    System.out.println("Please enter the book Id or book title for search:");
                    bookIdOrTitle = scan.nextLine();
                    searchBook("bookIdOrTitle");
                    break;

                case "7":
                    countTotalBooks();
                    break;

                case "8":
                    System.out.println("Please enter the book Id  for return:");
                    bookId = scan.nextLine();
                    returnBook("bookId");
                    break;

                case "9":
                System.out.println("Please enter the user Id  for check book return deadline:");
                userId = scan.nextLine();
                checkBookReturnDeadline(userId);
                    break;

                case "10":
                    System.out.println("Please enter the user Id  for search recommendations:");
                    userId = scan.nextLine();
                    generateBookRecommendations("userId");
                    break;

                case "11":
                    System.out.println("Please enter the user Id  for check out book:");
                    userId = scan.nextLine();
                    System.out.println("Please enter the book Id  for check out book:");
                    bookId = scan.nextLine();
                    checkOutBook("userId", "bookId");
                    break;

                case "12":
                    System.out.println("Please enter the book Id for reserve book:");
                    bookId = scan.nextLine();
                    reserveBook(bookId);
                    break;

                case "13":
                    System.out.println("Please enter the user name for update:");
                    title = scan.nextLine();
                    System.out.println("Please enter the user Id for update:");
                    author = scan.nextLine();
                    System.out.println("Please enter the user emal for update:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the user password for update:");
                    additionalDoc = scan.nextLine();
                    updateUserInfo("name", "userId", "email", "password");
                    break;

                case "14":
                    System.out.println("Please enter the user Id for delete usr informaition:");
                    userId = scan.nextLine();
                    System.out.println("Please enter the book Id  for delete user information:");
                    bookId = scan.nextLine();
                    deleteUserInfo("userId", "bookId");
                    break;

                case "15":
                   checkUserEligibilityForCheckout();
                    break;

                case "16":
                    generateReports();
                    break;

                case "17":
                    System.out.println("Exiting user menu:");
                    System.exit(0);

                
            }
        
        }
    }

    
    public static void addBook(String title, String author, String bookId, String additionalDoc) {
        if (bookQuantity < Index) {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = bookId;
            books[bookQuantity][3] = additionalDoc;
            bookQuantity++;

            succesTransaction("Add Book");

        } else {
            System.out.println("We cannot add books to shelves that are already full!");

        }
    }

    public static void viewAvailableBooks() {
        if (bookQuantity == 0) {
            System.out.println("There isn't available books!");
        } else {
            System.out.println("Available books:");
            for (int i = 0; i < bookQuantity; i++) {
                printBooks(books[i][0], books[i][1], books[i][2], books[i][3]);
            }
        }
    }

    private static void printBooks(String title, String author, String bookId, String additionalDoc) {
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Additional Document: " + additionalDoc);
    }

    //Total number of books
    public static void countTotalBooks() {
        System.out.println("Total number of books :" + bookQuantity);
    }

    public static void checkOutBook(String userId, String bookId) {
        if (checkBooks(bookId)) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(dateFormatter);

            transactions[transactionQuantity][0] = userId;
            transactions[transactionQuantity][1] = bookId;
            transactions[transactionQuantity][2] = formattedDate;
            transactionQuantity++;

            succesTransaction("Check Out Book");
        } else {
            System.out.println("Transaction failed");
        }
        int indexToRemove = getBookIndexByBookId(bookId);
        for (int i = indexToRemove; i < bookQuantity - 1; i++) {
            books[i] = books[i + 1];
        }
        books[bookQuantity - 1] = null;
        bookQuantity--;

    }

    public static int getBookIndexByBookId(String bookId) {
        int indexOfBook = -1;
        for (int i = 0; i < bookQuantity; i++) {
            if (bookId.equals(books[i][2])) {
                indexOfBook = i;
                break;
            }
        }
        return indexOfBook;
    }

    public static boolean checkBooks(String bookId) {
        boolean found = false;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                succesTransaction("Check Books");
                found = true;
                break;
            }
            if (!found) {
                System.out.println("The book is not found.");
            }

        }
        return found;
    }


    public static void signUp() {
        System.out.println("User Name :");
        String userName = scanner.nextLine();


        System.out.println("User ID :");
        String userId = scanner.nextLine();

        System.out.println("Email address :");
        String email = scanner.nextLine();

        System.out.println("Password :");
        String password = scanner.nextLine();

        users[userQuantity][0] = userName;
        users[userQuantity][1] = userId;
        users[userQuantity][2] = email;
        users[userQuantity][3] = password;
        userQuantity++;

        succesTransaction("Sign Up");

    }

    public static int login() {
        System.out.println("E-mail address:");
        String email = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        int index = invalidLoginCheck(email, password);
        if (index != -1) {
            succesTransaction("Login");
            return index;
        }
        return -1;
    }


    public static int invalidLoginCheck(String email, String password) {
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][2].equals(email) && users[i][3].equals(password)) {
                succesTransaction("Invalid Login Check");
                return i;
            }
        }
        System.out.println("Invalid login! Please check your email or password!");
        return -1;
    }

    public static void updateUserInfo(String userName, String userId, String email, String password) {
        int userIndex = getUserIndexById(userId);
        if (userIndex >= 0) {
            users[userIndex][0] = userName;
            users[userIndex][1] = userId;
            users[userIndex][2] = email;
            users[userIndex][3] = password;

            succesTransaction("Update User Information");
        } else {
            System.out.println("User not found!");
        }
    }


    public static int getUserIndexById(String userId) {
        int indexOfUser = -1;
        for (int i = 0; i < userQuantity; i++) {
            if (userId.equals(users[i][1])) {
                indexOfUser = i;
                break;
            }
        }
        return indexOfUser;

    }

    public static void searchBook(String query) { // Search with title or Id
        int temp = -1;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][0].equals(query) || books[i][2].equals(query)) {
                succesTransaction("Search Book");
                System.out.println("--Book Information--");
                System.out.println("Book title : " + books[i][0]);
                System.out.println("Book Author : " + books[i][1]);
                System.out.println("Book ID : " + books[i][2]);
                System.out.println("Book Additional Document : " + books[i][3]);
                temp = i;
            }
        }
        if (temp == -1) {
            System.out.println("The book is not found : ");
        }
    }

    public static void updateBook(String title, String author, String bookId, String additionalDoc) {
        int temp = -1, i;
        for (i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                books[i][0] = title;
                books[i][1] = author;
                books[i][2] = bookId;
                books[i][3] = additionalDoc;

                succesTransaction("Update Book");
                temp = i;
            }
        }
        if (temp == -1) {
            System.out.println("Book update transaction failed!");
        }
    }


    public static boolean checkBookReturnDeadline(String userId) {
        int transactionIndex = getTransactionIndexByUserId(userId);
        if (transactionIndex >= 0) {
            String borrowDateStr = transactions[transactionIndex][2];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate borrowDate = LocalDate.parse(borrowDateStr, dateFormatter);

            LocalDate deadlineDate = borrowDate.plusDays(15);
            LocalDate currentDate = LocalDate.now();

            if (currentDate.isAfter(deadlineDate)) {
                System.out.println("The return deadline for this book has passed. You cannot borrow book.");
                return false;
            }
        } else {
            System.out.println("Transaction not found!");
        }
        return true;
    }

    public static int getTransactionIndexByUserId(String userId) {
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkUserEligibilityForCheckout() {
        System.out.println("User Id:");
        String userId = scanner.nextLine();

        if (!checkBookReturnDeadline(userId)) {
            System.out.println("You have an overdue book. You cannot borrow more books until you return the overdue book.");
            return false;
        } else {
            System.out.println("You are eligible to borrow books.");
            return true;
        }
    }


    public static void extendBooksArrayOnAddition(String title, String author, String bookId, String additionalDoc) {
        int temp = -1, i, j;
        Scanner scan = new Scanner(System.in);
        if (Index <= bookQuantity) {
            temp = Index;
            Index = Index + 1;
            System.out.println("Enter the title of the book you want to add:");
            title = scan.nextLine();
            System.out.println("Enter the author of the book you want to add:");
            title = scan.nextLine();
            System.out.println("Enter the bookId of the book you want to add:");
            title = scan.nextLine();
            System.out.println("Enter the additional information of the book you want to add:");
            title = scan.nextLine();
            updateArrayAddition(books, title, author, bookId, additionalDoc);
            System.out.println("Extend Books and Array On Addition transaction successful");
        }
        if (temp == -1) {
            System.out.println("Extend Books and Array On Addition transaction failed!");
        }
    }


    public static void returnBook(String bookId) {

        int temp = -1, i;
        for (i = 0; i < transactionQuantity; i++) {
            if (transactions[i][1].equals(bookId)) {
                transactions[i][3] = "RETURNED";
                succesTransaction("Return Book");
                temp = i;
            }
        }
        if (temp == -1) {
            System.out.println("Book return transaction failed!");
        }

    }

    public static void deleteBook(String bookId) {
        if (bookQuantity > 0) {
            truncateBooksArrayOnDeletion(bookId);
        } else {
            System.out.println("There is no book that can be erased!");
        }
    }

    public static void truncateBooksArrayOnDeletion(String bookId) {
        int temp = -1, i, j;
        for (i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                temp = i;
                bookQuantity = bookQuantity - 1;
                updateArrayDeletion(books, temp);
                succesTransaction("Truncate Books Array On Deletion");
            }
        }
        if (temp == -1) {
            System.out.println("Truncate Books and Array On Deletion transaction failed!");
        }

    }


    public static void deleteUserInfo(String userId, String bookId) {
        if (transactionQuantity > 0) {
            returnBook(bookId);
            int temp = -1, i, j;
            for (i = 0; i < userQuantity; i++) {
                if (users[i][0].equals(userId)) {
                    temp = i;
                    userQuantity = userQuantity - 1;
                    truncateBooksArrayOnDeletion("bookId");

                    succesTransaction("User Info Deletion");
                }
            }
            if (temp == -1) {
                System.out.println("Truncate User and Array On Deletion transaction failed!");
            }
        } else {
            System.out.println("There is no user that can be erased!");
        }

    }


    public static void generateBookRecommendations(String userId) {

        int i, j, k, x, temp = 0;
        String userbookId = null;
        String userbookAuthor = null;

        for (i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId)) {
                userbookId = transactions[i][1];
            }
        }

        if (userbookId == null) {
            Random veri = new Random();
            x = veri.nextInt(bookQuantity - 1);
            System.out.println("Name of recommended book :" + books[x][0]);
            System.out.println("Author of recommended book :" + books[x][1]);

        } else {
            for (j = 0; j < bookQuantity; j++) {
                if (books[j][2].equals(userbookId)) {
                    userbookAuthor = books[j][1];
                }
            }
            for (k = 0; k < bookQuantity; k++) {
                if (books[k][1].equals(userbookAuthor) && books[k][2] != (userbookId)) {
                    System.out.println("Name of recommended book :" + books[k][0]);
                    System.out.println("Author of recommended book :" + books[k][1]);
                    temp = temp + 1;
                }

            }
            if (temp == 0) {
                Random veri = new Random();
                x = veri.nextInt(bookQuantity - 1);
                System.out.println("Name of recommended book :" + books[x][0]);
                System.out.println("Author of recommended book :" + books[x][1]);
            }

        }


    }


    public static void viewBooksDetails(String bookId) {

        int i;
        for (i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                System.out.println("Name of the book :" + books[i][0]);
                System.out.println("Author of the book :" + books[i][1]);
                System.out.println("Additional information of the book :" + books[i][3]);

            }
        }

    }


    public static void requestBook(String title, String author, String bookId, String additionalDoc) {
        if (requestBookQuantity < Index) {
            requestBooks[requestBookQuantity][0] = title;
            requestBooks[requestBookQuantity][1] = author;
            requestBooks[requestBookQuantity][2] = bookId;
            requestBooks[requestBookQuantity][3] = additionalDoc;
            requestBookQuantity++;

            succesTransaction("Request Book");

        } else {
            System.out.println("Book request added failed!\nThere is no capacity for request.");

        }

    }

    public static void generateReports() {
        int i, j, k;
        System.out.println("Total book number:" + bookQuantity);
        System.out.println("\n");
        for (i = 0; i < bookQuantity; i++) {
            System.out.println("Book Title:" + books[i][0]);
            System.out.println("Book Id:" + books[i][1]);
            System.out.println("Book Author:" + books[i][2]);
            System.out.println("Book Additional Doc.:" + books[i][3]);
            System.out.println("\n");
        }

        System.out.println("\nTotal user number:" + userQuantity);
        System.out.println("\n");
        for (j = 0; j < userQuantity; j++) {
            System.out.println("User Id:" + books[j][0]);
            System.out.println("User Name:" + books[j][1]);
            System.out.println("User email:" + books[j][2]);
            System.out.println("User Password:" + books[j][3]);
            System.out.println("\n");
        }

        System.out.println("\nTotal transaction number:" + transactionQuantity);
        System.out.println("\n");
        for (k = 0; k < transactionQuantity; k++) {
            System.out.println("User Id:" + books[k][0]);
            System.out.println("Book Id:" + books[k][1]);
            System.out.println("Date:" + books[k][2]);
            System.out.println("Status:" + books[k][3]);
            System.out.println("\n");
        }
    }


    public static int isAvailable(String bookId) {
        int temp = -1;
        for (int i = 0; i < books.length; i++) {
            if (bookId.equals(books[i][2]) && bookQuantity > 0) {
                temp = i;
            }
        }
        return temp;
    }


    public static void reserveBook(String bookId) {

        int response = isAvailable(bookId);
        if (response != -1) {
            succesTransaction("Reserve Book");
        } else {
            System.out.println("This book is not available in the library.");
        }


    }

    public static void updateArrayDeletion(String[][] array, int temp) {

        int i;
        String[][] arrayNew = new String[(array.length) - 1][4];
        for (i = 0; i < temp; i++) {
            arrayNew[i][0] = array[i][0];
            arrayNew[i][1] = array[i][1];
            arrayNew[i][2] = array[i][2];
            arrayNew[i][3] = array[i][3];
        }

        for (i = temp; i < array.length; i++) {
            arrayNew[i][0] = array[i + 1][0];
            arrayNew[i][1] = array[i + 1][1];
            arrayNew[i][2] = array[i + 1][2];
            arrayNew[i][3] = array[i + 1][3];
        }
        array = arrayNew;
    }

    public static void updateArrayAddition(String[][] array, String column1, String column2, String column3, String column4) {

        int i;
        String[][] arrayNew = new String[(array.length) + 1][4];
        for (i = 0; i < array.length; i++) {
            arrayNew[i][0] = array[i][0];
            arrayNew[i][1] = array[i][1];
            arrayNew[i][2] = array[i][2];
            arrayNew[i][3] = array[i][3];
        }
        arrayNew[array.length - 1][0] = column1;
        arrayNew[array.length - 1][1] = column2;
        arrayNew[array.length - 1][2] = column3;
        arrayNew[array.length - 1][3] = column4;

        array = arrayNew;
    }

    public static void succesTransaction(String temp) {
        System.out.println(temp + " Transaction Successfully: ");
    }

}