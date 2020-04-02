package eu.asquare.droplets.security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfiguration(
    private val dropletUserDetailService: DropletUserDetailService,
    private val groupCodeEncoder: GroupCodeEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web
            .ignoring()
            .antMatchers("/img/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(dropletUserDetailService)
            ?.passwordEncoder(groupCodeEncoder)
    }
}