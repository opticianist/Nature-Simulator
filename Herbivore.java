package project;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * Class representing a Herbivore.
 * This class is the child class of the Creature class.
 * @author yiðit
 *
 */
public class Herbivore extends Creature{
	/**
	 * freeDirections : direction list (empty cells),which herbivore could reproduce or move.
	 * directionToMove : is a free direction,which is randomly selected by getRandomDirection method.
	 * directionToAttack : is a direction,where there is a plant at this cell. Also this direction is randomly selected by getRandomDirection method.
	 * MAX_HEALTH : is the max health. it should be 20.0.it can not change. 
	 */
	
	private static final double MAX_HEALTH=20.0; 
	Direction directionToMove;
	List <Direction> freeDirections;
	Direction directionToAttack;

	/**
	 * Constructs the Herbivore with initial health equal to 10.0.
	 * @param x x-coordinate of the Herbivore.
	 * @param y y-coordinate of the Herbivore.
	 */
	public Herbivore(int x, int y) {
		super(x, y, MAX_HEALTH/2);

	}

	/**
	 * Constructs the Herbivore.
	 * @param x x-coordinate of the Herbivore.
	 * @param y y-coordinate of the Herbivore.
	 * @param health health property of the Herbivore.
	 */
	public Herbivore(int x, int y, double health) {
		super(x, y, health);
	}

	/**
	 * Draws the object on the grid panel (with Red color for Herbivore).
	 * @param panel grid panel to draw on
	 */
	@Override
	public void draw(GridPanel panel) {
		panel.drawSquare(this.getX(), this.getY(), Color.RED);

	}

	/**
	 * Chooses the action.
	 * if Herbivore's health is equal to its maximum health and there is an empty cell(direction) around,it should reproduce.
	 * else if reproduction is not possible and if there is any Plant around,it should attack.
	 * else if there is no Plant around the Herbivore, so attacking is not possible.So it should move to one of the empty cell around.
	 * else Herbivore should choose to stay if no other action is possible due to above-mentioned rules.
	 */
	@Override
	public Action chooseAction(LocalInformation createLocalInformationForCreature) {

		freeDirections=createLocalInformationForCreature.getFreeDirections();
		directionToMove =LocalInformation.getRandomDirection(freeDirections);

		//Check if there is any Plant around.
		boolean left=createLocalInformationForCreature.getCreatureLeft() instanceof Plant;
		boolean right=createLocalInformationForCreature.getCreatureRight() instanceof Plant;
		boolean up=createLocalInformationForCreature.getCreatureUp() instanceof Plant;
		boolean down=createLocalInformationForCreature.getCreatureDown() instanceof Plant;

		//if there is any Plant at next cell, add the Direction of the plant to filledDirection list. 
		ArrayList <Direction> filledDirection =new ArrayList <Direction> ();
		if(up) {
			filledDirection.add(Direction.UP);
		}
		if(down) {
			filledDirection.add(Direction.DOWN);
		}
		if(right) {
			filledDirection.add(Direction.RIGHT);
		}
		if(left) {
			filledDirection.add(Direction.LEFT);
		}

		//Use getRandomDirection method of LocalInformation class,to get a randomly selected direction among multiple directions.
		directionToAttack =LocalInformation.getRandomDirection(filledDirection);

		//Now Herbivore should choose the action type : Reproduce,Attack,Move,Stay

		//Reproduce
		if(this.getHealth()==MAX_HEALTH && ( ! freeDirections.isEmpty() ) ) {

			return new Action(Action.Type.REPRODUCE,directionToMove);

		}

		//Attack
		else if( directionToAttack!=null) {

			return new Action(Action.Type.ATTACK, directionToAttack);
		}


		//Move
		else if(!freeDirections.isEmpty() && this.getHealth()>1.0 ){

			return new Action(Action.Type.MOVE, directionToMove);
		}

		//Stay
		else {

			return new Action(Action.Type.STAY);
		}

	}


	/**
	 * Reproduces the Herbivore.
	 * The newly plant (child) have 20% of its parents health.The parents health reduced to 40% of its original health.
	 */
	@Override
	public Creature reproduce(Direction direction) {
		double parent_health=this.getHealth();
		double child_health=0.2*parent_health;
		this.setHealth(parent_health*0.4);	

		if(direction==Direction.UP) {
			return new Herbivore(this.getX(),this.getY()-1,child_health) ;
		}
		else if(direction==Direction.DOWN) {
			return new Herbivore(this.getX(),this.getY()+1,child_health) ;

		}
		else if(direction==Direction.RIGHT) {
			return new Herbivore(this.getX()+1,this.getY(),child_health) ;

		}
		else {
			return new Herbivore(this.getX()-1,this.getY(),child_health) ;
		}



	}

	/**
	 * Attacks to the Plant.
	 * Moves to the position(x,y) of the attackedCreature
	 * The newly plant (child) have 20% of its parents health.The parents health reduced to 40% of its original health.
	 */
	@Override
	public void attack(Creature attackedCreature) {

		double plant_health=attackedCreature.getHealth();
		int posX=attackedCreature.getX();
		int posY=attackedCreature.getY();

		double herb_health=this.getHealth()+plant_health;
		attackedCreature.setHealth(0.0);

		this.setX(posX);
		this.setY(posY);
		if(herb_health<= MAX_HEALTH) {
			this.setHealth(herb_health);
		}
		else {
			this.setHealth(MAX_HEALTH);
		}
	}


	/**
	 * Moves the herbivore
	 * This action causes the Herbivore to lose 1.0 health.
	 */
	@Override
	public void move(Direction direction) {

		this.setHealth(this.getHealth() -1.0);
		if(direction==Direction.UP) {
			this.setY(this.getY()-1);
		}
		else if(direction==Direction.DOWN) {
			this.setY(this.getY()+1);
		}
		else if(direction==Direction.RIGHT) {
			this.setX(this.getX()+1);
		}
		else {
			this.setX(this.getX()-1);
		}
	}



	/**
	 * Stays at the same position.
	 * This action causes the Herbivore to lose 0.1 health.
	 */
	@Override
	public void stay() {
		this.setHealth(this.getHealth()- 0.1);
	}

}
