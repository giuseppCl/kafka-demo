package com.clinaz.kafka_demo.model;

public class ActivityEvent {

  private String userId;
  private String type;
  private String target;
  private String timestamp;

  public ActivityEvent() {

  }


  public ActivityEvent(String userId, String type, String target, String timestamp) {
    this.userId = userId;
    this.type = type;
    this.target = target;
    this.timestamp = timestamp;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}