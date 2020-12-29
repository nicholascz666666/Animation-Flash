package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class represents the ending mdoel of the animation. Which contain the name, type and all the
 * related future movement inside, using HashMap {@Code Hashmap}, implemented by interface {@Code
 * AnimaitorModel}. It would be future used by IView for viewing part and IController for
 * controlling.
 */
public class AnimatorModel implements IModel {

  //local vairables
  private final int boundX;
  private final int boundY;
  private final int width;
  private final int height;
  private Map<Shape, ArrayList<KeyFrame>> hashShapes;
  private ArrayList<Shape> shapes;
  private int animationLength;

  /**
   * Constructs a Animator model the AnimationBuilder.
   *
   * @param b inserted builder
   * @throws IllegalArgumentException if shapes are null
   */
  public AnimatorModel(AnimeBuilder b) {
    this.boundX = b.boundX;
    this.boundY = b.boundY;
    this.width = b.width;
    this.height = b.height;
    this.hashShapes = b.hashShapes;
    this.shapes = b.shapes;
    this.animationLength = b.animationLength;
  }

  /**
   * Inserted provided AnimeBuilder which would be used to build the AnimeModel.
   */
  public static final class AnimeBuilder implements AnimationBuilder<AnimatorModel> {

    private LinkedHashMap<Shape, ArrayList<KeyFrame>> hashShapes = new LinkedHashMap<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private int boundX;
    private int boundY;
    private int width;
    private int height;
    private int animationLength = 0;


    @Override
    public AnimatorModel build() {
      return new AnimatorModel(this);
    }


    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.boundX = x;
      this.boundY = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      try {
        for (Shape shape : hashShapes.keySet()) {
          if (shape.getName().equals(name)) {
            throw new IllegalArgumentException("Repeated name.");
          }
        }
      } catch (NullPointerException e) {
        //When there is nothing in the list;
      }

      Shape newOne = new Shape(name, type, null);
      hashShapes.put(newOne, new ArrayList<>());
      shapes.add(newOne);

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {

      if (!containsName(name)) {
        throw new IllegalArgumentException("Shape not exists");
      }

      if (isInValidTick(name, t1, t2)) {
        throw new IllegalArgumentException("Invalid motion");
      }

      //Addmotion would use addkeyframe, all motion would be split into two frame
      this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
      this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);

      if (this.animationLength < t2) {
        this.animationLength = t2;
      }

      return this;
    }

    //Check whether the motion was implement on an existing shape.
    private boolean containsName(String name) {
      boolean find = false;
      for (Shape shape : hashShapes.keySet()) {
        if (shape.getName().equals(name)) {
          find = true;
        }
      }
      return find;
    }


    //Check whether the tick was valid according to starting and ending tick.
    private boolean isInValidTick(String name, int t1, int t2) {
      boolean find = false;
      ArrayList<KeyFrame> frames;
      for (Shape shape : hashShapes.keySet()) {
        if (shape.getName().equals(name)) {

          frames = hashShapes.get(shape);

          if (frames.size() > 1) {

            for (int i = 0; i < frames.size() - 1; i++) {

              if ((frames.get(i).getT() > t1
                  && t2 < frames.get(i + 1).getT()
                  && t2 > frames.get(i + 1).getT())
                  || (frames.get(i).getT() < t1
                  && t1 < frames.get(i + 1).getT()
                  && t2 < frames.get(i + 1).getT())) {
                find = true;
              }
            }
          }
        }
      }
      return find;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {

      if (!containsName(name)) {
        throw new IllegalArgumentException("Shape not exists");
      }

      KeyFrame key = new KeyFrame(t, x, y, w, h, r, g, b);

      for (Shape shape : hashShapes.keySet()) {
        if (shape.getName().equals(name)) {
          hashShapes.replace(shape, insertKeyFrame(name, key, hashShapes));

          break;
        }
      }

      if (this.animationLength < t) {
        this.animationLength = t;
      }

      shapes = initializeShapes(shapes, hashShapes);

      return this;
    }
  }

  //Initialize the ArrayList of shape, tranferring to the View part for drawing.
  private static ArrayList<Shape> initializeShapes(ArrayList<Shape> shapes,
      LinkedHashMap<Shape, ArrayList<KeyFrame>> hashShapes) {
    for (Shape shape : shapes) {
      ArrayList<KeyFrame> keyFrames = hashShapes.get(shape);

      if (shape.getFrame() == null) {

        // List of added KeyFrame is not empty
        if (keyFrames.size() != 0) {
          shape = new Shape(shape, keyFrames.get(0));
        }
      } else {

        // The initial keyframe changed
        if (shape.getFrame() != keyFrames.get(0)) {
          shape = new Shape(shape, keyFrames.get(0));
        }
      }
    }
    return shapes;
  }

