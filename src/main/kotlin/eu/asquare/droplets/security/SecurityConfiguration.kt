package eu.asquare.droplets.security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfiguration(
    private val dropletUserDetailService: DropletUserDetailService,
    private val groupCodeEncoder: GroupCodeEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin().permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(dropletUserDetailService)
            ?.passwordEncoder(groupCodeEncoder)
    }
}