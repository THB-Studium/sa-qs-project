import java.util.Date;
package de.thb.model;

public interface IEvent {

	public void createEvent( String name, Date date, int numberOfTicket);
    public void updateEventNumberOfTicket(int numberOfTicket);
    
}
