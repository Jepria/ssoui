package org.jepria.sso.ssoui.login.client;

import org.jepria.sso.ssoui.login.shared.LoginConstant;
import org.jepria.sso.ssoui.login.shared.text.LoginText;

import com.google.gwt.core.client.GWT;
 
public class LoginClientConstant extends LoginConstant {
 
  public static LoginText loginText = (LoginText) GWT.create(LoginText.class);
  
}
