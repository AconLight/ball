package com.redartedgames.ball.colors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class ColorGenerator {
	private float alfa, white, black;
	public Color color;
	
	public ColorGenerator(float alfa) {
		this.alfa = alfa;
		color = new Color(0, 0, 0, 1);
		updateColor(alfa);
	}
	public ColorGenerator(ColorGenerator cg) {
		this.alfa = cg.alfa;
		color = new Color(0, 0, 0, 1);
		updateColor(alfa);
	}
	
	private void updateColor(float alfa) {
		color.r = (float) (1f*(Math.sin(alfa)+0.5f));
		color.g = (float) (1f*(Math.sin(alfa + 2*Math.PI/3)+0.5f));
		color.b = (float) (1f*(Math.sin(alfa + 4*Math.PI/3)+0.5f));
		if (color.r > 1) color.r = 1;
		if (color.g > 1) color.g = 1;
		if (color.b > 1) color.b = 1;
		if (color.r < 0) color.r = 0;
		if (color.g < 0) color.g = 0;
		if (color.b < 0) color.b = 0;
		//color.r = color.r*color.r;
		//color.g = color.g*color.g;
		//color.b = color.b*color.b;
	}
	
	public Color generateNextColor(float deltaWhite, float deltaBlack, float deltaAlfa) {
		alfa += deltaAlfa;
		white += deltaWhite;
		black += deltaBlack;
		updateColor(alfa);
		color.r += (1-color.r)*white;
		color.g += (1-color.g)*white;
		color.b += (1-color.b)*white;
		color.r *= (1-black);
		color.g *= (1-black);
		color.b *= (1-black);

		return new Color(color.r, color.g, color.b, 1);
	}
	public ColorGenerator generateNextColorGenerator(float deltaWhite, float deltaBlack, float deltaAlfa) {
		generateNextColor(deltaWhite, deltaBlack, deltaAlfa);
		return new ColorGenerator(this);
	}
	
	
	public static Vector3 hsvToRgb(float hue, float sat, float val) {
		float red = 0, grn = 0, blu = 0;
		if(val==0) {red=0; grn=0; blu=0;}
		else{
		 hue/=60;
		 float i = (float) Math.floor(hue);
		 float f = hue-i;
		 float p = val*(1-sat);
		 float q = val*(1-(sat*f));
		 float t = val*(1-(sat*(1-f)));
		 if (i==0) {red=val; grn=t; blu=p;}
		 else if (i==1) {red=q; grn=val; blu=p;}
		 else if (i==2) {red=p; grn=val; blu=t;}
		 else if (i==3) {red=p; grn=q; blu=val;}
		 else if (i==4) {red=t; grn=p; blu=val;}
		 else if (i==5) {red=val; grn=p; blu=q;}
		}
		
		return new Vector3(red, grn, blu);
	}
	
	
}
