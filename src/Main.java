import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    static int Index = 50;
    static String[][] books = new String[Index][4]; // title , author , id , additional doc.
    static String[][] users = new String[Index][4];
    static String[][] transactions = new String[Index][4]; // userId , bookId , date , status
    static int bookQuantity = 0;
    static int transactionQuantity = 0;
    static int userQuantity = 0;


    public static void main(String[] args) {
    }

    public static void addBook(String title, String author, String bookId, String additionalDoc){
        if(bookQuantity < Index){
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
    static void checkBooks(String bookId){  
        boolean found = false;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(bookId)) {
                System.out.println("The book is found!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("The book is not found.");}
    }