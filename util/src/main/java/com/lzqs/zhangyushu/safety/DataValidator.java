package com.lzqs.zhangyushu.safety;


//import com.lzqs.acbsp.common.ResultInfo;
//import org.springframework.validation.BindingResult;
//
//public class DataValidator {
//
//    /**
//     * 处理数据校验框架校验结果
//     * @param result
//     * @return
//     */
//    public static ResultInfo setCheckResult(BindingResult result){
//        //数据校验
//        System.out.println("进入校验");
//        if (result.hasErrors()) {
//            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
//            return ResultInfo.failWithMsg(errorMessage);
//        }else{
//            return null;
//        }
//    }
//}