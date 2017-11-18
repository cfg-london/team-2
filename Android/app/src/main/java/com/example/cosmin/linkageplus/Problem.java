package com.example.cosmin.linkageplus;

/**
 * Created by cosmin on 18.11.2017.
 */

public class Problem {
    private String problemName;
    private String priority;

    public Problem(String problemName, String priority) {
        this.problemName = problemName;
        this.priority = priority;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
