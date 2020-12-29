import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.TexualView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

/**
 * Test for TextView.
 */
public class TestTextView {

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

  IView v = new TexualView();

  IController c = new AnimatorController(m, v, null);


  @Test
  public void testOutputToFile() {
    try {
      Appendable out = new FileWriter(new File("resources\textTest1.txt"));
      c.setOutput(out);
      c.setTextInfo();
      c.start();
      File f = new File("resources\textTest1.txt");
      assertTrue(f.exists());
    } catch (IOException e) {
      //if failed
    }
  }

  @Test
  public void testOutputDefault() {
    c.setOutput(out);
    c.setTextInfo();
    c.start();
    assertEquals(m.textInfo(),
        out.toString());
  }
}
