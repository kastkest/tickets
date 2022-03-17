package com.github.kastkest.idea;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    public static void main(String[] args) {

        App app = new App();
        System.out.println(app.findAvgFlightTime(app.createFlightTimeList(app.createTicketList(jsonToJava()))));
        System.out.println(app.percentile(app.createFlightTimeList(app.createTicketList(jsonToJava())), 90));

    }


    private List<Long> createFlightTimeList(List<Ticket> ticketList) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy H:mm");
        List<Long> flightTimeList = new ArrayList<>();
        long flightTime = 0;
        for (Ticket ticket : ticketList) {
            String dep_time = ticket.getDeparture_date() + " " + ticket.getDeparture_time();
            String arr_time = ticket.getArrival_date() + " " + ticket.getArrival_time();

            Date dep = null;
            Date arr = null;
            try {
                dep = formatter.parse(dep_time);
                arr = formatter.parse(arr_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            flightTime = (arr.getTime() - dep.getTime());
            flightTimeList.add(flightTime);
        }
        return flightTimeList;
    }

    private List<Ticket> createTicketList(Tickets jsonToJava) {
        List<Ticket> ticketList = jsonToJava.getTickets();
        return ticketList;
    }


    private String findAvgFlightTime(List<Long> flightTime) {
        long sumFlightTime = 0;
        for (Long timeInMillis : flightTime) {
            sumFlightTime += timeInMillis;
        }

        long avgFlightTime = sumFlightTime / flightTime.size();
        long avgFlightTimeHours = avgFlightTime / 60 / 60 / 1000;
        long minutesInMillis = (avgFlightTime % 3_600_000);
        long avgFlightTimeMinutes = minutesInMillis / 60 / 1000;

        return String.format("Среднее время полета %d часов, %d минуты", avgFlightTimeHours, avgFlightTimeMinutes);
    }

    private String percentile(List<Long> list, double percentile) {
        Collections.sort(list);
        int index = (int) Math.ceil(percentile / 100.0 * list.size());
        return (percentile + "-ый процентиль равен " + list.get(index - 1) / 3600000 + " часам, " + list.get(index - 1) % 3600000 / 60000 + " минутам");
    }


    private static Tickets jsonToJava() {
        String jsonString = "{\n" +
                "  \"tickets\": [{\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"16:20\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"22:10\",\n" +
                "    \"carrier\": \"TK\",\n" +
                "    \"stops\": 3,\n" +
                "    \"price\": 12400\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"17:20\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"23:50\",\n" +
                "    \"carrier\": \"S7\",\n" +
                "    \"stops\": 1,\n" +
                "    \"price\": 13100\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"12:10\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"18:10\",\n" +
                "    \"carrier\": \"SU\",\n" +
                "    \"stops\": 0,\n" +
                "    \"price\": 15300\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"17:00\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"23:30\",\n" +
                "    \"carrier\": \"TK\",\n" +
                "    \"stops\": 2,\n" +
                "    \"price\": 11000\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"12:10\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"20:15\",\n" +
                "    \"carrier\": \"BA\",\n" +
                "    \"stops\": 3,\n" +
                "    \"price\": 13400\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"9:40\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"19:25\",\n" +
                "    \"carrier\": \"SU\",\n" +
                "    \"stops\": 3,\n" +
                "    \"price\": 12450\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"17:10\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"23:45\",\n" +
                "    \"carrier\": \"TK\",\n" +
                "    \"stops\": 1,\n" +
                "    \"price\": 13600\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"6:10\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"15:25\",\n" +
                "    \"carrier\": \"TK\",\n" +
                "    \"stops\": 0,\n" +
                "    \"price\": 14250\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"16:50\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"23:35\",\n" +
                "    \"carrier\": \"SU\",\n" +
                "    \"stops\": 1,\n" +
                "    \"price\": 16700\n" +
                "  }, {\n" +
                "    \"origin\": \"VVO\",\n" +
                "    \"origin_name\": \"Владивосток\",\n" +
                "    \"destination\": \"TLV\",\n" +
                "    \"destination_name\": \"Тель-Авив\",\n" +
                "    \"departure_date\": \"12.05.18\",\n" +
                "    \"departure_time\": \"6:10\",\n" +
                "    \"arrival_date\": \"12.05.18\",\n" +
                "    \"arrival_time\": \"16:15\",\n" +
                "    \"carrier\": \"S7\",\n" +
                "    \"stops\": 0,\n" +
                "    \"price\": 17400\n" +
                "  }]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Tickets tickets = null;
        try {
            tickets = mapper.readValue(jsonString, Tickets.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tickets;
    }

}
