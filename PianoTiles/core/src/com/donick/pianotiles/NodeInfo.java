package com.donick.pianotiles;


/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeInfo {

    public enum NodeHigh{
        Empty,C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5,A6,A3,B3
    }
    public enum NodeType{
        type05,type10,type15,type20,type25,type30
    }

    float startTime;
    float endTime;

    NodeInfo(float _startTime, float _endTime){
        startTime = _startTime;
        endTime = _endTime;
    }

    NodeInfo(NodeHigh nodeHigh, NodeType nodeType ){
        switch (nodeHigh){
            case C4:
                startTime = 0;break;
            case D4:
                startTime = 0.5f;break;
            case E4:
                startTime = 1.5f;break;
            case F4:
                startTime = 3f;break;
            case G4:
                startTime = 4.5f;break;
            case A4:
                startTime = 6f;break;
            case B4:
                startTime = 7.5f;break;
            case C5:
                startTime = 9.5f;break;
            case D5:
                startTime = 12.5f;break;
            case E5:
                startTime = 14.5f;break;
            case F5:
                startTime = 17f;break;
            case G5:
                startTime = 19.5f;break;
            case A6:
                startTime = 21f;break;
            case A3:
                startTime = 22f;break;
            case B3:
                startTime = 25f;break;
            default:
                startTime = endTime = 0;
                return;
        }
        endTime = startTime + getNodeTypeDuration(nodeType);
    }

    float getNodeTypeDuration(NodeType nodeType){
        switch (nodeType){
            case type05: return 0.5f;
            case type10: return 1.0f;
            case type15: return 1.5f;
            case type20: return 2.0f;
            case type25: return 2.5f;
            case type30: return 3.0f;
        }
        return 0;
    }

}
