package sng.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application.properties")
@ComponentScan("sng")
public class Config {
}
