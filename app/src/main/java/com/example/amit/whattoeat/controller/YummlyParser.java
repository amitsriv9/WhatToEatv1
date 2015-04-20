package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.Recipe;
import com.example.amit.whattoeat.entity.YummlySearchResult;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/20/2015.
 */
public class YummlyParser implements ServiceResultParser {
    private YummlySearchResult yummlyResult;

    public YummlyParser(YummlySearchResult result){
        yummlyResult = result;
    }
    @Override
    public List<Object> parse(String callReturn) {
        List<Object> result = new LinkedList<Object>();
        JSONObject json;
        try {
             json = new JSONObject(callReturn);
            //parsing:
            yummlyResult.setAttribution(    json.getJSONObject(YummlySearchResult.ATTRIBUTION));
            yummlyResult.setTotalMatchCount(json.getInt(YummlySearchResult.TOTAL_MATCH_COUNT));
            yummlyResult.setFacetCounts(    json.getJSONObject(YummlySearchResult.FACET_COUNTS));
            yummlyResult.setMatches(        json.getJSONArray(YummlySearchResult.MATCHES));
            yummlyResult.setCriteria(       json.getJSONObject(YummlySearchResult.CRITERIA));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        result.add(yummlyResult.getAttribution());
        result.add(yummlyResult.getCriteria());
        result.add(yummlyResult.getFacetCounts());
        result.add(yummlyResult.getMatches());
        result.add(yummlyResult.getTotalMatchCount());
        return  result;
    }
}
