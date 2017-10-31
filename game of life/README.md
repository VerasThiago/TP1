# Game of Life in Scala

  - Framework-inspired;
  - Strategy;
  - Reflection;
  - GUI em Scala (ScalaFX);
  - Wrap-around board (mapa de Karnaugh).

## Members
- André Cassio
- Henrique Leo
- Giovanni Guidini
- Thiago Veras
- Vitor Dullens


### Recomended Sources

  - [Design Pattern Elements](http://www.uml.org.cn/c++/pdf/DesignPatterns.pdf)
  - [Explanation of the Game by J. Conway](https://youtu.be/E8kUJL04ELA)
  
  # Project Structure
  The project of Game of Life spans 3 different projects, following a framework-like structure. Each project and its contributions to the whole application is briefly explained here. The jars directory holds all compiled code. It has to be used in projects that aim to extend ours, or have ours as dependency/library.
  
  ### GoLStrategy
  This project is the Base of our application. It is not executable by itself. GoLStrategy implements the trait that will define any rule, and the definitions of the elements necessary to play the game, regardless of implementation. Conway's original rule is also implemented in this project.
  
  #### Important Classes
  - RuleGuide
      Trait that defines a rule. A rule has a name, a nextGen method to calculate the next generation, and a info method to explain how it works.
      
  - Board
      The universe where the cells live. Has methods to update itself, and return statistics (the number of living cells). The board wraps around itself, making so that all cells have 8 neighbours.
      
  - Cells
      The pieces that actually have their state changed during the game.
      
  ### GoLApp
  This is the executable project. It implements the GUI, providing a user-friendly environment to explore the game. Using Reflection it can instantiate rules from other projects. As a default it has Conway's rule from GoLStrategy. Extensions may also contribute to the rules' pool of options. 
  It also has a feature to create a rule during execution. The rule is not saved for after the game is finished, but can be played with during a run.
  
  ### GoLExtensions
  Created to demonstrate the framework usage of the project. It implements more rules, using the RuleGuide definition. These rules can be executed in the GoLApp later.
