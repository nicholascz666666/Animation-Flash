import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModel.AnimeBuilder;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TexualView;
import org.junit.Test;

/**
 * Test the Controller.
 */
public class TestController {

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


  IView testView = new TexualView();
  IView svgView = new SVGView();
  Appendable out = new StringBuilder();
  IController cText = new AnimatorController(m, testView, null);
  IController cSVG = new AnimatorController(m, svgView, null);


  @Test
  public void testSetText() {
    cText.setOutput(out);
    cText.setTextInfo();
    cText.start();
    assertEquals(this.out.toString(), m.textInfo());
  }

  @Test
  public void testSetSVG() {
    cSVG.setOutput(out);
    cSVG.setSpeed(20);
    cSVG.setSVG();
    cSVG.start();
    assertEquals(this.out.toString(), m.textSVG(20));
  }


}
