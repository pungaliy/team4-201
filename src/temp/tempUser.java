package temp;

public class tempUser {
	private String name;
	private String email;
	private tempRoom room;

	public String getName(){
		return this.name;
	}

	public String getEmail(){
		return this.email;
	}

	public tempRoom getRoom(){
		return this.room;
	}

	public int getRoomID(){
		return this.room.getRoomID();
	}
}
