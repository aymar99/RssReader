package com.aymar.rss.dto;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class FeedItem {
  private String title;
  private String link;
  private String description;
  private String pubDate;
}
