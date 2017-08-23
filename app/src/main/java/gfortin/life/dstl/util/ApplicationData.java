package gfortin.life.dstl.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.CharacterItemJunction;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.ItemItemJunction;
import gfortin.life.dstl.model.Location;
import gfortin.life.dstl.model.Type;

public class ApplicationData {

	private static final Class<?>[] modelClasses = new Class[] {
			Game.class,
			Type.class,
			Item.class,
			Character.class,
			Location.class,
			ItemItemJunction.class,
			CharacterItemJunction.class,
			ItemProperty.class,
			ItemPropertyJunction.class,

	};

	/**
	 * @return the modelclasses
	 */
	public static Class<?>[] getModelclasses() {
		return modelClasses;
	}

}
