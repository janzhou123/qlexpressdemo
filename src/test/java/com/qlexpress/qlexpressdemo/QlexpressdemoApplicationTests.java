package com.qlexpress.qlexpressdemo;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.InstructionSet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class QlexpressdemoApplicationTests {

  public void print(String s) {
    System.out.print(s);
  }

  public void println(String s) {
    System.out.println(s);
  }


  @Test
  public void testGongzi() throws Exception {
    /**
     * 是否需要高精度计算
     */
    boolean isPrecise = true;
    //是否跟踪执行指令的过程
    boolean istrace = true;

    ExpressRunner runner = new ExpressRunner(isPrecise, istrace);
    //定义操作符别名
    runner.addOperatorWithAlias("如果", "if", null);
    runner.addOperatorWithAlias("否则", "else", null);
    runner.addOperatorWithAlias("小于", "<",null);
    //
    runner.addFunctionOfServiceMethod("打印", new QlexpressdemoApplicationTests(), "println",
        new String[]{"String"}, null);
    //规则文件 路径
    String templateFile = "templates/jiabangongzi";
    //加载文件
    runner.loadExpress(templateFile);
    //设置上下文变量
    IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
    expressContext.put("加班类型", "日常加班");
    expressContext.put("加班工时", new Integer(10));
    expressContext.put("每月固定工时", new Integer(180));
    //金额单位到分
    expressContext.put("固定工资", new Integer(70));
    Object object = runner
        .executeByExpressName(templateFile, expressContext, null, false, false, null);
    System.out.println(object.toString());
  }


}
