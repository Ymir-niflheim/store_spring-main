package com.team.security;

import javax.annotation.PostConstruct;

import com.team.jwt.JwtRequestFilter;
import com.team.jwt.JwtSellerFilter;
import com.team.jwt.JwtUserFilter;
import com.team.service.SecurityUserDetailServiceimpl;
import com.team.service.SellerDetailService;
import com.team.service.UserDetailServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// 1
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
//     @Autowired
//     SecurityUserDetailServiceimpl sudService;

//     @Bean
//     public BCryptPasswordEncoder encode(){
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(sudService).passwordEncoder(encode());
//     }

//     @Autowired
//     private JwtRequestFilter jwtRequestFilter;

//     @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//         .antMatchers("/admin", "/admin/*", "/api/admin/*").hasAnyRole("ADMIN")
//         .anyRequest().permitAll();

//         http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//         http.csrf().disable();
//         http.headers().frameOptions().sameOrigin();

//     }
// }


// 2
// @Configuration
// @EnableWebSecurity
// @Order(2)
// public class SecurityConfig extends WebSecurityConfigurerAdapter{

//     @Autowired
//     UserDetailServiceimpl uService;

//     @Bean
//     public BCryptPasswordEncoder encode(){
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(uService).passwordEncoder(encode());
//     }

//     @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

//     @Autowired
//     private JwtRequestFilter jwtRequestFilter;


//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//         .antMatchers("/admin", "/admin/*", "/api/admin/*").hasAnyRole("ADMIN")
//         .anyRequest().permitAll();

//         http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//         http.csrf().disable();
//         http.headers().frameOptions().sameOrigin();

//     }

// }

// @Configuration
// @Order(1)
// class SecondSecurityConfig extends WebSecurityConfigurerAdapter{
    
//     @Autowired
//     SellerDetailService sService;

//     @Bean
//     public BCryptPasswordEncoder encode(){
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(sService).passwordEncoder(encode());
//     }

//     @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

//     @Autowired
//     private JwtRequestFilter jwtRequestFilter;


//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//         .antMatchers("/admin", "/admin/*", "/api/admin/*").hasAnyRole("ADMIN")
//         .anyRequest().permitAll();

//         http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//         http.csrf().disable();
//         http.headers().frameOptions().sameOrigin();

//     }
// }




