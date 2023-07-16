package com.company;

public class FillIn extends Question {
    @Override
    public String toString() {
        return getDescription().replace("{blank}", "______");
    }
}

