package com.kilogate.hello.java.javase.jdkapi.script;

import javax.script.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * 脚本用法
 *
 * @author fengquanwei
 * @create 2017/8/7 17:49
 **/
public class ScriptUsage {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

        // 打印所有引擎
        List<ScriptEngineFactory> scriptEngineFactories = scriptEngineManager.getEngineFactories();
        for (ScriptEngineFactory scriptEngineFactory : scriptEngineFactories) {
            List<String> names = scriptEngineFactory.getNames();
            System.out.println(names);
        }

        // 获取 JavaScript 引擎
        ScriptEngine javascript = scriptEngineManager.getEngineByName("javascript");

        // 变量绑定
        javascript.put("name", "Lask");
        Object name = javascript.eval("name");

        javascript.eval("age = 21");
        Object age = javascript.get("age");

        // 全局绑定
        scriptEngineManager.put("global", "全局绑定");
        Object global = javascript.get("global");

        // 绑定集
        Bindings bindings = javascript.createBindings();
        bindings.put("bindings", "绑定集");
        Object eval = javascript.eval("bindings", bindings);

        // 重定向输入和输出
        StringWriter writer = new StringWriter();
        javascript.getContext().setWriter(new PrintWriter(writer, true));
        javascript.eval("print('Hello')");
        System.out.println(writer.getBuffer());

    }
}
