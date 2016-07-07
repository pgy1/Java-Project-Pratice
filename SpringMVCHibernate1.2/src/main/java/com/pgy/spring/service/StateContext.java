package com.pgy.spring.service;

import com.pgy.spring.model.CommonBean;

/**
 * Created by pengguangyu on 2016/3/30.
 */
public class StateContext implements State {

    private State state;
    private CommonBean commonBean;


    public static class StateContextHelper{
        public static final StateContext INSTANCE = new StateContext();
    }

    public static StateContext getInstance(){
        return StateContextHelper.INSTANCE;
    }

    public StateContext setStateWith(State state){
        this.state = state;
        return this;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void doAction() {
        this.state.doAction();
    }

    @Override
    public CommonBean doActionReturn() {
        this.state.doActionReturn();
        return null;
    }
}
