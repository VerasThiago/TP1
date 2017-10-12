package com.tp1.gol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tp1.gol.GoL;
import com.tp1.gol.GameOfLife;


object DesktopLauncher extends scala.App {
  val config = new LwjglApplicationConfiguration()
  config.width = 500
  config.height = 500
  config.title = "Game of Life - Star Wars"
  val game = new GameOfLife()
  val app = new LwjglApplication(game, config)
}
