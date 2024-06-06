import { RoundRobinMode } from "brackets-model"
import MyRoundRobinMode from "../types/MyRoundRobinMode"

class RoundRobinT {

    to (value?:  MyRoundRobinMode): RoundRobinMode {
        switch (value) {
            case MyRoundRobinMode.SIMPLE:
                return "simple";
            default:
                return "double";
        }
    }

    from (value?: RoundRobinMode): MyRoundRobinMode {
        switch (value) {
            case "simple":
                return MyRoundRobinMode.SIMPLE;
            default:
                return MyRoundRobinMode.DOUBLE;
        }
    }
}

export default new RoundRobinT()