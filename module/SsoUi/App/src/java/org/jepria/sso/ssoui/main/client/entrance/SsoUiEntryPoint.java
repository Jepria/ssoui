package org.jepria.sso.ssoui.main.client.entrance;
 
import org.jepria.sso.ssoui.main.client.SsoUiClientFactoryImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.technology.jep.jepria.client.entrance.JepEntryPoint;

public class SsoUiEntryPoint extends JepEntryPoint {
    
  SsoUiEntryPoint() {
    super(SsoUiClientFactoryImpl.getInstance());
  }
  
  @Override
  protected PlaceHistoryMapper createPlaceHistoryMapper() {
    return GWT.create(PlaceHistoryMapper.class);
  }

}
