package com.safetynet.alerts.models;

import java.util.Objects;

public class FireStation {
    private String address;
    private int station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public FireStation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FireStation that = (FireStation) o;

        if (station != that.station) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + station;
        return result;
    }
}
