package entity;

public interface ILevelable {
	public int getLevel();
	public int getExperience();
	public int getExperienceReq();
	public void setLevel(int level);
	public void setExperience(int experience);
	public void addExperience(int experience);
	public void levelUp();
}
