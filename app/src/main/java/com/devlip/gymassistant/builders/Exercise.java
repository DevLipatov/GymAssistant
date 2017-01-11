package com.devlip.gymassistant.builders;

public class Exercise {

    private final int id;
    private final String name;
    private final int type;
    private final int approaches;

    public static class Builder {
        private int id;
        private String name;
        private int type;
        private int approaches;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }

        public int getApproaches() {
            return approaches;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder approaches(int val) {
            approaches = val;
            return this;
        }

        public Exercise build() {
            return new Exercise(this);
        }
    }

    private Exercise(Builder builder) {
        id = builder.id;
        name = builder.name;
        type = builder.type;
        approaches = builder.approaches;
    }

}
