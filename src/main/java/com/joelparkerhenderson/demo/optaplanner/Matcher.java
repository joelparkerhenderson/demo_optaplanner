package com.joelparkerhenderson.demo.optaplanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;

@PlanningEntity
public class Matcher implements ToStringDeep, ToXMLString, FromXMLString, HasName, Comparable<Matcher> {

    public String toString(){
        return "name:" + ((name != null) ? name : "null");
    }

    @Override
    public String toStringDeep(){
        return toString() + 
        ",maker:{" + ((maker != null) ? maker : "null") + "}" + 
        ",taker:{" + ((taker != null) ? taker : "null") + "}";
    }

    @Override
    public String toXMLString()
    {
        return AppXML.toXML(this);
    }

    //@Interface FromXMLString
    public static Matcher fromXMLString(String xml)
    {
        return (Matcher)AppXML.fromXML(xml);
    }

    private String name;

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    private Maker maker;

    public Maker getMaker(){
        return maker;
    }

    public void setMaker(Maker maker){
        this.maker = maker;
    }

    private Taker taker;

    // Ref corresponds to the ref on Solution.takers().
    @PlanningVariable(valueRangeProviderRefs = {"ValueRangeProviderTakers"})
    public Taker getTaker(){
        return taker;
    }

    public void setTaker(Taker taker){
        this.taker = taker;
    }

    @Override
    public int compareTo(Matcher that) {
        return (int)(this.getName().compareTo(that.getName()));
    }

}
