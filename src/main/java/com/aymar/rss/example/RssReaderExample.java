package com.aymar.rss.example;

import com.aymar.rss.parser.RssFeedParser;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

@Slf4j
public class RssReaderExample {
  public static void main(String[] args) {
    BasicConfigurator.configure();
    RssFeedParser parser = new RssFeedParser();
    log.info(
        "Feed list size {}", parser.parseRssFeedItemFromURL("https://den.dev/index.xml").size());
    List<String> urlList = new ArrayList<>();
    urlList.add("https://den.dev/index.xml");
    urlList.add("https://den.dev/index.xml");
    log.info("Feed list size {}", parser.parseRssFeedItemFromMultipleUrls(urlList).size());
  }
}
