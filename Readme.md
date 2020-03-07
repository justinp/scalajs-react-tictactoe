What is this?
------

This is a port of the [React Tutorial](https://reactjs.org/tutorial/tutorial.html) that I did using
[scalajs-react](https://github.com/japgolly/scalajs-react). I found a lot of older tutorials and
things pretty tightly integrated with play. I just wanted a simple example of a simple SPA using
scalajs-react.

I'm putting it up here in case it's useful to anyone else.

To run it type `sbt fullOptJS::webpack` and then open `src/main/resources/tictactoe.html`

References
----------

These are some references that I used while figuring out how to get this running:
 * https://github.com/japgolly/scalajs-react/blob/master/doc/USAGE.md
 * https://reactjs.org/tutorial/tutorial.html
 * https://scalacenter.github.io/scalajs-bundler/
 * https://ochrons.github.io/scalajs-spa-tutorial/en/
 * http://www.lihaoyi.com/hands-on-scala-js/
 
All the information is in there, it's just a bit spread around for exactly what I was trying to do -- create a
simple SPA written in scalajs that doesn't necessarily cohabit with its backend code.
