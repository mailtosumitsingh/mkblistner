package com.sumit.autometa.mkblistner;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MkblistnerApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(MkblistnerApplication.class, args);
	}
	@Bean
	public CommandLineRunner post(GlobalKeyListener kp,GlobalMouseListener mp, ScreenClickCapture screenClickCapture) {
		return (args) -> {
			try {
				GlobalScreen.registerNativeHook();
				GlobalScreen.addNativeKeyListener(kp);
				GlobalScreen.addNativeMouseListener(mp);
				GlobalScreen.addNativeMouseListener(screenClickCapture);
			} catch (NativeHookException e) {
				e.printStackTrace();
			}
		};
	}
}
