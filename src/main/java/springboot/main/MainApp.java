package springboot.main;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springboot.cache.RedisDataConfig;
import springboot.hbn.home.BaseHibernateHome;
import springboot.hbn.home.ServicesRegister;
import springboot.logs.Logs;
import springboot.utils.Classloader;

@SpringBootApplication(exclude = { RedisRepositoriesAutoConfiguration.class })
@ComponentScan(basePackages = "springboot")
@EnableScheduling
public class MainApp {

	public static Logs log = new Logs(BaseHibernateHome.class);
//	@Autowired
//	private YAMLConfig myConfig;

	public MainApp() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	private final class WebMvcConfigurerAdapterExtension extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		}
	}

	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapterExtension();
	}

	public static void main(String[] args) {
		Classloader.loadLib();
		String defaultCharacterEncoding = System.getProperty("file.encoding");
		System.out.println("defaultCharacterEncoding by property: " + defaultCharacterEncoding);
		System.setProperty("file.encoding", "UTF-8");
		defaultCharacterEncoding = System.getProperty("file.encoding");
		System.out.println("defaultCharacterEncoding by property reset: " + defaultCharacterEncoding);
		setTimeZone();

		Boolean continue_ = true;
		try {
			RedisDataConfig.redisConnectionFactory();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("KET NOI REDIS THAT BAI, KIEM TRA LAI CACHING");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				continue_ = false;
			}
			System.exit(5);
		}
		if (continue_) {
		
			SpringApplication.run(MainApp.class, args);
		}
	}

	private static void setTimeZone() {
		String defaultTimeZone = "Asia/Bangkok";
		// checking default time zone
		String timezoneId = defaultTimeZone;
		// Calendar.getInstance().getTimeZone().getID();
		// create time zone object

		TimeZone tzone = TimeZone.getTimeZone(timezoneId);

		System.out.println("Current TZ:" + tzone);
		// set time zone to default
		tzone.setDefault(tzone);

		// checking default time zone
		System.out.println("Default time zone:" + tzone);
	}

	@SuppressWarnings("resource")
	@Bean
	public CommandLineRunner commandLineRunner() {
		try {
			return args -> {
				System.out.println("SERVER CONFIG:........");
//				System.out.println(myConfig.getEnvironment());
//				System.out.println(myConfig.getName());
//				System.out.println(myConfig.getServers());
//				System.out.println(myConfig.getCard_url());

				System.out.println("Let's inspect the beans provided by Spring Boot:");
				ApplicationContext context = null;
				context = new FileSystemXmlApplicationContext("./config/Beans.xml");
				String[] beanNames = ((ListableBeanFactory) context).getBeanDefinitionNames();
				Arrays.sort(beanNames);
				for (String beanName : beanNames) {
					System.out.println(beanName);
				}
				log.info("-----------_START SERVER SUCCESS AT----------" + new Date());
			};
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
