import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    static int Index = 5;
    static String[][] books = new String[Index][4]; // title , author , id , additional doc.
    static String[][] users = new String[Index][4];
    static String[][] transactions = new String[Index][4]; // userId , bookId , date , status
    static int bookQuantity = 0;
    static int transactionQuantity = 0;
    static int userQuantity = 0;
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

    public static void checkBookReturnDeadline(){
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDeadline = currentDate.plusMonths(1); // 1 month later 
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedReturnDeadline = returnDeadline.format(dateFormatter);
        System.out.println("Book return deadline: " + formattedReturnDeadline);

        if (currentDate.isAfter(returnDeadline)) {
            System.out.println("The book return deadline has passed. User cannot take book.");
        } else {
            System.out.println("User can take book.");
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

    public static void checkOutBook(String userId, String bookId){
        if(checkBooks(bookId)){
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(dateFormatter);

            transactions[transactionQuantity][0] = userId;
            transactions[transactionQuantity][1] = bookId;
            transactions[transactionQuantity][2] = formattedDate;
            transactionQuantity++;

            System.out.println("Transaction successful");
        }
        else {
            System.out.println("Transaction failed");
    }
        int indexToRemove = getBookIndexByBookId(bookId);
        for (int i = indexToRemove; i < bookQuantity - 1; i++) {
             books[i] = books[i + 1];
    }
        books[bookQuantity - 1] = null;
        bookQuantity --;

    }

        public static int getBookIndexByBookId(String bookId) {
        int indexOfBook = -1;
        for (int i = 0; i < bookQuantity; i++) {
        if (bookId.equals(books[i][2])) {
        indexOfBook = i;
        break;
        }}
        return indexOfBook;
}

    public static boolean checkBooks(String bookId){  
        boolean found = false;
        for (int i = 0; i < bookQuantity; i++) {
                if (books[i][2].equals(bookId)) {
                    System.out.println("The book is found!");
                    found = true;
                    break;}
        if (!found) {
            System.out.println("The book is not found.");
            }
    }
    return found;
}

    public static void signUp(){
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
        }}

    public static void returnbook(String userId, String bookId) {
        int temp = -1, i;
        for (i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId) || transactions[i][1].equals(bookId)) {
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

    }}
