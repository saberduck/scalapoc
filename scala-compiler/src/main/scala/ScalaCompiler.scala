import scala.reflect.runtime.universe._
import scala.tools.reflect.ToolBox

object ScalaCompiler extends App {
  val tb = runtimeMirror(getClass.getClassLoader).mkToolBox()
  var tree: Tree = tb.parse("private object Hello extends App { print (x + y) }")
  /*
  ModuleDef(
    Modifiers(PRIVATE),
    TermName("Hello"),
    Template(
      List(Ident(TypeName("App"))),
      noSelfType,
      List(
        DefDef(
          Modifiers(),
          termNames.CONSTRUCTOR,
          List(),
          List(List()),
          TypeTree(),
          Block(
            List(pendingSuperCall),
            Literal(Constant(())))),
        Apply(
          Ident(TermName("print")), List(Apply(Select(Ident(TermName("x")), TermName("$plus")), List(Ident(TermName("y")))))))))
   */
  println(showRaw(tree))
  // It seems that tree.pos only tells us where a Tree starts, not where it ends
  println("pos.line: " + tree.pos.line + "; pos.column=" + tree.pos.column + "; pos.isRange=" + tree.pos.isRange)
  println(tree.asInstanceOf[ModuleDef].mods)
  // TODO tokens?
  // TODO comments?
  println(showRaw(tb.parse("x = /* hello */ 1")))
  println(showRaw(tb.parse("def main() { println( \"hello\") }")))




}
