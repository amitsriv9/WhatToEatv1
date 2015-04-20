package com.example.amit.whattoeat.controller;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jun on 4/20/2015.
 */
public interface ServiceResultParser {
    public List<Object> parse(String callReturn);
}
