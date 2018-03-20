package cn.bysj.core.config;

import cn.bysj.core.web.filter.ManagerLoginInterceptor;
import cn.bysj.core.web.filter.StudentLoginInterceptor;
import cn.bysj.core.web.filter.TeacherLoginPrivilegeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author victor.qin
 * @date 2018/3/20 16:36
 */
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter  {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new TeacherLoginPrivilegeInterceptor()).addPathPatterns("/teacher/**");
        registry.addInterceptor(new StudentLoginInterceptor()).addPathPatterns("/student/**");
        registry.addInterceptor(new ManagerLoginInterceptor()).addPathPatterns("/manage/**");
        super.addInterceptors(registry);
    }
}
