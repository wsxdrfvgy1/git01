package com.yjxxt.am;

import com.alibaba.fastjson.JSON;
import com.yjxxt.am.base.ResultInfo;
import com.yjxxt.am.exceptions.NoLoginException;
import com.yjxxt.am.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) {
        //未登录异常
        if(e instanceof NoLoginException){
            ModelAndView mav = new ModelAndView("redirect:/index");
            return mav;
        }
        //实例化对象
        ModelAndView mav = new ModelAndView("errors");
        mav.addObject("code",400);
        mav.addObject("msg","系统异常，请稍后重试");
        if(o instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod) o;
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            //判断是否有responseBody
            if(responseBody==null){
                //跳转到视图
                if(e instanceof ParamsException){
                    ParamsException pe=(ParamsException)e;
                    mav.addObject("code",pe.getCode());
                    mav.addObject("msg",pe.getMsg());
                }
                return  mav;
            }else{
                //返回json数据  resultInfo
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("参数异常了");
                //
                if(e instanceof ParamsException){
                    ParamsException pe=(ParamsException)e;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                //响应resultInfo
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter pw = null;
                try {
                    pw = resp.getWriter();
                    //将resultInfo中的数据转为json
                    pw.write(JSON.toJSONString(resultInfo));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    if(pw!=null){
                        pw.close();
                    }
                }
                return  null;

            }
        }
        System.out.println(mav);
        return mav;
    }

}
