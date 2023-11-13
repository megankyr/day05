package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

public static final int COL_TITLE = 1;
public static final int COL_PUBLISHER = 11;
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Please input file path in your command line argument");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
        Map<String, List<Book>> classified = bufferedReader.lines()
        .skip(1)
        .map(row -> row.trim().split(","))
        .map (fields -> new Book(fields[COL_TITLE], fields[COL_PUBLISHER]))
        .collect(Collectors.groupingBy(Book::getPublisher));

        for (String publisher : classified.keySet()){
            List<Book> books = classified.get(publisher);
            System.out.printf("%s (%d)\n", publisher, books.size());
            for (Book book : books){
                System.out.printf("\t%s\n", book.getTitle());
            }
        }
        }
    }
}
