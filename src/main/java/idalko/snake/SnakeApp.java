package idalko.snake;

import com.idalko.battle_snake.fw.api.server.SnakeAiServer;
import idalko.snake.service.SnakeService;

public class SnakeApp {
    public static void main(String[] args) {
        SnakeService snakeService = new SnakeService();
        SnakeAiServer.start(snakeService);
    }
}
