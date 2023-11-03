package com.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public class Dice {
    private int minValue;
    private int maxValue;
    private int currentValue;

    public int roll() {
        return new Random().nextInt(minValue, maxValue + 1);
    }
}
