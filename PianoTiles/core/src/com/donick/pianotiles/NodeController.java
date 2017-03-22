package com.donick.pianotiles;

import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeController {
    static Array<NodeInfo> createNodeArraySong1(){
        Array<NodeInfo> result = new Array<NodeInfo>(100);
        result.add(new NodeInfo(0,1.025f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,2.2f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,3.5f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,4.7f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,5.9f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,7.1f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,8.3f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,9.5f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,10.6f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,11.8f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,13.1f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,14.3f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,15.4f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,16.6f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,17.8f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,19.0f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,20.2f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,21.3f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,22.4f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,22.8f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,24f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,25f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,26.3f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,27.4f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,28.6f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,29.4f));
        result.add(new NodeInfo(result.get(result.size-1).endTime,29.7f));
//        result.add(new NodeInfo(result.get(result.size-1).endTime,f));
//        result.add(new NodeInfo(result.get(result.size-1).endTime,f));
//        result.add(new NodeInfo(result.get(result.size-1).endTime,f));
//        result.add(new NodeInfo(result.get(result.size-1).endTime,f));
//        result.add(new NodeInfo(result.get(result.size-1).endTime,f));
        return result;
    }
}
