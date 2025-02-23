package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validations.ZooKangrooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    // Tüm kangaroo listesini döner
    @GetMapping
    public List<Kangaroo> find(){
        return this.kangaroos.values().stream().toList();
    }

    // Belirtilen id'ye sahip kangaroo'yu döner
    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable Integer id) {
        ZooKangrooValidation.isValid(id);
        ZooKangrooValidation.checkKangrooExistence(kangaroos,id,true);
        return kangaroos.get(id);
    }
    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo){
        ZooKangrooValidation.checkKangrooExistence(kangaroos,kangaroo.getId(),false);
        ZooKangrooValidation.checkKangrooWeight(kangaroo.getWeight());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id,@RequestBody Kangaroo kangaroo){
        ZooKangrooValidation.isValid(id);
        ZooKangrooValidation.checkKangrooWeight(kangaroo.getWeight());
        kangaroo.setId(id);
        if(kangaroos.containsKey(id)){
            kangaroos.put(id, kangaroo);
            return kangaroos.get(id);
        }
        else{
            return save(kangaroo);
        }
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id){
        ZooKangrooValidation.isValid(id);
        ZooKangrooValidation.checkKangrooExistence(kangaroos,id,true);
        return kangaroos.remove(id);
    }
}
