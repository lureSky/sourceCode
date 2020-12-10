package com.martin.mybatis.sqlSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态上下文类
 *  作用：存储SqlNode解析过程中产生的Sql信息（片段）   并完成字符串拼接
 *          存储SqlNode解析过程中需要的入参信息
 * @author caofeng
 * @date 2020/12/10 9:43
 */
public class DynamicContext {

    //利用StringBuilder对资源进行整合
    private StringBuilder stringBuilder = new StringBuilder();
    private Map<String,Object> bindings = new HashMap<>();

    public DynamicContext(Object param){
        bindings.put("_parameter",param);
    }

    public void appendSql(String sql){
        stringBuilder.append(sql);
        stringBuilder.append(" ");
    }

    public String getSql(){
        return stringBuilder.toString().trim();
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, Object> bindings) {
        this.bindings = bindings;
    }
}
