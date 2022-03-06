package com.aymar.rss.example;

import com.aymar.rss.parser.RssFeedParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

@Slf4j
public class RssReaderExample {
  public static void main(String[] args) {
    BasicConfigurator.configure();
    RssFeedParser parser = new RssFeedParser();
    log.info("Feed list size {}", parser.parseRssFeedItems("https://den.dev/index.xml").size());
  }
}
