package com.benchmark.model;

public class Fortune implements Comparable<Fortune> {
    public int id;
    public String message;

    public Fortune(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int compareTo(Fortune other) {
        return this.message.compareTo(other.message);
    }
}
