package com.donick.pianotiles;


/**
 * Created by nguyen on 3/22/2017.
 */
public class NodeInfo {

    public enum NodeType{
        C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5
    }

    float startTime;
    float endTime;

    NodeInfo(float _startTime, float _endTime){
        startTime = _startTime;
        endTime = _endTime;
    }

    NodeInfo(NodeType nodeType){
        switch (nodeType){
            case C4:
                startTime = 0;endTime=0.5f;break;
            case D4:
                startTime = 0.5f;endTime=1f;break;
            case E4:
                startTime = 1.5f;endTime=2f;break;
            case F4:
                startTime = 3f;endTime=3.5f;break;
            case G4:
                startTime = 4.5f;endTime=5f;break;
            case A4:
                startTime = 6f;endTime=6.5f;break;
            case B4:
                startTime = 7.5f;endTime=8f;break;
            case C5:
                startTime = 9.5f;endTime=10f;break;
            case D5:
                startTime = 12.5f;endTime=13f;break;
            case E5:
                startTime = 14.5f;endTime=15f;break;
            case F5:
                startTime = 17f;endTime=17.5f;break;
            case G5:
                startTime = 19.5f;endTime=20f;break;
        }
    }

}
