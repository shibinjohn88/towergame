package towergame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int resources;
	private List <TowerLocation> towers = new ArrayList<TowerLocation>();
	
	public Player(int resources) {
		super();
		this.resources = resources;
	}
	public int getResources() {
		return resources;
	}

	public void setResources(int resources) {
		this.resources += resources;
	}
	public class TowerLocation {
		private Tower tower;
		private int position;
		
		public TowerLocation(Tower tower, int position) {
			super();
			this.tower = tower;
			this.position = position;
		}
		
		public Tower getTower() {
			return tower;
		}

		public int getPosition() {
			return this.position;
		}
	}
	
	public void buildTower(Tower tower, int position) {
		if (this.resources >= tower.getCost()) {
			this.resources -= tower.getCost();
			towers.add(new TowerLocation(tower, position));
			System.out.println("Built tower at position " + position);
		}
		else {
			System.out.println("Not enough resources to built tower");
		}
	}

	public List<TowerLocation> getTowers() {
		return towers;
	}

}
