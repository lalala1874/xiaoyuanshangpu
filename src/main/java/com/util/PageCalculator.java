package com.util;

public class PageCalculator {
    public static int CalculatorRowIndex(int pageIndex,int pageSiez){
        return  (pageIndex>0)?(pageIndex-1)*pageSiez:0;

    }
}
