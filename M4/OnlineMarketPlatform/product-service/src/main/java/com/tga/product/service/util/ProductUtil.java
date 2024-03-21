package com.tga.product.service.util;

import java.util.Random;

public class ProductUtil {

    public int getRandomNo(){
        Random random = new Random();
        return random.nextInt();
    }
}
