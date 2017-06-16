package config.dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
//@Profile("dev")
//
//public class SecurityConfig_DEV extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	static PasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder(10);
//	}
//
//	@Configuration
//	@Order(1)
//	public static class UserWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication().withUser("1").password("1").roles("ADMIN");
//		}
//
//		@Override
//		public void configure(WebSecurity web) throws Exception {
//			web.ignoring().antMatchers("/resources/**");
//		}
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests()
//					.anyRequest().authenticated()
//					.and()
//					.formLogin()
//					.loginPage("/login")
//                    .failureForwardUrl("/login?error")
//                    .defaultSuccessUrl("/article/list")
//                    .permitAll()
//					.and()
//					.logout().permitAll()
//					.and();
//
//		}
//	}
//}
