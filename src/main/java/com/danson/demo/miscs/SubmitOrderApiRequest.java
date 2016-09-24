package com.danson.demo.miscs;

import com.danson.api.remote.annotation.ApiBeanParams;
import com.danson.api.remote.annotation.ApiParam;
import com.danson.api.remote.definition.AbstractApiRequest;

/**
 * Created by dansonliu on 16/8/10.
 */
public class SubmitOrderApiRequest extends AbstractApiRequest {

//    @formatter:off
    @ApiParam(name = "type", desc = "订单类型", required = true, allowValues = {
            @ApiParam.AllowValue(value = "1", desc = "机票"),
            @ApiParam.AllowValue(value = "2", desc = "酒店"),
            @ApiParam.AllowValue(value = "3", desc = "度假"),
            @ApiParam.AllowValue(value = "4", desc = "火车票"),
    })
//    @formatter:on
    private int      type;

    @ApiParam(name = "item", desc = "预订项目名称", required = true)
    private String   orderItem;

    @ApiParam(name = "customer", desc = "预订客户")
    @ApiBeanParams(excludeParams = { "gender" })
    private Customer customer;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
