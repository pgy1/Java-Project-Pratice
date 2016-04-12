package com.pgy.spring.web;

import com.pgy.spring.model.CommonBean;
import com.pgy.spring.service.StateContext;
import com.pgy.spring.service.state.CheckState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jackson on 16/4/12.
 */
@Controller
public class StateController {

    /*
    * 测试状态组合设计模式
    * */
    @RequestMapping(value = "/state/test", method = RequestMethod.GET)
    public String test(){
        //数据
        CommonBean commonBean = new CommonBean();
        commonBean.setName("leader");
        //处理
        StateContext stateContext = StateContext.getInstance();
        stateContext.setState(new CheckState(commonBean));
        stateContext.doAction();
        //返回
        return "test";
    }

}
