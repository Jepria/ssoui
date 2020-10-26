package org.jepria.sso.ssoui.login.server;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jepria.sso.ssoui.login.shared.service.LoginService;

import com.technology.jep.jepria.server.service.JepServiceServlet;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.load.FindConfig;
import com.technology.jep.jepria.shared.load.PagingConfig;
import com.technology.jep.jepria.shared.load.PagingResult;
import com.technology.jep.jepria.shared.load.SortConfig;
import com.technology.jep.jepria.shared.record.JepRecord;

public class LoginServiceImpl extends JepServiceServlet implements LoginService {

  private static final long serialVersionUID = 2434671796678760044L;

  @Override
  public void bindParamsToSession(String csrfTokenClient, Map<String, String> params) {
    // создадим новую сессию, если старая истекла
    HttpSession session = getThreadLocalRequest().getSession(true/*обязательно true!*/);

    // сохраним в сессию csrf-токен для последующей проверки при первом запросе (любом) после логина
    CsrfTokenUtil.saveTokenClient(csrfTokenClient, session);

    // привяжем к сессии клиентские параметры
    if (params != null) {
      for (String paramName: params.keySet()) {
        session.setAttribute(paramName, params.get(paramName));
      }
    }
  }

  
  // TODO: Jepria не позволяет создать ПРОСТО СервисИмпл.
  // Обязательно наследоваться от Jep-чего-нибудь и затыкать методы, когда ни один из них не нужен...
  
  @Override
  public JepRecord create(FindConfig createConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(FindConfig deleteConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public JepRecord update(FindConfig updateConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public PagingResult<JepRecord> find(PagingConfig pagingConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public PagingResult<JepRecord> sort(SortConfig sortConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public PagingResult<JepRecord> paging(PagingConfig pagingConfig) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void prepareExcel(PagingConfig pagingConfig, List<JepRecord> selectedRecords, List<String> reportHeaders,
      List<String> reportFields) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Boolean isRefreshNeeded(Integer listUID) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer prepareDownload(String fileName, String mimeType, String fieldName, String recordKey) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer prepareDownload(String fileName, String mimeType, String fieldName, String recordKey,
      String contentDisposition, String extension, String fileNamePrefix) {
    throw new UnsupportedOperationException();
  }

}
