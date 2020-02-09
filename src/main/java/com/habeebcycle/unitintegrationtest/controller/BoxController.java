package com.habeebcycle.unitintegrationtest.controller;

import com.habeebcycle.unitintegrationtest.model.Box;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treasures")
public class BoxController {

    private final Box box = new Box();

    @GetMapping("/box")
    public Box getCurrentBox(){
        return box;
    }

    @GetMapping("/box/all")
    public List<String> getBoxContents(){
        return box.getBoxContents();
    }

    @GetMapping("/box/{treasure}")
    public int getTreasureNum(@PathVariable String treasure){
        return box.getNumTreasure(treasure);
    }

    @PostMapping("/box/{treasure}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTreasure(@PathVariable final String treasure) {
        box.addTreasure(treasure);
    }

    @DeleteMapping("/box/{treasure}")
    public boolean removeTreasure(@PathVariable String treasure){
        return box.removeTreasure(treasure);
    }

    @DeleteMapping("/box/all/{treasure}")
    public void removeTreasures(@PathVariable String treasure){
        box.removeAllSameTreasure(treasure);
    }

    @DeleteMapping("/box/all")
    public void removeEverything() {
        box.emptyBox();
    }
}
