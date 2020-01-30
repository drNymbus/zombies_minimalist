package model.item;

import draw.Sprite;
import model.util.*;

import javafx.scene.layout.Pane;

public class Prop extends Sprite {
	private int id;

	public Prop(Pane layer, double x, double y, int id, PropType pt) {
		super(layer, pt.getColor(), x, y, pt.getWidth(), pt.getHeight());
		this.id = id;
	}
}
