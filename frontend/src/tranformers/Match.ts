import { Match} from "brackets-model"
import MyMatch from "../types/MyMatch"
import MatchResultT from "./MatchResult"
import MatchStatus from "./MatchStatus";

class MatchT {

    resultT = new MatchResultT();

    to (value:  MyMatch): Match {
        return {
            id: value.id,
            group_id: value.group.id,
            stage_id: value.stage.id,
            round_id: value.round.id,
            child_count: value.childCount,
            status: MatchStatus.to(value.matchStatus),
            opponent1: this.resultT.to(value.opponentOneResult),
            opponent2: this.resultT.to(value.opponentTwoResult),
            number: value.number
        }
    }

    from (value: Match): MyMatch {
        return {
            id: value.id as number,
            matchStatus: MatchStatus.from(value.status),
            opponentOneResult: this.resultT.from(value.opponent1),
            opponentTwoResult: this.resultT.from(value.opponent2),
            stage: {
                id: value.stage_id as number
            },
            group: {
                id: value.group_id as number
            },
            round: {
                id: value.round_id as number
            },
            number: value.number,
            childCount: value.child_count
        }
    }
}

export default MatchT