package com.ssxu.exception;

import com.ssxu.entity.Ajax;
import com.ssxu.util.StaticVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 类描述:全局异常处理
 * 创建人:ssxu
 * 创建时间:2018-4-25 下午2:22:38
 *
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 全局异常处理 跳转页面
     *
     * @param ex 异常
     * @return 页面地址
     */
    @ExceptionHandler(value = MyPageException.class)
    public ModelAndView myPageExceptionHandle(MyPageException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/msg");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    /**
     * ajax异常
     *
     * @param ex 异常
     * @return ajax封装
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Ajax<String> myExceptionHandle(MyException ex) {
        return new Ajax<>(StaticVariable.AJAXERROR, ex.getMessage());
    }

    /**
     * ajax全局异常
     *
     * @param ex 异常
     * @return ajax封装
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Ajax Handle(Exception ex) {
        logger.error("^_^  [全局系统异常]{}----------{}", ex);
        return new Ajax<>(StaticVariable.AJAXERROR, "^_^  出错啦!!!");
    }

}
