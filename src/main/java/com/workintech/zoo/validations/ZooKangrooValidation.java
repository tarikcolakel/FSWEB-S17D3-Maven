package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKangrooValidation {
    public static void isValid(Integer id) {
        if(id==null || id<0){
            throw new ZooException("id is not valid"+id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void checkKangrooExistence(Map<Integer, Kangaroo> kangaroos,Integer id,boolean existence){
        if(existence){
            if (!kangaroos.containsKey(id)){
                throw new ZooException("Record is not exists:"+id,HttpStatus.NOT_FOUND);
            }
        }
        else {
            if (kangaroos.containsKey(id)){
                throw new ZooException("Record is already exists:"+id,HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKangrooWeight(Double weight) {
        if(weight==null ||weight<=0){
            throw new ZooException("weight should not in null or last then zero",HttpStatus.BAD_REQUEST);
        }
    }
}
