package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseToken {
    private String message;
    private String token;

    public ResponseToken(String message, String token){
        this.message = message;
        this.token = token;
    }
}
