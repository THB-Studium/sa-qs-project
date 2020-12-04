package de.thb.presenter;

public interface IMainComputer {

    int calculateActualAvailability(int actual, int bookedTicket);

    void onUIModelDataChanged();
}