  //Helper function to insertKeyFrames, insert, sort the frames based on their
  //order of tick.
  private static ArrayList<KeyFrame> insertKeyFrame(String name, KeyFrame k,
      LinkedHashMap<Shape, ArrayList<KeyFrame>> hashShapes) {

    LinkedHashMap<Shape, ArrayList<KeyFrame>> copy = hashShapes;
    ArrayList<KeyFrame> keyFrames = new ArrayList<>();

    for (Shape shape : copy.keySet()) {
      if (shape.getName().equals(name)) {
        keyFrames = copy.get(shape);

        KeyFrame k1 = null;

        for (KeyFrame key : keyFrames) {
          if (key.getT() == k.getT()) {
            k1 = key;
          }
        }

        if (k1 != null) {
          keyFrames.remove(k1);
        }
        keyFrames.add(k);

        keyFrames.sort(Comparator.comparingInt(KeyFrame::getT));

        break;
      }
    }
    return keyFrames;
  }

  @Override
  public int getBoundX() {
    int copy = boundX;
    return copy;
  }

  @Override
  public int getBoundY() {
    int copy = boundY;
    return copy;
  }

  @Override
  public int getWidth() {
    int copy = this.width;
    return copy;
  }

  @Override
  public int getHeight() {
    int copy = this.height;
    return copy;
  }

  /**
   * Update the Key frame based on the current tick. The data would be all implement the array of
   * shapes.
   *
   * @param tick current tick needed
   */
  @Override
  public void updateTick(int tick) {
    KeyFrame frame;
    ArrayList<Shape> copyShapes = new ArrayList<Shape>();
    ArrayList<KeyFrame> frames;

    for (Shape s : hashShapes.keySet()) {

      frames = hashShapes.get(s);

      if (frames.size() <= 1) {

        if (tick == frames.get(0).getT()) {
          copyShapes.add(new Shape(s, frames.get(0)));
        }

      } else {

        for (int i = 0; i < frames.size() - 1; i++) {

          int t1 = frames.get(i).getT();
          int t2 = frames.get(i + 1).getT();

          if (t1 <= tick && tick <= t2) {
            int newX = calcTween(t1, t2, tick, frames.get(i).getX(), frames.get(i + 1).getX());
            int newY = calcTween(t1, t2, tick, frames.get(i).getY(), frames.get(i + 1).getY());
            int newW = calcTween(t1, t2, tick, frames.get(i).getW(), frames.get(i + 1).getW());
            int newH = calcTween(t1, t2, tick, frames.get(i).getH(), frames.get(i + 1).getH());
            int newR = calcTween(t1, t2, tick, frames.get(i).getR(), frames.get(i + 1).getR());
            int newG = calcTween(t1, t2, tick, frames.get(i).getG(), frames.get(i + 1).getG());
            int newB = calcTween(t1, t2, tick, frames.get(i).getB(), frames.get(i + 1).getB());

            frame = new KeyFrame(tick, newX, newY, newW, newH, newR, newG, newB);
            copyShapes.add(new Shape(s, frame));
            break;
          }
        }
      }
    }
    this.shapes = copyShapes;
  }

  /**
   * Tween formula used to calculate the current state of argument based on the current tick.
   *
   * @param strT starting time
   * @param endT ending time
   * @param curT current time
   * @param a    arguemnt start
   * @param b    argument end
   * @return currrnet argument
   */
  protected int calcTween(int strT, int endT, int curT, int a, int b) {
    if (endT == strT) {
      throw new IllegalArgumentException("end tick must not be same as start tick");
    }
    int partA = a * (endT - curT) / (endT - strT);
    int partB = b * (curT - strT) / (endT - strT);

    return (partA + partB);
  }


  @Override
  public Map<Shape, ArrayList<KeyFrame>> getHashShapes() {
    Map<Shape, ArrayList<KeyFrame>> copy = hashShapes;
    return copy;
  }

  @Override
  public String textInfo() {
    StringBuilder sb = new StringBuilder();

    sb.append("canvas ").append(boundX).append(" ").append(boundY).append(" ")
        .append(width).append(" ").append(height).append("\n");

    for (Shape shape : hashShapes.keySet()) {
      sb.append("shape ").append(shape.getName()).append(" ").append(shape.getType()).append("\n");

      ArrayList<KeyFrame> frames = hashShapes.get(shape);

      if (frames.size() > 1) {

        for (int i = 0; i < frames.size() - 1; i++) {
          sb.append("motion ").append(frames.get(i).toString()).append(frames.get(i + 1).toString())
              .append("\n");
        }

      } else {

        sb.append("motion ").append(frames.get(0).toString()).append(frames.get(0).toString())
            .append("\n");
      }
    }

    return sb.toString();
  }

