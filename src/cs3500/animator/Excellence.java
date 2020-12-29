package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.EditController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.Loop;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TexualView;
import cs3500.animator.view.VisualView;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * This class represents the animator that could be run and see the actual view of the objects. It
 * takes the commend and would be inputted with txt file including the commend applying the
 * Animation Model and AnimationView. With correctly formatted input file, it could generate
 * animation based off text description or svg file or visual animaiton in a GUI. There has another
 * View mode Edit View could allow user to implement and edit the view creating in the GUI.
 */
public class Excellence {

  /**
   * Main function could be used to implement the object, including Controller, Model and View, with
   * argument commend line. Taking the input file.
   * @param args string argument for commend line
   * @throws IOException if input output error
   * @throws IllegalArgumentException if error appears in model and controller
   */
  public static void main(String[] args) {
    String inName = "";
    String viewType = "";
    int speed = 1;
    FileWriter output = null;

    Appendable out = null;

    try {
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "-in":
            inName = args[i + 1];
            i += 1;
            break;
          case "-view":
            viewType = args[i + 1];
            i += 1;
            break;
          case "-out":
            output = new FileWriter(args[i + 1]);
            i += 1;
            break;
          case "-speed":
            speed = Integer.parseInt(args[i + 1]);
            i += 1;
            break;
          default:
            JOptionPane.showMessageDialog(null,
                "Wrong command supplied", null, JOptionPane.ERROR_MESSAGE);
        }
      }

      IModel model = AnimationReader
          .parseFile(new FileReader(inName), new AnimatorModel.AnimeBuilder());

      controllerFactory(viewType, model, speed, output);

      if (output != null) {
        output.close();
      }

    } catch (IOException ie) {
      JOptionPane.showMessageDialog(null,
          "IOException ",
          null, JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(null,
          e.getMessage(),
          null, JOptionPane.ERROR_MESSAGE);
    }
  }


  /**
   * According to the view type creating the view type and relevent controller.
   *
   * @param type   view type
   * @param model  inputted model
   * @param speed  inputted speed
   * @param output inputted output
   * @throws IOException if Appendable out fails
   */
  protected static void controllerFactory(String type, IModel model, int speed, Appendable output)
      throws IOException {
    IView view;
    IEditView editView;
    IController controller;
    EditController editController;
    Loop loop;

    switch (type) {
      case "text":
        view = new TexualView();
        controller = new AnimatorController(model, view, null);
        controller.setTextInfo();
        controller.setSpeed(speed);
        controller.setOutput(output);
        controller.start();
        break;
      case "svg":
        view = new SVGView();
        controller = new AnimatorController(model, view, null);
        controller.setSpeed(speed);
        controller.setOutput(output);
        controller.setSVG();
        controller.start();
        break;
      case "visual":
        view = new VisualView();
        loop = new Loop(view, model);
        controller = new AnimatorController(model, view, loop);
        controller.setSpeed(speed);
        controller.setOutput(output);
        controller.start();
        break;
      case "edit":
        editView = new EditView("Edit View!!");
        editController = new EditController(model);
        editController.setSpeed(speed);
        editController.setEditView(editView);
        break;
      default:
        JOptionPane.showMessageDialog(null,
            "Unknown view type: " + type,
            null, JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("Unknown view type: " + type);
    }
  }
}
