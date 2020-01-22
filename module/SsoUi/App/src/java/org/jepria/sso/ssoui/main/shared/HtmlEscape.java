package org.jepria.sso.ssoui.main.shared;

public class HtmlEscape {
  public static String escape(String unescaped) {
    if (unescaped == null) {
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    
    for (char c: unescaped.toCharArray()) {
      switch (c) {
      case '&': {
        sb.append("&amp;");
        break;
      }
      case '<': {
        sb.append("&lt;");
        break;
      }
      case '>': {
        sb.append("&gt;");
        break;
      }
      case '"': {
        sb.append("&quot;");
        break;
      }
      case '\'': {
        sb.append("&#39;");
        break;
      }
      default: {
        sb.append(c);
        break;
      }
      }
    }
    
    return sb.toString();
  }
}
