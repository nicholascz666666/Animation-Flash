import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModel.AnimeBuilder;
import cs3500.animator.util.AnimationBuilder;
import org.junit.Test;

/**
 * The test for AnimatorModel.
 */
public class TestAnimatorModel {

  AnimatorModel m = new AnimeBuilder().setBounds(100, 200, 400, 300)
      .declareShape("R", "rectangle")
      .declareShape("C", "ellipse")
      .addKeyframe("R", 50, 290, 280, 22, 32, 2, 42, 92)
      .addKeyframe("R", 1, 190, 180, 20, 30, 0, 49, 90)
      .addMotion("R", 1, 190, 180, 20, 30, 0, 49, 90, 25,
          190, 180, 20, 30, 0, 49, 90)
      .addKeyframe("R", 10, 290, 280, 22, 32, 2, 42, 92)
      .addKeyframe("C", 1, 167, 210, 65, 30, 6, 247, 41)
      .addMotion("C", 1, 167, 210, 65, 30, 6, 247, 41,
          57, 167, 210, 65, 30, 6, 247, 41)
      .build();


  @Test
  public void testGetBound() {
    assertEquals(m.getBoundX(), 100);
    assertEquals(m.getBoundY(), 200);
    assertEquals(m.getWidth(), 400);
    assertEquals(m.getHeight(), 300);
  }

  @Test
  public void testTextInfo() {
    assertEquals("canvas 100 200 400 300\n"
            + "shape R rectangle\n"
            + "motion 1 190 180 20 30 0 49 90 10 290 280 22 32 2 42 92 \n"
            + "motion 10 290 280 22 32 2 42 92 25 190 180 20 30 0 49 90 \n"
            + "motion 25 190 180 20 30 0 49 90 50 290 280 22 32 2 42 92 \n"
            + "shape C ellipse\n"
            + "motion 1 167 210 65 30 6 247 41 57 167 210 65 30 6 247 41 \n",
        m.textInfo());
  }

  @Test
  public void testSVGinfo() {

    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\"> \n"
        + "<rect id=\"R\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90)\" "
        + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" "
        + "attributeName=\"x\" from=\"190\" to=\"290\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" "
        + "attributeName=\"y\" from=\"180\" to=\"280\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" "
        + "attributeName=\"width\" from=\"20\" to=\"22\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" "
        + "attributeName=\"height\" from=\"30\" to=\"32\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" "
        + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(2,42,92)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"300ms\" "
        + "attributeName=\"x\" from=\"290\" to=\"190\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"300ms\" "
        + "attributeName=\"y\" from=\"280\" to=\"180\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"300ms\" "
        + "attributeName=\"width\" from=\"22\" to=\"20\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"300ms\" "
        + "attributeName=\"height\" from=\"32\" to=\"30\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"300ms\" "
        + "attributeName=\"fill\" from=\"rgb(2,42,92)\" to=\"rgb(0,49,90)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" "
        + "attributeName=\"x\" from=\"190\" to=\"290\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" "
        + "attributeName=\"y\" from=\"180\" to=\"280\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"500ms\" "
        + "dur=\"500ms\" attributeName=\"width\" from=\"20\" to=\"22\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"500ms\" "
        + "dur=\"500ms\" attributeName=\"height\" from=\"30\" to=\"32\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"500ms\" "
        + "dur=\"500ms\" attributeName=\"fill\" from=\"rgb(0,49,90)\" "
        + "to=\"rgb(2,42,92)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "<ellipse id=\"C\" cx=\"167\" cy=\"210\" rx=\"65\" ry=\"30\" "
        + "fill=\"rgb(6,247,41)\" visibility=\"visible\" >\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", m.textSVG(20));
  }

  @Test
  public void testGetAnimationLength() {
    assertEquals(57, m.getAnimationLength());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShape() {
    AnimationBuilder b = new AnimeBuilder().setBounds(100, 200, 400, 300)
        .declareShape("R", "rectangle").declareShape("R", "rectangle");
  }

}
