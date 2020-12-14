package idalko.snake.service;

import com.idalko.battle_snake.fw.api.domain.move.IPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Snake {
   private IPoint destination;
   private boolean isDestinationSettled;
}
