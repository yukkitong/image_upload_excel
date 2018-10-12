package kr.or.visitkorea.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.visitkorea.model.Image;

public class ImageTableUtils {

  private static final Logger logger = LoggerFactory.getLogger(ImageTableUtils.class);

  public static boolean hasItem(Executer executer, String cotId, String url) throws SQLException {
    String query = String.format("select count(*) CNT from IMAGE where COT_ID='%s' and URL='%s'", cotId, url);
    List<Map<String, String>> countQuery = executer.selectQuery(query);
    int cnt = Integer.parseInt(countQuery.get(0).get("CNT"));
    return cnt > 0;
  }

  public static int insertItem(Executer executer, Image image) throws SQLException {
    String query = String.format(
        "insert into IMAGE (IMG_ID, COT_ID, IMAGE_DESCRIPTION, URL, IS_THUBNAIL)"
            + " values ('%s', '%s', '%s', '%s', %d)",
        image.getImgId(), image.getCotId(), clean(image.getDesc()), image.getUrl(), image.getIsThumb());
    // logger.info("executed insert: {}", query);
    return executer.executeUpdate(query);
  }

  private static String clean(String source) {
    return source.replaceAll("'", "''");
  }
}