// 3
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig{

//     @Order(1)
//     @Configuration
//     public static class FirstUserConfig extends WebSecurityConfigurerAdapter{

//         @Autowired
//         @Qualifier("UserDetailServiceimpl")
//         private UserDetailsService UserDetailServiceimpl;
//         // @Autowired
//         // UserDetailServiceimpl uDetailServiceimpl;

//         @Bean
//         public BCryptPasswordEncoder encode(){
//             return new BCryptPasswordEncoder();
//         }

//         // https://stackoverflow.com/questions/62185767/spring-boot-app-with-two-authentication-authenticationmanager-beans-problem

//         @Override
//         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//             System.out.println("uService ??????");
//             // auth.userDetailsService(uService).passwordEncoder(encode());
//             auth.userDetailsService(UserDetailServiceimpl).passwordEncoder(encode());
//         }
//         // @PostConstruct
//         //@Primary //?????? ????????? ???????????? ????????? ?????? ??????
//         @Override
//         @Bean
//         public AuthenticationManager authenticationManagerBean() throws Exception {
//             System.out.println("uManager ??????");
//             return super.authenticationManagerBean();
//         }

//         @Autowired
//         private JwtRequestFilter jwtRequestFilter;


//         @Override
//         protected void configure(HttpSecurity http) throws Exception {
//             http.authorizeRequests()
//             // .antMatchers("/xxx", "/xexe/*", "/oxox/admin/*").hasAnyRole("XOXO")
//             .anyRequest().permitAll();

//             http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//             http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//             http.csrf().disable();
//             http.headers().frameOptions().sameOrigin();

//         }
//     }
//     @Order(2)
//     @Configuration
//     public static class SecondSellerConfig extends WebSecurityConfigurerAdapter{

        
//         @Autowired
//         @Qualifier("SellerDetailService")
//         private UserDetailsService SellerDetailService;

//         // @Autowired
//         // SellerDetailService sService;

//         @Bean
//         public BCryptPasswordEncoder encode(){
//             return new BCryptPasswordEncoder();
//         }

//         @Override
//         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//             System.out.println("sService ??????");
//             auth.userDetailsService(SellerDetailService).passwordEncoder(encode());
//         }

//         // @PostConstruct
//         @Override
//         @Bean
//         public AuthenticationManager authenticationManagerBean() throws Exception {
//             System.out.println("sManager ??????");
//             return super.authenticationManagerBean();
//         }


//         @Autowired
//         private JwtRequestFilter jwtRequestFilter;


//         @Override
//         protected void configure(HttpSecurity http) throws Exception {
//             http.authorizeRequests()
//             .antMatchers("/admin", "/admin/*").hasAnyRole("ADMIN")
//             .anyRequest().permitAll();

//             http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//             http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//             http.csrf().disable();
//             http.headers().frameOptions().sameOrigin();

//         }
//     }
    
// }


// 4
// @EnableWebSecurity
// @Configuration
// public class SecurityConfig {

// 	@Configuration
// 	@Order(1)
// 	public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

// 		@Autowired
// 		private JwtSellerFilter jwtSellerFilter;

// 		@Autowired
// 		private SellerDetailService sService;

// 		@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
// 		@Override
// 		public AuthenticationManager authenticationManagerBean() throws Exception {
// 			return super.authenticationManagerBean();
// 		}

// 		@Bean
// 		public PasswordEncoder passwordEncoder() {
// 			return new BCryptPasswordEncoder();
// 		}

// 		@Override
// 		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 			 BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
// 			 auth.userDetailsService(sService).passwordEncoder(bcpe);
// 			//auth.authenticationProvider(authenticationProvider());
// 		}

// 		@Override
// 		protected void configure(HttpSecurity http) throws Exception {

// 			http.antMatcher("/seller/**").authorizeRequests().anyRequest().authenticated().and();

// 			// ?????? ??????
// //			http.authorizeRequests().antMatchers("/admin", "/admin/*").hasAuthority("ADMIN")
// //					.antMatchers("/seller", "/seller/*", "/api/seller/*").hasAnyAuthority("ADMIN", "SELLER")
// //					.antMatchers("/api/member/update", "/api/member/delete", "/api/member/passwd")
// //					.hasAnyAuthority("SELLER", "CUSTOMER").anyRequest().permitAll().and();

// 			http.csrf().disable();
// 			// http.authorizeRequests().anyRequest().permitAll().and();

// 			// // ????????? ?????????
// 			// http.formLogin().loginPage("/member/login").loginProcessingUrl("/member/login").usernameParameter("userid")
// 			// 		.passwordParameter("userpw").permitAll().successHandler(new MemberLoginSuccessHandler()).and();

// 			// // ???????????? ?????????
// 			// http.logout().logoutUrl("/member/logout").logoutSuccessHandler(new MemberLogoutSuccessHandler())
// 			// 		.invalidateHttpSession(true).clearAuthentication(true).permitAll().and();

// 			// ???????????? ????????????
// 			// http.exceptionHandling().accessDeniedPage("/page403").and();

// 			// ?????? ????????????(@controller ?????? ?????????)
// 			http.addFilterBefore(jwtSellerFilter, UsernamePasswordAuthenticationFilter.class);

// 			// jwt??? restcontoller??????
// 			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 			// SessionCreationPolicy.ALWAYS - ?????? ????????? ??????
// 			// SessionCreationPolicy.IF_REQUIRED - ????????? ??????(??????)
// 			// SessionCreationPolicy.NEVER - ?????????????????????, ????????? ???????????? ??????
// 			// SessionCreationPolicy.STATELESS - ????????????????????? ????????? ??????????????? ?????? (JWT ????????????)

// 			// h2-console ??????, restcontroller ??????

// 			http.headers().frameOptions().sameOrigin();
// 		}
// 	}

// 	@Configuration
// 	@Order(2)
// 	public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

// 		@Autowired
// 		private UserDetailServiceimpl uService;
		
//         @Autowired
// 		private JwtUserFilter jwtUserFilter;

// 		@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
// 		@Override
// 		public AuthenticationManager authenticationManagerBean() throws Exception {
// 			return super.authenticationManagerBean();
// 		}

// 		@Override
// 		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 			 BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
// 			 auth.userDetailsService(uService).passwordEncoder(bcpe);
// 		}

// 		@Override
// 		protected void configure(HttpSecurity http) throws Exception {
// //			http.antMatcher("/member1/**").authorizeRequests().anyRequest().authenticated().and();

// 			http.antMatcher("/member1/**")
// 				.authorizeRequests()
// 	            .antMatchers("/admin/**").hasAnyRole("ADMIN")
// 	            .antMatchers("/order/**").hasAnyRole("USER")
// 	            .anyRequest().authenticated();
			
//                 http.addFilterBefore(jwtUserFilter, UsernamePasswordAuthenticationFilter.class);

// 			// ?????? ??????
// //			http.authorizeRequests().antMatchers("/admin", "/admin/*").hasAuthority("ADMIN")
// //					.antMatchers("/seller", "/seller/*", "/api/seller/*").hasAnyAuthority("ADMIN", "SELLER")
// //					.antMatchers("/api/member/update", "/api/member/delete", "/api/member/passwd")
// //					.hasAnyAuthority("SELLER", "CUSTOMER").anyRequest().permitAll().and();

// 			// http.formLogin().loginPage("/member1/login").loginProcessingUrl("/member1/login")
// 			// 		.usernameParameter("userid").passwordParameter("userpw").permitAll()
// 			// 		.successHandler(new MemberLoginSuccessHandler1()).and();

// 			// http.logout().logoutUrl("/member1/logout").logoutSuccessHandler(new MemberLogoutSuccessHandler())
// 			// 		.invalidateHttpSession(true).clearAuthentication(true).permitAll().and();

// 			http.csrf().disable();
// 			http.headers().frameOptions().sameOrigin();
// 		}
// 	}

// }

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Configuration
	public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean
		public AuthenticationProvider authenticationProvider() {
			return new CustomAuthenticationProvider();
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			 BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
//			 auth.userDetailsService(mService).passwordEncoder(bcpe);
			auth.authenticationProvider(authenticationProvider());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			//http.antMatcher("/member/**").authorizeRequests().anyRequest().authenticated().and();

			// ?????? ??????
//			http.authorizeRequests().antMatchers("/admin", "/admin/*").hasAuthority("ADMIN")
//					.antMatchers("/seller", "/seller/*", "/api/seller/*").hasAnyAuthority("ADMIN", "SELLER")
//					.antMatchers("/api/member/update", "/api/member/delete", "/api/member/passwd")
//					.hasAnyAuthority("SELLER", "CUSTOMER").anyRequest().permitAll().and();

			http.csrf().disable();
			http.authorizeRequests().anyRequest().permitAll().and();

			// // ????????? ?????????
			// http.formLogin().loginPage("/member/login").loginProcessingUrl("/member/login").usernameParameter("userid")
			// 		.passwordParameter("userpw").permitAll().successHandler(new MemberLoginSuccessHandler()).and();

			// // ???????????? ?????????
			// http.logout().logoutUrl("/member/logout").logoutSuccessHandler(new MemberLogoutSuccessHandler())
			// 		.invalidateHttpSession(true).clearAuthentication(true).permitAll().and();

			// ???????????? ????????????
			// http.exceptionHandling().accessDeniedPage("/page403").and();

			// ?????? ????????????(@controller ?????? ?????????)
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

			// jwt??? restcontoller??????
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			// SessionCreationPolicy.ALWAYS - ?????? ????????? ??????
			// SessionCreationPolicy.IF_REQUIRED - ????????? ??????(??????)
			// SessionCreationPolicy.NEVER - ?????????????????????, ????????? ???????????? ??????
			// SessionCreationPolicy.STATELESS - ????????????????????? ????????? ??????????????? ?????? (JWT ????????????)

			// h2-console ??????, restcontroller ??????

			http.headers().frameOptions().sameOrigin();
		}
	}
}