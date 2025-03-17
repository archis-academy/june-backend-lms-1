import java.util.Objects;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    static String loggedInUserId = null;
    static int bookQuantity = 0;
    static int bookLimit = 50;
    static int userLimit = 50;
    static int transactionLimit = 100;
    static int transactionQuantity = 0;
    static int userQuantity = 0;
    static int requestBookQuantity = 0;
    static String[][] reservedBooks = new String[bookLimit][2]; //bookId, userId
    static int reservedQuantity = 0;
    static int requestBookLimit = 50;
    static String[][] books = new String[bookLimit][4]; // title, author, id, additional doc.
    static String[][] users = new String[userLimit][4]; //  name, id, email, password
    static String[][] transactions = new String[transactionLimit][7]; // userId, bookId, date, status, bookTitle, bookAuthor
    static String[][] requestBooks = new String[requestBookLimit][4];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userMenu();
    }

    public static void userMenu() {

        String title, author, bookId, additionalDoc;
        String userId,name, email, password;
        String bookIdOrTitle;

        Scanner scan = new Scanner(System.in);
        System.out.println("--WELCOME TO USER MENU--");

        while (true) {

            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Add Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Update Book");
            System.out.println("6. View Available Book");
            System.out.println("7. View Book Details");
            System.out.println("8. Search Book");
            System.out.println("9. Count Total Books");
            System.out.println("10. Return Book");
            System.out.println("11. Check Book Return Deadline");
            System.out.println("12. Generate Book Recommendations");
            System.out.println("13. Check Out Book");
            System.out.println("14. Reserve Book");
            System.out.println("15. Request Book");
            System.out.println("16. Update User Information");
            System.out.println("17. Delete User Information");
            System.out.println("18. Check User Eligibility For Checkout");
            System.out.println("19. Generate Reports");
            System.out.println("20. Logout");
            System.out.println("21. Exiting User Menu");

            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    signUp();
                    break;

                case "2":
                    login();
                    break;

                case "3":
                    System.out.println("Please enter the book title:");
                    title = scan.nextLine();
                    System.out.println("Please enter the book author:");
                    author = scan.nextLine();
                    System.out.println("Please enter the book Id:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the book additional information:");
                    additionalDoc = scan.nextLine();
                    addBook(title, author, bookId, additionalDoc);
                    break;

                case "4":
                    System.out.println("Please enter the Id number of the book you want to delete:");
                    bookId = scan.nextLine();
                    deleteBook(bookId);
                    break;

                case "5":
                    System.out.println("Please enter the book title for update:");
                    title = scan.nextLine();
                    System.out.println("Please enter the book author for update:");
                    author = scan.nextLine();
                    System.out.println("Please enter the book Id for update:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the book additional information for update:");
                    additionalDoc = scan.nextLine();
                    updateBook(title, author, bookId, additionalDoc);
                    break;

                case "6":
                    viewAvailableBooks();
                    break;

                case "7":
                    System.out.println("Please enter the book Id for view details:");
                    bookId = scan.nextLine();
                    viewBooksDetails(bookId);
                    break;

                case "8":
                    System.out.println("Please enter the book Id or book title for search:");
                    bookIdOrTitle = scan.nextLine();
                    searchBook(bookIdOrTitle);
                    break;

                case "9":
                    countTotalBooks();
                    break;

                case "10":
                    System.out.println("Please enter the book Id  for return:");
                    bookId = scan.nextLine();
                    returnBook(bookId);
                    break;

                case "11":
                    checkBookReturnDeadline();
                    break;

                case "12":
                    System.out.println("Please enter the user Id  for search recommendations:");
                    userId = scan.nextLine();
                    generateBookRecommendations(userId);
                    break;

                case "13":
                    System.out.println("Please enter the book Id  for check out book:");
                    bookId = scan.nextLine();
                    checkOutBook(bookId);
                    break;

                case "14":
                    System.out.println("Please enter the book Id for reserve book:");
                    bookId = scan.nextLine();
                    reserveBook(bookId);
                    break;

                case "15":
                    System.out.println("Please enter the title for request book:");
                    title = scan.nextLine();
                    System.out.println("Please enter the author for request book:");
                    author = scan.nextLine();
                    System.out.println("Please enter the book Id for request book:");
                    bookId = scan.nextLine();
                    System.out.println("Please enter the additional document for request book:");
                    additionalDoc = scan.nextLine();
                    requestBook(title,author,bookId,additionalDoc);
                    break;

                case "16":
                    System.out.println("Please enter the user name for update:");
                    name = scan.nextLine();
                    System.out.println("Please enter the user email for update:");
                    email = scan.nextLine();
                    System.out.println("Please enter the user password for update:");
                    password = scan.nextLine();
                    updateUserInfo(name, email, password);
                    break;

                case "17":
                    deleteUserInfo();
                    break;

                case "18":
                   checkUserEligibilityForCheckout();
                    break;

                case "19":
                    generateReports();
                    break;

                case "20":
                    logout();
                    break;

                case "21":
                    System.out.println("Exiting user menu...");
                    System.exit(0);

            }
        }
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

        successTransaction("Sign Up");
    }

    public static void login() {
        System.out.println("E-mail address:");
        String email = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        int index = invalidLoginCheck(email, password);
        if (index != -1) {
            loggedInUserId = users[index][0];
            successTransaction("Login");
            System.out.println("Login successful! Welcome, " + users[index][0]);
        } else {
            System.out.println("Invalid login! Please check your email or password!");
        }
    }

    public static int invalidLoginCheck(String email, String password) {
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][2].equals(email) && users[i][3].equals(password)) {
                successTransaction("Invalid Login Check");
                return i;
            }
        }
        System.out.println("Invalid login! Please check your email or password!");
        return -1;
    }

    public static void addBook(String title, String author, String bookId, String additionalDoc) {
        if (!isUserLoggedIn()) return;

        if (books.length > bookLimit) {
            System.out.println("Bookshelf is full! Extending the array...");
            extendBooksArrayOnAddition(title, author, bookId, additionalDoc);

        } else {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = bookId;
            books[bookQuantity][3] = additionalDoc;
            bookQuantity++;
            successTransaction("Add Book");
        }
    }

    public static void extendBooksArrayOnAddition(String title, String author, String bookId, String additionalDoc) {
        books = updateArrayAddition(books, title, author, bookId, additionalDoc);
        bookQuantity++;
        System.out.println("Extend Books and Array On Addition transaction successful");
    }

    public static String[][] updateArrayAddition(String[][] array, String column1, String column2, String column3, String column4) {

        int i;
        String[][] arrayNew = new String[(array.length) + 1][4];

        for (i = 0; i < array.length; i++) {
            arrayNew[i][0] = array[i][0];
            arrayNew[i][1] = array[i][1];
            arrayNew[i][2] = array[i][2];
            arrayNew[i][3] = array[i][3];
        }
        arrayNew[array.length][0] = column1;
        arrayNew[array.length][1] = column2;
        arrayNew[array.length][2] = column3;
        arrayNew[array.length][3] = column4;

        return arrayNew;
    }

    public static void deleteBook(String bookId) {
        if (!isUserLoggedIn()) return;

        if (bookQuantity > 0) {
            truncateBooksArrayOnDeletion(bookId);
        } else {
            System.out.println("There is no book that can be deleted!");
        }
    }

    public static void truncateBooksArrayOnDeletion(String bookId) {
        int temp = -1, i;
        for (i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                temp = i;
                bookQuantity -- ;
                books = updateArrayDeletion(books, temp);
                successTransaction("Truncate Books Array On Deletion");
            }
        }
        if (temp == -1) {
            System.out.println("Truncate Books and Array On Deletion transaction failed!");
        }
    }

    public static String[][] updateArrayDeletion(String[][] array, int temp) {

        int i;
        String[][] arrayNew = new String[(array.length) - 1][4];

        for (i = 0; i < temp; i++) {
            arrayNew[i][0] = array[i][0];
            arrayNew[i][1] = array[i][1];
            arrayNew[i][2] = array[i][2];
            arrayNew[i][3] = array[i][3];
        }

        for (i = temp; i < array.length -1; i++) {
            arrayNew[i][0] = array[i + 1][0];
            arrayNew[i][1] = array[i + 1][1];
            arrayNew[i][2] = array[i + 1][2];
            arrayNew[i][3] = array[i + 1][3];
        }
        return arrayNew;
    }

    public static void updateBook(String title, String author, String bookId, String additionalDoc) {
        int temp = -1, i;
        for (i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                books[i][0] = title;
                books[i][1] = author;
                books[i][2] = bookId;
                books[i][3] = additionalDoc;

                successTransaction( "Update Book");
                temp = i;
            }
        }
        if (temp == -1) {
            System.out.println("Book update transaction failed!");
        }
    }

    public static void viewAvailableBooks() {
        if (loggedInUserId == null) {
            System.out.println("You must be logged in to view available books!");
            return;
        }
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

    public static void searchBook(String query) {
        int temp = -1;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][0].equalsIgnoreCase(query) || books[i][2].equalsIgnoreCase(query)) {
                successTransaction( "Search Book");
                System.out.println("--Book Information--");
                System.out.println("Book title : " + books[i][0]);
                System.out.println("Book Author : " + books[i][1]);
                System.out.println("Book ID : " + books[i][2]);
                System.out.println("Book Additional Document : " + books[i][3]);
                temp = i;
            }
        }
        if (temp == -1) {
            System.out.println("The book is not found! ");
        }
    }

    public static void countTotalBooks() {
        System.out.println("Total number of books :" + bookQuantity);
    }

    public static void returnBook(String bookId) {
        if (loggedInUserId == null) {
            System.out.println("You must be logged in to return a book!");
            return;
        }
        int temp = -1;
        String bookTitle = "", bookAuthor = "", additionalDoc = "";

        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][1].equals(bookId)&& transactions[i][0].equals(loggedInUserId)) {
                transactions[i][3] = "RETURNED";
                successTransaction("Return Book");
                temp = i;

                bookTitle = transactions[i][4];
                bookAuthor = transactions[i][5];
                additionalDoc = transactions[i][6];

                break;
            }
        }

        if (temp == -1) {
            System.out.println("Book return transaction failed!");
            return;
        }

        if (transactionQuantity < transactions.length) {
            transactions[transactionQuantity][0] = loggedInUserId;
            transactions[transactionQuantity][1] = bookId;
            transactions[transactionQuantity][2] = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transactions[transactionQuantity][3] = "RETURNED";
            transactions[transactionQuantity][4] = bookTitle;
            transactions[transactionQuantity][5] = bookAuthor;
            transactions[transactionQuantity][6] = additionalDoc;
            transactionQuantity++;
        } else {
            System.out.println("Transaction log is full. Cannot record return transaction.");
        }

        if (bookQuantity < bookLimit) {
            if (books[bookQuantity] == null) {
                books[bookQuantity] = new String[4];
            }
            books[bookQuantity][0] = bookTitle;
            books[bookQuantity][1] = bookAuthor;
            books[bookQuantity][2] = bookId;
            books[bookQuantity][3] = additionalDoc;
            bookQuantity++;
        } else {
            System.out.println("Book return failed: Book list is full.");
            return;
        }

        for (int i = 0; i < reservedQuantity; i++) {
            if (reservedBooks[i][0].equals(bookId)) {
                System.out.println("Book is now available for User: " + reservedBooks[i][1]);

                checkOutBook(bookId);

                for (int j = i; j < reservedQuantity - 1; j++) {
                    reservedBooks[j] = reservedBooks[j + 1];
                }
                reservedBooks[reservedQuantity - 1] = null;
                reservedQuantity--;
                return;
            }
        }
    }

    public static boolean checkBookReturnDeadline() {
        if (!isUserLoggedIn()) return false;

        int transactionIndex = getTransactionIndexByUserId(loggedInUserId);
        if (transactionIndex >= 0) {
            String borrowDateStr = transactions[transactionIndex][2];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate borrowDate = LocalDate.parse(borrowDateStr, dateFormatter);

            LocalDate deadlineDate = borrowDate.plusDays(15);
            LocalDate currentDate = LocalDate.now();

            long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(currentDate, deadlineDate);

            if (daysDifference < 0) {
                System.out.println("The return deadline for this book has passed. You are " + (-daysDifference) + " days late.");
                return false;
            } else {
                System.out.println("You have " + daysDifference + " days left to return this book.");
            }
        } else {
            System.out.println("Transaction not found!");
        }
        return true;
    }

    public static boolean isUserLoggedIn() {
        if (loggedInUserId == null) {
            System.out.println("You must be logged in to perform this action!");
            return false;
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

    public static void generateBookRecommendations(String userId) {
        int i, j, k, x, temp = 0;
        String userBookId = null;
        String userBookAuthor = null;

        for (i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId)) {
                userBookId = transactions[i][1];
            }
        }

        if (userBookId == null) {
            Random data = new Random();
            x = data.nextInt(bookQuantity - 1);
            System.out.println("Name of recommended book :" + books[x][0]);
            System.out.println("Author of recommended book :" + books[x][1]);
        } else {
            for (j = 0; j < bookQuantity; j++) {
                if (books[j][2].equals(userBookId)) {
                    userBookAuthor = books[j][1];
                }
            }
            for (k = 0; k < bookQuantity; k++) {
                if (books[k][1].equals(userBookAuthor) && !Objects.equals(books[k][2], userBookId)) {
                    System.out.println("Name of recommended book :" + books[k][0]);
                    System.out.println("Author of recommended book :" + books[k][1]);
                    temp = temp + 1;
                }
            }
            if (temp == 0) {
                Random data = new Random();
                x = data.nextInt(bookQuantity - 1);
                System.out.println("Name of recommended book :" + books[x][0]);
                System.out.println("Author of recommended book :" + books[x][1]);
            }
        }
    }

    public static void checkOutBook(String bookId) {
        if(loggedInUserId == null){
            System.out.println("You must be logged in to check out a book!");
            return;
        }

        int indexToRemove = getBookIndexByBookId(bookId);

        if (indexToRemove == -1) {
            System.out.println("Transaction failed: Book not found.");
            return;
        }

        if (checkBooks(bookId)) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(dateFormatter);

            if (transactionQuantity >= transactions.length) {
                System.out.println("Transaction failed: Transaction list is full.");
                return;
            }

            String bookTitle = books[indexToRemove][0];
            String bookAuthor = books[indexToRemove][1];
            String additionalDoc = books[indexToRemove][3];

            transactions[transactionQuantity][0] = loggedInUserId;
            transactions[transactionQuantity][1] = bookId;
            transactions[transactionQuantity][2] = formattedDate;
            transactions[transactionQuantity][3] = "BORROWED";
            transactions[transactionQuantity][4] = bookTitle;
            transactions[transactionQuantity][5] = bookAuthor;
            transactions[transactionQuantity][6]= additionalDoc;

            transactionQuantity++;

            successTransaction("Check Out Book");

            for (int i = indexToRemove; i < bookQuantity - 1; i++) {
                books[i] = books[i + 1];
            }
            books[bookQuantity - 1] = null;
            bookQuantity--;
        } else {
            System.out.println("Transaction failed: Book is not available.");
        }
    }

    public static boolean checkBooks(String bookId) {
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                successTransaction("Check Books");
                return true;
            }
        }
        System.out.println("The book is not found.");
        return false;
    }

    public static int getBookIndexByBookId(String bookId) {
        for (int i = 0; i < bookQuantity; i++) {
            if (bookId.equals(books[i][2])) {
                return i;
            }
        }
        System.out.println("Book index not found for bookId: " + bookId);
        return -1;
    }

    public static void reserveBook(String bookId) {
        if (!isUserLoggedIn()) return;

        int availableIndex = isAvailable(bookId);

        if (availableIndex != -1) {
            System.out.println("Book is available, no need to reserve. You can check it out.");
        } else {
            if (reservedQuantity < bookLimit) {
                reservedBooks[reservedQuantity][0] = bookId;
                reservedBooks[reservedQuantity][1] = loggedInUserId;
                reservedQuantity++;
                System.out.println("Book reserved successfully for User: " + loggedInUserId + ". You will be notified when it's available.");
            } else {
                System.out.println("Reservation list is full. Cannot reserve this book.");
            }
        }
    }

    public static void requestBook(String title, String author, String bookId, String additionalDoc) {
        if (!isUserLoggedIn()) return;

        if (requestBookQuantity < requestBooks.length) {
            requestBooks[requestBookQuantity][0] = title;
            requestBooks[requestBookQuantity][1] = author;
            requestBooks[requestBookQuantity][2] = bookId;
            requestBooks[requestBookQuantity][3] = additionalDoc;
            requestBookQuantity++;

            successTransaction("Request Book");
            System.out.println("Your book request has been recorded successfully.");
        } else {
            System.out.println("Book request failed!\nThere is no capacity for request.");
        }
    }

    public static int isAvailable(String bookId) {
        int temp = -1;
        for (int i = 0; i < bookQuantity; i++) {
            if (bookId.equals(books[i][2])) {
                return i;
            }
        }
        return temp;
    }

    public static void updateUserInfo(String newUserName, String newEmail, String newPassword) {
        if (!isUserLoggedIn()) return;

        int userIndex = getUserIndexById(loggedInUserId);

        if (userIndex >= 0) {
            users[userIndex][1] = newUserName;
            users[userIndex][2] = newEmail;
            users[userIndex][3] = newPassword;

            successTransaction("User Info Updated");
            System.out.println("Your information has been successfully updated!");
        } else {
            System.out.println("Error: User not found!");
        }
    }

    public static int getUserIndexById(String userId) {
        int indexOfUser = -1;
        for (int i = 0; i < userQuantity; i++) {
            if (userId.equals(users[i][0])) {
                indexOfUser = i;
                break;
            }
        }
        return indexOfUser;
    }

    public static void deleteUserInfo() {
        if (!isUserLoggedIn()) return;
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(loggedInUserId) && transactions[i][3].equals("BORROWED")) {
                returnBook(transactions[i][1]);
            }
        }
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][0].equals(loggedInUserId)) {
                for (int j = i; j < userQuantity - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[userQuantity - 1] = null;
                userQuantity--;
                loggedInUserId = null;
                successTransaction("User Info Deletion");
                System.out.println("Your account has been successfully deleted.");
                return;
            }
        }
        System.out.println("User not found! Deletion failed.");
    }

    public static void checkUserEligibilityForCheckout() {
        if (!isUserLoggedIn()) return;

        boolean isEligible = checkBookReturnDeadline();

        if (!isEligible) {
            System.out.println("You have an overdue book. You cannot borrow more books until you return it.");
        } else {
            System.out.println("You are eligible to borrow books.");
        }
    }

    public static void generateReports() {
        int i, j, k;
        System.out.println("Total book number:" + bookQuantity);
        System.out.println("\n");
        for (i = 0; i < bookQuantity; i++) {
            System.out.println("Book Title:" + books[i][0]);
            System.out.println("Book Author:" + books[i][1]);
            System.out.println("Book Id:" + books[i][2]);
            System.out.println("Book Additional Doc.:" + books[i][3]);
            System.out.println("\n");
        }

        System.out.println("\nTotal user number:" + userQuantity);
        System.out.println("\n");
        for (j = 0; j < userQuantity; j++) {
            System.out.println("User Id:" + users[j][0]);
            System.out.println("User Name:" + users[j][1]);
            System.out.println("User email:" + users[j][2]);
            System.out.println("User Password:" + users[j][3]);
            System.out.println("\n");
        }

        System.out.println("\nTotal transaction number:" + transactionQuantity);
        System.out.println("\n");
        for (k = 0; k < transactionQuantity; k++) {
            System.out.println("User Id:" + transactions[k][0]);
            System.out.println("Book Id:" + transactions[k][1]);
            System.out.println("Date:" + transactions[k][2]);
            System.out.println("Status:" + transactions[k][3]);
            System.out.println("\n");
        }
    }

    public static void logout() {
        if (loggedInUserId == null) {
            System.out.println("No user is logged in.");
        } else {
            System.out.println("User " + loggedInUserId + " has logged out.");
            loggedInUserId = null;
        }
    }

    public static void successTransaction(String temp) {

        System.out.println(temp + " Transaction Successful! ");
    }
}