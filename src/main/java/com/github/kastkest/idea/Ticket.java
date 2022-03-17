package com.github.kastkest.idea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private int stops;
    private int price;

    @Override
    public String toString() {
        return "\nTicket{" +
                "origin='" + origin + '\'' +
                ", origin_name='" + origin_name + '\'' +
                ", destination='" + destination + '\'' +
                ", destination_name='" + destination_name + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }

}



