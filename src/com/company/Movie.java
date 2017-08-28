package com.company;

/**
 * Created by Harit Moolphunt on 28/8/2560.
 */
public class Movie {

    int TicketNumber;


    public Movie(){
        TicketNumber = 20;
    }

    public void reserve(int n)
    {
        TicketNumber -= n;
    }

    public int getTicketNumber()
    {
        return TicketNumber;
    }
}
