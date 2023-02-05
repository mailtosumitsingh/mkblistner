package com.sumit.autometa.mkblistner;

import java.util.UUID;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalMouseListener implements NativeMouseInputListener {
	@Autowired
	EventSender eventSender;
	boolean sendMousePressed = false;
	boolean sendMouseReleased = false;
	public void nativeMouseClicked(NativeMouseEvent e) {
		Point p = new Point();
		p.x = e.getX();
		p.y = e.getY();
	
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "mouse");
		p.getData().put("clickcount", e.getClickCount());
		p.getData().put("when", e.getWhen());
		p.getData().put("button", e.getButton());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "clicked");
		eventSender.sendEvent(p);	
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		Point p = new Point();
		p.x = e.getX();
		p.y = e.getY();
	
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "mouse");
		p.getData().put("clickcount", e.getClickCount());
		p.getData().put("when", e.getWhen());
		p.getData().put("button", e.getButton());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "pressed");
		if(sendMousePressed)
		eventSender.sendEvent(p);
		}

	public void nativeMouseReleased(NativeMouseEvent e) {
		Point p = new Point();
		p.x = e.getX();
		p.y = e.getY();
	
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "mouse");
		p.getData().put("clickcount", e.getClickCount());
		p.getData().put("when", e.getWhen());
		p.getData().put("button", e.getButton());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "released");
		if(sendMouseReleased)
		eventSender.sendEvent(p);
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		/*
		 * Point p = new Point(); p.x = e.getX(); p.y = e.getY();
		 * 
		 * p.setId(UUID.randomUUID().toString()); p.getData().put("etype", "mouse");
		 * p.getData().put("clickcount", e.getClickCount()); p.getData().put("when",
		 * e.getWhen()); p.getData().put("button", e.getButton());
		 * p.getData().put("modifier", e.getModifiers()); p.getData().put("type",
		 * "moved"); eventSender.sendEvent(p);
		 */	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		/*
		 * Point p = new Point(); p.x = e.getX(); p.y = e.getY();
		 * 
		 * p.setId(UUID.randomUUID().toString()); p.getData().put("etype", "mouse");
		 * p.getData().put("clickcount", e.getClickCount()); p.getData().put("when",
		 * e.getWhen()); p.getData().put("button", e.getButton());
		 * p.getData().put("modifier", e.getModifiers()); p.getData().put("type",
		 * "dragged"); eventSender.sendEvent(p);
		 */	}


}
