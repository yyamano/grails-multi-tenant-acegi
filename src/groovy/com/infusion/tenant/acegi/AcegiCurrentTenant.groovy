package com.infusion.tenant.acegi;

import com.infusion.tenant.CurrentTenant
import org.springframework.security.context.SecurityContextHolder
import com.infusion.util.event.EventBroker
import com.infusion.tenant.event.TenantChangedEvent;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Apr 18, 2009
 * Time: 3:54:38 PM
 */
public class AcegiCurrentTenant implements CurrentTenant {

  EventBroker eventBroker

  private ThreadLocal<Integer> tmpTenant = new ThreadLocal<Integer>()
  private List<Integer> loaded = new ArrayList<Integer>();

  public Integer get() {
    Integer rtn
    if (tmpTenant.get() != null) {
      rtn = tmpTenant.get()
    } else {
      rtn = getFromAcegi()
    }
    return rtn
  }

  private Integer getFromAcegi() {
    Object principal = SecurityContextHolder.getContext().getAuthentication()?.getPrincipal()
    if (principal?.getProperties()?.containsKey("domainClass")) {
      Integer tid = principal?.domainClass?.userTenantId
      if (tid) {
        if (!loaded.contains(tid)) {
          loaded.add(tid)
          eventBroker?.publish("newTenant", new TenantChangedEvent(0, tid))
        }
      }
      return tid
    } else {
      return 0;
    }
  }

  public void set(Integer tenantId) {
    Integer fromAcegi = getFromAcegi();
    if (fromAcegi == tenantId) {
      tmpTenant.set(null)
    } else {
      tmpTenant.set(tenantId)
    }
  }

  public void resetLoadedCache ( ) {
    loaded.clear()
  }

}
