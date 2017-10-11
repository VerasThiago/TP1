package com.tp1.gol

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20


class GameOfLife extends ApplicationAdapter {
  //we assign a default value to the following
  //attributes, so that they are not abstract.

  var batch : SpriteBatch = _


  override def create : Unit = {
    batch = new SpriteBatch()
    }

  override def render() : Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    batch.end();
  }

  override def dispose() : Unit = {
    batch.dispose();
    }



}