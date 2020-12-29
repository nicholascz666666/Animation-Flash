package cs3500.animator.model;

import java.util.Objects;

/**
 * The keyframe stores the position, size, and color of a shape. It represents the movement and
 * should be hashed into a hashmap to relate to a Shape {@Code HashMap}. It would be implemented by
 * Model and View.
 */
public class KeyFrame {

  //Local variable
  private final int tick;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int r;
  private final int g;
  private final int b;

  /**
   * Construct the keyFrame when another keyFrame preseent.
   *
   * @param k inputted keyFrame
   */
  public KeyFrame(KeyFrame k) {
    this(k.tick, k.x, k.y, k.width, k.height, k.r, k.g, k.b);
  }

  /**
   * Contruct the current keyframe for the later use.
   *
   * @param tick   time tick
   * @param x      x position
   * @param y      y position
   * @param width  width
   * @param height height
   * @param r      red value
   * @param g      green value
   * @param b      blue value
   */
  public KeyFrame(int tick, int x, int y, int width, int height, int r, int g, int b) {
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Compute the keyframe to string in the format would be implemented later for View.
   * @return String Key frame
   */
  @Override
  public String toString() {
    return String.format("%d %d %d %d %d %d %d %d ",
        this.tick, this.x, this.y, this.width, this.height, this.r, this.g, this.b);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof KeyFrame)) {
      return false;
    }

    KeyFrame that = (KeyFrame) a;

    return this.tick == that.tick
        && this.x == that.x
        && this.y == that.y
        && this.width == that.width
        && this.height == that.height
        && this.r == that.r
        && this.g == that.g
        && this.b == that.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tick, this.x, this.y, this.width, this.height,
        this.r, this.g, this.b);
  }

  /**
   * Gets the time of this Keyframe.
   *
   * @return the tick.
   */
  public int getT() {
    return this.tick;
  }


  /**
   * Gets the x position of this Keyframe.
   *
   * @return the position x.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y position of this Keyframe.
   *
   * @return the position y.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the width of this Keyframe.
   *
   * @return the width.
   */
  public int getW() {
    return this.width;
  }

  /**
   * Gets the height of this Keyframe.
   *
   * @return the height.
   */
  public int getH() {
    return this.height;
  }

  /**
   * Gets the red of this Keyframe.
   *
   * @return the red value.
   */
  public int getR() {
    return this.r;
  }

  /**
   * Gets the green of this Keyframe.
   *
   * @return the green value.
   */
  public int getG() {
    return this.g;
  }

  /**
   * Gets the blue of this Keyframe.
   *
   * @return the blue value.
   */
  public int getB() {
    return this.b;
  }
}
