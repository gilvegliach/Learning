package models;



import play.db.ebean.Model;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entry extends Model {
  public static Finder<String, Entry> find = new Finder<String, Entry>(String.class, Entry.class);

  @Id
  @Nullable
  public String id;
  public long dateMs;
  public int distanceMeters;
  public int timeSeconds;
  public double averageSpeedKmH;


  public Entry() {
  }

  public Entry(String id, long dateMs, int distanceMeters, int timeSeconds, double averageSpeedKmH) {
    this.id = id;
    this.dateMs = dateMs;
    this.distanceMeters = distanceMeters;
    this.timeSeconds = timeSeconds;
    this.averageSpeedKmH = averageSpeedKmH;
  }

  @Nullable
  public String getId() {
    return id;
  }

  public void setId(@Nullable String id) {
    this.id = id;
  }

  public long getDateMs() {
    return dateMs;
  }

  public void setDateMs(long dateMs) {
    this.dateMs = dateMs;
  }

  public int getDistanceMeters() {
    return distanceMeters;
  }

  public void setDistanceMeters(int distanceMeters) {
    this.distanceMeters = distanceMeters;
  }

  public int getTimeSeconds() {
    return timeSeconds;
  }

  public void setTimeSeconds(int timeSeconds) {
    this.timeSeconds = timeSeconds;
  }

  public double getAverageSpeedKmH() {
    return averageSpeedKmH;
  }

  public void setAverageSpeedKmH(double averageSpeedKmH) {
    this.averageSpeedKmH = averageSpeedKmH;
  }
}
