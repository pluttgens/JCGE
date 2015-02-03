package com.gamefactory.displayable;

public class GameObjectTest extends GameObject{

	public GameObjectTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		componentManager.init("Position","InputHandler");
	}

}
