import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

/**
 * The Test for SVGView.
 */
public class TestSVGView {

  IModel m = new AnimatorModel.AnimeBuilder().setBounds(100, 200, 400, 300)
      .declareShape("R", "rectangle")
      .declareShape("C", "ellipse")
      .addKeyframe("R", 1, 190, 180, 20, 30, 0, 49, 90)
      .addMotion("R", 1, 190, 180, 20, 30, 0, 49, 90, 25,
          190, 180, 20, 30, 0, 49, 90)
      .addKeyframe("C", 1, 167, 210, 65, 30, 6, 247, 41)
      .addMotion("C", 1, 167, 210, 65, 30, 6, 247, 41,
          57, 167, 210, 65, 30, 6, 247, 41)
      .build();

  Appendable out = new StringBuilder();

  IView v = new SVGView();

  IController c = new AnimatorController(m, v, null);


  @Test
  public void testOutputToFile() {
    try {

      Appendable out = new FileWriter(new File("resources\testSVG1.txt"));
      IView v = new SVGView();

      File f = new File("resources\testSVG1.txt");
      assertTrue(f.exists());
    } catch (IOException e) {
      //if failed
    }
  }

  @Test
  public void testOutputDefault() {
    c.setOutput(out);
    c.setSVG();
    c.setSpeed(20);
    c.start();
    assertEquals(m.textSVG(20),
        out.toString());
  }
}
