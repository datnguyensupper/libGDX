package com.donick.pianotiles;

import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeController {
    static Array<NodeInfo> createNodeArraySong1(){
        Array<NodeInfo> result = new Array<NodeInfo>(150);
        result.add(new NodeInfo(NodeInfo.NodeType.C4));
        result.add(new NodeInfo(NodeInfo.NodeType.D4));
        result.add(new NodeInfo(NodeInfo.NodeType.E4));
        result.add(new NodeInfo(NodeInfo.NodeType.F4));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        return result;
    }
}
