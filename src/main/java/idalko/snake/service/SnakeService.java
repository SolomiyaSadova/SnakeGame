package idalko.snake.service;

import com.idalko.battle_snake.fw.api.ai.IAi;
import com.idalko.battle_snake.fw.api.domain.move.IMoveRequest;
import com.idalko.battle_snake.fw.api.domain.move.IMoveResponse;
import com.idalko.battle_snake.fw.api.domain.move.IPoint;
import com.idalko.battle_snake.fw.api.domain.start.IStartRequest;
import com.idalko.battle_snake.fw.api.domain.start.IStartResponse;
import idalko.snake.response.StartResponse;

import javax.annotation.Nonnull;
import java.util.List;

public class SnakeService implements IAi {
    @Nonnull
    public IStartResponse getStartResponse(@Nonnull IStartRequest iStartRequest) {
        return new StartResponse();
    }

    @Nonnull
    public IMoveResponse makeMove(@Nonnull IMoveRequest iMoveRequest) {

        List<IPoint> completeSnake = iMoveRequest.getYou().getBody().getData();
        IPoint start = completeSnake.get(0);
        List<IPoint> food = iMoveRequest.getFood().getData();
        SnakeAI snakeAI = new SnakeAI();

        return snakeAI.move(food.get(0), start);
    }


}
