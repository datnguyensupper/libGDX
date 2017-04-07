package com.donick.pianotiles;

import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeController {
    static Array<NodeInfo> createNodeArraySong1(){
        Array<NodeInfo> result = new Array<NodeInfo>(150);

        // bar 1
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A6));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
//bar 2

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));

        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
// bar 3
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A6));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
//bar 4
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
// bar 5
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
// page 2
// bar 6
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));

//        result.add(new NodeInfo(NodeInfo.NodeType.A3));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
// bar 7
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));

//bar 8
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));

        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
// bar 9
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));

        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
//        result.add(new NodeInfo(NodeInfo.NodeType.A3));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
       // bar 10
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));

        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A6));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

     // page 3
        // bar 11
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G4));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
// bar 12
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.F5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.A6));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        // bar 13
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        // bar 14
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
     //bar 15
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));

        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.G5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));

        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.E5));
        result.add(new NodeInfo(NodeInfo.NodeType.A4));
        //page 4
   // bar 16
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.D5));
        result.add(new NodeInfo(NodeInfo.NodeType.Empty));

        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.B4));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));
        result.add(new NodeInfo(NodeInfo.NodeType.C5));


        return result;
    }
}
