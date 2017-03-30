package com.rent.mytestapplication.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by RenTao on 2017/3/30.
 */
public class Homework {
    private int index;
    private String name;
    private String desc;
    private String githubUrl;
    private String quote;
    private List<String> completedNames;

    private Homework() {
        //no instance
    }

    private Homework(Builder builder) {
        index = builder.index;
        name = builder.name;
        desc = builder.desc;
        githubUrl = builder.githubUrl;
        quote = builder.quote;
        completedNames = builder.completedNames;
    }

    public int index() {
        return index;
    }

    public String name() {
        return name;
    }

    public String desc() {
        return desc;
    }

    public String githubUrl() {
        return githubUrl;
    }

    public String quote() {
        return quote;
    }

    public List<String> completedNames() {
        return completedNames;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "index=" + index() +
                ", name='" + name() + '\'' +
                ", desc='" + desc() + '\'' +
                ", githubUrl='" + githubUrl() + '\'' +
                ", quote='" + quote() + '\'' +
                ", completedNames='" + completedNames().get(0) + "'" +
                '}';
    }

    final static class Builder {
        private int index;
        private String name;
        private String desc;
        private String githubUrl;
        private String quote;
        private List<String> completedNames;

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder githubUrl(String githubUrl) {
            this.githubUrl = githubUrl;
            return this;
        }

        public Builder quote(String quote) {
            this.quote = quote;
            return this;
        }

        public Builder addCompletedName(String name) {
            if (completedNames == null) {
                completedNames = new ArrayList<>();
            }
            completedNames.add(name);
            return this;
        }

        public Homework build() {
            return new Homework(this);
        }
    }
}
