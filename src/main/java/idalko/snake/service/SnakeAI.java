package idalko.snake.service;

import com.idalko.battle_snake.fw.api.domain.move.IPoint;
import com.idalko.battle_snake.fw.api.domain.move.Move;
import com.idalko.battle_snake.fw.api.domain.move.Point;
import idalko.snake.config.SnakeConstants;
import idalko.snake.response.MoveResponse;

import java.util.ArrayList;
import java.util.List;

public class SnakeAI {
    public List<Point> completeSnake;
    public SnakeNode previousNode;
    public Move previousMove;

    public MoveResponse move(IPoint destination, IPoint start) {

        List<SnakeNode> allowedNeighbor = fetchMyNeighbor(start);
        allowedNeighbor.remove(previousNode);
        SnakeNode node = getTheClosestNode(allowedNeighbor, destination);
        //  System.out.println("Previous node " + previousNode.getX() + " " + previousNode.getY());
        previousNode = node;
        // System.out.println("New node " + previousNode.getX() + " " + previousNode.getY());
        return new MoveResponse(node.getMove());
    }


    public List<SnakeNode> fetchMyNeighbor(IPoint point) {
        List<SnakeNode> list = new ArrayList<>();
        int widthCoordinate = point.getX();
        int heightCoordinate = point.getY();

        SnakeNode up = new SnakeNode(new Point(widthCoordinate, heightCoordinate - 1, "point"), Move.UP);
        SnakeNode down = new SnakeNode(new Point(widthCoordinate, heightCoordinate + 1, "point"), Move.DOWN);
        SnakeNode right = new SnakeNode(new Point(widthCoordinate + 1, heightCoordinate, "point"), Move.RIGHT);
        SnakeNode left = new SnakeNode(new Point(widthCoordinate - 1, heightCoordinate, "point"), Move.LEFT);

        list.add(up);
        list.add(down);
        list.add(right);
        list.add(left);
        return list;
    }

    public int manhattanDis(IPoint source, IPoint destination) {
        int xAbs = Math.abs(source.getX() - destination.getX());
        int yAbs = Math.abs(source.getY() - destination.getY());
        return xAbs + yAbs;
    }

//    public List<SnakeNode> convertPointsToNode(List<IPoint> points) {
//        List<SnakeNode> snakeNodes = new LinkedList<>();
//        for (int i = 0; i < points.size(); i++) {
//            snakeNodes.add(new SnakeNode(points.get(0).getX(), points.get(0).getY()));
//        }
//
//        return snakeNodes;
//    }

    /**
     * Find a appropriate random Neighbor
     *
     * @return MySnakeNode
     */
//    public MoveResponse getRandomMove(SnakeNode whereIAm) {
//        List<SnakeNode> list = whereIAm.myNeighbors();
//
//        for (SnakeNode node : list) {
//            if (shouldProcess(node)) {
//                Move move = node.getMove();
//                return new MoveResponse(move);
//            }
//
//        }
//        System.out.println("MoveRandom failed");
//        return new MoveResponse(Move.LEFT);
//    }
    public SnakeNode getTheClosestNode(List<SnakeNode> allowedNeighbor, IPoint currentNode) {
        SnakeNode closestPoint = allowedNeighbor.get(0);
     //   List<Integer> dest = new ArrayList<>();
        int destination = manhattanDis(currentNode, closestPoint.getPoint());
        int newDestination;

        allowedNeighbor.remove(0);
        for (int i = 0; i < allowedNeighbor.size(); i++) {
            SnakeNode node = allowedNeighbor.get(i);
            if (shouldProcess(node)) {
                newDestination = (manhattanDis(currentNode, node.getPoint()));
                if (newDestination < destination) {
                    destination = newDestination;
                    closestPoint = node;
                }
            }
        }
        return closestPoint;
    }

    /**
     * @return should we process this node
     */
    public boolean shouldProcess(SnakeNode node) {

        IPoint point = node.getPoint();
        //   System.out.println("previousNode" + previousNode.getX() + " " + previousNode.getY());
//        if (node.equals(previousNode)) {
//            return false;
//        }
        //if node is out of screen MAX
        if (point.getX() > (SnakeConstants.GAME_BOARD_WIDTH) ||
                point.getY() > (SnakeConstants.GAME_BOARD_HEIGHT)) {
         //   System.out.println("OUT");
            return false;
        }
        //if node is out of screen MIN
        if (point.getX() < 0 || point.getY() < 0) {
         //   System.out.println("IN");
            return false;
        }

        boolean shouldProceed = !completeSnake.contains(point);
        //	System.out.println("shouldProcess: "+shouldProceed);
        return shouldProceed;

    }

    public List<Move> allowedDirections(int widthCoordinate, int heightCoordinate) {
        List<Move> moves = new ArrayList<>();
        moves.add(Move.LEFT);
        moves.add(Move.DOWN);
        moves.add(Move.UP);
        moves.add(Move.RIGHT);
        System.out.println("HERE");

        if (widthCoordinate == SnakeConstants.GAME_BOARD_WIDTH) {
            moves.remove(Move.RIGHT);
        }

        if (heightCoordinate == SnakeConstants.GAME_BOARD_HEIGHT) {
            moves.remove(Move.DOWN);
        }

        if (widthCoordinate == 0) {
            moves.remove(Move.RIGHT);
        }

        if (heightCoordinate == 0) {
            moves.remove(Move.UP);
        }

        moves.remove(previousMove);
        System.out.println("MOVES " + moves.size());
        return moves;
    }

//    public void getAllowedNeighbors(IPoint currentPoint) {
//        int widthCoordinate = currentPoint.getX();
//        int heightCoordinate = currentPoint.getY();
//        List<Move> moves = allowedDirections(widthCoordinate, heightCoordinate);
//        moves.forEach((move) ->
//                generateAllowedNeighborsPoint(widthCoordinate, heightCoordinate, move)
//        );
//    }
//
//    public void generateAllowedNeighborsPoint(
//            int widthCoordinate,
//            int heightCoordinate,
//            Move move) {
//
//        Point up = new Point(widthCoordinate, heightCoordinate - 1, "point");
//        Point down = new Point(widthCoordinate, heightCoordinate + 1, "point");
//        Point right = new Point(widthCoordinate + 1, heightCoordinate, "point");
//        Point left = new Point(widthCoordinate - 1, heightCoordinate, "point");
//
//        if (move.equals(Move.UP) && !completeSnake.contains(up)) {
//            allowedNeighbor.add(new SnakeNode(up, Move.UP));
//            System.out.println("Add UP point");
//        } else if (move.equals(Move.RIGHT) && !completeSnake.contains(right)) {
//            allowedNeighbor.add(new SnakeNode(up, Move.RIGHT));
//            System.out.println("Add RIGHT point");
//        } else if (move.equals(Move.LEFT) && !completeSnake.contains(left)) {
//            allowedNeighbor.add(new SnakeNode(up, Move.LEFT));
//            System.out.println("Add LEFT point");
//        } else if (move.equals(Move.DOWN) && !completeSnake.contains(down)) {
//            allowedNeighbor.add(new SnakeNode(up, Move.DOWN));
//            System.out.println("Add DOWN point");
//        }
//    }
}
