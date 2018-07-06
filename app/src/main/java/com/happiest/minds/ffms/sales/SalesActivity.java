package com.happiest.minds.ffms.sales;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SalesActivity extends ExpandableGroup<SalesActivityDetails> {

    public SalesActivity(String title, List<SalesActivityDetails> items) {
        super(title, items);
    }
}
