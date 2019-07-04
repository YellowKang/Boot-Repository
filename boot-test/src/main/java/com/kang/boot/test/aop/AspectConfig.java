package com.kang.boot.test.aop;


import com.kang.boot.test.annotation.MethodUpArgs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@Component
@Aspect
@Slf4j
public class AspectConfig {
    //
    @Pointcut("execution(* com.kang.boot.test.controller.*.*(..))")
    public void testAspect() {
    }

//    //
//    @Before("testAspect()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 记录下请求内容
//        log.info("URL : " + request.getRequestURL().toString());//打印请求url
//        log.info("HTTP_METHOD : " + request.getMethod());//打印请求方法
//        log.info("IP : " + request.getRemoteAddr());//打印请求IP来源
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());//打印类方法路径
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));//打印参数
//    }
//
//    @AfterReturning(value = "testAspect()",returning = "param")
//    public Object test(JoinPoint joinPoint,Object param){
//
//        for (Object o : joinPoint.getArgs()) {
//            System.out.println(o.getClass().getTypeName());
//        }
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        MethodUpArgs annotation = signature.getMethod().getClass().getAnnotation(MethodUpArgs.class);
//        param = 123;
//        return param;
//    }


    /**
     * 执行前置通知，并且打印日志，以及获取注解值
     * @param joinPoint
     * @param methodUpArgs
     * @throws Throwable
     */
//    @Before(value = "@annotation(methodUpArgs)",argNames = "methodUpArgs")
//    public void doBefore(JoinPoint joinPoint,MethodUpArgs methodUpArgs) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        log.info("URL : " + request.getRequestURL().toString());//打印请求url
//        log.info("HTTP_METHOD : " + request.getMethod());//打印请求方法
//        log.info("IP : " + request.getRemoteAddr());//打印请求IP来源
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());//打印类方法路径
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));//打印参数
//        System.out.println(methodUpArgs.updateValue());
//    }

    /**
     * 根据注解修改参数值
     * @param point 代理信息
     * @param methodUpArgs 注解参数
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(methodUpArgs)",argNames = "methodUpArgs")
    public Object myprocess(ProceedingJoinPoint point,MethodUpArgs methodUpArgs) throws Throwable {
        //获取HttpServletRequest，然后获取头信息，并且搭建用户的系统以及浏览器
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info(request.getHeader("User-Agent").toString());

        //获取用户方法签名
        MethodSignature msg = (MethodSignature)point.getSignature();
        //获取方法参数名称
        String[] paramName = msg.getParameterNames();
        //获取传入的参数值
        Object[] args = point.getArgs();
        //将参数名称转为集合
        List<String> paramNameList = Arrays.asList(paramName);
        //循环遍历需要修改的参数
        if(methodUpArgs.argsName().length > 0 && methodUpArgs.argsName() != null){
            for (int i = 0; i < methodUpArgs.argsName().length; i++) {
                //非空判断
                if(!StringUtils.isEmpty(methodUpArgs.argsName()[i])){
                    //判断是否包含参数
                    if (paramNameList.contains(methodUpArgs.argsName()[i])) {
                        //获取参数位置
                        Integer pos = paramNameList.indexOf(methodUpArgs.argsName()[i]);
                        //判断值是否为空
                        if (StringUtils.isEmpty(methodUpArgs.argsValue()[i])){
                            log.info("取消修改参数！！！");
                        }else{
                            args[pos] = methodUpArgs.argsValue()[i];
                            log.info("修改为注解参数" + methodUpArgs.argsValue()[i]);
                        }
                    }
                }
            }
        }
        //执行方法
        Object result = point.proceed(args);
        return result;
    }


    //需要代理的表达式，com.kang.boot.test.controller包下所有方法
    @Pointcut("execution(* com.kang.boot.test.controller.**.*(..))")
    private void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求URL: " + request.getRequestURL().toString());//打印请求url
        log.info("请求IP: " + request.getRemoteAddr());//打印请求IP来源


        // 获取目标Logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取开始时间戳
        long start = System.currentTimeMillis();
        logger.info( "{}: {}: 开始执行方法...", clazzName, methodName);
        // 调用目标方法
        Object result = joinPoint.proceed();
        //计算运行时间
        long time = System.currentTimeMillis() - start;
        logger.info( "{}: {}: 方法执行结束... 执行时间: {} ms", clazzName, methodName, time);
        return result;
    }
}
