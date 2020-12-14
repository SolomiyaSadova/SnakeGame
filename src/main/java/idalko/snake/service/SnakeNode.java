package idalko.snake.service;

import com.idalko.battle_snake.fw.api.domain.move.IPoint;
import com.idalko.battle_snake.fw.api.domain.move.Move;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SnakeNode {
    private IPoint point;
    private Move move;
}
