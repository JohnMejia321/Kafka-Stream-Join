package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependence {

    private long dependenceId;
    private long strategy;



    public long getDependenceId() {
        return dependenceId;
    }

    public void setDependenceId(long dependenceId) {
        this.dependenceId = dependenceId;
    }

    public long getStrategy() {
        return strategy;
    }

    public void setStrategy(long strategy) {
        this.strategy = strategy;
    }
}
