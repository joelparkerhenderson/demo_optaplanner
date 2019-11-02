package com.joelparkerhenderson.demo.optaplanner;

import java.util.*;
import java.util.stream.*; 
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Solution implements ToStringDeep, HasName {

    public String toString(){
        return "name:" + ((name != null) ? name : "null") +
           ",score:(" + score + ")";
    }

    @Override
    public String toStringDeep(){
        String matchersString = "";
        if (matchers != null ) {
            matchersString = matchers.stream().sorted().map((matcher) -> 
                "matcher:{" + matcher.toStringDeep() + "}"
            ).collect(Collectors.joining(","));
        }
        return toString() + ",matchers:[" + matchersString + "]";
    }

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @PlanningScore
    private HardSoftScore score = HardSoftScore.of(0,0);

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    private Set<Maker> makers;

    @ValueRangeProvider(id = "ValueRangeProviderMakers")
    @ProblemFactCollectionProperty
    public Set<Maker> getMakers() {
        return makers;
    }

    public void setMakers(Set<Maker> makers) {
        this.makers = makers;
    }

    private Set<Taker> takers;

    @ValueRangeProvider(id = "ValueRangeProviderTakers")
    @ProblemFactCollectionProperty
    public Set<Taker> getTakers() {
        return takers;
    }

    public void setTakers(Set<Taker> takers) {
        this.takers = takers;
    }

    private Set<Matcher> matchers;

    @PlanningEntityCollectionProperty
    public Set<Matcher> getMatchers() {
        return matchers;
    }

    public void setMatchers(Set<Matcher> matchers) {
        this.matchers = matchers;
    }

}
