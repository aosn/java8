package com.tasktoys.java8ws.mrbearing.ch9.ex10;

import java.util.Objects;

import com.tasktoys.java8ws.mrbearing.ch9.ex08.Point;

public class LabeledPoint implements Comparable<LabeledPoint>{

  private String label;
  private int x;
  private int y;

  public LabeledPoint(String _label, int _x, int _y) {
    super();
    this.label = _label;
    this.x = _x;
    this.y = _y;
  }
  
  @Override
  public int hashCode(){
    return Objects.hash(this.label,this.x,this.y);
  }

  @Override
  public boolean equals(Object otherObjects) {
    LabeledPoint other = (LabeledPoint) otherObjects;
    return Objects.equals(label, other.label) && Objects.equals(x, other.x)
        && Objects.equals(y, other.y);
  }
  
  @Override
  public int compareTo(LabeledPoint other) {

    int diff = Integer.compare(x, other.x);
    if (diff != 0)
      return diff;
    return Integer.compare(y, other.y);

  }
  

}
