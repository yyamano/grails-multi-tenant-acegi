package com.infusion.tenant.acegi;

import com.infusion.tenant.CurrentTenant
import org.springframework.security.context.SecurityContextHolder;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Apr 18, 2009
 * Time: 3:54:38 PM
 */
public class AcegiCurrentTenant implements CurrentTenant {

    public Integer get() {
        Object principal = SecurityContextHolder.getContext().getAuthentication()?.getPrincipal()
      if(principal?.getProperties()?.containsKey("domainClass")) {
        return principal?.domainClass?.userTenantId
      } else {
        return 0;
      }
    }

    public void set(Integer tenantId) {
      
    }
}
