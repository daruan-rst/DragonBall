package com.practice.dragonballcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImpossibleWish extends RuntimeException{
    public ImpossibleWish(){super("Shenlong couldn't be summoned\n Two conditions must be fulfilled: the planet must have dragon balls and; the planet must have at least one NAMEK");}
}
