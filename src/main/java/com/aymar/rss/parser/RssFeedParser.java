package com.aymar.rss.parser;

import com.aymar.rss.dto.FeedItem;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import lombok.SneakyThrows;

public class RssFeedParser {

  private SAXParserFactory factory = SAXParserFactory.newInstance();
  FeedItemHandler feedItemHandler = new FeedItemHandler();

  @SneakyThrows
  public List<FeedItem> parseRssFeedItems(String url) {
    SAXParser saxParser = factory.newSAXParser();
    saxParser.parse(url, feedItemHandler);
    return feedItemHandler.getFeedItemList();
  }
}
