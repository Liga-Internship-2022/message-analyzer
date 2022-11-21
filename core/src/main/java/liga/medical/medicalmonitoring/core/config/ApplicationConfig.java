package liga.medical.medicalmonitoring.core.config;

import liga.medical.config.ApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = ApplicationConfiguration.class)
public class ApplicationConfig {
}
