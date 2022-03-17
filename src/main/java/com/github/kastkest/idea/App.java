package com.github.kastkest.idea;


import java.util.*;

public class App {

    public static void main(String[] args) {

        Tickets tickets = Tickets.jsonToJava();

        List<Ticket> ticketList = tickets.createTicketList(tickets);
        List<Long> flightTimeList = tickets.createFlightTimeList(ticketList);

        System.out.println(tickets.findAvgFlightTime(flightTimeList));
        System.out.println(tickets.percentile(flightTimeList, 90));

    }

}