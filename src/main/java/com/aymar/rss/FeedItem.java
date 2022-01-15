package com.aymar.rss;

import static com.aymar.rss.RssConstants.*;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedItem {
  String title;
  String link;
  String description;

  public void readFeed() {
    List<FeedItem> feedItemList;
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      DefaultHandler handler =
          new DefaultHandler() {

            boolean item = false;
            boolean title = false;
            boolean link = false;
            boolean description = false;

            public void startElement(
                String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
              if (qName.equalsIgnoreCase(ITEM)) {
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
              }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
              if (qName.equalsIgnoreCase(ITEM)) {
                item = false;
              }
            }

            public void characters(char ch[], int start, int length) throws SAXException {
              if (item) {
                if (title) {
                  System.out.println("title : " + new String(ch, start, length));
                  title = false;
                }
                if (link) {
                  System.out.println("link : " + new String(ch, start, length));
                  link = false;
                }
                if (description) {
                  System.out.println("description : " + new String(ch, start, length));
                  description = false;
                }
              }
            }
          };

      saxParser.parse("https://critter.blog/feed/", handler);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
