package com.dimo.games.battleships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dimo.games.battleships.player.Game;
import com.dimo.games.battleships.service.impl.BattlefieldServiceImpl;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext ac = SpringApplication.run(App.class, args);
        Game game = new Game(ac.getBean(BattlefieldServiceImpl.class));
        game.play();
    }
}
