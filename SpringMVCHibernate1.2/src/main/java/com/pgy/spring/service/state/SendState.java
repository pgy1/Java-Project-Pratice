package com.pgy.spring.service.state;

import com.pgy.spring.model.CommonBean;
import com.pgy.spring.service.State;

/**
 * Created by pengguangyu on 2016/3/30.
 */
public class SendState implements State {

    private CommonBean commonBean;

    public SendState(){}
    public SendState(CommonBean commonBean){
        this.commonBean = commonBean;
    }

    @Override
    public void doAction() {
        System.out.println("Send....");
    }

    @Override
    public CommonBean doActionReturn() {
        System.out.println("Send Return....");
        return null;
    }
}
