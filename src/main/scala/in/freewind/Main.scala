package in.freewind

import org.widok._
import org.widok.html._

object Main extends PageApplication {

  val teams = Buffer(
    Team("team 1", Seq(Member("member1"), Member("member2"), Member("member3"))),
    Team("team 2", Seq(Member("member1"), Member("member2"), Member("member3"))),
    Team("team 3", Seq(Member("member1"), Member("member2"), Member("member3")))
  )

  def view() = span(
    h1("All teams"),
    teams.map { team =>
      val showForm = Var(false)
      div(
        div(
          team.name,
          button("+ new member").onClick(_ => showForm := true),
          new CreateForm().apply(team, showForm).show(showForm)
        ),
        div(team.members.map(_.name).mkString(","))
      )
    }
  )

  class CreateForm {
    private val memberName = new Var[String]("")

    private def createMember(team: Team, newMemberName: String): Unit = {
      teams.replace(team, team.copy(members = team.members :+ Member(newMemberName)))
    }

    def apply(team: Team, showTeam: Var[Boolean]) = div(
      div(text().placeholder("Member name").bind(memberName)),
      div(
        button("close").onClick(_ => showTeam := false),
        button("OK").onClick { _ => createMember(team, memberName.get); showTeam := false }
      )
    ).css("popup")

  }

  def ready() {
    log("Page loaded.")
  }
}

case class Team(name: String, members: Seq[Member])
case class Member(name: String)
