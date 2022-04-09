package com.aymar.rss.parser;

import com.aymar.rss.dto.FeedItem;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RssFeedParser {

  private SAXParserFactory factory = SAXParserFactory.newInstance();
  ExecutorService rssParserPoll =
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  @SneakyThrows
  public List<FeedItem> parseRssFeedItemFromURL(String url) {
    SAXParser saxParser = factory.newSAXParser();
    FeedItemHandler feedItemHandler = new FeedItemHandler();
    saxParser.parse(url, feedItemHandler);
    return feedItemHandler.getFeedItemList();
  }

  @SneakyThrows
  public List<FeedItem> parseRssFeedItemFromMultipleUrls(List<String> urlList) {
    List<Future<List<FeedItem>>> futureList = new ArrayList<>();
    for (String url : urlList) {
      futureList.add(rssParserPoll.submit(() -> parseRssFeedItemFromURL(url)));
    }
    List<FeedItem> feedItems = new ArrayList<>();
    for (Future<List<FeedItem>> future : futureList) {
      feedItems.addAll(future.get());
    }
    rssParserPoll.shutdown();
    return feedItems;
  }
}
