package scalapoc
import scala.meta._

object Scalameta {

  def process(tree: scala.meta.Tree): Unit = {
    tree.children.foreach(t => process(t))


    // use pattern matching and quasiquotes to process trees
    tree match {
      case q"if ($cond) $thenClause else $elseClause" => {
        println(s"if detected\nthen: ${thenClause.structure} \nelse: ${elseClause.structure} ")
      }
      // for leaf nodes print token info
      case t if t.children.isEmpty => println(s"Leaf: ${t.structure} ${t.tokens.structure}")
      case _ => // ignore
    }
  }

  def analyze(code: String): Unit = {
    val ast = code.parse[Source] match {
      case scala.meta.parsers.Parsed.Success(tree) => tree
      // detect parse error
      case scala.meta.parsers.Parsed.Error(e, _, _) => {
        throw new RuntimeException(e.toString)
      }
    }

    process(ast)
  }

}
