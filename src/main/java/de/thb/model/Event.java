package de.thb.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

    private String name;
    private Date date;
    private int numberOfTicket;
}
