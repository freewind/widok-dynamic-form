package in.freewind

import org.widok._
import org.widok.html._

object Main extends PageApplication {

  val teams = new Var[Seq[Team]](Seq(
    Team("team 1", Seq(Member("member1"), Member("member2"), Member("member3"))),
    Team("team 2", Seq(Member("member1"), Member("member2"), Member("member3"))),
    Team("team 3", Seq(Member("member1"), Member("member2"), Member("member3")))
  ))

  def view() = span(
    h1("All teams"),
    teams.map(_.map(team => div(
      div(
        team.name,
        button("+ new member").onClick { _ =>
          // how to show this form ???
          new CreateForm().apply(team)
        }
      ),
      div(team.members.map(_.name).mkString(","))
    ))).map(div(_))
  )

  class CreateForm {
    private val memberName = new Var[String]("")

    private def createMember(team: Team, newMemberName: String): Unit = {
      teams := teams.get.map {
        case t if t == team => t.copy(members = t.members :+ Member(newMemberName))
        case other => other
      }
    }

    def apply(team: Team) = div(
      div(text().placeholder("Member name").bind(memberName)),
      div(
        button("close"),
        button("OK").onClick(_ => createMember(team, memberName.get))
      )
    ).css("popup")

  }

  def ready() {
    log("Page loaded.")
  }
}

case class Team(name: String, members: Seq[Member])
case class Member(name: String)
