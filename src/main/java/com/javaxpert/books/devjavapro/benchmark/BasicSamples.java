package com.javaxpert.books.devjavapro.benchmark;

import java.util.List;

/**
 * very basic performance code
 * @author deadbrain
 */
public class BasicSamples {
    private List<Integer> values;

    public BasicSamples(List<Integer> values) {
        this.values = values;
    }

    public Integer computeLoopStandardWithStream(){
        return values.stream().reduce((integer, integer2) -> integer+integer2).get();
    }

    public Integer computeLoopShorter(){
        Integer result =0;
        for(int i = 0;i < values.size();i=i+5){
            result+= values.get(i);
        }
        return result;
    }

    public Integer computeLoopStandardWithLoop(){
        Integer result =0;
        for(int i = 0;i < values.size();i++){
            result+= values.get(i);
        }
        return result;
    }
}
