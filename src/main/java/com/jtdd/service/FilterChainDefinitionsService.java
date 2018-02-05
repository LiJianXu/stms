package com.jtdd.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtdd.entity.Authority;
import com.jtdd.shiro.ShiroPermissionFactory;

@Service
public class FilterChainDefinitionsService {
	
    
	@Autowired
	private ShiroPermissionFactory permissionFactory;
	
	public void reloadFilterChains() {  
        synchronized (permissionFactory) {   //强制同步，控制线程安全  
            AbstractShiroFilter shiroFilter = null;  
  
            try {  
                shiroFilter = (AbstractShiroFilter) permissionFactory.getObject();  
  
                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter  
                        .getFilterChainResolver();  
                // 过滤管理器  
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();  
                // 清除权限配置  
                manager.getFilterChains().clear();  
                permissionFactory.getFilterChainDefinitionMap().clear();  
                // 重新设置权限  
                permissionFactory.setFilterChainDefinitions(ShiroPermissionFactory.definition);//传入配置中的filterchains  
  
                Map<String, String> chains = permissionFactory.getFilterChainDefinitionMap();  
                
                //重新生成过滤链  
                if (!CollectionUtils.isEmpty(chains)) {
                    chains.forEach((url, definitionChains) -> {  
                        manager.createChain(url, definitionChains.trim().replace(" ", ""));  
                    });
                }
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
