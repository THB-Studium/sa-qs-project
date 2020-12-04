import java.time.LocalDate;
package de.thb.model;

public interface IEvent {

	public void createEvent( String name, LocalDate date, int numberOfTicket);
    public void updateEventNumberOfTicket(int numberOfTicket);
    
}
