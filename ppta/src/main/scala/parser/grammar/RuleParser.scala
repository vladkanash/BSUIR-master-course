package parser.grammar

import lab1.{Rule, Word}

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait RuleParser extends RegexParsers {

  val sep = "|"
  val arrow = "=>"
  val emptyWord = "ε"
  val nonEmptyWord: Regex = "[-)(+a-zA-Z]+".r

  protected def rule: Parser[List[Rule]] = (word <~ arrow) ~ right ^^ (res => res._2.map(right => Rule(res._1, right)))

  private def right: Parser[List[Word]] = word ~ rep(sep ~> word) ^^ (e => e._1 :: e._2)

  private def word: Parser[Word] = emptyWord ^^ (_ => Word.empty) | nonEmptyWord ^^ (Word(_))

  def parse(str: CharSequence): List[Rule] = parseAll(rule, str) match {
    case Success(result, _) => result
    case NoSuccess(msg, _) => print(msg); List.empty
  }

  def parse(rules: Iterable[String]): List[Rule] = rules.flatMap(parse(_)).toList
}
