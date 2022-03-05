package com.aymar.rss.example;

import com.aymar.rss.Feed;
import com.aymar.rss.FeedItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RssReaderExample {
  public static void main(String[] args) {
    Feed feed = new Feed();
    FeedItem feedItem = new FeedItem();
    feedItem.readFeed();
    log.info("Feed title");
  }
}
