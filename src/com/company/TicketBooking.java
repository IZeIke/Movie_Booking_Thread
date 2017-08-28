package com.company;

import java.util.Random;


public class TicketBooking {

    public static void main(String[] args) {

        Movie m1 = new Movie();
        Movie m2 = new Movie();
        Movie m3 = new Movie();
        Movie m4 = new Movie();
        Movie m5 = new Movie();
        Movie[] MovieList = {m1,m2,m3,m4,m5};


        Thread user1 = new Thread(new User("user1",MovieList));
        user1.start();

        Thread user2 = new Thread(new User("user2",MovieList));
        user2.start();

    }
}


class User extends Thread {

    String Username;
    Movie[] MovieList;

    public User(String name,Movie[] list)
    {
        Username = name;
        MovieList = list;
    }

    Random randMovie = new Random();
    Random randTicket = new Random();
    Random decision = new Random();

    @Override
    public void run() {
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
                System.out.println(Username + " watch Movie" + NumM + " with " + NumT + " ticket");
            }
            if(MovieList[NumM].getTicketNumber() < 0)
            {
                System.out.println("การจองไม่สำเร็จเพราะ"+Username+"จองตั๋วซ้ำ"+"และ Movie" + NumM+"มีจำนวนที่นั่งเต็มแล้ว");
                MovieList[NumM].TicketNumber = 0;
            }else {
                System.out.println("Movie" + NumM + " have " + MovieList[NumM].getTicketNumber() + " tickets");
            }
        }

    }

}