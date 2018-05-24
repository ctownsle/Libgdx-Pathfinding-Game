package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ParseText {

    FileHandle file = Gdx.files.internal("map03.txt");
    // boolean isFileGucci = false;
    public boolean hasTeleports = true;
    private byte[][] finalmap;
    private ArrayList<Location> allTeles = new ArrayList<>();
    private int size;
    private ArrayList<Location> mappedTps = new ArrayList<>();

    public ParseText(int size) throws IOException {

        boolean isFileGucci = Gdx.files.local("map03.txt").exists();
        this.size = size;
        if (isFileGucci) {
            // read from file
            File f = file.file();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String[] useful = new String[size];// = new String[size];
            int counter = 0;
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                    //System.out.println(line);

                    if(line != null){
                        useful[counter] = line;
                       // System.out.println(useful[counter]);
                        counter++;
                    }

                }
                //String text = file.readString();
                //String lines[] = text.split("\\n");
                //System.out.println(lines[0]);
                //System.out.println(text);
            } finally {
                br.close();
            }
            parseGraph(useful);
            //System.out.println(useful.length);
        }

    }

    private void parseGraph(String[] yeet){
        byte[][] map = new byte[size][size];
       // String[] tempvar = new String[];
        System.out.println(map.length + ":" + map[0].length);
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++) {
                String temp = yeet[i];
                String temp2[] = temp.split(" ");
               // System.out.println(yeet.length + " " +  yeet[0].length());
                map[i][j] = Byte.parseByte(temp2[j]);
                if(map[i][j] == 0){
                  Location jeff = new Location(i, j);
                  allTeles.add(jeff);
                }
            }
         //   tempvar = temp.split(" ");
        }
        if(hasTeleports){
            executeTpCondition();
        }
        finalmap = map;

    }

    public byte[][] getFinalmap() {
        return finalmap;
    }

    private void createTpMappings(){
        for(int i = 0; i < allTeles.size() - 1; i++){
            if(!mappedTps.contains(allTeles.get(i)) && !mappedTps.contains(allTeles.get(i + 1))){
                new TeleportCase(allTeles.get(i), allTeles.get(i + 1));
                new TeleportCase(allTeles.get(i + 1), allTeles.get(i));
                mappedTps.add(allTeles.get(i));
                mappedTps.add(allTeles.get(i + 1));
            }

        }
        System.out.println(TeleportCase.getPairs());
    }
    public void executeTpCondition(){
        createTpMappings();
    }
}
