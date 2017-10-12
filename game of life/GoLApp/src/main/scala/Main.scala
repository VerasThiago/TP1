
import jdk.internal.org.objectweb.asm.commons.RemappingAnnotationAdapter

import scala.util.Random
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Line, Rectangle}



object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Game of Life"

    scene = new Scene (250, 280) {
      //linhas
      val linha0 = Line(0, 0, 250, 0)
      val linha1 = Line(0, 25, 250, 25)
      val linha2 = Line(0, 50, 250, 50)
      val linha3 = Line(0, 75, 250, 75)
      val linha4 = Line(0, 100, 250, 100)
      val linha5 = Line(0, 125, 250, 125)
      val linha6 = Line(0, 150, 250, 150)
      val linha7 = Line(0, 175, 250, 175)
      val linha8 = Line(0, 200, 250, 200)
      val linha9 = Line(0, 225, 250, 225)
      val linha10 = Line(0, 250, 250, 250)
      //colunas
      val column1 = Line(25, 0, 25, 250)
      val column2 = Line(50, 0, 50, 250)
      val column3 = Line(75, 0, 75, 250)
      val column4 = Line(100, 0, 100, 250)
      val column5 = Line(125, 0, 125, 250)
      val column6 = Line(150, 0, 150, 250)
      val column7 = Line(175, 0, 175, 250)
      val column8 = Line(200, 0, 200, 250)
      val column9 = Line(225, 0, 225, 250)
      //botoes
      val start = new Button("Start")
      start.layoutX = 0
      start.layoutY = 255
      val stop = new Button("Stop")
      stop.layoutX = 100
      stop.layoutY = 255
      val next = new Button("Next")
      next.layoutX = 210
      next.layoutY = 255
      GridPane

      content = List(
        //linhas
        linha0, linha1,linha2,linha3,linha4,linha5,linha6,linha7,linha8,linha9,linha10,
        //colunas
        column1, column2, column3,column4,column5,column6,column7,column8, column9,
        //botoes
        start, stop, next
      )

    }

  }
}