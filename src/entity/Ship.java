package entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;

/**
 * Implements a ship, to be controlled by the player.
 *
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 *
 */
public class Ship extends Entity {

	/** Time between shots. */
	private static final int SHOOTING_INTERVAL = 750;
	/** Speed of the bullets shot by the ship. */
	private static final int BULLET_SPEED = -6;
	/** Movement of the ship for each unit of time. */
	private static final int SPEED = 2;

	/** Minimum time between shots. */
	private Cooldown shootingCooldown;
	/** Time spent inactive between hits. */

	private ArrayList<Cooldown> destructionCooldown;
	private ArrayList<SpriteType> destructionSpriteType;

	/**
	 * Constructor, establishes the ship's properties.
	 *
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 */
	public Ship(final int positionX, final int positionY) {
		super(positionX, positionY, 13 * 2, 8 * 2, Color.GREEN);

		this.spriteType = SpriteType.Ship;
		this.shootingCooldown = Core.getCooldown(SHOOTING_INTERVAL);
		this.destructionCooldown = new ArrayList<>();
		this.destructionSpriteType = new ArrayList<>();
		destructionCount(10);
		destructionGraphic(SpriteType.ShipDestroyedLeft, SpriteType.ShipDestroyedRight);
	}

	public final void destructionCount(int count) {
		int term = 1000 / count;
		Cooldown Cd;
		for (int i = 1; i < count; ++i) {
			Cd = Core.getCooldown(term * i);
			destructionCooldown.add(Cd);
		}
		Cd = Core.getCooldown(1000);
		destructionCooldown.add(Cd);
	}

	public final void destructionGraphic(SpriteType st1, SpriteType st2) {
		destructionSpriteType.add(st1);
		destructionSpriteType.add(st2);
	}

	/**
	 * Moves the ship speed uni ts right, or until the right screen border is
	 * reached.
	 */
	public final void moveRight() {
		this.positionX += SPEED;
	}

	/**
	 * Moves the ship speed units left, or until the left screen border is
	 * reached.
	 */
	public final void moveLeft() {
		this.positionX -= SPEED;
	}

	/**
	 * Shoots a bullet upwards.
	 *
	 * @param bullets
	 *            List of bullets on screen, to add the new bullet.
	 * @return Checks if the bullet was shot correctly.
	 */
	public final boolean shoot(final Set<Bullet> bullets) {
		if (this.shootingCooldown.checkFinished()) {
			this.shootingCooldown.reset();
			bullets.add(BulletPool.getBullet(positionX + this.width / 2,
				positionY, BULLET_SPEED));
			return true;
		}
		return false;
	}

	/**
	 * Updates status of the ship.
	 */
	public final void update() {
		if (!this.destructionCooldown.get(0).checkFinished()) {
			this.spriteType = destructionSpriteType.get(0);
		} else if (!this.destructionCooldown.get(1).checkFinished()) {
			this.spriteType = destructionSpriteType.get(1);
		} else if (!this.destructionCooldown.get(2).checkFinished()) {
			this.spriteType = destructionSpriteType.get(0);
		} else if (!this.destructionCooldown.get(3).checkFinished()) {
			this.spriteType = destructionSpriteType.get(1);
		} else if (!this.destructionCooldown.get(4).checkFinished()) {
			this.spriteType = destructionSpriteType.get(0);
		} else if (!this.destructionCooldown.get(5).checkFinished()) {
			this.spriteType = destructionSpriteType.get(1);
		} else if (!this.destructionCooldown.get(6).checkFinished()) {
			this.spriteType = destructionSpriteType.get(0);
		} else if (!this.destructionCooldown.get(7).checkFinished()) {
			this.spriteType = destructionSpriteType.get(1);
		} else if (!this.destructionCooldown.get(8).checkFinished()) {
			this.spriteType = destructionSpriteType.get(0);
		} else if (!this.destructionCooldown.get(9).checkFinished()) {
			this.spriteType = destructionSpriteType.get(1);
		} else
			this.spriteType = SpriteType.Ship;
	}

	/**
	 * Switches the ship to its destroyed state.
	 */
	public final void destroy() {
		for (int i = 0; i < destructionCooldown.size(); ++i) {
			this.destructionCooldown.get(i).reset();
		}
	}

	/**
	 * Checks if the ship is destroyed.
	 *
	 * @return True if the ship is currently destroyed.
	 */
	public final boolean isDestroyed() {
		return !this.destructionCooldown.get(destructionCooldown.size() - 1).checkFinished();
	}

	/**
	 * Getter for the ship's speed.
	 *
	 * @return Speed of the ship.
	 */
	public final int getSpeed() {
		return SPEED;
	}
}