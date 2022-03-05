package com.aymar.rss;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@Setter
public class FeedItem {
  private String title;
  private String link;
  private String description;
  private String pubDate;


  @Override
  public String toString() {
    return "FeedItem{" +
            "title='" + title + '\'' +
            ", link='" + link + '\'' +
            ", description='" + description + '\'' +
            ", pubDate='" + pubDate + '\'' +
            '}';
  }

  public void readFeed() {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      FeedItemHandler feedItemHandler = new FeedItemHandler();
      saxParser.parse("https://den.dev/index.xml",feedItemHandler);
      List<FeedItem> feedItems = feedItemHandler.getFeedItemList();
      log.info("FeedItem size: {}","test");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
