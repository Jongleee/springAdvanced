package com.project.springproject2.model;

import lombok.Data;

@Data
public class Message {
    private boolean success;
    private Object data;
    private String error;

    public Message(){
        this.success=true;
        this.data=null;
        this.error=null;
    }

}
