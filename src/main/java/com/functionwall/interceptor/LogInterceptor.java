package com.functionwall.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
        log.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                .format(beginTime), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 打印JVM信息。
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();    //2、结束时间
        log.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), (endTime - beginTime),
                request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
    }
}
