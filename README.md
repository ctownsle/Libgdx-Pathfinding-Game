# Libgdx-Pathfinding-Game

# INSTRUCTIONS
<html>
<body bgcolor="#FFFFFF">
<center>
<h1>CSC455: Homework 2</h1>
</center>
<hr />
<p>In this homework you will implement several pathfinding algorithms on a grid, and you will visualize the shortest path that you have found.</p>
<p>You are given a game map in the shape of a square grid in the following format:<br />
1 3 3 2 F 5 8<br />
2 F 1 8 3 1 2<br />
1 2 2 5 2 1 3<br />
where every number represents the terrain of the respective grid cell. F means impassable. 1 means easily passable; 9 means nearly impossible to pass. These are effectively the weights assigned with stepping on the respective tile.</p>
<p>Limits: the map will be no larger than 10000x10000 cells, and the weights between passable tiles will be between 1 and 9.</p>
<p>Implement an algorithm that finds the shortest path between two tiles on the map <b>optimally</b>. The starting and ending tile are selected by the user by clicking / touching the respective tile. Obviously, display the grid (so the user can select a starting and an ending tile), and denote the tiles your algorithm is exploring as it explores them. When your algorithm is done, there should be three colors on the grid: untouched tiles, explored tiles, and tiles that form the shortest path.</p>
<p>An interesting addition (that you still have to do) involves teleports. Some tiles on the map form direct links with other tiles (weight 0). Those are designated in the following way:<br />
1 3 3 T1 F T2 8<br />
2 F T2 8 3 1 2<br />
T1 2 2 5 2 1 3<br />
where T1 -- T1 is a teleport between the respective tiles, and T2 -- T2 is another such teleport. There will only ever be pairs of teleports linked in this way, but you do not know how many teleports there will be. It is possible, for example, that there are none; it is also possible that every tile on the map is a teleport.</p>
<p>Make sure that your algorithms handle the case with teleports, too.</p>
<p>Submit your sources to the dropbox before the deadline. You will also have to demonstrate your working homework to me within a week after the deadline.</p>
<hr />
</body>
</html>

# My Implementation

Use Tiled textures to generate a simple tile map to grab cells, and populate a grid with those. To visualize the path, click on one tile, then click on a second tile and the path will be visualized in RED. All tiles considered will be BLACK. Shortest paths will use A*, unless there is teleporters, in which case basic Djikstra's will be used. I use manhattan distance as the heuristic for A* searching. 

## Notes

Super unoptimized for larger grids, changes can be made to render the grid as the camera moves. 

Textures are strange because I didn't want to spend too much time generating them.

There is no way to currently restart the game, you have to kill the program then rerun it.

I made a side program to generate a map in the correct format, for simplicity sake, I won't include it because it is rather uninteresting.
