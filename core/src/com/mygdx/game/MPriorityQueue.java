package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class MPriorityQueue<T> {
    private List<Tuple<T, Short>> elements = new ArrayList<>();

    public int count(){
        return elements.size();
    }

    public void enqueue(T item, short priority){
        //System.out.println(item + " " + priority);
        elements.add(new Tuple<>(item, priority));
    }

    public T dequeue(){
        int bestIndex = 0;

        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).y < elements.get(bestIndex).y){
                bestIndex = i;
            }
        }

        T bestItem = elements.get(bestIndex).x;
        elements.remove(bestIndex);
        return bestItem;
    }
}
