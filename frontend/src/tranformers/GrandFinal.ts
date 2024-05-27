import { GrandFinalType } from "brackets-model";
import MyGrandFinalType from "../types/MyGrandFinalType";

class GrandFinalT {

    to (value?:  MyGrandFinalType): GrandFinalType {
        switch (value) {
            case MyGrandFinalType.DOUBLE:
                return "double";
            case MyGrandFinalType.SIMPLE:
                return "simple";        
            default:
                return "none";
        }
    }

    from (value?: GrandFinalType): MyGrandFinalType {
        switch (value) {
            case "simple":
                return MyGrandFinalType.SIMPLE;
            case "double":
                return MyGrandFinalType.DOUBLE;    
            default:
                return MyGrandFinalType.NONE;
        }
    }
}

export default new GrandFinalT()