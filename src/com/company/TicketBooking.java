package com.company;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class TicketBooking {

    public static void main(String[] args) {

        Movie m1 = new Movie();
        Movie m2 = new Movie();
        Movie m3 = new Movie();
        Movie m4 = new Movie();
        Movie m5 = new Movie();
        Movie[] MovieList = {m1, m2, m3, m4, m5};

        List<Future<String>> futures = new ArrayList<Future<String>>(100);

        ExecutorService pool = Executors.newFixedThreadPool(5);
        while (true) {
            pool.submit(new User(MovieList));
        }



    }
}

class User implements Callable<String> {

    String[] AllUser = {"user1", "user2", "user3", "user4", "user5"};
    Movie[] MovieList;

    public User (Movie[] movieLists) {
        MovieList = movieLists;
    }

    public String call() {
        Random randUser = new Random();
        Random randMovie = new Random();
        Random randTicket = new Random();
        Random decision = new Random();

        String Username = AllUser[randUser.nextInt(5)];

        int NumM = randMovie.nextInt(5);
        int NumT = randTicket.nextInt(5) + 1;
        MovieList[NumM].TicketNumber -= NumT;

        int Numdecision = decision.nextInt(2);

        if (Numdecision == 0) {
            MovieList[NumM].TicketNumber += NumT;
            System.out.println(Username + " deny Movie" + NumM + " return " + NumT + " ticket");
        } else {
            if (MovieList[NumM].getTicketNumber() < 0) {
                System.out.println(Username + " can not book ticket because this seat booked");
                MovieList[NumM].TicketNumber = 0;
            } else {
                System.out.println(Username + " watch Movie" + NumM + " with " + NumT + " ticket");
            }
        }

        if (MovieList[NumM].getTicketNumber() < 0) {
            MovieList[NumM].TicketNumber = 0;
        }
        System.out.println("Movie" + NumM + " have " + MovieList[NumM].getTicketNumber() + " tickets");
        return ("Movie" + NumM + " have " + MovieList[NumM].getTicketNumber() + " tickets");
    }

}
