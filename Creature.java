package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
/**
 * Class representing a Creature.
 * This class is a parent class for the child creature types (eg. Plant,Herbivore).
 * Implements the Drawable interface
 * @author yiðit
 *
 */

public abstract class Creature implements Drawable {
	private int x;
	private int y;
	private double health;
	
	/**
	 * Constructs the creature.
	 * @param x x-coordinate of the creature
	 * @param y y-coordinate of the creature
	 * @param health health property of the creature
	 */
	public Creature(int x, int y, double health) {
		this.x = x;
		this.y = y;
		this.health = health;
	}

	/**
	 * Getter for the x-coordinate of the creature.
	 * @return x-coordinate of the creature.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter for the x-coordinate of the creature.
	 * @param x x-coordinate of the creature.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the y-coordinate of the creature
	 * @return y-coordinate of the creature
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter for the y-coordinate of the creature
	 * @param y y-coordinate of the creature
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for the health property of the creature
	 * @return health property of the creature
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * Setter for the health property of the creature
	 * @param health health property of the creature
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * Returns the chosen action
	 * @param createLocalInformationForCreature provides informations for each creature
	 * @return chosen Action
	 */
	public abstract Action chooseAction(LocalInformation createLocalInformationForCreature) ;

	/**
	 * Attacks another adjacent Creature.
	 * In which case,it will kill and move to its position.
	 * Only herbivores could attack.
	 * @param attackedCreature Creature(Plant) which herbivore attack and kill it.
	 */
	public void attack(Creature attackedCreature) {
		// TODO Auto-generated method stub	
	}
	
	/**
	 *Moves to adjacent empty cell. 
	 * @param direction randomly selected free direction,which this creature will move.
	 */
	public void move(Direction direction) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Creates a new creature of the same type on an adjacent empty space.
	 * @param direction randomly selected free direction,which this creature could reproduce
	 * @return new Creature
	 */
	public abstract Creature reproduce(Direction direction);

	/**
	 * Stay at the same space.
	 */
	public abstract void stay();
	

}