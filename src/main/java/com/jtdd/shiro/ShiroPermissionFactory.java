package com.jtdd.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtdd.entity.Authority;
import com.jtdd.service.AuthorityService;



public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

	/** 记录配置中的过滤链 */  
    public static String definition = "";  
	
    @Autowired
    private AuthorityService authorityService;
	
	@Override
	public void setFilterChainDefinitions(String definitions) {
		definition = definitions;
		List<Authority> authorities = authorityService.getAllAuthority();
		Map<String, String> otherChains = new HashMap<String, String>(); 
		authorities.forEach(aus->{
			otherChains.put(aus.getResourceUrl(),"myPerm["+aus.getAuthName()+"]");
		});
		otherChains.put("/**", "authc");  
		// 加载配置默认的过滤链  
        Ini ini = new Ini();  
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);  
        if (CollectionUtils.isEmpty(section)) {  
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
        }
        // 加上数据库中过滤链  
        section.putAll(otherChains);  
        setFilterChainDefinitionMap(section);  
	}

}
