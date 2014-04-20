package kmap.web.mbean;

import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Component;

@Component("myManagedBean")
@ManagedResource(objectName="your.domain.jmx:name=MyMBean",
        description="My MBean goal")
public class AnnotationTestBean {

    private int age;

    @ManagedAttribute(description="The age attribute", currencyTimeLimit=15)
    public int getAge() {
        return age;
    }

    @ManagedOperation(description = "Check permissions for the given activity")
    @ManagedOperationParameters( {
            @ManagedOperationParameter(name = "activity",
                    description = "The activity to check")
    })
    public boolean isAllowedTo(final String activity) {
        return true;
    }
}