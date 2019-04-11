package bo;

//need persistence includes!

@Entity(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer TeamId;


    //ignoring element collection because we don't have excess fields to pull in

    @OnetoMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.team")
    @Fetch(FetchMode.JOIN)
    Set<TeamSeason> seasons = new HashSet<TeamSeason>();

    @Column
    String name;
    @Column
    String league;
    @Column
    Int yearFounded;
    @Column
    Int yearLast;

 
    //make getters and setters and other utility functions
    public TeamSeason getTeamSeason(Integer year) {
		for (TeamSeason ts : seasons) {
			if (ts.getYear().equals(year)) return ts;
		}
		return null;
    }
    
    public void addSeason(TeamSeason s) {
		seasons.add(s);
	}

	public Set<TeamSeason> getSeasons() {
		return seasons;
    }
    
    public void setSeasons(Set<TeamSeason> seasons) {
		this.seasons = seasons;
	}
	
	public Integer getId() {
		return TeamId;
    }
    
    //do we want this to happen?
	public void setId(Integer id) {
		this.TeamId = id;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
    }
    
    public Int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
    }
    
    public Int getYearLast() {
		return yearLast;
	}

	public void setYearLast(int yearLast) {
		this.yearLast = yearLast;
    }
    
    //overides necessary?
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Team)){
            return false;
        }
        Team other = (Team) obj;
        return (this.getName().equalsIgnoreCase(other.getName());
    }

    @Override
	public int hashCode() {
		Integer hash = 0;
		if (this.getName()!=null) hash += this.getName().hashCode(); 
		return hash;
	}

}