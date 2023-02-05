package com.sumit.autometa.mkblistner;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.kafka.common.metrics.Stat;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.sikuli.script.Image;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenClickCapture implements NativeMouseInputListener {
	@Autowired
	EventSender eventSender;

	public void nativeMouseClicked(NativeMouseEvent e) {
		System.out.println("screens: "+Screen.getNumberScreens());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		System.out.println("Ww: "+width+" Hh: "+height);
		
		int x , y, w,h;
		int xoff= 20;
		int yoff=20;
		double screenW=width, screenH=height;

		screenW = 1920;
		screenH = 1080;
		if(e.getX()-xoff < 0) {
			x = 0;
		}else {
			x = e.getX() - xoff;
		}
		if(e.getY()-yoff < 0) {
			y = 0;
		}else {
			y = e.getY()-yoff;
		}
		System.out.println("w: "+screenW+" h: "+screenH);
		if(e.getX()+xoff > screenW) {
			w = (int) (screenW-e.getX());
		}else {
			w = xoff*2;
		}
		if(e.getY()+yoff > screenH) {
			h = (int) (screenH-e.getY());
		}else {
			h = yoff*2;
		}
		Region r = new Region(0,0,width,height);
		Image img = r.getImage();
		System.out.println("x: "+x+" y: "+y+" w: "+w +" h: "+h);
		Image img2 = img.getSub(x,y,w,h);
		Point p = new Point();
		p.x = e.getX();
		p.y = e.getY();
	
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "click");
		p.getData().put("clickcount", e.getClickCount());
		p.getData().put("when", e.getWhen());
		p.getData().put("button", e.getButton());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "clicked");
		p.getData().put("screenWidth", width);
		p.getData().put("screenHeight", height);

		p.getData().put("mrx", x);
		p.getData().put("mry", y);
		p.getData().put("mrw", w);
		p.getData().put("mrh", h);


		//p.getData().put("text", img2.text());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(img.get(), "png", baos);
			byte[] bytes = baos.toByteArray();
			String data = Base64.getEncoder().encodeToString(bytes);
			p.getData().put("img", data);
			//StaticUtil.saveImage(img2, StaticUtil.getTempDir()+p.getId()+".jpg");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		eventSender.sendEvent(p);
		
			
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		}

	public void nativeMouseReleased(NativeMouseEvent e) {
		}

	public void nativeMouseMoved(NativeMouseEvent e) {
		}

	public void nativeMouseDragged(NativeMouseEvent e) {
		}


}
