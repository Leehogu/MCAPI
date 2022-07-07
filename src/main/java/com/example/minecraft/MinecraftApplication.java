package com.example.minecraft;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;


//@InitializationHint(types = ColorConverter.class, initTime = InitializationTime.BUILD)
@TypeHint(types = Level.class, typeNames = "ch.qos.logback.classic.Level")
@TypeHint(types = LevelFilter.class, typeNames = "ch.qos.logback.classic.filter.LevelFilter")
@TypeHint(types = FilterReply.class, typeNames = "ch.qos.logback.core.spi.FilterReply")
@SpringBootApplication
public class MinecraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinecraftApplication.class, args);
	}

}
