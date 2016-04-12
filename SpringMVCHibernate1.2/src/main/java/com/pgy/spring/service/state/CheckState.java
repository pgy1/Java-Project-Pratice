package com.pgy.spring.service.state;

import com.pgy.spring.model.CommonBean;
import com.pgy.spring.service.State;

/**
 * Created by pengguangyu on 2016/3/30.
 */
public class CheckState implements State {

    private CommonBean commonBean;

    public CheckState(){}

    public CheckState(CommonBean commonBean){
        this.commonBean = commonBean;
    }

    public void doSomething(){
        System.out.println(this.commonBean.getName());
    }

    @Override
    public void doAction() {
        doSomething();
        System.out.println("Check....");
    }

    @Override
    public CommonBean doActionReturn() {
        System.out.println("Check Return....");
        return null;
    }
}