  @Override
  public String textSVG(int speed) {

    StringBuilder svg = new StringBuilder();
    ArrayList<KeyFrame> frames;

    svg.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\"> \n",
        this.boundX + this.width,
        this.boundY + this.height));

    for (Shape shape : hashShapes.keySet()) {

      String type = "";

      KeyFrame key = hashShapes.get(shape).get(0);

      StringBuilder output = new StringBuilder();

      switch (shape.getType()) {
        case "rectangle":
          type = "rect";
          svg.append(String.format("<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" "
                  + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >", type, shape.getName(),
              key.getX(),
              key.getY(), key.getW(), key.getH(), key.getR(), key.getG(),
              key.getB()));
          break;

        case "ellipse":
          type = "ellipse";
          svg.append(String.format("<%s id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" "
                  + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n", type, shape.getName(),
              key.getX(),
              key.getY(), key.getW(), key.getH(), key.getR(), key.getG(),
              key.getB()));
          break;
        default:
          throw new IllegalArgumentException("Invalid shape type");
      }

      frames = hashShapes.get(shape);

      if (frames.size() > 1) {

        for (int i = 0; i < frames.size() - 1; i++) {
          svg.append(getAnimeSVG(frames.get(i), frames.get(i + 1), type, speed));
        }
      }

      svg.append("</" + type + ">\n");
    }

    svg.append("\n</svg>");


    return svg.toString();
  }

  //Construct the animate part of the SVG based on the provided KeyFrame and type of Shape
  private String getAnimeSVG(KeyFrame start, KeyFrame end, String type, int speed) {

    String anime = "";

    if (start.getX() != end.getX()) {
      anime += animeBuilder(start.getX(), end.getX(), start.getT(), end.getT(), type,
          "x", "cx", speed);
    }
    if (start.getY() != end.getY()) {
      anime += animeBuilder(start.getY(), end.getY(), start.getT(), end.getT(), type,
          "y", "cy", speed);
    }

    if (start.getW() != end.getW()) {
      anime += animeBuilder(start.getW(), end.getW(), start.getT(), end.getT(), type,
          "width", "rx", speed);
    }
    if (start.getH() != end.getH()) {
      anime += animeBuilder(start.getH(), end.getH(), start.getT(), end.getT(), type,
          "height", "ry", speed);
    }
    if (start.getR() != end.getR()
        || start.getG() != end.getG() || start.getG() != end.getG()) {
      anime += colorSVG(start.getR(), end.getR(),
          start.getG(), end.getG(), start.getB(), end.getB(), start.getT(), end.getT(), speed);
    }
    return anime;
  }

  //Abstract Helper function for producing the sentence of the animaiton in SVG format. Inputted
  //start and end argument and start and end tick and String type.
  private String animeBuilder(int a, int b, int t1, int t2, String type,
      String recA, String ovalA, int speed) {

    StringBuilder output = new StringBuilder();

    if (t1 == t2) {
      t2 = t1 - 1;
    }
    output.append("<animate attributeType=\"xml\" begin=\"");
    output.append(t1 * speed);
    output.append("ms\" dur=\"");
    output.append((t2 - t1) * speed);
    output.append("ms\" attributeName=\"");

    if (type.equals("rect")) {
      output.append(recA);
    } else {
      output.append(ovalA);
    }

    output.append("\" from=\"").append(a).append("\" to=\"");
    output.append(b).append("\" fill=\"freeze\" />\n");

    return output.toString();

  }


  //Construct svg color part movement of Animaiton part.
  //Six values, keyframe, and type are inputted.
  private String colorSVG(int strr, int endr, int strg, int endg,
      int strb, int endb, int startTime, int endTime, int speed) {

    StringBuilder output = new StringBuilder();

    if (startTime == endTime) {
      startTime = startTime - 1;
    }

    output.append("<animate attributeType=\"xml\" begin=\"");
    output.append(startTime * speed);
    output.append("ms\" dur=\"");
    output.append((endTime - startTime) * speed);
    output.append("ms\" attributeName=\"fill\"");
    output.append(" from=\"rgb(").append(strr).append("," + strg)
        .append("," + strb);
    output.append(")\" to=\"");
    output.append("rgb(").append(endr).append("," + endg)
        .append("," + endb).append(")\" fill=\"freeze\" />\n");

    return output.toString();

  }


  @Override
  public ArrayList<Shape> getShapes() {
    return shapes;
  }

  @Override
  public int getAnimationLength() {
    return this.animationLength;
  }
}
