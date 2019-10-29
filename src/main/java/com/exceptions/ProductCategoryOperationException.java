package com.exceptions;

public class ProductCategoryOperationException extends RuntimeException {
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ProductCategoryOperationException(String message) {
        super(message);
    }
}
