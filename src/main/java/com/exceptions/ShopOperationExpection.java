package com.exceptions;

import java.io.Serializable;

public class ShopOperationExpection extends RuntimeException implements Serializable {


    public ShopOperationExpection(String msg){
        super(msg);
    }
}
