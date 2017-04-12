package com.donick.pianotiles;

import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeController {
    static Array<NodeInfo> createNodeArraySong1(){
        Array<NodeInfo> result = new Array<NodeInfo>(150);
        

        // bar 1
//        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A6, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
//bar 2

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type20));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
// bar 3
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A6, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
//bar 4
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type30));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));

        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
// bar 5
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));

        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));
// page 2
// bar 6
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type15));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type10));

//        result.add(new NodeInfo(NodeInfo.NodeHigh.A3));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
// bar 7
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

//bar 8
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
// bar 9
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type10));
//        result.add(new NodeInfo(NodeInfo.NodeHigh.A3));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type20));
       // bar 10
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type15));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A6, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));

     // page 3
        // bar 11
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type15));
// bar 12
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type15));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.F5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.A6, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        // bar 13
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type30));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        // bar 14
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type05));
     //bar 15
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.G5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type10));

        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.E5, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.A4, NodeInfo.NodeType.type15));
        //page 4
   // bar 16
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.D5, NodeInfo.NodeType.type10));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));

        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.B4, NodeInfo.NodeType.type15));
        result.add(new NodeInfo(NodeInfo.NodeHigh.Empty, NodeInfo.NodeType.type05));
        result.add(new NodeInfo(NodeInfo.NodeHigh.C5, NodeInfo.NodeType.type15));


        return result;
    }
}
