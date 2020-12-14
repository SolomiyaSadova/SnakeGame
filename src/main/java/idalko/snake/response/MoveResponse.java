package idalko.snake.response;

import com.idalko.battle_snake.fw.api.domain.move.IMoveResponse;
import com.idalko.battle_snake.fw.api.domain.move.Move;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveResponse implements IMoveResponse {
    public Move move;

    public MoveResponse(Move move) {
        this.move = move;
    }

    @Override
    public Move getMove() {
        return move;
    }
}
