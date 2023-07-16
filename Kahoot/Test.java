package com.company;

import java.util.ArrayList;

public class Test extends Question {
    private String[] options;
    private int numOfOptions;
    private ArrayList<Character> labels;

    public Test() {
        options = new String[4];
        numOfOptions = 4;
        labels = new ArrayList<>();
        labels.add('A');
        labels.add('B');
        labels.add('C');
        labels.add('D');
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getOptionAt(int index) {
        return options[index];
    }

    @Override
    public String toString() {
        String out = getDescription() + "\n";
        for (int i = 0; i < numOfOptions; i++) {
            out += labels.get(i) + ") " + getOptionAt(i) + "\n";
        }
        return out;
    }
}

