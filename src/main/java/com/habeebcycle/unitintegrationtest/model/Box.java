package com.habeebcycle.unitintegrationtest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box {

    private List<String> treasures;
    private String name;

    public Box(){
        this.name = "";
        this.treasures = new ArrayList<>();
    }

    public Box(String name){
        this.name = name;
        this.treasures = new ArrayList<>();
    }

    public void addTreasure(String treasure){
        this.treasures.add(treasure);
    }

    public boolean removeTreasure(String treasure){
        return this.treasures.remove(treasure);
    }

    public boolean isEmpty(){
        return this.treasures.isEmpty();
    }

    public int getNumTreasure(String treasure){
        return Collections.frequency(this.treasures, treasure);
    }

    public List<String> getBoxContents(){
        return this.treasures;
    }

    public boolean removeAllSameTreasure(String treasure){
        boolean removed = false;
        while (this.treasures.contains(treasure)){
            removeTreasure(treasure);
            removed = true;
        }
        return removed;
    }

    public void emptyBox(){
        this.treasures.clear();
    }

}
