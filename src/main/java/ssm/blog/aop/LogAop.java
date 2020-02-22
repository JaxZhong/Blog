package ssm.blog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.blog.entity.SysLog;
import ssm.blog.entity.User;
import ssm.blog.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 1:45
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Before(value = "execution(* ssm.blog.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i<args.length;i++){
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }

    @After(value = "execution(* ssm.blog.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws NoSuchMethodException {
        long time =  new Date().getTime() - visitTime.getTime();

        String url ="";
        //获取url
        if(clazz != null && method != null && clazz !=  LogAop.class){
            //从类中获取@RequestMapping注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();
                //获取方法上的@RequestMapping注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] menthodValue = methodAnnotation.value();
                    url = classValue[0] + menthodValue[0];
                    String ip = request.getRemoteAddr();

                    User currentUser = (User) request.getSession().getAttribute("CurrentUser");
                    String username = currentUser.getEmail();

                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" +  clazz.getName() + "[方法名]" + method.getName());
                    sysLog.setTime(time);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    sysLogService.insertSysLog(sysLog);
                }

            }
        }
    }
}
