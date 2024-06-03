import { Result } from "brackets-model";
import MatchResult from "../types/MatchResult";

class ResultT {

    to (value?:  MatchResult): Result{
        switch (value) {
            case MatchResult.LOSS:
                return "loss";
            case MatchResult.WIN:
                return "win";        
            default:
                return "draw";
        }
    }

    from (value?: Result): MatchResult {
        switch (value) {
            case "loss":
                return MatchResult.LOSS;
            case "win":
                return MatchResult.WIN;    
            default:
                return MatchResult.DRAW;
        }
    }
}

export default new ResultT()