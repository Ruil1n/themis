package dangod.themis;

import dangod.themis.service.core.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@EnableCaching
@EnableAsync
@ComponentScan
public class ThemisApplication {
	private static ThemisApplication app;

	@Autowired
	private AuthorityService authorityService;
	public static void main(String[] args) {
		SpringApplication.run(ThemisApplication.class, args);
		app.authorityService.initAuthorityTable();
	}
	@PostConstruct
	private void init(){
		app = this;
		app.authorityService = this.authorityService;
	}
}
