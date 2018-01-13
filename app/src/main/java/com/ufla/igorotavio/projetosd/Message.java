package com.ufla.igorotavio.projetosd;

/**
 * Created by IgorOtávioCaetanoDin on 10/01/2018.
 */

public class Message {
    private String content, username, time;

    public Message(String content, String username, String time) {
        this.content = content;
        this.username = username;
        this.time = time;

    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}