package com.root.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component /*Чтобы Spring мог распознать фильтр, нам нужно было определить его как Bean с аннотацией @Component.*/
@Order(1) /*И чтобы фильтры срабатывали в правильном порядке – нам нужно было использовать аннотацию @Order.*/
public class TransFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransFilter.class);

    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            LOGGER.info(
                    "Starting a transaction for req : {}",
                    req.getRequestURI());

            chain.doFilter(request, response);
            LOGGER.info(
                    "Committing a transaction for req : {}",
                    req.getRequestURI());
        }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    // other methods
    }

@Component
@Order(2)
class RequestResponseLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        chain.doFilter(request, response);
        LOGGER.info(
                "Logging Response :{}",
                res.getContentType());
    }

    // other methods


/*В приведенном выше примере наши фильтры регистрируются по умолчанию для всех URL-адресов в нашем приложении.
 Однако иногда мы можем захотеть, чтобы фильтр применялся только к определенным шаблонам URL-адресов.*/

/*В этом случае мы должны удалить аннотацию @Component из определения класса фильтра и зарегистрировать фильтр с
помощью компонента регистрации фильтра:*/

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/users/*");

        return registrationBean;
    }
}

/*Теперь фильтр будет применяться только для путей, соответствующих шаблону /users/*.*/
/*Чтобы задать шаблоны URL-адресов для фильтра, мы можем использовать addUrlPatterns() или setUrlPatterns() methods.*/

