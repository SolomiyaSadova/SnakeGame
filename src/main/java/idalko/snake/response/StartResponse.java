package idalko.snake.response;

import com.idalko.battle_snake.fw.api.domain.start.HeadType;
import com.idalko.battle_snake.fw.api.domain.start.IStartResponse;
import com.idalko.battle_snake.fw.api.domain.start.TailType;
import idalko.snake.config.SnakeConstants;

public class StartResponse implements IStartResponse {

    @Override
    public String getColor() {
        return SnakeConstants.SNAKE_COLOR;
    }

    @Override
    public String getSecondaryColor() {
        return SnakeConstants.SECONDARY_COLOR;
    }

    @Override
    public String getName() {
        return "MyPerfectSnake";
    }

    @Override
    public String getHeadUrl() {
        return "http://battlesnake.exalate.st:3000/9";
    }

    @Override
    public String getTaunt() {
        return "Taunt";
    }

    @Override
    public HeadType getHeadType() {
        return HeadType.BENDR;
    }

    @Override
    public TailType getTailType() {
        return TailType.BLOCK_BUM;
    }
}
