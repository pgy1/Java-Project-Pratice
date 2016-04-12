package com.pgy.spring.service.state;

import com.pgy.spring.model.CommonBean;
import com.pgy.spring.service.State;

/**
 * Created by pengguangyu on 2016/3/30.
 */
public class NewState implements State {

    private CommonBean commonBean;
    private Types typeState;

    public NewState(){}

    public NewState(CommonBean commonBean){
        this.commonBean = commonBean;
    }

    public NewState(Types typeState){
        this.typeState = typeState;
    }

    public void BeforeAction(){
        System.out.println("BeforeAction....");
    }

    public void NewAction(){
        System.out.println("NewAction....");
    }

    public void AfterAction(){
        System.out.println("AfterAction....");
    }

    @Override
    public void doAction() {
        switch (this.typeState){
            case BeforeAction:BeforeAction();break;
            case NewAction:NewAction();break;
            case AfterAction:AfterAction();break;
        }
    }

    @Override
    public CommonBean doActionReturn() {
        System.out.println("New Return....");
        return null;
    }

    public static enum Types{
        BeforeAction,NewAction,AfterAction
    }

}
