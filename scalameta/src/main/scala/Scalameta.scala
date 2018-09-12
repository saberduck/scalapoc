
import scala.meta._

object Run {
  def main(args: Array[String]): Unit = {
    val ast = new java.io.File("sample.scala").parse[Source]
    print(ast.get.structure)
  }
}
