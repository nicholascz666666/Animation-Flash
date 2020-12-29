package cs3500.animator.model;

/**
 * Class shape contain the baisc name, type and keyframe of one shape. It could be implment as the
 * transfer between the Model adn View part.
 */
public class Shape {

  private final String name;
  private final String type;
  private final KeyFrame frame;

  /**
   * Constuct the shape, by setting the shape and keyframe.
   *
   * @param shape inputted shape
   * @param frame inputted frame
   */
  public Shape(Shape shape, KeyFrame frame) {
    this(shape.name, shape.type, frame);
  }

  /**
   * Contruct the shape by setting name, type and keyframe.
   *
   * @param name  name of shape
   * @param type  type of shape
   * @param frame keyframe of shape
   */
  public Shape(String name, String type, KeyFrame frame) {
    this.name = name;
    this.type = type;
    this.frame = frame;
  }

  /**
   * Get the name of shape.
   *
   * @return string name of shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the type of the shape.
   *
   * @return string type of shape
   */
  public String getType() {
    return this.type;
  }

  /**
   * Get the frame of shape.
   *
   * @return Keyframe of the shape
   */
  public KeyFrame getFrame() {
    return this.frame;
  }
}
