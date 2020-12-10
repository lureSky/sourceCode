package com.martin.mybatis.sqlNode;

import com.martin.mybatis.sqlSource.DynamicContext;
import com.martin.mybatis.utils.GenericTokenParser;
import com.martin.mybatis.utils.OgnlUtils;
import com.martin.mybatis.utils.SimpleTypeRegistry;
import com.martin.mybatis.utils.TokenHandler;

/**
 * @author caofeng
 * @date 2020/12/10 10:17
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //处理文本结点信息,处理OGNL表达式
        TokenHandler tokenHandler = new BindingTokenParser(context);
        //新建解析器，主要是解析#{} ${}
        GenericTokenParser tokenParser = new GenericTokenParser("${","}",tokenHandler);
        context.appendSql(tokenParser.parse(sqlText));
        return true;
    }

    //如果有${就是动态的
    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }else{
            return false;
        }
    }

    private class BindingTokenParser implements TokenHandler{

        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        private DynamicContext context;
        public BindingTokenParser(DynamicContext context){
            this.context = context;
        }

        //在parse（）方法中进行使用
        @Override
        public String handleToken(String content) {
            //1.获取值
            Object parameterObject = context.getBindings().get("_parameter");
            if (parameterObject == null) {
                //返回空
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(parameterObject.getClass())) {
                //如果是8中基础类型+一些常见类型，直接返回即可
                return String.valueOf(parameterObject);
            }
            //如果不属于上述类型又不为空，则需要进行呢Ognl解析
            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(content, context.getBindings());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }
    }
}
