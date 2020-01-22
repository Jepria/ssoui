package org.jepria.sso.ssoui.login.shared.record;

import java.util.HashMap;
import java.util.Map;

import com.technology.jep.jepria.shared.field.JepTypeEnum;
import com.technology.jep.jepria.shared.record.JepRecordDefinition;
 
public class LoginRecordDefinition extends JepRecordDefinition {
 
  public static final LoginRecordDefinition instance = new LoginRecordDefinition();
 
  private LoginRecordDefinition() {
    super(buildTypeMap()
      , new String[]{}
    );
  }
 
  private static Map<String, JepTypeEnum> buildTypeMap() {
    Map<String, JepTypeEnum> typeMap = new HashMap<String, JepTypeEnum>();
    return typeMap;
  }
}
