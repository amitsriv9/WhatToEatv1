package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.YummlyGetRequest;
import com.example.amit.whattoeat.entity.YummlyGetResult;

/**
 * Created by Jun on 4/24/2015.
 */
public class YummlyGetThread extends Thread {
    YummlyGetResult result;
    YummlyGetRequest request;

    public YummlyGetThread(YummlyGetRequest request, YummlyGetResult result) {
        this.result = result;
        this.request = request;
    }

    @Override
    public void run() {
//        super.run();
        System.out.println("running get thread");
        new YummlyRecipeGetter().getRecipe(request, result);
//        notifyAll();
    }
}
