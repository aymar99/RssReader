package com.aymar.rss.example;

import com.aymar.rss.Feed;
import com.aymar.rss.FeedItem;

public class RssReaderExample {
  public static void main(String[] args) {
    Feed feed = new Feed();
    FeedItem feedItem = new FeedItem();
    feedItem.readFeed();
  }
}
