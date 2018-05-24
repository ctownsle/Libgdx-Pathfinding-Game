package com.mygdx.game;


import java.util.*;

public class SearchAlgorithm {

    public HashMap<Location, Short> costSoFar = new HashMap<>();
    public HashMap<Location, Location> cameFrom = new HashMap<>();
    public HashSet<Location> visited = new HashSet<>();

    public List<Location> dijkstras(byte[][] grid, int startx, int starty, int endx, int endy, boolean astar) {
        // ending condition


        MPriorityQueue<Location> p = new MPriorityQueue<>();
        Location startLocation = new Location(startx, starty);
        p.enqueue(startLocation, (byte) 0);
        Location endLocation = new Location(endx, endy);

        if (grid[startx][starty] != 10) {
            cameFrom.put(startLocation, startLocation);
            costSoFar.put(startLocation, (short) 0);

        } else
            return null;
        while (p.count() > 0) {

            Location current = p.dequeue();
            //System.out.println(current);
            if (current.equals(endLocation)) {
                break;
            }
            HashSet<Location> neighbors = new HashSet<>();
            int currentx;

            currentx = current.getX();
            int currenty;
            currenty = current.getY();
            boolean isTeleport = false;
            if(grid[currentx][currenty] == 0){
                isTeleport = true;
            }
            if (grid[currentx][currenty] != 10) {
                if (currentx - 1 >= 0 && grid[currentx -1][currenty] != 10 && grid[currentx][currenty] != 0) {
                    Location leftN = new Location(currentx -1, currenty);
                    neighbors.add(leftN);
            //        System.out.println(leftN);
              //      System.out.println(visited);
                    if(!visited.contains(leftN)) {
                        visited.add(leftN);
                    }
                } else if(isTeleport){
                    if(TeleportCase.getPairs().get(current)!= null) {
                        Location getPair = TeleportCase.getPairs().get(current);
                        if (getPair.getX() - 1 >= 0 && grid[getPair.getX() - 1][getPair.getY()] != 10) {
                            visited.add(getPair);
                            Location leftN = new Location(getPair.getX() - 1, getPair.getY());
                            visited.add(leftN);
                            neighbors.add(leftN);
                        }
                    }
                }
                if (currentx + 1 <= grid.length - 1 && grid[currentx + 1][currenty] != 10 && grid[currentx][currenty] != 0) {
                    Location rightN = new Location(currentx +1, currenty);
                    neighbors.add(rightN);
                    if(!visited.contains(rightN)) {
                        visited.add(rightN);
                    }
                } else if(isTeleport){
                    if(TeleportCase.getPairs().get(current)!= null) {
                        Location getPair = TeleportCase.getPairs().get(current);
                        if (getPair.getX() + 1 <= grid.length - 1 && grid[getPair.getX() + 1][getPair.getY()] != 10) {
                            visited.add(getPair);
                            Location rightN = new Location(getPair.getX() + 1, getPair.getY());
                            visited.add(rightN);
                            neighbors.add(rightN);
                        }
                    }
                }

                if (currenty + 1 <= grid[0].length - 1 && grid[currentx][currenty+1] != 10 && grid[currentx][currenty] != 0) {
                    Location upN = new Location(currentx, currenty + 1);
                    neighbors.add(upN);
                    if(!visited.contains(upN)) {
                        visited.add(upN);
                    }
                } else if(isTeleport){
                    if(TeleportCase.getPairs().get(current)!= null) {
                        Location getPair = TeleportCase.getPairs().get(current);
                        if (getPair.getY() + 1 <= grid[0].length - 1 && grid[getPair.getX()][getPair.getY() + 1] != 10) {
                            visited.add(getPair);
                            Location upN = new Location(getPair.getX(), getPair.getY() + 1);
                            visited.add(upN);
                            neighbors.add(upN);
                        }
                    }
                }
                if (currenty - 1 >= 0 && grid[currentx][currenty-1] != 10 && !isTeleport) {
                    if(isTeleport){
                        System.out.println("nani");
                    }
                    Location downN = new Location(currentx, currenty-1);
                    neighbors.add(downN);
                    if(!visited.contains(downN)) {
                        visited.add(downN);
                    }
                } else if(isTeleport){
                    if(TeleportCase.getPairs().get(current)!= null) {
                        Location getPair = TeleportCase.getPairs().get(current);
                        if (getPair.getY() - 1 >= 0 && grid[getPair.getX()][getPair.getY() - 1] != 10) {
                            visited.add(getPair);
                            Location downN = new Location(getPair.getX(), getPair.getY() - 1);
                            visited.add(downN);
                            neighbors.add(downN);
                        }
                    }
                }

                for (Location n : neighbors) {
                    short new_cost;
                    short priority;
                    if(grid[n.getX()][n.getY()] == 10){
                        new_cost = Byte.MAX_VALUE;
                    } else {
                        new_cost = (short) (costSoFar.get(current) + grid[n.getX()][n.getY()]);
                    }

                    //System.out.println(new_cost);//costSoFar[currentx][currenty] + grid[n.x][n.y];
                    if (!costSoFar.containsKey(n) || new_cost < costSoFar.get(n)) {
                        costSoFar.put(n, new_cost);
                        if(astar) {
                            priority = (short) (new_cost + 2 * getHeuristic(n, new Location(endx, endy)));
                            p.enqueue(n, priority);
                            cameFrom.put(n, current);
                        }
                        else {

                            priority = new_cost;
                            p.enqueue(n, priority);
                            cameFrom.put(n, current);
                        }
                    }


                }

            }


        }
        return getPath(new Location(startx, starty), new Location(endx, endy), grid);
    }

    static public double getHeuristic(Location a, Location b){
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private List<Location> getPath(Location start, Location end, byte[][] grid){
        //Location startPos = start;
        //Location endPos = end;
        List<Location> path = new ArrayList<>();
        Location current = end;
        while (!current.equals(start)){
            if(grid[current.getX()][current.getY()] == 0){
                path.add(current);
                path.add(TeleportCase.getPairs().get(current));
                current = cameFrom.get(current);
            } else {
                path.add(current);
                current = cameFrom.get(current);
            }
        }
        path.add(start);
        return path;
    }


}
