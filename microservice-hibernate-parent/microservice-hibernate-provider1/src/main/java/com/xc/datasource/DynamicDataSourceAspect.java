package com.xc.datasource;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by xiongying on 16/8/2.
 */
@Aspect
@Order(0)
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    @Pointcut("execution(* com.xc..service..*.*(..))")
    public void dynamicDs() {
    }

    @Before("dynamicDs()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = method.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(dataSource.readOnly());
//                logger.debug(new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(),
//                        "DynamicDataSourceAspect doBefore,methodName=" + method.getName() + ",HandleDataSource readOnly=" + dataSource.readOnly()).toJson());
            } else {
                HandleDataSource.putDataSource(false);
//                logger.debug(new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(),
//                        "DynamicDataSourceAspect doBefore,methodName=" + method.getName() + ",use the default DataSource(master)").toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("dynamicDs()")
    public void doAfter() {
        try {
            HandleDataSource.remove();
//            logger.debug(new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(),
//                    "DynamicDataSourceAspect doAfter,remove ThreadLocal DataSource").toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
