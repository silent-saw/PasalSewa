package com.pasalsewa.pasalsewa.helper;

import com.pasalsewa.pasalsewa.*;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aezirus on 11/19/2017.
 */

public class Parent extends ExpandableGroup {
    public Parent(String title, ArrayList<Bill> items) {
        super(title, items);
    }


}
