package com.hcm.sale_laptop.utils;

import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.data.model.other.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ConstantsList {
    private static List<String> orderStateSelect;

    public static List<String> getOrderStateSelect() {
        if (orderStateSelect == null) {
            orderStateSelect = new ArrayList<String>();
        }
        return orderStateSelect;
    }

    public static void setOrderStateSelect(String model) {
        if (orderStateSelect == null) {
            orderStateSelect = new ArrayList<String>();
        }
        if(orderStateSelect.contains(model)) orderStateSelect.remove(model);
        else orderStateSelect.add(model);
    }

    public static void resetOrderStateSelect() {
        orderStateSelect = new ArrayList<String>();
    }

}
