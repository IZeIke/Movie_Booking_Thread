package com.company;

import java.util.Random;

public class TicketBooking {

    public static void main(String[] args) {

        Movie m1 = new Movie();
        Movie m2 = new Movie();
        Movie m3 = new Movie();
        Movie m4 = new Movie();
        Movie m5 = new Movie();
        Movie[] MovieList = {m1, m2, m3, m4, m5};


        Thread user1 = new Thread (new User("user1", MovieList));
        user1.start();

        Thread user2 = new Thread (new User("user2", MovieList));
        user2.start();

        Thread user3 = new Thread (new User("user3", MovieList));
        user3.start();

        Thread user4 = new Thread (new User("user4", MovieList));
        user4.start();

        Thread user5 = new Thread (new User("user5", MovieList));
        user5.start();

    }
}


class User extends Thread {

    String Username;
    Movie[] MovieList;

    public User(String name, Movie[] list)
    {
        Username = name;
        MovieList = list;
    }

    Random randMovie = new Random();
    Random randTicket = new Random();
    Random decision = new Random();

    @Override
    public synchronized void run() {
        while(true) {

            int NumM = randMovie.nextInt(5);
            int NumT = randTicket.nextInt(5) + 1;
            MovieList[NumM].TicketNumber -= NumT;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
        }

    }
}
