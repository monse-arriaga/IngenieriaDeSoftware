import { Result } from "brackets-model";
import MatchResult from "../types/MatchResult";

class ResultT {

    to (value?:  MatchResult): Result | undefined{
        switch (value) {
            case MatchResult.LOSS:
                return "loss";
            case MatchResult.WIN:
                return "win"; 
            case null:
                return undefined;
            default:
                return "draw";
        }
    }

    from (value?: Result): MatchResult | undefined {
        switch (value) {
            case "loss":
                return MatchResult.LOSS;
            case "win":
                return MatchResult.WIN;
            case undefined:
                return null as any;
            default:
                return MatchResult.DRAW;
        }
    }
}

export default ResultT