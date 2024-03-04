package com.jsxa.vapp.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;


@Component
public class SpringUtil implements BeanFactoryPostProcessor,ApplicationContextAware {


    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        SpringUtil.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException
    {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 通过class获取Bean
     * */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }



    /**
     * 通过name,以及Object动态替换bean实例
     * */
    public static void replaceBean(String beanName, Object targetObj) throws NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)getApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        //反射获取Factory中的singletonObjects 将该名称下的bean进行替换
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.put(beanName, targetObj);
    }


    /**
     * 主动向Spring容器中注册一个新的bean
     *
     * @param name               BeanName
     * @param clazz              注册的bean的类性
     * @param args               构造方法的必要参数，顺序和类型要求和clazz中定义的一致
     * @param <T>
     * @return 返回注册到容器中的bean对象
     */
    public static <T> T registerBean(/*ConfigurableApplicationContext applicationContext,*/
            String name,
            Class<T> clazz,
            Object... args) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        if(configurableApplicationContext.containsBean(name)) {
            Object bean = configurableApplicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return (T) bean;
            } else {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();


        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return configurableApplicationContext.getBean(name, clazz);
    }

    /**
     * 主动向Spring容器中新注册或更新一个现有的bean
     *
     * @param beanName               BeanName
     * @param targetObj              现有的bean
     * @return 返回注册到容器中的bean对象
     */
    public static void registerBean( String beanName,Object targetObj) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)getApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        //反射获取Factory中的singletonObjects 将该名称下的bean进行替换
        Field singletonObjects = null;
        try {
            singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        singletonObjects.setAccessible(true);
        Map<String, Object> map = null;
        try {
            map = (Map<String, Object>) singletonObjects.get(beanFactory);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        map.put(beanName, targetObj);
    };

}
