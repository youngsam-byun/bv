package config.dev;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;

@Configuration
//@EnableAspectJAutoProxy
@ActiveProfiles("dev")
public class AspectConfig_DEV {

}
