package tictactoe

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomElement
import org.scalajs.dom.document
import japgolly.scalajs.react.vdom.html_<^._


object TicTacToe {

  final case class SquareProps(onClick: Callback, value: Option[Char])

  class SquareBackend(bs: BackendScope[SquareProps, Unit]) {
    def render(props: SquareProps): VdomElement = {
      <.button(
        ^.className := "square",
        ^.onClick --> props.onClick,
        vdomNodeFromString(props.value.map(_.toString).getOrElse(""))
      )
    }
  }

  val Square =
    ScalaComponent.builder[SquareProps]("Square")
      .renderBackend[SquareBackend]
      .build

  case class BoardState(squares: Vector[Option[Char]], nextChar: Char, winner: Option[Char] = None)

  class BoardBackend(bs: BackendScope[Unit, BoardState]) {
    private def handleClick(i: Int) =
      bs.modState { s =>
        if ( s.squares(i).isEmpty && s.winner.isEmpty ) {
          val nextSquares = s.squares.updated(i, Some(s.nextChar))
          val winner = calculateWinners(nextSquares).headOption
          BoardState(
            nextSquares,
            if ( s.nextChar == 'X' ) 'O' else 'X',
            winner,
          )
        } else
          s
      }

    private def renderSquare(s: BoardState, i: Int) =
      Square(SquareProps(handleClick(i), s.squares(i)))

    private def renderRow(s: BoardState, i: Int) =
      <.div(
        ^.className := "board-row",
        this.renderSquare(s, i * 3 + 0),
        this.renderSquare(s, i * 3 + 1),
        this.renderSquare(s, i * 3 + 2)
      )

    def render(s: BoardState): VdomElement =
      <.div(
        <.div(
          ^.className := "status",
          s.winner match {
            case Some(w) => s"$w has won!!!"
            case None => s"Next player is ${s.nextChar}"
          }
        ),
        renderRow(s, 0),
        renderRow(s, 1),
        renderRow(s, 2),
      )

    private val lines = Iterable(
      Iterable(0, 1, 2),
      Iterable(3, 4, 5),
      Iterable(6, 7, 8),
      Iterable(0, 3, 6),
      Iterable(1, 4, 7),
      Iterable(2, 5, 8),
      Iterable(0, 4, 8),
      Iterable(2, 4, 6),
    )

    private def calculateWinners(squares: Seq[Option[Char]]) =
      for {
        line <- lines
        chars = line.flatMap(squares(_)).toList
        distinctChars = chars.distinct
        if (chars.size == 3 && distinctChars.size == 1)
      } yield distinctChars.head

  }

  val Board =
    ScalaComponent.builder[Unit]("Board")
      .initialState(BoardState(Vector.fill(9)(None), 'X'))
      .renderBackend[BoardBackend]
      .build


  val Game =
    ScalaComponent.builder[Unit]("Game")
      .renderStatic(
        <.div(
          ^.className := "game",
          <.div(
            ^.className := "game-board",
            Board()
          ),
          <.div(
            ^.className := "game-info",
            <.div,
            <.ol
          )
        )
      ).build

  def main(args: Array[String]): Unit = Game().renderIntoDOM(document.body)
}