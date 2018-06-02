package project;

import java.awt.Color;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * Class representing a Plant
 * This class is the child class of the Creature class
 * @author yiðit
 *
 */
public class Plant extends Creature {
	
	/**
	 * freeDirections : direction list (empty cells),which plant could reproduce or move.
	 * directionToMove : is a free direction,which is randomly selected by getRandomDirection method.
	 * MAX_HEALTH : is the max health. it should be 1.0.it can not change. 
	 */
	private static final double MAX_HEALTH=1.0; 
	List <Direction> freeDirections;
	Direction directionToMove;
	
	/**
	 * Constructs the Plant with initial health equal to 0.5
	 * @param x x-coordinate of the plant
	 * @param y y-coordinate of the plant
	 */
	public Plant(int x, int y) {
		super(x, y, MAX_HEALTH/2);
	}
	
	/**
	 * Constructs the Plant
	 * @param x x-coordinate of the plant
	 * @param y y-coordinate of the plant
	 * @param health health property of the plant
	 */
	public Plant(int x, int y,double health) {
		super(x, y, health);
		
	}
	
	
	@Override
	/**
	 * Draws the object on the grid panel (with Green color for Plants)
	 * @param panel grid panel to draw on
	 */
	public void draw(GridPanel panel) {
		panel.drawSquare(this.getX(), this.getY(), Color.GREEN);
		
	}

	
	/**
	 * Chooses the action. 
	 * if Plant health is larger than or equal to 0.75, and there is an empty cell (direction) around it will reproduce 
	 * else if plant's health is not enough to reproduce or there is no space around, it should stay.
	 */
	@Override
	public Action chooseAction(LocalInformation createLocalInformationForCreature) {
		freeDirections=createLocalInformationForCreature.getFreeDirections();
		directionToMove =LocalInformation.getRandomDirection(freeDirections);

		//Reproduce
		if(this.getHealth()>=0.75 &&  !freeDirections.isEmpty() ) {
			return new Action(Action.Type.REPRODUCE,directionToMove);

		}

		//Stay
		else {
			return new Action(Action.Type.STAY);
		}
	}
	
	
	/**
	 * Stays at the same position.
	 * This action increases the Plant's health by 0.05. Plant max health should be 1.00.
	 */
	@Override
	public void stay() {
		if(this.getHealth()<=0.95)  {
			this.setHealth(this.getHealth() + 0.05);
		}
		else {
			this.setHealth(MAX_HEALTH);
		}
	}
	
	
	
	/**
	 * Reproduces the plant.
	 * The newly plant have 10% of its parents health.The parent's health reduced to 70% of its original health.
	 */
	@Override
	public Creature reproduce(Direction direction) {
		double parent_health=this.getHealth();
		double child_health=0.1*parent_health;
		this.setHealth(parent_health*0.7);
		
		if(direction==Direction.UP) {
			return new Plant(this.getX(),this.getY()-1,child_health) ;
		}
		else if(direction==Direction.DOWN) {
			return new Plant(this.getX(),this.getY()+1,child_health) ;
			
		}
		else if(direction==Direction.RIGHT) {
			return new Plant(this.getX()+1,this.getY(),child_health) ;
			
		}
		else {
			return new Plant(this.getX()-1,this.getY(),child_health) ;
		}
				
	}


}
