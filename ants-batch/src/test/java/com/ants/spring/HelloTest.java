package com.ants.spring;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void ApplicationContext(){
        // IoC 컨테이너 생성, 생성과 동시에 컨테이너 등록
        StaticApplicationContext ac = new StaticApplicationContext();

        // Hello 클래스를 HelloBean 이라는 이름의 싱글톤빈으로 컨테이너에 등록
        ac.registerSingleton("HelloBean", Hello.class);

        // IoC 컨테이너가 등록한 빈을 생성했는지 확인하기 위해 빈을 요청하고 Null 이 아닌지 확인.
        Hello hello1 = ac.getBean("HelloBean", Hello.class);



        // 빈 메타정보를 담은 오브젝트. 빈 클래스는 Hello로 지정함
        // xml에서 <bean class="com.ants.spring.Hello" /> 에 해당
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);

        // 빈의 name Property에 값 주입,
        // xml에서 <property name="name" value="betterFLY" /> 해당
        helloDef.getPropertyValues().addPropertyValue("name", "betterFLY");

        // 앞에 생성한 빈 메타정보를 hello2 라는 이름을 가진빈으로 해서 등록
        // <bean id="hello2" />
        ac.registerBeanDefinition("hello2", helloDef);

        /*
        위와 같이 할 경우 IoC 컨테이너는 빈 설정 메타정보를 담은 BeanDefinition을 이용해 오브젝트를 생성하고, DI작업을 진행한 후 Bean을 사용할 수 있도록 등록해줌

         */


        Hello hello2 = ac.getBean("hello2", Hello.class);
        assertThat(hello2.sayHello(), is("Hello betterFLY"));
    }

}