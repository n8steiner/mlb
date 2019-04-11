package bo;







//copied from moodle to handle three-way connection
@ManyToMany(fetch = FetchType.LAZY)

@JoinTable(name = "teamseasonplayer", 

   joinColumns={
     @JoinColumn(name="teamId", insertable = false, updatable = false), 
     @JoinColumn(name="year",  insertable = false, updatable = false)}, 

   inverseJoinColumns={
     @JoinColumn(name="playerId", insertable = false, updatable = false)})

Set<Player> players = new HashSet<Player>();