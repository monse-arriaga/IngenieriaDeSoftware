import { ParticipantResult } from "brackets-model"
import ParticipantMatchGameResult from "../types/ParticipantMatchGameResult";
import ResultT from "./Result";

class MatchGameResultT {
    transformer = new ResultT()

    to (value:  ParticipantMatchGameResult | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: this.transformer.to(value.result)
        }
    }

    from (value: ParticipantResult | null): ParticipantMatchGameResult | null{
        if (value == null) return null; 
        return {
            participant: {
                id: value.id as number
            },
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: this.transformer.from(value.result),
        }
    }
}

export default MatchGameResultT