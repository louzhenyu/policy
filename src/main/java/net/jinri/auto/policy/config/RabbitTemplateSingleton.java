package net.jinri.auto.policy.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RabbitTemplateSingleton {
	AbstractApplicationContext ctx;  
	RabbitTemplate template;
	private static class SingletonHolder {  
		private static final RabbitTemplateSingleton INSTANCE = new RabbitTemplateSingleton();  
	}  
	private RabbitTemplateSingleton (){
		ctx = new ClassPathXmlApplicationContext("rabbit-context.xml");
		template = ctx.getBean(RabbitTemplate.class);
	}
	public static final RabbitTemplateSingleton getInstance() {  
	return SingletonHolder.INSTANCE;  
	}
	public AbstractApplicationContext getCtx() {
		return ctx;
	}
	public void setCtx(AbstractApplicationContext ctx) {
		this.ctx = ctx;
	}
	public RabbitTemplate getTemplate() {
		return template;
	}
	public void setTemplate(RabbitTemplate template) {
		this.template = template;
	}
	
}
