package com.aymar.rss;

import static com.aymar.rss.RssConstants.*;
import static com.aymar.rss.RssConstants.ITEM;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedItemHandler extends DefaultHandler {
  private boolean item = false;
  private boolean title = false;
  private boolean link = false;
  private boolean description = false;
  private boolean publishedDate = false;
  private List<FeedItem> feedItemList = new ArrayList<>();
  private FeedItem newFeedItem;

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    if (qName.equalsIgnoreCase(ITEM)) {
      newFeedItem = new FeedItem();
      item = true;
    }
    if (item) {
      if (qName.equalsIgnoreCase(TITLE)) {
        title = true;
      }
      if (qName.equalsIgnoreCase(LINK)) {
        link = true;
      }
      if (qName.equalsIgnoreCase(DESCRIPTION)) {
        description = true;
      }
      if (qName.equalsIgnoreCase(PUBLISHED_DATE)) {
        publishedDate = true;
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equalsIgnoreCase(ITEM)) {
      item = false;
      feedItemList.add(newFeedItem);
    }
  }

  public void characters(char ch[], int start, int length) throws SAXException {
    if (item) {
      if (title) {
        newFeedItem.setTitle(new String(ch, start, length));
        title = false;
      }
      if (link) {
        newFeedItem.setLink(new String(ch, start, length));
        link = false;
      }
      if (description) {
        newFeedItem.setDescription(new String(ch, start, length));
        description = false;
      }
      if (publishedDate) {
        newFeedItem.setPubDate(new String(ch, start, length));
        publishedDate = false;
      }
    }
  }

  public List<FeedItem> getFeedItemList() {
    return feedItemList;
  }
}
