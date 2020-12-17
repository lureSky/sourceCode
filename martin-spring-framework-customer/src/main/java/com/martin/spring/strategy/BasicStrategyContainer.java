package com.martin.spring.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/17 17:01
 */
public class BasicStrategyContainer{

    //新建容器并进行转换
    private List<BasicStrategy> strategyList;
    public BasicStrategyContainer(){
        this.strategyList = new ArrayList<>();
        strategyList.add(new IntegerStrategy());
        strategyList.add(new StringStrategy());
    }

    public Object parseByType (Class<?> targetType,String value) {
        for (BasicStrategy basicStrategy : strategyList) {
            if (basicStrategy.isOk(targetType)) {
                return basicStrategy.parseToType(value);
            }
        }
        return null;
    }

}
