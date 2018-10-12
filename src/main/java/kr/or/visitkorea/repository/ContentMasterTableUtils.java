package kr.or.visitkorea.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ContentMasterTableUtils {

  public static String getCotId(Executer executer, String contentId) throws SQLException {
    String query = "select COT_ID from CONTENT_MASTER where CONTENT_ID='" + contentId + "'";
    List<Map<String, String>> result = executer.selectQuery(query);
    if (result == null || result.size() == 0)
      return null;
    return result.get(0).get("COT_ID");
  }
}