package com.sumit.autometa.mkblistner;

import java.util.UUID;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalKeyListener implements NativeKeyListener {
	@Autowired
	EventSender eventSender;
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		//System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		Point p = new Point();
		p.x = -100;
		p.y = -100;
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "kbd");
		p.getData().put("keycode", e.getKeyCode());
		p.getData().put("when", e.getWhen());
		p.getData().put("char", e.getKeyChar());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "pressed");
		eventSender.sendEvent(p);
	}




	public void nativeKeyReleased(NativeKeyEvent e) {
		//System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		Point p = new Point();
		p.x = -100;
		p.y = -100;
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "kbd");
		p.getData().put("keycode", e.getKeyCode());
		p.getData().put("when", e.getWhen());
		p.getData().put("char", e.getKeyChar());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "released");
		eventSender.sendEvent(p);
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
		Point p = new Point();
		p.x = -100;
		p.y = -100;
		p.setId(UUID.randomUUID().toString());
		p.getData().put("etype", "kbd");
		p.getData().put("keycode", e.getKeyCode());
		p.getData().put("when", e.getWhen());
		p.getData().put("char", e.getKeyChar());
		p.getData().put("modifier", e.getModifiers());
		p.getData().put("type", "typed");
		eventSender.sendEvent(p);
	}

	
}