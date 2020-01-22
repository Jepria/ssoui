package org.jepria.sso.ssoui.main.client;

import org.jepria.sso.ssoui.main.shared.text.SsoUiText;

import com.google.gwt.core.client.GWT;
import com.technology.jep.jepria.shared.JepRiaConstant;
 
public class SsoUiClientConstant extends JepRiaConstant {
 
  public static final String LOGIN_MODULE_ID = "Login";
  
  public static SsoUiText SsoUiText = (SsoUiText) GWT.create(SsoUiText.class);
}
