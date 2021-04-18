package com.rodrigo.desafiojava.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;


public class Problem {

    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private List<Body> bodies;




    public static class Body {
        private String name;
        private String message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Body(String name, String message) {
            super();
            this.name = name;
            this.message = message;
        }


    }

    public void setBodies(List<Body> bodies) {
        this.bodies = bodies;
    }


    public List<Body> getBodies() {
        return bodies;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
