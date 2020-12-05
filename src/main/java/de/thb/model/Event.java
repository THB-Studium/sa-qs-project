package de.thb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Event {

    private String name;
    private LocalDate date;
    private int numberOfTicket; 

    public void createEvent() {

        Random rand = new Random();
        int minDay = (int) LocalDate.now().toEpochDay();
        int maxDay = (int) LocalDate.of(2021, 12, 31).toEpochDay();
        int randomDay = minDay + rand.nextInt(maxDay - minDay);
        LocalDate randomEventDate = LocalDate.ofEpochDay(randomDay);

        //generate random name, date and numberOfTicket
        this.name = Long.toString(Math.abs(rand.nextLong() % 3656158440062976L), 36);
        this.date = randomEventDate;
        this.numberOfTicket = (int) (Math.random() * (100 - 0 + 1) + 0);
    }

    public void updateEventNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = this.numberOfTicket - numberOfTicket;
    }   
    
}
