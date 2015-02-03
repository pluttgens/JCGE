package com.gamefactory.displayable;

public class GameObjectTest extends GameObject{

	public GameObjectTest() {
		super();
		
	}

	@Override
	public void init() {
            componentManager.init("Position","InputHandler","Renderer");
	}

}
