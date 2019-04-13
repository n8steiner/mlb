package bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;


@Entity(name = "teamseason")
public class TeamSeason implements Serializable {

  @EmbeddedId
  TeamSeasonId id;
  
  @Embeddable
  static class TeamSeasonId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "teamid", referencedColumnName = "teamid", insertable = false, updatable = false)
		Team team;
		@Column(name="year")
		Integer teamYear;
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof TeamSeasonId)){
				return false;
			}
			TeamSeasonId other = (TeamSeasonId)obj;
			// in order for two different object of this type to be equal,
			// they must be for the same year and for the same player
			return (this.team==other.team &&
					this.teamYear==other.teamYear);
		}
		 
		@Override
		public int hashCode() {
			Integer hash = 0;
			if (this.team != null) hash += this.team.hashCode();
			if (this.teamYear != null) hash += this.teamYear.hashCode();
			return hash;
		}
  }

  //copied from moodle to handle three-way connection
  @ManyToMany(fetch = FetchType.LAZY)

  @JoinTable(name = "teamseasonplayer", 

    joinColumns={
      @JoinColumn(name="teamId", insertable = false, updatable = false), 
      @JoinColumn(name="year",  insertable = false, updatable = false)}, 

    inverseJoinColumns={
      @JoinColumn(name="playerId", insertable = false, updatable = false)})

  Set<Player> players = new HashSet<Player>();



  @Column
  int gamesPlayed;
  @Column
  int wins;
  @Column
  int losses;
  @Column
  int rank;
  @Column
  int totalAttendance;

  //is this necessary?
  public TeamSeason() {}
	
	public TeamSeason(Team t, Integer year) {
		TeamSeasonId tsi = new TeamSeasonId();
		tsi.team = t;
		tsi.teamYear = year;
		this.id = tsi;
  }
  

  //getters, setters, and other utility functions

	public TeamSeasonId getId() {
		return this.id;
  }
  
  public void setYear(Integer year) {
		this.id.teamYear = year;
	}

	public Integer getYear() {
		return this.id.teamYear;
	}

  public void setGamesPlayed(Integer gamesPlayed){
    this.gamesPlayed = gamesPlayed;
  }

  public Integer getGamesPlayed(){
    return this.gamesPlayed;
  }

  public void setWins(Integer wins){
    this.wins = wins;
  }

  public Integer getWins(){
    return this.wins;
  }

  public void setLosses(Integer losses){
    this.losses = losses;
  }

  public Integer getLosses(){
    return this.losses;
  }

  public void setRank(Integer rank){
    this.rank = rank;
  }

  public Integer getRank(){
    return this.rank;
  }

  public void setTotalAttendance(Integer totalAttendance){
    this.totalAttendance = totalAttendance;
  }

  public Integer getTotalAttendance(){
    return this.gamesPlayed;
  }

}