package com.lzqs.zhangyushu.config;//package com.lzqs.acbsp.config;
//
//import com.lzqs.acbsp.common.ResultInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by JinZhicheng on 2018/11/22 21:55
// * <p>
// * 全局异常捕获
// */
//@ControllerAdvice
//public class GlobalDefaultExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
//
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
//    public ResultInfo defaultExceptionHandler(HttpServletRequest req, Exception e) {
//        // 返回String
//        logger.info("异常信息：", e);
//        return ResultInfo.fail();
//    }
//}
