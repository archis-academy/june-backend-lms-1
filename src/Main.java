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

    public static void addBook(String title, String author, String bookId, String additionalDoc) {
        if (bookQuantity < Index) {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = bookId;
            books[bookQuantity][3] = additionalDoc;
            bookQuantity++;

            System.out.println(bookQuantity + "." + " Book added successfully");

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

            System.out.println("Transaction successful");
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
                System.out.println("The book is found!");
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

        System.out.println("Signup Successful.");

    }

    public static int login() {
        System.out.println("E-mail address:");
        String email = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        int index = invalidLoginCheck(email, password);
        if (index != -1) {
            System.out.println("Login successful!");
            return index;
        }
        return -1;
    }


    public static int invalidLoginCheck(String email, String password) {
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][2].equals(email) && users[i][3].equals(password)) {
                System.out.println("Login successful!");
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

            System.out.println("User information has been updated successfully.");
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
                System.out.println("The book was found :");
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
                System.out.println("Book update transaction successful");
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
            }}
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
        if (Index <= bookQuantity) {
            Index = Index + 1;
            bookQuantity = bookQuantity + 1;
            String[][] booksNew = new String[bookQuantity][4];
            for (i = 0; i < bookQuantity; i++) {
                for (j = 0; j < bookQuantity - 1; j++) {
                    booksNew[j][0] = books[j][0];
                    booksNew[j][1] = books[j][1];
                    booksNew[j][2] = books[j][2];
                    booksNew[j][3] = books[j][3];
                }
                booksNew[bookQuantity - 1][0] = title;
                booksNew[bookQuantity - 1][1] = author;
                booksNew[bookQuantity - 1][2] = bookId;
                booksNew[bookQuantity - 1][3] = additionalDoc;

                books = booksNew;
                System.out.println("Extend Books and Array On Addition transaction successful");
            }
            if (temp == -1) {
                System.out.println("Extend Books and Array On Addition transaction failed!");
            }
        }
    }


    public static void returnBook(String bookId) {

        int temp = -1, i;
        for (i = 0; i < transactionQuantity; i++) {
            if (transactions[i][1].equals(bookId)) {
                transactions[i][3] = "RETURNED";
                System.out.println("Book return transaction successful");
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
                String[][] booksNew = new String[bookQuantity][4];
                for (j = 0; j < temp; j++) {
                    booksNew[j][0] = books[j][0];
                    booksNew[j][1] = books[j][1];
                    booksNew[j][2] = books[j][2];
                    booksNew[j][3] = books[j][3];
                }

                for (j = temp; j < bookQuantity; j++) {
                    booksNew[j][0] = books[j + 1][0];
                    booksNew[j][1] = books[j + 1][1];
                    booksNew[j][2] = books[j + 1][2];
                    booksNew[j][3] = books[j + 1][3];
                }
                books = booksNew;
                System.out.println("Truncate Books and Array On Deletion transaction successful");
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
                    String[][] usersNew = new String[userQuantity][4];
                    for (j = 0; j < temp; j++) {
                        usersNew[j][0] = users[j][0];
                        usersNew[j][1] = users[j][1];
                        usersNew[j][2] = users[j][2];
                        usersNew[j][3] = users[j][3];
                    }

                    for (j = temp; j < userQuantity; j++) {
                        usersNew[j][0] = users[j + 1][0];
                        usersNew[j][1] = users[j + 1][1];
                        usersNew[j][2] = users[j + 1][2];
                        usersNew[j][3] = users[j + 1][3];
                    }
                    users = usersNew;
                    System.out.println("Truncate User and Array On Deletion transaction successful");
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

            System.out.println("Book request added successfully");

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
            System.out.println("Book is Available.Book reservation completed successfully.");
        } else {
            System.out.println("This book is not available in the library.");
        }


    }
}