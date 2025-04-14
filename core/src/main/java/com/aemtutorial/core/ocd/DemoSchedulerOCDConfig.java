package com.aemtutorial.core.ocd;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Designate(ocd = DemoSchedulerOCDConfig.class)
@ObjectClassDefinition(name = "Scheduler Cron Config", description = "Configuration of Cron for Scheduler ")
public @interface  DemoSchedulerOCDConfig {

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron job expression to schedule the task"
    )
    String scheduler_expression() default "";

    @AttributeDefinition(
            name = "Enable Scheduler",
            description = "Toggle to enable or disable the scheduler"
    )
    boolean scheduler_enabled() default true;
}
