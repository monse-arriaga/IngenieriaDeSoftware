import { MatchGame } from "brackets-model";
import MyMatchGame from "../types/MyMatchGame";
import MatchGameResultT from "./MatchGameResult";

class MatchGameT {

    resultT = new MatchGameResultT;

    to (value:  MyMatchGame): MatchGame {
        return {
            id: value.id,
            parent_id: value.match.id,
            stage_id: value.stage.id,
            status: value.matchStatus as number,
            opponent1: this.resultT.to(value.opponentOneResult),
            opponent2: this.resultT.to(value.opponentTwoResult),
            number: value.number
        }
    }

    from (value: MatchGame): MyMatchGame {
        return {
            id: value.id as number,
            matchStatus: value.status as number,
            opponentOneResult: this.resultT.from(value.opponent1),
            opponentTwoResult: this.resultT.from(value.opponent2),
            stage: {
                id: value.stage_id as number
            },
            number: value.number,
            match: {
                id: value.parent_id as number
            }
        }
    }
}

export default MatchGameT