package com.github.kastkest.idea;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tickets {
    private ArrayList<Ticket> tickets;

    @Override
    public String toString() {
        return "Tickets{" +
                "tickets=" + tickets +
                '}';
    }

    public List<Ticket> createTicketList(Tickets jsonToJava) {
        List<Ticket> ticketList = jsonToJava.getTickets();
        return ticketList;
    }
}