package com.aymar.rss;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Feed {

  String title;
  String description;
  List<Post> postList;

  public void test() {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      DefaultHandler handler =
          new DefaultHandler() {

            boolean title = false;
            boolean link = false;

            public void startElement(
                String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
              if (qName.equalsIgnoreCase("title")) {
                title = true;
              }
              if (qName.equalsIgnoreCase("link")) {
                link = true;
              }
            }

            public void characters(char ch[], int start, int length) throws SAXException {
              if (title) {
                System.out.println("title : " + new String(ch, start, length));
                title = false;
              }
              if (link) {
                System.out.println("link : " + new String(ch, start, length));
                link = false;
              }
            }
          };

      saxParser.parse("https://hnrss.github.io/updates.xml", handler);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
